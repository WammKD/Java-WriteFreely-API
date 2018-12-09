package fediverse.writefreely.api.model;

import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostReturned {
	private final String        id;
	private final String        slug;
	private final String        token;
	private final Appearance    appearance;
	private final String        language;
	private final Boolean       rtl;
	private final ZonedDateTime created;
	private final ZonedDateTime updated;
	private final String        title;
	private final String        body;
	private final String[]      tags;
	private final long          views;

	@JsonCreator
	public PostReturned(@JsonProperty("id")         final String        id,
	                    @JsonProperty("slug")       final String        slug,
	                    @JsonProperty("token")      final String        token,
	                    @JsonProperty("appearance") final Appearance    appearance,
	                    @JsonProperty("language")   final String        language,
	                    @JsonProperty("rtl")        final boolean       rtl,
	                    @JsonProperty("created")    final ZonedDateTime created,
	                    @JsonProperty("created")    final ZonedDateTime updated,
	                    @JsonProperty("title")      final String        title,
	                    @JsonProperty("body")       final String        body,
	                    @JsonProperty("tags")       final String[]      tags,
	                    @JsonProperty("views")      final long          views) {
		this.id         = id;
		this.slug       = slug;
		this.token      = token;
		this.appearance = appearance;
		this.language   = language;
		this.rtl        = rtl;
		this.created    = created;
		this.updated    = updated;
		this.title      = title;
		this.body       = body;
		this.tags       = tags;
		this.views      = views;
	}

	public final String        getID() {
		return this.id;
	}

	public final String        getSlug() {
		return this.slug;
	}

	public final String        getToken() {
		return this.token;
	}

	public final Appearance    getAppearance() {
		return this.appearance;
	}

	public final String        getLanguage() {
		return this.language;
	}

	public final boolean       isRtL() {
		return this.rtl;
	}

	public final ZonedDateTime getCreated() {
		return this.created;
	}

	public final ZonedDateTime getUpdated() {
		return this.updated;
	}

	public final String        getTitle() {
		return this.title;
	}

	public final String        getBody() {
		return this.body;
	}

	public final String[]      getTags() {
		return this.tags;
	}

	public final long          getViews() {
		return this.views;
	}
}
