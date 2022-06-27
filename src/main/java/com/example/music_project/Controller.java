package com.example.music_project;

import com.example.music_project.model.Albums;
import com.example.music_project.model.ArtistList;
import com.example.music_project.model.Artists;
import com.example.music_project.model.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    public TableView artistTable;
    @FXML
    private ProgressBar progressBar;

    public void listArtists() {
        Task<ObservableList<Artists>> task = new GetAllArtistsTask();

        artistTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        progressBar.setVisible(true);

        task.setOnSucceeded(e->progressBar.setVisible(false));
        task.setOnFailed(e->progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void listAlbumsForArtist() {
        final Artists artist = (Artists) artistTable.getSelectionModel().getSelectedItem();

        if (artist == null) {
            System.out.println("No artist is selected");
            return;
        }

        Task<ObservableList<Albums>> task = new Task<ObservableList<Albums>>() {
            @Override
            protected ObservableList<Albums> call() throws Exception {
                return FXCollections.observableArrayList
                        (Datasource.getInstance().queryAlbumForArtistId(artist.getId()));
            }
        };

        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void updateArtist() {
        final Artists artist = (Artists) artistTable.getItems().get(2);
        artistTable.getColumns().add("Album");
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return Datasource.getInstance().updateArtistName(artist.getId(),"AC/DC");
            }
        };

        task.setOnSucceeded(e -> {
            if (task.valueProperty().get()) {
                artist.setName("AC/DC");
                artistTable.refresh();
            }
        });
        new Thread(task).start();
    }

    @FXML
    public void listSong() {
        final Artists artist = (Artists) artistTable.getSelectionModel().getSelectedItem();

        if (artist == null) {
            System.out.println("No artist is selected");
            return;
        }

        Task<ObservableList<ArtistList>> task = new Task<>() {
            @Override
            protected ObservableList<ArtistList> call() throws Exception {
                return FXCollections.observableArrayList
                        (Datasource.getInstance().queryViewSonfForArtist(artist.getName()));
            }
        };
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