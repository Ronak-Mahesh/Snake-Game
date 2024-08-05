package com.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @file SnakeTests.java
 * @author Ronak Mahesh
 * @see com.controller.Snake
 *
 * @brief This class is responsible for testing Snake.java
 */
class SnakeTests
{
    @Test
    public void testGetSpeed()
    {
        Snake snake = new Snake(0,0, "Green");
        assertEquals(5, snake.getSpeed());
    }

    @Test
    public void testSetSpeed()
    {
        Snake snake = new Snake(0,0, "Green");
        snake.setSpeed(10);
        assertEquals(10, snake.getSpeed());
    }

    @Test
    public void testGetNum()
    {
        Snake snake = new Snake(0,0, "Green");
        assertEquals((snake.getW()/snake.getSpeed()), snake.getNum());
    }

    @Test
    public void testSetNum()
    {
        Snake snake = new Snake(0,0, "Green");
        snake.setNum(2);
        assertEquals(2, snake.getNum());
    }

    @Test
    public void testGetScore()
    {
        Snake snake = new Snake(0,0, "Green");
        assertEquals(0, snake.getScore());
    }

    @Test
    public void testSetScore()
    {
        Snake snake = new Snake(0,0, "Green");
        snake.setScore(10420);
        assertEquals(10420, snake.getScore());
    }

    @Test
    public void testGetLength()
    {
        Snake snake = new Snake(0,0, "Green");
        assertEquals(1, snake.getLength());
    }

    @Test
    public void testSetLength()
    {
        Snake snake = new Snake(0,0, "Green");
        snake.setLength(10);
        assertEquals(10, snake.getLength());
    }

    @Test
    public void testIsUp()
    {
        Snake snake = new Snake(0,0, "Green");
        assertFalse(snake.isUp());
    }

    @Test
    public void testSetUp()
    {
        Snake snake = new Snake(0,0, "Green");
        snake.setUp(false);
        assertFalse(snake.isUp());
    }

    @Test
    public void testIsDown()
    {
        Snake snake = new Snake(0,0, "Green");
        assertFalse(snake.isDown());
    }

    @Test
    public void testSetDown()
    {
        Snake snake = new Snake(0,0, "Green");
        snake.setDown(false);
        assertFalse(snake.isDown());
    }

    @Test
    public void testIsLeft()
    {
        Snake snake = new Snake(0,0, "Green");
        assertFalse(snake.isLeft());
    }

    @Test
    public void testSetLeft()
    {
        Snake snake = new Snake(0,0, "Green");
        snake.setLeft(false);
        assertFalse(snake.isLeft());
    }

    @Test
    public void testIsRight()
    {
        Snake snake = new Snake(0,0, "Green");
        assertTrue(snake.isRight());
    }

    @Test
    public void testSetRight()
    {
        Snake snake = new Snake(0,0, "Green");
        snake.setRight(false);
        assertFalse(snake.isRight());
    }

    @Test
    public void testOutOfBounds()
    {
        //Out of bounds X-Coordinate
        //In bounds Y-Coordinate
        Snake snake = new Snake(-5,25, "Green");
        snake.outOfBounds(); //Test if snake is in bounds
        assertFalse(snake.getL()); //Snake's life (L) should be false
    }

    @Test
    public void testWithinBounds()
    {
        //In bounds X-Coordinate
        //In bounds Y-Coordinate
        Snake snake = new Snake(30,25, "Green");
        snake.outOfBounds(); //Test if snake is in bounds
        assertTrue(snake.getL()); //Snake's life (L) should be true
    }

    @Test
    public void testMoveUp()
    {
        Snake snake = new Snake(50,50, "Green");
        snake.setUp(true);
        snake.setDown(false);
        snake.setLeft(false);
        snake.setRight(false);
        snake.move();
        assertEquals(45, snake.getY()); // 50 (Y) - 5 (speed) = 45
    }

    @Test
    public void testMoveDown()
    {
        Snake snake = new Snake(50,50, "Green");
        snake.setUp(false);
        snake.setDown(true);
        snake.setLeft(false);
        snake.setRight(false);
        snake.move();
        assertEquals(55, snake.getY()); // 50 (Y) + 5 (speed) = 55
    }

    @Test
    public void testMoveLeft()
    {
        Snake snake = new Snake(50,50, "Green");
        snake.setUp(false);
        snake.setDown(false);
        snake.setLeft(true);
        snake.setRight(false);
        snake.move();
        assertEquals(45, snake.getX()); // 50 (X) - 5 (speed) = 45
    }

    @Test
    public void testMoveRight()
    {
        Snake snake = new Snake(50,50, "Green");
        snake.setUp(false);
        snake.setDown(false);
        snake.setLeft(false);
        snake.setRight(true);
        snake.move();
        assertEquals(50, snake.getY()); // Moving right by default
    }
}