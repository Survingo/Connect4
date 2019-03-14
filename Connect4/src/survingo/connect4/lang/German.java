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

package survingo.connect4.lang;

import java.util.HashMap;
import java.util.Map;

public class German {
	
	public static Map<String, String> getMap () {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("TITLE_LOCALFRIEND", "Vier Gewinnt - Lokales Spiel mit einem Freund");
		map.put("RESTART_BUTTON", "Neustarten");
		
		// Scoreboard
		map.put("SB_TITLE", "Scoreboard");
		map.put("SB_SCORE_RED", "Punktestand von Spieler 1 (Rot) - ");
		map.put("SB_SCORE_YELLOW", "Punktestand von Spieler 2 (Gelb) - ");
		map.put("SB_CURTURN_P1", "Spieler 1 ist am Zug.");
		map.put("SB_CURTURN_P2", "Spieler 2 ist am Zug.");
		
		return map;
	}
	
}
