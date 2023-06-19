package classes.octopushSms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class SmsObject {

	ConfigFile config = new ConfigFile();

	public SmsObject() {
		super();
	}
	public String sendSms(String _sms_text) {
		String msgStr;

                HashMap<String, String> smsData = new HashMap<>();
                smsData.put("user_login", config._user_login);
                smsData.put("api_key", config._api_key);
    //		smsData.put("sms_text", config._sms_text);
                smsData.put("sms_text", _sms_text);
		smsData.put("sms_recipients", createImplode(",", config._sms_recipients));
		smsData.put("recipients_first_names",
				createImplode(",", config._recipients_first_names));
		smsData.put("recipients_last_names",
				createImplode(",", config._recipients_last_names));
		smsData.put("sms_fields_1", createImplode(",", config._sms_fields_1));
		smsData.put("sms_fields_2", createImplode(",", config._sms_fields_2));
		smsData.put("sms_fields_3", createImplode(",", config._sms_fields_3));
		smsData.put("sms_mode", config._sms_mode.toString());
		smsData.put("sms_type", config._sms_type);
		smsData.put("sms_sender", config._sms_sender);
		smsData.put("request_mode", config._request_mode);

//                System.out.println(config._sms_mode);
//                System.out.println(config.DIFFERE);
		if (Objects.equals(config._sms_mode, config.DIFFERE)) {
			smsData.put("sms_d", String.valueOf(config._sms_d));
			smsData.put("sms_m", String.valueOf(config._sms_m));
			smsData.put("sms_h", String.valueOf(config._sms_h));
			smsData.put("sms_i", String.valueOf(config._sms_i));
			smsData.put("sms_y", String.valueOf(config._sms_y));
		}
		try {
			String res = (this.myHttpRequest(config.DOMAIN, config.PATH_SMS, config.PORT, smsData))
					.trim(); 
 
			if (res.contains("<error_code>000</error_code>")) {
				msgStr = "The sending was successful ";
			}else
				msgStr = "The sending was unsuccessful, please try again!!! ";
		} catch (Exception e) {
//			e.printStackTrace();
			msgStr = "Unable to get response from server!!! ";
		}
		return msgStr;
	}
        
	public String sendSms(String _sms_text,ArrayList<String> _sms_recipients) {
		String msgStr;

                HashMap<String, String> smsData = new HashMap<>();
                smsData.put("user_login", config._user_login);
                smsData.put("api_key", config._api_key);
    //		smsData.put("sms_text", config._sms_text);
                smsData.put("sms_text", _sms_text);
		smsData.put("sms_recipients", createImplode(",", _sms_recipients));
		smsData.put("recipients_first_names",
				createImplode(",", config._recipients_first_names));
		smsData.put("recipients_last_names",
				createImplode(",", config._recipients_last_names));
		smsData.put("sms_fields_1", createImplode(",", config._sms_fields_1));
		smsData.put("sms_fields_2", createImplode(",", config._sms_fields_2));
		smsData.put("sms_fields_3", createImplode(",", config._sms_fields_3));
		smsData.put("sms_mode", config._sms_mode.toString());
		smsData.put("sms_type", config._sms_type);
		smsData.put("sms_sender", config._sms_sender);
		smsData.put("request_mode", config._request_mode);

//                System.out.println(config._sms_mode);
//                System.out.println(config.DIFFERE);
		if (Objects.equals(config._sms_mode, config.DIFFERE)) {
			smsData.put("sms_d", String.valueOf(config._sms_d));
			smsData.put("sms_m", String.valueOf(config._sms_m));
			smsData.put("sms_h", String.valueOf(config._sms_h));
			smsData.put("sms_i", String.valueOf(config._sms_i));
			smsData.put("sms_y", String.valueOf(config._sms_y));
		}
                try {
                    String res = (this.myHttpRequest(config.DOMAIN, config.PATH_SMS, config.PORT, smsData))
                            .trim();
                    if (res.contains("<error_code>000</error_code>")) {
                        msgStr = "The sending was successful ";
                    } else {
                        msgStr = "The sending was unsuccessful, please try again!!! ";
                    }
                } catch (Exception e) {
    //			e.printStackTrace();
                    System.out.println(e.getMessage());
                    msgStr = "Unable to get response from server!!! ";
                }
		return msgStr;
	}

	public String getBalance() {
		String blStr;
		HashMap<String, String> balanceData = new HashMap<>();
		balanceData.put("user_login", config._user_login);
		balanceData.put("api_key", config._api_key);

		try {
			blStr = myHttpRequest( config.DOMAIN, config.PATH_BALANCE, config.PORT, balanceData).trim();

			blStr = "For pro sms type the balance is: "
			+ blStr.substring(blStr.indexOf("type=\"FR\"")+("type=\"FR\">").length(),
					blStr.indexOf("</balance>  <balance type"))
			+ "\nFor standard sms type the balance is: " + 
					blStr.substring(blStr.indexOf("type=\"XXX\">")+("type=\"XXX\">").length(),
					blStr.indexOf("</balance></octopush>"));
		} catch (Exception e) {
			blStr = "Unable to get response from server !!!";
		}
		return blStr;
	
	}
        
    public String getBalanceSTD() {
        String blStr;
        HashMap<String, String> balanceData = new HashMap<>();
        balanceData.put("user_login", config._user_login);
        balanceData.put("api_key", config._api_key);

        try {
            blStr = myHttpRequest(config.DOMAIN, config.PATH_BALANCE, config.PORT, balanceData).trim();

            blStr = "For standard sms type the balance is: "
                    + blStr.substring(blStr.indexOf("type=\"XXX\"") + ("type=\"XXX\">").length(),
                            blStr.indexOf("</balance>  <balance type"));
        } catch (Exception e) {
            blStr = "Unable to get response from server !!!";
        }
        return blStr;

    }
                
    public String getBalancePRO() {
        String blStr;
        HashMap<String, String> balanceData = new HashMap<>();
        balanceData.put("user_login", config._user_login);
        balanceData.put("api_key", config._api_key);

        try {
            blStr = myHttpRequest(config.DOMAIN, config.PATH_BALANCE, config.PORT, balanceData).trim();
            blStr = "For pro sms type the balance is: "
                    + blStr.substring(blStr.indexOf("type=\"FR\">") + ("type=\"FR\">").length(),
                            blStr.indexOf("</balance></octopush>"));
        } catch (Exception e) {
            blStr = "Unable to get response from server !!!";
        }
        return blStr;

    }

	public static <T> String createImplode(String glue, ArrayList<T> list) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		Iterator<T> iter = list.iterator();
		StringBuilder sb = new StringBuilder();
		sb.append(iter.next());
		while (iter.hasNext()) {
			sb.append(glue).append(iter.next());
		}
		return sb.toString();
	}

	public String myHttpRequest(String domain, String path, String port,
			HashMap<String, String> myMap) throws Exception {
		URL myUrl;
		String strRequest = "";
		if (myMap.size() < 2)
			return "No params";

		for (String hashKey : myMap.keySet())
			strRequest += "&"
					+ hashKey
					+ "="
					+ URLEncoder.encode((myMap.get(hashKey) == null ? ""
							: myMap.get(hashKey)), "UTF-8");
		strRequest = strRequest.substring(1);

		myUrl = new URL(domain + ":" + port + path + "?" + strRequest);
		HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
		con.setReadTimeout(0);
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

                StringBuffer response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
            }
		return response.toString();
	}

}