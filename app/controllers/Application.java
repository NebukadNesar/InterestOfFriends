package controllers;

import play.*;
import play.mvc.*;
import common.FBSecure;
import common.FBUtil;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import models.*;

@With(FBSecure.class)
public class Application extends FBController {

    public static void index() {
      User currentUser = getUser();
      if (currentUser == null) {
        notFound("INVALID_USER"); // we should not be here!
      }
      
    	String oauthToken = getOAuthToken();
      if (currentUser == null) {
        notFound("INVALID_AUTH_TOKEN"); // we should not be here!
      }
    	
    	JsonElement je = FBUtil.getInstance().executeGraphRequest("/me/friends", oauthToken);
    	List<String> friendList = new ArrayList<String>();
    	
    	if ((je != null) && (je.isJsonObject()) && (je.getAsJsonObject().has("data"))) {
    		JsonArray ja = je.getAsJsonObject().get("data").getAsJsonArray();
    		JsonObject jo = null;
    		friendList = new ArrayList<String>();
    		for (int i = 0; i < ja.size(); i++) {
    			jo = ja.get(i).getAsJsonObject();
    			friendList.add("<a target=\"_top\" href=\"http://www.facebook.com/profile.php?id=" + jo.get("id").getAsString() + "\">" + jo.get("name").getAsString() + "</a>, facebook id: " + jo.get("id").getAsString());
    		}
    	}
    	
    	String facebookName = null;
    	
    	je = FBUtil.getInstance().executeGraphRequest("/me", oauthToken);
    	if ((je != null) && (je.isJsonObject()) && (je.getAsJsonObject().has("name"))) {
    		facebookName = je.getAsJsonObject().get("name").getAsString();
    	}
    	
    	String facebookApplicationID = Play.configuration.getProperty("fb.app.id"); 
      render(facebookApplicationID, facebookName, friendList);
    }

}