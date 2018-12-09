package fediverse.writefreely.api.model;

public class Auth {
	private final String accessToken;
	private final User   user;

	public Auth(final String accessToken, final User user) {
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
