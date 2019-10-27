package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller
{
    @FXML
    private Label myMessage;

    public void generateRandom(ActionEvent event)
    {
        Random rand = new Random();
        int myrand = rand.nextInt(50)+1;
        System.out.println(Integer.toString(myrand));
    }

    public void startTimer(ActionEvent event)
    {
        TimerThread timer = new TimerThread();


        myMessage.textProperty().bind(timer.messageProperty());
        new Thread(timer).start();

    }


}
