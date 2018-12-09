package fediverse.writefreely.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

public enum Appearance {
	// Sans-serif (Open Sans)
	@SerializedName("sans")
	 SANS("sans"),
	// Serif (Lora)
	@SerializedName("serif")
	SERIF("serif", "norm"),
	// Monospace
	@SerializedName("wrap")
	 WRAP("wrap"),
	// Monospace
	@SerializedName("mono")
	 MONO("mono"),
	// Syntax-highlighted monospace
	@SerializedName("code")
	 CODE("code");

	private static       Map<String,
	                         Appearance> appearances = new HashMap<String,
	                                                               Appearance>();
	private        final String[]            aliases;

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

	public static Appearance findByFont(final String appearance) {
		final Appearance app = appearances.get(appearance);

		if(app == null) {
			return Appearance.SERIF;
		} else {
			return app;
		}
	}
}
