package survingo.connect4.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Lang {
	
	static String lang = "eng";
	static Map<String, String> deu = new HashMap<String, String>();
	static Map<String, String> eng = new HashMap<String, String>();
	
	public static void init () {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("/connect4.properties");
		lang = prop.getProperty("lang"); // TO-DO: generate properties file
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (lang.equals("deu")) {
			deu = German.getMap();
		} else {
			eng = English.getMap();
		}
	}
	
	public static String get (String value) { // function to get a language string by name
		if (lang.equals("deu")) {
			return deu.get(value);
		} else {
			return eng.get(value);
		}
	}
	
}