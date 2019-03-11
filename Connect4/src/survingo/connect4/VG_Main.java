package survingo.connect4;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import survingo.connect4.gui.VG_GUI_LocalFriend;

public class VG_Main {
	
	public static ImageIcon	redIcon, yellowIcon;
	
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
		
		new VG_GUI_LocalFriend();
	}
	
}