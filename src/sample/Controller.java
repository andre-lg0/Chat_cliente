package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.PrintWriter;
import java.net.Socket;

public class Controller {

   private  Socket cliente;
   private PrintWriter msg;
   private Receber recebe;

   @FXML
   private TextField Message;

   @FXML
   private TextArea textChat;

   @FXML
   private Button enviar;




   public Controller() {
       Rede();

   }

// Metodo para configuração do cliente e receber dados do servidor
        private  void Rede(){
        try {
            cliente = new Socket("127.0.0.1", 5000);
              msg =  new PrintWriter(cliente.getOutputStream());
              // classe recebe trata a mensagem que chega do servidor e envia para o chat
              recebe = new Receber(cliente.getInputStream(), this);
              Thread t  =  new Thread(recebe);
              t.start();

        }catch (Exception e){
            System.out.println("problema na conexao do servidor" + e);
        }



    }
    // metodo que pegar a entrada do usuario e envia para o servidor
    @FXML
    private void Send(){
            this.msg.println(this.Message.getText());
            this.msg.flush();
            this.textChat.appendText("Usuario: " + this.Message.getText() + "\n");
            this.Message.setText("");

    }

    public void recebido(String msg){
       textChat.appendText(msg + "\n");
    }

    @FXML
    private void close(){

    }
}
