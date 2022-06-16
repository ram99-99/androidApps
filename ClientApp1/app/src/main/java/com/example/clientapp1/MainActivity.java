package com.example.clientapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private PrintWriter output;
    private BufferedReader input;
    private Socket socket;

    Thread Thread1 = null;
   // EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMessages = findViewById(R.id.tvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        Button btnConnect = findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread1 = new Thread((new Thread1()));
                Thread1.start();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message = etMessage.getText().toString().trim();

                if (!message.isEmpty()) {

                    new Thread(new Thread3(message)).start();
                }
            }
        });
    }

    class Thread1 implements Runnable {
        public void run() {
            //Socket socket;
            try {
                 socket = new Socket("192.168.55.108",5000);
                //socket = new Socket(192.168.55.108, 5000);
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText("Connected\n");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    //input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    final String message = input.readLine();
                   if (message != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvMessages.append("server: " + message + "\n");
                            }
                        });

                    } else{
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Thread3 implements Runnable {
        private String message;
        Thread3(String message) {
            this.message = message;
        }

        @Override
        public void run() {
                    output.println(message);
                    output.flush();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   // tvMessages.setText("connected \n");
                    tvMessages.append( message + "\n");
                    etMessage.setText("");
                }
            });

        }
    }
}