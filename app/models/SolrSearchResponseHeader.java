package models;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class SolrSearchResponseHeader {
	private Integer status;
	
	private Long QTime;
	
	private Map<String,String> params;

	public Long getQTime() {
		return QTime;
	}
	@JsonProperty("QTime")
	public void setQTime(Long qTime) {
		QTime = qTime;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Map<String,String> getParams() {
		return params;
	}
	public void setParams(Map<String,String> params) {
		this.params = params;
	}
}
