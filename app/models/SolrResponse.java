package models;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolrResponse {
	private Long numFound;
	private Long start;
	
	private List<Address> docs;
	
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getNumFound() {
		return numFound;
	}
	public void setNumFound(Long numFound) {
		this.numFound = numFound;
	}
	public List<Address> getDocs() {
		return docs;
	}
	public void setDocs(List<Address> docs) {
		this.docs = docs;
	}
}
