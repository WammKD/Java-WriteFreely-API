package fediverse.writefreely.api;

import com.google.gson.JsonParser;
import fediverse.writefreely.api.model.Auth;
import fediverse.writefreely.api.model.Channel;
import fediverse.writefreely.api.model.Collection;
import fediverse.writefreely.api.model.PostCreated;
import fediverse.writefreely.api.model.PostReturned;
import fediverse.writefreely.api.model.PostUpdate;
import fediverse.writefreely.api.model.ResponseWrapper;
import fediverse.writefreely.api.model.User;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Logger;
import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

public class WriteFreelyAPIwithUser extends WriteFreelyAPIabstract {
	private static final Logger              LOG = Logger.getLogger(WriteFreelyAPIwithUser.class.getName());
	private        final Endpoints     endpoints;
	private        final URL              domain;
	private        final String         username;
	private        final String         passcode;

	private        final Authenticator generateAuthenticator() {
		return new Authenticator() {
			@Override
			public Request authenticate(final Route    route,
			                            final Response response) throws IOException {
				// If we've failed 3 times, give up.
				if(responseCount(response) >= 3) {
					WriteFreelyAPIwithUser.LOG.warning("Unable to authenticate to API " +
					                                   "after 3 attempts; giving up!");

					return null;
				}

				WriteFreelyAPIwithUser.LOG.fine("Intercepted 401 UnAuthorized for URL: " +
				                                response.request().url().toString());

				final Request req = new Request.Builder()
				                               .url(WriteFreelyAPIwithUser.this.domain + Endpoints.LOGIN)
				                               .addHeader("Content-Type",
				                                          WriteFreelyAPIabstract.APPLICATION_JSON)
				                               .post(RequestBody.create(WriteFreelyAPIabstract.APP_JSON_MEDIA,
				                                                        "{ \"alias\": \"" + WriteFreelyAPIwithUser.this.username + "\", " +
				                                                        "   \"pass\": \"" + WriteFreelyAPIwithUser.this.passcode + "\" }"))
				                               .build();
				WriteFreelyAPIwithUser.super.authToken = new JsonParser().parse(new OkHttpClient().newCall(req)
				                                                                                  .execute()
				                                                                                  .body()
				                                                                                  .string())
				                                                         .getAsJsonObject()
				                                                         .get("data")
				                                                         .getAsJsonObject()
				                                                         .get("access_token")
				                                                         .getAsString();

				WriteFreelyAPIwithUser.LOG.finest("Acquired Mall API authorization token.");

				return response.request()
				               .newBuilder()
				               .header("Content-Type",            WriteFreelyAPIabstract.APPLICATION_JSON)
				               .header("Authorization", "Token " + WriteFreelyAPIwithUser.super.authToken)
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
	}

	public WriteFreelyAPIwithUser(final String domain,
	                              final String username,
	                              final String passcode) throws IOException,
	                                                            MalformedURLException {
		this.domain    = new URL(domain);
		this.username  = username;
		this.passcode  = passcode;
		this.endpoints = super.generateEndpoints(this.domain.toString(),
		                                         WriteFreelyAPIwithUser.LOG,
		                                         this.generateAuthenticator());
		this.login();
	}

	public WriteFreelyAPIwithUser(final URL    domain,
	                              final String username,
	                              final String passcode) throws IOException {
		this.domain    = domain;
		this.username  = username;
		this.passcode  = passcode;
		this.endpoints = super.generateEndpoints(this.domain.toString(),
		                                         WriteFreelyAPIwithUser.LOG,
		                                         this.generateAuthenticator());
		this.login();
	}

	public ResponseWrapper<Auth> login() throws IOException {
		final ResponseWrapper<Auth> result = super.parseResponse(this.endpoints
		                                                             .getToken(RequestBody.create(WriteFreelyAPIabstract.APP_JSON_MEDIA,
		                                                                                          "{ \"alias\": \"" + this.username + "\", " +
		                                                                                          "   \"pass\": \"" + this.passcode + "\" }"))
		                                                             .execute());

		WriteFreelyAPIwithUser.super.authToken = result.getData().getAccessToken();

		return result;
	}

	public ResponseWrapper<PostReturned> publishPost(final PostCreated post) throws IOException {
		return super.parseResponse(this.endpoints.publishPost(post).execute());
	}

	public ResponseWrapper<PostReturned> retrievePost(final String postID) throws IOException {
		return super.parseResponse(this.endpoints.retrievePost(postID).execute());
	}

	public ResponseWrapper<PostReturned> updatePost(final String     postID,
	                                                final PostUpdate post) throws IOException {
		return super.parseResponse(this.endpoints.updatePost(postID,
		                                                     post).execute());
	}

	public boolean deletePost(final String postID) throws IOException {
		return this.endpoints.deletePost(postID).execute().code() == WriteFreelyAPIabstract.SUCCESSFUL_DELETE;
	}

	public boolean deletePost(final String postID,
	                          final String token) throws IOException {
		return this.endpoints.deletePost(postID,
		                                 token).execute().code() == WriteFreelyAPIabstract.SUCCESSFUL_DELETE;
	}

	public ResponseWrapper<ResponseWrapper<PostReturned>[]> claimPosts(final String[][] postsIDsAndTokens) throws IOException {
		return super.parseResponse(this.endpoints
		                               .claimPosts(RequestBody.create(WriteFreelyAPIabstract.APP_JSON_MEDIA,
		                                                              WriteFreelyAPIabstract.convertArrayToJSON("id",
		                                                                                                        "token",
		                                                                                                        postsIDsAndTokens)))
		                               .execute());
	}

	public ResponseWrapper<Collection> createCollection(final String title) throws IOException {
		return super.parseResponse(this.endpoints
		                               .createCollection(RequestBody.create(APP_JSON_MEDIA,
		                                                                    "{ \"title\": \"" + title + "\" }"))
		                               .execute());
	}

	public ResponseWrapper<Collection> createCollection(final String title,
	                                                    final String alias) throws IOException {
		return super.parseResponse(this.endpoints
		                               .createCollection(RequestBody.create(APP_JSON_MEDIA,
		                                                                    "{ \"title\": \"" + title + "\"," +
		                                                                    "  \"alias\": \"" + alias + "\" }"))
		                               .execute());
	}

	public ResponseWrapper<Collection> retrieveCollection(final String alias) throws IOException {
		return super.parseResponse(this.endpoints.retrieveCollection(alias).execute());
	}

	public boolean deleteCollection(final String alias) throws IOException {
		return this.endpoints.deleteCollection(alias).execute().code() == WriteFreelyAPIabstract.SUCCESSFUL_DELETE;
	}

	public ResponseWrapper<PostReturned> retrievePostByCollection(final String cAlias,
	                                                              final String pSlug) throws IOException {
		return super.parseResponse(this.endpoints.retrievePostByCollection(cAlias,
		                                                                   pSlug).execute());
	}

	public ResponseWrapper<PostReturned> publishPostByCollection(final String      cAlias,
	                                                             final PostCreated post) throws IOException {
		return super.parseResponse(this.endpoints.publishPostByCollection(cAlias,
		                                                                  post).execute());
	}

	public ResponseWrapper<Collection> retrievePostsByCollection(final String cAlias) throws IOException {
		return super.parseResponse(this.endpoints.retrievePostsByCollection(cAlias).execute());
	}

	public ResponseWrapper<Collection> retrievePostsByCollection(final String  cAlias,
	                                                             final boolean isFormatted) throws IOException {
		if(isFormatted) {
			return super.parseResponse(this.endpoints.retrievePostsByCollection(cAlias,
			                                                                    "html").execute());
		} else {
			return super.parseResponse(this.endpoints.retrievePostsByCollection(cAlias).execute());
		}
	}

	public ResponseWrapper<ResponseWrapper<PostReturned>[]> movePostToCollection(final String     cAlias,
	                                                                             final String[][] postsIDsAndTokens) throws IOException {
		return super.parseResponse(this.endpoints
		                               .movePostToCollection(cAlias,
		                                                     RequestBody.create(WriteFreelyAPIabstract.APP_JSON_MEDIA,
		                                                                        WriteFreelyAPIabstract.convertArrayToJSON("id",
		                                                                                                                  "token",
		                                                                                                                  postsIDsAndTokens)))
		                           	   .execute());
	}

	public ResponseWrapper<ResponseWrapper<String>[]> pinPostToCollection(final String     cAlias,
	                                                                      final String[][] postsIDsAndPositions) throws IOException {
		return super.parseResponse(this.endpoints
		                               .pinPostToCollection(cAlias,
		                                                    RequestBody.create(WriteFreelyAPIabstract.APP_JSON_MEDIA,
		                                                                       WriteFreelyAPIabstract.convertArrayToJSON("id",
		                                                                                                                 "position",
		                                                                                                                 postsIDsAndPositions)))
		                               .execute());
	}

	public ResponseWrapper<ResponseWrapper<String>[]> unpinPostToCollection(final String     cAlias,
	                                                                        final String[] postsIDs) throws IOException {
		final String result = Arrays.stream(postsIDs)
		                            .reduce("[",
		                                    (String r,
		                                     String pID) -> r + "{ \"id\": \"" + pID + "\" }, ");

		return super.parseResponse(this.endpoints
		                               .pinPostToCollection(cAlias,
		                                                    RequestBody.create(APP_JSON_MEDIA,
		                                                                       result.substring(0,
		                                                                                        result.length() - 2) + "]"))
		                               .execute());
	}

	public boolean logout() throws IOException {
		return this.endpoints.logout().execute().code() == WriteFreelyAPIabstract.SUCCESSFUL_DELETE;
	}

	public ResponseWrapper<User> retrieveUser() throws IOException {
		return super.parseResponse(this.endpoints.retrieveUser().execute());
	}

	public ResponseWrapper<PostReturned[]> retrieveUserPosts() throws IOException {
		return super.parseResponse(this.endpoints.retrieveUserPosts().execute());
	}

	public ResponseWrapper<Collection[]> retrieveUserCollections() throws IOException {
		return super.parseResponse(this.endpoints.retrieveUserCollections().execute());
	}

	public ResponseWrapper<Channel[]> retrieveUserChannels() throws IOException {
		return super.parseResponse(this.endpoints.retrieveUserChannels().execute());
	}
}
