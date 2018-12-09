package fediverse.writefreely.api;

import fediverse.writefreely.api.model.Auth;
import fediverse.writefreely.api.model.Collection;
import fediverse.writefreely.api.model.PostReturned;
import fediverse.writefreely.api.model.PostCreated;
import fediverse.writefreely.api.model.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface Endpoints {
	static final String AUTH          = "api/auth/login";
	static final String POST_PUBLISH  = "api/posts";
	static final String POST_RETRIEVE = "api/posts/{postID}";
	static final String COLLECTION    = "api/collections/{collectionAlias}";

	@POST(Endpoints.AUTH)
	Call<ResponseWrapper<Auth>>         getToken();

	@POST(Endpoints.POST_PUBLISH)
	Call<ResponseWrapper<PostReturned>> publishPost(@Body PostCreated body);

	@GET(Endpoints.POST_RETRIEVE)
	Call<ResponseWrapper<PostReturned>> retrievePost(@Path("postID") String pID);

	@GET(Endpoints.COLLECTION)
	Call<ResponseWrapper<Collection>>   getCollection(@Path("collectionAlias") String ca);
}
