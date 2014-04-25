package com.kilobolt.robotgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.R.string;
import android.graphics.Color;
import android.graphics.Paint;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;
import com.kilobolt.robotgame.Character.State;
import com.kilobolt.robotgame.Item.Type;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Running;

	// Variable Setup

	public static Image down, down1, down2, up, left, left1, left2, right,
			right1, right2, g_up, g_down, g_down1, g_left, g_left1, g_right,
			g_right1, grass, cake, g_dead, g_dead1, g_dead2, g_dead3, g_dead4,
			g_dead5, g_dead6, g_dead7, g_dead8, g_dead9, walk_left, walk_right,
			walk_down, walk_up, sword_down, sword_right, sword_left, sword_up,
			heart, item_heart, item_bow, item_arrow, shuriken, 
			item_diamond, item_bronze_coin, item_gold_coin, item_cake_mix, 
			item_cake_item, cake_placed, item_bomb, placed_bomb;

	static Paint paint;

	Paint paint2;
	private static int rows = 16;
	private static int columns = 9;
	private static int[][] tilemap;
	static Character link;
	private Animation a_down, a_left, a_right, ag_right, ag_left, ag_down;
	private static ArrayList<Ghost> ghosts;
	private static ArrayList<Arrow> arrows;
	private static ArrayList<Bomb> bombs;
	static Animation ag_dead;
	private SpriteSheet w_left, w_right, w_down, w_up;
	private boolean nearGhost;

	private int frameCounter;
	private int timer;

	private ArrayList<Item> items;

	private boolean hasPortal = false;

	private static SpriteSheet s_down, s_right, s_left, s_up;

	public static ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	public void setGhosts(ArrayList<Ghost> ghosts) {
		this.ghosts = ghosts;
	}

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here

		createTilemap();
		if (ShopScreen.getLevel() == 0) {
			link = new Character();
		}

		ghosts = new ArrayList<Ghost>();
		addGhost();
		addGhost();
		addGhost();
		addGhost();

		items = new ArrayList<Item>();
		arrows = new ArrayList<Arrow>();
		bombs = new ArrayList<Bomb>();

		up = Assets.up;
		down = Assets.down;
		down1 = Assets.down1;
		down2 = Assets.down2;
		left = Assets.left;
		left1 = Assets.left1;
		left2 = Assets.left2;
		right = Assets.right;
		right1 = Assets.right1;
		right2 = Assets.right2;
		g_left = Assets.g_left;
		g_left1 = Assets.g_left1;
		g_right = Assets.g_right;
		g_right1 = Assets.g_right1;
		g_up = Assets.g_up;
		g_down = Assets.g_down;
		g_down1 = Assets.g_down1;
		g_dead = Assets.g_dead;
		g_dead1 = Assets.g_dead1;
		g_dead2 = Assets.g_dead2;
		g_dead3 = Assets.g_dead3;
		g_dead4 = Assets.g_dead4;
		g_dead5 = Assets.g_dead5;
		g_dead6 = Assets.g_dead6;
		g_dead7 = Assets.g_dead7;
		g_dead8 = Assets.g_dead8;
		g_dead9 = Assets.g_dead9;
		walk_left = Assets.walk_left;
		walk_right = Assets.walk_right;
		walk_down = Assets.walk_down;
		walk_up = Assets.walk_up;
		sword_down = Assets.sword_down;
		sword_right = Assets.sword_right;
		sword_left = Assets.sword_left;
		sword_up = Assets.sword_up;
		heart = Assets.heart;
		item_heart = Assets.item_heart;
		item_bow = Assets.item_bow;
		item_arrow = Assets.item_arrow;
		shuriken = Assets.shuriken;
		item_diamond = Assets.item_diamond;
		item_bronze_coin = Assets.item_bronze_coin;
		item_gold_coin = Assets.item_gold_coin;
		item_cake_mix = Assets.item_cake_mix;
		item_cake_item = Assets.item_cake_item;
		cake_placed = Assets.cake_placed;
		item_bomb = Assets.item_bomb;
		placed_bomb = Assets.placed_bomb;

		switch (ShopScreen.getLevel()) {
		case 0:
			grass = Assets.grass;
			break;

		case 1:
			grass = Assets.grass_2;
			break;

		case 2:
			grass = Assets.grass_3;
			break;

		case 3:
			grass = Assets.grass_4;
			break;

		default:
			grass = Assets.grass;
			break;
		}

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(60);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(20.2f);
		// Add animations
		a_down = new Animation();
		a_down.addFrame(down, 1250);
		a_down.addFrame(down1, 50);
		a_down.addFrame(down2, 50);
		a_down.addFrame(down1, 50);

		a_left = new Animation();
		a_left.addFrame(left, 1250);
		a_left.addFrame(left1, 50);
		a_left.addFrame(left2, 50);
		a_left.addFrame(left1, 50);

		a_right = new Animation();
		a_right.addFrame(right, 1250);
		a_right.addFrame(right1, 50);
		a_right.addFrame(right2, 50);
		a_right.addFrame(right1, 50);

		ag_right = new Animation();
		ag_right.addFrame(g_right, 150);
		ag_right.addFrame(g_right1, 150);

		ag_left = new Animation();
		ag_left.addFrame(g_left, 150);
		ag_left.addFrame(g_left1, 150);

		ag_down = new Animation();
		ag_down.addFrame(g_down, 150);
		ag_down.addFrame(g_down1, 150);

		ag_dead = new Animation();
		ag_dead.addFrame(g_dead, 60);
		ag_dead.addFrame(g_dead1, 60);
		ag_dead.addFrame(g_dead2, 60);
		ag_dead.addFrame(g_dead3, 60);
		ag_dead.addFrame(g_dead4, 60);
		ag_dead.addFrame(g_dead5, 60);
		ag_dead.addFrame(g_dead6, 60);
		ag_dead.addFrame(g_dead7, 60);
		ag_dead.addFrame(g_dead9, 60);
		ag_dead.addFrame(g_dead8, 700);
		ag_dead.addFrame(g_dead8, 50);

		w_left = new SpriteSheet(walk_left, 8, 40);
		w_right = new SpriteSheet(walk_right, 8, 40);
		w_down = new SpriteSheet(walk_down, 9, 40);
		w_up = new SpriteSheet(walk_up, 9, 40);
		s_down = new SpriteSheet(sword_down, 6, 30);
		s_right = new SpriteSheet(sword_right, 5, 30, 0, 40, 160);
		s_left = new SpriteSheet(sword_left, 5, 30, 40, 40, 160);
		s_up = new SpriteSheet(sword_up, 7, 30, 31, 40, 160);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

	}

	public static SpriteSheet getS_down() {
		return s_down;
	}

	public static void setS_down(SpriteSheet s_down) {
		GameScreen.s_down = s_down;
	}

	public static SpriteSheet getS_right() {
		return s_right;
	}

	public static void setS_right(SpriteSheet s_right) {
		GameScreen.s_right = s_right;
	}

	public static int[][] getTilemap() {
		return tilemap;
	}

	public void setTilemap(int[][] tilemap) {
		this.tilemap = tilemap;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		//if (state == GameState.Ready)
		 //updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		if (!link.isAlive) {
			state = GameState.GameOver;
		}

		if (link.getPoints() >= (30 + ShopScreen.getLevel() * 30) && !hasPortal) {
		Item portal = new Item(14, 5);
		tilemap[14][5] = 0;
		portal.setType(Type.Portal);
		items.add(portal);
		hasPortal = true;
		}

		for (Ghost gst : ghosts) { // Clean map before ghost move, so it is zero
									// again
			tilemap[gst.getXpos()][gst.getYpos()] = 0;
		}
		for (Ghost gst : ghosts) {
			gst.update();
			gst.randonMovement();
		}
		for (Ghost gst : ghosts) {
			tilemap[gst.getXpos()][gst.getYpos()] = 3; // New position is now =
														// 3 (not acessible to
														// walk)
		}

		// This is identical to the update() method from our Unit 2/3 game.
		tilemap[link.getXpos()][link.getYpos()] = 0;
		animate();
		link.update();
		
		if (timer >= 300) {
			addGhost();
			timer = 0;
		} else {
			timer++;
		}

		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {

				if (inBounds(event, 0, 767, 187, 141)) {
					link.moveLeft();
				} else if (inBounds(event, 322, 767, 220, 141 )) {
					link.moveRight();
				} else if (inBounds(event, 188, 540, 145, 227)) {
					link.moveUp();
				} else if (inBounds(event, 178, 907, 145, 174)) {
					link.moveDown();
				} else if (inBounds(event, 1346, 791, 210, 225)) {
					link.atack();
				} else if (inBounds(event, 1542, 629, 212, 217)) {
					arrows.add(link.shoot());
				} else if (link.getUsingBombs() && inBounds(event, 1440, 0, 480, 540)) {
					bombs.add(link.placeBomb());
				} else if (link.getUsingCakes() && inBounds(event, 1440, 0, 480, 540)) {
					//this is where the method goes for placing cakes
				}				
			}
			tilemap[link.getXpos()][link.getYpos()] = 2;
		}

		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			Item item = (Item) itr.next();
			item.update();
			if (!item.isOnGround()) {
				itr.remove();
			}
		}

		Iterator itr2 = arrows.iterator();
		while (itr2.hasNext()) {
			Arrow arrow = (Arrow) itr2.next();
			arrow.update();
			if (arrow.isHit()) {
				itr2.remove();
			}
		}
		
		Iterator itr3 = bombs.iterator();
		while (itr3.hasNext()) {
			Bomb bomb = (Bomb) itr3.next();
			bomb.update();
			if (bomb.getExploded()) {
				itr3.remove();
			}
		}
		
		if(frameCounter%5 == 0)
			ghostNear();
		frameCounter++;
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 730, 360, 460, 120)) {
					resume();
				}

				if (inBounds(event, 730, 600, 460, 120)) {
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 1920, 1080)) {
					nullify();
					ShopScreen.setLevel(0);
					link.setAlive(true);
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		// Print background [grass + cakes]
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				int mod_i = 120 * i;
				int mod_j = 120 * j;

				switch (tilemap[i][j]) {
				case 0:
					g.drawImage(grass, mod_i, mod_j);
					break;
				case 1:
					g.drawImage(grass, mod_i, mod_j);
					g.drawImage(Assets.cake, mod_i, mod_j);
					break;

				default:
					g.drawImage(grass, mod_i, mod_j);
					break;

				}
			}
		}

		// Print Link
		int mod_i = 120 * link.getXpos();
		int mod_j = 120 * link.getYpos();
		//
		// Draw character
		switch (link.getState()) {
		case Right:
			if (link.isMoving) {
				w_right.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else {
				g.drawImage(a_right.getImage(), mod_i, mod_j);
			}
			break;

		case Left:
			if (link.isMoving) {
				w_left.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else {
				g.drawImage(a_left.getImage(), mod_i, mod_j);
			}
			break;

		case Up:
			if (link.isMoving) {
				w_up.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else {
				g.drawImage(Assets.up, mod_i + link.xbonus, mod_j + link.ybonus);
			}
			break;

		case Down:
			if (link.isMoving) {
				w_down.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else {
				g.drawImage(a_down.getImage(), mod_i, mod_j);
			}
			break;

		case SwordAttack:
			if (link.getOld_state() == State.Right) {
				s_right.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else if (link.getOld_state() == State.Left) {
				s_left.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else if (link.getOld_state() == State.Up) {
				s_up.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else {
				s_down.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			}
			break;
		}

		// Print Ghosts

		for (Ghost gst : ghosts) {

			mod_i = 120 * gst.getXpos();
			mod_j = 120 * gst.getYpos();

			switch (gst.getState()) {
			case Right:
				g.drawImage(ag_right.getImage(), mod_i + gst.xbonus, mod_j
						+ gst.ybonus);

				break;

			case Left:
				g.drawImage(ag_left.getImage(), gst.xbonus + mod_i, mod_j
						+ gst.ybonus);
				break;

			case Up:
				g.drawImage(ag_down.getImage(), gst.xbonus + mod_i, mod_j
						+ gst.ybonus);
				break;

			case Down:
				g.drawImage(ag_down.getImage(), gst.xbonus + mod_i, mod_j
						+ gst.ybonus);
				break;

			case Dead:
				g.drawImage(ag_dead.getImage(), mod_i, mod_j);
				break;

			case Invisible:
				// Derp, nothing here!
				break;
			}
		}

		for (Item itn : items) {

			mod_i = 120 * itn.getXpos();
			mod_j = 120 * itn.getYpos();

			switch (itn.getType()) {
			case Heart:
				g.drawImage(item_heart, mod_i, mod_j);
				break;

			case Bow:
				g.drawImage(item_bow, mod_i, mod_j);
				break;

			case Arrow:
				g.drawImage(item_arrow, mod_i, mod_j);
				break;
				
			case Diamond:
				g.drawImage(item_diamond, mod_i, mod_j);
				break;
				
			case Bronze:
				g.drawImage(item_bronze_coin, mod_i, mod_j);
				break;
				
			case Gold:
				g.drawImage(item_gold_coin, mod_i, mod_j);
				break;
			
			case Cake_Mix:
				g.drawImage(item_cake_mix, mod_i, mod_j);
				break;
				
			case Cake:
				g.drawImage(item_cake_item, mod_i, mod_j);
				break;
				
			case Bomb:
				g.drawImage(item_bomb, mod_i, mod_j);
				break;
				
			case Portal:
				g.drawImage(Assets.portal_b, mod_i, mod_j);
			break;
			}	
		}

		for (Arrow arrow : arrows) {
			mod_i = 120 * arrow.getXpos();
			mod_j = 120 * arrow.getYpos();
			g.drawImage(shuriken, mod_i + arrow.getXbonus(),
					mod_j + arrow.getYbonus());
		}
		
		for (Bomb bomb : bombs) {
			mod_i = 120 * bomb.getXpos();
			mod_j = 120 * bomb.getYpos();
			g.drawImage(placed_bomb, mod_i, mod_j);
		}

		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running){
			drawRunningUI();
		if (nearGhost == true)
			drawDanger();
		}
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
		

	}

	public static SpriteSheet getSword() {
		return s_down;
	}

	public void setSword(SpriteSheet swordM) {
		s_down = swordM;
	}

	public void animate() {

		if (link.isMoving && !(link.getState() == State.SwordAttack)) {
			w_left.update(40);
			w_right.update(40);
			w_down.update(40);
			w_up.update(40);
		} else if (link.getState() == State.SwordAttack) {
			s_down.update(30);
			s_right.update(40);
			s_left.update(30);
			s_up.update(30);
		} else {
			a_down.update(40);
			a_left.update(40);
			a_right.update(40);
		}
		for (Ghost gst : ghosts) {
			if (gst.isAlive) {
				ag_right.update(25);
				ag_left.update(25);
				ag_down.update(25);
			} else {
				ag_dead.update(40);
			}

		}
		Iterator<Ghost> iterator = ghosts.iterator();
		while (iterator.hasNext()) {
			Ghost gts = iterator.next();
			if (!gts.isAlive && ag_dead.getCurrentFrame() == 10) {
				tilemap[gts.xpos][gts.ypos] = 0;
				ag_dead.update(1);
				iterator.remove();
				dropItem(gts.getXpos(), gts.getYpos());
				ag_dead.setCurrentFrame(0);
			}
		}
	}

	private static void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		up = null;
		down = null;
		left = null;
		right = null;
		g_up = null;
		g_down = null;
		g_left = null;
		g_right = null;
		grass = null;
		cake = null;

		// Call garbage collector to clean up memory.
		System.gc();

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 400, 240, paint);

	}

	private void drawRunningUI() {
		int offset = 20;
		Graphics g = game.getGraphics();
		for (int i = 0; i < link.getLife(); i++) {
			g.drawImage(Assets.heart, offset, 20);
			offset += heart.getWidth() + 10;
		}
		g.drawImage(Assets.item_GUI, 1093, 10);
		g.drawString(String.valueOf(link.getArrows()), 1850, 50, paint);
		g.drawString(String.valueOf(link.getMoney()), 1390, 50, paint);
		g.drawString(String.valueOf(link.getBombs()), 1540, 50, paint);
		g.drawString(String.valueOf(link.getCakes()), 1710, 50, paint);
		g.drawString(String.valueOf(link.getPoints()), 1230, 50, paint);
		g.drawImage(Assets.dpad, 70, 630);
		g.drawImage(Assets.buttons, 1440, 630);
		
		if (!link.isWithBow()) {
			g.drawImage(Assets.crossout_GUI, 1731, 34);
		}
		if (!link.getWithCakeMix()) {
			g.drawImage(Assets.crossout_GUI, 1567, 34);
		}
		
		//for the 3 methods below need to add the drawImage for which weapon were using
		if (link.getUsingArrows()) {
			
		}
		if (link.getUsingBombs()) {
			
		}
		if (link.getUsingCakes()) {
			
		}

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 960, 420, paint2);
		g.drawString("Menu", 960, 660, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.gameover, 0, 0);

	}
	
	private void drawDanger() {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.exclamation, (link.getXpos()*120)+ 43+ link.xbonus, (link.getYpos()*120)-42 + link.ybonus);
	}
	
	private void drawTest() {
		Graphics g = game.getGraphics();
		g.drawARGB(100, 0, 255, 0);
	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		if (state == GameState.Running)
			pause();
		else if (state == GameState.Paused)
			android.os.Process.killProcess(android.os.Process.myPid());
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public void createTilemap() {
		int freq = 5;

		tilemap = new int[rows][columns];

		rows = tilemap.length;
		columns = tilemap[1].length;

		Random r = new Random();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i == 0 || i == rows-1 || j == 0 || j == columns -1){
					tilemap[i][j] = 1;
				}
				else if (r.nextInt(5) == 0) {
					tilemap[i][j] = 1;
				} else {
					tilemap[i][j] = 0;
				}
			}
		}
	}

	public static int getRows() {
		return rows;
	}

	public static void setRows(int rows) {
		GameScreen.rows = rows;
	}

	public static int getColumns() {
		return columns;
	}

	public static void setColumns(int columns) {
		GameScreen.columns = columns;
	}

	public SpriteSheet getW_left() {
		return w_left;
	}

	public void setW_left(SpriteSheet w_left) {
		this.w_left = w_left;
	}

	public void cleanCurrentFrames() {
		w_left.setCurrentFrame(0);

	}

	public void atackGhost() {
		for (Ghost gst : ghosts) {

			switch (link.state) {

			case Down:
				if (link.xpos == gst.xpos && (link.ypos + 1) == gst.ypos) {
					gst.kill();
				}
				break;

			case Up:
				if (link.xpos == gst.xpos && (link.ypos - 1) == gst.ypos) {
					gst.kill();
				}
				break;

			case Right:
				if ((link.xpos + 1) == gst.xpos && link.ypos == gst.ypos) {
					gst.kill();
				}
				break;

			case Left:
				if ((link.xpos - 1) == gst.xpos && link.ypos == gst.ypos) {
					gst.kill();
				}
				break;

			}
		}
	}

	public static void cleanFrames() {
		ag_dead.update(50);
		ag_dead.setCurrentFrame(0);
	}

	public static Character getLink() {
		return link;
	}

	public void setLink(Character link) {
		this.link = link;
	}

	public void addGhost() {
		Ghost generated = new Ghost();
		ghosts.add(generated);
	}

	public void dropItem(int x, int y) {
		Item item = new Item(x, y);
		items.add(item);
	}

	public static SpriteSheet getS_left() {
		return s_left;
	}

	public static void setS_left(SpriteSheet s_left) {
		GameScreen.s_left = s_left;
	}

	public static SpriteSheet getS_up() {
		return s_up;
	}

	public static void setS_up(SpriteSheet s_up) {
		GameScreen.s_up = s_up;
	}

	public static void goToMerchant() {
		game.setScreen(new ShopScreen(game));
		nullify();
		
	}

	
	public void ghostNear(){
		for(int i = link.getXpos()-1; i <= link.getXpos()+1; i++){
			for(int j = link.getYpos()-1; j <= link.getYpos()+1; j++){
				if(tilemap[i][j] == 3){
					nearGhost = true;
					return;
				}
			}
		}
		nearGhost = false;
	}
}
