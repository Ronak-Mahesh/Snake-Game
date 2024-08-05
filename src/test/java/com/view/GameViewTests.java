package com.view;

import com.model.ImageUtil;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @file GameViewTests.java
 * @author Ronak Mahesh
 * @see com.view.GameView
 *
 * @brief This class is responsible for testing GameView.java
 */
class GameViewTests
{
    @Test
    public void testIsUpdated()
    {
        GameView gameView = new GameView("Neon Theme");
        assertFalse(gameView.isUpdated());
    }

    @Test
    public void testSetUpdated()
    {
        GameView gameView = new GameView("Neon Theme");
        gameView.setUpdated(true);
        assertTrue(gameView.isUpdated());
    }

    @Test
    public void testGetTheme()
    {
        GameView gameView = new GameView("Neon Theme");
        assertEquals("Neon Theme", gameView.getTheme());
    }

    @Test
    public void testSetTheme()
    {
        GameView gameView = new GameView("Neon Theme");
        gameView.setTheme("Classic Theme");
        assertEquals("Classic Theme", gameView.getTheme());
    }

    @Test
    public void testGetNeonBg()
    {
        GameView gameView = new GameView("Neon Theme");
        Image neonbg1 = ImageUtil.images.get("neon1");
        assertEquals(neonbg1, gameView.getBg());
    }

    @Test
    public void testSetNeonBg()
    {
        GameView gameView = new GameView("Neon Theme");
        Image neonbg2 = ImageUtil.images.get("neon2");
        gameView.setBg(ImageUtil.images.get("neon2"));
        assertEquals(neonbg2, gameView.getBg());
    }

    @Test
    public void testGetNeonBg2()
    {
        GameView gameView = new GameView("Neon Theme");
        Image neonbg2 = ImageUtil.images.get("neon2");
        assertEquals(neonbg2, gameView.getBg2());
    }

    @Test
    public void testSetNeonBg2()
    {
        GameView gameView = new GameView("Neon Theme");
        Image neonbg3 = ImageUtil.images.get("neon3");
        gameView.setBg2(ImageUtil.images.get("neon3"));
        assertEquals(neonbg3, gameView.getBg2());
    }

    @Test
    public void testGetNeonBg3()
    {
        GameView gameView = new GameView("Neon Theme");
        Image neonbg3 = ImageUtil.images.get("neon3");
        assertEquals(neonbg3, gameView.getBg3());
    }

    @Test
    public void testSetNeonBg3()
    {
        GameView gameView = new GameView("Neon Theme");
        Image neonbg2 = ImageUtil.images.get("neon2");
        gameView.setBg3(ImageUtil.images.get("neon2"));
        assertEquals(neonbg2, gameView.getBg3());
    }

    @Test
    public void testGetClassicBg()
    {
        GameView gameView = new GameView("Classic Theme");
        Image classicbg1 = ImageUtil.images.get("UI-background");
        assertEquals(classicbg1, gameView.getBg());
    }

    @Test
    public void testSetClassicBg()
    {
        GameView gameView = new GameView("Classic Theme");
        Image classicbg2 = ImageUtil.images.get("background2");
        gameView.setBg(ImageUtil.images.get("background2"));
        assertEquals(classicbg2, gameView.getBg());
    }

    @Test
    public void testGetClassicBg2()
    {
        GameView gameView = new GameView("Classic Theme");
        Image classicbg2 = ImageUtil.images.get("background2");
        assertEquals(classicbg2, gameView.getBg2());
    }

    @Test
    public void testSetClassicBg2()
    {
        GameView gameView = new GameView("Classic Theme");
        Image classicbg3 = ImageUtil.images.get("background3");
        gameView.setBg2(ImageUtil.images.get("background3"));
        assertEquals(classicbg3, gameView.getBg2());
    }

    @Test
    public void testGetClassicBg3()
    {
        GameView gameView = new GameView("Classic Theme");
        Image classicbg3 = ImageUtil.images.get("background3");
        assertEquals(classicbg3, gameView.getBg3());
    }

    @Test
    public void testSetClassicBg3()
    {
        GameView gameView = new GameView("Classic Theme");
        Image classicbg2 = ImageUtil.images.get("background2");
        gameView.setBg3(ImageUtil.images.get("background2"));
        assertEquals(classicbg2, gameView.getBg2());
    }

    @Test
    public void testGetFail()
    {
        GameView gameView = new GameView("Classic Theme");
        Image fail = ImageUtil.images.get("end");
        assertEquals(fail, gameView.getFail());
    }

    @Test
    public void testSetFail()
    {
        GameView gameView = new GameView("Classic Theme");
        Image fail = ImageUtil.images.get("wall");
        gameView.setFail(ImageUtil.images.get("wall"));
        assertEquals(fail, gameView.getFail());
    }

    @Test
    public void testGetColour()
    {
        GameView gameView = new GameView("Neon Theme");
        assertEquals("White", gameView.getColour());

        GameView gameView2 = new GameView("Classic Theme");
        assertEquals("Green", gameView2.getColour());
    }

    @Test
    public void testSetColour()
    {
        GameView gameView = new GameView("Neon Theme");
        gameView.setColour("Green");
        assertEquals("Green", gameView.getColour());

        GameView gameView2 = new GameView("Classic Theme");
        gameView.setColour("White");
        assertEquals("White", gameView.getColour());
    }
}