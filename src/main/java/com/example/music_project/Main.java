package com.example.music_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("Music Database!");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}