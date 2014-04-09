package cmt3319.littleminesweeper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import cmt3319.framework.Game;
import cmt3319.framework.Graphics;
import cmt3319.framework.Pixmap;
import cmt3319.framework.Screen;
/**
 * Displays a leader board with highest scores for every difficulity
 * @author
 *
 */
public class LeaderBoardScreen extends Screen {

	private int[] easy = new int[5];
	private int[] medium = new int[5];
	private int[] hard = new int[5];
	/**
	 * Gets scores from shared preferences
	 * @param game Game Object
	 */
	public LeaderBoardScreen(Game game) {
		super(game);
		SharedPreferences sharedPref = ((Activity)game).getSharedPreferences("leaderboard", Context.MODE_PRIVATE);
		for (int i=0; i<5; i++) {
			easy[i] = sharedPref.getInt("easy"+i, 0);
			medium[i] = sharedPref.getInt("medium"+i, 0);
			hard[i] = sharedPref.getInt("hard"+i, 0);
		}
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.leaderboard, 0, 0);
		
		drawDigits(g, easy[0], 27, 140);
		drawDigits(g, easy[1], 27, 199);
		drawDigits(g, easy[2], 27, 261);
		drawDigits(g, easy[3], 27, 321);
		drawDigits(g, easy[4], 27, 381);
		
		drawDigits(g, medium[0], 135, 140);
		drawDigits(g, medium[1], 135, 199);
		drawDigits(g, medium[2], 135, 261);
		drawDigits(g, medium[3], 135, 321);
		drawDigits(g, medium[4], 135, 381);
		
		drawDigits(g, hard[0], 242, 140);
		drawDigits(g, hard[1], 242, 199);
		drawDigits(g, hard[2], 242, 261);
		drawDigits(g, hard[3], 242, 321);
		drawDigits(g, hard[4], 242, 381);

	}
	/**
     * Draws a number using digit pictures
     * @param g Graphics object
     * @param n Number to draw
     * @param x X coordinate
     * @param y Y coordinate
     */
	public void drawDigits(Graphics g, int n, int x, int y) {
        if (n<10) {
        	g.drawPixmap(Assets.digit0, x, y);
        	g.drawPixmap(Assets.digit0, x+25, y);
        	g.drawPixmap(getDigitPixmap(n), x+50, y);
        }
        else if (n<100) {
        	g.drawPixmap(Assets.digit0, x, y);
        	g.drawPixmap(getDigitPixmap(n/10), x+25, y);
        	g.drawPixmap(getDigitPixmap(n%10), x+50, y);
        }
        else {
        	g.drawPixmap(getDigitPixmap(n/100), x, y);
        	g.drawPixmap(getDigitPixmap((n%100)/10), x+25, y);
        	g.drawPixmap(getDigitPixmap(n%10), x+50, y);
        }
    }
	/**
     * Picks right picture for a digit
     * @param n Number to pick picture for
     * @return Pixmap picture
     */
	private Pixmap getDigitPixmap(int n) {
    	Pixmap icon = null;
    	
    	switch (n) {
    	case 0:
    		icon = Assets.digit0;
    		break;
    	case 1:
    		icon = Assets.digit1;
    		break;
    	case 2:
    		icon = Assets.digit2;
    		break;
    	case 3:
    		icon = Assets.digit3;
    		break;
    	case 4:
    		icon = Assets.digit4;
    		break;
    	case 5:
    		icon = Assets.digit5;
    		break;
    	case 6:
    		icon = Assets.digit6;
    		break;
    	case 7:
    		icon = Assets.digit7;
    		break;
    	case 8:
    		icon = Assets.digit8;
    		break;
    	case 9:
    		icon = Assets.digit9;
    		break;
    	}
    	
    	return icon;
    }

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
