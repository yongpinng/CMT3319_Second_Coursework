package cmt3319.littleminesweeper;

import cmt3319.framework.Game;
import cmt3319.framework.Graphics;
import cmt3319.framework.Screen;
import cmt3319.framework.Graphics.PixmapFormat;
/**
 * Loads assets into the game, to be used later.
 * Sets screen to main menu
 * @author
 *
 */
public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.won = g.newPixmap("won.png", PixmapFormat.RGB565);
        Assets.leaderboard = g.newPixmap("leaderboard.png", PixmapFormat.RGB565);
        Assets.mainmenu_easy = g.newPixmap("mainmenu_easy.png", PixmapFormat.RGB565);
        Assets.mainmenu_medium = g.newPixmap("mainmenu_medium.png", PixmapFormat.RGB565);
        Assets.mainmenu_hard = g.newPixmap("mainmenu_hard.png", PixmapFormat.RGB565);
        Assets.button_up = g.newPixmap("button-up.png", PixmapFormat.ARGB4444);
        Assets.button_down = g.newPixmap("button-down.png", PixmapFormat.ARGB4444);
        Assets.tile[0] = g.newPixmap("tile.png", PixmapFormat.ARGB4444);
        Assets.empty[0] = g.newPixmap("empty.png", PixmapFormat.ARGB4444);
        Assets.one[0] = g.newPixmap("one.png", PixmapFormat.ARGB4444);
        Assets.two[0] = g.newPixmap("two.png", PixmapFormat.ARGB4444);
        Assets.three[0] = g.newPixmap("three.png", PixmapFormat.ARGB4444);
        Assets.four[0] = g.newPixmap("four.png", PixmapFormat.ARGB4444);
        Assets.five[0] = g.newPixmap("five.png", PixmapFormat.ARGB4444);
        Assets.six[0] = g.newPixmap("six.png", PixmapFormat.ARGB4444);
        Assets.seven[0] = g.newPixmap("seven.png", PixmapFormat.ARGB4444);
        Assets.eigth[0] = g.newPixmap("eigth.png", PixmapFormat.ARGB4444);
        Assets.mine[0] = g.newPixmap("mine.png", PixmapFormat.ARGB4444);
        Assets.no_mine[0] = g.newPixmap("no-mine.png", PixmapFormat.ARGB4444);
        Assets.marked[0] = g.newPixmap("marked.png", PixmapFormat.ARGB4444);
        Assets.exploded[0] = g.newPixmap("exploded.png", PixmapFormat.ARGB4444);
        
        Assets.tile[1] = g.newPixmap("tile_24x24.png", PixmapFormat.ARGB4444);
        Assets.empty[1] = g.newPixmap("empty_24x24.png", PixmapFormat.ARGB4444);
        Assets.one[1] = g.newPixmap("one_24x24.png", PixmapFormat.ARGB4444);
        Assets.two[1] = g.newPixmap("two_24x24.png", PixmapFormat.ARGB4444);
        Assets.three[1] = g.newPixmap("three_24x24.png", PixmapFormat.ARGB4444);
        Assets.four[1] = g.newPixmap("four_24x24.png", PixmapFormat.ARGB4444);
        Assets.five[1] = g.newPixmap("five_24x24.png", PixmapFormat.ARGB4444);
        Assets.six[1] = g.newPixmap("six_24x24.png", PixmapFormat.ARGB4444);
        Assets.seven[1] = g.newPixmap("seven_24x24.png", PixmapFormat.ARGB4444);
        Assets.eigth[1] = g.newPixmap("eigth_24x24.png", PixmapFormat.ARGB4444);
        Assets.mine[1] = g.newPixmap("mine_24x24.png", PixmapFormat.ARGB4444);
        Assets.no_mine[1] = g.newPixmap("no-mine_24x24.png", PixmapFormat.ARGB4444);
        Assets.marked[1] = g.newPixmap("marked_24x24.png", PixmapFormat.ARGB4444);
        Assets.exploded[1] = g.newPixmap("exploded_24x24.png", PixmapFormat.ARGB4444);
        
        Assets.tile[2] = g.newPixmap("tile_18x18.png", PixmapFormat.ARGB4444);
        Assets.empty[2] = g.newPixmap("empty_18x18.png", PixmapFormat.ARGB4444);
        Assets.one[2] = g.newPixmap("one_18x18.png", PixmapFormat.ARGB4444);
        Assets.two[2] = g.newPixmap("two_18x18.png", PixmapFormat.ARGB4444);
        Assets.three[2] = g.newPixmap("three_18x18.png", PixmapFormat.ARGB4444);
        Assets.four[2] = g.newPixmap("four_18x18.png", PixmapFormat.ARGB4444);
        Assets.five[2] = g.newPixmap("five_18x18.png", PixmapFormat.ARGB4444);
        Assets.six[2] = g.newPixmap("six_18x18.png", PixmapFormat.ARGB4444);
        Assets.seven[2] = g.newPixmap("seven_18x18.png", PixmapFormat.ARGB4444);
        Assets.eigth[2] = g.newPixmap("eigth_18x18.png", PixmapFormat.ARGB4444);
        Assets.mine[2] = g.newPixmap("mine_18x18.png", PixmapFormat.ARGB4444);
        Assets.no_mine[2] = g.newPixmap("no-mine_18x18.png", PixmapFormat.ARGB4444);
        Assets.marked[2] = g.newPixmap("marked_18x18.png", PixmapFormat.ARGB4444);
        Assets.exploded[2] = g.newPixmap("exploded_18x18.png", PixmapFormat.ARGB4444);
        
        Assets.digit0 = g.newPixmap("0.png", PixmapFormat.ARGB4444);
        Assets.digit1 = g.newPixmap("1.png", PixmapFormat.ARGB4444);
        Assets.digit2 = g.newPixmap("2.png", PixmapFormat.ARGB4444);
        Assets.digit3 = g.newPixmap("3.png", PixmapFormat.ARGB4444);
        Assets.digit4 = g.newPixmap("4.png", PixmapFormat.ARGB4444);
        Assets.digit5 = g.newPixmap("5.png", PixmapFormat.ARGB4444);
        Assets.digit6 = g.newPixmap("6.png", PixmapFormat.ARGB4444);
        Assets.digit7 = g.newPixmap("7.png", PixmapFormat.ARGB4444);
        Assets.digit8 = g.newPixmap("8.png", PixmapFormat.ARGB4444);
        Assets.digit9 = g.newPixmap("9.png", PixmapFormat.ARGB4444);
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

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