package controllers;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import play.mvc.Controller;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

public class SolrSearch extends Controller {
	private static final String solrSearchIndex = System.getenv("WEBSOLR_URL");
	
	public static void stats(){
		try {
			CommonsHttpSolrServer server = new CommonsHttpSolrServer(solrSearchIndex);
			SolrQuery query = new SolrQuery();
			query.setQuery("*:*");
			Long start, end;
			query.setRows(0); // don't actually request any data
			renderText("Searching %s addresses",server.query(query).getResults().getNumFound());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			renderText("Error !!!!");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			renderText("Error !!!!");
		}
	}
	public static void search(String q,boolean facetedSearch){
		try{
			  CommonsHttpSolrServer server = new CommonsHttpSolrServer( solrSearchIndex);
			  SolrQuery query = new SolrQuery();
			  query.setQuery( "text:"+q );
			  query.setFields("id","StreetAddress");
			  query.setFacet(true);
			  query.addFacetField("State");
			  
			  Long start,end;
			  start = System.currentTimeMillis();
			  QueryResponse queryResponse = server.query(query);
			  end = System.currentTimeMillis();
			  
			  if(facetedSearch){
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
				      searchResults.add((String) resultDoc.getFieldValue("StreetAddress"));
				  }
				  render(searchResults);
			  }
			  
			}catch(Exception ex){
				ex.printStackTrace();
				renderText("Error searching on Solr");
			}
		
	}
}
