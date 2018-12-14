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
		if(post.getCrosspostInfo() != null) {
			throw new IllegalArgumentException("Posts cannot be crossposted unless authenticated!");
		} else {
			return super.parseResponse(this.endpoints.publishPost(post).execute());
		}
	}

	public ResponseWrapper<PostReturned> retrievePost(final String postID) throws IOException {
		return super.parseResponse(this.endpoints.retrievePost(postID).execute());
	}

	public ResponseWrapper<PostReturned> updatePost(final String     postID,
	                                                final PostUpdate post) throws IOException {
		if(post.getToken() == null) {
			throw new IllegalArgumentException("Updating a post, while "    +
			                                   "not having authenticated "  +
			                                   "a user, requires the "      +
			                                   "sent post to have a token.");
		} else {
			return super.parseResponse(this.endpoints.updatePost(postID,
			                                                     post).execute());
		}
	}

	public boolean deletePost(final String postID,
	                          final String token) throws IOException {
		return this.endpoints.deletePost(postID,
		                                 token).execute().code() == WriteFreelyAPI.SUCCESSFUL_DELETE;
	}

	public ResponseWrapper<Collection> retrieveCollection(final String alias) throws IOException {
		return super.parseResponse(this.endpoints.retrieveCollection(alias).execute());
	}

	public ResponseWrapper<PostReturned> retrievePostByCollection(final String cAlias,
	                                                              final String pSlug) throws IOException {
		return super.parseResponse(this.endpoints.retrievePostByCollection(cAlias,
		                                                                   pSlug).execute());
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
}
