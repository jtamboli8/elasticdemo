package org.jatin.elasticdemo.service;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public interface TagQueryAppender {
	
	public static final String TAG_JSONPATH = "$.query.nested.query.bool.should";
	
	public JsonNode appendTagsInQuery(JsonNode query, JsonNode... tagsNodes);

}
