package fediverse.writefreely.api.model;

import fediverse.writefreely.api.HTTPstatus;

public class ResponseWrapper<T> {
	private final HTTPstatus   code;
	private final T            data;
	private final PostReturned post;
	private final String       errorMsg;

	public ResponseWrapper(final HTTPstatus code,
	                       final T          data) {
		this.code     = code;
		this.data     = data;
		this.post     = null;
		this.errorMsg = null;
	}

	public ResponseWrapper(final int code,
	                       final T   data) {
		this.code     = HTTPstatus.findByCode(code);
		this.data     = data;
		this.post     = null;
		this.errorMsg = null;
	}

	public ResponseWrapper(final HTTPstatus   code,
	                       final PostReturned post) {
		this.code     = code;
		this.post     = post;
		this.data     = null;
		this.errorMsg = null;
	}

	public ResponseWrapper(final int          code,
	                       final PostReturned post) {
		this.code     = HTTPstatus.findByCode(code);
		this.post     = post;
		this.data     = null;
		this.errorMsg = null;
	}

	public ResponseWrapper(final HTTPstatus code,
	                       final String     errorMsg) {
		this.code     = code;
		this.errorMsg = errorMsg;
		this.data     = null;
		this.post     = null;
	}

	public ResponseWrapper(final int    code,
	                       final String errorMsg) {
		this.code     = HTTPstatus.findByCode(code);
		this.errorMsg = errorMsg;
		this.data     = null;
		this.post     = null;
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
		return this.errorMsg;
	}

	public final boolean      isError() {
		return this.data == null;
	}
}
