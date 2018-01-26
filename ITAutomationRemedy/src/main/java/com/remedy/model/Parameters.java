package com.remedy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Parameters
{
    private String lastName;

    private String groupName;

    private String software;

    private String incidentNumber;

    private String description;

    private String userName;

    private String firstName;

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getGroupName ()
    {
        return groupName;
    }

    public void setGroupName (String groupName)
    {
        this.groupName = groupName;
    }

    public String getSoftware ()
    {
        return software;
    }

    public void setSoftware (String software)
    {
        this.software = software;
    }

    public String getIncidentNumber ()
    {
        return incidentNumber;
    }

    public void setIncidentNumber (String incidentNumber)
    {
        this.incidentNumber = incidentNumber;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getUserName ()
    {
        return userName;
    }

    public void setUserName (String userName)
    {
        this.userName = userName;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [lastName = "+lastName+", groupName = "+groupName+", software = "+software+", incidentNumber = "+incidentNumber+", description = "+description+", userName = "+userName+", firstName = "+firstName+"]";
    }
}
			
			