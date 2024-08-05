package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.model.Leaderboard;
import com.controller.Snake;
import com.model.*;

import javax.swing.*;


/**
 * This class is responsible for updating the game board (view) in
 * real time as certain events occur. It is responsible for updating and
 * displaying the current score, current level, background, and the size and
 * position of all the BoardObjets (Food, Walls, Snake) in real time.
 *
 * @author Ronak Mahesh - Modified
 * @see com.view.MyFrame
 */

public class GameView extends MyFrame
{
	//Constants:-
	private static final int START_COORD = 100;
	private static final int FONT_SIZE = 30;
	private static final int SCORE_X = 20;
	private static final int SCORE_Y = 40;
	private static final int LEVEL1_TRANSITION = 5210;
	private static final int LEVEL2_TRANSITION = 10420;
	private static final int SCORE_INCR = 521;
	private static final int ADJUST_LEVEL = 30;
	private static final int BUTTON_W = 600;
	private static final int BUTTON_H = 400;
	private static final int BUTTON_X = 20;
	private static final int BUTTON_Y = 420;
	private static final int LABEL_X = 20;
	private static final int LABEL_Y = 470;
	private static final int LABEL_SIZE = 25;
	private static final int POS = 25;
	private static final int TOP3_SIZE = 17;
	private static final int TOP3_X = 20;
	private static final int TOP3_Y = 225;
	private static final int TWO = 2;
	private static final int THREE = 3;

	//Member Attributes:-
	public Snake mySnake;
	public Food food;
	public FoodFactory foodFactory = new FoodFactory();
	public Wall wall;
	public Wall wall2;
	public Wall wall3;
	private String theme;
	private String colour;
	private Image background;
	private Image background2;
	private Image background3;
	private Image fail;
	private boolean updated;
	private boolean sorted;
	JButton restart;
	private HashMap<Integer, String> scoreMap = new HashMap<>();

	/**
	 * Getter method for the sorted attribute
	 * @return A boolean, sorted, that indicates if the high scores have been
	 * sorted yet or not
	 */
	public boolean isSorted()
	{
		return this.sorted;
	}

	/**
	 * Setter method for the sorted attribute
	 * @param sorted Updates the status of whether the high scores have been
	 *                  sorted yet or not
	 */
	public void setSorted(boolean sorted)
	{
		this.sorted = sorted;
	}

	/**
	 * Getter method for the updated attribute
	 * @return A boolean, updates, that indicates if the current player's
	 * score has been added to the HighScores file or not
	 */
	public boolean isUpdated()
	{
		return this.updated;
	}

	/**
	 * Setter method for the updated attribute
	 * @param updated Updates the status of whether the current score has been
	 *                  written to the HighScores file or not
	 */

	public void setUpdated(boolean updated)
	{
		this.updated = updated;
	}

	/**
	 * Getter method for the theme attribute
	 * @return A string that indicates what theme the user has chosen
	 */
	public String getTheme()
	{
		return this.theme;
	}

	/**
	 * Setter method for the theme attribute
	 * @param theme A string that decides what theme the game will be played on
	 */
	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	/**
	 * Getter method for the first background attribute
	 * @return An Image that corresponds to the background being used in level 1
	 */
	public Image getBg()
	{
		return this.background;
	}

	/**
	 * Setter method for the first background attribute
	 * @param background An image that the background of level 1 is set to
	 */
	public void setBg(Image background)
	{
		this.background = background;
	}

	/**
	 * Getter method for the second background attribute
	 * @return An Image that corresponds to the background being used in level 2
	 */
	public Image getBg2()
	{
		return this.background2;
	}

	/**
	 * Setter method for the second background attribute
	 * @param background2 An image that the background of level 2 is set to
	 */
	public void setBg2(Image background2)
	{
		this.background2 = background2;
	}

	/**
	 * Getter method for the third background attribute
	 * @return An Image that corresponds to the background being used in level 3
	 */
	public Image getBg3()
	{
		return this.background3;
	}

	/**
	 * Setter method for the third background attribute
	 * @param background3 An image that the background of level 3 is set to
	 */
	public void setBg3(Image background3)
	{
		this.background3 = background3;
	}

	/**
	 * Getter method for the Image used in the game over screen
	 * @return An Image that corresponds to the background being used in game
	 * over screen
	 */
	public Image getFail()
	{
		return this.fail;
	}

	/**
	 * Setter method for the Image used in the game over screen
	 * @param fail An Image that the background used in the game over screen
	 *                is set to
	 */
	public void setFail(Image fail)
	{
		this.fail = fail;
	}

	/**
	 * Getter method for the snake's color
	 * @return A string, colour, that corresponds to the colour of the snake
	 * to be used in the game
	 */
	public String getColour()
	{
		return this.colour;
	}

