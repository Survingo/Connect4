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

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import survingo.connect4.gui.VG_GUI_LocalFriend;
import survingo.connect4.lang.Lang;

public class VG_Main {
	
	public static ImageIcon	redIcon, yellowIcon;
	public static final String VER = "0.1.1";
	
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
	
	// main function
	public static void main ( String[] args ) {
		// Initiate images
		redIcon = setImage ( "utils/VG_Red.png", 90, 90 );
		yellowIcon = setImage ( "utils/VG_Yellow.png", 90, 90 );
		Lang.init();
		
		new VG_GUI_LocalFriend();
	}
	
}