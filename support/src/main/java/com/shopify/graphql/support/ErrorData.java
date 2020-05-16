package com.shopify.graphql.support;

import java.io.Serializable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * <ul>
 * <li>Created by kmurray on 2020-05-14 to reflect Veritone's Error model
 * </ul>
 */
public class ErrorData implements Serializable {
	private final String serviceMessage;
	private final String requestId;
	private final ID objectId;
	private final String objectType;
	private final String errorCode;
	private final ID errorId;

	public ErrorData(String message) {
		this.serviceMessage = message;
		this.requestId = null;
		this.objectId = null;
		this.objectType = null;
		this.errorCode = null;
		this.errorId = null;
	}

	public ErrorData(JsonObject fields) {
		JsonElement element = fields.get("serviceMessage");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			this.serviceMessage = element.getAsString();
		}
		else {
			this.serviceMessage = null;
		}

		element = fields.get("requestId");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			this.requestId = element.getAsString();
		}
		else {
			this.requestId = null;
		}

		element = fields.get("objectId");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			this.objectId = new ID(element.getAsString());
		}
		else {
			this.objectId = null;
		}

		element = fields.get("objectType");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			this.objectType = element.getAsString();
		}
		else {
			this.objectType = null;
		}

		element = fields.get("errorCode");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			this.errorCode = element.getAsString();
		}
		else {
			this.errorCode = null;
		}

		element = fields.get("errorId");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			this.errorId = new ID(element.getAsString());
		}
		else {
			this.errorId = null;
		}

	}

	@Override
	public String toString() {
		return serviceMessage();
	}

	public String serviceMessage() {
		return serviceMessage;
	}

	public String requestId() {
		return requestId;
	}

	public ID objectId() {
		return objectId;
	}

	public String objectType() {
		return objectType;
	}

	public String errorCode() {
		return errorCode;
	}

	public ID errorId() {
		return errorId;
	}

}
