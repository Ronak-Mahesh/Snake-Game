package com.view;

import com.model.BoardObject;
import com.controller.Snake;
import com.model.ImageUtil;

import java.awt.*;
import java.util.Objects;

/**
 * This class is responsible for creating a new Wall object with the
 * specified color on the board. It is also responsible for checking its own
 * state so that the game is ended when the snake has collided with the wall.
 *
 * @author Ronak Mahesh
 * @see com.model.BoardObject
 */
public class Wall extends BoardObject
{
    //Constants:-
    private static final int ADJUST_WIDTH = 40;
    private static final int ADJUST_HEIGHT = 10;
    private static final int BOARD_WIDTH = 870;
    private static final int BOARD_HEIGHT = 560;
    private static final int MIN_NUM = 1;
    private static final int MAX_NUM = 100;
    private static final int EVEN = 2;
    private static final int WALL_SIZE = 50;

    //Attributes:-
    private int random_int;
    private String colour;

    /**
     * Getter method for the random_int attribute
     * @return An integer, random_int, that if even, creates a vertical wall,
     * and creates a horizontal wall otherwise
     */
    public int getRandInt()
    {
        return this.random_int;
    }

    /**
     * Setter method for the random_int attribute
     * @param random_int An integer that the random_int attribute is set to
     */
    public void setRandInt(int random_int)
    {
        this.random_int = random_int;
    }

    /**
     * Getter method for the wall's colour attribute
     * @return A string, colour, that corresponds to the colour of the wall
     * to be used in the game
     */
    public String getColour()
    {
        return this.colour;
    }

    /**
     * Setter method for the wall's colour attribute
     * @param colour The colour that the wall's colour attribute is set to
     */
    public void setColour(String colour)
    {
        this.colour = colour;
    }

    /**
     * Public constructor for the wall class that initializes the wall to
     * have a particular orientation and a particular colour
     * @param colour The colour that the wall will be set to
     */
    public Wall(String colour)
    {
        this.setColour(colour);
        setRandInt((int)Math.floor(Math.random()*(MAX_NUM-MIN_NUM+1)+MIN_NUM));
        this.setL(true);

        // Checks if random_int is even or odd
        // Even: Vertical Wall
        // Odd: Horizontal Wall
        if(getRandInt()%EVEN == 0)
        {
            if(Objects.equals(this.getColour(), "Black"))
                this.setI(ImageUtil.images.get("wall"));
            else
                this.setI(ImageUtil.images.get("wall-white"));
        }
        else
        {
            if (Objects.equals(this.getColour(), "Black"))
                this.setI(ImageUtil.images.get("rotated"));
            else
                this.setI(ImageUtil.images.get("rotated-white"));
        }

        this.setW(WALL_SIZE);
        this.setH(WALL_SIZE);

        // Set random position for wall on the game board
        this.setX((int) (Math.random() * (BOARD_WIDTH - this.getW() +
                ADJUST_HEIGHT)));
        this.setY((int) (Math.random() * (BOARD_HEIGHT - this.getH() -
                ADJUST_WIDTH)));

        //Test to ensure that wall doesn't spawn in front of snake at the start
        while(this.getY() > 100 && this.getY() <=150)
            this.setY((int) (Math.random() * (BOARD_HEIGHT - this.getH() -
                    ADJUST_WIDTH)));
    }

    /**
     * A method that returns a rectangle around the wall's current position
     * @return A rectangle object
     */
    protected Rectangle getPos()
    {
        // Returns a different rectangle depending on if the wall is oriented
        // vertically or horizontally
        if(random_int%2 == 0)
            return new Rectangle(getX(), getY(), getW()-ADJUST_WIDTH,
                    getH()-ADJUST_HEIGHT);
        else
            return new Rectangle(getX(), getY(), getW()-ADJUST_HEIGHT,
                    getH()-ADJUST_WIDTH);

    }

    /**
     * Method to check if the snake has collided with the wall
     * @param mySnake The snake for which the method checks if it is has
     *                collided with the wall
     */
    protected void collided(Snake mySnake)
    {
        if(mySnake.getRectangle().intersects(this.getPos()))
            this.setL(false);
    }

    /**
     * Method to draw the wall on the game board
     * @param g A graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        g.drawImage(this.getI(), this.getX(), this.getY(), null);
    }
}
