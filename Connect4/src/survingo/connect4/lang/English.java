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

import survingo.connect4.VG_Main;

public class English {
	
	public static Map<String, String> getMap () {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("TITLE", "Connect Four");
		map.put("TITLE_LOCAL_PVP", "Local - Player vs. Player");
		map.put("TITLE_ONLINE_PVP", "Online - Player vs. Player");
		map.put("TITLE_AI_EASY", "Player vs. AI - Easy");
		map.put("TITLE_AI_HARD", "Player vs. AI - Hard");
		
		map.put("MENU_LANGUAGE", "Language");
		map.put("MENU_LANG_CHANGED", "In order to change the language you will have to restart the program.");
		map.put("MENU_FILE", "File");
		map.put("MENU_FILE_SAVE", "Save scoreboard");
		map.put("MENU_FILE_MENU", "Return to Main Menu");
		map.put("MENU_FILE_EXIT", "Exit");
		map.put("MENU_HELP", "Help");
		map.put("MENU_HELP_TUTORIAL", "Tutorial");
		map.put("MENU_HELP_UPDATE", "Check for updates");
		map.put("MENU_HELP_ABOUT", "About");
		map.put("MENU_HELP_ABOUT_TEXT", "Connect Four v" + VG_Main.VER + " (c) 2019 Survingo\nThis open-source project was written in Java.");
		
		map.put("CONFIRM_EXIT", "Are you sure that you want to close the program?");
		map.put("CONFIRM_RESTART", "Are you sure that you want to restart the current game?\nGame progress will not be saved.");
		map.put("CONFIRM_LEAVE", "Are you sure that you want to leave the game?\nYou will be redirected to the main menu.");
		map.put("RESTART_BUTTON", "Restart");
		map.put("UPDATE_AVAILABLE", "A new update for the game is available! Click <a href=\"https://github.com/Survingo/Connect4/releases\"> here</a> to download it.");
		map.put("UP_TO_DATE", "The program is up to date! No new update is available.");
		
		// Errors
		map.put("FEATURE_UNAVAILABLE", "This feature is not available, yet!\nYou can check for updates in the official GitHub Repository.");
		map.put("FEATURE_NOT_COMPLETE", "This feature is still in development and not completely done.\nIf you encounter any bugs please be sure to report them to\nthe official GitHub Repository in the issues section.");
		map.put("NIMBUS_LAF_ERROR", "An error occured while setting the Nimbus Look and Feel.\nThis program is optimized for the Nimbus Look and Feel. Visiual bugs may appear now. Error code: ");
		
		// Scoreboard
		map.put("SB_TITLE", "Scoreboard");
		map.put("SB_SCORE_RED", "Score of player 1 (Red) - ");
		map.put("SB_SCORE_YELLOW", "Score of player 2 (Yellow) - ");
		map.put("SB_CURTURN", "Current Turn:");
		map.put("SB_CURTURN_P1", "Player 1");
		map.put("SB_CURTURN_P2", "Player 2");
		
		return map;
	}
	
}
