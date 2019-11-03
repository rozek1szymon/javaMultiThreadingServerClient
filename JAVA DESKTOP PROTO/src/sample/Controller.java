package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class Controller
{
    private InputStream input = null;
    private OutputStream output = null;
    private PrintWriter writer = null;
    private Socket socket = null;

    @FXML
    private Label myMessage;
    @FXML
    private TableView<Hotels> tableview;


    @FXML
    public void initialize() {
        TableColumn nameOfHotel = new TableColumn("Hotel");
        nameOfHotel.setCellValueFactory(new PropertyValueFactory<Hotels,String>("name"));

        TableColumn localization = new TableColumn("Localization");
        localization.setCellValueFactory(new PropertyValueFactory<Hotels,String>("localization"));

        TableColumn price = new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<Hotels,String>("price"));

       // TableColumn reserve = new TableColumn("reserve");
       // reserve.setCellValueFactory(new PropertyValueFactory<Hotels,String>("button"));

        tableview.getColumns().addAll(nameOfHotel, localization, price);
    }

    public void generateRandom(ActionEvent event)
    {
        Random rand = new Random();
        int myrand = rand.nextInt(50)+1;
        System.out.println(Integer.toString(myrand));
    }

    public void startTimer(ActionEvent event) throws IOException {
        TimerThread timer = new TimerThread();
        String line ="1";

        socket = new Socket("127.0.0.1", 5000);
        System.out.println("Connected");
        input = socket.getInputStream();
        output = socket.getOutputStream();
        writer = new PrintWriter(output,true);
        writer.println(line);
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(input));


        ObservableList<Hotels> lines = FXCollections.observableArrayList();
        String dataAboutHotels = null;
        while ((line = serverReader.readLine()) != null) {
            String info =  serverReader.readLine();
            info = info.replaceAll("\\s+","");
            String [] array = info.split(":");

           for (int i=0; i<array.length; i++) {
               String[] array1 = array[i].split(",");
               String hotel = array1[0];
               String localizations = array1[1];
               String pricec = array1[2];

               Hotels hotelik = new Hotels(hotel,localizations,pricec);
               lines.add(hotelik);
           }

        }
        tableview.setItems(lines);


        myMessage.textProperty().bind(timer.messageProperty());

        new Thread(timer).start();
        socket.close();

    }

    @FXML
    public void ReserveHotel(ActionEvent event)  throws IOException
    {
        String line ="2";
        String nameOfHotel  = tableview.getSelectionModel().getSelectedItem().getName();
        line += nameOfHotel;
        socket = new Socket("127.0.0.1", 5000);
        System.out.println("Connected");
        input = socket.getInputStream();
        output = socket.getOutputStream();
        writer = new PrintWriter(output,true);
        writer.println(line);

          tableview.getItems().removeAll(tableview.getSelectionModel().getSelectedItem());
          socket.close();
    }


}
