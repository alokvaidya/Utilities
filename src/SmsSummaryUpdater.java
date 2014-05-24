import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by alokvaidya on 06/05/14.
 */
public class SmsSummaryUpdater {

    private String usr;
    private String pwd;

    public SmsSummaryUpdater(){
        String ua,dt,ct,op,rt,ds;
        int sms;
        double chg;
        init();
        try{
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dems",usr,pwd);
            Statement st = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = st.executeQuery("select UserAccount,Date(SmsRcvd),Country,Operator,Route,DlrStatus," +
                    "SUM(NoOfParts),SUM(Charge) from dems.Sms where Date(SmsRcvd)>=DATE(DATE_SUB(CURDATE(), " +
                    "INTERVAL 48 HOUR)) group by UserAccount,Date(SmsRcvd),Country,OPerator,Route,DlrStatus");
            Statement st2 = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE);
            rs.beforeFirst();
            while(rs.next()){
                ua = rs.getString("UserAccount");
                dt = rs.getString("Date(SmsRcvd)");
                ct = rs.getString("Country");
                op = rs.getString("Operator");
                rt = rs.getString("Route");
                ds = rs.getString("DlrStatus");
                sms = rs.getInt("SUM(NoOfParts)");
                chg = rs.getDouble("SUM(Charge)");

                int i = st2.executeUpdate("Update SmsSummary SET Sms=" + sms + ",Charge=" + chg + " where User='" + ua + "' and" +
                        " Date='" + dt + "' and Country='" + ct + "' and Operator='" + op + "' and Route='" + rt + "' and" +
                        " DlrStatus='" + ds + "'");
                if (i==0){
                    st2.executeUpdate("Insert into SmsSummary(User,Date,Country,Operator," +
                            "Route,DlrStatus,Sms,Charge) values('"+ua+"','"+dt+"','"+ct+"','"+op+"','"+
                    rt+"','"+ds+"',"+sms+","+chg+")");
                }
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init(){
        try{
            Properties props = new Properties();
            props.load(new FileInputStream("/opt/dems/daemon-tools/utils-conf/ssu.conf"));
            usr = props.getProperty("user");
            pwd = props.getProperty("pwd");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String [] args){
        new SmsSummaryUpdater();
    }
}
