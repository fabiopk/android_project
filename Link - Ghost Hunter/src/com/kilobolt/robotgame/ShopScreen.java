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

                if (inBounds(event, 0, 0, 1920, 1080)) {
                    game.setScreen(new MainMenuScreen(game));
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
		//g.drawString(Character.getMoney(), 0, 0, paint); -> This creates an error because
		// it says it is a static reference to a non-static method, do you know what I should do?
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

}