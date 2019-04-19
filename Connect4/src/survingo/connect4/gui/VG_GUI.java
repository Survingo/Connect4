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

package survingo.connect4.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import survingo.connect4.VG_Main;
import survingo.connect4.lang.Lang;
import survingo.connect4.utils.VG_Button;

public class VG_GUI {
	
	public static void setDefaults () {
		if (VG_Main.prop.getProperty("darkmode", "false").equals("true")) {
			// Dark Mode
			UIManager.put("control", new Color(31, 31, 31));
			UIManager.put("nimbusBase", new Color(11, 11, 11));
			UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
			UIManager.put("nimbusFocus", new Color(94, 94, 94));
			UIManager.put("nimbusLightBackground", new Color(32, 34, 35));
			UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
			UIManager.put("nimbusSelectionBackground", new Color(80, 80, 80));
			UIManager.put("text", new Color(230, 230, 230));
			
		} else {
			// Light Mode
			UIManager.put("control", new Color(236, 236, 226));
			UIManager.put("nimbusBase", new Color(147, 139, 139));
			UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
			UIManager.put("nimbusFocus", new Color(154, 161, 168));
			UIManager.put("nimbusLightBackground", new Color(222, 222, 222));
			UIManager.put("nimbusSelectedText", new Color(60, 60, 60));
			UIManager.put("nimbusSelectionBackground", new Color(255, 255, 255));
			UIManager.put("text", new Color(40, 40, 40));
			
		}
	}
	
