package com.kilobolt.robotgame;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {

		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.ARGB4444);
		//Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.up = g.newImage("up.png", ImageFormat.ARGB4444);
		Assets.down = g.newImage("down.png", ImageFormat.ARGB4444);
		Assets.down1 = g.newImage("down1.png", ImageFormat.ARGB4444);
		Assets.down2 = g.newImage("down2.png", ImageFormat.ARGB4444);
		Assets.right = g.newImage("right.png", ImageFormat.ARGB4444);
		Assets.right1 = g.newImage("right1.png", ImageFormat.ARGB4444);
		Assets.right2 = g.newImage("right2.png", ImageFormat.ARGB4444);
		Assets.left = g.newImage("left.png", ImageFormat.ARGB4444);
		Assets.left1 = g.newImage("left1.png", ImageFormat.ARGB4444);
		Assets.left2 = g.newImage("left2.png", ImageFormat.ARGB4444);

		Assets.g_up = g.newImage("ghost_up.png", ImageFormat.ARGB4444);
		Assets.g_down = g.newImage("g_down.png", ImageFormat.ARGB4444);
		Assets.g_down1 = g.newImage("g_down1.png", ImageFormat.ARGB4444);
		Assets.g_right = g.newImage("g_right.png", ImageFormat.ARGB4444);
		Assets.g_right1 = g.newImage("g_right1.png", ImageFormat.ARGB4444);
		Assets.g_left = g.newImage("g_left.png", ImageFormat.ARGB4444);
		Assets.g_left1 = g.newImage("g_left1.png", ImageFormat.ARGB4444);
		
		Assets.g_dead = g.newImage("g_dead.png", ImageFormat.ARGB4444);
		Assets.g_dead1 = g.newImage("g_dead1.png", ImageFormat.ARGB4444);
		Assets.g_dead2 = g.newImage("g_dead2.png", ImageFormat.ARGB4444);
		Assets.g_dead3 = g.newImage("g_dead3.png", ImageFormat.ARGB4444);
		Assets.g_dead4 = g.newImage("g_dead4.png", ImageFormat.ARGB4444);
		Assets.g_dead5 = g.newImage("g_dead5.png", ImageFormat.ARGB4444);
		Assets.g_dead6 = g.newImage("g_dead6.png", ImageFormat.ARGB4444);
		Assets.g_dead7 = g.newImage("g_dead7.png", ImageFormat.ARGB4444);
		Assets.g_dead8 = g.newImage("g_dead8.png", ImageFormat.ARGB4444);
		Assets.g_dead9 = g.newImage("g_dead9.png", ImageFormat.ARGB4444);
		
		Assets.walk_left = g.newImage("walk_left.png", ImageFormat.ARGB4444);
		Assets.walk_right = g.newImage("walk_right.png", ImageFormat.ARGB4444);
		Assets.walk_down = g.newImage("walk_down.png", ImageFormat.ARGB4444);
		Assets.walk_up = g.newImage("walk_up.png", ImageFormat.ARGB4444);
		
		Assets.sword_down = g.newImage("sword_swing.png", ImageFormat.ARGB4444);
		Assets.sword_right = g.newImage("sword_right.png", ImageFormat.ARGB4444);
		Assets.sword_left = g.newImage("sword_left.png", ImageFormat.ARGB4444);
		Assets.sword_up = g.newImage("sword_up.png", ImageFormat.ARGB4444);
		
		Assets.item_heart = g.newImage("item_heart.png", ImageFormat.ARGB4444);
		Assets.item_cake_mix = g.newImage("cake_mix.png", ImageFormat.ARGB4444);
		Assets.item_cake_item = g.newImage("cake_item.png", ImageFormat.ARGB4444);
		Assets.cake_placed = g.newImage("cake_placed.png", ImageFormat.ARGB4444);
		Assets.item_bow = g.newImage("item_bow.png", ImageFormat.ARGB4444);
		Assets.item_arrow = g.newImage("item_arrow.png", ImageFormat.ARGB4444);
		Assets.item_bomb = g.newImage("bomb_item.png", ImageFormat.ARGB4444);
		Assets.item_bronze_coin = g.newImage("bronze_coin.png", ImageFormat.ARGB4444);
		Assets.item_gold_coin = g.newImage("gold_coin.png", ImageFormat.ARGB4444);
		Assets.item_diamond = g.newImage("diamond.png", ImageFormat.ARGB4444);
		Assets.item_GUI = g.newImage("gui_new.png", ImageFormat.ARGB4444);
		Assets.crossout_GUI = g.newImage("crossout.png", ImageFormat.ARGB4444);
		
		Assets.grass = g.newImage("grass.png", ImageFormat.RGB565);
		Assets.grass_2 = g.newImage("grass_2.png", ImageFormat.RGB565);
		Assets.grass_3 = g.newImage("grass_3.png", ImageFormat.RGB565);
		Assets.grass_4 = g.newImage("grass_4.png", ImageFormat.RGB565);
		Assets.block = g.newImage("block.png", ImageFormat.RGB565);
		Assets.cake = g.newImage("cake.png", ImageFormat.RGB565);
		Assets.heart = g.newImage("heart.png", ImageFormat.RGB565);
		Assets.gameover = g.newImage("gameover.png", ImageFormat.RGB565);
		Assets.shuriken = g.newImage("shuriken.png", ImageFormat.RGB565);
		Assets.placed_bomb = g.newImage("placed_bomb.png", ImageFormat.RGB565);
		Assets.icon = g.newImage("icon.png", ImageFormat.RGB565);
		Assets.shop_screen_selection = g.newImage("select_shop_screen.png", ImageFormat.RGB565);
		Assets.merchant_screen = g.newImage("merchant_screen.png", ImageFormat.RGB565);
		
		Assets.bomb_0 = g.newImage("bomb_0.png", ImageFormat.RGB565);
		Assets.bomb_1 = g.newImage("bomb_1.png", ImageFormat.RGB565);
		Assets.bomb_2 = g.newImage("bomb_2.png", ImageFormat.RGB565);
		Assets.bomb_3 = g.newImage("bomb_3.png", ImageFormat.RGB565);
		Assets.bomb_4 = g.newImage("bomb_4.png", ImageFormat.RGB565);
		
