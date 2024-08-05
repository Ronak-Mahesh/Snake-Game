package com.view;

import com.model.Leaderboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @file LeaderboardTests.java
 * @author Ronak Mahesh
 * @see Leaderboard
 *
 * @brief This class is responsible for testing Leaderboard.java
 */
class LeaderboardTests
{
    @Test
    public void testGetLimit()
    {
        Leaderboard leaderboard = new Leaderboard();
        // Test if limit gets set to default constructor value, i.e.- 5
        assertEquals(5, leaderboard.getLimit());
    }

    @Test
    public void testSetLimit()
    {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setLimit(10);
        assertEquals(10, leaderboard.getLimit());
    }
}