package models;

public class SolrSearchResponse {
	public SolrSearchResponseHeader getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(SolrSearchResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	public SolrResponse getResponse() {
		return response;
	}
	public void setResponse(SolrResponse response) {
		this.response = response;
	}
	private SolrSearchResponseHeader responseHeader;
	private SolrResponse response;
}
