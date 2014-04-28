package com.kilobolt.robotgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Input.TouchEvent;

public class ShopScreen extends Screen {
	
	private static int level = 0;
	
	public ShopScreen(Game game) {
		super(game);
		
	}

	@Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
        	TouchEvent event = touchEvents.get(i);
        	if (event.type == TouchEvent.TOUCH_UP) {

        		//if click bomb
        		if (inBounds(event, 100, 95, 155, 280)) {
        			if (GameScreen.getLink().loseMoney(10)) {
        				GameScreen.getLink().addBombs(1);
        			}
        		}
        		//if click heart
        		if (inBounds(event, 350, 95, 155, 280)) {
        			if (GameScreen.getLink().loseMoney(5)) {
        			GameScreen.getLink().addLife(1);
        			}
        		}
        		//if click arrow
        		if (inBounds(event, 591, 100, 168, 281)) {
        			if (GameScreen.getLink().loseMoney(3)) {
        			GameScreen.getLink().addArrows(1);
        			}
        		}
        		//if click cake
        		if (inBounds(event, 845, 100, 155, 280)) {
        			if (GameScreen.getLink().loseMoney(1)) {
        				GameScreen.getLink().addCakes(1);
        			}
        		}
        		//if choose bomb for weapon
        		if (inBounds(event, 1483, 311, 151, 273)) {
        			Assets.weapon_selection = 1;
        			GameScreen.getLink().setUsingBombs(true);
        			GameScreen.getLink().setUsingArrows(false);
        			GameScreen.getLink().setUsingCakes(false);
        			level++;
        			game.setScreen(new GameScreen(game));
        		}
        		//if choose cake for weapon
        		if (inBounds(event, 1335, 634, 153, 273)) {
        			Assets.weapon_selection = 2;
        			GameScreen.getLink().setUsingCakes(true);
        			GameScreen.getLink().setUsingBombs(false);
        			GameScreen.getLink().setUsingArrows(false);
        			level++;
        			game.setScreen(new GameScreen(game));
        		}
        		//if choose arrow for weapon
        		if (inBounds(event, 1587, 634, 151, 273)) {
        			Assets.weapon_selection = 0;
        			GameScreen.getLink().setUsingArrows(true);
        			GameScreen.getLink().setUsingBombs(false);
        			GameScreen.getLink().setUsingCakes(false);
        			level++;
        			game.setScreen(new GameScreen(game));
        		}
        		

        		
        	}
        }
	}
	
    private boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.merchant_screen, 0, 0);
		
		// Defining a paint object
		Paint paint = new Paint();
		paint.setTextSize(100);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		//set bomb price
		g.drawString("10", 170, 358, paint);
		//set heart price
		g.drawString("5", 430, 358, paint);
		//set arrow price
		g.drawString("3", 670, 358, paint);
		//set cake price
		g.drawString("1", 918, 358, paint);
		//show number of coins
		g.drawString(String.valueOf(GameScreen.getLink().getMoney()), 300, 592, paint);
		//show number of points
		g.drawString(String.valueOf(GameScreen.getLink().getPoints()), 300, 815, paint);
		//show number of bombs
		g.drawString(String.valueOf(GameScreen.getLink().getBombs()), 1558, 563, paint);
		//show number of cakes
		g.drawString(String.valueOf(GameScreen.getLink().getCakes()), 1412, 890, paint);
		//show number of arrows
		g.drawString(String.valueOf(GameScreen.getLink().getArrows()), 1665, 890, paint);
		
		switch(Assets.weapon_selection) {
		case 0: //draw box around bow
			g.drawImage(Assets.shop_screen_selection, 1538, 586);
			break;
		case 1: //draw box around bombs
			g.drawImage(Assets.shop_screen_selection, 1434, 263);
			break;
		case 2: //draw box around cakes
			g.drawImage(Assets.shop_screen_selection, 1287, 586);
			break;
		default: //default to draw box around bow
			g.drawImage(Assets.shop_screen_selection, 1538, 586);
			break;
		}

		
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

	@Override
	public void backButton() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level1) {
		level = level1;
	}

}