	/**
	 * Setter method for the snake's colour
	 * @param colour A string, colour, that decides the colour of the snake
	 *                  to be used in the game
	 */
	public void setColour(String colour)
	{
		this.colour = colour;
	}

	/**
	 * Constructor for the game board and that sets its initial state
	 * @param theme A string that corresponds to the theme to be used in the
	 *                 game
	 */
	public GameView(String theme)
	{
		this.setFail(ImageUtil.images.get("end"));
		this.setUpdated(false);
		this.setTheme(theme);
		this.setSorted(false);
		this.food = foodFactory.getFood();
		if(Objects.equals(this.getTheme(), "Classic Theme"))
		{
			this.setBg(ImageUtil.images.get("UI-background"));
			this.setBg2(ImageUtil.images.get("background2"));
			this.setBg3(ImageUtil.images.get("background3"));
			this.setColour("Green");
			this.wall = new Wall("Black");
			this.wall2 = new Wall("Black");
			this.wall3 = new Wall("Black");
		}
		else if(Objects.equals(this.getTheme(), "Neon Theme"))
		{
			this.setBg(ImageUtil.images.get("neon1"));
			this.setBg2(ImageUtil.images.get("neon2"));
			this.setBg3(ImageUtil.images.get("neon3"));
			this.setColour("White");
			this.wall = new Wall("White");
			this.wall2 = new Wall("White");
			this.wall3 = new Wall("White");
		}
		mySnake = new Snake(START_COORD, START_COORD, this.getColour());
		restart = new JButton("Replay");
		restart.addActionListener(this);
	}

