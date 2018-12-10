package fediverse.writefreely.api;

import fediverse.writefreely.api.model.Auth;
import fediverse.writefreely.api.model.Collection;
import fediverse.writefreely.api.model.PostCreated;
import fediverse.writefreely.api.model.PostReturned;
import fediverse.writefreely.api.model.PostUpdate;
import fediverse.writefreely.api.model.ResponseWrapper;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface Endpoints {
	static final String POST_PUBLISH            = "api/posts";
	static final String POST_RETRIEVE           = "api/posts/{postID}";
	static final String POST_CLAIM              = "api/posts/claim";
	static final String COLLECTIONS             = "api/collections";
	static final String COLLECTION              = "api/collections/{collectionAlias}";
	static final String POST_PUBLISH_BY_SLUG    = "api/collections/{collectionAlias}/posts";
	static final String POST_RETRIEVE_BY_SLUG   = "api/collections/{collectionAlias}/posts/{slug}";
	static final String POST_MOVE_TO_COLLECTION = "api/collections/{collectionAlias}/collect";

	static final String LOGIN                    = "api/auth/login";
	@POST(Endpoints.LOGIN)
	Call<ResponseWrapper<Auth>>                            getToken();

	@POST(Endpoints.POST_PUBLISH)
	Call<ResponseWrapper<PostReturned>>                    publishPost(@Body PostCreated body);

	@GET(Endpoints.POST_RETRIEVE)
	Call<ResponseWrapper<PostReturned>>                    retrievePost(@Path("postID") String pID);

	@POST(Endpoints.POST_RETRIEVE)
	Call<ResponseWrapper<PostReturned>>                    updatePost(@Path("postID") String     pID,
	                                                                  @Body           PostUpdate body);

	@DELETE(Endpoints.POST_RETRIEVE)
	Call<Void>                                             deletePost(@Path("postID") String pID);

	@DELETE(Endpoints.POST_RETRIEVE)
	Call<Void>                                             deletePost(@Path("postID") String pID,
	                                                                  @Query("token") String token);

	@POST(Endpoints.POST_CLAIM)
	Call<ResponseWrapper<ResponseWrapper<PostReturned>[]>> claimPosts(@Body RequestBody posts);

	@POST(Endpoints.COLLECTIONS)
	Call<ResponseWrapper<Collection>>                      createCollection(@Body RequestBody coll);

	@GET(Endpoints.COLLECTION)
	Call<ResponseWrapper<Collection>>                      retrieveCollection(@Path("collectionAlias") String ca);

	@DELETE(Endpoints.COLLECTION)
	Call<Void>                                             deleteCollection(@Path("collectionAlias") String ca);

	@GET(Endpoints.POST_RETRIEVE_BY_SLUG)
	Call<ResponseWrapper<PostReturned>>                    retrievePostByCollection(@Path("collectionAlias") String ca,
	                                                                                @Path("slug")            String s);

	@POST(Endpoints.POST_PUBLISH_BY_SLUG)
	Call<ResponseWrapper<PostReturned>>                    publishPostByCollection(@Path("collectionAlias") String      ca,
	                                                                               @Body                    PostCreated body);

	@GET(Endpoints.POST_PUBLISH_BY_SLUG)
	Call<ResponseWrapper<Collection>>                      retrievePostsByCollection(@Path("collectionAlias") String ca);

	@GET(Endpoints.POST_PUBLISH_BY_SLUG)
	Call<ResponseWrapper<Collection>>                      retrievePostsByCollection(@Path("collectionAlias") String ca,
	                                                                                 @Query("body")           String format);

	@POST(Endpoints.POST_MOVE_TO_COLLECTION)
	Call<ResponseWrapper<ResponseWrapper<PostReturned>[]>> movePostToCollection(@Path("collectionAlias") String      ca,
	                                                                            @Body                    RequestBody posts);
}
