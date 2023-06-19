package classes;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class SendSMS {

    public static int solde(String apikey) {
        try {
            URL url = new URL("http://www.envoyersms.biz/api/v1/?method=credit&apikey=" + URLEncoder.encode(apikey, "ISO-8859-1"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer results = new StringBuffer();
            String solde = br.readLine();
            br.close();
            return Integer.parseInt(solde);
        } catch (Exception exception) {
            return 0;
        }
    }

    public static void send(String apikey, String number, String msg, String sender, String msg_id) {
        try {
            URL url = new URL("http://www.envoyersms.biz/api/v1/?method=send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String postBody = "apikey=" + URLEncoder.encode(apikey, "ISO-8859-1") + "&"
                    + "number=" + URLEncoder.encode(number, "ISO-8859-1") + "&"
                    + "message=" + URLEncoder.encode(msg, "ISO-8859-1") + "&"
                    + "expediteur=" + URLEncoder.encode(sender, "ISO-8859-1") + "&"
                    + "msg_id=" + URLEncoder.encode(msg_id, "ISO-8859-1");

            conn.setRequestMethod("POST");

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(postBody);
            wr.flush();
            wr.close();

            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer results = new StringBuffer();

            String oneline;
            while ((oneline = br.readLine()) != null) {
                results.append(oneline);
            }

            br.close();
            System.out.println(URLDecoder.decode(results.toString(), "ISO-8859-1"));
        } catch (Exception exception) {
            System.out.println(exception.getMessage() + exception.getCause());
        }
    }
    
    public static void main(String args[]) {
        String[] errorSoon = new String[1];
        errorSoon[0] = "?";
//        lancer(errorSoon);
        envoyer_sms();
    }

    public static void lancer(String args[]) {

        if (args.length == 1 && (args[0].equals("?") || args[0].equals("help") || args[0].equals("--help"))) {
            System.out.println("Usage: java SendSMS [apikey] [number] [message] [exp] [msg_id]");
            System.out.println("");
            System.out.println("[apikey] = ApiKey ENVOYERSMS");
            System.out.println("[number] = Le num�ro du destinataire au format international");
            System.out.println("[message] = Contenu de votre message (160 carracteres par SMS)");
            System.out.println("[exp] = Le num�ro de l'exp�diteur (optionnel)");
            System.out.println("[msg_id] = Identifiant du message (optionnel)");
        } else if (args.length == 0) {
            String apikey = "Votre ApiKey";
            //Recuperer le solde de son compte
            Integer solde = SendSMS.solde(apikey);
            System.out.println("Solde : " + solde);

            // Envoyer un SMS
            SendSMS.send(apikey, "33600000000", "Votre message", "envoyersms", "");
        } else if (args.length >= 4) {
            String msg_id = (args.length == 5) ? args[4] : "";
            String expediteur = (args.length >= 4) ? args[3] : "";
            SendSMS.send(args[0], args[1], args[2], expediteur, msg_id);
        }
    }
    
    
//    import java.net.*;
//import java.io.*;
//
//public class SendSms {

    static public void envoyer_sms() {
        try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  http://developer.bulksms.com/eapi/submission/character-encoding/
             */
            data += "username=" + URLEncoder.encode("myusername", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("xxxxxx", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode("This is a test", "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=44123123123";

            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("«EAPI URL»/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            BufferedReader rd;
            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
                wr.write(data);
                wr.flush();
                // Get the response
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    // Print the response output...
                    System.out.println(line);
                }
            }
            rd.close();
        } catch (Exception e) {
        }
//    }
}
}
