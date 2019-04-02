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
		
		map.put("TITLE", "Connect Four");
		map.put("TITLE_LOCAL_PVP", "Local - Player vs. Player");
		map.put("TITLE_ONLINE_PVP", "Online - Player vs. Player");
		map.put("TITLE_AI_EASY", "Player vs. AI - Easy");
		map.put("TITLE_AI_HARD", "Player vs. AI - Hard");
		
		map.put("CONFIRM_EXIT", "Are you sure that you want to close the program?");
		map.put("CONFIRM_LEAVE", "Are you sure that you want to leave the game? You will be redirected to the main menu.");
		map.put("RESTART_BUTTON", "Restart");
		map.put("UPDATE_AVAILABLE", "A new update for the game is available! Click <a href=\"https://github.com/Survingo/Connect4/releases\"> here</a> to download it.");
		map.put("FEATURE_UNAVAILABLE", "This feature is not available, yet! You can check for updates in the official GitHub Repository.");
		map.put("FEATURE_NOT_COMPLETE", "This feature is still in development and not completely done. " + "If you encounter any bugs be sure to report them in the official GitHub Repository in the issues section.");
		
		// Scoreboard
		map.put("SB_TITLE", "Scoreboard");
		map.put("SB_SCORE_RED", "Score of player 1 (Red) - ");
		map.put("SB_SCORE_YELLOW", "Score of player 2 (Yellow) - ");
		map.put("SB_CURTURN_P1", "It is player 1's turn.");
		map.put("SB_CURTURN_P2", "It is player 2's turn.");
		
		return map;
	}
	
}
