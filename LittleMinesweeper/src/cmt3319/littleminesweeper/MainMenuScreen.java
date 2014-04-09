package cmt3319.littleminesweeper;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import cmt3319.framework.Game;
import cmt3319.framework.Graphics;
import cmt3319.framework.Input.TouchEvent;
import cmt3319.framework.Screen;

/**
 * Displays main menu of the game.
 * Can start game, select difficulity or go to leaderboard.
 * @author
 *
 */
public class MainMenuScreen extends Screen {
    int difficulity;
    SharedPreferences sharedPref;

    /**
     * Gets difficulity setting from shared preferences.
     * @param game
     */
	public MainMenuScreen(Game game) {
        super(game);   
        sharedPref = ((Activity)game).getSharedPreferences("settings", Context.MODE_PRIVATE);
		this.difficulity = sharedPref.getInt("difficulity", 0);
    }   

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
            	//Leaderboard button
                if(inBounds(event, 62, 75, 196, 62) ) {
                	game.setScreen(new LeaderBoardScreen(game));
                    return;
                }
                //Difficulity button
                else if(inBounds(event, 62, 196, 196, 62) ) {
                    if (difficulity<2)
                    	difficulity++;
                    else difficulity = 0;
                    
                    SharedPreferences.Editor edit = sharedPref.edit();
                    edit.putInt("difficulity", difficulity);
                    edit.commit();
                }
                //Start button (mine)
                else if (inBounds(event, 84, 284, 147, 147)) {
                	game.setScreen(new GameScreen(game, difficulity));
                    return;
                }

            }
        }
    }
    /**
     * Checks if touch was in bounds of specified coordinates
     * @param event Touch Event
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Area width
     * @param height Area height
     * @return
     */
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        switch (difficulity) {
        case GameScreen.EASY:
        	g.drawPixmap(Assets.mainmenu_easy, 0, 0);
        	break;
        case GameScreen.MEDIUM:
        	g.drawPixmap(Assets.mainmenu_medium, 0, 0);
        	break;
        case GameScreen.HARD:
        	g.drawPixmap(Assets.mainmenu_hard, 0, 0);
        	break;
        }
        

    }

    @Override
    public void pause() {        
        //Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
