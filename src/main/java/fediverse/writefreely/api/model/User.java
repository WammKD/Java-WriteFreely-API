package fediverse.writefreely.api.model;

import java.time.ZonedDateTime;

public class User {
	private final String        name;
	private final Boolean       hasPass;
	private final String        eMail;
	private final ZonedDateTime created;

	public User(final String name,  final Boolean       hasPass,
	            final String eMail, final ZonedDateTime created) {
		this.name    = name;
		this.hasPass = hasPass;
		this.eMail   = eMail;
		this.created = created;
	}

	public final String        getName() {
		return this.name;
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
