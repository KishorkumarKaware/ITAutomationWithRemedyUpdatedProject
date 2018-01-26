package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result
{
private String actionIncomplete;

private String speech;

private String source;

private Fulfillment fulfillment;

private String score;

private String action;

private String resolvedQuery;

private Contexts[] contexts;

private Parameters parameters;

private Metadata metadata;

public String getActionIncomplete ()
{
return actionIncomplete;
}

public void setActionIncomplete (String actionIncomplete)
{
this.actionIncomplete = actionIncomplete;
}

public String getSpeech ()
{
return speech;
}

public void setSpeech (String speech)
{
this.speech = speech;
}

public String getSource ()
{
return source;
}

public void setSource (String source)
{
this.source = source;
}

public Fulfillment getFulfillment ()
{
return fulfillment;
}

public void setFulfillment (Fulfillment fulfillment)
{
this.fulfillment = fulfillment;
}

public String getScore ()
{
return score;
}

public void setScore (String score)
{
this.score = score;
}

public String getAction ()
{
return action;
}

public void setAction (String action)
{
this.action = action;
}

public String getResolvedQuery ()
{
return resolvedQuery;
}

public void setResolvedQuery (String resolvedQuery)
{
this.resolvedQuery = resolvedQuery;
}

public Contexts[] getContexts ()
{
return contexts;
}

public void setContexts (Contexts[] contexts)
{
this.contexts = contexts;
}

public Parameters getParameters ()
{
return parameters;
}

public void setParameters (Parameters parameters)
{
this.parameters = parameters;
}

public Metadata getMetadata ()
{
return metadata;
}

public void setMetadata (Metadata metadata)
{
this.metadata = metadata;
}

@Override
public String toString()
{
return "ClassPojo [actionIncomplete = "+actionIncomplete+", speech = "+speech+", source = "+source+", fulfillment = "+fulfillment+", score = "+score+", action = "+action+", resolvedQuery = "+resolvedQuery+", contexts = "+contexts+", parameters = "+parameters+", metadata = "+metadata+"]";
}
}



