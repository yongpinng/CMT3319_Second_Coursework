package cmt3319.littleminesweeper;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import cmt3319.framework.Game;
import cmt3319.framework.Graphics;
import cmt3319.framework.Input.TouchEvent;
import cmt3319.framework.Pixmap;
import cmt3319.framework.Screen;

/**
 * Displays gameplay and reacts to user input, updates logical world.
 * @author
 *
 */
public class GameScreen extends Screen {
	
	public static final int EASY = 0;
	public static final int MEDIUM = 1;
	public static final int HARD = 2;
    /**
     * Logical world of the game
     */
    World world;
    /**
     * Pin or open tile
     */
    boolean pin;
    /**
     * Has user won
     */
    boolean hasWon;
    /**
     * Game difficulity setting
     */
	int difficulity;
    /**
     * Sets difficulity and creates world object.
     * @param game Game object
     * @param difficulity Difficulity setting
     */
    public GameScreen(Game game, int difficulity) {
        super(game);
        this.difficulity = difficulity;
        switch (difficulity) {
        case EASY:
        	world = new World(9,9,10); //Easy world generation
        	break;
        case MEDIUM:
        	world = new World(12,12,20); //Medium world generation 
        	break;
        case HARD:
        	world = new World(16,16,40); //Hard world generation
        	break;
        }
        
        pin = false;
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
               
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 16, 116, 288, 288)) {
                	//Determine cell number according to coordinates and difficulity (cell sizes)
                	int column = -1,row = -1;
                	switch (difficulity) {
                	case EASY:
                		column = (event.x-16)/32;
                		row = (event.y-116)/32;
                		break;
                	case MEDIUM:
                		column = (event.x-16)/24;
                		row = (event.y-116)/24;
                		break;
                	case HARD:
                		column = (event.x-16)/18;
                		row = (event.y-116)/18;
                		break;
                	}
                	//Pin tile
                	if (pin) {
                		if (world.display[row][column].pinned)
                			world.unPinTile(row,column);
                		else world.pinTile(row,column);
                		pin = false;
                	}
                	//Unpin tile
                	else if (world.display[row][column].pinned)
            			world.unPinTile(row,column);
                	//Open tile
                	else world.openTile(row, column);
                }
                //Pin button pressed
                else if (inBounds(event, 140, 63, 40, 40)) {
                	if (pin)
                		pin = false;
                	else pin = true;
                }
                //Start Over button
                else if (inBounds(event, 84, 428, 157, 48)) {
                	hasWon = false;
                	switch (difficulity) {
                    case EASY:
                    	world = new World(9,9,10);
                    	break;
                    case MEDIUM:
                    	world = new World(12,12,20);
                    	break;
                    case HARD:
                    	world = new World(16,16,40);
                    	break;
                    }
                }
            }
        }
        //Check for win
        if (!hasWon) {
        	if (world.hasWon()) {
        		hasWon = true;
        		world.stopTimer();
        		updateLeaderBoard();
        	}
        }
    }   
    /**
     * Gets data from shared preferences and updates leader board if the score is better than one in the board.
     */
    private void updateLeaderBoard() {
    	SharedPreferences sharedPref = ((Activity)game).getSharedPreferences("leaderboard", Context.MODE_PRIVATE);
    	String lvl = "";
    	if (difficulity == EASY)
    		lvl = "easy";
    	else if (difficulity == MEDIUM)
    		lvl = "medium";
    	else lvl = "hard";
    	
    	int [] scores= new int[5];
    	
    	for (int i=0; i<5; i++) {
    		scores[i] = sharedPref.getInt(lvl+i, 0);
    	}
    		
    	//Sort the leaderboard
    	for (int i=0; i<5; i++) {
    		if ((world.score < scores[i]) || (scores[i] == 0)) {
    			int temp;
    			int temp2 = world.score;
    			
    			for (int n=i; n<5; n++) {
    				temp = scores[n];
    				scores[n] = temp2;
    				temp2 = temp;
    			}
    			
    			break;
    			
    		}
    	}
    	

    	//Save to shared preferences
		Editor edit = sharedPref.edit();
		
    	for (int i=0; i<5; i++) {
    		Log.v("score "+i, ""+scores[i]);
    		edit.putInt(lvl+i, scores[i]);
    	}
    	
    	edit.commit();
				
		
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
        
        g.drawPixmap(Assets.background, 0, 0);
        if (pin)
        	g.drawPixmap(Assets.button_down, 140, 63);
        else
        	g.drawPixmap(Assets.button_up, 140, 63);
        for (int h=0; h<world.height; h++) {
        	for (int w=0; w<world.width; w++) {
        		switch (difficulity) {
        		case EASY:
        			g.drawPixmap(selectPixmap(world.display[h][w]), 16+32*w, 116+32*h);
        			break;
        		case MEDIUM:
        			g.drawPixmap(selectPixmap(world.display[h][w]), 16+24*w, 116+24*h);
        			break;
        		case HARD:
        			g.drawPixmap(selectPixmap(world.display[h][w]), 16+18*w, 116+18*h);
        			break;
        		}
        		
        	}
        }       
        if (hasWon)
        	g.drawPixmap(Assets.won, 0, 0);
        
        drawDigits(g, world.minesleft, 24, 64);    
        drawDigits(g, world.score, 221, 64);  
    }
    /**
     * Picks the right picture according to tile type
     * @param tile Tile to pick picture for
     * @return Pixmap picture
     */
    private Pixmap selectPixmap(Tile tile) {
    	Pixmap icon = null;
    	
    	if (tile.open) {
    		if (tile.type == Tile.EMPTY)
    			icon = Assets.empty[difficulity];
    		else if (tile.type == Tile.ONE)
    			icon = Assets.one[difficulity];
    		else if (tile.type == Tile.TWO)
    			icon = Assets.two[difficulity];
    		else if (tile.type == Tile.THREE)
    			icon = Assets.three[difficulity];
    		else if (tile.type == Tile.FOUR)
    			icon = Assets.four[difficulity];
    		else if (tile.type == Tile.FIVE)
    			icon = Assets.five[difficulity];
    		else if (tile.type == Tile.SIX)
    			icon = Assets.six[difficulity];
    		else if (tile.type == Tile.SEVEN)
    			icon = Assets.seven[difficulity];
    		else if (tile.type == Tile.EIGTH)
    			icon = Assets.eigth[difficulity];
    		else if (tile.type == Tile.MINE) {
    			if (tile.exploded)
    				icon = Assets.exploded[difficulity];
    			else icon = Assets.mine[difficulity];
    		}    			
    	}
    	else if (tile.pinned) 
    		icon = Assets.marked[difficulity];
    	else icon = Assets.tile[difficulity];
    	
    	return icon;
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
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
        
    }
}