package com.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for defining a static hashmap that stores
 * all the image objects used in the game (such as backgrounds, the snake's
 * body, pictures of food, walls, etc). It also provides 2 methods relating
 * to images and their properties.
 *
 * @author Ronak Mahesh - Modified
 */
public class ImageUtil
{
	//Constant:-
	private static final int TWO = 2;

	/**
	 * A HashMap that stores all images used in the game, including the snake
	 * body itself, backgrounds, and obstacles. Indexes each Image file with
	 * a strong that gives it a symbolic name
	 */
	public static Map<String, Image> images = new HashMap<>();

	static
	{
		// Snake head and body images
		images.put(
				"snake-head-right", ImageUtil.getImage("snake-head-right.png"));
		images.put(
				"snake-head-white", ImageUtil.getImage("snake-head-white.png"));
		images.put("snake-body", ImageUtil.getImage("snake-body.png"));
		images.put("snake-white", ImageUtil.getImage("snake-white.png"));

		// Food and Walls
		images.put("0", ImageUtil.getImage("food-kiwi.png"));
		images.put("1", ImageUtil.getImage("food-lemon.png"));
		images.put("2", ImageUtil.getImage("food-litchi.png"));
		images.put("3", ImageUtil.getImage("food-mango.png"));
		images.put("4", ImageUtil.getImage("food-apple.png"));
		images.put("5", ImageUtil.getImage("food-banana.png"));
		images.put("6", ImageUtil.getImage("food-blueberry.png"));
		images.put("7", ImageUtil.getImage("food-cherry.png"));
		images.put("8", ImageUtil.getImage("food-durian.png"));
		images.put("9", ImageUtil.getImage("food-grape.png"));
		images.put("10", ImageUtil.getImage("food-grapefruit.png"));
		images.put("11", ImageUtil.getImage("food-peach.png"));
		images.put("12", ImageUtil.getImage("food-pear.png"));
		images.put("13", ImageUtil.getImage("food-orange.png"));
		images.put("14", ImageUtil.getImage("food-pineapple.png"));
		images.put("15", ImageUtil.getImage("food-strawberry.png"));
		images.put("16", ImageUtil.getImage("food-watermelon.png"));
		images.put("wall", ImageUtil.getImage("wall.png"));
		images.put("rotated", ImageUtil.getImage("wall-rotated.png"));
		images.put("wall-white", ImageUtil.getImage("wall-white.jpg"));
		images.put("rotated-white", ImageUtil.getImage("rotated-white.jpg"));

		// Backgrounds and Logos
		images.put(
				"UI-background", ImageUtil.getImage("UI-background.png"));
		images.put(
				"background2", ImageUtil.getImage("UI-background2.png"));
		images.put(
				"background3", ImageUtil.getImage("background3.jpg"));
		images.put("neon1", ImageUtil.getImage("neon.jpg"));
		images.put("neon2", ImageUtil.getImage("neon2.jpg"));
		images.put("neon3", ImageUtil.getImage("neon3.jpg"));
		images.put("end", ImageUtil.getImage("end.png"));
		images.put("snake-logo", ImageUtil.getImage("snake-logo.png"));
	}

	/**
	 * Returns an image from the resources folder
	 * @param imagePath The path to the image that is to be retrieved from
	 *                     the file system
	 * @return A java.awt Image
	 */
	public static Image getImage(String imagePath)
	{
		URL u = ImageUtil.class.getClassLoader().getResource(imagePath);
		BufferedImage i = null;
		try
		{
			i = ImageIO.read(u);
		} catch (Exception e)
		{
			System.err.println("ERROR : SPECIFIC IMAGE NOT FOUND!\n");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * A utility function that rotates the specified image by the number of
	 * degrees soecified
	 * @param bufferedImage The image to be rotated
	 * @param degree The number of degrees by which the image is to be rotated
	 * @return The rotated image
	 */
	public static Image rotateImage(
			final BufferedImage bufferedImage,
			final int degree)
	{
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		int t = bufferedImage.getColorModel().getTransparency();

		BufferedImage i;
		Graphics2D graphics2d;

		//Converts the image to a Graphics2D object
		(graphics2d = (
				i = new BufferedImage(w, h, t)).createGraphics()).
				setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		//Use the Graphics2D rotate function to rotate the image
		graphics2d.rotate(Math.toRadians(degree), w / TWO, h / TWO);

		//Use the Graphics2D drawImage function to convert it back to an image
		graphics2d.drawImage(bufferedImage, 0, 0, null);

		graphics2d.dispose();

		return i;
	}
}
