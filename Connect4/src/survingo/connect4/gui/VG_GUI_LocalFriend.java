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
import java.awt.Font;
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

public class VG_GUI_LocalFriend extends JFrame implements ActionListener {
	
	public static int currentTurn = 1; // 1 - red, 2 - yellow
	public static int redScore = 0, yellowScore = 0; // score counter
	JButton restartButton = new JButton( Lang.get("RESTART_BUTTON") );
	public static JLabel currentPlayer = new JLabel( Lang.get("SB_CURTURN_P1") );
	public static JLabel redScoreLabel = new JLabel ( "0" ), yellowScoreLabel = new JLabel ( "0" );
	VG_Button [] [] sf = new VG_Button [6] [7]; // create game field using multidimensional array = 6 rows (y-axis) / 7 columns (x-axis)
	
	public VG_GUI_LocalFriend () {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle( Lang.get("TITLE_LOCALFRIEND") );
		getContentPane().setPreferredSize(new Dimension(1100, 650));
		getContentPane().setLayout(null);
		
		JPanel gamefield = new JPanel(); // seperate JPanel for game field
		gamefield.setSize(700,600);
		gamefield.setLayout( new GridLayout ( 6, 7 ) ); // layout manager sets size and position of buttons automatically
		
		JLabel scoreboardLabel = new JLabel( Lang.get("SB_TITLE") );
		scoreboardLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 25));
		scoreboardLabel.setSize ( scoreboardLabel.getPreferredSize().width, scoreboardLabel.getPreferredSize().height );
		scoreboardLabel.setLocation ( 850, 50 );
		getContentPane().add(scoreboardLabel);
		
		JLabel redScoreboard = new JLabel( Lang.get("SB_SCORE_RED") );
		redScoreboard.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		redScoreboard.setLocation( 750, 100 );
		redScoreboard.setSize ( redScoreboard.getPreferredSize().width, redScoreboard.getPreferredSize().height );
		getContentPane().add(redScoreboard);
		
		JLabel yellowScoreboard = new JLabel( Lang.get("SB_SCORE_YELLOW") );
		yellowScoreboard.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		yellowScoreboard.setLocation( 750, 150 );
		yellowScoreboard.setSize ( yellowScoreboard.getPreferredSize().width, yellowScoreboard.getPreferredSize().height );
		getContentPane().add(yellowScoreboard);
		
		redScoreLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		redScoreLabel.setLocation( 1000, 100);
		redScoreLabel.setSize ( redScoreLabel.getPreferredSize().width, redScoreLabel.getPreferredSize().height );
		getContentPane().add(redScoreLabel);
		
		yellowScoreLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		yellowScoreLabel.setLocation ( 1000, 150 );
		yellowScoreLabel.setSize ( yellowScoreLabel.getPreferredSize().width, yellowScoreLabel.getPreferredSize().height );
		getContentPane().add(yellowScoreLabel);
		
		currentPlayer.setFont(new Font("Liberation Sans", Font.PLAIN, 25));
		currentPlayer.setLocation(800, 300);
		currentPlayer.setSize ( currentPlayer.getPreferredSize().width, currentPlayer.getPreferredSize().height );
		getContentPane().add(currentPlayer);
		
		restartButton.setLocation(850, 400);
		restartButton.setSize( 100, 30 );
		restartButton.addActionListener( this );
		getContentPane().add(restartButton);
		
		try {
			setIconImage ( VG_Main.redIcon.getImage() ); // Fensterbild festlegen
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		int z = sf.length; // number of rows = length of array
		int zCounter;
		int s = 7; // number of columns
		int sCounter;
		
		for ( zCounter = 0; zCounter < z; zCounter++ ) { // loop for rows
			for ( sCounter = 0; sCounter < s; sCounter++ ) { // loop for columns
				sf [ zCounter ] [ sCounter ] = new VG_Button ( null, zCounter+1, sCounter+1 ); // init all 42 game field buttons
				VG_GUI.initButton( gamefield, this, sf [ zCounter ] [ sCounter ], sCounter+1, zCounter+1); // Knöpfe einstellen mithilfe der Methode
			}
		}
		
		getContentPane().add(gamefield);
		gamefield.setLocation(25, 25); // add small edge
		
		setResizable(false);
		pack();
		setLocationRelativeTo(VG_Main.mainFrame);
		setVisible(true);
	}
	
	public void actionPerformed ( ActionEvent e ) {
		if (e.getSource() == restartButton) {
			VG_EventHandler.restart( sf );
		} else {
			VG_GUI.setButton(sf, (VG_Button) e.getSource(), currentTurn); // set button using that function (drop from top to bottom)
			VG_EventHandler.checkForWin( sf, currentTurn );
			
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