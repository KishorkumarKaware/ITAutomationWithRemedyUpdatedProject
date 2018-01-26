package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)  
public class Data
{
private String token;

private Event event;

private String[] authed_users;

private String event_time;

private String event_id;

private String type;

private String team_id;

private String api_app_id;

public String getToken ()
{
return token;
}

public void setToken (String token)
{
this.token = token;
}

public Event getEvent ()
{
return event;
}

public void setEvent (Event event)
{
this.event = event;
}

public String[] getAuthed_users ()
{
return authed_users;
}

public void setAuthed_users (String[] authed_users)
{
this.authed_users = authed_users;
}

public String getEvent_time ()
{
return event_time;
}

public void setEvent_time (String event_time)
{
this.event_time = event_time;
}

public String getEvent_id ()
{
return event_id;
}

public void setEvent_id (String event_id)
{
this.event_id = event_id;
}

public String getType ()
{
return type;
}

public void setType (String type)
{
this.type = type;
}

public String getTeam_id ()
{
return team_id;
}

public void setTeam_id (String team_id)
{
this.team_id = team_id;
}

public String getApi_app_id ()
{
return api_app_id;
}

public void setApi_app_id (String api_app_id)
{
this.api_app_id = api_app_id;
}

@Override
public String toString()
{
return "ClassPojo [token = "+token+", event = "+event+", authed_users = "+authed_users+", event_time = "+event_time+", event_id = "+event_id+", type = "+type+", team_id = "+team_id+", api_app_id = "+api_app_id+"]";
}
}