//		Assets.grass = g.newImage("grass.png", ImageFormat.ARGB4444);
//		Assets.grass_2 = g.newImage("grass_2.png", ImageFormat.ARGB4444);
//		Assets.grass_3 = g.newImage("grass_3.png", ImageFormat.ARGB4444);
//		Assets.grass_4 = g.newImage("grass_4.png", ImageFormat.ARGB4444);
//		Assets.block = g.newImage("block.png", ImageFormat.ARGB4444);
//		Assets.cake = g.newImage("cake.png", ImageFormat.ARGB4444);
//		Assets.heart = g.newImage("heart.png", ImageFormat.ARGB4444);
//		Assets.gameover = g.newImage("gameover.png", ImageFormat.ARGB4444);
//		Assets.shuriken = g.newImage("shuriken.png", ImageFormat.ARGB4444);
//		Assets.placed_bomb = g.newImage("placed_bomb.png", ImageFormat.ARGB4444);
//		Assets.icon = g.newImage("icon.png", ImageFormat.ARGB4444);
//		Assets.merchant_screen = g.newImage("merchant_screen.png", ImageFormat.ARGB4444);
		
		Assets.menu_screen_easy = g.newImage("menu_screen_easy.png", ImageFormat.RGB565);
		Assets.menu_screen_normal = g.newImage("menu_screen_normal.png", ImageFormat.RGB565);
		Assets.menu_screen_hard = g.newImage("menu_screen_hard.png", ImageFormat.RGB565);
		Assets.menu_screen_muted = g.newImage("menu_screen_muted.png", ImageFormat.RGB565);		
		Assets.options_screen_clean = g.newImage("options_screen_clean.png", ImageFormat.RGB565);
		
		Assets.portal_b = g.newImage("portal_blue.png", ImageFormat.ARGB4444);
		Assets.portal_o = g.newImage("portal_orange.png", ImageFormat.ARGB4444);
		Assets.dpad = g.newImage("dpad.png", ImageFormat.ARGB4444);
		Assets.buttons = g.newImage("buttons.png", ImageFormat.ARGB4444);
		Assets.exclamation = g.newImage("exclamation.png", ImageFormat.ARGB4444);
		Assets.chocolate_cake = g.newImage("chocolate_cake.png", ImageFormat.ARGB4444);
		
		Assets.red_button = g.newImage("attack_button.png", ImageFormat.ARGB4444);
		Assets.green_bow_button = g.newImage("bow_button.png", ImageFormat.ARGB4444);
		Assets.green_bomb_button = g.newImage("bomb_button.png", ImageFormat.ARGB4444);
		Assets.green_cake_button = g.newImage("cake_button.png", ImageFormat.ARGB4444);
		
//		Assets.menu_screen_easy = g.newImage("menu_screen_easy.png", ImageFormat.ARGB4444);
//		Assets.menu_screen_normal = g.newImage("menu_screen_normal.png", ImageFormat.ARGB4444);
//		Assets.menu_screen_hard = g.newImage("menu_screen_hard.png", ImageFormat.ARGB4444);
//		Assets.menu_screen_muted = g.newImage("menu_screen_muted.png", ImageFormat.ARGB4444);
//		Assets.options_screen_clean = g.newImage("options_screen_clean.png", ImageFormat.ARGB4444);
		
		// This is how you would load a sound if you had one.
		 Assets.explosion = game.getAudio().createSound("explosion.mp3");
		 Assets.applause = game.getAudio().createSound("applause.mp3");

		game.setScreen(new SplashScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
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
