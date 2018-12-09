package fediverse.writefreely.api.model;

import java.util.HashMap;
import java.util.Map;

public enum Appearance {
	// Sans-serif (Open Sans)
	 SANS("sans"),
	// Serif (Lora)
	SERIF("serif", "norm"),
	// Monospace
	 WRAP("wrap"),
	// Monospace
	 MONO("mono"),
	// Syntax-highlighted monospace
	 CODE("code");

	private static       Map<String,
	                         Appearance> appearances = new HashMap<String,
	                                                               Appearance>();
	private        final String[]            aliases;
	private              String            origValue;

	static {
		for(final Appearance app : Appearance.values()) {
			for(final String alias : app.aliases) {
				app.setFont(alias);
				appearances.put(alias, app);
			}
		}
	}

	private Appearance(final String... aliases) {
		this.aliases = aliases;
	}

	public final String getFont() {
		return this.origValue;
	}

	public static Appearance findByFont(final String appearance) {
		final Appearance app = appearances.get(appearance);

		if(app == null) {
			return Appearance.SERIF;
		} else {
			return app;
		}
	}

	private void setFont(final String appearance) {
		this.origValue = appearance;
	}
}
