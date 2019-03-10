package survingo.connect4;

import java.awt.Color;

import survingo.connect4.gui.VG_GUI_LocalFriend;
import survingo.connect4.utils.VG_Button;

public class VG_EventHandler
{
	
	public static void checkForWin ( VG_Button [] [] check, int turn ) //Methode, um zu prüfen, ob jemand gewonnen hat. Vier Knöpfe werden jedes mal geprüft, ob ein Spieler sie "besitzt"
	{
		// check = alle 42 Knöpfe, turn = Wer ist aktuell am Zug? 1 - Rot / 2 - Gelb
		// Diagonal unten-links -> oben-rechts
		for ( int i = 0; i < 3; i++ ) // y-Achse prüfen (Zeilen)
		{
			for ( int j = 0; j < 4; j++ ) // x-Achse (Spalten) prüfen
			{	// Prüft, ob 4 Knöpfe den gleichen "Besitzer" haben
				if( check [i] [j].getOwner() == turn && check [i+1] [j+1].getOwner() == turn && check [i+2] [j+2].getOwner() == turn && check [i+3] [j+3].getOwner() == turn )
				{	// Falls ja -> Gewinn-Methode abrufen, Parameter: aktueller Zug (1-Rot/2-Gelb), alle 42 Knöpfe, die 4 Gewinn-Knöpfe
					win ( turn, check, new VG_Button [] { check [i] [j], check [i+1] [j+1], check [i+2] [j+2], check [i+3] [j+3] } );
				}
			}
		}
		
		// Diagonal oben-rechts -> unten-links
		for ( int i = 0; i < 3; i++ )
		{
			for ( int j = 6; j > 2; j-- )
			{
				if( check [i] [j].getOwner() == turn && check [i+1] [j-1].getOwner() == turn && check [i+2] [j-2].getOwner() == turn && check [i+3] [j-3].getOwner() == turn )
				{
					win ( turn, check, new VG_Button [] { check [i] [j], check [i+1] [j-1], check [i+2] [j-2], check [i+3] [j-3] } );
				}
			}
		}
		
		// Horizontal
		for ( int i = 0; i < 6; i++ )
		{
			for ( int j = 0; j < 4; j++ )
			{
				if( check [i] [j].getOwner() == turn && check [i] [j+1].getOwner() == turn && check [i] [j+2].getOwner() == turn && check [i] [j+3].getOwner() == turn )
				{
					win ( turn, check, new VG_Button [] { check [i] [j], check [i] [j+1], check [i] [j+2], check [i] [j+3] } );
				}
			}
		}
		
		// Vertikal
		for ( int i = 0; i < 3; i++ )
		{
			for ( int j = 0; j < 7; j++ )
			{
				if( check [i] [j].getOwner() == turn && check [i+1] [j].getOwner() == turn && check [i+2] [j].getOwner() == turn && check [i+3] [j].getOwner() == turn )
				{
					win ( turn, check, new VG_Button [] {check [i] [j], check [i+1] [j], check [i+2] [j], check [i+3] [j]} );
				}
			}
		}
		
	}
	
	// Gewinn-Methode
	public static void win ( int turn, VG_Button [] [] sf, VG_Button [] b )
	{
		for ( int i = 0; i < b.length; i++ ) // Die vier Gewinn-Knöpfe markieren mit schwarzen Hintergrund
		{
			b[i].setBackground( Color.BLACK );
		}
		
		// Scoreboard aktualisieren
		if ( turn == 1 )
		{
			VG_GUI_LocalFriend.redScore++; // Score um einen Punkt erhöhen
			VG_GUI_LocalFriend.redScoreLabel.setText(""+VG_GUI_LocalFriend.redScore); // Label vom Score aktualisieren mit aktuellen Score
			VG_GUI_LocalFriend.redScoreLabel.setSize(VG_GUI_LocalFriend.redScoreLabel.getPreferredSize().width, VG_GUI_LocalFriend.redScoreLabel.getPreferredSize().height); // Breite und Höhe anpassen, ansonsten Text nicht komplett dargestellt
		}
		else
		{
			VG_GUI_LocalFriend.yellowScore++;
			VG_GUI_LocalFriend.yellowScoreLabel.setText(""+VG_GUI_LocalFriend.yellowScore);
			VG_GUI_LocalFriend.yellowScoreLabel.setSize(VG_GUI_LocalFriend.yellowScoreLabel.getPreferredSize().width, VG_GUI_LocalFriend.yellowScoreLabel.getPreferredSize().height);
		}
		
		for ( int i = 0; i < 7; i++ ) // Erste Zeile von Knöpfen deaktivieren
		{
			sf [0] [i].setEnabled(false);
		}
	}
	
	public static void restart ( VG_Button [] [] b )
	{ // Mothode für den Neustart des Spieles
		for ( int i = 0; i < b.length; i++)
		{
			for (int j = 0; j < 7; j++)
			{ // Alle 42 Knöpfe
				b [i] [j].setBackground(null); //Hintergrundfarbe entfernen
				b [i] [j].setIcon(null); // Icons entfernen
				b [i] [j].setOwner(0); // "Besitzer" entfernen
				if ( i == 0 ) b [i] [j].setEnabled(true); // Erste Reihe an Knöpfen wieder aktivieren
			}
		}
		// Spieler 1 als ersten Spieler wieder festlegen
		VG_GUI_LocalFriend.currentTurn = 1;
		VG_GUI_LocalFriend.currentPlayer.setText( "Spieler 1 ist am Zug." );
		VG_GUI_LocalFriend.currentPlayer.setForeground( Color.RED );
	}
	
}