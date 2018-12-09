package fediverse.writefreely.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fediverse.writefreely.api.HTTPstatus;

public class ResponseWrapper<T> {
	private final HTTPstatus code;
	private final T          data;
	private final String     errorMsg;

	@JsonCreator
	public ResponseWrapper(@JsonProperty("code") final int code,
	                       @JsonProperty("data") final T   data) {
		this.code     = HTTPstatus.findByCode(code);
		this.data     = data;
		this.errorMsg = null;
	}

	@JsonCreator
	public ResponseWrapper(@JsonProperty("code")      final int    code,
	                       @JsonProperty("error_msg") final String errorMsg) {
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
