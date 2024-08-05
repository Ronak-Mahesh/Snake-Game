package com.model;

import com.controller.MainMenu;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.*;

/**
 * This class is responsible for defining methods that allow music
 * files (such as mp3 and wav) to be fetched and played in different ways. An
 * object of this class runs on its own separate thread in parallel to the
 * game in order to be able to provide sound effects as the game is being
 * played. The class allows any piece of music to be played infinitely, or to
 * be played exactly once.
 *
 * @author Ronak Mahesh - Modified
 * @see MainMenu
 */
public class MusicPlayer extends Thread
{
	//Member Attributes:-
	private String filename;
	private Player player; //A javazoom music player

	/**
	 * Getter method for the name of the audio file to be played
	 * @return A string that corresponds to the name of the audio file to be
	 * played
	 */
	public String getFilename()
	{
		return this.filename;
	}

	/**
	 * Setter method for the name of the audio file to be played
	 * @param filename A string that corresponds to the new name of the audio
	 *                   file to be played
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}


	/**
	 * Constructor for the MusicPlayer class
	 * @param filename Name of the audio file to be played
	 */
	public MusicPlayer(String filename)
	{
		this.setFilename(filename);
	}

	/**
	 * A method that plays the audio file selected in a loop (permanently
	 * until the program ends)
	 *
	 * @throws RuntimeException if the specified file isn't found or is of an
	 * unsupported format
	 */
	public void playInLoop()
	{
		try
		{
			File musicPath = new File("src/main/resources/"+ getFilename());
			if(musicPath.exists())
			{
				AudioInputStream audioInput =
						AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();

				//Loop back to the start of the audio clip after it ends
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (UnsupportedAudioFileException
				 | IOException
				 | LineUnavailableException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * A method that plays the selected audio file just once
	 */
	public void playOnce()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				try
				{
					player = new Player(new BufferedInputStream(
							new FileInputStream(getFilename())));
					player.play();
				} catch (Exception e)
				{
					System.out.println(e);
				}
			}
		}.start();
	}

	/**
	 * A static method used to instantiate the music player class with the
	 * filename of an audio file. The method then proceeds to play the
	 * selected audio file once.
	 * @param filename Name of the audio file to be played
	 */
	public static void getMusicPlay(String filename)
	{
		MusicPlayer musicPlayer = new MusicPlayer(filename);
		musicPlayer.playOnce();
	}


}
