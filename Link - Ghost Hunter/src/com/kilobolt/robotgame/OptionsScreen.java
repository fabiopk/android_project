package com.kilobolt.robotgame;

import java.util.List;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.robotgame.Assets;
import com.kilobolt.robotgame.SplashScreen;

public class OptionsScreen extends Screen {
	

	
    public OptionsScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        //Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	
            	if( inBounds(event, 740, 105, 570, 155)) {
            		if(Assets.mute == 0) {
            			Assets.mute = 1;
            			Assets.theme.stop();
            		}
            		else if(Assets.mute == 1) {
            			Assets.mute = 0;
            			Assets.theme.play();
            		}
                } //mutes sound
            	
            	if(inBounds(event, 420, 390, 1080, 150) || 
            	(Assets.option_screen_difficulty == 0 && inBounds(event, 750, 565, 415, 140)) ||
            	(Assets.option_screen_difficulty == 1 && inBounds(event, 635, 565, 645, 140)) ||
            	(Assets.option_screen_difficulty == 2 && inBounds(event, 750, 565, 420, 140))) {
            		if(Assets.option_screen_difficulty == 0)
            			Assets.option_screen_difficulty = 1;
            			//if set to easy, then change to normal
            		else if(Assets.option_screen_difficulty == 1)
            			Assets.option_screen_difficulty = 2;
            			//if set to normal, then change to hard
            		else if(Assets.option_screen_difficulty == 2)
            			Assets.option_screen_difficulty = 0;
            			//if set to hard, then change to easy
            	} //changes difficulty (defaults to easy. cycles in order: easy, normal, hard
            	
            	if (inBounds(event, 550, 875, 630, 160)) {
            		game.setScreen(new MainMenuScreen(game));
            	} //returns to the main menu
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
        g.drawImage(Assets.options_screen_clean, 0, 0);
        
        if(Assets.mute == 0) {
        	g.drawImage(Assets.options_screen_clean, 0, 0);
        	paintDifficulty(g);
    	}
        else if(Assets.mute == 1) {
        	g.drawImage(Assets.menu_screen_muted, 0, 0);
        	paintDifficulty(g);
        }
    }
    
    public void paintDifficulty(Graphics g){
        if(Assets.option_screen_difficulty == 0)
        	g.drawImage(Assets.menu_screen_easy, 0, 0);        	
        else if(Assets.option_screen_difficulty == 1)
        	g.drawImage(Assets.menu_screen_normal,  0, 0);
        else if(Assets.option_screen_difficulty == 2)
            g.drawImage(Assets.menu_screen_hard, 0, 0);
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
    	game.setScreen(new MainMenuScreen(game));
    }
}