package com.kilobolt.robotgame;

import java.util.Iterator;
import java.util.Random;

import com.kilobolt.robotgame.Character.State;

public class Ghost extends Character {

	private int count_to_move;
	private int movCheck;

	public Ghost() {
		this.linkSpeed = 7 + ShopScreen.getLevel() + Assets.option_screen_difficulty * 2;
		int[][] mapa = GameScreen.getTilemap();
		Random position = new Random();
		int x;
		int y;
		int checkSpawn = 0;
		
		while (checkSpawn == 0){
			x = position.nextInt(14)+1;
			y = position.nextInt(7)+1;
			if(mapa[x][y] == 0){
				if(!(x == GameScreen.getLink().getXpos() && y == GameScreen.getLink().getYpos())) {
					this.xpos = x;
					this.ypos = y;
					//mapa[x][y] = 3;
					checkSpawn = 1;
					return;
				}
			}
			
		}
		
//		int x = position.nextInt(7);
//		int y = position.nextInt(7);
//		for (int i = x; i < GameScreen.getRows(); i++) {
//			for (int j = y; j < GameScreen.getColumns(); j++) {
//				if (mapa[i][j] != 1) {
//					this.xpos = i;
//					ypos = j;
//					return;
//				}
//			}
//		}
		return;
	}
	
	@Override
	public void moveRight() {
		int movCheck = 0;
		if (!isMoving && isAlive && state == State.Right
				&& xpos < (GameScreen.getRows() - 1)) {
			int[][] mapa = GameScreen.getTilemap();
			if (mapa[xpos + 1][ypos] == 0 &&
			   (!(xpos + 1 == GameScreen.getLink().getXpos() && ypos == GameScreen.getLink().getYpos()))) {
				for(Ghost gst: GameScreen.getGhosts()){
					if(xpos + 1 == gst.getXpos() && ypos == gst.getYpos()) {
						movCheck = 1;
					}
				}
				if(movCheck == 0) {
					mapa[xpos + 1][ypos] = 3;
					xspeed = +linkSpeed;
					this.isMoving = true;
				}
			}
		} else if (isAlive && !isMoving) {
			state = State.Right;
		}
	}

	@Override
	public void moveLeft() {
		int movCheck = 0;
		if (!isMoving && isAlive && state == State.Left && xpos > 0) {
			int[][] mapa = GameScreen.getTilemap();
			if (mapa[xpos - 1][ypos] == 0 &&
					   (!(xpos - 1 == GameScreen.getLink().getXpos() && ypos == GameScreen.getLink().getYpos()))) {
						for(Ghost gst: GameScreen.getGhosts()){
							if(xpos - 1 == gst.getXpos() && ypos == gst.getYpos()) {
								movCheck = 1;
							}
						}
						if(movCheck == 0) {
							mapa[xpos - 1][ypos] = 3;
							xspeed = -linkSpeed;
							this.isMoving = true;
						}
					}
		} else if (isAlive && !isMoving) {
			state = State.Left;
		}
	}

	@Override
	public void moveUp() {	
		int movCheck = 0;
		if (!isMoving && isAlive && state == State.Up && ypos > 0) {
			int[][] mapa = GameScreen.getTilemap();
			if (mapa[xpos][ypos - 1] == 0 &&
					   (!(xpos == GameScreen.getLink().getXpos() && ypos - 1 == GameScreen.getLink().getYpos()))) {
						for(Ghost gst: GameScreen.getGhosts()){
							if(xpos == gst.getXpos() && ypos - 1 == gst.getYpos()) {
								movCheck = 1;
							}
						}
						if(movCheck == 0) {
							mapa[xpos][ypos - 1] = 3;
							yspeed = -linkSpeed;
							this.isMoving = true;
						}
					}
		} else if (isAlive && !isMoving) {
			state = State.Up;
		}
	}

	@Override
	public void moveDown() {
		int movCheck = 0;
		if (!isMoving && isAlive && state == State.Down
				&& ypos < (GameScreen.getColumns() - 1)) {
			int[][] mapa = GameScreen.getTilemap();
			if (mapa[xpos][ypos + 1] == 0 &&
					   (!(xpos == GameScreen.getLink().getXpos() && ypos + 1 == GameScreen.getLink().getYpos()))) {
						for(Ghost gst: GameScreen.getGhosts()){
							if(xpos == gst.getXpos() && ypos + 1 == gst.getYpos()) {
								movCheck = 1;
							}
						}
						if(movCheck == 0) {
							mapa[xpos][ypos + 1] = 3;
							yspeed = +linkSpeed;
							this.isMoving = true;
						}
					}
		} else if (isAlive && !isMoving) {
			state = State.Down;
		}
	}

	public void randomMovement() {

		if (isAlive && count_to_move == 18 - ShopScreen.getLevel() - Assets.option_screen_difficulty * 2) {
			move();
			count_to_move = 0;
		} else {
			count_to_move++;
		}
	}

	public void move() {
//		int [][] movmap = GameScreen.getMovingMap();
		Random dir = new Random();
		switch (dir.nextInt(7)) {
		case 0:
//			movmap[xpos][ypos+1] = 4;
//			movCheck = 1;
			this.moveDown();
			this.atack();
//			movmap[xpos][ypos+1] = 3;
//			movCheck = 0;
			break;

		case 1:
//			movmap[xpos][ypos-1] = 4;
			this.moveUp();
			this.atack();
//			movmap[xpos][ypos-1] = 3;
			break;

		case 2:
//			movmap[xpos-1][ypos] = 4;
			this.moveLeft();
			this.atack();
//			movmap[xpos-1][ypos] = 3;
			break;

		case 3:
//			movmap[xpos+1][ypos] = 4;
			this.moveRight();
			this.atack();
//			movmap[xpos+1][ypos] = 3;
			break;

		case 4:
			moveSameDirection();
			this.atack();
			break;

		case 5:
			moveSameDirection();
			this.atack();
			break;

		case 6:
			moveSameDirection();
			this.atack();
			break;

		}
	}

	public void moveSameDirection() {
//		int [][] movmap = GameScreen.getMovingMap();
		switch (state) {
		case Up:
//			movmap[xpos][ypos-1] = 3;
			this.moveUp();
			break;

		case Down:
//			movmap[xpos][ypos+1] = 3;
			this.moveDown();
			break;

		case Left:
//			movmap[xpos-1][ypos] = 3;
			this.moveLeft();
			break;

		case Right:
//			movmap[xpos+1][ypos] = 3;
			this.moveRight();
			break;
		}
	}

	public void kill() {
		GameScreen.cleanFrames();
		state = State.Dead;
		this.isAlive = false;
	}

	@Override
	public void atack() {
		
		switch (state) {

		case Up:
			if (this.xpos == GameScreen.getLink().xpos
					&& (this.ypos - 1) == GameScreen.getLink().ypos) {
				GameScreen.getLink().kill();
			}
			break;

		case Down:
			if (this.xpos == GameScreen.getLink().xpos
					&& (this.ypos + 1) == GameScreen.getLink().ypos) {
				GameScreen.getLink().kill();
			}
			break;

		case Left:
			if ((this.xpos - 1) == GameScreen.getLink().xpos
					&& this.ypos == GameScreen.getLink().ypos) {
				GameScreen.getLink().kill();
			}
			break;

		case Right:
			if ((this.xpos + 1) == GameScreen.getLink().xpos
					&& this.ypos == GameScreen.getLink().ypos) {
				GameScreen.getLink().kill();
			}
			break;

		default:
			break;

		}
	}
	


}
