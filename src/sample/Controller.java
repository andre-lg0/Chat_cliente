package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {

    Socket cliente;
    PrintWriter msg;


        public Controller() {
            Rede();
        }


    private  void Rede(){
        try {
            cliente = new Socket("127.0.0.1", 12345);
              msg =  new PrintWriter(cliente.getOutputStream());
        }catch (Exception e){

        }



    }


}
