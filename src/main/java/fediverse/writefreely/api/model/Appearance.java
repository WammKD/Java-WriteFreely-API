package fediverse.writefreely.api.model;

import java.util.Map;
import java.util.HashMap;

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

	private static Map<String,
	                   Appearance> appearances = new HashMap<String,
	                                                         Appearance>();
	private        String[]            aliases;

	static {
		for(final Appearance app : Appearance.values()) {
			for(final String alias : app.aliases) {
				appearances.put(alias, app);
			}
		}
	}

	private Appearance(final String... aliases) {
		this.aliases = aliases;
	}

	public final String getFont() {
		return this.aliases[0];
	}

	public static Appearance getByFont(final String appearance) {
		final Appearance app = appearances.get(appearance);

		if(app == null) {
			return Appearance.SERIF;
		} else {
			return app;
		}
	}
}
