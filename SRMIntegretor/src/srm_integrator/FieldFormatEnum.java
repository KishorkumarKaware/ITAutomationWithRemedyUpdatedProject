/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srm_integrator;

/**
 *
 * @author sundeep
 */
public class FieldFormatEnum {
    String Format="";
    String ReturnStatus(int number)
    {
        switch(number){  
        case 0: Format="TEXT";break;  
        case 1: Format="RADIO_BUTTONS";break;   
        case 2: Format="CHECK_BOXES";break;    
        case 3: Format="RANGE_CHOICE";break;    
        case 4: Format="STATIC_MENU";break;   
        case 5: Format="QUERY_MENU";break; 
        case 6: Format="DYNAMIC_MENU";break;
        case 7: Format="DATE_TIME";break;   
        case 8: Format="DATE_ONLY";break; 
        case 9: Format="TIME_ONLY";break; 
        default:System.out.println("Nothing found for "+number);  
    }  
        
        return Format;
    }
    
}
