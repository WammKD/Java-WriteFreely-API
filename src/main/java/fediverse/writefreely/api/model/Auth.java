package fediverse.writefreely.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Auth {
	private final String accessToken;
	private final User   user;

	@JsonCreator
	public Auth(@JsonProperty("access_token") final String accessToken,
	            @JsonProperty("user")         final User   user) {
		this.accessToken = accessToken;
		this.user        = user;
	}

	public final String getAccessToken() {
		return this.accessToken;
	}

	public final User   getUser() {
		return this.user;
	}
}
