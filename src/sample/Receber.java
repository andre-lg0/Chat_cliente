package sample;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Receber implements Runnable{
    private InputStream servidor;
    private String saida;
    private Controller controller;

    public Receber(InputStream servidor , Controller controller) {
        this.servidor = servidor;
        this.controller = controller;
    }

    @Override
    public void run()  {
        try {
            Scanner s  = new Scanner(this.servidor);
            while(s.hasNextLine()){
                this.saida = s.nextLine();
                this.controller.recebido(this.saida);
            }
        }catch (Exception e){
            System.out.println("problema na entrada do servidor");
        }
    }


    public String getSaida() {
        return saida;
    }
}
