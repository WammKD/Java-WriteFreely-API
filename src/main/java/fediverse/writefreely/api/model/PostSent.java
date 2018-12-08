package fediverse.writefreely.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.Locale;

public class PostSent {
	private final String        body;
	private final String        title;
	private final Appearance    font;
	private final Locale        lang;
	private final Boolean       rtl;
	private final ZonedDateTime created;
	private final Object[]      crosspost;

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("title")   final String        title,
	                @JsonProperty("font")    final Appearance    font,
	                @JsonProperty("lang")    final Locale        lang,
	                @JsonProperty("rtl")     final Boolean       rtl,
	                @JsonProperty("created") final ZonedDateTime created,
	                @JsonProperty("views")   final Object[]      crosspost) {
		this.body      = body;
		this.title     = title;
		this.font      = font;
		this.lang      = lang;
		this.rtl       = rtl;
		this.created   = created;
		this.crosspost = crosspost;
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("title")   final String        title,
	                @JsonProperty("font")    final Appearance    font,
	                @JsonProperty("lang")    final Locale        lang,
	                @JsonProperty("rtl")     final Boolean       rtl,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, title, font, lang, rtl, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String     body,
	                @JsonProperty("title")     final String     title,
	                @JsonProperty("font")      final Appearance font,
	                @JsonProperty("lang")      final Locale     lang,
	                @JsonProperty("rtl")       final Boolean    rtl,
	                @JsonProperty("crosspost") final Object[]   crosspost) {
		this(body, title, font, lang, rtl, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("title")     final String        title,
	                @JsonProperty("font")      final Appearance    font,
	                @JsonProperty("lang")      final Locale        lang,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, title, font, lang, null, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("title")     final String        title,
	                @JsonProperty("font")      final Appearance    font,
	                @JsonProperty("rtl")       final Boolean       rtl,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, title, font, null, rtl, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("title")     final String        title,
	                @JsonProperty("lang")      final Locale        lang,
	                @JsonProperty("rtl")       final Boolean       rtl,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, title, null, lang, rtl, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("font")      final Appearance    font,
	                @JsonProperty("lang")      final Locale        lang,
	                @JsonProperty("rtl")       final Boolean       rtl,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, null, font, lang, rtl, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")  final String     body,
	                @JsonProperty("title") final String     title,
	                @JsonProperty("font")  final Appearance font,
	                @JsonProperty("lang")  final Locale     lang,
	                @JsonProperty("rtl")   final Boolean    rtl) {
		this(body, title, font, lang, rtl, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("title")   final String        title,
	                @JsonProperty("font")    final Appearance    font,
	                @JsonProperty("lang")    final Locale        lang,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, title, font, lang, null, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String     body,
	                @JsonProperty("title")     final String     title,
	                @JsonProperty("font")      final Appearance font,
	                @JsonProperty("lang")      final Locale     lang,
	                @JsonProperty("crosspost") final Object[]   crosspost) {
		this(body, title, font, lang, null, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("title")   final String        title,
	                @JsonProperty("font")    final Appearance    font,
	                @JsonProperty("rtl")     final Boolean       rtl,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, title, font, null, rtl, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String     body,
	                @JsonProperty("title")     final String     title,
	                @JsonProperty("font")      final Appearance font,
	                @JsonProperty("rtl")       final Boolean    rtl,
	                @JsonProperty("crosspost") final Object[]   crosspost) {
		this(body, title, font, null, rtl, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("title")     final String        title,
	                @JsonProperty("font")      final Appearance    font,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, title, font, null, null, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("title")   final String        title,
	                @JsonProperty("lang")    final Locale        lang,
	                @JsonProperty("rtl")     final Boolean       rtl,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, title, null, lang, rtl, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String   body,
	                @JsonProperty("title")     final String   title,
	                @JsonProperty("lang")      final Locale   lang,
	                @JsonProperty("rtl")       final Boolean  rtl,
	                @JsonProperty("crosspost") final Object[] crosspost) {
		this(body, title, null, lang, rtl, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("title")     final String        title,
	                @JsonProperty("lang")      final Locale        lang,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, title, null, lang, null, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("title")     final String        title,
	                @JsonProperty("rtl")       final Boolean       rtl,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, title, null, null, rtl, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("font")    final Appearance    font,
	                @JsonProperty("lang")    final Locale        lang,
	                @JsonProperty("rtl")     final Boolean       rtl,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, null, font, lang, rtl, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String     body,
	                @JsonProperty("font")      final Appearance font,
	                @JsonProperty("lang")      final Locale     lang,
	                @JsonProperty("rtl")       final Boolean    rtl,
	                @JsonProperty("crosspost") final Object[]   crosspost) {
		this(body, null, font, lang, rtl, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("font")      final Appearance    font,
	                @JsonProperty("lang")      final Locale        lang,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, null, font, lang, null, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("font")      final Appearance    font,
	                @JsonProperty("rtl")       final Boolean       rtl,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, null, font, null, rtl, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("lang")      final Locale        lang,
	                @JsonProperty("rtl")       final Boolean       rtl,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, null, null, lang, rtl, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")  final String     body,
	                @JsonProperty("title") final String     title,
	                @JsonProperty("font")  final Appearance font,
	                @JsonProperty("lang")  final Locale     lang) {
		this(body, title, font, lang, null, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")  final String     body,
	                @JsonProperty("title") final String     title,
	                @JsonProperty("font")  final Appearance font,
	                @JsonProperty("rtl")   final Boolean    rtl) {
		this(body, title, font, null, rtl, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("title")   final String        title,
	                @JsonProperty("font")    final Appearance    font,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, title, font, null, null, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String     body,
	                @JsonProperty("title")     final String     title,
	                @JsonProperty("font")      final Appearance font,
	                @JsonProperty("crosspost") final Object[]   crosspost) {
		this(body, title, font, null, null, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")  final String  body,
	                @JsonProperty("title") final String  title,
	                @JsonProperty("lang")  final Locale  lang,
	                @JsonProperty("rtl")   final Boolean rtl) {
		this(body, title, null, lang, rtl, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("title")   final String        title,
	                @JsonProperty("lang")    final Locale        lang,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, title, null, lang, null, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String body,
	                @JsonProperty("title")     final String title,
	                @JsonProperty("lang")      final Locale lang,
	                @JsonProperty("crosspost") final Object[] crosspost) {
		this(body, title, null, lang, null, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("title")   final String        title,
	                @JsonProperty("rtl")     final Boolean       rtl,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, title, null, null, rtl, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String   body,
	                @JsonProperty("title")     final String   title,
	                @JsonProperty("rtl")       final Boolean  rtl,
	                @JsonProperty("crosspost") final Object[] crosspost) {
		this(body, title, null, null, rtl, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("title")     final String        title,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, title, null, null, null, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body") final String     body,
	                @JsonProperty("font") final Appearance font,
	                @JsonProperty("lang") final Locale     lang,
	                @JsonProperty("rtl")  final Boolean    rtl) {
		this(body, null, font, lang, rtl, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("font")    final Appearance    font,
	                @JsonProperty("lang")    final Locale        lang,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, null, font, lang, null, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String     body,
	                @JsonProperty("font")      final Appearance font,
	                @JsonProperty("lang")      final Locale     lang,
	                @JsonProperty("crosspost") final Object[]   crosspost) {
		this(body, null, font, lang, null, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("font")    final Appearance    font,
	                @JsonProperty("rtl")     final Boolean       rtl,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, null, font, null, rtl, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String     body,
	                @JsonProperty("font")      final Appearance font,
	                @JsonProperty("rtl")       final Boolean    rtl,
	                @JsonProperty("crosspost") final Object[]   crosspost) {
		this(body, null, font, null, rtl, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("font")      final Appearance    font,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, null, font, null, null, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("lang")    final Locale        lang,
	                @JsonProperty("rtl")     final Boolean       rtl,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, null, null, lang, rtl, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String   body,
	                @JsonProperty("lang")      final Locale   lang,
	                @JsonProperty("rtl")       final Boolean  rtl,
	                @JsonProperty("crosspost") final Object[] crosspost) {
		this(body, null, null, lang, rtl, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("lang")      final Locale        lang,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, null, null, lang, null, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("rtl")       final Boolean       rtl,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, null, null, null, rtl, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")  final String     body,
	                @JsonProperty("title") final String     title,
	                @JsonProperty("font")  final Appearance font) {
		this(body, title, font, null, null, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")  final String body,
	                @JsonProperty("title") final String title,
	                @JsonProperty("lang")  final Locale lang) {
		this(body, title, null, lang, null, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")  final String  body,
	                @JsonProperty("title") final String  title,
	                @JsonProperty("rtl")   final Boolean rtl) {
		this(body, title, null, null, rtl, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("title")   final String        title,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, title, null, null, null, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String   body,
	                @JsonProperty("title")     final String   title,
	                @JsonProperty("crosspost") final Object[] crosspost) {
		this(body, title, null, null, null, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body") final String     body,
	                @JsonProperty("font") final Appearance font,
	                @JsonProperty("lang") final Locale     lang) {
		this(body, null, font, lang, null, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body") final String     body,
	                @JsonProperty("font") final Appearance font,
	                @JsonProperty("rtl")  final Boolean    rtl) {
		this(body, null, font, null, rtl, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("font")    final Appearance    font,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, null, font, null, null, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String     body,
	                @JsonProperty("font")      final Appearance font,
	                @JsonProperty("crosspost") final Object[]   crosspost) {
		this(body, null, font, null, null, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body") final String  body,
	                @JsonProperty("lang") final Locale  lang,
	                @JsonProperty("rtl")  final Boolean rtl) {
		this(body, null, null, lang, rtl, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("lang")    final Locale        lang,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, null, null, lang, null, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String   body,
	                @JsonProperty("lang")      final Locale   lang,
	                @JsonProperty("crosspost") final Object[] crosspost) {
		this(body, null, null, lang, null, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("rtl")     final Boolean       rtl,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, null, null, null, rtl, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String   body,
	                @JsonProperty("rtl")       final Boolean  rtl,
	                @JsonProperty("crosspost") final Object[] crosspost) {
		this(body, null, null, null, rtl, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String        body,
	                @JsonProperty("created")   final ZonedDateTime created,
	                @JsonProperty("crosspost") final Object[]      crosspost) {
		this(body, null, null, null, null, created, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")  final String body,
	                @JsonProperty("title") final String title) {
		this(body, title, null, null, null, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body") final String     body,
	                @JsonProperty("font") final Appearance font) {
		this(body, null, font, null, null, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body") final String body,
	                @JsonProperty("lang") final Locale lang) {
		this(body, null, null, lang, null, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body") final String  body,
	                @JsonProperty("rtl")  final Boolean rtl) {
		this(body, null, null, null, rtl, null, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")    final String        body,
	                @JsonProperty("created") final ZonedDateTime created) {
		this(body, null, null, null, null, created, null);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body")      final String   body,
	                @JsonProperty("crosspost") final Object[] crosspost) {
		this(body, null, null, null, null, null, crosspost);
	}

	@JsonCreator
	public PostSent(@JsonProperty("body") final String body) {
		this(body, null, null, null, null, null, null);
	}

	public final String        getBody() {
		return this.body;
	}

	public final String        getTitle() {
		return this.title;
	}

	public final Appearance    getFont() {
		return this.font;
	}

	public final Locale        getLang() {
		return this.lang;
	}

	public final Boolean       getRtl() {
		return this.rtl;
	}

	public final ZonedDateTime getCreated() {
		return this.created;
	}

	public final Object[]      getCrosspostInfo() {
		return this.crosspost;
	}
}
