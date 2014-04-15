package com.kilobolt.robotgame;

import java.util.List;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Music;

public class OptionsScreen extends Screen {
	
	private int option_screen_difficulty;
	private int mute;
	
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
            		if(mute == 0) {
            			mute = 1;
            			Assets.theme.stop();
            		}
            		else if(mute == 1) {
            			mute = 0;
            			Assets.theme.play();
            		}
                } //mutes sound
            	
            	if(inBounds(event, 420, 390, 1080, 150) || 
            	(option_screen_difficulty == 0 && inBounds(event, 750, 565, 415, 140)) ||
            	(option_screen_difficulty == 1 && inBounds(event, 635, 565, 645, 140)) ||
            	(option_screen_difficulty == 2 && inBounds(event, 750, 565, 420, 140))) {
            		if(option_screen_difficulty == 0)
            			option_screen_difficulty = 1;
            			//if set to easy, then change to normal
            		else if(option_screen_difficulty == 1)
            			option_screen_difficulty = 2;
            			//if set to normal, then change to hard
            		else if(option_screen_difficulty == 2)
            			option_screen_difficulty = 0;
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
        
        if(mute == 0) {
        	g.drawImage(Assets.options_screen_clean, 0, 0);
        	paintDifficulty(g);
    	}
        else if(mute == 1) {
        	g.drawImage(Assets.menu_screen_muted, 0, 0);
        	paintDifficulty(g);
        }
    }
    
    public void paintDifficulty(Graphics g){
        if(option_screen_difficulty == 0)
        	g.drawImage(Assets.menu_screen_easy, 0, 0);        	
        else if(option_screen_difficulty == 1)
        	g.drawImage(Assets.menu_screen_normal,  0, 0);
        else if(option_screen_difficulty == 2)
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