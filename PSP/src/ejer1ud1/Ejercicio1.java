package ejer1ud1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio1 {

    public static void main(String[] args) {
        String comando = obtenerComandoIP();
        
        ejecutarComando(comando);
        
    }

    private static String obtenerComandoIP() {
        String so = System.getProperty("os.name").toLowerCase();
        System.out.println(so);
        if (so.contains("windows")) {
        	return "ipconfig";
        }else if (so.contains("linux") || so.contains("mac")){
        	return "ifconfig";
        }
		return null;
    }

    private static void ejecutarComando(String comando) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(comando);
            Process proceso = processBuilder.start();

            InputStream is = proceso.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String linea;
            
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

