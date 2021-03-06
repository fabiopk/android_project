package com.kilobolt.robotgame;

import com.kilobolt.framework.Image;
import com.kilobolt.framework.Music;
import com.kilobolt.framework.Sound;

public class Assets {

	public static Image down, down1, down2, up, left, left1, left2, right,
			right1, right2, g_up, g_down, g_down1, g_left, g_left1, g_right,
			g_right1, grass,grass_2,grass_3, grass_4, block, ghost_dead, g_dead, g_dead1, g_dead2,
			g_dead3, g_dead4, g_dead5, g_dead6, g_dead7, g_dead8, g_dead9,
			walk_left, walk_right, walk_down, walk_up, sword_down, sword_right, sword_left, sword_up, heart,
			gameover, item_heart, item_bow, item_arrow, shuriken, icon, merchant_screen, item_GUI, crossout_GUI,
			menu_screen_easy, menu_screen_normal, menu_screen_hard, menu_screen_muted, options_screen_clean,
			item_diamond, item_bronze_coin, item_gold_coin, item_cake_mix, item_cake_item, cake_placed, portal_o, portal_b, 
			item_bomb, dpad, buttons, exclamation, placed_bomb, chocolate_cake,
			red_button, green_bow_button, green_bomb_button, green_cake_button, shop_screen_selection, bomb_0, bomb_1, bomb_2 ,bomb_3 ,bomb_4;

	// public static Music theme;
	public static int option_screen_difficulty;
	public static int weapon_selection;
	public static int mute;
	public static Image splash;
	public static Image menu;
	public static Image cake;
	public static Music theme;
	public static Sound explosion;
	public static Sound applause;

	public static int prev_points;

	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		theme = sampleGame.getAudio().createMusic("zelda.mp3");
		theme.setLooping(true);
		theme.setVolume(0.50f);
		theme.play();
	}

}
