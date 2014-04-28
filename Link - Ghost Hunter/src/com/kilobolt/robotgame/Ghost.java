package com.kilobolt.robotgame;

import java.util.Iterator;
import java.util.Random;

import com.kilobolt.robotgame.Character.State;

public class Ghost extends Character {

	private int count_to_move;

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
					checkSpawn = 1;
					return;
				}
			}
			
		}
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
		Random dir = new Random();
		switch (dir.nextInt(7)) {
		case 0:
			this.moveDown();
			this.atack();
			break;

		case 1:
			this.moveUp();
			this.atack();
			break;

		case 2:
			this.moveLeft();
			this.atack();
			break;

		case 3:
			this.moveRight();
			this.atack();
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
		switch (state) {
		case Up:
			this.moveUp();
			break;

		case Down:
			this.moveDown();
			break;

		case Left:
			this.moveLeft();
			break;

		case Right:
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
			if ((GameScreen.getLink().getHurtCheck() == 0) && (this.xpos == GameScreen.getLink().xpos
					&& (this.ypos - 1) == GameScreen.getLink().ypos)) {
				GameScreen.getLink().kill();
				GameScreen.getLink().setHurtCheck(1);
			}
			break;

		case Down:
			if ((GameScreen.getLink().getHurtCheck() == 0) && (this.xpos == GameScreen.getLink().xpos
					&& (this.ypos + 1) == GameScreen.getLink().ypos)) {
				GameScreen.getLink().kill();
				GameScreen.getLink().setHurtCheck(1);
			}
			break;

		case Left:
			if ((GameScreen.getLink().getHurtCheck() == 0) && ((this.xpos - 1) == GameScreen.getLink().xpos
					&& this.ypos == GameScreen.getLink().ypos)) {
				GameScreen.getLink().kill();
				GameScreen.getLink().setHurtCheck(1);
			}
			break;

		case Right:
			if ((GameScreen.getLink().getHurtCheck() == 0) && ((this.xpos + 1) == GameScreen.getLink().xpos
					&& this.ypos == GameScreen.getLink().ypos)) {
				GameScreen.getLink().kill();
				GameScreen.getLink().setHurtCheck(1);
			}
			break;

		default:
			break;

		}
	}
	


}
