package com.kilobolt.robotgame;

import java.util.Iterator;
import java.util.Random;

public class Character {

	enum State {
		Down, Up, Right, Left, Dead, Invisible, SwordAttack
	}
	private static final int KILL_POINTS = 10;
	private boolean withBow;
	private boolean withCakeMix;
	protected int xpos, ypos;
	protected int xspeed, yspeed;
	protected int xbonus, ybonus;
	protected State state = State.Down;
	protected boolean isAlive = true;
	protected boolean isMoving = false;
	protected int linkSpeed;
	private int[][] maps = GameScreen.getTilemap();
	private int arrows;
	private int life;
	private State old_state = State.Down;
	private int money, points, bombs, cakes;

	public Character() {
		withBow = false;
		withCakeMix = false;
		arrows = 3;
		life = 3;
		linkSpeed = 8;
		money = 0;
		bombs = 0;
		cakes = 0;

		int[][] mapa = GameScreen.getTilemap();
		Random position = new Random();
		int x = position.nextInt(5);
		int y = position.nextInt(5);
		for (int i = x; i < GameScreen.getRows(); i++) {
			for (int j = y; j < GameScreen.getColumns(); j++) {
				if (mapa[i][j] != 1) {
					xpos = i;
					ypos = j;
					return;
				}
			}
		}
		return;
	}

	public void update() {
		xbonus += xspeed;
		ybonus += yspeed;

		if (xbonus <= -120) {

			xpos -= 1;
			xbonus = 0;
			xspeed = 0;
			this.isMoving = false;
		}

		if (xbonus >= 120) {
			xpos += 1;
			xbonus = 0;
			xspeed = 0;
			this.isMoving = false;
		}

		if (ybonus <= -120) {
			ypos -= 1;
			ybonus = 0;
			yspeed = 0;
			this.isMoving = false;
		}

		if (ybonus >= 120) {
			ypos += 1;
			ybonus = 0;
			yspeed = 0;
			this.isMoving = false;
		}

		if (state == State.SwordAttack) {
			if (GameScreen.getS_right().isOver() && old_state == State.Right) {
				this.isMoving = false;
				GameScreen.getS_right().setCurrentFrame(0);
				GameScreen.getS_down().setCurrentFrame(0);
				GameScreen.getS_left().setCurrentFrame(0);
				GameScreen.getS_up().setCurrentFrame(0);

				GameScreen.getS_right().setOver(false);
				GameScreen.getS_down().setOver(false);
				GameScreen.getS_left().setOver(false);
				GameScreen.getS_up().setOver(false);
				this.state = old_state;
			} else if (GameScreen.getS_down().isOver()
					&& old_state == State.Down) {
				this.isMoving = false;
				GameScreen.getS_right().setCurrentFrame(0);
				GameScreen.getS_down().setCurrentFrame(0);
				GameScreen.getS_left().setCurrentFrame(0);
				GameScreen.getS_up().setCurrentFrame(0);
				GameScreen.getS_right().setOver(false);
				GameScreen.getS_down().setOver(false);
				GameScreen.getS_left().setOver(false);
				GameScreen.getS_up().setOver(false);
				this.state = old_state;
			} else if (GameScreen.getS_left().isOver()
					&& old_state == State.Left) {
				this.isMoving = false;
				GameScreen.getS_right().setCurrentFrame(0);
				GameScreen.getS_down().setCurrentFrame(0);
				GameScreen.getS_left().setCurrentFrame(0);
				GameScreen.getS_up().setCurrentFrame(0);
				GameScreen.getS_right().setOver(false);
				GameScreen.getS_down().setOver(false);
				GameScreen.getS_left().setOver(false);
				GameScreen.getS_up().setOver(false);
				this.state = old_state;
			} else if (GameScreen.getS_up().isOver() && old_state == State.Up) {
				this.isMoving = false;
				GameScreen.getS_right().setCurrentFrame(0);
				GameScreen.getS_down().setCurrentFrame(0);
				GameScreen.getS_left().setCurrentFrame(0);
				GameScreen.getS_up().setCurrentFrame(0);
				GameScreen.getS_right().setOver(false);
				GameScreen.getS_down().setOver(false);
				GameScreen.getS_left().setOver(false);
				GameScreen.getS_up().setOver(false);
				this.state = old_state;
			}
		}
	}

	public int getBombs() {
		return bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}
	public void addBombs(int amount) {
		this.bombs += amount;
	}

	public int getCakes() {
		return cakes;
	}

	public void setCakes(int cakes) {
		this.cakes = cakes;
	}
	
	public void addCakes(int amount) {
		this.cakes += amount;
	}

	public void moveRight() {
		if (!isMoving && isAlive && state == State.Right
				&& xpos < (GameScreen.getRows() - 1)) {
			int[][] mapa = GameScreen.getTilemap();
			if (mapa[xpos + 1][ypos] == 0) {
				xspeed = +linkSpeed;
				this.isMoving = true;
			}
		} else if (isAlive && !isMoving) {
			state = State.Right;
		}
	}

