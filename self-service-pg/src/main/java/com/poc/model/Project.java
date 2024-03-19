package com.poc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
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
