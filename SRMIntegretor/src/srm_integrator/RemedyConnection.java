package srm_integrator;
import com.bmc.arsys.api.ARException;
import com.bmc.arsys.api.ARServerUser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class RemedyConnection {
    public static ARServerUser ctx;
    Connection connection=null;
    ResultSet rs = null;
    Statement statement=null;
    String AR_HostName="";
    String AR_Port="";
    String AR_AdminUser="";
    String AR_Pwd="";
    int AR_IntPort=0;
    String sql="select * from remedycondetails";
        
    public void InitialiseConnection() throws SQLException {
        
             
               try{
                        ctx = new ARServerUser();
                        ctx.setServer("10.41.4.2");
                        ctx.setUser("appadmin");
                        ctx.setPassword("pune@123");
                        ctx.setPort(AR_IntPort);
                        ctx.verifyUser();
          
                   } catch (ARException e) {System.out.println(e.getMessage());
               
                   }     
                    System.out.println("Remedy Connection Successfull");
                  
    }
    
    public ARServerUser getRemedyConnection()
    {
     if(ctx==null)
     {
         try {
                InitialiseConnection();
                System.out.println("Remedy Connection Initialised");
            } catch (SQLException ex) {
             Logger.getLogger(RemedyConnection.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
   
     return ctx;
    
    }
    
}