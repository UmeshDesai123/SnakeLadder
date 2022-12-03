package com.example.snakeladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class GamePageController {
    @FXML
    Text number;
    @FXML
    Text turnname;
    @FXML
    ImageView player1;
    @FXML
    ImageView player2;
    int turn= 1;
    HashMap<Pair<Double,Double>, Pair<Double,Double>> SnakeLadderCoordinateChanges;

    @FXML
    public void rolldice(MouseEvent event) throws IOException{
        getSnakeLadderCoordinates();
        Random random= new Random();
        int randomnumber= random.nextInt(6)+1;
        number.setText(""+randomnumber);
        if(turn==1){
            double moveX= player1.getTranslateX();
            double moveY= player1.getTranslateY();

            Pair<Double,Double> movementcoordinate= movement(moveX,moveY,randomnumber);
            player1.setTranslateX(movementcoordinate.getKey());
            player1.setTranslateY(movementcoordinate.getValue());
            if(SnakeLadderCoordinateChanges.containsKey(movementcoordinate)){
                player1.setTranslateX(SnakeLadderCoordinateChanges.get(movementcoordinate).getKey());
                player1.setTranslateY(SnakeLadderCoordinateChanges.get(movementcoordinate).getValue());
            }
            CheckWin(player1.getTranslateX(), player1.getTranslateY());
        }
        else{
            double moveX= player2.getTranslateX();
            double moveY= player2.getTranslateY();

            Pair<Double,Double> movementcoordinate= movement(moveX,moveY,randomnumber);
            player2.setTranslateX(movementcoordinate.getKey());
            player2.setTranslateY(movementcoordinate.getValue());
            if(SnakeLadderCoordinateChanges.containsKey(movementcoordinate)){
                player2.setTranslateX(SnakeLadderCoordinateChanges.get(movementcoordinate).getKey());
                player2.setTranslateY(SnakeLadderCoordinateChanges.get(movementcoordinate).getValue());
            }
            CheckWin(player2.getTranslateX(), player2.getTranslateY());
        }
        if(randomnumber!=6) {
            if (turn == 1){
                turnname.setText("Player 2 turn");
                turn = 2;
            }
            else{
                turnname.setText("Player 1 turn");
                turn = 1;
            }
        }
    }
    Pair<Double,Double> movement(double moveX, double moveY, int randomnumber){
        double X= moveX;
        double Y= moveY;
        if(moveY%100==0){
            moveX += randomnumber*50;
            if(moveX> 500){
                moveX= 500*2-moveX+50;
                moveY -= 50;
            }
        }
        else{
            moveX -= randomnumber*50;
            if(moveX<50){
                if(moveY==-450.0){
                    return new Pair<>(X,Y);
                }
                moveX= -1*(moveX-50);
                moveY -= 50;
            }
        }
        return new Pair<>(moveX,moveY);
    }
    void getSnakeLadderCoordinates(){
        SnakeLadderCoordinateChanges= new HashMap<>();
        SnakeLadderCoordinateChanges.put(new Pair<>(50.0,0.0), new Pair<>(150.0,-150.0));
        SnakeLadderCoordinateChanges.put(new Pair<>(200.0,0.0), new Pair<>(350.0,-50.0));

        SnakeLadderCoordinateChanges.put(new Pair <> (200.0, -50.0),new Pair <> (350.0, 0.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (450.0, 0.0),new Pair <> (500.0, -150.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (50.0, -100.0),new Pair <> (100.0, -200.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (400.0, -100.0),new Pair <> (200.0, -400.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (100.0, -300.0),new Pair <> (100.0, -50.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (50.0, 0.0),new Pair <> (150.0, -150.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (500.0, -250.0),new Pair <> (350.0, -300.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (500.0, -350.0),new Pair <> (500.0, -450.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (50.0, -350.0),new Pair <> (50.0, -450.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (200.0, -300.0),new Pair <> (50.0, -250.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (350.0, -250.0),new Pair <> (350.0, -150.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (150.0, -450.0),new Pair <> (100.0, -350.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (300.0, -450.0),new Pair <> (300.0, -350.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (400.0, -450.0),new Pair <> (400.0, -350.0));
        SnakeLadderCoordinateChanges.put(new Pair <> (350.0, -400.0),new Pair <> (200.0, -100.0));

    }
    void CheckWin(Double X, Double Y) throws IOException{
        if(X== 50.0 && Y== -450.0){
            Alert resultAlert= new Alert(Alert.AlertType.INFORMATION);
            resultAlert.setHeaderText("RESULT");
            if(turn==1){
                resultAlert.setContentText("Player 1 is Winner!!!");
                resultAlert.showAndWait();
            }
            else{
                resultAlert.setContentText("Player 2 is Winner!!!");
                resultAlert.showAndWait();
            }
            StartGame start= new StartGame();
            HelloApplication.root.getChildren().setAll(start.root);
        }
    }
}
