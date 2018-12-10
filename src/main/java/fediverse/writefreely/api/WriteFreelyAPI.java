package fediverse.writefreely.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import fediverse.writefreely.api.model.Appearance;
import fediverse.writefreely.api.model.Collection;
import fediverse.writefreely.api.model.CrosspostInfo;
import fediverse.writefreely.api.model.PostCreated;
import fediverse.writefreely.api.model.PostReturned;
import fediverse.writefreely.api.model.PostUpdate;
import fediverse.writefreely.api.model.ResponseWrapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.logging.Logger;
import okhttp3.Authenticator;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  Hello world!
 */
public class WriteFreelyAPI {
	private static final String         APPLICATION_JSON = "application/json";
	private static final int           SUCCESSFUL_DELETE = HTTPstatus.NO_CONTENT.getCode();
	private static final Logger                      LOG = Logger.getLogger(WriteFreelyAPI.class.getName());
	private        final Endpoints             endpoints;
	private        final URL                      domain;
	private        final String                 username;
	private        final String                 passcode;
	private        final Interceptor         interceptor = new Interceptor() {
	                                                       	@Override
	                                                       	public Response intercept(final Chain chain) throws IOException {
	                                                       		final Request         inRequest          = chain.request();
	                                                       		final String          inRequestURLstring = inRequest.url().toString();
	                                                       		LOG.finest("Intercepting Request: " +
	                                                       		           inRequestURLstring);

	                                                       		// Add Headers to request
	                                                       		LOG.finest("Adding headers and building.");
	                                                       		final Request.Builder builder            = inRequest.newBuilder()
	                                                       		                                                    .addHeader("Content-Type",
	                                                       		                                                               APPLICATION_JSON);

	                                                       		// if(!WriteFreelyAPI.this.username.isEmpty()) {
	                                                       			builder.addHeader("Authorization",
	                                                       			                  WriteFreelyAPI.this.authToken);
	                                                       		// }

	                                                       		final Request         updatedRequest     = builder.build();
	                                                       		LOG.finest("Outbound Request: "            +
	                                                       		           updatedRequest.url().toString());

	                                                       		return chain.proceed(updatedRequest);
	                                                       	}
	                                                       };
	@SuppressWarnings("RequireThis")
	private        final Authenticator     authenticator = new Authenticator() {
	                                                       	@Override
	                                                       	public Request authenticate(final Route    route,
	                                                       	                            final Response response) throws IOException {
	                                                       		// If we've failed 3 times, give up.
	                                                       		if(responseCount(response) >= 3) {
	                                                       			LOG.warning("Unable to authenticate to API " +
	                                                       			            "after 3 attempts; giving up!");

	                                                       			return null;
	                                                       		}

	                                                       		LOG.fine("Intercepted 401 UnAuthorized for URL: " +
	                                                       		         response.request().url().toString());

	                                                       		final Request req = new Request.Builder()
	                                                       		                               .url(domain + Endpoints.AUTH)
	                                                       		                               .addHeader("Content-Type", APPLICATION_JSON)
	                                                       		                               .post(RequestBody.create(MediaType.parse(APPLICATION_JSON),
	                                                       		                                                        "{ \"alias\": \"" + username + "\", " +
	                                                       		                                                        "   \"pass\": \"" + passcode + "\" }"))
	                                                       		                               .build();
	                                                       		authToken = new JsonParser().parse(new OkHttpClient().newCall(req)
	                                                       		                                                     .execute()
	                                                       		                                                     .body()
	                                                       		                                                     .string())
	                                                       		                            .getAsJsonObject()
	                                                       		                            .get("data")
	                                                       		                            .getAsJsonObject()
	                                                       		                            .get("access_token")
	                                                       		                            .getAsString();

	                                                       		LOG.finest("Acquired Mall API authorization token.");

	                                                       		return response.request()
	                                                       		               .newBuilder()
	                                                       		               .header("Content-Type",  APPLICATION_JSON)
	                                                       		               .header("Authorization",        authToken)
	                                                       		               .build();
	                                                       	}

	                                                       	private int responseCount(final Response resp) {
	                                                       		Response it     = resp;
	                                                       		int      result = 1;

	                                                       		while((it = it.priorResponse()) != null) {
	                                                       			result++;
	                                                       		}

	                                                       		return result;
	                                                       	}
	                                                       };
	private              String                authToken = "";

	private JsonSerializer<CrosspostInfo> createJSCI() {
		return new JsonSerializer<CrosspostInfo>() {
		       	@Override
		       	public JsonObject serialize(final CrosspostInfo            ci,
		       	                            final Type                     type,
		       	                            final JsonSerializationContext context) throws JsonParseException {
		       		final JsonObject jo = new JsonObject();
		       		jo.addProperty(ci.getSocialNetwork().getID(),
		       		               ci.getUsername());

		       		return jo;
		       	}
		       };
	}

