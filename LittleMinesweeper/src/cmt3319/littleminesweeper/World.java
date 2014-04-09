package cmt3319.littleminesweeper;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Represents logical game world
 * @author
 *
 */
public class World {
	/**
	 * World width
	 */
	public int width;
	/**
	 * World height
	 */
	public int height;
	/**
	 * Total number of mines in the World
	 */
	public int mines;
	/**
	 * Game Over or not
	 */
    public boolean gameOver = false;
    /**
     * Score
     */
    public int score = 0;
    /**
     * Number of mines not pinned (May be inaccurate if user pins wrong tile)
     */
    public int minesleft;
    /**
     * Timer to increase score
     */
    private Timer t;
    /**
     * Stores mines locations
     */
    public boolean mineField[][];
    /**
     * Stores Tile details
     * System displays graphics according to this array
     */
    public Tile display[][];
    private Random random = new Random();
    /**
     * First move or not (to start timer)
     */
	private boolean firstMove;
	/**
	 * Creates new world
	 * @param width World width
	 * @param height World height 
	 * @param mines Number of mines
	 */
    public World(int width, int height, int mines) {
    	this.width = width;
    	this.height = height;
    	this.mines = mines;
    	minesleft = mines;
    	mineField = new boolean[height][width];
        placeMines();
        generateDisplay();
        firstMove = true;
    }
    /**
     * Check whether user has won
     * @return True if all mines are pinned
     */
    public boolean hasWon() {
    	boolean won = true;
    	
    	if (minesleft==0) {
    		int mines_pinned = 0;
    		int other_tiles_open = 0;
    		for (int x=0; x<width; x++) {
    			for (int y=0; y<height; y++) {
    				if (display[x][y].pinned && mineField[x][y]) {
    					mines_pinned++;
    				}
    				else if (display[x][y].open && !mineField[x][y]) {
    					other_tiles_open++;
    				}
    			}    		
    		}
    		if ((mines != mines_pinned) || (other_tiles_open != ((width*height)-mines))) {
    			won = false;
    		}
    	}
    	else won=false;
    	
    	return won;
    }
    /**
     * Stops the timer
     */
    public void stopTimer() {
    	t.cancel();
    }
    /**
     * Generates other tiles according to mines location
     */
	private void generateDisplay() {
		display = new Tile[height][width];
		
		for (int h=0; h<height; h++) {
			for (int w=0; w<width; w++) {
				if (mineField[h][w])
					display[h][w] = new Tile(Tile.MINE);
				else display[h][w] = new Tile(checkAround(h,w));
			}
		}
	}
	/**
	 * Pin a tile
	 * @param row Tile row number
	 * @param column Tile column number
	 */
	public void pinTile(int row, int column) {
		display[row][column].pinned = true;
		minesleft--;
	}
	/**
	 * Unpin a tile
	 * @param row Tile row number
	 * @param column Tile column number
	 */
	public void unPinTile(int row, int column) {
		display[row][column].pinned = false;
		minesleft++;
	}
	/**
	 * Open a tile
	 * @param row Tile row number
	 * @param column Tile column number
	 */
	public void openTile(int row, int column) {
		if (firstMove) {
			t = new Timer();
	        t.scheduleAtFixedRate(new TimerTask() {

	            @Override
	            public void run() {
	            	score++;
	            }
	                 
	        }, 0, 1000);
	        firstMove = false;
		}
		if (!display[row][column].open) {
			if (display[row][column].type == Tile.EMPTY) {
				display[row][column].open = true;
				openSpace(row,column);
			}
			else if (display[row][column].type == Tile.MINE) {
				display[row][column].open = true;
				display[row][column].exploded = true;
				t.cancel();
				revealField();
			}
			else display[row][column].open = true;
		}
	}
	/**
	 * Opens every tile on game board (when hit mine)
	 */
	private void revealField() {
		for (int h=0; h<height; h++) {
			for (int w=0; w<width; w++) {
				display[h][w].open = true;
			}
		}
	}
	/**
	 * Opens a number of tiles around, when user hits empty tile.
	 * Uses callbacks to previous method openTile().
	 * @param row Tile row number
	 * @param column Tile column number
	 */
	private void openSpace(int row, int column) {
			try {
					openTile(row+1,column);
			} catch (ArrayIndexOutOfBoundsException e) {}
			try {
					openTile(row+1,column+1);
			} catch (ArrayIndexOutOfBoundsException e) {}
			try {
					openTile(row,column+1);
			} catch (ArrayIndexOutOfBoundsException e) {}
			try {
					openTile(row-1,column+1);
			} catch (ArrayIndexOutOfBoundsException e) {}
			try {

					openTile(row-1,column);
			} catch (ArrayIndexOutOfBoundsException e) {}
			try {

					openTile(row-1,column-1);
			} catch (ArrayIndexOutOfBoundsException e) {}
			try {

					openTile(row,column-1);
			} catch (ArrayIndexOutOfBoundsException e) {}
			try {

					openTile(row+1,column-1);
			} catch (ArrayIndexOutOfBoundsException e) {}
	}
	/**
	 * Checks how many mines are around the tile.
	 * Used when building 'display' array.
	 * @param row Tile row number
	 * @param column Tile column number
	 * @return
	 */
	protected int checkAround(int row, int column) {
		int result = 0;
		
		try {
			if (mineField[row+1][column])
				result++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (mineField[row+1][column+1])
				result++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (mineField[row][column+1])
				result++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (mineField[row-1][column+1])
				result++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (mineField[row-1][column])
				result++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (mineField[row-1][column-1])
				result++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (mineField[row][column-1])
				result++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if (mineField[row+1][column-1])
				result++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		return result;
	}
	/**
	 * Places mines in random positions
	 */
	private void placeMines() {
		
		for (int h=0; h<height; h++) {
			for (int w=0; w<width; w++) {
				mineField[h][w] = false;
			}
		}
		
		for (int i=0; i<mines; i++) {			
			boolean placed = false;			
			while (!placed) {
				int column = random.nextInt(width);
				int row = random.nextInt(height);
				if (!mineField[row][column]) {
					mineField[row][column] = true;
					placed = true;
				}
			}
		}		
	}

    
}
