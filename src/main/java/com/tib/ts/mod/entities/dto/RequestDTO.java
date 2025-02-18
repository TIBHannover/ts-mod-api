package com.tib.ts.mod.entities.dto;

import com.tib.ts.mod.entities.enums.ActionType;
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
	private ActionType operationType;

	private RequestDTO(Builder request) {
		this.artefactId = request.artefactId;
		this.distributionId = request.distributionId;
		this.resourceId = request.resourceId;
		this.query = request.query;
		this.format = request.format;
		this.page = request.page;
		this.pageSize = request.pageSize;
		this.display = request.display;
		this.operationType = request.operationType;
	}

	public String getArtefactId() {
		return artefactId;
	}

	public String getDistributionId() {
		return distributionId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public String getQuery() {
		return query;
	}

	public FormatOption getFormat() {
		return format;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public String getDisplay() {
		return display;
	}

	public ActionType getOperationType() {
		return operationType;
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
		private ActionType operationType;

		public Builder(ActionType ontologies) {
			this.operationType = ontologies;
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
