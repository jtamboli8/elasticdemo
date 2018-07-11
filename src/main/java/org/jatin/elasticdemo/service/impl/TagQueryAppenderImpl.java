package org.jatin.elasticdemo.service.impl;

import org.jatin.elasticdemo.service.TagQueryAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

@Service
public class TagQueryAppenderImpl implements TagQueryAppender{
	
	@Autowired
	private Configuration configuration;

	@Override
	public JsonNode appendTagsInQuery(JsonNode query, JsonNode... tagsNodes) {
		for(JsonNode tagsNode : tagsNodes) {
			JsonPath.using(configuration).parse(query).add(TAG_JSONPATH, tagsNode);
		}
		return query;
	}

}
