package com.kilobolt.robotgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Input.TouchEvent;

public class SplashScreen extends Screen {

	public SplashScreen(Game game) {
		super(game);
	}

	//BEST COMMENT EVER!!
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
		g.drawImage(Assets.menu, 0, 0);
		
		// Defining a paint object
		Paint paint = new Paint();
		paint.setTextSize(100);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		// Darken the entire screen
		g.drawARGB(255, 0, 0, 0);
		g.drawString("UVA CS2110 Spring 2014", 960, 165, paint);
		g.drawString("Masterminds: ", 960, 450, paint);
		g.drawString("Fabio Baldissera, Brian Spann, &", 960, 550, paint);
		g.drawString("Leah Grande",960, 650, paint);
		g.drawString("Special thanks to: kilobolt.com", 960, 850, paint);
		g.drawImage(Assets.icon, 960, 900);
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
