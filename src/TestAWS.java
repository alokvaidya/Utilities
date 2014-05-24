import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by alokvaidya on 27/02/14.
 */
public class TestAWS {
    public static void main(String [] args)throws Exception{
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://demsinstance.cebxtmv5wm2t.us-west-2.rds.amazonaws.com:3306/dems", "alokv", "fuhrer1945");
        Statement st = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.TYPE_SCROLL_INSENSITIVE);
        ResultSet rs = st.executeQuery("Select * from test");
        rs.beforeFirst();
        while(rs.next()){
            System.out.println("Id:"+rs.getInt("id")+", Name:"+rs.getString("name"));
        }
        rs.close();
        st.close();
        con.close();
    }
}
