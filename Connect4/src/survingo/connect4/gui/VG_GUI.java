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
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import survingo.connect4.VG_Main;
import survingo.connect4.lang.Lang;
import survingo.connect4.utils.VG_Button;

public class VG_GUI {
	
	public static void initUI (JLabel sL, JLabel rSB, JLabel ySB, JLabel rSL, JLabel ySL, JLabel curPl, JButton restart, ActionListener listener) {
		sL.setFont(new Font("Liberation Sans", Font.PLAIN, 25));
		sL.setText( Lang.get("SB_TITLE") );
		sL.setSize( sL.getPreferredSize().width, sL.getPreferredSize().height );
		sL.setLocation(850, 50);
		
		rSB.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		rSB.setText( Lang.get("SB_SCORE_RED") );
		rSB.setSize( rSB.getPreferredSize().width, rSB.getPreferredSize().height );
		rSB.setLocation(750, 100);
		
		ySB.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		ySB.setText( Lang.get("SB_SCORE_YELLOW") );
		ySB.setSize( ySB.getPreferredSize().width, ySB.getPreferredSize().height );
		ySB.setLocation(750, 150);
		
		rSL.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		rSL.setSize( rSL.getPreferredSize().width, rSL.getPreferredSize().height );
		rSL.setLocation(1000, 100);
		
		ySL.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		ySL.setSize ( ySL.getPreferredSize().width, ySL.getPreferredSize().height );
		ySL.setLocation(1000, 150);
		
		curPl.setFont(new Font("Liberation Sans", Font.PLAIN, 25));
		curPl.setSize ( curPl.getPreferredSize().width, curPl.getPreferredSize().height );
		curPl.setLocation(800, 300);
		
		restart.setLocation(850, 400);
		restart.setSize(100, 30);
		restart.addActionListener(listener);
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
				sf [y] [b.getS()-1].setEnabled(false);
				break; // stop loop
			}
		}
		
	}
	
}