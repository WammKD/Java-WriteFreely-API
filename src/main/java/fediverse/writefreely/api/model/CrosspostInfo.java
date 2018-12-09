package fediverse.writefreely.api.model;

import com.google.gson.annotations.SerializedName;

public class CrosspostInfo {
	public enum CrosspostID {
		@SerializedName("twitter")
		TWITTER("twitter"),
		@SerializedName("tumblr")
		TUMBLR("tumblr"),
		@SerializedName("medium")
		MEDIUM("medium");

		private final String id;

		private CrosspostID(final String id) {
			this.id = id;
		}

		public final String getID() {
			return this.id;
		}

		public static CrosspostID findByID(final String name) {
			for(final CrosspostID cID : CrosspostID.values()) {
				if(cID.getID().equals(name)) {
					return cID;
				}
			}

			throw new IllegalArgumentException("The only acceptable IDs are "  +
			                                   "\"tumblr\", \"twitter\", and " +
			                                   "\"medium\".");
		}
	}

	public  static final int         TUMBLR        =  1;
	public  static final int         MEDIUM        =  0;
	public  static final int         TWITTER       = -1;
	private        final CrosspostID socialNetwork;
	private        final String      username;

	public CrosspostInfo(final int socialNetwork, final String username) {
		if(socialNetwork == TUMBLR) {
			this.socialNetwork = CrosspostID.TUMBLR;
		} else if(socialNetwork == MEDIUM) {
			this.socialNetwork = CrosspostID.MEDIUM;
		} else if(socialNetwork == TWITTER) {
			this.socialNetwork = CrosspostID.TWITTER;
		} else {
			throw new IllegalArgumentException("Social Network must be "     +
			                                   "TUMBLR (1), MEDIUM (2), or " +
			                                   "TWITTER (-1).");
		}

		this.username = username;
	}

	public CrosspostInfo(final CrosspostID socialNetwork,
	                     final String      username) {
		this.socialNetwork = socialNetwork;
		this.username      = username;
	}

	public final CrosspostID getSocialNetwork() {
		return this.socialNetwork;
	}

	public final String getUsername() {
		return this.username;
	}
}
