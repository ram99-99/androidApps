import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        final ServerSocket serverSocket;
        final Socket clientSocket;
        final InputStreamReader inputStreamReader;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);
        try {


            serverSocket = new ServerSocket(5000);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            inputStreamReader=new InputStreamReader(clientSocket.getInputStream());
            in = new BufferedReader(inputStreamReader);

			Thread receive = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        System.out.println(msg);

                        //tant que le client est connecté
                        while(msg != null) {
                            System.out.println("Client : " + msg);

                            msg = in.readLine();

                        }
                        System.out.println("Client disconect");

                        out.close();
                        clientSocket.close();
                        serverSocket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            });

            Thread sender = new Thread(new Runnable() {
                String msg; //variable that will contains the data writter by the user

                @Override   // annotation to override the run method
                public void run() {
                    while (true) {
                        msg = sc.nextLine();//reads data from user's keybord
                        System.out.println(msg);
                        out.println(msg);    // write data stored in msg in the clientSocket
                        out.flush();   // forces the sending of the data
                    }
                }

            });
			
			receive.start();
            sender.start();

            
           

        }catch (IOException e) {
            e.printStackTrace();
        }

    }


    }

