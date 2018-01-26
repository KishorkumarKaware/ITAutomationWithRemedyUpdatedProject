package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fulfillment
{
	private String speech;

	private Messages[] messages;

	public String getSpeech ()
	{
		return speech;
	}

	public void setSpeech (String speech)
	{
		this.speech = speech;
	}

	public Messages[] getMessages ()
	{
		return messages;
	}

	public void setMessages (Messages[] messages)
	{
		this.messages = messages;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [speech = "+speech+", messages = "+messages+"]";
	}
}

