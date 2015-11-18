package com.webapp.rest;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.webapp.rest.FolderList;

public class ScanFolder {

	private String VideoCategory;

	public ScanFolder(String VideoCategory) {
		this.VideoCategory = VideoCategory;
	}

	public String Folderlist(ScanFolder scan) {

		FolderList movieList = new FolderList();

		List<String> ListOfItemsinFolder = new ArrayList<String>();

		AWS object = new AWS();

		ListOfItemsinFolder = object.ListVideos(VideoCategory);

		movieList.setListOfItemsinFolder(ListOfItemsinFolder);

		Gson gson = new Gson();

		String json = gson.toJson(movieList);

		return json;

	}

}
