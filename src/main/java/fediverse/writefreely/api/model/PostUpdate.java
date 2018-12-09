package fediverse.writefreely.api.model;

import java.util.Locale;

public class PostUpdate {
	private final String     body;
	private final String     title;
	private final Appearance font;
	private final Locale     lang;
	private final Boolean    rtl;
	private final String     token;

	public PostUpdate(final String     body, final String title,
	                  final Appearance font, final Locale lang,
	                  final Boolean    rtl,  final String token) {
		this.body  = body;
		this.title = title;
		this.font  = font;
		this.lang  = lang;
		this.rtl   = rtl;
		this.token = token;
	}

	public PostUpdate(final String     body,
	                  final String     title,
	                  final Appearance font,
	                  final Locale     lang,
	                  final Boolean    rtl) {
		this(body, title, font, lang, rtl, null);
	}

	public PostUpdate(final String     body,
	                  final String     title,
	                  final Appearance font,
	                  final Locale     lang,
	                  final String     token) {
		this(body, title, font, lang, null, token);
	}

	public PostUpdate(final String     body,
	                  final String     title,
	                  final Appearance font,
	                  final Boolean    rtl,
	                  final String     token) {
		this(body, title, font, null, rtl, token);
	}

	public PostUpdate(final String  body,
	                  final String  title,
	                  final Locale  lang,
	                  final Boolean rtl,
	                  final String  token) {
		this(body, title, null, lang, rtl, token);
	}

	public PostUpdate(final String     body,
	                  final Appearance font,
	                  final Locale     lang,
	                  final Boolean    rtl,
	                  final String     token) {
		this(body, null, font, lang, rtl, token);
	}

	public PostUpdate(final String     body, final String title,
	                  final Appearance font, final Locale lang) {
		this(body, title, font, lang, null, null);
	}

	public PostUpdate(final String     body, final String  title,
	                  final Appearance font, final Boolean rtl) {
		this(body, title, font, null, rtl, null);
	}

	public PostUpdate(final String     body, final String title,
	                  final Appearance font, final String token) {
		this(body, title, font, null, null, token);
	}

	public PostUpdate(final String body, final String  title,
	                  final Locale lang, final Boolean rtl) {
		this(body, title, null, lang, rtl, null);
	}

	public PostUpdate(final String body, final String title,
	                  final Locale lang, final String token) {
		this(body, title, null, lang, null, token);
	}

	public PostUpdate(final String  body, final String title,
	                  final Boolean rtl,  final String token) {
		this(body, title, null, null, rtl, token);
	}

	public PostUpdate(final String body, final Appearance font,
	                  final Locale lang, final Boolean    rtl) {
		this(body, null, font, lang, rtl, null);
	}

	public PostUpdate(final String body, final Appearance font,
	                  final Locale lang, final String     token) {
		this(body, null, font, lang, null, token);
	}

	public PostUpdate(final String  body, final Appearance font,
	                  final Boolean rtl,  final String     token) {
		this(body, null, font, null, rtl, token);
	}

	public PostUpdate(final String  body, final Locale lang,
	                  final Boolean rtl,  final String token) {
		this(body, null, null, lang, rtl, token);
	}

	public PostUpdate(final String     body,
	                  final String     title,
	                  final Appearance font) {
		this(body, title, font, null, null, null);
	}

	public PostUpdate(final String body,
	                  final String title,
	                  final Locale lang) {
		this(body, title, null, lang, null, null);
	}

	public PostUpdate(final String  body,
	                  final String  title,
	                  final Boolean rtl) {
		this(body, title, null, null, rtl, null);
	}

	public PostUpdate(final String body,
	                  final String title,
	                  final String token) {
		this(body, title, null, null, null, token);
	}

	public PostUpdate(final String     body,
	                  final Appearance font,
	                  final Locale     lang) {
		this(body, null, font, lang, null, null);
	}

	public PostUpdate(final String     body,
	                  final Appearance font,
	                  final Boolean    rtl) {
		this(body, null, font, null, rtl, null);
	}

	public PostUpdate(final String     body,
	                  final Appearance font,
	                  final String     token) {
		this(body, null, font, null, null, token);
	}

	public PostUpdate(final String body, final Locale lang, final Boolean rtl) {
		this(body, null, null, lang, rtl, null);
	}

	public PostUpdate(final String body,
	                  final Locale lang,
	                  final String token) {
		this(body, null, null, lang, null, token);
	}

	public PostUpdate(final String  body,
	                  final Boolean rtl,
	                  final String  token) {
		this(body, null, null, null, rtl, token);
	}

	public PostUpdate(final boolean isTitle,
	                  final String  body,
	                  final String  titleOrToken) {
		this.body  = body;
		this.title = isTitle ? titleOrToken : null;
		this.font  = null;
		this.lang  = null;
		this.rtl   = null;
		this.token = isTitle ? null : titleOrToken;
	}

	public PostUpdate(final String body, final Appearance font) {
		this(body, null, font, null, null, null);
	}

	public PostUpdate(final String body, final Locale lang) {
		this(body, null, null, lang, null, null);
	}

	public PostUpdate(final String body, final Boolean rtl) {
		this(body, null, null, null, rtl, null);
	}

	public PostUpdate(final String body) {
		this(body, null, null, null, null, null);
	}

	public final String     getBody() {
		return this.body;
	}

	public final String     getTitle() {
		return this.title;
	}

	public final Appearance getFont() {
		return this.font;
	}

	public final Locale     getLang() {
		return this.lang;
	}

	public final Boolean    isRtL() {
		return this.rtl;
	}

	public final String     getToken() {
		return this.token;
	}
}
