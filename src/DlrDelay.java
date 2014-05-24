import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: alokvaidya
 * Date: 12/12/13
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class DlrDelay {
    public DlrDelay(String[] files) throws Exception {
        for (String s : files) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(s))));

        }

    }

    public static void main(String[] args) throws Exception {
        new DlrDelay(args);
    }
}
