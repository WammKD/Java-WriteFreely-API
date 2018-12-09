package fediverse.writefreely.api.model;

public enum CrosspostID {
	TWITTER("twitter"), TUMBLR("tumblr"), MEDIUM("medium");

	private final String id;

	private CrosspostID(final String id) {
		this.id = id;
	}

	public final String getID() {
		return this.id;
	}

	public static CrosspostID getByID(final String name) {
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
