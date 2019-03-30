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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import survingo.connect4.gui.VG_GUI_LocalFriend;
import survingo.connect4.utils.VG_Button;

public class VG_EventHandler {
	
	static Timer timer = new Timer(500, new ActionListener() {
		public void actionPerformed (ActionEvent e) {
			for ( int i = 0; i < winner.length; i++ ) { // highlight the 4 buttons
				if (winner[i].getDisabledIcon() != null) {
					winner[i].setIcon(null);
					winner[i].setDisabledIcon(null);
				} else {
					if (curturn == 1) {
						winner[i].setIcon( VG_Main.redIcon);
						winner[i].setDisabledIcon( VG_Main.redIcon ); // set icon and disabled icon so icon won't be grey
					} else {
						winner[i].setIcon( VG_Main.yellowIcon );
						winner[i].setDisabledIcon( VG_Main.yellowIcon );
					}
				}
			}
		}
	});
	static int curturn = 0;
	static VG_Button[] winner = new VG_Button[4];
	
	public static void checkForWin ( VG_Button [] [] check, int turn ) {
		// var check = all 42 buttons, var turn = current Turn ( 1 -> red, 2 -> yellow )
		curturn = turn;
		// diagonal bottom left -> top right
		for ( int i = 0; i < 3; i++ ) { // y-axis (rows)
			for ( int j = 0; j < 4; j++ ) { // x-axis (columns)
				if( check [i] [j].getOwner() == turn && check [i+1] [j+1].getOwner() == turn && check [i+2] [j+2].getOwner() == turn && check [i+3] [j+3].getOwner() == turn ) {
					winner[0] = check [i] [j];
					winner[1] = check [i+1] [j+1];
					winner[2] = check [i+2] [j+2];
					winner[3] = check [i+3] [j+3];
					win(check);
				}
			}
		}
		
		// diagonal top right -> bottom left
		for ( int i = 0; i < 3; i++ ) {
			for ( int j = 6; j > 2; j-- ) {
				if( check [i] [j].getOwner() == turn && check [i+1] [j-1].getOwner() == turn && check [i+2] [j-2].getOwner() == turn && check [i+3] [j-3].getOwner() == turn ) {
					winner[0] = check [i] [j];
					winner[1] = check [i+1] [j-1];
					winner[2] = check [i+2] [j-2];
					winner[3] = check [i+3] [j-3];
					win(check);
				}
			}
		}
		
		// horizontal
		for ( int i = 0; i < 6; i++ ) {
			for ( int j = 0; j < 4; j++ ) {
				if( check [i] [j].getOwner() == turn && check [i] [j+1].getOwner() == turn && check [i] [j+2].getOwner() == turn && check [i] [j+3].getOwner() == turn ) {
					winner[0] = check [i] [j];
					winner[1] = check [i] [j+1];
					winner[2] = check [i] [j+2];
					winner[3] = check [i] [j+3];
					win(check);
				}
			}
		}
		
		// vertical
		for ( int i = 0; i < 3; i++ ) {
			for ( int j = 0; j < 7; j++ ) {
				if( check [i] [j].getOwner() == turn && check [i+1] [j].getOwner() == turn && check [i+2] [j].getOwner() == turn && check [i+3] [j].getOwner() == turn ) {
					winner[0] = check [i] [j];
					winner[1] = check [i+1] [j];
					winner[2] = check [i+2] [j];
					winner[3] = check [i+3] [j];
					win(check);
				}
			}
		}
		
	}
	
	// Gewinn-Methode
	public static void win (VG_Button [] [] sf) {
		for ( int i = 0; i < 7; i++ ) { // Deactivate first row of buttons
			sf[0][i].setEnabled(false);
		}
		
		timer.start();
		// update scoreboard
		if ( curturn == 1 ) {
			VG_GUI_LocalFriend.redScore++;
			VG_GUI_LocalFriend.redScoreLabel.setText(""+VG_GUI_LocalFriend.redScore);
			VG_GUI_LocalFriend.redScoreLabel.setSize(VG_GUI_LocalFriend.redScoreLabel.getPreferredSize().width, VG_GUI_LocalFriend.redScoreLabel.getPreferredSize().height); // optimize size to show complete text
		} else {
			VG_GUI_LocalFriend.yellowScore++;
			VG_GUI_LocalFriend.yellowScoreLabel.setText(""+VG_GUI_LocalFriend.yellowScore);
			VG_GUI_LocalFriend.yellowScoreLabel.setSize(VG_GUI_LocalFriend.yellowScoreLabel.getPreferredSize().width, VG_GUI_LocalFriend.yellowScoreLabel.getPreferredSize().height);
		}
	}
	
	public static void restart (VG_Button[][] b) {
		timer.stop();
		for ( int i = 0; i < b.length; i++) {
			for (int j = 0; j < 7; j++) {
				b[i][j].setIcon(null); // Remove Icons
				b[i][j].setDisabledIcon(null);
				b[i][j].setOwner(0); // Remove owner of button
			}
		}
		
		for ( int i = 0; i < 7; i++ ) { // Reactivate first row of buttons
			b[0][i].setEnabled(true);
		}
	}
	
}