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