	public void moveLeft() {
		if (!isMoving && isAlive && state == State.Left && xpos > 0) {
			int[][] mapa = GameScreen.getTilemap();
			if (mapa[xpos - 1][ypos] == 0) {
				xspeed = -linkSpeed;
				this.isMoving = true;
			}
		} else if (isAlive && !isMoving) {
			state = State.Left;
		}
	}

	public void moveUp() {
		if (!isMoving && isAlive && state == State.Up && ypos > 0) {
			int[][] mapa = GameScreen.getTilemap();
			if (mapa[xpos][ypos - 1] == 0) {
				yspeed -= linkSpeed;
				this.isMoving = true;
			}
		} else if (isAlive && !isMoving) {
			state = State.Up;
		}
	}

	public void moveDown() {
		if (!isMoving && isAlive && state == State.Down
				&& ypos < (GameScreen.getColumns() - 1)) {
			int[][] mapa = GameScreen.getTilemap();
			if (mapa[xpos][ypos + 1] == 0) {
				yspeed += linkSpeed;
				this.isMoving = true;
			}
		} else if (isAlive && !isMoving) {
			state = State.Down;
		}
	}

	public void atack() {
		if (!this.isMoving && !(this.state == State.SwordAttack)) {
			this.isMoving = true;
			Iterator<Ghost> its = GameScreen.getGhosts().iterator();
			while (its.hasNext()) {
				Ghost gts = its.next();
				switch (state) {

				case Up:
					old_state = State.Up;
					if (this.xpos == gts.xpos && (this.ypos - 1) == gts.ypos) {
						gts.kill();
						points += KILL_POINTS;
					}
					break;

				case Down:
					old_state = State.Down;
					if (this.xpos == gts.xpos && (this.ypos + 1) == gts.ypos) {
						gts.kill();
						points += KILL_POINTS;
					}
					break;

				case Left:
					old_state = State.Left;
					if ((this.xpos - 1) == gts.xpos && this.ypos == gts.ypos) {
						gts.kill();
						points += KILL_POINTS;
					}
					break;

				case Right:
					old_state = State.Right;
					if ((this.xpos + 1) == gts.xpos && this.ypos == gts.ypos) {
						gts.kill();
						points += KILL_POINTS;
					}
					break;

				default:
					this.isMoving = false;
					break;

				}
			}
		}
		this.state = State.SwordAttack;
	}

	public Arrow shoot() {
		//Can only attack with arrows and if have Bow
		if (arrows > 0 && withBow) {
			arrows -= 1;
			Arrow ar1;
			int arrow_speed = 15;
			switch (state) {
			case Down:
				ar1 = new Arrow(this.xpos, this.ypos + 1);
				ar1.setXspeed(0);
				ar1.setYspeed(arrow_speed);
				ar1.setYbonus(-60);
				return ar1;
			case Up:
				ar1 = new Arrow(this.xpos, this.ypos - 1);
				ar1.setXspeed(0);
				ar1.setYspeed(-arrow_speed);
				ar1.setYbonus(60);
				return ar1;

			case Left:
				ar1 = new Arrow(this.xpos - 1, this.ypos);
				ar1.setXspeed(-arrow_speed);
				ar1.setYspeed(0);
				ar1.setXbonus(60);
				return ar1;

			case Right:
				ar1 = new Arrow(this.xpos + 1, this.ypos);
				ar1.setXspeed(arrow_speed);
				ar1.setYspeed(0);
				ar1.setXbonus(-60);
				return ar1;

			default:
				ar1 = new Arrow(this.xpos + 1, this.ypos);
				ar1.setHit(true);
				// Since I am returning an arrow, if the character is not
				// standing,
				// returns an arrow "already hit"
				// ADdasda
				return ar1;
			}
		}
		Arrow ar1 = new Arrow(this.xpos + 1, this.ypos);
		ar1.setHit(true);
		// Since I am returning an arrow, if the character is not
		// standing,
		// returns an arrow "already hit"
		// ADdasda
		return ar1;
	}

	public int getXpos() {
		return xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public void addLife(int amount) {
		this.life += amount;
	}

	public void kill() {
		this.life -= 1;
		if (this.life == 0) {
			this.isAlive = false;
		}

	}

	public void spendMoney(int amount) {
		this.money -= amount;
	}

	public int getMoney() {
		return money;
	}

	public void addMoney(int amount) {
		this.money += amount;
	}

	public State getOld_state() {
		return old_state;
	}

	public void setOld_state(State old_state) {
		this.old_state = old_state;
	}

	public int getArrows() {
		return arrows;
	}

	public void setArrows(int arrows) {
		this.arrows = arrows;
	}

	public boolean isWithBow() {
		return withBow;
	}

	public void setWithBow(boolean withBow) {
		this.withBow = withBow;
	}
	
	public boolean getWithCakeMix() {
		return withCakeMix;
	}
	
	public void setWithCakeMix(boolean withCakeMix) {
		this.withCakeMix = withCakeMix;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	public void addPoints(int amount) {
		this.points += amount;
	}
}
