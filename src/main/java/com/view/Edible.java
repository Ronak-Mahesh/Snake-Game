package com.view;

import com.controller.Snake;

/**
 * An interface for any object that can be eaten, like the Food that
 * the snake eats.
 *
 * @author Ronak Mahesh
 * @see com.view.Food An implementation of this interface
 */
public interface Edible
{
    /**
     * A method to be implemented in any way the user wants. Defines the way
     * in which any edible item can tell if it has been eaten or not.
     *
     * @param mySnake Snake object that eats the food
     */
    void eaten(Snake mySnake);
}
