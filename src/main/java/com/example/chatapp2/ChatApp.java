package com.example.chatapp2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showLoginView();
    }

    private void showLoginView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Login");
        primaryStage.show();

        ChatAppLoginController controller = loader.getController();
        controller.setChatApp(this);
    }

    public void connectToServer(String usuario, String ip, int puerto) {
        try {
            Socket socket = new Socket(ip, puerto);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(usuario);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("CHATAPP");

            ChatAppController controller = loader.getController();
            controller.setSocket(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
