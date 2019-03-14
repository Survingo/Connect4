/*
.d8888. db    db d8888b. db    db d888888b d8b   db  d888b   .d88b.  
88'  YP 88    88 88  `8D 88    88   `88'   888o  88 88' Y8b .8P  Y8. 
`8bo.   88    88 88oobY' Y8    8P    88    88V8o 88 88      88    88 
  `Y8b. 88    88 88`8b   `8b  d8'    88    88 V8o88 88  ooo 88    88 
db   8D 88b  d88 88 `88.  `8bd8'    .88.   88  V888 88. ~8~ `8b  d8' 
`8888Y' ~Y8888P' 88   YD    YP    Y888888P VP   V8P  Y888P   `Y88P'  

(c) Survingo 2019
This file is part of the Connect4 project which is released under GNU General Public License v3.0.
See https://github.com/Survingo/Connect4/blob/master/LICENSE for full license details.
*/

package survingo.connect4.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Lang {
	
	static String lang;
	static Map<String, String> deu = new HashMap<String, String>();
	static Map<String, String> eng = new HashMap<String, String>();
	
	public static void init () {
		File f = new File(System.getProperty("user.dir") + "/connect4.properties");
		if (!f.exists()) { // no properties file exists -> create one
			try {
				f.createNewFile();
				PrintWriter out = new PrintWriter(f);
				out.println("#.d8888. db    db d8888b. db    db d888888b d8b   db  d888b   .d88b.  ");
				out.println("#88'  YP 88    88 88  `8D 88    88   `88'   888o  88 88' Y8b .8P  Y8. ");
				out.println("#`8bo.   88    88 88oobY' Y8    8P    88    88V8o 88 88      88    88 ");
				out.println("#  `Y8b. 88    88 88`8b   `8b  d8'    88    88 V8o88 88  ooo 88    88 ");
				out.println("#db   8D 88b  d88 88 `88.  `8bd8'    .88.   88  V888 88. ~8~ `8b  d8' ");
				out.println("#`8888Y' ~Y8888P' 88   YD    YP    Y888888P VP   V8P  Y888P   `Y88P'  ");
				out.println("");
				out.println("#This is to check if the version of the properties file equals");
				out.println("#the version of the game");
				out.println("version=0.1.1");
				out.println("");
				out.println("#This will change the language of the game");
				out.println("# Available languages: English (eng), German (deu)");
				out.println("lang=eng");
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		Properties prop = new Properties();
		try {
			FileInputStream input = new FileInputStream( System.getProperty("user.dir") + "/connect4.properties");
			prop.load(input);
			input.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		lang = prop.getProperty("lang", "eng");
		
		if ( lang.equals("deu") ) {
			deu = German.getMap();
		} else {
			eng = English.getMap();
		}
	}
	
	public static String get (String key) { // function to get a language string by name
		if (lang.equals("deu")) {
			return deu.get(key);
		} else {
			return eng.get(key);
		}
	}
	
}