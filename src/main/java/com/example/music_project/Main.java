package com.example.music_project;

import com.example.music_project.model.Datasource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.listArtists();
        stage.setTitle("Music Database");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        if (!Datasource.getInstance().open()) {
            System.out.println("Fatal Error: Couldn't connect to database!");
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();
    }

    public static void main(String[] args) {
        launch();
    }
}