package fediverse.writefreely.api;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *  Hello world!
 */
public class WriteFreelyAPI {
	private final URL domain;

	public WriteFreelyAPI(final String domain) throws MalformedURLException {
		this.domain = new URL(domain);
	}
}
