package com.poc.model;

import java.io.Serializable;

public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	private int projectId;
	private String projectName;

	public Project() {
	}

	public Project(int projectId, String projectName) {
		this.projectId = projectId;
		this.projectName = projectName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
