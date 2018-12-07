package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {

   private  Socket cliente;
   private PrintWriter msg;

   @FXML
   private TextField Message;

   @FXML
   private TextArea textChat;

   @FXML
   private Button enviar;



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

    @FXML
    private void Send(){
            this.msg.println(this.Message.getText());
            this.textChat.appendText("Usuario: " + this.Message.getText() + "\n");
            this.Message.setText("");
    }


}
