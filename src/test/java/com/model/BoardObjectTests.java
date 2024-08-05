package com.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

// Since Food and Wall are PhysicalObjects, this test file covers the testing
// for the getters and setters (and the getRectangle method) used in all 3
// aforementioned classes

/**
 * @file BoardObjectTests.java
 * @author Ronak Mahesh
 * @see com.model.BoardObject
 *
 * @brief This class is responsible for testing BoardObject.java
 */
class BoardObjectTests
{
    @Test
    public void testGetSetX()
    {
        BoardObject obj = new BoardObject()
        {@Override public void draw(Graphics g) {}};
        obj.setX(5);
        assertEquals(5, obj.getX());
    }

    @Test
    public void testGetSetY()
    {
        BoardObject obj = new BoardObject()
        {@Override public void draw(Graphics g) {}};
        obj.setY(10);
        assertEquals(10, obj.getY());
    }

    @Test
    public void testGetSetI()
    {
        BoardObject obj = new BoardObject()
        {@Override public void draw(Graphics g) {}};
        obj.setI(ImageUtil.images.get("wall.png"));
        assertEquals(ImageUtil.images.get("wall.png"), obj.getI());
    }

    @Test
    public void testGetSetW()
    {
        BoardObject obj = new BoardObject()
        {@Override public void draw(Graphics g) {}};
        obj.setW(25);
        assertEquals(25, obj.getW());
    }

    @Test
    public void testGetSetH()
    {
        BoardObject obj = new BoardObject()
        {@Override public void draw(Graphics g) {}};
        obj.setH(40);
        assertEquals(40, obj.getH());
    }

    @Test
    public void testGetSetL()
    {
        BoardObject obj = new BoardObject()
        {@Override public void draw(Graphics g) {}};
        obj.setL(false);
        assertFalse(obj.getL());
    }

    // This tests to see if the method getRectangle() correctly returns a
    // rectangle with the specified dimensions
    @Test
    public void testGetRectangle()
    {
        BoardObject obj = new BoardObject()
        {@Override public void draw(Graphics g) {}};
        obj.setX(10);
        obj.setY(20);
        obj.setW(40);
        obj.setH(50);
        Rectangle rect = obj.getRectangle();
        Rectangle identical = new Rectangle(10,20,40,50);
        assertEquals(identical, rect);
    }
}