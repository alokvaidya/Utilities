import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by alokvaidya on 13/02/14.
 */
public class LogProcSim {
    private String [] logbank = {"2014-02-15 13:23:01 Receive DLR [SMSC:R27] [SVC:morgreg] [ACT:56791] [BINF:] [ID:9e3a9aaf-a34c-484b-9463-b742d5515171] [FID:120245073730] [from:SMSZing] [to:971551065128] [flags:-1:-1:-1:-1:1] [msg:111:id:120245073730 sub:001 dlvrd:001 submit date:1402150852 done date:1402150852 stat:DELIVRD err:000 text: 24F430] [udh:0:]\n",
            "2014-02-15 13:23:02 Receive DLR [SMSC:R27] [SVC:morgreg] [ACT:56791] [BINF:] [ID:c75244a2-3bc3-4b59-8044-2e80ec7ea1f8] [FID:121245073728] [from:SMSZing] [to:971561066126] [flags:-1:-1:-1:-1:1] [msg:111:id:121245073728 sub:001 dlvrd:001 submit date:1402150852 done date:1402150852 stat:DELIVRD err:000 text: 24F410] [udh:0:]\n",
            "2014-02-15 13:23:18 Receive DLR [SMSC:R27] [SVC:morgreg] [ACT:56791] [BINF:] [ID:ffa87dc2-b909-4115-9d98-a0b2d97da724] [FID:121245073003] [from:SMSZing] [to:971562173534] [flags:-1:-1:-1:-1:2] [msg:111:id:121245073003 sub:001 dlvrd:001 submit date:1402150853 done date:1402150853 stat:UNDELIV err:000 text: 24F410] [udh:0:]\n",
            "2014-02-15 13:23:33 Receive DLR [SMSC:R27] [SVC:morgreg] [ACT:56791] [BINF:] [ID:c745c52e-ecc3-47a9-a523-9ebeae907a33] [FID:121245073315] [from:SMSZing] [to:971502638926] [flags:-1:-1:-1:-1:2] [msg:111:id:121245073315 sub:001 dlvrd:001 submit date:1402150853 done date:1402150853 stat:UNDELIV err:000 text: 24F410] [udh:0:]\n",
            "2014-02-15 13:23:38 Sent SMS [SMSC:R02] [SVC:hamrani] [ACT:] [BINF:] [ID:1b002787-002c-414a-a057-e91ac42f0a9b] [FID:481375012] [from:POSService] [to:966542221909] [flags:-1:0:-1:0:-1] [msg:105:OPR TRANSFER/PPL120120/10/BSF/72030133/BALSEM HANIN PHARMACY/POS NOR INST IWL220-GPRS/14-02-2014 15:04:52] [udh:0:]\n",
            "2014-02-15 13:23:38 Sent SMS [SMSC:R02] [SVC:hamrani] [ACT:] [BINF:] [ID:9f617a0c-a5cd-48e6-9c0d-9ddebaa11637] [FID:481375013] [from:POSService] [to:966553053305] [flags:-1:0:-1:0:-1] [msg:105:OPR TRANSFER/PPL120120/10/BSF/72030133/BALSEM HANIN PHARMACY/POS NOR INST IWL220-GPRS/14-02-2014 15:04:52] [udh:0:]\n",
            "2014-02-15 13:23:39 Sent SMS [SMSC:R02] [SVC:hamrani] [ACT:] [BINF:] [ID:7506fd42-377f-4091-9d61-846e0b1ac573] [FID:481375014] [from:POSService] [to:966533591276] [flags:-1:0:-1:0:-1] [msg:86:ASSIGNMENT/SPM032704/10/ALR/SN-1C1/AL ROWYDAN GA/SECURITY PM S0013/01-01-2014 07:00:00] [udh:0:]\n",
            "2014-02-15 13:23:39 Sent SMS [SMSC:R02] [SVC:hamrani] [ACT:] [BINF:] [ID:e5a23ab0-e3db-4c76-9cc4-a78b7869ce7b] [FID:481375015] [from:POSService] [to:966543665046] [flags:-1:0:-1:0:-1] [msg:90:ASSIGNMENT/SPM032757/10/ALR/SD-1HC/KOZAM GAS STATION/SECURITY PM S0013/18-02-2014 07:00:00] [udh:0:]\n",
            "2014-02-15 13:23:39 Sent SMS [SMSC:R02] [SVC:hamrani] [ACT:] [BINF:] [ID:b70423d2-d9fb-4256-b83e-d5c163f9925d] [FID:481375016] [from:POSService] [to:966533591276] [flags:-1:0:-1:0:-1] [msg:90:ASSIGNMENT/SPM032977/10/ALR/SD-1UZ/ZAYTI GAS STATION/SECURITY PM S0013/01-04-2014 07:00:00] [udh:0:]\n",
            "2014-02-15 13:24:17 Sent SMS [SMSC:R16] [SVC:voxcine] [ACT:] [BINF:] [ID:a87319d8-07c7-4698-93c2-2506f30a8d22] [FID:00db4021-a93d-4146-8297-78f5a00c0b6f] [from:VOX Cinemas] [to:971505476462] [flags:-1:0:-1:0:-1] [msg:109:Dear Customer, .Your VOX Booking ID is WW4LWWK for Movie: The Nut Job; Show Time: 2014-02-15 14:15 .Thank You] [udh:0:]\n",
            "2014-02-15 13:24:42 Receive DLR [SMSC:R27] [SVC:morgreg] [ACT:56791] [BINF:] [ID:3d357ede-b260-4224-a3db-e39ec119c47e] [FID:121245073420] [from:SMSZing] [to:971505917055] [flags:-1:-1:-1:-1:2] [msg:111:id:121245073420 sub:001 dlvrd:001 submit date:1402150854 done date:1402150854 stat:UNDELIV err:000 text: 24F410] [udh:0:]"};

    public LogProcSim()throws Exception{
        PrintWriter pw = new PrintWriter("/Users/alokvaidya/Documents/testfile.txt");
        int cnt = 0;
        Random rand = new Random();
        while (cnt++<500){
            pw.println(logbank[rand.nextInt(11)]);
            pw.flush();
            Thread.sleep(2*1000);
        }
        pw.close();
    }

    public static void main(String [] args)throws Exception{
        new LogProcSim();
    }
}
