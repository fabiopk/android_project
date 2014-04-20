//package com.kilobolt.robotgame;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class Bomb {//.
//
//	private Timer t;
//	private boolean placed;
//	private int timeCounter = 0;
//	private int timer = 3;
//	private int xpos, ypos, ybonus, xbonus;
//
//	public Bomb(int x, int y) {
//		xpos = x;
//		ypos = y;
//		placed = false;
//	}
//
//	public void update() {
//		int[][] map = GameScreen.getTilemap();
//		boolean bombTimer;
//		
//	
//		if (xpos >= (GameScreen.getRows()) || ypos >= (GameScreen.getColumns()) || xpos < 0 || ypos <0) {
//
//		} else {
//
//			switch (map[xpos][ypos]) {
//			case 0:
//				// Do nothing
//				break;
//
//			case 1:
//				hit = true;
//				break;
//
//			case 2:
//				//fixed
//				break;
//			case 3:
//				killGhost();
//				GameScreen.getLink().setPoints(GameScreen.getLink().getPoints() + 5);
//				hit = true;
//				break;
//
//			}
//		}
//
//	}
//
//	
//
//
//	private void killGhost() {
//		Iterator<Ghost> its = GameScreen.getGhosts().iterator();
//		while (its.hasNext()) {
//			Ghost ghost = its.next();
//			if ((timer <= 0) && ((ghost.getXpos() == this.xpos) || (ghost.getXpos() == this.xpos+1) || (ghost.getXpos() == this.xpos-1))
//				&& ((ghost.getYpos() == this.ypos) || (ghost.getYpos() == this.ypos+1) || (ghost.getYpos() == this.ypos-1))) {
//				ghost.kill();
//			}
//		}
//
//	}
//
//	public int getXpos() {
//		return xpos;
//	}
//
//	public void setXpos(int xpos) {
//		this.xpos = xpos;
//	}
//
//	public int getYpos() {
//		return ypos;
//	}
//
//	public void setYpos(int ypos) {
//		this.ypos = ypos;
//	}
//
//	public int getTimer() {
//		return timer;
//	}
//
//	public void setTimer(int timer) {
//		this.timer = timer;
//	}
//
//}
