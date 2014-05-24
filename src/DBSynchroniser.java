import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by alokvaidya on 14/05/14.
 */
public class DBSynchroniser {

    private String usr;
    private String pwd;
    private String tsf;
    private Logger logger;
    private String lc;
    private String lhost;
    private String fhost;

    public DBSynchroniser(){
        init();
        try{
            Connection con1 = DriverManager.getConnection(
                    "jdbc:mysql://"+lhost+":3306/dems", usr, pwd);
            Connection con2 = DriverManager.getConnection(
                    "jdbc:mysql://" + fhost + ":3306/dems", usr, pwd);
            Statement st1 = con1.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE);
            Statement st2 = con2.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = st1.executeQuery("Select * from Sms where SmsRcvd>'"+getLastAccessTime()+"'");
            rs.beforeFirst();
            while(rs.next(    
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void init(){
        try{
            Properties props = new Properties();
            props.load(new FileInputStream("/opt/dems/daemon-tools/utils-conf/dbs.conf"));
            usr = props.getProperty("user");
            pwd = props.getProperty("pwd");
            tsf = props.getProperty("tsfile");
            lhost = props.getProperty("local-host");
            fhost = props.getProperty("foreign-host");
            lc = props.getProperty("logconfig");
        }catch(Exception e){
            e.printStackTrace();
        }

        PropertyConfigurator.configure(lc);
        logger.debug("Initializing...");
    }

    public String getLastAccessTime(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tsf)));
            String ts = br.readLine();
            return ts;
        }catch(Exception e){
            logger.error("TS FileNotFound",e);
            return "ERR";
        }
    }
}
