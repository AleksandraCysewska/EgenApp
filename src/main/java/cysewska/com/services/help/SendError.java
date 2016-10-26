package cysewska.com.services.help;

import cysewska.com.services.contractors.AddContractorWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ola on 2016-10-26.
 */
@Component
public class SendError {

    AnchorPane root;
    Stage stage;

    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/send_email.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Wysyłanie zgłoszenia");
        stage.show();
        fxmlLoader.setController(SendError.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }

    @FXML
    TextArea t_statement;
    public void send(){
        final String username = "egenAppHelper@gmail.com";
        final String password = "&g&n@pp!@#";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("egenAppHelper@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("aleksandra.cysewska@gmail.com"));
            message.setSubject("Zgłoszenie buga");
            message.setText(t_statement.getText());
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
