package fediverse.writefreely.api.model;

public class Collection {
	private final String  alias;
	private final String  title;
	private final String  description;
	private final String  styleSheet;
	private final boolean pub;
	private final int     views;
	private final int     totalPosts;

	public Collection(final String  alias,
	                  final String  title,
	                  final String  description,
	                  final String  styleSheet,
	                  final boolean pub,
	                  final int     views,
	                  final int     totalPosts) {
		this.alias       = alias;
		this.title       = title;
		this.description = description;
		this.styleSheet  = styleSheet;
		this.pub         = pub;
		this.views       = views;
		this.totalPosts  = totalPosts;
	}

	public final String  getAlias() {
		return this.alias;
	}

	public final String  getTitle() {
		return this.title;
	}

	public final String  getDescription() {
		return this.description;
	}

	public final String  getStyleSheet() {
		return this.styleSheet;
	}

	public final boolean isPublic() {
		return this.pub;
	}

	public final int     getViews() {
		return this.views;
	}

	public final int     getTotalPosts() {
		return this.totalPosts;
	}
}
