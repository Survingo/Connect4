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

import java.awt.Color;

import survingo.connect4.gui.VG_GUI_LocalFriend;
import survingo.connect4.lang.Lang;
import survingo.connect4.utils.VG_Button;

public class VG_EventHandler {
	
	public static void checkForWin ( VG_Button [] [] check, int turn ) {
		// var check = all 42 buttons, var turn = current Turn ( 1 -> red, 2 -> yellow )
		
		// diagonal bottom left -> top right
		for ( int i = 0; i < 3; i++ ) { // y-axis (rows)
			for ( int j = 0; j < 4; j++ ) { // x-axis (columns)
				if( check [i] [j].getOwner() == turn && check [i+1] [j+1].getOwner() == turn && check [i+2] [j+2].getOwner() == turn && check [i+3] [j+3].getOwner() == turn ) {
					win ( turn, check, new VG_Button [] { check [i] [j], check [i+1] [j+1], check [i+2] [j+2], check [i+3] [j+3] } );
				}
			}
		}
		
		// diagonal top right -> bottom left
		for ( int i = 0; i < 3; i++ ) {
			for ( int j = 6; j > 2; j-- ) {
				if( check [i] [j].getOwner() == turn && check [i+1] [j-1].getOwner() == turn && check [i+2] [j-2].getOwner() == turn && check [i+3] [j-3].getOwner() == turn ) {
					win ( turn, check, new VG_Button [] { check [i] [j], check [i+1] [j-1], check [i+2] [j-2], check [i+3] [j-3] } );
				}
			}
		}
		
		// horizontal
		for ( int i = 0; i < 6; i++ ) {
			for ( int j = 0; j < 4; j++ ) {
				if( check [i] [j].getOwner() == turn && check [i] [j+1].getOwner() == turn && check [i] [j+2].getOwner() == turn && check [i] [j+3].getOwner() == turn ) {
					win ( turn, check, new VG_Button [] { check [i] [j], check [i] [j+1], check [i] [j+2], check [i] [j+3] } );
				}
			}
		}
		
		// vertical
		for ( int i = 0; i < 3; i++ ) {
			for ( int j = 0; j < 7; j++ ) {
				if( check [i] [j].getOwner() == turn && check [i+1] [j].getOwner() == turn && check [i+2] [j].getOwner() == turn && check [i+3] [j].getOwner() == turn ) {
					win ( turn, check, new VG_Button [] {check [i] [j], check [i+1] [j], check [i+2] [j], check [i+3] [j]} );
				}
			}
		}
		
	}
	
	// Gewinn-Methode
	public static void win ( int turn, VG_Button [] [] sf, VG_Button [] b ) {
		for ( int i = 0; i < b.length; i++ ) { // highlight the 4 buttons
			b[i].setBackground( Color.BLACK );
		}
		
		// update scoreboard
		if ( turn == 1 ) {
			VG_GUI_LocalFriend.redScore++;
			VG_GUI_LocalFriend.redScoreLabel.setText(""+VG_GUI_LocalFriend.redScore);
			VG_GUI_LocalFriend.redScoreLabel.setSize(VG_GUI_LocalFriend.redScoreLabel.getPreferredSize().width, VG_GUI_LocalFriend.redScoreLabel.getPreferredSize().height); // optimize size to show complete text
		} else {
			VG_GUI_LocalFriend.yellowScore++;
			VG_GUI_LocalFriend.yellowScoreLabel.setText(""+VG_GUI_LocalFriend.yellowScore);
			VG_GUI_LocalFriend.yellowScoreLabel.setSize(VG_GUI_LocalFriend.yellowScoreLabel.getPreferredSize().width, VG_GUI_LocalFriend.yellowScoreLabel.getPreferredSize().height);
		}
		
		for ( int i = 0; i < 7; i++ ) { // Deactivate first row of buttons
			sf [0] [i].setEnabled(false);
		}
	}
	
	public static void restart ( VG_Button [] [] b ) {
		for ( int i = 0; i < b.length; i++) {
			for (int j = 0; j < 7; j++) {
				b [i] [j].setBackground(null); // Remove background color
				b [i] [j].setIcon(null); // Remove Icons
				b [i] [j].setOwner(0); // Remove owner of button
				if ( i == 0 ) {
					b [i] [j].setEnabled(true); // Reactivate first row of button
				}
			}
		}
		
		VG_GUI_LocalFriend.currentTurn = 1;
		VG_GUI_LocalFriend.currentPlayer.setText( Lang.get("SB_CURTURN_P1") );
		VG_GUI_LocalFriend.currentPlayer.setForeground( Color.RED );
	}
	
}