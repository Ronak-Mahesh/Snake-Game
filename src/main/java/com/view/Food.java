package com.view;

import java.awt.Graphics;
import java.util.Random;

import com.controller.*;
import com.model.*;

/**
 * This class is responsible for creating a new Food object on the
 * board. It is also responsible for checking its own state so that it is
 * taken off the board when it has been eaten.
 *
 * @author Ronak Mahesh - Modified
 * @see com.model.BoardObject
 * @see com.view.Edible
 */
public class Food extends BoardObject implements Edible
{
	//Constants:-
	private static final int ADJUST_WIDTH = 10;
	private static final int UPPER_BOUND = 10;
	private static final int ADJUST_HEIGHT = 40;
	private static final int BOARD_WIDTH = 870;
	private static final int BOARD_HEIGHT = 560;
	private static final int ADD_SCORE = 521;

	/**
	 * Public constructor that initializes every new Food object
	 */
	public Food()
	{
		//The food should be put on the board
		this.setL(true);

		//Get random food image from ImageUtil hashmap
		this.setI(ImageUtil.images.get(String.valueOf(new Random().nextInt(
				UPPER_BOUND))));


		//Set height and width for the food
		this.setW(this.getI().getWidth(null));
		this.setH(this.getI().getHeight(null));


		//Set a random pair of coordinates for the food's position
		this.setX(
				(int) (Math.random() *
						(BOARD_WIDTH - this.getW() + ADJUST_WIDTH)));
		this.setY(
				(int) (Math.random() *
						(BOARD_HEIGHT - this.getH() - ADJUST_HEIGHT)));
	}

	/**
	 * Method to draw the food on the board
	 * @param g A graphics object
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(this.getI(), this.getX(), this.getY(), null);
	}

	/**
	 * A method to check if the food has been eaten or not
	 * @param mySnake The snake that we're checking has collided with the food
	 */
	@Override
	public void eaten(Snake mySnake)
	{
		//If food and snake intersect one another, food has been eaten
		if (mySnake.getRectangle()
				.intersects(this.getRectangle())
				&& getL()
				&& mySnake.getL())
		{
			this.setL(false); //Remove food from board
			mySnake.setLength(mySnake.getLength() + 1); //Increment snake length
			mySnake.setScore(mySnake.getScore()+ADD_SCORE); //Increment score
		}
	}
}
