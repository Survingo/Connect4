package survingo.connect4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import survingo.connect4.VG_EventHandler;
import survingo.connect4.VG_Main;
import survingo.connect4.utils.VG_Button;

public class VG_GUI_LocalFriend extends JFrame implements ActionListener
{
	public static int currentTurn = 1; // Wer ist aktuell am Zug? 1 - Rot / 2 - Gelb
	JButton restartButton = new JButton( "Neustarten" ); // Neustart-Knopf initialisieren
	public static JLabel currentPlayer = new JLabel( "Spieler 1 ist am Zug." );
	public static int redScore = 0, yellowScore = 0; // Score-Zähler
	public static JLabel redScoreLabel = new JLabel ( "0" ), yellowScoreLabel = new JLabel ( "0" ); // Label für Score-Zähler
	VG_Button [] [] sf = new VG_Button [6] [7]; // Spielfelder erstellen mithilfe multidimensionalen Arrays = 6 Zeilen / 7 Spalten
	
	public VG_GUI_LocalFriend ()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Bei schließen des Fensters Programm komplett beenden
		setTitle( "Vier Gewinnt - Lokales Spiel" ); // Titel des Fensters festlegen
		getContentPane().setPreferredSize(new Dimension(1100, 650)); // Größe des Fensters in Pixeln festlegen
		getContentPane().setLayout(null); // kein Layout-Manager verwenden anstatt von FlowLayout
		
		JPanel spielfeld = new JPanel(); // eigenes JPanel für Spielfeld
		spielfeld.setSize(700,600);
		spielfeld.setLayout( new GridLayout ( 6, 7 ) ); // Layout-Manager verwenden, um Größe / Position Knöpfe automatisch auszuwählen
		
		JLabel scoreboardLabel = new JLabel("Scoreboard"); // neues JLabel
		scoreboardLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 25)); //Text formatieren
		scoreboardLabel.setSize ( scoreboardLabel.getPreferredSize().width, scoreboardLabel.getPreferredSize().height ); //Größe nach Text anpassen
		scoreboardLabel.setLocation ( 850, 50 ); // Ort festlegen auf JFrame
		getContentPane().add(scoreboardLabel); // Zum Haupt-JPanel hinzufügen
		
		JLabel redScoreboard = new JLabel("Punktestand von Spieler 1 (Rot) - ");
		redScoreboard.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		redScoreboard.setLocation( 750, 100 );
		redScoreboard.setSize ( redScoreboard.getPreferredSize().width, redScoreboard.getPreferredSize().height );
		getContentPane().add(redScoreboard);
		
		JLabel yellowScoreboard = new JLabel("Punktestand von Spieler 2 (Gelb) - ");
		yellowScoreboard.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		yellowScoreboard.setLocation( 750, 150 );
		yellowScoreboard.setSize ( yellowScoreboard.getPreferredSize().width, yellowScoreboard.getPreferredSize().height );
		getContentPane().add(yellowScoreboard);
		
		redScoreLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		redScoreLabel.setLocation( 1000, 100);
		redScoreLabel.setSize ( redScoreLabel.getPreferredSize().width, redScoreLabel.getPreferredSize().height );
		getContentPane().add(redScoreLabel);
		
		yellowScoreLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 13));
		yellowScoreLabel.setLocation ( 1000, 150 );
		yellowScoreLabel.setSize ( yellowScoreLabel.getPreferredSize().width, yellowScoreLabel.getPreferredSize().height );
		getContentPane().add(yellowScoreLabel);
		
		currentPlayer.setFont(new Font("Liberation Sans", Font.PLAIN, 25));
		currentPlayer.setLocation(800, 300);
		currentPlayer.setSize ( currentPlayer.getPreferredSize().width, currentPlayer.getPreferredSize().height );
		getContentPane().add(currentPlayer);
		
		restartButton.setLocation(850, 400);
		restartButton.setSize( 100, 30 );
		restartButton.addActionListener( this );
		getContentPane().add(restartButton);
		
		try
		{
			setIconImage ( VG_Main.redIcon.getImage() ); // Fensterbild festlegen
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
		int z = sf.length; // Anzahl Zeilen = Länge des Arrays
		int zCounter; // Zähler für Schleife...
		int s = 7; // Anzahl Spalten
		int sCounter;
		
		for ( zCounter = 0; zCounter < z; zCounter++ ) // Schleife für Zeilen
		{
			for ( sCounter = 0; sCounter < s; sCounter++ ) // Schleife für Spalten
			{
				sf [ zCounter ] [ sCounter ] = new VG_Button ( null, zCounter+1, sCounter+1 ); // Alle 42 Spielfelder initialisieren
				VG_GUI.initButton( spielfeld, this, sf [ zCounter ] [ sCounter ], sCounter+1, zCounter+1); // Knöpfe einstellen mithilfe der Methode
			}
		}
		
		getContentPane().add(spielfeld); //Spielfeld hinzufügen
		spielfeld.setLocation(25, 25); //Kleinen Rand
		
		setResizable(false); // Fenstergröße fixieren - nicht mehr änderbar vom Benutzer
		pack(); // Fenstergröße und Größe von Komponenten darin optimieren
		setLocationRelativeTo(null); // Fenster zentral auf den Bildschirm platzieren
		setVisible(true); // Fenster sichtbar machen
		
	}
	
	// Knopf gedrückt -> entscheidet, was pasiert
	public void actionPerformed ( ActionEvent e )
	{
		//Falls Neustart-Knopf gedrückt
		if (e.getSource() == restartButton)
		{
			VG_EventHandler.restart( sf ); // Neustart-Methode ausführen
		}
		else
		{ // Falls nicht -> Spielfeld-Knopf
			VG_GUI.setButton(sf, (VG_Button) e.getSource(), currentTurn); //Knopf festlegen mithilfe setButton-Methode -> von unten nach oben
			VG_EventHandler.checkForWin( sf, currentTurn ); //prüfen, ob jemand gewonnen hat
			
			// aktuellen Zug ändern
			if ( currentTurn == 1 ) // Falls Rot
			{
				currentTurn = 2; // Gelb nun am Zug
				currentPlayer.setText( "Spieler 2 ist am Zug." ); // Text vom Label aktualisieren
				currentPlayer.setForeground( Color.YELLOW );
			}
			else
			{ // Falls Gelb
				currentTurn = 1; // Rot nun am Zug
				currentPlayer.setText( "Spieler 1 ist am Zug." );
				currentPlayer.setForeground( Color.RED );
			}
		}
		
	}
	
}