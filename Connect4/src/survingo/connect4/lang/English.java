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

public class English {
	
	public static Map<String, String> getMap () {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("TITLE_MAIN", "Connect Four");
		map.put("TITLE_LOCALFRIEND", "Connect Four - Local Game with a Friend");
		map.put("RESTART_BUTTON", "Restart");
		map.put("UPDATE_AVAILABLE", "A new update for the game is available! Click <a href=\"https://github.com/Survingo/Connect4/releases\"> here</a> to download it.");
		map.put("PROPERTIES_NOT_UP_TO_DATE", "The version of the properties file is not compatible with the program. Creating a new file now...");
		
		// Scoreboard
		map.put("SB_TITLE", "Scoreboard");
		map.put("SB_SCORE_RED", "Score of player 1 (Red) - ");
		map.put("SB_SCORE_YELLOW", "Score of player 2 (Yellow) - ");
		map.put("SB_CURTURN_P1", "It is player 1's turn.");
		map.put("SB_CURTURN_P2", "It is player 2's turn.");
		
		return map;
	}
	
}
