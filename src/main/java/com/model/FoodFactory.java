package com.model;

import com.view.Food;

/**
 * This class is responsible for returning a new Food object when
 * requested. It makes use of the Factory Design Pattern to do this.
 *
 * @author Ronak Mahesh
 * @see com.view.GameView
 */
public class FoodFactory
{
    /**
     * Public constructor for the food factory that doesn't do anything
     */
    public FoodFactory() {}

    /**
     * @return A new Food object reference
     */
    public Food getFood()
    {
        return new Food();
    }
}
