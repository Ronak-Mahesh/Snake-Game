package com.model;

import com.controller.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

/**
 * This JavaFX application is responsible for creating and displaying
 * the top 5 high scores when requested by the player. It reads the contents
 * of the high-scores file, sorts it, and displays the highest 5 scores in a
 * new scene, switching away from the Main Menu screen.
 *
 * @author Ronak Mahesh
 * @see MainMenu
 * @see com.view.GameView
 */
public class Leaderboard extends Application
{
    //Constants:-
    private static final int LIMIT = 5;
    private static final int FRAME_HEIGHT = 400;
    private static final int FRAME_WIDTH = 500;
    private static final int HEADING_SIZE = 30;
    private static final int FONT_SIZE = 23;
    private static final int CURVE = 12;

    //Attributes:-
    public static HashMap<Integer, String> scoreMap = new HashMap<>();
    public static TreeMap<Integer, String> sortedScores = new TreeMap<>();

    //Sorted ArrayList
    public static ArrayList<String> scores = new ArrayList<>();
    private int limit = LIMIT;

    /**
     * Getter method for the limit (i.e.- how many scores to be printed)
     * @return An integer, limit, that corresponds to how many high scores
     * are to be printed
     */
    public int getLimit()
    {
        return this.limit;
    }

    /**
     * Setter method for the limit (i.e.- how many scores to be printed)
     * @param limit An integer that decides how many high scores are to be
     *              printed
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {}

    /**
     * Method to initialize the JavaFX stage and scene
     * @param primaryStage The stage on which the leaderboard will go
     * @param original The original main menu scene
     */
    public void init(Stage primaryStage, Scene original)
    {
        BorderPane pane = new BorderPane();
        BackgroundImage background = new BackgroundImage(new Image(
                "menu.jpg", FRAME_WIDTH, FRAME_HEIGHT, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(background));

        Button back = new Button("Back To Main Menu");
        back.setStyle("-fx-background-radius: 0 0 "+ CURVE+ " 0;");
        back.setOnAction(actionEvent ->
        {
            primaryStage.setScene(original);
            primaryStage.setTitle("Snakee- Main Menu");
        });

        pane.setTop(back);

        sortScore();
        displayLeaderboard(pane);

        Scene scene = new Scene(pane);

        //Use CSS styles for all components in the scene
        scene.getStylesheets().add("Styles.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Leaderboard");
    }

    /**
     * Method to print the leaderboard to screen
     * @param pane The border pane on which the leaderboard is printed
     */
    private void displayLeaderboard(BorderPane pane)
    {
        VBox box = new VBox();
        for(Map.Entry<Integer, String> entry: sortedScores.entrySet())
            scores.add(""+entry.getValue()+": "+entry.getKey());

        Label[] labels = new Label[scores.size()+1];
        labels[0] = new Label("Top 5 High Scores:-");
        labels[0].setFont(new Font("Impact", HEADING_SIZE));
        labels[0].setTextFill(Color.web("#990329"));
        box.getChildren().add(labels[0]);
        box.getChildren().add(new Label(" "));

        Collections.reverse(scores);
        if(scores.size()<LIMIT)
            setLimit(scores.size());

        for(int i=1;i<=limit;i++)
        {
            Label tempLabel = new Label(scores.get(i-1));
            tempLabel.setText(""+i+") "+tempLabel.getText()+"\n\n");
            tempLabel.setFont(new Font("Copperplate", FONT_SIZE));
            tempLabel.setTextFill(Color.web("#990329"));
            labels[i] = tempLabel;
            box.getChildren().add(labels[i]);
        }

        pane.setCenter(box);
    }

    /**
     * A static method to sort the high scores from the text file by putting
     * them into a TreeMap structure that is naturally sorted
     */
    public static void sortScore()
    {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("HighScores.txt"));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] temp = line.split(":");
                scoreMap.put((Integer.parseInt(temp[0])), temp[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sortedScores.putAll(scoreMap);
    }

    /**
     * A method to add the score of the current game to the high scores text
     * file
     * @param score The score in the current game after it has ended
     * @param name The name of the player who the score belongs to
     */
    public static void updateLeaderboard(int score, String name)
    {
        boolean foundFlag = false;
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("HighScores.txt"));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] temp = line.split(":");
                if(Integer.parseInt(temp[0]) == score)
                {
                    temp[1] += "," + name;
                    foundFlag = true;
                }
                scoreMap.put((Integer.parseInt(temp[0])), temp[1]);
            }
            reader.close();
            try
            {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("HighScores.txt"));
                for(Map.Entry<Integer,String> entry : scoreMap.entrySet())
                {
                    writer.write(
                            entry.getKey()
                                    + ":"
                                    + entry.getValue()
                                    + "\n");
                }
                writer.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!foundFlag)
        {
            try
            {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("HighScores.txt", true));
                writer.write(score + ":" + name + "\n");
                writer.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
