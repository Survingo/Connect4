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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import survingo.connect4.VG_EventHandler;
import survingo.connect4.VG_Main;
import survingo.connect4.lang.Lang;
import survingo.connect4.utils.Updater;
import survingo.connect4.utils.VG_Button;

public class VG_GUI_LocalFriend extends JFrame implements ActionListener {
	
	int						currentTurn			= 1, // 1 - red, 2 - yellow
							redScore 			= 0, // score counter
							yellowScore			= 0;
	JButton					restartButton		= new JButton(Lang.get("RESTART_BUTTON"));
	JLabel					curTurn				= new JLabel(Lang.get("SB_CURTURN")),
							currentPlayer		= new JLabel(Lang.get("SB_CURTURN_P1")),
							redScoreLabel 		= new JLabel("0"),
							yellowScoreLabel	= new JLabel("0"),
							scoreboardLabel		= new JLabel(),
							redScoreboard		= new JLabel(),
							yellowScoreboard	= new JLabel();
	VG_Button[][]			sf					= new VG_Button [6] [7]; // create game field using multidimensional array = 6 rows (y-axis) / 7 columns (x-axis)
	JRadioButtonMenuItem	eng					= new JRadioButtonMenuItem("English"),
							deu					= new JRadioButtonMenuItem("Deutsch");
	JMenuItem				saveItem			= new JMenuItem(Lang.get("MENU_FILE_SAVE")),
							restartItem			= new JMenuItem(Lang.get("RESTART_BUTTON")),
							mainMenuItem		= new JMenuItem(Lang.get("MENU_FILE_MENU")),
							exitItem			= new JMenuItem(Lang.get("MENU_FILE_EXIT")),
							tutorialItem		= new JMenuItem(Lang.get("MENU_HELP_TUTORIAL")),
							updateItem			= new JMenuItem(Lang.get("MENU_HELP_UPDATE")),
							aboutItem			= new JMenuItem(Lang.get("MENU_HELP_ABOUT"));
	boolean					running				= true;
	
