package fediverse.writefreely.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

public class User {
	private final String        name;
	private final Boolean       hasPass;
	private final String        eMail;
	private final ZonedDateTime created;

	@JsonCreator
	public User(@JsonProperty("username") final String        name,
	            @JsonProperty("has_pass") final Boolean       hasPass,
	            @JsonProperty("email")    final String        eMail,
	            @JsonProperty("created")  final ZonedDateTime created) {
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
