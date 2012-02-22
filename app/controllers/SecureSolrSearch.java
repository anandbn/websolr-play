package controllers;

import controllers.force.ForceController;

import play.mvc.Controller;
import play.mvc.Http.Header;
import play.mvc.With;

@With(ForceController.class)
public class SecureSolrSearch extends Controller{
	public static void index() {
        render();
    }
}
