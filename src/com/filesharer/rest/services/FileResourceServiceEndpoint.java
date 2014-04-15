package com.filesharer.rest.services;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.filesharer.common.file.FileMetaData;

@Path("/file")
public class FileResourceServiceEndpoint {
	
	@Path("/list")
	@GET
	public List<FileMetaData> getFileResourceList(@FormParam("upload") InputStream in) {
		
	}
}
