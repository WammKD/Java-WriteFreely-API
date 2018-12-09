package fediverse.writefreely.api;

public enum HTTPstatus {
	                            CONTINUE(100, "Continue",                             "The client should continue with its request."),
	                 SWITCHING_PROTOCOLS(101, "Switching Protocols",                  "Informs the client that the server will switch to the "  +
	                                                                                  "protocol specified in the Upgrade message header field."),
	                          PROCESSING(102, "Processing",                           "The server requiring a long time to complete " +
	                                                                                  "the request. This prevents the client from "   +
	                                                                                  "timing out and assuming the request was lost."),
	                         EARLY_HINTS(103, "Early Hints",                          "Used to return some response headers before entire HTTP response."),

	                                  OK(200, "OK",                                   "The request sent by the client was successful."),
	                             CREATED(201, "Created",                              "The request was successful and the resource has been created."),
	                            ACCEPTED(202, "Accepted",                             "The request has been accepted but has not yet finished processing."),
	        NONAUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information",        "The returned meta-information in the entity header "   +
	                                                                                  "is not the definitative set of information, it might " +
	                                                                                  "be a local copy or contain local alterations."),
	                          NO_CONTENT(204, "No Content",                           "The request was successful but not "   +
	                                                                                  "require the return of an entity body."),
	                       RESET_CONTENT(205, "Reset Content",                        "The request was successful and the user agent " +
	                                                                                  "should reset the view that sent the request."),
	                     PARTIAL_CONTENT(206, "Partial Content",                      "The partial request was successful."),
	                        MULTI_STATUS(207, "Multi-Status",                         "The message body that follows is an XML message and " +
	                                                                                  "can contain a number of separate response codes, "    +
	                                                                                  "depending on how many sub-requests were made."),
	                    ALREADY_REPORTED(208, "Already Reported",                     "The members of a DAV binding have already been enumerated in a "   +
	                                                                                  "previous reply to this request, and are not being included again."),
	                             IM_USED(226, "IM Used",                              "The server has fulfilled a request for the resource, and "    +
	                                                                                  "the response is a representation of the result of one or "    +
	                                                                                  "more instance-manipulations applied to the current instance."),

	                    MULTIPLE_CHOICES(300, "Multiple Choices",                     "The requested resource has multiple choices, " +
	                                                                                  "each of which has a different location."),
	                   MOVED_PERMANENTLY(301, "Moved Permanently",                    "The requested resources has moved permanently to a new location."),
	                               FOUND(302, "Found",                                "The requested resource has been found at a different " +
	                                                                                  "location but the client should use the original URI."),
	                           SEE_OTHER(303, "See Other",                            "The requested resource is located at a different location "      +
	                                                                                  "which should be returned by the location field in the response."),
	                        NOT_MODIFIED(304, "Not Modified",                         "The resource has not been modified since the last request."),
	                           USE_PROXY(305, "Use Proxy",                            "The requested resource can only be accessed through a " +
	                                                                                  "proxy which should be provided in the location field."),
	                              UNUSED(306, "Unused",                               "This status code is no longer in "   +
	                                                                                  "use but is reserved for future use."),
	                  TEMPORARY_REDIRECT(307, "Temporary Redirect",                   "The requested resource is temporarily moved to the "  +
	                                                                                  "provided location but the client should continue "    +
	                                                                                  "to use this location as the resource may again move."),
	                  PERMANENT_REDIRECT(308, "Permanent Redirect",                   "The request and all future requests should be repeated "    +
	                                                                                  "using another URI. Do not allow the HTTP method to change."),

	                         BAD_REQUEST(400, "Bad Request",                          "The request could not be understood by the server."),
	                        UNAUTHORIZED(401, "Unauthorized",                         "The request requires authorization."),
	                    PAYMENT_REQUIRED(402, "Payment Required",                     "Reserved for future use."),
	                           FORBIDDEN(403, "Forbidden",                            "Whilst the server did understand the request, the server is "   +
	                                                                                  "refusing to complete it. This is not an authorization problem."),
	                           NOT_FOUND(404, "Not Found",                            "The requested resource was not found."),
	                  METHOD_NOT_ALLOWED(405, "Method Not Allowed",                   "The supplied method was not allowed on the given resource."),
	                      NOT_ACCEPTABLE(406, "Not Acceptable",                       "The resource is not able to return a response that is suitable for " +
	                                                                                  "the characteristics required by the accept headers of the request."),
	       PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required",        "The client must authenticate themselves with the proxy."),
	                     REQUEST_TIMEOUT(408, "Request Timeout",                      "The client did not supply a request "  +
	                                                                                  "in the period required by the server."),
	                            CONFLICT(409, "Conflict",                             "The request could not be completed as " +
	                                                                                  "the resource is in a conflicted state."),
	                                GONE(410, "Gone",                                 "The requested resource is no longer available on " +
	                                                                                  "the server and no redirect address is available."),
	                     LENGTH_REQUIRED(411, "Length Required",                      "The server will not accept the request " +
	                                                                                  "without a Content-Length field."),
	                 PRECONDITION_FAILED(412, "Precondition Failed",                  "The supplied precondition evaluated to false on the server."),
	            REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large",             "The request was unsuccessful because the request " +
	                                                                                  "entity was larger than the server would allow"),
	              REQUESTED_URI_TOO_LONG(414, "Request URI Too Long",                 "The request was unsuccessful because the requested URI is longer " +
	                                                                                  "than the server is willing to process (that's what she said)."),
	              UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type",               "The request was unsuccessful because the " +
	                                                                                  "request was for an unsupported format."),
	       REQUEST_RANGE_NOT_SATISFIABLE(416, "Request Range Not Satisfiable",        "The range of the resource does not overlap with the " +
	                                                                                  "values specified in the requests Range header field " +
	                                                                                  "and not alternative If-Range field was supplied."),
	                  EXPECTATION_FAILED(417, "Expectation Failed",                   "The expectation supplied in the Expectation " +
	                                                                                  "header field could not be met by the server."),
	                         IM_A_TEAPOT(418, "I'm a teapot",                         "I'm a teapot"),
	                      METHOD_FAILURE(420, "Method Failure",                       "A deprecated response when a method has failed."),
	                   ENHANCE_YOUR_CALM(420, "Enhance Your Calm",                    "The client is being rate limited"),
	                 MISDIRECTED_REQUEST(421, "Misdirected Request",                  "The request was directed at a server that is not able to "   +
	                                                                                  "produce a response (for example because a connection reuse)"),
	                  UNPROCESSED_ENTITY(422, "Unprocessed Entity",                   "The request was well-formed but was unable " +
	                                                                                  "to be followed due to semantic errors."),
	                              LOCKED(423, "Locked",                               "The resource that is being accessed is locked."),
	                   FAILED_DEPENDENCY(424, "Failed Dependency",                    "The request failed due to failure of a previous request."),
	                    UPGRADE_REQUIRED(426, "Upgrade Required",                     "The client should switch to a different "     +
	                                                                                  "protocol, given in the Upgrade header field."),
	               PRECONDITION_REQUIRED(428, "Precondition Required",                "The origin server requires the request to be conditional. " +
	                                                                                  "Intended to prevent the lost update problem."),
	                   TOO_MANY_REQUESTS(429, "Too Many Requests",                    "The user has sent too many requests in a given amount " +
	                                                                                  "of time. Intended for use with rate-limiting schemes."),
	     REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large",      "The server is unwilling to process the request because either an " +
	                                                                                  "individual header field, or all the header fields, are too large."),
	                      LOGIN_TIME_OUT(440, "Login Time Out",                       "The client's session has expired."),
	                         NO_RESPONSE(444, "No Response",                          "Returned no information to the client and closed the connection."),
	                          RETRY_WITH(449, "Retry With",                           "The server cannot honour the request because the " +
	                                                                                  "user has not provided the required information."),
	BLOCKED_BY_WINDOWS_PARENTAL_CONTROLS(450, "Blocked by Windows Parental Controls", "When Windows Parental Controls are turned on " +
	                                                                                  "and are blocking access to the given webpage."),
	       UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons",        "A server operator has received a legal demand "  +
	                                                                                  "to deny access to a resource or to a set of "    +
	                                                                                  "resources that includes the requested resource."),
	                            REDIRECT(451, "Redirect",                             "Used when either a more efficient server is available " +
	                                                                                  "or the server cannot access the users' mailbox."),
	               SSL_CERTIFICATE_ERROR(495, "SSL Certificate Error",                "The client has provided an invalid client certificate."),
	            SSL_CERTIFICATE_REQUIRED(496, "SSL Certificate Required",             "A client certificate is required but not provided."),
	     HTTP_REQUEST_SENT_TO_HTTPS_PORT(497, "HTTP Request Sent to HTTPS Port",      "client has made a HTTP request to a " +
	                                                                                  "port listening for HTTPS requests."),
	                       INVALID_TOKEN(498, "Invalid Token",                        "Indicates an expired or otherwise invalid token."),
	                      TOKEN_REQUIRED(499, "Token Required",                       "Indicates that a token is required but was not submitted."),
	               CLIENT_CLOSED_REQUEST(499, "Client Closed Request",                "The client has closed the request before " +
	                                                                                  "the server could send a response."),

	               INTERNAL_SERVER_ERROR(500, "Internal Server Error",                "The request was unsuccessful because the " +
	                                                                                  "server encountered an unexpected error."),
	                     NOT_IMPLEMENTED(501, "Not Implemented",                      "The server does not support the request."),
	                         BAD_GATEWAY(502, "Bad Gateway",                          "The server, whilst acting as a proxy, received an invalid " +
	                                                                                  "response from the server that was fulfilling the request."),
	                 SERVICE_UNAVAILABLE(503, "Service Unavailable",                  "The request was unsuccessful as the server is "   +
	                                                                                  "either down or slash^H^H^H^H^Hdug^H^H^Hreddited."),
	                     GATEWAY_TIMEOUT(504, "Gateway Timeout",                      "The server, whilst acting as a proxy, did not receive a " +
	                                                                                  "response from the upstream server in an acceptable time."),
	          HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported",           "The server does not supported the HTTP "   +
	                                                                                  "protocol version specified in the request"),
	             VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates",              "Transparent content negotiation for the " +
	                                                                                  "request results in a circular reference."),
	                INSUFFICIENT_STORAGE(507, "Insufficient Storage",                 "The server is unable to store the representation " +
	                                                                                  "needed to complete the request."),
	                       LOOP_DETECTED(508, "Loop Detected",                        "The server detected an infinite loop while processing the request."),
	            BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded",             "The server has exceeded the bandwidth " +
	                                                                                  "specified by the server administrator."),
	                        NOT_EXTENDED(510, "Not Extended",                         "Further extensions to the request are " +
	                                                                                  "required for the server to fulfill it."),
	     NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required",      "The client needs to authenticate to gain network access."),
	                       UNKNOWN_ERROR(520, "Unknown Error",                        "Server returns something unexpected."),
	                  WEB_SERVER_IS_DOWN(521, "Web Server is Down",                   "Refused the connection."),
	                CONNECTION_TIMED_OUT(522, "Connection Timed Out",                 "Cannot negotiate a TCP handshake with the origin server."),
	               ORIGIN_IS_UNREACHABLE(523, "Origin is Unreachable",                "Cannot reach the origin server"),
	                  A_TIMEOUT_OCCURRED(524, "A Timeout Occurred",                   "Did not receive a timely HTTP response."),
	                SSL_HANDSHAKE_FAILED(525, "SSL Handshake Failed",                 "Cannot negotiate a SSL/TLS handshake with the origin server."),
	             INVALID_SSL_CERTIFICATE(526, "Invalid SSL Certificate",              "Cannot validate the SSL/TLS certificate " +
	                                                                                  "that the origin server presented."),
	                       RAILGUN_ERROR(527, "Railgun Error",                        "The requests timeout or failed after the " +
	                                                                                  "WAN connection has been established."),
	                      SITE_IS_FROZEN(530, "Site is Frozen",                       "Indicate a site that has been frozen due to inactivity."),
	          NETWORK_READ_TIMEOUT_ERROR(598, "Network read timeout error",           "To signal a network read timeout behind "     +
	                                                                                  "the proxy to a client in front of the proxy."),
	       NETWORK_CONNECT_TIMEOUT_ERROR(599, "Network connect timeout error",        "Indicate when the connection to the network times out."),

	                             UNKNOWN(999, "Unknown HTTP Status Code",             "Unknown or unsupported HTTP status code");

	private final int    code;
	private final String name;
	private final String description;
	private final String jsonString;

	private HTTPstatus(final int    code,
	                   final String name,
	                   final String description) {
		this.code        = code;
		this.name        = name;
		this.description = description;
		this.jsonString  = generateJsonString();
	}

	/**
	 *  Returns the int status code this enum represents
	 *
	 *  @return the int status code this enum represents
	 */
	public final int getCode() {
		return this.code;
	}

	/**
	 *  Returns the name of the HTTP status this enum represents
	 *
	 *  @return the name of the HTTP status this enum represents
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 *  Returns a description of the HTTP status this enum represents
	 *
	 *  @return a description of the HTTP status this enum represents
	 */
	public final String getDescription() {
		return this.description;
	}

	/**
	 *  Returns a json string representing this HTTPstatus
	 *
	 *  @return a json string representing this HTTPstatus
	 */
	public final String getJsonString() {
		return this.jsonString;
	}

	/**
	 *  Returns the HTTPstatus object with a code matching the supplied int
	 *
	 *  @param httpStatus
	 *             the httpStatus code
	 *  @return the HTTPstatus object with a code matching the supplied int
	 */
	public static HTTPstatus findByCode(final int httpStatus) {
		for(final HTTPstatus status : HTTPstatus.values()) {
			if(status.getCode() == httpStatus) {
				return status;
			}
		}

		return UNKNOWN;
	}

	/**
	 *  Returns the HTTPstatus with the Integer code that matches the supplied
	 *  String. Returns HTTPstatus.Unknown if the supplied String is not a valid
	 *  Integer or is not in the list of available HTTP status codes.
	 *
	 *  @param httpStatus
	 *             the String containing the status code to match
	 *  @return the HTTPstatus for the supplied String
	 */
	public static HTTPstatus findByCode(final String httpStatus) {
		final int statusCode;

		try {
			statusCode = Integer.parseInt(httpStatus);
		} catch(final NumberFormatException e) {
			return UNKNOWN;
		}

		return findByCode(statusCode);
	}

	/**
	 *  Returns a json string representing this HTTPstatus
	 *
	 *  @return a json string representing this HTTPstatus
	 */
	public String generateJsonString() {
		return String.format("{\"code\": %s, \"name\": \"%s\", \"description\": \"%s\"}",
		                     this.code,
		                     this.name,
		                     this.description);
	}
}
