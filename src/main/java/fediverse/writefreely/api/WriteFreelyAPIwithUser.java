package fediverse.writefreely.api;

import com.google.gson.JsonParser;
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
				               .header("Content-Type",  WriteFreelyAPIabstract.APPLICATION_JSON)
				               .header("Authorization",  WriteFreelyAPIwithUser.super.authToken)
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
	                              final String passcode) throws MalformedURLException {
		this.domain    = new URL(domain);
		this.username  = username;
		this.passcode  = passcode;
		this.endpoints = WriteFreelyAPIabstract.generateEndpoints(this.domain.toString(),
		                                                          WriteFreelyAPIwithUser.LOG,
		                                                          super.authToken,
		                                                          this.generateAuthenticator());
	}

	public WriteFreelyAPIwithUser(final URL    domain,
	                              final String username,
	                              final String passcode) throws MalformedURLException {
		this.domain    = domain;
		this.username  = username;
		this.passcode  = passcode;
		this.endpoints = WriteFreelyAPIabstract.generateEndpoints(this.domain.toString(),
		                                                          WriteFreelyAPIwithUser.LOG,
		                                                          super.authToken,
		                                                          this.generateAuthenticator());
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
		return this.endpoints.deletePost(postID).execute().code() == WriteFreelyAPIabstract.SUCCESSFUL_DELETE;
	}

	public boolean deletePost(final String postID,
	                          final String token) throws IOException {
		return this.endpoints.deletePost(postID,
		                                 token).execute().code() == WriteFreelyAPIabstract.SUCCESSFUL_DELETE;
	}

	public ResponseWrapper<ResponseWrapper<PostReturned>[]> claimPosts(final String[][] postsIDsAndTokens) throws IOException {
		return this.endpoints
		           .claimPosts(RequestBody.create(WriteFreelyAPIabstract.APP_JSON_MEDIA,
		                                          WriteFreelyAPIabstract.convertArrayToJSON("id",
		                                                                                    "token",
		                                                                                    postsIDsAndTokens)))
		           .execute()
		           .body();
	}

	public ResponseWrapper<Collection> createCollection(final String title) throws IOException {
		return this.endpoints
		           .createCollection(RequestBody.create(APP_JSON_MEDIA,
		                                                "{ \"title\": \"" + title + "\" }"))
		           .execute()
		           .body();
	}

	public ResponseWrapper<Collection> createCollection(final String title,
	                                                    final String alias) throws IOException {
		return this.endpoints
		           .createCollection(RequestBody.create(APP_JSON_MEDIA,
		                                                "{ \"title\": \"" + title + "\"," +
		                                                "  \"alias\": \"" + alias + "\" }"))
		           .execute()
		           .body();
	}

	public ResponseWrapper<Collection> retrieveCollection(final String alias) throws IOException {
		return this.endpoints.retrieveCollection(alias).execute().body();
	}

	public boolean deleteCollection(final String alias) throws IOException {
		return this.endpoints.deleteCollection(alias).execute().code() == WriteFreelyAPIabstract.SUCCESSFUL_DELETE;
	}

	public ResponseWrapper<PostReturned> retrievePostByCollection(final String cAlias,
	                                                              final String pSlug) throws IOException {
		return this.endpoints.retrievePostByCollection(cAlias,
		                                               pSlug).execute().body();
	}

	public ResponseWrapper<PostReturned> publishPostByCollection(final String      cAlias,
	                                                             final PostCreated post) throws IOException {
		return this.endpoints.publishPostByCollection(cAlias,
		                                              post).execute().body();
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

	public ResponseWrapper<ResponseWrapper<PostReturned>[]> movePostToCollection(final String     cAlias,
	                                                                             final String[][] postsIDsAndTokens) throws IOException {
		return this.endpoints
		           .movePostToCollection(cAlias,
		                                 RequestBody.create(WriteFreelyAPIabstract.APP_JSON_MEDIA,
		                                                    WriteFreelyAPIabstract.convertArrayToJSON("id",
		                                                                                              "token",
		                                                                                              postsIDsAndTokens)))
		       	   .execute()
			       .body();
	}

	public ResponseWrapper<ResponseWrapper<String>[]> pinPostToCollection(final String     cAlias,
	                                                                      final String[][] postsIDsAndPositions) throws IOException {
		return this.endpoints
		           .pinPostToCollection(cAlias,
		                                RequestBody.create(WriteFreelyAPIabstract.APP_JSON_MEDIA,
		                                                   WriteFreelyAPIabstract.convertArrayToJSON("id",
		                                                                                             "position",
		                                                                                             postsIDsAndPositions)))
		           .execute()
		           .body();
	}

	public ResponseWrapper<ResponseWrapper<String>[]> unpinPostToCollection(final String     cAlias,
	                                                                        final String[] postsIDs) throws IOException {
		final String result = Arrays.stream(postsIDs)
		                            .reduce("[",
		                                    (String r,
		                                     String pID) -> r + "{ \"id\": \"" + pID + "\" }, ");

		return this.endpoints
		           .pinPostToCollection(cAlias,
		                                RequestBody.create(APP_JSON_MEDIA,
		                                                   result.substring(0,
		                                                                    result.length() - 2) + "]"))
		           .execute()
		           .body();
	}

	public boolean logout() throws IOException {
		return this.endpoints.logout().execute().code() == WriteFreelyAPIabstract.SUCCESSFUL_DELETE;
	}

	public ResponseWrapper<User> retrieveUser() throws IOException {
		return this.endpoints.retrieveUser().execute().body();
	}

	public ResponseWrapper<PostReturned[]> retrieveUserPosts() throws IOException {
		return this.endpoints.retrieveUserPosts().execute().body();
	}

	public ResponseWrapper<Collection[]> retrieveUserCollections() throws IOException {
		return this.endpoints.retrieveUserCollections().execute().body();
	}

	public ResponseWrapper<Channel[]> retrieveUserChannels() throws IOException {
		return this.endpoints.retrieveUserChannels().execute().body();
	}
}
