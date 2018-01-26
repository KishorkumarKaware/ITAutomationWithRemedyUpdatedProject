package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
	private String text;

    private String event_ts;

    private String ts;

    private String type;

    private String user;

    private String channel;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getEvent_ts ()
    {
        return event_ts;
    }

    public void setEvent_ts (String event_ts)
    {
        this.event_ts = event_ts;
    }

    public String getTs ()
    {
        return ts;
    }

    public void setTs (String ts)
    {
        this.ts = ts;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getUser ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    public String getChannel ()
    {
        return channel;
    }

    public void setChannel (String channel)
    {
        this.channel = channel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [text = "+text+", event_ts = "+event_ts+", ts = "+ts+", type = "+type+", user = "+user+", channel = "+channel+"]";
    }
}
