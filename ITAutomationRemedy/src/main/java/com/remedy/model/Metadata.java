package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata
{
private String intentId;

private String webhookUsed;

private String intentName;

private String webhookForSlotFillingUsed;

public String getIntentId ()
{
return intentId;
}

public void setIntentId (String intentId)
{
this.intentId = intentId;
}

public String getWebhookUsed ()
{
return webhookUsed;
}

public void setWebhookUsed (String webhookUsed)
{
this.webhookUsed = webhookUsed;
}

public String getIntentName ()
{
return intentName;
}

public void setIntentName (String intentName)
{
this.intentName = intentName;
}

public String getWebhookForSlotFillingUsed ()
{
return webhookForSlotFillingUsed;
}

public void setWebhookForSlotFillingUsed (String webhookForSlotFillingUsed)
{
this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
}

@Override
public String toString()
{
return "ClassPojo [intentId = "+intentId+", webhookUsed = "+webhookUsed+", intentName = "+intentName+", webhookForSlotFillingUsed = "+webhookForSlotFillingUsed+"]";
}
}


