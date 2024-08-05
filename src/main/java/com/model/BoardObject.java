package com.model;

import java.awt.*;

/**
 * This abstract class is responsible for defining the attributes and
 * methods for all objects on the board, i.e.- the snake, walls and
 * food items.
 *
 * @author Ronak Mahesh - Modified
 * @see com.view.Wall
 * @see com.view.Food
 */
public abstract class BoardObject
{
    //Member Attributes:-
    private int x;
    private int y;
    private Image i;
    private int w;
    private int h;

    //Describes if a board object has been collided with/is still on the board
    private boolean l;

    /**
     * Getter method for a board object's x coordinate
     * @return An integer, x, that describes the current x coordinate of any
     * board object
     */
    public int getX()
    {
        return this.x;
    }

    /**
     * Setter method for a board object's x coordinate
     * @param x An integer that the board object's current x coordinate is
     *          changed to
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Getter method for a board object's y coordinate
     * @return An integer, y, that describes the current y coordinate of any
     * board object
     */
    public int getY()
    {
        return this.y;
    }

    /**
     * Setter method for a board object's y coordinate
     * @param y An integer that the board object's current y coordinate is
     *          changed to
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Getter method for a board object's image reference
     * @return A java.awt Image, i, that corresponds to a board object's
     * visual representation
     */
    public Image getI()
    {
        return this.i;
    }

    /**
     * Setter method for a board object's image reference
     * @param i A java.awt Image object that the board object's visual
     *          representation is changed to
     */
    public void setI(Image i)
    {
        this.i = i;
    }

    /**
     * Getter method for a board object's width
     * @return An integer, w, that describes the width of a board object
     */
    public int getW()
    {
        return this.w;
    }

    /**
     * Setter method for a board object's width
     * @param w An integer that the board object's current width is changed to
     */
    public void setW(int w)
    {
        this.w = w;
    }

    /**
     * Getter method for a board object's height
     * @return An integer, h, that describes the height of a board object
     */
    public int getH()
    {
        return this.h;
    }

    /**
     * Setter method for a board object's height
     * @param h An integer that the board object's current height is changed to
     */
    public void setH(int h)
    {
        this.h = h;
    }

    /**
     * Getter method for a board object's current "life status"
     * @return A boolean, l, that describes if the board object is "alive"
     * or not (i.e- if it has been collided with or gone out of bounds)
     */
    public boolean getL()
    {
        return this.l;
    }

    /**
     * Setter method for a board object's "life status"
     * @param l A boolean that decides whether the board object is still
     *          "alive" (within bounds and not colliding with anything)
     */
    public void setL(boolean l)
    {
        this.l = l;
    }

    /**
     * @param g A graphics object
     */
    public abstract void draw(Graphics g);

    /**
     * A method that returns a rectangle around the board object's current
     * position
     * @return A rectangle object
     */
    public Rectangle getRectangle()
    {
        return new Rectangle(getX(), getY(), getW(), getH());
    }
}
