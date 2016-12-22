package cysewska.com.services.help;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.validation.RequiredFieldValidator;
import cysewska.com.services.login.Login;
import cysewska.com.services.validators.ValidatorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ola on 2016-10-26.
 */
@Component
public class SendError implements Initializable {

    AnchorPane root;
    Stage stage;
@Autowired
    Login login;
    private final Logger logger = Logger.getLogger(this.getClass());
    public void showWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/send_email.fxml"));
        fxmlLoader.setController(this);
        try {
            root = (AnchorPane)fxmlLoader.load();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Wysyłanie zgłoszenia");
        stage.show();
      stage.setResizable(false);
        fxmlLoader.setController(SendError.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();

    }

    @Autowired
    ValidatorController validatorController;
    @FXML
    JFXTextArea t_statement;

    public void cancel(ActionEvent event){
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    public void send(ActionEvent event) throws FileNotFoundException {
        final String username = "egenAppHelper@gmail.com";
        final String password = "&g&n@pp!@#";


        if (!(t_statement.getValidators().get(0).getHasErrors() )){


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


            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Zgłoszenie wysłane przez: " +login.getLoginName() + "\nData: "
                    + new Date() + "\nTreść zgłoszonego błędu: " + t_statement.getText());
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);



            messageBodyPart = new MimeBodyPart();
            String filename = "C:/Users/Ola/Desktop/myApp.log";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);


            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);


        }
        File file = new File("C:/Users/Ola/Desktop/myApp.log");

        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();

        ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validatorController.setAreaValidators(validator, t_statement, "Podaj treść zgłoszenia");

    }
}
