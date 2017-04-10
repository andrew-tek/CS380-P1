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
            	while (true) {
	            	try {
	            		System.out.println(br.readLine());
	            	}
	            	catch (Exception e){}
	            	}
            };
            Thread messages = new Thread (messageReceiver);
            messages.start();
            System.out.print("Client> ");
            String userInput = sc.nextLine();
            while(!userInput.equals("exit")) {
             out.println(userInput);
             System.out.println(br.readLine());
             userInput = sc.nextLine();
            }
           
            socket.close();
            
        }

	}

}
