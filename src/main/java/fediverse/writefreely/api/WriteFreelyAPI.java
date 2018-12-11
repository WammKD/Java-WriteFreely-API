package fediverse.writefreely.api;

import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.MediaType;
import java.util.logging.Logger;
import java.net.URL;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.Interceptor.Chain;
import java.io.IOException;
import okhttp3.Request;
import com.google.gson.JsonSerializer;
import fediverse.writefreely.api.model.CrosspostInfo;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonParseException;
import com.google.gson.JsonDeserializer;
import java.time.ZonedDateTime;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import fediverse.writefreely.api.model.Appearance;
import java.util.function.Function;
import java.net.MalformedURLException;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Authenticator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import fediverse.writefreely.api.model.ResponseWrapper;
import fediverse.writefreely.api.model.PostReturned;
import fediverse.writefreely.api.model.PostCreated;
import fediverse.writefreely.api.model.PostUpdate;
import fediverse.writefreely.api.model.Collection;

public class WriteFreelyAPI {
	private static final String     APPLICATION_JSON = "application/json";
	private static final MediaType    APP_JSON_MEDIA = MediaType.parse(WriteFreelyAPI.APPLICATION_JSON);
	private static final int       SUCCESSFUL_DELETE = HTTPstatus.NO_CONTENT.getCode();
	private static final Logger                  LOG = Logger.getLogger(WriteFreelyAPI.class.getName());
	private        final Endpoints         endpoints;
	private        final URL                  domain;
	private              String            authToken = "";

	private static final String convertArrayToJSON(final String          firstKey,
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

	private static final JsonSerializer<CrosspostInfo> createJSCI() {
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

	private static final <T> JsonDeserializer<T> createJD(final Function<JsonElement, T> f) {
		return new JsonDeserializer<T>() {
			@Override
			public T deserialize(final JsonElement                json,
			                     final Type                       type,
			                     final JsonDeserializationContext context) throws JsonParseException {
				return f.apply(json);
			}
		};
	}

	private static final JsonDeserializer<ZonedDateTime> createJDZDT() {
		return WriteFreelyAPI.createJD((final JsonElement json) -> ZonedDateTime.parse(json.getAsJsonObject()
		                                                                                   .getAsString()));
	}

	private static final JsonDeserializer<Appearance> createJDA() {
		return WriteFreelyAPI.createJD((final JsonElement json) -> Appearance.findByFont(json.getAsJsonPrimitive()
		                                                                                     .getAsString()));
	}

	private static final JsonDeserializer<HTTPstatus> createJDHS() {
		return WriteFreelyAPI.createJD((final JsonElement json) -> HTTPstatus.findByCode(json.getAsJsonPrimitive()
		                                                                                     .getAsString()));
	}

	private final Interceptor generateInterceptor(final Logger log) {
		return new Interceptor() {
			@Override
			public Response intercept(final Chain chain) throws IOException {
				final Request inRequest          = chain.request();
				final String  inRequestURLstring = inRequest.url().toString();
				log.finest("Intercepting Request: " + inRequestURLstring);

				// Add Headers to request
				log.finest("Adding headers and building.");
				final Request updatedRequest     = inRequest.newBuilder()
				                                            .addHeader("Content-Type",  WriteFreelyAPI.APPLICATION_JSON)
				                                            .addHeader("Authorization", WriteFreelyAPI.this.authToken)
				                                            .build();
				log.finest("Outbound Request: "            +
				           updatedRequest.url().toString());

				return chain.proceed(updatedRequest);
			}
		};
	}

	private final Endpoints generateEndpoints(final Logger        log,
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
		final Gson                   gson               = new GsonBuilder().registerTypeAdapter(CrosspostInfo.class, WriteFreelyAPI.createJSCI())
		                                                                   .registerTypeAdapter(Appearance.class,    WriteFreelyAPI.createJDA())
		                                                                   .registerTypeAdapter(HTTPstatus.class,    WriteFreelyAPI.createJDHS())
		                                                                   .registerTypeAdapter(ZonedDateTime.class, WriteFreelyAPI.createJDZDT())
		                                                                   .create();

		return new Retrofit.Builder()
		                   .baseUrl(this.domain.toString())
		                   .client(okHTTPclient)
		                   .addConverterFactory(GsonConverterFactory.create(gson))
		                   .build()
		                   .create(Endpoints.class);
	}

	public WriteFreelyAPI(final String domain) throws MalformedURLException {
		this.domain    = new URL(domain);
		this.endpoints = this.generateEndpoints(WriteFreelyAPI.LOG,
		                                        null);
	}

	public WriteFreelyAPI(final URL domain) {
		this.domain    = domain;
		this.endpoints = this.generateEndpoints(WriteFreelyAPI.LOG,
		                                        null);
	}

	public ResponseWrapper<PostReturned> publishPost(final PostCreated post) throws IOException {
		return this.endpoints.publishPost(post).execute().body();
	}

	public ResponseWrapper<PostReturned> retrievePost(final String postID) throws IOException {
		return this.endpoints.retrievePost(postID).execute().body();
	}

	public ResponseWrapper<PostReturned> updatePost(final String     postID,
	                                                final PostUpdate post) throws IOException {
		return this.endpoints.updatePost(postID, post).execute().body();
	}

	public boolean deletePost(final String postID) throws IOException {
		return this.endpoints.deletePost(postID).execute().code() == WriteFreelyAPI.SUCCESSFUL_DELETE;
	}

	public boolean deletePost(final String postID,
	                          final String token) throws IOException {
		return this.endpoints.deletePost(postID,
		                                 token).execute().code() == WriteFreelyAPI.SUCCESSFUL_DELETE;
	}

	public ResponseWrapper<Collection> retrieveCollection(final String alias) throws IOException {
		return this.endpoints.retrieveCollection(alias).execute().body();
	}

	public ResponseWrapper<PostReturned> retrievePostByCollection(final String cAlias,
	                                                              final String pSlug) throws IOException {
		return this.endpoints.retrievePostByCollection(cAlias,
		                                               pSlug).execute().body();
	}

	public ResponseWrapper<Collection> retrievePostsByCollection(final String cAlias) throws IOException {
		return this.endpoints.retrievePostsByCollection(cAlias).execute().body();
	}

	public ResponseWrapper<Collection> retrievePostsByCollection(final String  cAlias,
	                                                             final boolean isFormatted) throws IOException {
		if(isFormatted) {
			return this.endpoints.retrievePostsByCollection(cAlias,
			                                                "html").execute().body();
		} else {
			return this.endpoints.retrievePostsByCollection(cAlias).execute().body();
		}
	}
}
