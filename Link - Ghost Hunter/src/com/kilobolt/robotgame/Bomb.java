package com.kilobolt.robotgame;

import java.util.ArrayList;
import java.util.Iterator;

public class Bomb {

	private int xpos, ypos;
	private boolean placed;//says whether or not the bomb has been placed (means it hasnt exploded yet
	private boolean exploded;//says whether or not the bomb has exploded
	private int timeCounter;//measures how long until the bomb explodes. explodes when counter reaches 200
	private int numGhostsKilled;

	public Bomb(int x, int y) {
		xpos = x;
		ypos = y;
		placed = true;
		exploded = false;
		timeCounter = 0;
		numGhostsKilled = 0;
	}

	public void update() {
		int[][] map = GameScreen.getTilemap();
		if(placed == true){
			timeCounter++;
		}
		else
			timeCounter = 0;
		
//		if(timeCounter >= 200){
//			for(int i = xpos-1; i < xpos+2; i++){
//				for(int j = ypos-1; j < ypos+1; j++){
//					switch (map[i][j]) {
//					case 0://grass
//						exploded = true;
//						break;
//
//					case 1://cake
//						exploded = true;
//						break;
//
//					case 2://link
//						exploded = true;
//						break;
//					case 3:
//						killGhost();
//						GameScreen.getLink().setPoints(GameScreen.getLink().getPoints() + 5);
//						exploded = true;
//						break;
//					}
//				}
//			}		
//		}
		
		if(timeCounter >= 200){
			killGhost();
			GameScreen.getLink().setPoints(GameScreen.getLink().getPoints() + 5*numGhostsKilled);
			exploded = true;
			placed = false;
		}
	}

	


	private void killGhost() {
		Iterator<Ghost> its = GameScreen.getGhosts().iterator();
		while (its.hasNext()) {
			Ghost ghost = its.next();
			if (((ghost.getXpos() == this.xpos) || (ghost.getXpos() == this.xpos+1) || (ghost.getXpos() == this.xpos-1))
				&& ((ghost.getYpos() == this.ypos) || (ghost.getYpos() == this.ypos+1) || (ghost.getYpos() == this.ypos-1))) {
				ghost.kill();
				numGhostsKilled++;
			}
		}

	}
	
//	private void killGhost() {
//		Iterator<Ghost> its = GameScreen.getGhosts().iterator();
//		while (its.hasNext()) {
//			Ghost ghost = its.next();
//			if ((ghost.getXpos() == this.xpos)	&& (ghost.getYpos() == this.ypos)) {
//				ghost.kill();
//			}
//		}
//
//	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	
	public boolean getPlaced() {
		return placed;
	}
	
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	
	public boolean getExploded() {
		return exploded;
	}
	
	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	public int getTimeCounter() {
		return timeCounter;
	}

	public void setTimeCounter(int timeCounter) {
		this.timeCounter = timeCounter;
	}
	
	public int getNumGhostsKilled() {
		return numGhostsKilled;
	}
	
	public void setNumGhostsKilled(int numGhostsKilled) {
		this.numGhostsKilled = numGhostsKilled;
	}

}
