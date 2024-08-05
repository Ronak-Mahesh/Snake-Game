package com.view;

import com.controller.Snake;
import com.model.FoodFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @file FoodObjectTests.java
 * @author Ronak Mahesh
 * @see com.view.Food
 *
 * @brief This class is responsible for testing Food.java
 */
class FoodTests
{
    @Test
    public void testEaten()
    {
        Snake snake = new Snake(0,0, "Green");
        FoodFactory foodFactory = new FoodFactory();
        Food food = foodFactory.getFood();

        // Make food and snake be in the same position (make them intersect)
        food.setX(snake.getX());
        food.setY(snake.getY());

        food.setL(true); // Making sure it is definitely on the board
        food.eaten(snake);

        assertFalse(food.getL()); // The food should be eaten (Life = false)
    }

    @Test
    public void testNotEaten()
    {
        Snake snake = new Snake(0,0, "Green");
        FoodFactory foodFactory = new FoodFactory();
        Food food = foodFactory.getFood();

        // Make food and snake be in different positions (shouldn't intersect)
        // 40 is an offset to ensure that they're in different positions
        food.setX(snake.getX()+40);
        food.setY(snake.getY()+40);

        food.eaten(snake);

        assertTrue(food.getL()); // The food should't be eaten (Life = true)
    }
}