package fediverse.writefreely.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import fediverse.writefreely.api.model.Appearance;
import fediverse.writefreely.api.model.CrosspostInfo;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.function.Function;
import java.util.logging.Logger;
import okhttp3.Authenticator;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

abstract class WriteFreelyAPIabstract {
	static final String     APPLICATION_JSON = "application/json";
	static final MediaType    APP_JSON_MEDIA = MediaType.parse(WriteFreelyAPIabstract.APPLICATION_JSON);
	static final int       SUCCESSFUL_DELETE = HTTPstatus.NO_CONTENT.getCode();	
	             String            authToken = "";

	static final String convertArrayToJSON(final String          firstKey,
	                                       final String         secondKey,
	                                       final String[][] arrayOfArrays) {
		String result = "[";

		for(final String[] array : arrayOfArrays) {
			result += "{ \"" +  firstKey + "\": \"" + array[0] + "\"";

			if(array.length == 1) {
				result += ", \"" + secondKey + "\": \"" + array[1] + "\"";
			}

			result += " }";
		}

		return result + "]";
	}

	static final JsonSerializer<CrosspostInfo> createJSCI() {
		return new JsonSerializer<CrosspostInfo>() {
			@Override
			public JsonObject serialize(final CrosspostInfo            ci,
			                            final Type                     type,
			                            final JsonSerializationContext context) throws JsonParseException {
				final JsonObject jo = new JsonObject();
				jo.addProperty(ci.getSocialNetwork().getID(), ci.getUsername());

				return jo;
			}
		};
	}

	static final <T> JsonDeserializer<T> createJD(final Function<JsonElement, T> f) {
		return new JsonDeserializer<T>() {
			@Override
			public T deserialize(final JsonElement                json,
			                     final Type                       type,
			                     final JsonDeserializationContext context) throws JsonParseException {
				return f.apply(json);
			}
		};
	}

	static final JsonDeserializer<ZonedDateTime> createJDZDT() {
		return WriteFreelyAPIabstract.createJD((final JsonElement json) -> ZonedDateTime.parse(json.getAsJsonPrimitive()
		                                                                                           .getAsString()));
	}

	static final JsonDeserializer<Appearance> createJDA() {
		return WriteFreelyAPIabstract.createJD((final JsonElement json) -> Appearance.findByFont(json.getAsJsonPrimitive()
		                                                                                             .getAsString()));
	}

	static final JsonDeserializer<HTTPstatus> createJDHS() {
		return WriteFreelyAPIabstract.createJD((final JsonElement json) -> HTTPstatus.findByCode(json.getAsJsonPrimitive()
		                                                                                             .getAsString()));
	}

	final Interceptor generateInterceptor(final Logger log) {
		return new Interceptor() {
			@Override
			public Response intercept(final Chain chain) throws IOException {
				final Request inRequest          = chain.request();
				final String  inRequestURLstring = inRequest.url().toString();
				log.finest("Intercepting Request: " + inRequestURLstring);

				// Add Headers to request
				log.finest("Adding headers and building.");
				final Request updatedRequest     = inRequest.newBuilder()
				                                            .addHeader("Content-Type",           WriteFreelyAPIabstract.APPLICATION_JSON)
				                                            .addHeader("Authorization", "Token " + WriteFreelyAPIabstract.this.authToken)
				                                            .build();
				log.finest("Outbound Request: "            +
				           updatedRequest.url().toString());

				return chain.proceed(updatedRequest);
			}
		};
	}

	final Endpoints generateEndpoints(final String        url,
	                                  final Logger        log,
	                                  final Authenticator auth) {
		final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
		final OkHttpClient           okHTTPclient       = auth == null                                                   ?
		                                                  new OkHttpClient.Builder()
		                                                                  .addInterceptor(this.generateInterceptor(log))
		                                                                  .addInterceptor(loggingInterceptor)
		                                                                  .build()                                       :
		                                                  new OkHttpClient.Builder()
		                                                                  .addInterceptor(this.generateInterceptor(log))
		                                                                  .addInterceptor(loggingInterceptor)
		                                                                  .authenticator(auth)
		                                                                  .build();
		final Gson                   gson               = new GsonBuilder().registerTypeAdapter(CrosspostInfo.class, WriteFreelyAPIabstract.createJSCI())
		                                                                   .registerTypeAdapter(Appearance.class,    WriteFreelyAPIabstract.createJDA())
		                                                                   .registerTypeAdapter(HTTPstatus.class,    WriteFreelyAPIabstract.createJDHS())
		                                                                   .registerTypeAdapter(ZonedDateTime.class, WriteFreelyAPIabstract.createJDZDT())
		                                                                   .create();

		return new Retrofit.Builder()
		                   .baseUrl(url)
		                   .client(okHTTPclient)
		                   .addConverterFactory(GsonConverterFactory.create(gson))
		                   .build()
		                   .create(Endpoints.class);
	}
}
