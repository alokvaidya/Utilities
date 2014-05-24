import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by alokvaidya on 17/12/13.
 */
public class CreditUpdateAlert {
    public CreditUpdateAlert() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dems", "root", "blowfish401x");
            Statement st = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = st.executeQuery("Select SUM(NoOfParts) as Total "
                    + "from Sms where date(SmsRcvd)=DATE(DATE_SUB(CURDATE(),INTERVAL 24 HOUR))");
            rs.beforeFirst();
            rs.next();
            int smscnt = rs.getInt("Total");
            rs.close();

            rs = st.executeQuery("Select UserAccount,SUM(NoOfParts) as SMS,"
                    + "SUM(Charge) as Charge from Sms where date(SmsRcvd)=DATE(DATE_SUB(CURDATE(),INTERVAL 24 HOUR)) "
                    + "group by UserAccount order by Sms desc");
            rs.beforeFirst();
            String htmlcontent = "<html><head></head><body>";
            String precontent = "Hello,<br><br>";
            String mailcontent = "Sms Summary For Yesterday:<br><br>";
            mailcontent += "Total Sms Sent: <b>" + smscnt + "</b><br><br>";
            mailcontent += "<table><tr><td><b>Account</b></td>"
                    + "<td><b>Sms</b></td><td><b>Charge</b></td></tr>";
            while (rs.next()) {
                mailcontent += "<tr><td>" + rs.getString("UserAccount") + "</td>"
                        + "<td>" + rs.getString("Sms") + "</td>"
                        + "<td>" + rs.getString("Charge") + "</td></tr>";
            }
            rs.close();
            st.close();
            con.close();
            mailcontent += "</table></body></html>";
            new MailSender("Daily SMS Summary", htmlcontent + precontent + mailcontent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CreditUpdateAlert();
    }
}
