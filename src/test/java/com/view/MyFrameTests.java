package com.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @file MyFrameTests.java
 * @author Ronak Mahesh
 * @see com.view.MyFrame
 *
 * @brief This class is responsible for testing MyFrame.java
 */
class MyFrameTests
{
    @Test
    public void testGetSetName()
    {
        String test = "This Is A JUnit Test";
        MyFrame frame = new MyFrame();
        frame.setPlayerName(test);
        assertEquals(test, frame.getPlayerName());
    }

    @Test
    void testLoadFrame()
    {
        String test = "Snakee- Peter Is Playing Game";
        MyFrame frame = new MyFrame();
        frame.loadFrame("Peter");
        assertEquals(test, frame.jFrame.getTitle());
    }
}