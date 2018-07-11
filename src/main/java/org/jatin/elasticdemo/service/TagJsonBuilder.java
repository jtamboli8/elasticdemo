package org.jatin.elasticdemo.service;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public interface TagJsonBuilder {
	
	public JsonNode buildTagJson(String tag);

}