	/**
	 * Paints the game over screen (the game over image, the partial
	 * leaderboard, and the return to main menu button)
	 * @param g A graphics object
	 */
	private void gameOver(Graphics g)
	{
		if(!isUpdated())
		{
			//If high score hasn't been added to the text file, then do so
			Leaderboard.updateLeaderboard(mySnake.getScore(),
					this.getPlayerName());
			this.setUpdated(true);
			Leaderboard.sortScore();
			JOptionPane.showMessageDialog(null,
					"Your Score: "+mySnake.getScore() + "\n"
					+ "You Reached Level " + this.getLevel() + "\n"
					+ "You Ate " + this.getNumFruits() + " Fruits",
					this.getPlayerName()+"'s Stats",
					JOptionPane.PLAIN_MESSAGE);
		}
		g.drawImage(getFail().getScaledInstance(
						this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH),
				0, 0, null);

		restart.setSize(BUTTON_W, BUTTON_H);
		restart.setLocation(BUTTON_X, BUTTON_Y);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, LABEL_SIZE));
		g.setColor(Color.GREEN);
		g.drawString(
				"Click *HERE* To Go Back To Main Menu & View Full Leaderboard",
				LABEL_X,
				LABEL_Y);

		jFrame.getContentPane().add(restart);
		restart.setVisible(true);

		displayPartialLeaderboard(g);
	}

	/**
	 * Method to get the number of fruits eaten
	 * @return Number of fruits eaten
	 */
	private int getNumFruits()
	{
		int score = mySnake.getScore();
		return (score/SCORE_INCR);
	}

	/**
	 * Method to get the level reached at the end of the game
	 * @return Level reached at the end of the game
	 */
	private int getLevel()
	{
		int score = mySnake.getScore();
		if(score<LEVEL1_TRANSITION)
			return 1;
		else if(score>=LEVEL1_TRANSITION && score<LEVEL2_TRANSITION)
			return TWO;
		else
			return THREE;
	}

	/**
	 * Method to paint level 1
	 * @param g A graphics object
	 */
	public void paint(Graphics g)
	{
		//If the score hasn't crossed the level 1 to 2 transition point yet
		if(mySnake.getScore()<LEVEL1_TRANSITION)
		{
			g.drawImage(background, 0, 0, null);
			wall.draw(g);
			//If the snake isn't colliding with the wall
			if (mySnake.getL() && wall.getL())
			{
				mySnake.draw(g);
				if (wall.getL())
				{
					wall.draw(g);
					wall.collided(mySnake);
				}
				//If the food exists, and it doesn't intersect the wall
				if (food.getL() && !(wall.getRectangle().intersects(
						food.getRectangle())))
				{
					food.draw(g);
					food.eaten(mySnake);
				}
				else
				{
					MusicPlayer.getMusicPlay("src/main/resources/munch.mp3");
					food = foodFactory.getFood();
				}
			}
			else
			{
				gameOver(g);
			}
			drawScore(g);
			drawLevel(g);
		}
		else
			paintLevelTwo(g);
	}

	/**
	 * Method to paint level 2
	 * @param g A graphics object
	 */
	private void paintLevelTwo(Graphics g)
	{
		//If the score hasn't crossed the level 2 to 3 transition point yet
		if(mySnake.getScore() < LEVEL2_TRANSITION)
		{
			g.drawImage(background2, 0, 0, null);
			wall.draw(g);
			wall2.draw(g);

			//If the snake isn't colliding with both the walls
			if (mySnake.getL() && wall.getL() && wall2.getL())
			{
				mySnake.draw(g);
				drawLevel(g);
				if (wall.getL())
				{
					wall.draw(g);
					wall.collided(mySnake);
				}
				if (wall2.getL())
				{
					wall2.draw(g);
					wall2.collided(mySnake);
				}
				if (food.getL()
						&& !(wall.getRectangle().intersects(food.getRectangle()))
						&& !(wall2.getRectangle().intersects(food.getRectangle())))
				{
					food.draw(g);
					food.eaten(mySnake);
				}
				else
				{
					MusicPlayer.getMusicPlay("src/main/resources/munch.mp3");
					food = foodFactory.getFood();
				}
			}
			else
			{
				gameOver(g);
			}
			drawScore(g);
			drawLevel(g);
		}
		else
			paintLevelThree(g);
	}

	/**
	 * Method to paint level 3
	 * @param g A graphics object
	 */
	private void paintLevelThree(Graphics g)
	{
		g.drawImage(background3, 0, 0, null);
		wall.draw(g);
		wall2.draw(g);
		wall3.draw(g);

		//If the snake isn't colliding with all three the walls
		if (mySnake.getL() && wall.getL() && wall2.getL() && wall3.getL())
		{
			mySnake.draw(g);
			drawLevel(g);
			if (wall.getL())
			{
				wall.draw(g);
				wall.collided(mySnake);
			}
			if (wall2.getL())
			{
				wall2.draw(g);
				wall2.collided(mySnake);
			}
			if (wall3.getL())
			{
				wall3.draw(g);
				wall3.collided(mySnake);
			}
			if (food.getL()
					&& !(wall.getRectangle().intersects(food.getRectangle()))
					&& !(wall2.getRectangle().intersects(food.getRectangle()))
					&& !(wall3.getRectangle().intersects(food.getRectangle())))
			{
				food.draw(g);
				food.eaten(mySnake);
			}
			else
			{
				MusicPlayer.getMusicPlay("src/main/resources/munch.mp3");
				food = foodFactory.getFood();
			}
		}
		else
		{
			gameOver(g);
		}
		drawScore(g);
		drawLevel(g);
	}

	/**
	 * @param e the event to be processed
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		mySnake.keyPressed(e);
	}

	/**
	 * Method to display the live score on the screen as the user plays the game
	 * @param g A graphics object used for drawing
	 */
	private void drawScore(Graphics g)
	{
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, FONT_SIZE));
		g.setColor(Color.MAGENTA);
		g.drawString("SCORE : " + mySnake.getScore(), SCORE_X, SCORE_Y);
	}

	/**
	 * Method to display the current level on the screen as the user plays the
	 * game
	 * @param g A graphics object used for drawing
	 */
	private void drawLevel(Graphics g)
	{
		int level = 1;
		if(mySnake.getScore()>=LEVEL1_TRANSITION)
			level = TWO;
		if(mySnake.getScore()>=LEVEL2_TRANSITION)
			level = THREE;
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, FONT_SIZE));
		g.setColor(Color.MAGENTA);
		g.drawString("LEVEL : " + level, SCORE_X, SCORE_Y+ADJUST_LEVEL);
	}

	/**
	 * Method to display the top 3 high scores on the game over screen
	 * @param g A graphics object used for drawing
	 */
	private void displayPartialLeaderboard(Graphics g)
	{
		int limit;
		if(Leaderboard.scores.size()<THREE)
			limit = Leaderboard.scores.size();
		else
			limit = THREE;
		if(!isSorted())
		{
			Leaderboard.sortScore();
			for (Map.Entry<Integer, String> entry :
					Leaderboard.sortedScores.entrySet())
				Leaderboard.scores.
						add("" + entry.getValue() + ": " + entry.getKey());

			this.setSorted(true);
			Collections.reverse(Leaderboard.scores);
		}
		int pos = POS;
		for(int i=0;i<limit;i++)
		{
			g.setFont(new java.awt.Font(java.awt.Font.SANS_SERIF,
					java.awt.Font.BOLD, TOP3_SIZE));
			g.setColor(java.awt.Color.PINK);
			g.drawString("Top 3 High Scores:-", TOP3_X, TOP3_Y);
			g.drawString(
					(""+(i+1)+". "+Leaderboard.scores.get(i)),
					TOP3_X,
					TOP3_Y+pos);
			pos+=POS; //Move to next line for next score
		}
	}
}