package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginalRequest
{
private String source;

private Data data;

public String getSource ()
{
return source;
}

public void setSource (String source)
{
this.source = source;
}

public Data getData ()
{
return data;
}

public void setData (Data data)
{
this.data = data;
}

@Override
public String toString()
{
return "ClassPojo [source = "+source+", data = "+data+"]";
}
}
	

