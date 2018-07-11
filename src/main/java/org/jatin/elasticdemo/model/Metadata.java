package org.jatin.elasticdemo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metadata {
	
	private String region;
	
	@JsonProperty("long_synopsis")
	private String longSynopsis;
	
	@JsonProperty("meta_desc")
	private String metaDesc;
	
	private List<String> tags;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLongSynopsis() {
		return longSynopsis;
	}

	public void setLongSynopsis(String longSynopsis) {
		this.longSynopsis = longSynopsis;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getMetaDesc() {
		return metaDesc;
	}

	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}
	
}
