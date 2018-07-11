package org.jatin.elasticdemo.service.impl;

import org.jatin.elasticdemo.service.TagQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

@Service
public class TagQueryBuilderImpl implements TagQueryBuilder{
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	public JsonNode buildTagQuery() {
		ObjectNode outerQueryNode = mapper.createObjectNode();
		ObjectNode nestedNode = mapper.createObjectNode();
		nestedNode.set("path", new TextNode("metadata"));
		ObjectNode query = mapper.createObjectNode();
		ObjectNode queryNode = mapper.createObjectNode();
		ObjectNode boolNode = mapper.createObjectNode();
		ArrayNode shouldNode = mapper.createArrayNode();
		boolNode.set("should", shouldNode);
		queryNode.set("bool", boolNode);
		nestedNode.set("query", queryNode);
		query.set("nested", nestedNode);
		outerQueryNode.set("query", query);
		return outerQueryNode;
	}

}
