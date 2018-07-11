package org.jatin.elasticdemo.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface TagQueryBuilder {

	public JsonNode buildTagQuery();
}
