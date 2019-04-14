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

package survingo.connect4.utils;

import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import survingo.connect4.VG_Main;
import survingo.connect4.lang.Lang;

public class Updater {
	
	public static boolean isUpToDate () {
		String version = null;
		try { // get newest version code of game
			URL url = new URL("https://raw.githubusercontent.com/Survingo/Connect4/master/version");
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader( connection.getInputStream() ));
			Scanner scanner = new Scanner(in);
			version = scanner.next();
			scanner.close();
		} catch ( Exception e) {
			return true;
		}
		
		if (!version.equals(VG_Main.VER)) {
			JLabel label = new JLabel(); // to copy the style of it
			Font font = label.getFont();
			StringBuffer style = new StringBuffer("font-family:" + font.getFamily() + ";");
			style.append("font-weight:normal;");
			style.append("font-size:" + font.getSize() + "pt;");
			
			JEditorPane editorpane = new JEditorPane("text/html", "<html><body style=\"" + style + "\">" + Lang.get("UPDATE_AVAILABLE") + "</body></html>");
			
			editorpane.addHyperlinkListener(new HyperlinkListener() {
				@Override
				public void hyperlinkUpdate(HyperlinkEvent e) {
					if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
						try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch ( Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			editorpane.setEditable(false);
			editorpane.setBackground(label.getBackground());
			
			JOptionPane.showMessageDialog(
					null,
					editorpane,
					Lang.get("TITLE"),
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			return true;
		}
		
	}
	
}