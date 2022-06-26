package com.example.music_project;

import com.example.music_project.model.Artists;
import com.example.music_project.model.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    public TableView<Artists> artistTable;

    public void listArtists() {
        Task<ObservableList<Artists>> task = new GetAllArtistsTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }
}

class GetAllArtistsTask extends Task {
    @Override
    protected ObservableList<Artists> call() throws Exception {
        return FXCollections.observableArrayList
                (Datasource.getInstance().queryArtists(Datasource.ORDER_BY_ASC));
    }
}