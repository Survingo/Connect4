package survingo.connect4.utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VG_Button extends JButton { // modificated JButton class to store information about the button's position and owner
	int o, z, s;
	
	public VG_Button ( ImageIcon icon, int z, int s ) {
		super ( icon ); // call JButton( ImageIcon );
		this.z = z; // set row of button
		this.s = s; // set column of button
	}
	
	public void setOwner ( int o ) {
		this.o = o; // set owner of button, 1 - red, 2 - yellow
	}
	
	public int getOwner () {
		return o;
	}
	
	public int getZ () {
		return z; // return row of button
	}
	
	public int getS () {
		return s; // return column
	}
	
}