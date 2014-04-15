package com.kilobolt.robotgame;

import java.util.Random;

public class Item {

	enum Type {
		Heart, Cake_Mix, Cake, Bow, Arrow, Bomb, Bronze, Gold, Diamond, Nothing
	}

	private int xpos, ypos;
	private Type type;
	private boolean onGround;

	public Item() { // Create a random item at a random location.
		onGround = true;
		boolean isEmpty = false;
		int x, y;
		int[][] map = GameScreen.getTilemap();
		Random rdn = new Random();
		while (!isEmpty) { // Check if the place is empty
			x = rdn.nextInt(15);
			y = rdn.nextInt(8);
			if (map[x][y] == 0) {
				isEmpty = false;
			}
			xpos = x;
			ypos = y;
		}

		x = rdn.nextInt(100); // using same variable to get random type
		if(x < 35)
			type = Type.Bronze;
		else if(x >= 35 && x < 58)
			type = Type.Gold;
		else if(x >= 58 && x < 59)
			type = Type.Diamond;
		else if(x >= 59 && x < 64)
			type = Type.Nothing;
		else if(x >= 64 && x < 73)
			type = Type.Heart;
		else if(x >= 73 && x < 82)
			type = Type.Bomb;
		else if((x >= 82 && x < 91) && (!(GameScreen.getLink().isWithBow())))
			type = Type.Bow;
		else if((x >= 82 && x < 91) && (GameScreen.getLink().isWithBow()))
			type = Type.Arrow;
		else if((x >= 91 && x < 100) && (!(GameScreen.getLink().getWithCakeMix())))
			type = Type.Cake_Mix;
		else if((x >= 91 && x < 100) && (GameScreen.getLink().getWithCakeMix()))
			type = Type.Cake;
		
		

	}

	public Item(int x, int y) { // Create random item at position x, y
		xpos = x;
		ypos = y;
		onGround = true;

		Random rdn = new Random();

		x = rdn.nextInt(100); // using same variable to get random type
		if(x < 100)
		if(x < 35)
			type = Type.Bronze;
		else if(x >= 35 && x < 58)
			type = Type.Gold;
		else if(x >= 58 && x < 59)
			type = Type.Diamond;
		else if(x >= 59 && x < 64)
			type = Type.Nothing;
		else if(x >= 64 && x < 73)
			type = Type.Heart;
		else if(x >= 73 && x < 82)
			type = Type.Bomb;
		else if((x >= 82 && x < 91) && (!(GameScreen.getLink().isWithBow())))
			type = Type.Bow;
		else if((x >= 82 && x < 91) && (GameScreen.getLink().isWithBow()))
			type = Type.Arrow;
		else if((x >= 91 && x < 100) && (!(GameScreen.getLink().getWithCakeMix())))
			type = Type.Cake_Mix;
		else if((x >= 91 && x < 100) && (GameScreen.getLink().getWithCakeMix()))
			type = Type.Cake;
		
		
	}
	
	public void update() {
		if(GameScreen.getLink().getXpos() == this.xpos && GameScreen.getLink().getYpos() == this.ypos) {
			switch(type) {
			
			case Bronze:
				GameScreen.getLink().addMoney(2);
				break;
			
			case Gold:
				GameScreen.getLink().addMoney(4);
				break;
		
			case Diamond:
				GameScreen.getLink().addMoney(15);
				break;
				
			case Nothing:
				break;
			
			case Heart:
				GameScreen.getLink().setLife(GameScreen.getLink().getLife() +1);
				break;
				
			case Bomb:
				break;
				
			case Bow:
				GameScreen.getLink().setWithBow(true);
				break;
				
			case Arrow:
				GameScreen.getLink().setArrows(GameScreen.getLink().getArrows() +1);
				break;
				
			case Cake_Mix:
				GameScreen.getLink().setWithCakeMix(true);
				break;
				
			case Cake:
				break;
			}
			onGround = false;
		}
	}

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}


}
