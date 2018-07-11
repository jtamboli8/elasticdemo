package org.jatin.elasticdemo.controller;

import java.util.HashMap;
import java.util.Map;

import org.jatin.elasticdemo.service.TagJsonBuilder;
import org.jatin.elasticdemo.service.TagQueryAppender;
import org.jatin.elasticdemo.service.TagQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class ElasticController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TagQueryBuilder tagQueryBuilder;
	
	@Autowired
	private TagJsonBuilder tagJsonBuilder;
	
	@Autowired
	private TagQueryAppender tagQueryAppender;
	
	@PutMapping(path="/v1/add", consumes="application/json", produces="application/json")
	public JsonNode addDocument(@RequestBody(required=true)JsonNode data) {
		JsonNode resultBody = null;
		String id = data.get("id").asText();
		try {
			//String contentJson = mapper.writeValueAsString(data);
			Map<String,String> uriVariables = new HashMap<>();
			uriVariables.put("id", id);
			ResponseEntity<JsonNode> result = restTemplate.exchange("http://localhost:9200/contents/content/{id}", HttpMethod.POST, new HttpEntity<JsonNode>(data), JsonNode.class, uriVariables);
			resultBody = result.getBody();
			System.out.println(resultBody);
		} catch(RestClientException e) {
			throw new RuntimeException(e);
		}
		return resultBody;
	}
	
	@DeleteMapping(path="/v1/delete/{id}", consumes="application/json")
	public void delete(@PathVariable(required=true)String id) {
		try {
			//String contentJson = mapper.writeValueAsString(data);
			Map<String,String> uriVariables = new HashMap<>();
			uriVariables.put("id", id);
			restTemplate.delete("http://localhost:9200/contents/content/{id}", uriVariables);
		} catch(RestClientException e) {
			throw new RuntimeException(e);
		}
	}
	
	@GetMapping(path="/v1/search/{tags}")
	public JsonNode searchByTags(@PathVariable(name="tags") String commaSeperatedTags) {
		String[] tags = commaSeperatedTags.split(",");
		JsonNode outerQueryNode = tagQueryBuilder.buildTagQuery();
		JsonNode searchTagQueryJson = null;
		for(String tag : tags){
			JsonNode tagJson = tagJsonBuilder.buildTagJson(tag.trim());
			searchTagQueryJson = tagQueryAppender.appendTagsInQuery(outerQueryNode, tagJson);
		}
		System.out.println("Query Build:"+searchTagQueryJson);
		if(searchTagQueryJson == null) {
			throw new RuntimeException("Invalid Search Query Build");
		}
		ResponseEntity<JsonNode> exchange = restTemplate.exchange("http://localhost:9200/contents/_search", HttpMethod.GET, 
				new HttpEntity<JsonNode>(searchTagQueryJson), JsonNode.class);
		return exchange.getBody();
	}

}
