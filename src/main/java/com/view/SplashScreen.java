package com.view;

import com.controller.MainMenu;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * This is the class that is first used when the program is started up.
 * It is responsible for displaying a splash screen to the user using
 * animations and transitions.
 *
 * @author Ronak Mahesh
 */
public class SplashScreen extends Application
{
    //Constants:-
    private static final int IMAGE_HEIGHT = 300;
    private static final int IMAGE_WIDTH = 175;
    private static final int TRANSITION_TIME = 2;

    /**
     * @param stage The stage on which the splash screen is displayed
     */
    @Override
    public void start(Stage stage)
    {
        displaySplashScreen(stage);
    }

    /**
     * Main method used to display the splash screen to the user
     * @param stage The stage on which the splash screen is displayed
     */
    private void displaySplashScreen(Stage stage)
    {
        StackPane pane = new StackPane();

        BackgroundImage background = new BackgroundImage(
                new Image(
                        "splash-screen.png",
                        IMAGE_HEIGHT,
                        IMAGE_WIDTH,
                        false,
                        true),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(background));

        // Use a fade in transition from JavaFX for 2 seconds and display the
        // splash screen after that
        FadeTransition fadeIn =
                new FadeTransition(Duration.seconds(TRANSITION_TIME), pane);

        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        // After displaying the splash screen, fade out to the main menu
        FadeTransition fadeOut =
                new FadeTransition(Duration.seconds(TRANSITION_TIME), pane);

        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        Scene scene = new Scene(pane, IMAGE_HEIGHT, IMAGE_WIDTH);
        stage.setTitle("Loading Snakee...");
        Image icon = new Image(("snake-logo.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED); //Remove Borders
        stage.show();
        fadeIn.play();

        fadeIn.setOnFinished(e ->
        {
            fadeOut.play();
        });

        fadeOut.setOnFinished(e ->
        {
            stage.close();
            new MainMenu().start(new Stage());
        });
    }
}
