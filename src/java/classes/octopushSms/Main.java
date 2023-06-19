package classes.octopushSms;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		SmsObject sms = new SmsObject();
		int i;
            OUTER:
            do {
                System.out.println("Press 1 to get balance ");
                System.out.println("Press 2 to send SMS ");
                System.out.println("Press 0 to exit ");
                i = in.nextInt();
                switch (i) {
                    case 1:
                        System.out.println(sms.getBalance()+"\n");
                        break;
                    case 2:
//                        System.out.println(sms.sendSms()+"\n");
                        break;
                    case 0:
                        break OUTER;
                    default:
                        System.out.println("Enter only number 1/2/3");
                        break;
                }
            } while (i != 0);
		in.close();
	}
}
