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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import survingo.connect4.VG_EventHandler;
import survingo.connect4.VG_Main;
import survingo.connect4.lang.Lang;
import survingo.connect4.utils.VG_Button;

public class VG_GUI_LocalAI_Easy extends JFrame implements ActionListener {
	
	public static int		currentTurn			= 1; // 1 - red, 2 - yellow
	public static int		redScore 			= 0, // score counter
							yellowScore			= 0;
	JButton					restartButton		= new JButton( Lang.get("RESTART_BUTTON") );
	public static JLabel	currentPlayer		= new JLabel( Lang.get("SB_CURTURN_P1") );
	public static JLabel	redScoreLabel 		= new JLabel ( "0" ),
							yellowScoreLabel	= new JLabel ( "0" );
	JLabel					scoreboardLabel		= new JLabel();
	JLabel					redScoreboard		= new JLabel();
	JLabel					yellowScoreboard	= new JLabel();
	VG_Button[][]			sf					= new VG_Button [6] [7]; // create game field using multidimensional array = 6 rows (y-axis) / 7 columns (x-axis)
	
	public VG_GUI_LocalAI_Easy () {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle( Lang.get("TITLE") + " - " + Lang.get("TITLE_AI_EASY") );
		getContentPane().setPreferredSize(new Dimension(VG_Main.WIDTH, VG_Main.HEIGHT));
		getContentPane().setLayout(null);
		setResizable(false);
		pack();
		setLocationRelativeTo(VG_Main.mainFrame);
		
		VG_GUI.initUI(scoreboardLabel, redScoreboard, yellowScoreboard, redScoreLabel, yellowScoreLabel, currentPlayer, restartButton, this);
		
		getContentPane().add(scoreboardLabel);
		getContentPane().add(redScoreboard);
		getContentPane().add(yellowScoreboard);
		getContentPane().add(redScoreLabel);
		getContentPane().add(yellowScoreLabel);
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
		
		setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == restartButton) {
			VG_EventHandler.restart(sf, currentTurn, currentPlayer);
		} else {
			VG_GUI.setButton(sf, (VG_Button) e.getSource(), currentTurn); // set button using that function (drop from top to bottom)
			VG_EventHandler.checkForWin(sf, currentTurn);
			
			if ( currentTurn == 1 ) { // change current turn, if red
				currentTurn = 2;
				currentPlayer.setText( Lang.get("SB_CURTURN_P2") ); // update JLabel text
				currentPlayer.setForeground( new Color(229, 204, 41) );
			} else { // if yellow
				currentTurn = 1;
				currentPlayer.setText( Lang.get("SB_CURTURN_P1") );
				currentPlayer.setForeground( new Color(209, 73, 73) );
			}
		}
		
	}
	
}