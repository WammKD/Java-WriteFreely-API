package fediverse.writefreely.api.model;

import fediverse.writefreely.api.HTTPstatus;

public class ResponseWrapper<T> {
	private final String       id;
	private final HTTPstatus   code;
	private final T            data;
	private final PostReturned post;
	private final String       error_msg;

	public ResponseWrapper(final HTTPstatus code,
	                       final T          data) {
		this.code      = code;
		this.data      = data;
		this.id        = null;
		this.post      = null;
		this.error_msg = null;
	}

	public ResponseWrapper(final int code,
	                       final T   data) {
		this.code      = HTTPstatus.findByCode(code);
		this.data      = data;
		this.id        = null;
		this.post      = null;
		this.error_msg = null;
	}

	public ResponseWrapper(final HTTPstatus   code,
	                       final PostReturned post) {
		this.code      = code;
		this.post      = post;
		this.id        = null;
		this.data      = null;
		this.error_msg = null;
	}

	public ResponseWrapper(final int          code,
	                       final PostReturned post) {
		this.code      = HTTPstatus.findByCode(code);
		this.post      = post;
		this.id        = null;
		this.data      = null;
		this.error_msg = null;
	}

	public ResponseWrapper(final String     id,
	                       final HTTPstatus code) {
		this.id        = id;
		this.code      = code;
		this.post      = null;
		this.data      = null;
		this.error_msg = null;
	}

	public ResponseWrapper(final String id,
	                       final int    code) {
		this.id        = id;
		this.code      = HTTPstatus.findByCode(code);
		this.post      = null;
		this.data      = null;
		this.error_msg = null;
	}

	public ResponseWrapper(final HTTPstatus code,
	                       final String     error_msg) {
		this.code      = code;
		this.error_msg = error_msg;
		this.id        = null;
		this.data      = null;
		this.post      = null;
	}

	public ResponseWrapper(final int    code,
	                       final String error_msg) {
		this.code      = HTTPstatus.findByCode(code);
		this.error_msg = error_msg;
		this.id        = null;
		this.data      = null;
		this.post      = null;
	}

	public final String       getID() {
		return this.id;
	}

	public final HTTPstatus   getCode() {
		return this.code;
	}

	public final T            getData() {
		return this.data;
	}

	public final PostReturned getPost() {
		return this.post;
	}

	public final String       getErrorMsg() {
		return this.error_msg;
	}

	public final boolean      isError() {
		return this.id == null && this.data == null && this.post == null;
	}
}
