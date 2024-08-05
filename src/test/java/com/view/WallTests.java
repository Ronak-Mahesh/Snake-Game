package com.view;

import com.controller.Snake;
import com.model.ImageUtil;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @file WallTests.java
 * @author Ronak Mahesh
 * @see com.view.Wall
 *
 * @brief This class is responsible for testing Wall.java
 */
class WallTests
{
    private static final int MIN_NUM = 1;
    private static final int MAX_NUM = 100;

    @Test
    public void testGetSetRandInt()
    {
        Wall wall = new Wall("Black");
        int temp = (int)Math.floor(Math.random()*(MAX_NUM-MIN_NUM+1)+MIN_NUM);
        wall.setRandInt(temp);
        assertEquals(temp, wall.getRandInt());
    }

    @Test
    public void testCollided()
    {
        Snake snake = new Snake(0,0, "Green");
        Wall wall = new Wall("Black");

        // Make wall and snake be in the same position (make them intersect)
        wall.setX(snake.getX());
        wall.setY(snake.getY());

        wall.setL(true); // Making sure it is definitely on the board
        wall.collided(snake);

        assertFalse(wall.getL()); // The wall should be collided with
    }

    @Test
    public void testNotCollided()
    {
        Snake snake = new Snake(0,0, "Green");
        Wall wall = new Wall("Black");

        // Make wall and snake be in different positions (shouldn't intersect)
        // 40 is an offset to ensure that they're in different positions
        wall.setX(snake.getX()+40);
        wall.setY(snake.getY()+40);

        wall.collided(snake);

        assertTrue(wall.getL()); // The wall shouldn't be collided with
    }

    @Test
    public void testGetColour()
    {
        Wall wall = new Wall("Black");
        assertEquals("Black", wall.getColour());
    }

    @Test
    public void testSetColour()
    {
        Wall wall = new Wall("Black");
        wall.setColour("White");
        assertEquals("White", wall.getColour());
    }

    @Test
    public void testGetImage()
    {
        Wall blackWall = new Wall("Black");
        Image expected1 = ImageUtil.images.get("wall");
        Image expected2 = ImageUtil.images.get("rotated");
        assertTrue((Objects.equals(expected1, blackWall.getI())) ||
                (Objects.equals(expected2, blackWall.getI())));

        Wall whiteWall = new Wall("White");
        Image expected3 = ImageUtil.images.get("wall-white");
        Image expected4 = ImageUtil.images.get("rotated-white");
        assertTrue((Objects.equals(expected3, whiteWall.getI())) ||
                (Objects.equals(expected4, whiteWall.getI())));
    }
}