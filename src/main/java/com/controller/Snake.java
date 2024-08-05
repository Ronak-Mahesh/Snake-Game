package com.controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.model.*;

/**
 * This class is responsible for defining the attributes and methods
 * that the Snake that the player controls. Implements move() from the Movable
 * interface, and keyPressed() from KeyListener
 *
 * @author Ronak Mahesh - Modified
 * @see com.view.GameView
 * @see com.controller.Movable
 */

public class Snake extends BoardObject implements Movable, KeyListener
{
    //Constants:-
    private static final int LEFT = -180;
    private static final int UP = -90;
    private static final int DOWN = 90;
    private static final int SPEED = 5;
    private static final int MAX_X = 870;
    private static final int MAX_Y = 560;

    //Member Attributes:-
    private int speed_XY;
    private int length;

    /**
     * How many movements are needed to cross one section of the snake's body
     */
    private int num;
    private int score;
    private boolean up, down, left, right = true;
    private static List<Point> bodyPoints;
    private static BufferedImage newImgSnakeHead;
    private static BufferedImage IMG_SNAKE_HEAD;

    /**
     * Getter method for the snake's speed attribute
     * @return An integer, speed_XY, which is the current speed that the
     * Snake is moving at
     */
    public int getSpeed()
    {
        return this.speed_XY;
    }

    /**
     * Setter method for the snake's speed attribute
     * @param speed_XY An integer value that the snake's speed is changed to
     */
    public void setSpeed(int speed_XY)
    {
        this.speed_XY = speed_XY;
    }

    /**
     * Getter method for the snake's num attribute
     * @return An integer, num, which describes how many movements are needed
     * to cross one section of the snake's body
     */
    public int getNum()
    {
        return this.num;
    }

    /**
     * Setter method for the snake's speed attribute
     * @param num An integer value that the snake's "num" attribute is set to.
     *            Describes how many movements are needed to cross one section
     *            of the snake's body
     */
    public void setNum(int num)
    {
        this.num = num;
    }

    /**
     * Getter method for the snake's score attribute
     * @return An integer, score, which is the current score
     */
    public int getScore()
    {
        return this.score;
    }

    /**
     * Setter method for the snake's score attribute
     * @param score An integer value that the snake's score is changed to
     */
    public void setScore(int score)
    {
        this.score = score;
    }

    /**
     * Getter method for the snake's length attribute
     * @return An integer, length, which describes how long (in terms of body
     * units) the snake is
     */
    public int getLength()
    {
        return this.length;
    }

    /**
     * Setter method for the snake's speed attribute
     * @param length An integer value that the snake's length is changed to
     */
    public void setLength(int length)
    {
        this.length = length;
    }

    /**
     * Getter method for the snake's Up attribute
     * @return A boolean, up, that says whether the Snake is currently moving
     * upwards or not
     */
    public boolean isUp()
    {
        return this.up;
    }

    /**
     * Setter method for the snake's Up attribute
     * @param up A boolean that decides whether the Snake will move in
     *           the upwards direction or not
     */
    public void setUp(boolean up)
    {
        this.up = up;
    }

    /**
     * Getter method for the snake's Down attribute
     * @return A boolean, down, that says whether the Snake is currently moving
     * downwards or not
     */
    public boolean isDown()
    {
        return this.down;
    }

    /**
     * Setter method for the snake's Down attribute
     * @param down A boolean that decides whether the Snake will move in
     *           the downwards direction or not
     */
    public void setDown(boolean down)
    {
        this.down = down;
    }

    /**
     * Getter method for the snake's Left attribute
     * @return A boolean, left, that says whether the Snake is currently moving
     * towards the left of the board or not
     */
    public boolean isLeft()
    {
        return left;
    }

    /**
     * Setter method for the snake's Left attribute
     * @param left A boolean that decides whether the Snake will move towards
     *             the left or not
     */
    public void setLeft(boolean left)
    {
        this.left = left;
    }

    /**
     * Getter method for the snake's Right attribute
     * @return A boolean, right, that says whether the Snake is currently moving
     * towards the right of the board or not
     */
    public boolean isRight()
    {
        return this.right;
    }

    /**
     * Setter method for the snake's Right attribute
     * @param right A boolean that decides whether the Snake will move
     *             towards the right or not
     */
    public void setRight(boolean right)
    {
        this.right = right;
    }

