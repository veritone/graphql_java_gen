package com.shopify.graphql.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.veritone.sdk.scalar.DateTime;

/**
 * <ul>
 * <li>Created by eapache on 2015-11-17.
 * <li>Updated by kmurray on 2020-05-14 to reflect Veritone's Error model
 * </ul>
 */
public class Error implements Serializable {
	private final String message;
	private final String name;
	private final DateTime timeThrown;
	private final ErrorData data;
	private final List<String> path;
	private final List<ErrorLocation> locations;

	public Error(String message) {
		this.message = message;
		this.name = null;
		this.timeThrown = null;
		this.data = null;
		this.path = null;
		this.locations = null;
	}

	public Error(JsonObject fields) {
		JsonElement element = fields.get("message");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			this.message = element.getAsString();
		}
		else {
			this.message = "Unknown error";
		}

		element = fields.get("name");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			this.name = element.getAsString();
		}
		else {
			this.name = null;
		}

		element = fields.get("time_thrown");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
			this.timeThrown = new DateTime(element.getAsString());
		}
		else {
			this.timeThrown = null;
		}

		element = fields.get("data");
		if (element != null && element.isJsonObject()) {
			this.data = new ErrorData(element.getAsJsonObject());
		}
		else {
			this.data = null;
		}

		element = fields.get("path");
		if (element != null && element.isJsonArray()) {
			this.path = new ArrayList<>();
			element.getAsJsonArray().forEach(e -> {
				if (e.isJsonPrimitive()) this.path.add(e.getAsString());
			});
		}
		else {
			this.path = null;
		}

		element = fields.get("locations");
		if (element != null && element.isJsonArray()) {
			this.locations = new ArrayList<>();
			element.getAsJsonArray().forEach(e -> {
				if (e.isJsonObject()) this.locations.add(new ErrorLocation(e.getAsJsonObject()));
			});
		}
		else {
			this.locations = null;
		}
	}

	@Override
	public String toString() {
		return message();
	}

	public String message() {
		return message;
	}

	public String name() {
		return name;
	}

	public DateTime timeThrown() {
		return timeThrown;
	}

	public ErrorData data() {
		return data;
	}

	public List<String> path() {
		return path;
	}

	public List<ErrorLocation> locations() {
		return locations;
	}
}
