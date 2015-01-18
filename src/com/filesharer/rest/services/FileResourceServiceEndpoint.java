package com.filesharer.rest.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.wink.common.model.multipart.InMultiPart;
import org.apache.wink.common.model.multipart.InPart;

import com.filesharer.common.file.FileMetaData;

@Path("/files")
public class FileResourceServiceEndpoint {
	
	@GET
	public List<FileMetaData> getFileResourceList() {
		// TODO
		System.out.println("/files: get all files in a list");
		return null;
	}
	
	@POST
	@Path("/files/{name}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String upload(@PathParam("name") String fileName, InMultiPart imp) {
		while (imp.hasNext()) {
			InPart part = imp.next();
			try {
				/*MultivaluedMap<String, String> heades = part.getHeaders();
		        String filename = heades.getFirst("filename");*/
				InputStream is = part.getBody(InputStream.class, null);
				// TODO write file
			} catch (IOException e) {
				e.printStackTrace();
				return "Error";
			}
		}
		return "OK";
	}
}
