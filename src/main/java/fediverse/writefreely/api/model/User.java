package fediverse.writefreely.api.model;

import java.time.ZonedDateTime;

public class User {
	private final String        username;
	private final Boolean       hasPass;
	private final String        eMail;
	private final ZonedDateTime created;

	public User(final String username, final Boolean       hasPass,
	            final String email,    final ZonedDateTime created) {
		this.username = username;
		this.hasPass  = hasPass;
		this.eMail    = email;
		this.created  = created;
	}

	public final String        getUsername() {
		return this.username;
	}

	public final Boolean       doesHavePass() {
		return this.hasPass;
	}

	public final String        getEMail() {
		return this.eMail;
	}

	public final ZonedDateTime getCreated() {
		return this.created;
	}
}
