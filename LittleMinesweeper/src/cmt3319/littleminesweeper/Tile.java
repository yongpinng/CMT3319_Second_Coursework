package cmt3319.littleminesweeper;
/**
 * Represents a tile on a game board.
 * @author
 *
 */
public class Tile {
	
	public static final int EMPTY = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGTH = 8;
	public static final int MINE = 9;
	/**
	 * Tile type
	 */
	public int type;
	/**
	 * Pinned or not
	 */
	public boolean pinned;
	/**
	 * Open or not
	 */
	public boolean open;
	/**
	 * Exploded or not (only for mines)
	 */
	public boolean exploded;
	/**
	 * Sets default field values
	 * @param type Tile type
	 */
	public Tile(int type) {
		this.type = type;
		pinned = false;
		open = false;
		exploded = false;
	}

}