	public VG_GUI_LocalFriend () {
		// initiate menu
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu(Lang.get("MENU_FILE"));
		saveItem.addActionListener(this);
		file.add(saveItem);
		restartItem.addActionListener(this);
		file.add(restartItem);
		file.add(new JSeparator());
		mainMenuItem.addActionListener(this);
		file.add(mainMenuItem);
		exitItem.addActionListener(this);
		file.add(exitItem);
		menuBar.add(file);
		
		JMenu lang = new JMenu(Lang.get("MENU_LANGUAGE"));
		ButtonGroup langs = new ButtonGroup();
		
		eng.addActionListener(this);
		langs.add(eng);
		lang.add(eng);
		
		deu.addActionListener(this);
		langs.add(deu);
		lang.add(deu);
		
		if (VG_Main.prop.getProperty("lang", "eng").equals("eng")) {
			langs.setSelected(eng.getModel(), true);
		} else if (VG_Main.prop.getProperty("lang").equals("deu")) {
			langs.setSelected(deu.getModel(), true);
		}
		menuBar.add(lang);
		
		JMenu help = new JMenu(Lang.get("MENU_HELP"));
		tutorialItem.addActionListener(this);
		help.add(tutorialItem);
		updateItem.addActionListener(this);
		help.add(updateItem);
		help.add(new JSeparator());
		aboutItem.addActionListener(this);
		help.add(aboutItem);
		menuBar.add(help);
		setJMenuBar(menuBar);
		
		
		VG_GUI.initUI(scoreboardLabel, redScoreboard, yellowScoreboard, redScoreLabel, yellowScoreLabel, curTurn, currentPlayer, restartButton, this);
		
		getContentPane().add(scoreboardLabel);
		getContentPane().add(redScoreboard);
		getContentPane().add(yellowScoreboard);
		getContentPane().add(redScoreLabel);
		getContentPane().add(yellowScoreLabel);
		getContentPane().add(curTurn);
		getContentPane().add(currentPlayer);
		getContentPane().add(restartButton);
		
		
		JPanel gamefield = new JPanel(); // seperate JPanel for game field
		gamefield.setSize(700, 600);
		gamefield.setLayout(new GridLayout(6, 7)); // layout manager sets size and position of buttons automatically
		gamefield.setLocation(25, 25); // add small edge
		getContentPane().add(gamefield);
		
		int z; //row
		int s; //column
		for (z = 0; z < sf.length; z++) { // loop for rows
			for (s = 0; s < 7; s++) { // loop for columns
				sf[z][s] = new VG_Button(null, z+1, s+1); // init all 42 game field buttons
				VG_GUI.initButton(gamefield, this, sf[z][s], z);
			}
		}
		
		try {
			setIconImage ( VG_Main.redIcon.getImage() ); // set icon of window in task bar
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener( new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				JFrame frame = (JFrame) e.getSource();
				int confirm = JOptionPane.showConfirmDialog(
						frame,
						Lang.get("CONFIRM_LEAVE"),
						Lang.get("TITLE"),
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					VG_Main.mainFrame.setLocationRelativeTo(frame);
					VG_Main.mainFrame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		});
		setTitle( Lang.get("TITLE") + " - " + Lang.get("TITLE_LOCAL_PVP") );
		getContentPane().setPreferredSize(new Dimension(VG_Main.WIDTH, VG_Main.HEIGHT));
		getContentPane().setLayout(null);
		setResizable(false);
		pack();
		setLocationRelativeTo(VG_Main.mainFrame);
		setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e) {
		if (e.getSource().getClass().getSimpleName().equals("VG_Button")) { // game field button
			VG_GUI.setButton(sf, (VG_Button) e.getSource(), currentTurn); // set button using that function (drop from top to bottom)
			
			if ( VG_EventHandler.won(sf, currentTurn) ) { // check if someone has won
				running = false;
				for ( int i = 0; i < 7; i++ ) { // Deactivate first row of buttons
					sf[0][i].setEnabled(false);
				}
				
				if ( currentTurn == 1 ) { // update scoreboard
					redScore++;
					redScoreLabel.setText(""+redScore);
					redScoreLabel.setSize(redScoreLabel.getPreferredSize().width, redScoreLabel.getPreferredSize().height); // optimize size to show complete text
				} else {
					yellowScore++;
					yellowScoreLabel.setText(""+yellowScore);
					yellowScoreLabel.setSize(yellowScoreLabel.getPreferredSize().width, yellowScoreLabel.getPreferredSize().height);
				}
			}
			
			if ( currentTurn == 1 ) { // change current turn, if red
				currentTurn = 2;
				currentPlayer.setText( Lang.get("SB_CURTURN_P2") ); // update JLabel text
				currentPlayer.setForeground( new Color(229, 204, 41) );
			} else { // if yellow
				currentTurn = 1;
				currentPlayer.setText( Lang.get("SB_CURTURN_P1") );
				currentPlayer.setForeground( new Color(209, 73, 73) );
			}
			
		} else if (e.getSource() == restartButton) { // restart button
			if (running) {
				int confirm = JOptionPane.showConfirmDialog(
						this,
						Lang.get("CONFIRM_RESTART"),
						Lang.get("TITLE"),
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					VG_EventHandler.restart(sf);
				}
			} else {
				VG_EventHandler.restart(sf);
				running = true;
			}
			
		} else if (e.getSource() == eng) { // English menu item
			VG_Main.setProperty("lang", "eng");
			JOptionPane.showMessageDialog(
					this,
					Lang.get("MENU_LANG_CHANGED"),
					Lang.get("TITLE"),
					JOptionPane.WARNING_MESSAGE);
			
		} else if (e.getSource() == deu) { // German menu item
			VG_Main.setProperty("lang", "deu");
			JOptionPane.showMessageDialog(
					this,
					Lang.get("MENU_LANG_CHANGED"),
					Lang.get("TITLE"),
					JOptionPane.WARNING_MESSAGE);
			
		} else if (e.getSource() == saveItem) { // File menu - save item
			JOptionPane.showMessageDialog(
					this,
					Lang.get("FEATURE_UNAVAILABLE"),
					Lang.get("TITLE"),
					JOptionPane.ERROR_MESSAGE);
			
		} else if (e.getSource() == restartItem) { // File menu - restart item
			if (running) {
				int confirm = JOptionPane.showConfirmDialog(
						this,
						Lang.get("CONFIRM_RESTART"),
						Lang.get("TITLE"),
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					VG_EventHandler.restart(sf);
				}
			} else {
				VG_EventHandler.restart(sf);
				running = true;
			}
			
		} else if (e.getSource() == mainMenuItem) { // File menu - return to menu item
			int confirm = JOptionPane.showConfirmDialog(
					this,
					Lang.get("CONFIRM_LEAVE"),
					Lang.get("TITLE"),
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				VG_Main.mainFrame.setLocationRelativeTo(this);
				VG_Main.mainFrame.setVisible(true);
				this.dispose();
			}
			
		} else if (e.getSource() == exitItem) { // File menu - exit item
			int confirm = JOptionPane.showConfirmDialog(
					this,
					Lang.get("CONFIRM_EXIT"),
					Lang.get("TITLE"),
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			
		} else if (e.getSource() == tutorialItem) {
			JOptionPane.showMessageDialog(
					this,
					Lang.get("FEATURE_UNAVAILABLE"),
					Lang.get("TITLE"),
					JOptionPane.ERROR_MESSAGE);
			
		} else if (e.getSource() == updateItem) {
			Updater.checkForUpdate();
			
		} else if (e.getSource() == aboutItem) {
			JOptionPane.showMessageDialog(
					this,
					Lang.get("MENU_HELP_ABOUT_TEXT"),
					Lang.get("TITLE"),
					JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
}