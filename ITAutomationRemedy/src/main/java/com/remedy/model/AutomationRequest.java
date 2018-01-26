package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutomationRequest {
	  
	  // Field descriptor #41 Ljava/lang/String;
	  private java.lang.String orgCode;
	  
	  public java.lang.String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	public java.lang.String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(java.lang.String workflowName) {
		this.workflowName = workflowName;
	}

	public java.lang.String getSource() {
		return source;
	}

	public void setSource(java.lang.String source) {
		this.source = source;
	}

	public java.lang.String getSourceId() {
		return sourceId;
	}

	public void setSourceId(java.lang.String sourceId) {
		this.sourceId = sourceId;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getResponseMailSubject() {
		return responseMailSubject;
	}

	public void setResponseMailSubject(java.lang.String responseMailSubject) {
		this.responseMailSubject = responseMailSubject;
	}

	public java.lang.Long getMaxExecutionTimeInSeconds() {
		return maxExecutionTimeInSeconds;
	}

	public void setMaxExecutionTimeInSeconds(java.lang.Long maxExecutionTimeInSeconds) {
		this.maxExecutionTimeInSeconds = maxExecutionTimeInSeconds;
	}

	public java.lang.Long getExpectedExecutionTimeInSeconds() {
		return expectedExecutionTimeInSeconds;
	}

	public void setExpectedExecutionTimeInSeconds(java.lang.Long expectedExecutionTimeInSeconds) {
		this.expectedExecutionTimeInSeconds = expectedExecutionTimeInSeconds;
	}

	public java.util.List getParams() {
		return params;
	}

	public void setParams(java.util.List params) {
		this.params = params;
	}

	// Field descriptor #41 Ljava/lang/String;
	  private java.lang.String workflowName;
	  
	  // Field descriptor #41 Ljava/lang/String;
	  private java.lang.String source;
	  
	  // Field descriptor #41 Ljava/lang/String;
	  private java.lang.String sourceId;
	  
	  // Field descriptor #41 Ljava/lang/String;
	  private java.lang.String userId;
	  
	  // Field descriptor #41 Ljava/lang/String;
	  private java.lang.String responseMailSubject;
	  
	  // Field descriptor #48 Ljava/lang/Long;
	  private java.lang.Long maxExecutionTimeInSeconds;
	  
	  // Field descriptor #48 Ljava/lang/Long;
	  private java.lang.Long expectedExecutionTimeInSeconds;
	  
	  // Field descriptor #51 Ljava/util/List;
	  // Signature: Ljava/util/List<Lcom/automationedge/model/WorkletParameter;>;
	  private java.util.List params;
	  
	  // Method descriptor #55 ()V
	  // Stack: 1, Locals: 1
	}