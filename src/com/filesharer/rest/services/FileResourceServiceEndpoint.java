package com.filesharer.rest.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.filesharer.common.file.FileMetaData;

@Path("/file")
public class FileResourceServiceEndpoint {
	
	@Path("/all")
	@GET
	public List<FileMetaData> getAllFileResources() {
		
	}
}
