package com.webapp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/category")
public class Category {

/*
URL call format:
http://localhost:8080/vlc/rest/vlc/parameters?URL=http://s3-us-west-1.amazonaws.com/shank7485/Music/Canon.mp3&code=start

http://localhost:8080/WebApp/rest/category/parameters?folder=Movies
http://localhost:8080/WebApp/rest/category/parameters?folder=Music
http://localhost:8080/WebApp/rest/category/parameters?folder=Others
*/
	
	
	@GET
	@Path("/parameters")
	@Produces(MediaType.TEXT_PLAIN)
	public String RespMsg(@QueryParam("folder") String folder){
		System.out.println("Somebody accesses");
		String value = folder;
		
		ScanFolder object = new ScanFolder(value);
		return object.Folderlist(object);
		
	}

}
