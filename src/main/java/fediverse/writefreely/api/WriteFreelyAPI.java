package fediverse.writefreely.api;

import fediverse.writefreely.api.model.Collection;
import fediverse.writefreely.api.model.PostCreated;
import fediverse.writefreely.api.model.PostReturned;
import fediverse.writefreely.api.model.PostUpdate;
import fediverse.writefreely.api.model.ResponseWrapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class WriteFreelyAPI extends WriteFreelyAPIabstract {
	private static final Logger          LOG = Logger.getLogger(WriteFreelyAPI.class.getName());
	private        final Endpoints endpoints;
	private        final URL          domain;

	public WriteFreelyAPI(final String domain) throws MalformedURLException {
		this.domain    = new URL(domain);
		this.endpoints = super.generateEndpoints(this.domain.toString(),
		                                         WriteFreelyAPI.LOG,
		                                         null);
	}

	public WriteFreelyAPI(final URL domain) {
		this.domain    = domain;
		this.endpoints = super.generateEndpoints(this.domain.toString(),
		                                         WriteFreelyAPI.LOG,
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
