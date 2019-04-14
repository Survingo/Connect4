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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import survingo.connect4.gui.VG_GUI_LocalAI_Easy;
import survingo.connect4.gui.VG_GUI_LocalFriend;
import survingo.connect4.lang.Lang;
import survingo.connect4.utils.Updater;

public class VG_Main extends JFrame implements ActionListener {
	
	public static final String	VER 			= "0.3.0-beta";
	public static int			WIDTH			= 1100;
	public static int			HEIGHT			= 650;
	public static ImageIcon		redIcon, yellowIcon;
	public static JFrame		mainFrame, gameFrame;
	String[]					modes 			= {Lang.get("TITLE_LOCAL_PVP"),
												Lang.get("TITLE_ONLINE_PVP"),
												Lang.get("TITLE_AI_EASY"),
												Lang.get("TITLE_AI_HARD")};
	JComboBox<String>			gO				= new JComboBox<String>(modes);
	public static Properties	prop			= new Properties();
	
	// function to scale images
	public static ImageIcon setImage (String path, int width, int height) {
		URL IconURL = VG_Main.class.getResource(path);
		
		if ( IconURL != null ) { // check if image exists
			ImageIcon icon = new ImageIcon(IconURL);
			icon.setImage( icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH) );
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
	
	public static void loadProperties () throws IOException {
		FileInputStream in = new FileInputStream( System.getProperty("user.dir") + "/connect4.properties" );
		prop.load(in);
		in.close();
		if (!prop.getProperty("version", "null").equals(VER)) {
			JOptionPane.showMessageDialog(
					null,
					"The version of the properties file is not compatible with the program. Creating a new file now...",
					"Connect Four",
					JOptionPane.WARNING_MESSAGE);
			createProperties();
		}
	}
	
	public static void setProperty (String key, String value) {
		try { // shortcut to change values of properties file
			FileOutputStream out = new FileOutputStream( System.getProperty("user.dir") + "/connect4.properties" );
			prop.setProperty(key, value);
			prop.store(out, null); // this will cause the comments in the properties file to disappear, I don't plan to change that due to lack of ressources
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// main function
	public static void main ( String[] args ) {
		
		try {
			loadProperties();
		} catch (FileNotFoundException e) {
			createProperties();
			try {
				loadProperties();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Lang.init(); // initiate language
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					null,
					Lang.get("NIMBUS_LAF_ERROR") + e.getClass().getSimpleName(),
					Lang.get("TITLE"),
					JOptionPane.ERROR_MESSAGE);
		}
		
		// Initiate images
		redIcon = setImage("utils/VG_Red.png", 90, 90);
		yellowIcon = setImage("utils/VG_Yellow.png", 90, 90);
		
		mainFrame = new VG_Main();
		
		if (prop.getProperty("update", "true").equals("true")) {
			Updater.isUpToDate();
		}
	}
	
	public VG_Main () {
		
		try {
			setIconImage ( redIcon.getImage() ); // set icon of window in task bar
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		JLabel vg = new JLabel( Lang.get("TITLE") );
		vg.setFont(new Font("Liberation Sans", Font.PLAIN, 100));
		vg.setSize ( vg.getPreferredSize().width, vg.getPreferredSize().height );
		getContentPane().add(vg);
		vg.setLocation(WIDTH/2-vg.getSize().width/2, new Double(HEIGHT*0.2).intValue() );
		
		gO.setFont(new Font("Liberation Sans", Font.PLAIN, 30));
		gO.setSize(gO.getPreferredSize().width+10, gO.getPreferredSize().height);
		gO.setSelectedIndex(0);
		getContentPane().add(gO);
		gO.setLocation(WIDTH/2-gO.getSize().width/2, HEIGHT/2-gO.getSize().height/2);
		
		JButton play = new JButton("Start");
		play.setFont(new Font("Liberation Sans", Font.PLAIN, 30));
		play.setSize(play.getPreferredSize().width, play.getPreferredSize().height);
		play.addActionListener(this);
		getContentPane().add(play);
		play.setLocation(WIDTH/2-play.getSize().width/2, new Double(HEIGHT*0.6).intValue());
		
		JLabel versionLabel = new JLabel( "v" + VER );
		versionLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 40));
		versionLabel.setSize(versionLabel.getPreferredSize().width, versionLabel.getPreferredSize().height);
		versionLabel.setLocation( WIDTH-versionLabel.getSize().width-10, new Double(HEIGHT*0.93).intValue() );
		getContentPane().add(versionLabel);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener( new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				JFrame frame = (JFrame) e.getSource();
				int confirm = JOptionPane.showConfirmDialog(
						frame,
						Lang.get("CONFIRM_EXIT"),
						Lang.get("TITLE"),
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		setTitle( Lang.get("TITLE") );
		getContentPane().setPreferredSize( new Dimension(WIDTH, HEIGHT) );
		getContentPane().setLayout(null);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (gO.getSelectedItem().equals(modes[0])) {
			gameFrame = new VG_GUI_LocalFriend();
			mainFrame.setVisible(false);
		}
		
		if (gO.getSelectedItem().equals(modes[1])) {
			JOptionPane.showMessageDialog(
					mainFrame,
					Lang.get("FEATURE_UNAVAILABLE"),
					Lang.get("TITLE"),
					JOptionPane.ERROR_MESSAGE);
		}
		
		if (gO.getSelectedItem().equals(modes[2])) {
			gameFrame = new VG_GUI_LocalAI_Easy();
			mainFrame.setVisible(false);
			JOptionPane.showMessageDialog(
					mainFrame,
					Lang.get("FEATURE_NOT_COMPLETE"),
					Lang.get("TITLE"),
					JOptionPane.ERROR_MESSAGE);
		}
		
		if (gO.getSelectedItem().equals(modes[3])) {
			JOptionPane.showMessageDialog(
					mainFrame,
					Lang.get("FEATURE_UNAVAILABLE"),
					Lang.get("TITLE"),
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
}