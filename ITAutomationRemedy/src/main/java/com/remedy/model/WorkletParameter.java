package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkletParameter {
	  
	  public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isSecret() {
		return secret;
	}

	public void setSecret(boolean secret) {
		this.secret = secret;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public java.lang.String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(java.lang.String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public java.lang.String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(java.lang.String displayName) {
		this.displayName = displayName;
	}

	public java.lang.String getExtension() {
		return extension;
	}

	public void setExtension(java.lang.String extension) {
		this.extension = extension;
	}

	// Field descriptor #33 Ljava/lang/String;
	  private java.lang.String name;
	  
	  // Field descriptor #33 Ljava/lang/String;
	  private java.lang.String value;
	  
	  // Field descriptor #33 Ljava/lang/String;
	  private java.lang.String type;
	  
	  // Field descriptor #37 I
	  private int order;
	  
	  // Field descriptor #39 Z
	  private boolean secret;
	  
	  // Field descriptor #39 Z
	  private boolean optional;
	  
	  // Field descriptor #33 Ljava/lang/String;
	  private java.lang.String defaultValue;
	  
	  // Field descriptor #33 Ljava/lang/String;
	  private java.lang.String displayName;
	  
	  // Field descriptor #33 Ljava/lang/String;
	  private java.lang.String extension;
}