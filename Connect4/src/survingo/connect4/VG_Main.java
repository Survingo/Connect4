package survingo.connect4;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import survingo.connect4.gui.VG_GUI_LocalFriend;

public class VG_Main
{
	
	public static ImageIcon	redIcon, yellowIcon; // Speicherort der Bilder f�r Spielsteine
	
	// Methode, um Bilder zu skalieren auf einen bestimmten Wert in Pixeln
	public static ImageIcon setImage ( String path, int width, int height )
	{
		URL IconURL = VG_Main.class.getResource(path); // Pfad wird von Hauptklasse aus angegeben und von Hauptklasse geladen, muss so sein in Eclipse ansonsten wird kein Bild zur�ckgegeben
		
		if ( IconURL != null ) { // Pr�ft, ob Bild vorhanden ist
			ImageIcon icon = new ImageIcon( IconURL ); // Bild initialisieren
			icon.setImage( icon.getImage().getScaledInstance( width, height, Image.SCALE_SMOOTH ) ); //Bild wird skaliert auf angegebene Breite und H�he, nutzt einen Algorithmus, der Sch�rfe des Bildes �ber Zeit bevorzugt
			return icon; // Bild zur�ckgeben
		}
		else
		{ //Bild nicht vorhanden / gefunden -> leeren Dummy zur�ckgeben, damit Programm nicht abst�rzt
			return new ImageIcon();
		}
	}
	
	// Hauptmethode
	public static void main ( String[] args )
	{
		// Bilder der Spielsteine initialisieren
		redIcon = setImage ( "utils/VG_Red.png", 90, 90 );
		yellowIcon = setImage ( "utils/VG_Yellow.png", 90, 90 );
		
		new VG_GUI_LocalFriend(); // neues JFrame f�r Spieler gegen Spieler
	}
	
}