    /**
     * Constructor for the Snake class
     * @param x The starting X-Coordinate for the snake
     * @param y The starting Y-Coordinate for the snake
     * @param colour The colour that the snake head and body should be set to
     *              (depends on choice of theme)
     * @see com.view.GameView More information on theme and snake colour
     * choices
     */
    public Snake(int x, int y, String colour)
    {
        this.setL(true); //Snake is alive
        this.setX(x);
        this.setY(y);
        bodyPoints = new LinkedList<>(); //To store the body of the snake
        if(Objects.equals(colour, "Green"))
        {
            IMG_SNAKE_HEAD = (BufferedImage)
                    ImageUtil.images.get("snake-head-right");

            this.setI(ImageUtil.images.get("snake-body"));
        }
        else
        {
            IMG_SNAKE_HEAD = (BufferedImage)
                    ImageUtil.images.get("snake-head-white");

            this.setI(ImageUtil.images.get("snake-white"));
        }
        this.setW(this.getI().getWidth(null));
        this.setH((this.getI().getHeight(null)));
        this.setSpeed(SPEED);
        this.setLength(1);

        //The time (number of movements) it takes for the whole snake to move
        // a space
        this.setNum(getW() / getSpeed());
        this.setScore(0);
        newImgSnakeHead = IMG_SNAKE_HEAD;
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e)
    {
        // TODO
    }

    /**
     * Changes the Snake's direction of movement based on what key is pressed
     * @param e the event to be processed
     */
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            //Rotate snake's head to point upwards
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (!isDown()) //Cannot flip direction by 180 degrees
                {
                    setUp(true);
                    setDown(false);
                    setLeft(false);
                    setRight(false);

                    newImgSnakeHead = (BufferedImage)
                            ImageUtil.rotateImage(IMG_SNAKE_HEAD, UP);
                }
                break;

            //Rotate snake's head to point downwards
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (!isUp()) //Cannot flip direction by 180 degrees
                {
                    setUp(false);
                    setDown(true);
                    setLeft(false);
                    setRight(false);

                    newImgSnakeHead = (BufferedImage)
                            ImageUtil.rotateImage(IMG_SNAKE_HEAD, DOWN);
                }
                break;

            //Rotate snake's head to point towards the left
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (!isRight()) //Cannot flip direction by 180 degrees
                {
                    setUp(false);
                    setDown(false);
                    setLeft(true);
                    setRight(false);

                    newImgSnakeHead = (BufferedImage)
                            ImageUtil.rotateImage(IMG_SNAKE_HEAD, LEFT);
                }
                break;

            //Rotate snake's head to point towards the right
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (!isLeft()) //Cannot flip direction by 180 degrees
                {
                    setUp(false);
                    setDown(false);
                    setLeft(false);
                    setRight(true);

                    newImgSnakeHead = IMG_SNAKE_HEAD;
                }

            default:
                break;
        }
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
    }


    /**
     * Method to manipulate the snake's direction of movement based on which
     * of its boolean directions are set
     */
    public void move()
    {
        if (isUp())
        {
            setY(getY() - getSpeed());
        }
        else if (isDown())
        {
            setY(getY() + getSpeed());
        }
        else if (isLeft())
        {
            setX(getX() - getSpeed());
        }
        else if (isRight())
        {
            setX(getX() + getSpeed());
        }
    }

    /**
     * Method to draw the snake's head
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        outOfBounds();
        eatBody();

        bodyPoints.add(new Point(getX(), getY()));

        if (bodyPoints.size() == (this.getLength() + 1) * getNum())
        {
            bodyPoints.remove(0);
        }
        g.drawImage(newImgSnakeHead, getX(), getY(), null);
        drawBody(g);

        move();
    }

    /**
     * Method to check if any of the snake's body points are intersecting
     * with one another
     */
    public void eatBody()
    {
        for (Point point : bodyPoints)
        {
            for (Point point2 : bodyPoints)
            {
                if (point.equals(point2) && point != point2)
                {
                    //If any two body points intersect, snake dies
                    this.setL(false);
                }
            }
        }
    }

    /**
     * Method to draw the snake's body
     * @param g Graphics object used to draw the snake
     */
    private void drawBody(Graphics g)
    {
        int length = bodyPoints.size() - 1 - getNum();

        for (int i = length; i >= getNum(); i -= getNum())
        {
            Point point = bodyPoints.get(i);
            g.drawImage(this.getI(), point.x, point.y, null);
        }
    }

    /**
     * Method to check if the snake's head has gone out of bounds (out of the
     * frame)
     */
    protected void outOfBounds()
    {
        boolean xOut = (getX() <= 0 || getX() >= (MAX_X - getW()));
        boolean yOut = (getY() <= 0 || getY() >= (MAX_Y - getH()));
        if (xOut || yOut)
        {
            //If the snake is outside the frame's bounds, it dies
            this.setL(false);
        }
    }
}


