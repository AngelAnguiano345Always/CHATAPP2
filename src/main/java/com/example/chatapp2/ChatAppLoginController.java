package com.example.chatapp2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChatAppLoginController {
    @FXML private TextField usuarioField;
    @FXML private TextField ipField;
    @FXML private TextField puertoField;

    private ChatApp chatApp;

    public void setChatApp(ChatApp chatApp) {
        this.chatApp = chatApp;
    }

    @FXML
    private void connect(ActionEvent event) {
        String usuario = usuarioField.getText();
        String ip = ipField.getText();
        int puerto = Integer.parseInt(puertoField.getText());
        chatApp.connectToServer(usuario, ip, puerto);

        Stage stage = (Stage) usuarioField.getScene().getWindow();
        stage.close();
    }
}
