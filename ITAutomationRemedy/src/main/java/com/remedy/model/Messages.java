package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Messages
{
private String id;

private String speech;

private int type;

public String getId ()
{
return id;
}

public void setId (String id)
{
this.id = id;
}

public String getSpeech ()
{
return speech;
}

public void setSpeech (String speech)
{
this.speech = speech;
}

public int getType ()
{
return type;
}

public void setType (int type)
{
this.type = type;
}

@Override
public String toString()
{
return "ClassPojo [id = "+id+", speech = "+speech+", type = "+type+"]";
}
}

