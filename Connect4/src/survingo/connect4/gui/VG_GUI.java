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