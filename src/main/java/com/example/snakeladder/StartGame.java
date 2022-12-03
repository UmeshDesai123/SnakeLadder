package com.example.snakeladder;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartGame {
    public AnchorPane root;
    StartGame() throws IOException{
        root= FXMLLoader.load(getClass().getResource("StartGame.fxml"));

    }
}
