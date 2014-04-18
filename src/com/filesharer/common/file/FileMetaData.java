package com.filesharer.common.file;

import com.filesharer.common.user.User;

public class FileMetaData {
	// full qualified path
	private String path;
	private String fileName;
	private long lastModified;
	private long createTime;
	private String type;
	private User user;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public static class FileMetaDataBuilder {
		private final FileMetaData fMetaData = new FileMetaData();;
		
		public FileMetaDataBuilder(String path) {
			fMetaData.setPath(path);
		}
		
		public FileMetaDataBuilder fileName(String fileName) {
			fMetaData.setFileName(fileName);
			return this;
		}
		
		public FileMetaDataBuilder lastModified(long lastModified) {
			fMetaData.setLastModified(lastModified);
			return this;
		}
		
		public FileMetaDataBuilder createTime(long createTime) {
			fMetaData.setCreateTime(createTime);
			return this;
		}
		
		public FileMetaDataBuilder type(String type) {
			fMetaData.setType(type);
			return this;
		}
		
		public FileMetaDataBuilder user(User user) {
			fMetaData.setUser(user);
			return this;
		}
		
		public FileMetaData build() {
			return fMetaData;
		}
	}
	
}
