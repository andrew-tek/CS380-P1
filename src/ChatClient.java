//Andrew Tek
//CS 380
//4/11/17

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) throws Exception{
        try (Socket socket = new Socket("codebank.xyz", 38001)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            Scanner sc = new Scanner (System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Runnable messageReceiver = () -> {
            	String message;
            	while (true) {
	            	try {
	            		message = br.readLine();
	            		if (message != null) {
	            			System.out.println("Server:" + message);
	            		}
	            	}
	            	catch (Exception e){
	            		System.out.println(e);
	            	}
            	}
	            	
            };
            Thread messages = new Thread (messageReceiver);
            messages.start();
            String userInput = sc.nextLine();
            while(!userInput.equals("exit")) {
             out.println(userInput);
             userInput = sc.nextLine();
            }
           
            socket.close();
            
        }

	}

}
