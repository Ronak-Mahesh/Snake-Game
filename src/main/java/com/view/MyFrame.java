package com.view;

import java.awt.event.*;

import javax.swing.*;

import com.controller.Snake;
import com.model.*;


/**
 * This class is responsible for creating a new JFrame to display
 * the game. It runs the game on a new thread.
 *
 * @author Ronak Mahesh - Modified
 * @see com.view.GameView
 */

public class MyFrame extends JPanel implements KeyListener, ActionListener
{
	//Constants:-
	private static final int BOARD_WIDTH = 870;
	private static final int BOARD_HEIGHT = 560;
	private static final int SLEEP_TIME = 30;

	//Attributes:-
	public JFrame jFrame = new JFrame();
	private String playerName;

	/**
	 * Getter method for tne player's name
	 * @return A string, playerName, that corresponds to the name of the
	 * current player playing the game
	 */
	public String getPlayerName()
	{
		return this.playerName;
	}

	/**
	 * Setter method for the player's name
	 * @param name A string containing the player's name that the playerName
	 *                attribute is set to
	 */
	public void setPlayerName(String name)
	{
		this.playerName = name;
	}

	/**
	 * Public constructor for MyFrame that initializes the frame's attributes
	 */
	public MyFrame()
	{
		jFrame.setIconImage(ImageUtil.images.get("snake-logo"));
	}

	/**
	 * Method to initialize and load the frame up when the user wants to play
	 * the game
	 * @param name A string that corresponds to the name of the current
	 *                player playing the game
	 */
	public void loadFrame(String name)
	{
		this.setPlayerName(name);
		this.setDoubleBuffered(true);
		jFrame.add(this);
		jFrame.addKeyListener(this);
		jFrame.setTitle("Snakee- "+name+" Is Playing Game");
		jFrame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
		new MyThread().start();
	}

	/**
	 * Method that sets the action for the JButton used to return to the main
	 * menu
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//Close the game screen, and go back to main menu
		jFrame.dispatchEvent(new WindowEvent(
				jFrame, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Class that manages the different threads involved in the game and runs
	 * them indefinitely until the user exits the program
	 */
	class MyThread extends Thread
	{
		/**
		 * Method to run the game thread
		 */
		@Override
		public void run()
		{
			super.run();
			while (true)
			{
				repaint();
				try
				{
					sleep(SLEEP_TIME);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
	}
}
