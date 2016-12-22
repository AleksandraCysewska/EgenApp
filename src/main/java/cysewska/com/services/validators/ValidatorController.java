package cysewska.com.services.validators;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import cysewska.com.services.validators.services.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Ola on 2016-12-10.
 */
@Component
public class ValidatorController {

    public void setValidators(RequiredFieldValidator validator, JFXTextField t_name, String warning) {
        t_name.getValidators().add(validator);
        try {
            Image imageIcon = new Image(new FileInputStream("C:\\Users\\Ola\\workspace\\EgenApp666\\src\\main\\resources\\error.png"));
            validator.setIcon(new ImageView(imageIcon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        validator.setMessage(warning);
        t_name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                t_name.validate();
            }
        });
    }
    public void setEmailValidators(EmailValidator validator, JFXTextField t_name, String warning) {
        t_name.getValidators().add(validator);
        try {
            Image imageIcon = new Image(new FileInputStream("C:\\Users\\Ola\\workspace\\EgenApp666\\src\\main\\resources\\error.png"));
            validator.setIcon(new ImageView(imageIcon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        validator.setMessage(warning);
        t_name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                t_name.validate();
            }
        });
    }
    public void setValidatorsIfContainLetter(RequiredFieldValidator validator, JFXTextField t_name, String warning) {
        t_name.getValidators().add(validator);
        try {
            Image imageIcon = new Image(new FileInputStream("C:\\Users\\Ola\\workspace\\EgenApp666\\src\\main\\resources\\error.png"));
            validator.setIcon(new ImageView(imageIcon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        validator.setMessage(warning);
        t_name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue  ) {

                t_name.validate();
                System.out.println("Waliduję");

            }
        });
    }
    public void setStringValidator(StringValidator validator, JFXTextField t_name, String warning) {
        t_name.getValidators().add(validator);
        try {
            Image imageIcon = new Image(new FileInputStream("C:\\Users\\Ola\\workspace\\EgenApp666\\src\\main\\resources\\error.png"));
            validator.setIcon(new ImageView(imageIcon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        validator.setMessage(warning);
        t_name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue  ) {

                t_name.validate();
                System.out.println("Waliduję");

            }
        });
    }
    public void setValidatorsIfContainLetter(DoubleValidator validator, JFXTextField t_name, String warning) {
        t_name.getValidators().add(validator);
        try {
            Image imageIcon = new Image(new FileInputStream("C:\\Users\\Ola\\workspace\\EgenApp666\\src\\main\\resources\\error.png"));
            validator.setIcon(new ImageView(imageIcon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        validator.setMessage(warning);
        t_name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue  ) {
                t_name.validate();
            }
        });
    }
    public void setIntegerValidators(IntegerValidator validator, JFXTextField t_name, String warning) {
        t_name.getValidators().add(validator);
        try {
            Image imageIcon = new Image(new FileInputStream("C:\\Users\\Ola\\workspace\\EgenApp666\\src\\main\\resources\\error.png"));
            validator.setIcon(new ImageView(imageIcon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        validator.setMessage(warning);
        t_name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue  ) {
                t_name.validate();
            }
        });
    }

    public void setValidatorsIfContainLetter(DoubleWithNullValidator validator, JFXTextField t_name, String warning) {
        t_name.getValidators().add(validator);
        try {
            Image imageIcon = new Image(new FileInputStream("C:\\Users\\Ola\\workspace\\EgenApp666\\src\\main\\resources\\error.png"));
            validator.setIcon(new ImageView(imageIcon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        validator.setMessage(warning);
        t_name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue  ) {

                t_name.validate();
                System.out.println("Waliduję");

            }
        });
    }
    public void setAreaValidators(RequiredFieldValidator validator, JFXTextArea t_name, String warning) {
        t_name.getValidators().add(validator);
        try {
            Image imageIcon = new Image(new FileInputStream("C:\\Users\\Ola\\workspace\\EgenApp666\\src\\main\\resources\\error.png"));
            validator.setIcon(new ImageView(imageIcon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        validator.setMessage(warning);
        t_name.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue && t_name.getText().contains("[a-zA-Z]+")) {
                t_name.validate();
            }
        });
    }
    public void setZipValidators(ZipValidator validator, JFXTextField t_name, String warning) {
        t_name.getValidators().add(validator);
        try {
            Image imageIcon = new Image(new FileInputStream("C:\\Users\\Ola\\workspace\\EgenApp666\\src\\main\\resources\\error.png"));
            validator.setIcon(new ImageView(imageIcon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        validator.setMessage(warning);
        t_name.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue ) {
                t_name.validate();
            }
        });
    }

}
