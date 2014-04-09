package cmt3319.littleminesweeper;

import cmt3319.framework.Pixmap;
import cmt3319.framework.Sound;
/**
 * Assets class stores all pictures as static fields.
 * Every other class can access them in order to display.
 * @author 
 *
 */
public class Assets {
    public static Pixmap background;
    public static Pixmap button_up; 
    public static Pixmap button_down;
    public static Pixmap [] tile = new Pixmap[3]; 
    public static Pixmap [] empty = new Pixmap[3];
    public static Pixmap [] one = new Pixmap[3];
    public static Pixmap [] two = new Pixmap[3];
    public static Pixmap [] three = new Pixmap[3];
    public static Pixmap [] four = new Pixmap[3];
    public static Pixmap [] five = new Pixmap[3];
    public static Pixmap [] six = new Pixmap[3];
    public static Pixmap [] seven = new Pixmap[3];
    public static Pixmap [] eigth = new Pixmap[3];
    public static Pixmap [] marked = new Pixmap[3];
    public static Pixmap [] mine = new Pixmap[3];   
    public static Pixmap [] no_mine = new Pixmap[3];   
    public static Pixmap [] exploded = new Pixmap[3];   
    
	public static Pixmap digit0;
	public static Pixmap digit1;
	public static Pixmap digit2;
	public static Pixmap digit3;
	public static Pixmap digit4;
	public static Pixmap digit5;
	public static Pixmap digit6;
	public static Pixmap digit7;
	public static Pixmap digit8;
	public static Pixmap digit9;
	public static Pixmap mainmenu_easy;
	public static Pixmap mainmenu_medium;
	public static Pixmap mainmenu_hard;
	public static Pixmap won;
	public static Pixmap leaderboard;
}
