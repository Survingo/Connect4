package survingo.connect4.gui;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import survingo.connect4.VG_Main;
import survingo.connect4.utils.VG_Button;

public class VG_GUI
{
	
	public static void initButton (JPanel frame, ActionListener listener, VG_Button button, int x, int y) // Methode um Knöpfe einzustellen, um Platz zu sparen; x = Zahl aktueller Spalte, y = Zahl aktueller Zeile
	{
		
		button.addActionListener( listener ); // ActionListener hinzufügen zum Knopf, welcher dann bestimmt, was passiert falls er gedrückt wird
		button.setBackground( new Color( 238, 238, 238 ) ); //Farbe des Knopfes 238,238,238
		button.setSize( button.getPreferredSize().width, button.getPreferredSize().height ); // Größe bestätigen
		if ( y == 1 ) button.setEnabled(true); else button.setEnabled(false); // Falls Knopf in erster Zeile -> aktivieren, sonst deaktivieren
		frame.add( button ); // Knopf zum JPanel hinzufügen
	}
	
	public static void setButton ( VG_Button [] [] sf, VG_Button b, int turn )
	{
		// von letzter Zeile zu ester Zeile aus (unten nach oben)
		for ( int y = 5; y > -1; y--)
		{
			if ( sf [y] [b.getS()-1].getOwner() == 0 ) // Falls kein "Besitzer" vorhanden ist
			{
				sf [y] [b.getS()-1].setOwner(turn); // Besitzer festlegen
				if (turn == 1) // Icon der Buttons festlegen
				{
					sf [y] [b.getS()-1].setIcon( VG_Main.redIcon); // ImageIcon festlegen
					sf [y] [b.getS()-1].setDisabledIcon( VG_Main.redIcon ); //Icon für deaktivierten Button, beides muss verwendet werden da es aus irgendeinen Grund allein nicht funktioniert (sonst Icon nur einfarbig, da Knopf deaktiviert)
				}
				else
				{
					sf [y] [b.getS()-1].setIcon( VG_Main.yellowIcon );
					sf [y] [b.getS()-1].setDisabledIcon( VG_Main.yellowIcon );
				}
				sf [y] [b.getS()-1].setEnabled(false); // Knopf nochmal deaktivieren
				break; // Schleife abbrechen
			}
		}
		
	}
	
}