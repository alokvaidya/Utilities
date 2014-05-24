import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by alokvaidya on 13/02/14.
 */
public class SmsTracker {
    public SmsTracker()throws Exception{
        RandomAccessFile raf = new RandomAccessFile("/Users/alokvaidya/Documents/testfile.txt","r");
        int cnt = 0;
        String temp = null;
        while (cnt++ < 50){
            temp = raf.readLine();
            display(temp);
            if (temp.contains("gib")){
                parseline(temp);
            }
            while (raf.getFilePointer()==raf.length()){
                Thread.sleep(2 * 1000);

            }
        }
    }

    private void display(String str){
        System.out.println(str);
    }

    private void parseline(String str){

    }

    public static void main(String [] args)throws Exception{
        new SmsTracker();
    }
}
