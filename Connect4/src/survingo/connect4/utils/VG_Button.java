package survingo.connect4.utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VG_Button extends JButton // erweiterte JButton Klasse, um Position und "Besitzer" des Buttons zu speichern
{
	int o, z, s; //Speicherort für "Besitzer" des Buttons - 1 - Rot / 2 - Gelb und Zeile und Spalte, wo Knopf ist
	
	public VG_Button ( ImageIcon icon, int z, int s )
	{
		super ( icon ); // JButton( ImageIcon); aufrufen
		this.z = z; // Zeile festlegen
		this.s = s; // Spalte festlegen
	}
	
	public void setOwner ( int o )
	{
		this.o = o; // "Besitzer" festlegen
	}
	
	public int getOwner ()
	{
		return o; // "Besitzer" zurückgeben - 1 - Rot / 2 - Gelb
	}
	
	public int getZ ()
	{
		return z; // Zeile des Buttons zurückgeben
	}
	
	public int getS ()
	{
		return s; //Spalte zurückgeben
	}
	
}