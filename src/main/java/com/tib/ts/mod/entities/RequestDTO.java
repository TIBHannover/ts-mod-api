package com.tib.ts.mod.entities;

import com.tib.ts.mod.entities.enums.FormatOption;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

public class RequestDTO {
	
	private String artefactId;
	private String distributionId;
	private String resourceId;
	private String query;
	private FormatOption format;
	private Integer page;
	private Integer pageSize;
	private String display;
	
	private RequestDTO(Builder request) {
		this.artefactId = request.artefactId;
		this.distributionId = request.distributionId;
		this.resourceId = request.resourceId;
		this.query = request.query;
		this.format = request.format;
		this.page = request.page;
		this.pageSize = request.pageSize;
		this.display = request.display;
	}
	
	public static class Builder {
		private String artefactId;
		private String distributionId;
		private String resourceId;
		private String query;
		private FormatOption format;
		private Integer page;
		private Integer pageSize;
		private String display;
		
		
		public Builder() {
			
		}
		
		public Builder setArtefactId(String artefactId) {
			this.artefactId = artefactId;
			return this;
		}
		public Builder setDistributionId(String distributionId) {
			this.distributionId = distributionId;
			return this;
		}
		public Builder setResourceId(String resourceId) {
			this.resourceId = resourceId;
			return this;
		}
		public Builder setQuery(String query) {
			this.query = query;
			return this;
		}
		public Builder setFormat(FormatOption format) {
			this.format = format;
			return this;
		}
		public Builder setPage(Integer page) {
			this.page = page;
			return this;
		}
		public Builder setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
			return this;
		}
		
		public Builder setDisplay(String display) {
			this.display = display;
			return this;
		}
		
		public RequestDTO build() {
			return new RequestDTO(this);
		}
		
	}
	

}
