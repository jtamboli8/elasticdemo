package org.jatin.elasticdemo.service.impl;

import org.jatin.elasticdemo.service.TagJsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class TagJsonBuilderImpl implements TagJsonBuilder {
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public JsonNode buildTagJson(String tag) {
		ObjectNode objectNode = mapper.createObjectNode();
		ObjectNode matchNode = mapper.createObjectNode();
		matchNode.put("metadata.tags", tag);
		objectNode.set("match", matchNode);
		return objectNode;
	}

}
