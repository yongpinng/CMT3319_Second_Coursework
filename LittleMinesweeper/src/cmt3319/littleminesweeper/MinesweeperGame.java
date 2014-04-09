package cmt3319.littleminesweeper;

import cmt3319.framework.Screen;
import cmt3319.framework.impl.AndroidGame;
/**
 * Launcher activity.
 * Sets screen to loading.
 * Handles back button presses, to achieve correct behaviour
 * @author
 *
 */
public class MinesweeperGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
    
    @Override
    public void onBackPressed() {
    	//If back pressed in game screen
    	if (getCurrentScreen() instanceof GameScreen)
    		setScreen(new MainMenuScreen(this));
    	//if back pressed in leaderboard
    	else if (getCurrentScreen() instanceof LeaderBoardScreen)
    		setScreen(new MainMenuScreen(this));
    	//If back pressed in main menu
    	else super.onBackPressed();
    }
    
    
}