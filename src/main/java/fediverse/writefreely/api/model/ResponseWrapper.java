package fediverse.writefreely.api.model;

import fediverse.writefreely.api.HTTPstatus;

public class ResponseWrapper<T> {
	private final HTTPstatus code;
	private final T          data;
	private final String     errorMsg;

	public ResponseWrapper(final HTTPstatus code,
	                       final T          data) {
		this.code     = code;
		this.data     = data;
		this.errorMsg = null;
	}

	public ResponseWrapper(final int code,
	                       final T   data) {
		this.code     = HTTPstatus.findByCode(code);
		this.data     = data;
		this.errorMsg = null;
	}

	public ResponseWrapper(final HTTPstatus code,
	                       final String     errorMsg) {
		this.code     = code;
		this.errorMsg = errorMsg;
		this.data     = null;
	}

	public ResponseWrapper(final int    code,
	                       final String errorMsg) {
		this.code     = HTTPstatus.findByCode(code);
		this.errorMsg = errorMsg;
		this.data     = null;
	}

	public final HTTPstatus getCode() {
		return this.code;
	}

	public final T          getData() {
		return this.data;
	}

	public final String     getErrorMsg() {
		return this.errorMsg;
	}

	public final boolean    isError() {
		return this.data == null;
	}
}
