package com.shopify.graphql.support;

import java.io.Serializable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * <ul>
 * <li>Created by kmurray on 2020-05-14 to reflect Veritone's Error model
 * </ul>
 */
public class ErrorLocation implements Serializable {
	private final int line;
	private final int column;

	public ErrorLocation(String message) {
		this.line = 0;
		this.column = 0;
	}

	public ErrorLocation(JsonObject fields) {
		JsonElement element = fields.get("line");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
			this.line = element.getAsInt();
		}
		else {
			this.line = 0;
		}

		element = fields.get("column");
		if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
			this.column = element.getAsInt();
		}
		else {
			this.column = 0;
		}

	}

	@Override
	public String toString() {
		return line + "," + column;
	}

	public int line() {
		return line;
	}

	public int column() {
		return column;
	}

}
