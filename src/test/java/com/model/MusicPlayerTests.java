package com.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @file MusicPlayerTests.java
 * @author Ronak Mahesh
 * @see com.model.MusicPlayer
 *
 * @brief This class is responsible for testing MusicPlayer.java
 */
class MusicPlayerTests
{
    @Test
    public void testGetFilename()
    {
        MusicPlayer player = new MusicPlayer("frogger.mp3");
        assertEquals("frogger.mp3", player.getFilename());
    }

    @Test
    public void testSetFilename()
    {
        MusicPlayer player = new MusicPlayer("frogger.mp3");
        player.setFilename("munch.mp3");
        assertEquals("munch.mp3", player.getFilename());
    }

    //These tests check whether all the audio components run perfectly

    @Test
    public void testPlayFroggerInLoop()
    {
        MusicPlayer player = new MusicPlayer("frogger.wav");
        assertDoesNotThrow(player::playInLoop);
    }

    @Test
    public void testPlayFroggerOnce()
    {
        MusicPlayer player = new MusicPlayer("frogger.wav");
        assertDoesNotThrow(player::playOnce);
    }

    @Test
    public void testPlayMunchOnce()
    {
        MusicPlayer player = new MusicPlayer("munch.mp3");
        assertDoesNotThrow(player::playOnce);
    }
}