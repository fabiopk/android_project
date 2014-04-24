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

        		//if click "select your item"
        		if (inBounds(event, 0, 0, 100, 100)) {
        			level++;
        			game.setScreen(new GameScreen(game));
        		}
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
        			GameScreen.getLink().setUsingBombs(true);
        			GameScreen.getLink().setUsingArrows(false);
        			GameScreen.getLink().setUsingCakes(false);
        		}
        		//if choose cake for weapon
        		if (inBounds(event, 1335, 634, 153, 273)) {
        			GameScreen.getLink().setUsingCakes(true);
        			GameScreen.getLink().setUsingBombs(false);
        			GameScreen.getLink().setUsingArrows(false);
        		}
        		//if choose arrow for weapon
        		if (inBounds(event, 1587, 634, 151, 273)) {
        			GameScreen.getLink().setUsingArrows(true);
        			GameScreen.getLink().setUsingBombs(false);
        			GameScreen.getLink().setUsingCakes(false);
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
		g.drawString("10", 170, 360, paint);
		//set heart price
		g.drawString("5", 420, 360, paint);
		//set arrow price
		g.drawString("3", 660, 360, paint);
		//set cake price
		g.drawString("1", 910, 360, paint);
		//show number of coins
		g.drawString(String.valueOf(GameScreen.getLink().getMoney()), 300, 600, paint);
		//show number of points
		g.drawString(String.valueOf(GameScreen.getLink().getPoints()), 300, 825, paint);
		//show number of bombs
		g.drawString(String.valueOf(GameScreen.getLink().getBombs()), 1550, 560, paint);
		//show number of cakes
		g.drawString(String.valueOf(GameScreen.getLink().getCakes()), 1405, 890, paint);
		//show number of arrows
		g.drawString(String.valueOf(GameScreen.getLink().getArrows()), 1660, 890, paint);

		
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

	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level1) {
		level = level1;
	}

}
