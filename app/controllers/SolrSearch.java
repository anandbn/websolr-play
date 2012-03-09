package controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.Address;
import models.SolrSearchResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Http.Header;


public class SolrSearch extends Controller {
	private static final String solrSearchIndex = System.getenv("WEBSOLR_URL")+"/select";
	private static ObjectMapper m = new ObjectMapper();

	public static void stats(){
		try {
			Map<String,String> reqParams = new HashMap<String,String>();
			reqParams.put("q","*:*");
			reqParams.put("wt","json");
			
			SolrSearchResponse response = doSolrQuery(reqParams);
			if(response!=null && response.getResponseHeader().getStatus()==0){
				renderJSON(response);
			}
			
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				renderText("Error !!!!");
		}
	}
	public static void search(String q,boolean facetedSearch){
		try {
			Map<String,String> reqParams = new HashMap<String,String>();
			reqParams.put("q",q);
			reqParams.put("wt","json");
			SolrSearchResponse response = doSolrQuery(reqParams);
			if(response!=null && response.getResponseHeader().getStatus()==0){
				// can either use mapper.readTree(JsonParser), or bind to JsonNode
				long numRecs =response.getResponse().getNumFound();
				DecimalFormat formatter = new DecimalFormat("###,###,###");
				renderJSON(response);
			}		
			
			
		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				renderText("Error !!!!");
		}
		
		/*try{
			  CommonsHttpSolrServer server = new CommonsHttpSolrServer( solrSearchIndex);
			  SolrQuery query = new SolrQuery();
			  query.setQuery( "text:"+q );
			  query.setFields("id","address_texts");
			 /* query.setFacet(true);
			  query.addFacetField("State");
			
			  Long start,end;
			  start = System.currentTimeMillis();
			  QueryResponse queryResponse = server.query(query);
			  end = System.currentTimeMillis();
			  
			 /* if(facetedSearch){
				  List<FacetField.Count> searchResults = new ArrayList<FacetField.Count>();
				  for (FacetField.Count entry:queryResponse.getFacetFields().get(0).getValues()) {
					  if(entry.getName().length()==2){
						  searchResults.add(entry);
						  
					  }
				  }
				  renderTemplate("SolrSearch/facetedSearch.html", searchResults); 
			  }else{
			  
				  List<String> searchResults = new ArrayList<String>();
				  SolrDocument resultDoc;
				  Iterator<SolrDocument> iter = queryResponse.getResults().iterator();
				  while (iter.hasNext()) {
					  resultDoc= iter.next();
				      searchResults.add((String) resultDoc.getFieldValue("address_texts"));
				  }
				  render(searchResults);
			//  }
			  */
				renderText("To be completed");
			/*}catch(Exception ex){
				ex.printStackTrace();
				renderText("Error searching on Solr");
			}*/
		
	}
	
	
	private static SolrSearchResponse doSolrQuery(Map<String,String> qryParams) throws JsonParseException, JsonMappingException, IOException{
		WS.HttpResponse response = WS.url(solrSearchIndex).setParameters(qryParams).get();
		if(response.getStatus()==200){
			
			System.out.println(">>>>>>>>>>>>doSolrQuery():responseBody="+response.getString());
			// can either use mapper.readTree(JsonParser), or bind to JsonNode
			return(m.readValue(response.getString(), SolrSearchResponse.class));
			
		}
		return null;
	}
}