	static void initMenu (ActionListener listener, JMenuBar menuBar, JMenuItem saveItem, JMenuItem restartItem, JMenuItem mainMenuItem, JMenuItem exitItem, JRadioButtonMenuItem eng, JRadioButtonMenuItem deu, JMenuItem tutorialItem, JMenuItem updateItem, JMenuItem aboutItem) {
		
		// File menu
		JMenu file = new JMenu(Lang.get("MENU_FILE"));
		saveItem.addActionListener(listener);
		file.add(saveItem);
		restartItem.addActionListener(listener);
		file.add(restartItem);
		file.add(new JSeparator());
		mainMenuItem.addActionListener(listener);
		file.add(mainMenuItem);
		exitItem.addActionListener(listener);
		file.add(exitItem);
		menuBar.add(file);
		
		// Settings menu
		JMenu settings = new JMenu(Lang.get("MENU_SETTINGS"));
		// Language sub-menu
		JMenu lang = new JMenu(Lang.get("MENU_SETTINGS_LANGUAGE"));
		ButtonGroup langs = new ButtonGroup();
		
		eng.addActionListener(listener);
		langs.add(eng);
		lang.add(eng);
		
		deu.addActionListener(listener);
		langs.add(deu);
		lang.add(deu);
		
		if (VG_Main.prop.getProperty("lang", "eng").equals("eng")) {
			langs.setSelected(eng.getModel(), true);
		} else if (VG_Main.prop.getProperty("lang").equals("deu")) {
			langs.setSelected(deu.getModel(), true);
		}
		settings.add(lang);
		settings.add(new JSeparator());
		JCheckBoxMenuItem darkMode = new JCheckBoxMenuItem(Lang.get("MENU_SETTINGS_DARKMODE"));
		darkMode.addItemListener (new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					VG_Main.setProperty("darkmode", "true");
				} else {
					VG_Main.setProperty("darkmode", "false");
				}
			}
		});
		if (VG_Main.prop.getProperty("darkmode", "true").equals("true")) {
			darkMode.setSelected(true);
		} else {
			darkMode.setSelected(false);
		}
		settings.add(darkMode);
		menuBar.add(settings);
		
		// Help menu
		JMenu help = new JMenu(Lang.get("MENU_HELP"));
		tutorialItem.addActionListener(listener);
		help.add(tutorialItem);
		help.add(new JSeparator());
		JCheckBoxMenuItem checkUpdateStart = new JCheckBoxMenuItem(Lang.get("MENU_HELP_UPDATE_START"));
		checkUpdateStart.addItemListener (new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					VG_Main.setProperty("update", "true");
				} else {
					VG_Main.setProperty("update", "false");
				}
			}
		});
		if (VG_Main.prop.getProperty("update", "true").equals("true")) {
			checkUpdateStart.setSelected(true);
		} else {
			checkUpdateStart.setSelected(false);
		}
		help.add(checkUpdateStart);
		updateItem.addActionListener(listener);
		help.add(updateItem);
		help.add(new JSeparator());
		aboutItem.addActionListener(listener);
		help.add(aboutItem);
		menuBar.add(help);
	}
	
	public static void initUI (Container con, ActionListener listener, JLabel rSL, JLabel ySL, JLabel curPl, JButton restart) {
		// scoreboard text label
		JLabel sL = new JLabel(Lang.get("SB_TITLE"));
		sL.setFont(new Font("Arial", Font.PLAIN, 45));
		sL.setText( Lang.get("SB_TITLE") );
		sL.setSize( sL.getPreferredSize().width, sL.getPreferredSize().height );
		sL.setLocation(750, 25);
		
		// score text label for red player
		JLabel rSB	= new JLabel(Lang.get("SB_SCORE_RED"));
		rSB.setFont(new Font("Arial", Font.PLAIN, 16));
		rSB.setSize( rSB.getPreferredSize().width, rSB.getPreferredSize().height );
		rSB.setLocation(750, 90);
		
		// score text label for yellow player
		JLabel ySB = new JLabel(Lang.get("SB_SCORE_YELLOW"));
		ySB.setFont(new Font("Arial", Font.PLAIN, 16));
		ySB.setSize( ySB.getPreferredSize().width, ySB.getPreferredSize().height );
		ySB.setLocation(750, 140);
		
		// score label for red player
		rSL.setFont(new Font("Arial", Font.PLAIN, 16));
		rSL.setSize( rSL.getPreferredSize().width, rSL.getPreferredSize().height );
		rSL.setLocation(1000, 90);
		
		// score label for yellow player
		ySL.setFont(new Font("Arial", Font.PLAIN, 16));
		ySL.setSize ( ySL.getPreferredSize().width, ySL.getPreferredSize().height );
		ySL.setLocation(1000, 140);
		
		// "Current Turn:" label
		JLabel curT = new JLabel(Lang.get("SB_CURTURN"));
		curT.setFont(new Font("Arial", Font.PLAIN, 45));
		curT.setSize ( curT.getPreferredSize().width, curT.getPreferredSize().height );
		curT.setLocation(750, 240);
		
		// Current player label in scoreboard
		curPl.setFont(new Font("Arial", Font.PLAIN, 45));
		curPl.setForeground(new Color(209, 73, 73)); // red
		curPl.setSize ( curPl.getPreferredSize().width, curPl.getPreferredSize().height );
		curPl.setLocation(750, 290);
		
		// Restart button
		restart.setLocation(850, 400);
		restart.setSize(restart.getPreferredSize().width+10, restart.getPreferredSize().height+10);
		restart.setFocusable(false);
		restart.addActionListener(listener);
		
		con.add(sL);
		con.add(rSB);
		con.add(ySB);
		con.add(rSL);
		con.add(ySL);
		con.add(curT);
		con.add(curPl);
		con.add(restart);
	}
	
	public static void initButton (JPanel panel, ActionListener listener, VG_Button button, int y) {
		button.addActionListener(listener);
		button.setSize( button.getPreferredSize().width, button.getPreferredSize().height );
		if ( y == 0 ) { // if button is located in first row
			button.setEnabled(true);
		} else {
			button.setEnabled(false);
		}
		panel.add(button);
	}
	
	public static void setButton (VG_Button [] [] sf, VG_Button b, int turn) {
		for ( int y = 5; y > -1; y--) { // start from last row to first row (bottom to top)
			if ( sf [y] [b.getS()-1].getOwner() == 0 ) { // if button has no owner
				sf [y] [b.getS()-1].setOwner(turn);
				if (turn == 1) {
					sf [y] [b.getS()-1].setIcon( VG_Main.redIcon);
					sf [y] [b.getS()-1].setDisabledIcon( VG_Main.redIcon ); // set icon and disabled icon so icon won't be grey
				} else {
					sf [y] [b.getS()-1].setIcon( VG_Main.yellowIcon );
					sf [y] [b.getS()-1].setDisabledIcon( VG_Main.yellowIcon );
				}
				if (y == 0) {
					sf [y] [b.getS()-1].setEnabled(false);
				}
				break; // stop loop
			}
		}
		
	}
	
}