package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLOutput;

public class Controller {

   private  Socket cliente;
   private Socket arquivo;
   private PrintWriter msg;
   private Receber recebe;

   @FXML
   private TextField Message;

   @FXML
   private TextArea textChat;

   @FXML
   private Button enviar;

   private FileChooser choose;


   public Controller() {
       Rede();
       this.choose = new FileChooser();


   }

// Metodo para configuração do cliente e receber dados do servidor
        private  void Rede(){
        try {
            this.cliente = new Socket("127.0.0.1", 5000);
            this.arquivo = new Socket("127.0.0.1", 5001);
             this.msg =  new PrintWriter(cliente.getOutputStream());
              // classe recebe trata a mensagem que chega do servidor e envia para o chat
              this.recebe = new Receber(cliente.getInputStream(), this);
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
            //this.textChat.appendText("Usuario: " + this.Message.getText() + "\n");
            this.Message.setText("");

    }

    public void recebido(String msg){
       textChat.appendText(this.cliente.getInetAddress().getHostName()
       + ": " + msg + "\n");
    }

    @FXML
    private void Arquivo() {
       cofiguraFile(this.choose);
       File file =  this.choose.showOpenDialog(null);
       if(file != null);
       try {
           byte[]buffer = new byte[1024];
           int bytes;
           FileInputStream FileEn = new FileInputStream(file);
           System.out.println("lendo o arquivo");
           OutputStream envia  = this.arquivo.getOutputStream();
           // enviando o arquivo
           while((bytes = FileEn.read(buffer))!= -1 ){
               envia.write(buffer,0,bytes);
               envia.flush();
           }

       }catch (Exception e){
           System.out.println("erro no arquvo");
       }

    }

    private static void cofiguraFile(FileChooser file){
       file.setTitle("Seleciona o arquivo");
       file.setInitialDirectory(new File(System.getProperty("user.home")));
    }
}