	private JsonDeserializer<ZonedDateTime> createJDZDT() {
		return new JsonDeserializer<ZonedDateTime>() {
		       	@Override
		       	public ZonedDateTime deserialize(final JsonElement                json,
		       	                                 final Type                       type,
		       	                                 final JsonDeserializationContext context) throws JsonParseException {
		       		return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString());
		       	}
		       };
	}

	private JsonDeserializer<Appearance> createJDA() {
		return new JsonDeserializer<Appearance>() {
		       	@Override
		       	public Appearance deserialize(final JsonElement                json,
		       	                              final Type                       type,
		       	                              final JsonDeserializationContext context) throws JsonParseException {
		       		return Appearance.findByFont(json.getAsJsonPrimitive().getAsString());
		       	}
		       };
	}

	private JsonDeserializer<HTTPstatus> createJDHS() {
		return new JsonDeserializer<HTTPstatus>() {
		       	@Override
		       	public HTTPstatus deserialize(final JsonElement                json,
		       	                              final Type                       type,
		       	                              final JsonDeserializationContext context) throws JsonParseException {
		       		return HTTPstatus.findByCode(json.getAsJsonPrimitive().getAsString());
		       	}
		       };
	}

	public WriteFreelyAPI(final String domain)   throws MalformedURLException {
		this.domain   = new URL(domain);
		this.username = "";
		this.passcode = "";

		final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
		final OkHttpClient           okHTTPclient       = new OkHttpClient.Builder()
		                                                                  .addInterceptor(this.interceptor)
		                                                                  .addInterceptor(loggingInterceptor)
		                                                                  .build();
		final Gson                   gson               = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, createJDZDT())
		                                                                   .registerTypeAdapter(Appearance.class,    createJDA())
		                                                                   .registerTypeAdapter(HTTPstatus.class,    createJDHS())
		                                                                   .registerTypeAdapter(CrosspostInfo.class, createJSCI())
		                                                                   .create();

		this.endpoints = new Retrofit.Builder()
		                             .baseUrl(this.domain.toString())
		                             .client(okHTTPclient)
		                             .addConverterFactory(GsonConverterFactory.create(gson))
		                             .build()
		                             .create(Endpoints.class);
	}

	public WriteFreelyAPI(final String domain,
	                      final String username,
	                      final String passcode) throws MalformedURLException {
		this.domain   = new URL(domain);
		this.username = username;
		this.passcode = passcode;

		final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
		final OkHttpClient           okHTTPclient       = new OkHttpClient.Builder()
		                                                                  .addInterceptor(this.interceptor)
		                                                                  .addInterceptor(loggingInterceptor)
		                                                                  .authenticator(this.authenticator)
		                                                                  .build();
		final Gson                   gson               = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, createJDZDT())
		                                                                   .registerTypeAdapter(Appearance.class,    createJDA())
		                                                                   .registerTypeAdapter(HTTPstatus.class,    createJDHS())
		                                                                   .registerTypeAdapter(CrosspostInfo.class, createJSCI())
		                                                                   .create();

		this.endpoints = new Retrofit.Builder()
		                             .baseUrl(this.domain.toString())
		                             .client(okHTTPclient)
		                             .addConverterFactory(GsonConverterFactory.create(gson))
		                             .build()
		                             .create(Endpoints.class);
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

	public boolean                       deletePost(final String postID) throws IOException {
		return this.endpoints.deletePost(postID).execute().code() == WriteFreelyAPI.SUCCESSFUL_DELETE;
	}

	public boolean                       deletePost(final String postID,
	                                                final String token) throws IOException {
		return this.endpoints.deletePost(postID,
		                                 token).execute().code() == WriteFreelyAPI.SUCCESSFUL_DELETE;
	}

	public ResponseWrapper<ResponseWrapper<PostReturned>[]> claimPosts(final String[][] postsIDsAndTokens) throws IOException {
		String result = "[";

		for(final String[] tokensAndIDs : postsIDsAndTokens) {
			result += "{ \"id\"   : \"" + tokensAndIDs[0] + "\","  +
			          "  \"token\": \"" + tokensAndIDs[1] + "\" }";
		}

		return this.endpoints
		           .claimPosts(RequestBody.create(MediaType.parse(WriteFreelyAPI.APPLICATION_JSON),
		                                          result + "]"))
		           .execute()
		           .body();
	}

	public ResponseWrapper<Collection> getCollection(final String name) throws IOException {
		return this.endpoints.getCollection(name).execute().body();
	}
}
