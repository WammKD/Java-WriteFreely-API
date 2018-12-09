package fediverse.writefreely.api.model;

import java.time.ZonedDateTime;
import java.util.Locale;

public class PostSent {
	private final String          body;
	private final String          title;
	private final Appearance      font;
	private final Locale          lang;
	private final Boolean         rtl;
	private final ZonedDateTime   created;
	private final CrosspostInfo[] crosspost;

	public PostSent(final String          body,
	                final String          title,
	                final Appearance      font,
	                final Locale          lang,
	                final Boolean         rtl,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this.body      = body;
		this.title     = title;
		this.font      = font;
		this.lang      = lang;
		this.rtl       = rtl;
		this.created   = created;
		this.crosspost = crosspost;
	}

	public PostSent(final String        body,
	                final String        title,
	                final Appearance    font,
	                final Locale        lang,
	                final Boolean       rtl,
	                final ZonedDateTime created) {
		this(body, title, font, lang, rtl, created, null);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Appearance      font,
	                final Locale          lang,
	                final Boolean         rtl,
	                final CrosspostInfo[] crosspost) {
		this(body, title, font, lang, rtl, null, crosspost);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Appearance      font,
	                final Locale          lang,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, title, font, lang, null, created, crosspost);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Appearance      font,
	                final Boolean         rtl,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, title, font, null, rtl, created, crosspost);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Locale          lang,
	                final Boolean         rtl,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, title, null, lang, rtl, created, crosspost);
	}

	public PostSent(final String          body,
	                final Appearance      font,
	                final Locale          lang,
	                final Boolean         rtl,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, null, font, lang, rtl, created, crosspost);
	}

	public PostSent(final String     body,
	                final String     title,
	                final Appearance font,
	                final Locale     lang,
	                final Boolean    rtl) {
		this(body, title, font, lang, rtl, null, null);
	}

	public PostSent(final String        body,
	                final String        title,
	                final Appearance    font,
	                final Locale        lang,
	                final ZonedDateTime created) {
		this(body, title, font, lang, null, created, null);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Appearance      font,
	                final Locale          lang,
	                final CrosspostInfo[] crosspost) {
		this(body, title, font, lang, null, null, crosspost);
	}

	public PostSent(final String        body,
	                final String        title,
	                final Appearance    font,
	                final Boolean       rtl,
	                final ZonedDateTime created) {
		this(body, title, font, null, rtl, created, null);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Appearance      font,
	                final Boolean         rtl,
	                final CrosspostInfo[] crosspost) {
		this(body, title, font, null, rtl, null, crosspost);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Appearance      font,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, title, font, null, null, created, crosspost);
	}

	public PostSent(final String        body,
	                final String        title,
	                final Locale        lang,
	                final Boolean       rtl,
	                final ZonedDateTime created) {
		this(body, title, null, lang, rtl, created, null);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Locale          lang,
	                final Boolean         rtl,
	                final CrosspostInfo[] crosspost) {
		this(body, title, null, lang, rtl, null, crosspost);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Locale          lang,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, title, null, lang, null, created, crosspost);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Boolean         rtl,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, title, null, null, rtl, created, crosspost);
	}

	public PostSent(final String        body,
	                final Appearance    font,
	                final Locale        lang,
	                final Boolean       rtl,
	                final ZonedDateTime created) {
		this(body, null, font, lang, rtl, created, null);
	}

	public PostSent(final String          body,
	                final Appearance      font,
	                final Locale          lang,
	                final Boolean         rtl,
	                final CrosspostInfo[] crosspost) {
		this(body, null, font, lang, rtl, null, crosspost);
	}

	public PostSent(final String          body,
	                final Appearance      font,
	                final Locale          lang,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, null, font, lang, null, created, crosspost);
	}

	public PostSent(final String          body,
	                final Appearance      font,
	                final Boolean         rtl,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, null, font, null, rtl, created, crosspost);
	}

	public PostSent(final String          body,
	                final Locale          lang,
	                final Boolean         rtl,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, null, null, lang, rtl, created, crosspost);
	}

	public PostSent(final String     body,
	                final String     title,
	                final Appearance font,
	                final Locale     lang) {
		this(body, title, font, lang, null, null, null);
	}

	public PostSent(final String     body,
	                final String     title,
	                final Appearance font,
	                final Boolean    rtl) {
		this(body, title, font, null, rtl, null, null);
	}

	public PostSent(final String        body,
	                final String        title,
	                final Appearance    font,
	                final ZonedDateTime created) {
		this(body, title, font, null, null, created, null);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Appearance      font,
	                final CrosspostInfo[] crosspost) {
		this(body, title, font, null, null, null, crosspost);
	}

	public PostSent(final String  body,
	                final String  title,
	                final Locale  lang,
	                final Boolean rtl) {
		this(body, title, null, lang, rtl, null, null);
	}

	public PostSent(final String        body,
	                final String        title,
	                final Locale        lang,
	                final ZonedDateTime created) {
		this(body, title, null, lang, null, created, null);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Locale          lang,
	                final CrosspostInfo[] crosspost) {
		this(body, title, null, lang, null, null, crosspost);
	}

	public PostSent(final String        body,
	                final String        title,
	                final Boolean       rtl,
	                final ZonedDateTime created) {
		this(body, title, null, null, rtl, created, null);
	}

	public PostSent(final String          body,
	                final String          title,
	                final Boolean         rtl,
	                final CrosspostInfo[] crosspost) {
		this(body, title, null, null, rtl, null, crosspost);
	}

	public PostSent(final String          body,
	                final String          title,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, title, null, null, null, created, crosspost);
	}

	public PostSent(final String     body,
	                final Appearance font,
	                final Locale     lang,
	                final Boolean    rtl) {
		this(body, null, font, lang, rtl, null, null);
	}

	public PostSent(final String        body,
	                final Appearance    font,
	                final Locale        lang,
	                final ZonedDateTime created) {
		this(body, null, font, lang, null, created, null);
	}

	public PostSent(final String          body,
	                final Appearance      font,
	                final Locale          lang,
	                final CrosspostInfo[] crosspost) {
		this(body, null, font, lang, null, null, crosspost);
	}

	public PostSent(final String        body,
	                final Appearance    font,
	                final Boolean       rtl,
	                final ZonedDateTime created) {
		this(body, null, font, null, rtl, created, null);
	}

	public PostSent(final String          body,
	                final Appearance      font,
	                final Boolean         rtl,
	                final CrosspostInfo[] crosspost) {
		this(body, null, font, null, rtl, null, crosspost);
	}

	public PostSent(final String          body,
	                final Appearance      font,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, null, font, null, null, created, crosspost);
	}

	public PostSent(final String        body,
	                final Locale        lang,
	                final Boolean       rtl,
	                final ZonedDateTime created) {
		this(body, null, null, lang, rtl, created, null);
	}

	public PostSent(final String          body,
	                final Locale          lang,
	                final Boolean         rtl,
	                final CrosspostInfo[] crosspost) {
		this(body, null, null, lang, rtl, null, crosspost);
	}

	public PostSent(final String          body,
	                final Locale          lang,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, null, null, lang, null, created, crosspost);
	}

	public PostSent(final String          body,
	                final Boolean         rtl,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, null, null, null, rtl, created, crosspost);
	}

	public PostSent(final String     body,
	                final String     title,
	                final Appearance font) {
		this(body, title, font, null, null, null, null);
	}

	public PostSent(final String body,
	                final String title,
	                final Locale lang) {
		this(body, title, null, lang, null, null, null);
	}

	public PostSent(final String  body,
	                final String  title,
	                final Boolean rtl) {
		this(body, title, null, null, rtl, null, null);
	}

	public PostSent(final String        body,
	                final String        title,
	                final ZonedDateTime created) {
		this(body, title, null, null, null, created, null);
	}

	public PostSent(final String          body,
	                final String          title,
	                final CrosspostInfo[] crosspost) {
		this(body, title, null, null, null, null, crosspost);
	}

	public PostSent(final String     body,
	                final Appearance font,
	                final Locale     lang) {
		this(body, null, font, lang, null, null, null);
	}

	public PostSent(final String     body,
	                final Appearance font,
	                final Boolean    rtl) {
		this(body, null, font, null, rtl, null, null);
	}

	public PostSent(final String        body,
	                final Appearance    font,
	                final ZonedDateTime created) {
		this(body, null, font, null, null, created, null);
	}

	public PostSent(final String          body,
	                final Appearance      font,
	                final CrosspostInfo[] crosspost) {
		this(body, null, font, null, null, null, crosspost);
	}

	public PostSent(final String  body,
	                final Locale  lang,
	                final Boolean rtl) {
		this(body, null, null, lang, rtl, null, null);
	}

	public PostSent(final String        body,
	                final Locale        lang,
	                final ZonedDateTime created) {
		this(body, null, null, lang, null, created, null);
	}

	public PostSent(final String          body,
	                final Locale          lang,
	                final CrosspostInfo[] crosspost) {
		this(body, null, null, lang, null, null, crosspost);
	}

	public PostSent(final String        body,
	                final Boolean       rtl,
	                final ZonedDateTime created) {
		this(body, null, null, null, rtl, created, null);
	}

	public PostSent(final String          body,
	                final Boolean         rtl,
	                final CrosspostInfo[] crosspost) {
		this(body, null, null, null, rtl, null, crosspost);
	}

	public PostSent(final String          body,
	                final ZonedDateTime   created,
	                final CrosspostInfo[] crosspost) {
		this(body, null, null, null, null, created, crosspost);
	}

	public PostSent(final String body,
	                final String title) {
		this(body, title, null, null, null, null, null);
	}

	public PostSent(final String     body,
	                final Appearance font) {
		this(body, null, font, null, null, null, null);
	}

	public PostSent(final String body,
	                final Locale lang) {
		this(body, null, null, lang, null, null, null);
	}

	public PostSent(final String  body,
	                final Boolean rtl) {
		this(body, null, null, null, rtl, null, null);
	}

	public PostSent(final String        body,
	                final ZonedDateTime created) {
		this(body, null, null, null, null, created, null);
	}

	public PostSent(final String          body,
	                final CrosspostInfo[] crosspost) {
		this(body, null, null, null, null, null, crosspost);
	}

	public PostSent(final String body) {
		this(body, null, null, null, null, null, null);
	}

	public final String          getBody() {
		return this.body;
	}

	public final String          getTitle() {
		return this.title;
	}

	public final Appearance      getFont() {
		return this.font;
	}

	public final Locale          getLang() {
		return this.lang;
	}

	public final Boolean         getRtl() {
		return this.rtl;
	}

	public final ZonedDateTime   getCreated() {
		return this.created;
	}

	public final CrosspostInfo[] getCrosspostInfo() {
		return this.crosspost;
	}
}
