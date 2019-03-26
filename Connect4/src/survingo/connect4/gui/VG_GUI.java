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
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import survingo.connect4.VG_Main;
import survingo.connect4.utils.VG_Button;

public class VG_GUI {
	
	public static void initButton (JPanel frame, ActionListener listener, VG_Button button, int x, int y) {
		button.addActionListener( listener );
		button.setBackground( new Color( 238, 238, 238 ) ); // set color of button
		button.setContentAreaFilled(true);
		button.setSize( button.getPreferredSize().width, button.getPreferredSize().height );
		if ( y == 1 ) { // if button is located in first row
			button.setEnabled(true);
		} else {
			button.setEnabled(false);
		}
		frame.add( button );
	}
	
	public static void setButton ( VG_Button [] [] sf, VG_Button b, int turn ) {
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
				sf [y] [b.getS()-1].setEnabled(false);
				break; // stop loop
			}
		}
		
	}
	
}