package com.controller;

import com.model.Leaderboard;
import com.view.GameView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.model.MusicPlayer;

import java.util.Objects;

/**
 * This class is responsible for providing a loading (splash) screen, and a
 * UI to the user before the game starts. It is responsible for displaying a
 * main menu that provides the user with a multitude of options, including
 * sound controls, entering their name, viewing the top 5 high scores,
 * changing the look (theme) of the game and the snake, and of course,
 * launching the game itself.
 *
 * @author Ronak Mahesh
 * @see Leaderboard
 * @see com.view.GameView
 */
public class MainMenu extends Application
{
    //Constants:-
    private static final int FRAME_HEIGHT = 425;
    private static final int FRAME_WIDTH = 500;
    private static final int LOGO_HEIGHT = 180;
    private static final int LOGO_WIDTH = 500;
    private static final int TEXTBOX_W = 112;
    private static final int TEXTBOX_H = 20;
    private static final int SCALE = 5;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        displayMenu(primaryStage);
    }

    /**
     * The main method that displays the menu screen to users
     * @param primaryStage The JavaFX stage on which the menu is displayed
     */
    private void displayMenu(Stage primaryStage)
    {
        BorderPane borderPane = new BorderPane();
        primaryStage.setTitle("Snakee- Main Menu");

        Scene scene = new Scene(borderPane, FRAME_WIDTH, FRAME_HEIGHT);
        scene.getStylesheets().add("Styles.css");

        addMenuNodes(primaryStage, scene, borderPane);

        setLogo(borderPane);
        setBackground(borderPane);

        primaryStage.setScene(scene);

        Image icon = new Image(("snake-logo.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setHeight(FRAME_HEIGHT);
        primaryStage.setWidth(FRAME_WIDTH);

        //Exit the whole program when the main menu is closed
        primaryStage.setOnCloseRequest(e ->
                {
                    Platform.exit();
                    System.exit(0);
                });

        primaryStage.show();
    }

    /**
     * Method to add and format the buttons, checkboxes, and other JavaFX nodes
     * to the main menu screen
     * @param primaryStage The stage on which the menu is displayed
     * @param scene The scene for the main meny
     * @param pane The border pane that contains (and is used to describe the
     *            position of) these JavaFX nodes
     */
    private void addMenuNodes(Stage primaryStage, Scene scene, BorderPane pane)
    {
        CheckBox mute = new CheckBox("Mute");
        CheckBox once = new CheckBox("Play Audio Once");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Your Name:");
        nameField.setPrefSize(TEXTBOX_W, TEXTBOX_H);
        nameField.setMaxSize(TEXTBOX_W, TEXTBOX_H);

        Button button = new Button("Start Game!");

        ComboBox<String> themes = new ComboBox<>();
        themes.getItems().addAll("Classic Theme", "Neon Theme");
        themes.setValue("Classic Theme");

        play(button, mute, once, nameField, themes);

        Button viewHS = new Button("View Leaderboard");

        viewHS.setOnAction(actionEvent->
                new Leaderboard().init(primaryStage, scene));

        VBox buttons = new VBox();
        buttons.getChildren().add(button);
        buttons.getChildren().add(viewHS);
        buttons.getChildren().add(new Label(" "));

        pane.setCenter(buttons);

        VBox conf = new VBox(); //Game Configurations
        conf.setPrefSize(pane.getWidth()/SCALE, pane.getHeight());
        conf.getChildren().add(nameField);
        conf.getChildren().add(new Label(" "));
        conf.getChildren().add(themes);
        conf.getChildren().add(new Label(" "));
        conf.getChildren().add(mute);
        conf.getChildren().add(once);

        pane.setBottom(conf);
    }

    /**
     * Method to display the main logo of the game
     * @param borderPane The border pane that contains (and is used to
     *                   describe the position of) the logo
     */
    private void setLogo(BorderPane borderPane)
    {
        ImageView logo = new ImageView();
        logo.setImage(new Image("menu-logo.png"));
        logo.setFitHeight(LOGO_HEIGHT);
        logo.setFitWidth(LOGO_WIDTH);
        logo.setX(Pos.TOP_CENTER.ordinal());
        borderPane.setTop(logo);
    }

    /**
     * Method to set the background image of the main menu
     * @param borderPane The border pane used in the main menu
     */
    private void setBackground(BorderPane borderPane)
    {
        BackgroundImage background = new BackgroundImage(new Image(
                "menu.jpg", FRAME_WIDTH, FRAME_HEIGHT, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(background));
    }

    /**
     * Method to launch the game in one of 2 configurations: on mute, with
     * audio playing once, or with the audio looping infinitely (default)
     * @param button The button clicked to play the game
     * @param mute Checkbox that describes if the user wants the game muted
     * @param once Checkbox that describes if the user wants the audio to
     *             play only once
     * @param tf Text field that contains the user's name (set to "Player" if
     *           left empty)
     * @param themes Combobox that decides what theme the user has selected
     *               for the game
     */
    private void play(Button button, CheckBox mute, CheckBox once,
                      TextField tf, ComboBox<String> themes)
    {
        button.setOnAction(actionEvent ->
        {
            String name = "Player";
            String theme = themes.getValue();
            if(!Objects.equals(tf.getText(), ""))
                name = tf.getText();

            if(mute.isSelected() || (mute.isSelected() && once.isSelected()))
            {
                new GameView(theme).loadFrame(name);
            }
            else if(once.isSelected())
            {
                new GameView(theme).loadFrame(name);
                MusicPlayer.getMusicPlay("src/main/resources/frogger.mp3");
            }
            else
            {
                new GameView(theme).loadFrame(name);
                MusicPlayer player = new MusicPlayer("frogger.wav");
                player.playInLoop();
            }
        });
    }
}
