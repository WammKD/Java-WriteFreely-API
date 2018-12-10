package fediverse.writefreely.api.model;

import java.net.URI;

public class Channel {
	private final String id;
	private final URI    url;
	private final String name;
	private final String username;

	public Channel(final String id,
	               final URI    url,
	               final String name,
	               final String username) {
		this.id       = id;
		this.url      = url;
		this.name     = name;
		this.username = username;
	}

	public final String getID() {
		return this.id;
	}

	public final URI    getURL() {
		return this.url;
	}

	public final String getName() {
		return this.name;
	}

	public final String getUsername() {
		return this.username;
	}
}
