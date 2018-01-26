/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srm_integrator;

import com.bmc.arsys.api.ARException;
import com.bmc.arsys.api.ARServerUser;
import com.bmc.arsys.api.Entry;
import com.bmc.arsys.api.Value;

/**
 *
 * @author sundeepk
 */
public class SRDModification {
    
      RemedyConnection rcon=null;
      SearchRemedy remser = new SearchRemedy();
      Entry record=null;
      String RequestID="";
      String SRDTitle="";
      String Notes="";
      String NavCatagory="";
      int impact;
      int urgency;
      String ApprovalKey;
      String SRD_Numer="";
      String submitter="";
      int ServiceRequestApproval;
      String SRDInstanceID="";
      String TitleFromSRD="";
      static ARServerUser ctx;
              
      public void GetRemedyConnection()
      {
        rcon = new RemedyConnection();
        ctx= rcon.getRemedyConnection();  
      }
       public void RetriveSRDdetails(String title)
      {
        record=remser.FetchRemedyRecord(ctx, title);
        if(record != null && !(record.isEmpty()))
         {
               for (Integer i : record.keySet()) 
               {
                    RequestID=record.getEntryId();
                    SRDTitle=record.get(301244700).toString();
                    SRD_Numer= record.get(302849400).toString();
                    ApprovalKey=record.get(303772800).toString();
                    ServiceRequestApproval=Integer.parseInt(record.get(301248900).toString());
                    NavCatagory=record.get(1000000063).toString();
                    SRD_Numer=record.get(302849400).toString();
                    SRDInstanceID=record.get(179).toString();
                    TitleFromSRD=record.get(301244700).toString();
                    
                }
                    System.out.println("SRD  Details : \n");
                    System.out.println("SRD  Number : "+SRD_Numer);
                    System.out.println("ApprovalKey : "+ApprovalKey);
                    System.out.println("SRDTitle : "+SRDTitle);
                    System.out.println("NavCatagory : "+NavCatagory);
                    System.out.println("SRD_Numer : "+SRD_Numer);
                    System.out.println("SRDInstanceID : "+SRDInstanceID);
                    System.out.println("TitleFromSRD : "+TitleFromSRD);
                    System.out.println("ServiceRequestApproval : "+ServiceRequestApproval);
                    
                    
        }
      }
    public void ModifySRDDetails(String EntryID,String Response) throws ARException
    {
        String schema = "SRM:Request";
        Entry entry = new Entry();
        entry.put(301244700, new Value(SRDTitle));
        entry.put(1000000151, new Value("JAVA API Test32"));
        entry.put(302829600, new Value(TitleFromSRD));
        entry.put(1000000063, new Value(NavCatagory));
        entry.put(1000000163, new Value(1000));
        entry.put(1000000162, new Value(1000));
        entry.put(303772800, new Value(ApprovalKey));
        entry.put(302849400, new Value(SRD_Numer));
        entry.put(5, new Value("Allen"));
        entry.put(301248900, new Value(ServiceRequestApproval));
        entry.put(301303200, new Value(SRDInstanceID));
        ctx.setEntry(schema, EntryID, entry, null, 0);
        System.out.println("Service Request Modified");
     }
     public void TrigerSRDFurther(String EntryID,String Response) throws ARException
    {
      
        String schema = "SRM:Request";
        Entry entry = new Entry();
        entry.put(7, new Value(1800));
        ctx.setEntry(schema, EntryID, entry, null, 0);
        System.out.println("Service Request Further Processing Triggered");
     }
    
    public static void main(String args[]) throws ARException
{
   
    SRDModification rmodify = new SRDModification();
    rmodify.GetRemedyConnection();
    rmodify.RetriveSRDdetails("LaptopRequest");
    rmodify.ModifySRDDetails("000000000002815", "");
    rmodify.TrigerSRDFurther("000000000002815", "");
    
}
}
