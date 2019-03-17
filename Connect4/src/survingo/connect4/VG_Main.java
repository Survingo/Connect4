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

package survingo.connect4;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import survingo.connect4.gui.VG_GUI_LocalFriend;
import survingo.connect4.lang.Lang;
import survingo.connect4.utils.Updater;

public class VG_Main extends JFrame {
	
	public static ImageIcon	redIcon, yellowIcon;
	public static final String VER = "0.2.0";
	static JFrame mainFrame, localFriendFrame;
	
	// function to scale images
	public static ImageIcon setImage ( String path, int width, int height ) {
		URL IconURL = VG_Main.class.getResource(path);
		
		if ( IconURL != null ) { // check if image exists
			ImageIcon icon = new ImageIcon( IconURL );
			icon.setImage( icon.getImage().getScaledInstance( width, height, Image.SCALE_SMOOTH ) );
			return icon;
		} else { // image does not exist -> return new dummy
			return new ImageIcon();
		}
	}
	
	public static void createProperties () {
		File f = new File(System.getProperty("user.dir") + "/connect4.properties");
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
			out.println("version=" + VER);
			out.println("");
			out.println("#This will change the language of the game");
			out.println("# Available languages: English (eng), German (deu)");
			out.println("lang=eng");
			out.println("");
			out.println("#Check for an update at program start (true/false)");
			out.println("#This could slow down the start of the program");
			out.println("update=true");
			out.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// main function
	public static void main ( String[] args ) {
		File f = new File(System.getProperty("user.dir") + "/connect4.properties");
		if (!f.exists()) { // no properties file exists -> create one
			createProperties();
		}
		
		Properties prop = new Properties();
		try {
			FileInputStream input = new FileInputStream( System.getProperty("user.dir") + "/connect4.properties");
			prop.load(input);
			input.close();
			Lang.init(prop);
			if (!prop.getProperty("version", "null").equals(VER)) {
				JOptionPane.showMessageDialog(null, Lang.get("PROPERTIES_NOT_UP_TO_DATE"));
				createProperties();
			}
			if (prop.getProperty("update", "true").equals("true")) {
				Updater.checkForUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Initiate images
		redIcon = setImage ( "utils/VG_Red.png", 90, 90 );
		yellowIcon = setImage ( "utils/VG_Yellow.png", 90, 90 );
		
		mainFrame = new VG_Main();
	}
	
	public VG_Main () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle( Lang.get("TITLE_MAIN") );
		getContentPane().setPreferredSize(new Dimension(1100, 650));
		getContentPane().setLayout(null);
		
		JLabel vg = new JLabel( Lang.get("TITLE_MAIN") );
		vg.setFont(new Font("Liberation Sans", Font.PLAIN, 80));
		vg.setSize ( vg.getPreferredSize().width, vg.getPreferredSize().height );
		vg.setLocation ( 300, 100 );
		getContentPane().add(vg);
		
		// TO-DO...
		JComboBox<String> gameOptions = new JComboBox<String>();
		gameOptions.addItem("Locale Player vs. Player");
		gameOptions.addItem("Player vs. AI - Easy");
		gameOptions.addItem("Player vs. AI - Hard");
		gameOptions.addItem("Online Player vs. Player");
		gameOptions.setSelectedIndex(0);
		getContentPane().add(gameOptions);
		
		JLabel versionLabel = new JLabel( "v" + VER );
		versionLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 25));
		versionLabel.setSize ( versionLabel.getPreferredSize().width, versionLabel.getPreferredSize().height );
		versionLabel.setLocation ( 1020, 620 );
		getContentPane().add(versionLabel);
		
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}