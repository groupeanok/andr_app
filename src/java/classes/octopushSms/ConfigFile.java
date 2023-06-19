package classes.octopushSms;

import java.util.ArrayList;
import java.util.Arrays;
 
public class ConfigFile {
	
public String DOMAIN = "http://www.octopush-dm.com";
public String PORT = "80";
public String PATH_SMS = "/api/sms";
public String PATH_BALANCE = "/api/balance";
public final String QUALITE_STANDARD = "XXX";
public final String QUALITE_PRO = "FR";
public final Integer INSTANTANE = 1;
public final Integer DIFFERE = 2;
public final String SIMULATION = "simu";
public final String REEL = "real";

public String _user_login = "konombo@hotmail.com";
public String _api_key = "djlS4l8SiR0gGtbr4ZdOQiaqIcUDfdC3";
//public String _sms_text = "Ceci est un message test de AGRIC_TRADE, via Octopush STOP";

//Aimé,hamidou, Daouda, IDI, konombo, KAM, konombo bis, Tordina, Tankari
//public ArrayList<String> _sms_recipients = new ArrayList<String>(Arrays.asList("22676634710","22678024424","22666894124","22676160581","22678803880","22678844487","22676658084","22664307002","22664169377","22679712222","22664482222","22664169371"));
public ArrayList<String> _sms_recipients= new ArrayList<>(Arrays.asList("22676658084","22678803880"));


//_sms_recipients.a


//public ArrayList<String> _sms_recipients = new ArrayList<String>(Arrays.asList("22678844484","22676608669","22540032560","22678634264","22670132070"));
public ArrayList<String> _recipients_first_names = new ArrayList<>(Arrays.asList("fn1", "fn2", "fn3"));
public ArrayList<String> _recipients_last_names = new ArrayList<>(Arrays.asList("ln1", "ln2", "ln3"));
public ArrayList<String> _sms_fields_1 = new ArrayList<>(Arrays.asList("1_field1", "1_field2", "1_field3"));
public ArrayList<String> _sms_fields_2 = new ArrayList<>(Arrays.asList("2_field1", "2_field2", "2_field3"));
public ArrayList<String> _sms_fields_3 = new ArrayList<>(Arrays.asList("3_field1", "3_field2", "3_field3"));


public String _request_mode = REEL;	// REEL or change to SIMULATION to test your code

public String _sms_type = QUALITE_PRO;	// QUALITE_PRO or change to QUALITE_STANDARD 

public Integer _sms_mode = INSTANTANE;	// INSTANTANE or change to DIFFERE for delay mode sending

// in "DIFFERE" change to correct value DAY_OF_MONTH, MONTH, HOUR_OF_DAY etc'
public int _sms_d = 16;	//	DAY_OF_MONTH
public int _sms_m = 12;	//	MONTH
public int _sms_h = 19;	//	HOUR_OF_DAY;
public int _sms_i = 54;	//	MINUTE;
public int _sms_y = 2017;	//	YEAR;

public String _sms_sender = "AGRIC-TRADE";

public String _sms_ticket = "***********";

}


