package com.example.chatapp2;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ChatAppController {
    @FXML private ListView<String> mensajeLista;
    @FXML private TextField mensajeField;

    private PrintWriter out;
    private BufferedReader in;

    public void initialize() {
        // Initialize the message list view if needed
    }

    public void setSocket(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        Executors.newSingleThreadExecutor().execute(() -> {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    String finalMessage = message;
                    Platform.runLater(() -> mensajeLista.getItems().add(finalMessage));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void sendMessage() {
        String message = mensajeField.getText();
        if (!message.isEmpty()) {
            out.println(message);
            mensajeField.clear();
        }
    }
}

