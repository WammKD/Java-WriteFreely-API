package fediverse.writefreely.api.model;

import java.net.URL;

public class Collection {
	private final String         alias;
	private final String         title;
	private final String         description;
	private final String         styleSheet;
	private final String         eMail;
	private final boolean        pub;
	private final URL            url;
	private final int            views;
	private final int            totalPosts;
	private final PostReturned[] posts;

	public Collection(final String         alias,
	                  final String         title,
	                  final String         description,
	                  final String         styleSheet,
	                  final String         eMail,
	                  final boolean        pub,
	                  final URL            url,
	                  final int            views,
	                  final int            totalPosts,
	                  final PostReturned[] posts) {
		this.alias       = alias;
		this.title       = title;
		this.description = description;
		this.styleSheet  = styleSheet;
		this.eMail       = eMail;
		this.pub         = pub;
		this.url         = url;
		this.views       = views;
		this.totalPosts  = totalPosts;
		this.posts       = posts;
	}

	public final String         getAlias() {
		return this.alias;
	}

	public final String         getTitle() {
		return this.title;
	}

	public final String         getDescription() {
		return this.description;
	}

	public final String         getStyleSheet() {
		return this.styleSheet;
	}

	public final String         getEMail() {
		return this.eMail;
	}

	public final boolean        isPublic() {
		return this.pub;
	}

	public final URL            getURL() {
		return this.url;
	}

	public final int            getViews() {
		return this.views;
	}

	public final int            getTotalPosts() {
		return this.totalPosts;
	}

	public final PostReturned[] getPosts() {
		return this.posts;
	}
}
