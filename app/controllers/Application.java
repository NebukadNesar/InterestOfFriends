package controllers;

import play.*;
import play.mvc.*;
import common.FBSecure;
import common.FBUtil;

import groovy.lang.Category;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import data.CategoryStruct;

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

		JsonElement je = FBUtil.getInstance().executeGraphRequest(
				"/me/friends", oauthToken);
		List<String> friendList = new ArrayList<String>();
		List<CategoryStruct> csdata = new ArrayList<CategoryStruct>();

		if ((je != null) && (je.isJsonObject())
				&& (je.getAsJsonObject().has("data"))) {
			JsonArray ja = je.getAsJsonObject().get("data").getAsJsonArray();
			JsonObject jo = null;
			friendList = new ArrayList<String>();
			String name = null;
			String id = null;
			String category = null;
			String date = null;
			String url=null;
			for (int i = 0; i < ja.size(); i++) {
				jo = ja.get(i).getAsJsonObject();
				JsonElement je2 = FBUtil.getInstance().executeGraphRequest(
						"/" + jo.get("id").getAsString(), oauthToken);
				String username = "" + je2.getAsJsonObject().get("username");
				username = username.replace("\"", "");
				String userlink = "" + je2.getAsJsonObject().get("link");
				userlink = userlink.replace("\"", "");
				JsonElement je3 = FBUtil.getInstance().executeGraphRequest(
						username + "/likes", oauthToken);
				System.out.println(je3);
				if (je3 != null) {
					JsonArray jarData = je3.getAsJsonObject().get("data")
							.getAsJsonArray();
					if (jarData != null) {
						JsonObject joObj = null;
						for (int t = 0; t < jarData.size(); t++) {
							CategoryStruct asd=null;
							joObj = jarData.get(t).getAsJsonObject();
							if (joObj.get("name") != null)
								name = joObj.get("name").getAsString();
							if (joObj.get("category") != null)
								category = joObj.get("category").getAsString();
							if (joObj.get("created_time") != null)
								date = joObj.get("created_time").getAsString();
							if (joObj.get("id") != null){
								id = joObj.get("id").getAsString();
								url="http://graph.facebook.com/"+id+"/picture";
							}	
							if (csdata.contains(id) == false)
								csdata.add(new CategoryStruct(name,category,date,id,url));
						}
					}
				}
				userlink = userlink + "/favorites";
				friendList.add("<a href=\"" + userlink + "\">" + userlink
						+ "</a>");
			}
		}

		String facebookName = null;

		je = FBUtil.getInstance().executeGraphRequest("/me", oauthToken);
		if ((je != null) && (je.isJsonObject())
				&& (je.getAsJsonObject().has("name"))) {
			facebookName = je.getAsJsonObject().get("name").getAsString();
		}

		String facebookApplicationID = Play.configuration
				.getProperty("fb.app.id");
		csdata.toString();
		render(facebookApplicationID, facebookName, friendList, csdata);
	}
	
	
	
	
}