/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srm_integrator;

import com.bmc.arsys.api.ARException;
import com.bmc.arsys.api.ARServerUser;
import com.bmc.arsys.api.Constants;
import com.bmc.arsys.api.Entry;
import com.bmc.arsys.api.EntryListInfo;
import com.bmc.arsys.api.QualifierInfo;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author sundeepk
 */
public class SearchRemedy {
    
    Entry record=null;
    String schemaName = "SRD:ServiceRequestDefinition";
    String queryString="";
    List<Entry> RecordMultiple=null;
   
  public Entry FetchRemedyRecord(ARServerUser ctx,String SRDTitle) 
    {
        queryString = "'301244700'=\""+SRDTitle+"\"";
        System.out.println(queryString);
        
        try {
                        QualifierInfo qual = ctx.parseQualification(schemaName, queryString);
                        List<EntryListInfo> eListInfos = ctx.getListEntry(schemaName, qual, 0, 0, null, null, false, null);
                        List<Integer> fieldIDList = ctx.getListField(schemaName, Constants.AR_FIELD_TYPE_DATA, 0); 
                        fieldIDList.remove((Integer) 15);
                        fieldIDList.remove((Integer) 2);
                        fieldIDList.remove((Integer) 3);
                        fieldIDList.remove((Integer) 4);
                        fieldIDList.remove((Integer) 5);
                        fieldIDList.remove((Integer) 6);
                        fieldIDList.remove((Integer) 8);
                        fieldIDList.remove((Integer) 536870923);
                        
                        int[] fieldIDs = convertIntegers(fieldIDList); 
                        for (EntryListInfo eListInfo : eListInfos)
                        {
                            record = ctx.getEntry(schemaName,eListInfo.getEntryID(), fieldIDs);
                           
                        }
                   } catch (ARException e) {System.out.println(e.getMessage());}
         
        return record;
    }
    public List<Entry> FetchRecordWithQual(ARServerUser ctx,String Qual,String SchemaName) 
    {
        queryString = Qual;
        System.out.println(queryString);
        
        try {
                        QualifierInfo qual = ctx.parseQualification(SchemaName, queryString);
                        List<EntryListInfo> eListInfos = ctx.getListEntry(SchemaName, qual, 0, 0, null, null, false, null);
                        List<Integer> fieldIDList = ctx.getListField(SchemaName, Constants.AR_FIELD_TYPE_DATA, 0); 
                        fieldIDList.remove((Integer) 15);
                        fieldIDList.remove((Integer) 2);
                        fieldIDList.remove((Integer) 3);
                        fieldIDList.remove((Integer) 4);
                        fieldIDList.remove((Integer) 5);
                        fieldIDList.remove((Integer) 6);
                        fieldIDList.remove((Integer) 8);
                        fieldIDList.remove((Integer) 536870923);
                        
                        int[] fieldIDs = convertIntegers(fieldIDList); 
                        for (EntryListInfo eListInfo : eListInfos)
                        {
                            RecordMultiple = ctx.getListEntryObjects(SchemaName, qual, 0, 0, null, fieldIDs, true, null);
                           
                        }
                   } catch (ARException e) {System.out.println(e.getMessage());}
         
        return RecordMultiple;
    }
    public Entry FetchSingleRecordWithQual(ARServerUser ctx,String Qual,String SchemaName) 
    {
        queryString = Qual;
        System.out.println(queryString);
        
        try {
                        QualifierInfo qual = ctx.parseQualification(SchemaName, queryString);
                        List<EntryListInfo> eListInfos = ctx.getListEntry(SchemaName, qual, 0, 0, null, null, false, null);
                        List<Integer> fieldIDList = ctx.getListField(SchemaName, Constants.AR_FIELD_TYPE_DATA, 0); 
                        fieldIDList.remove((Integer) 15);
                        fieldIDList.remove((Integer) 2);
                        fieldIDList.remove((Integer) 3);
                        fieldIDList.remove((Integer) 4);
                        fieldIDList.remove((Integer) 5);
                        fieldIDList.remove((Integer) 6);
                        fieldIDList.remove((Integer) 8);
                        fieldIDList.remove((Integer) 536870923);
                        
                        int[] fieldIDs = convertIntegers(fieldIDList); 
                        for (EntryListInfo eListInfo : eListInfos)
                        {
                             record = ctx.getEntry(SchemaName,eListInfo.getEntryID(), fieldIDs);
                           
                        }
                   } catch (ARException e) {System.out.println(e.getMessage());}
         
        return record;
    }
    
    private static int[] convertIntegers(List<Integer> integers) {  
    if (integers != null) {  
        int[] ret = new int[integers.size()];  
        Iterator<Integer> iterator = integers.iterator();  
        for (int i = 0; i < ret.length; i++) {  
            ret[i] = iterator.next();  
        }  
        return ret;  
    }  
    return null;  
}   
 
}
