package cysewska.com.services.validators;

import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by Ola on 2016-10-26.
 */
@Component
public class Validator {


    public void validate(ConstraintViolationException e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błędne dane");
        alert.setHeaderText("Popraw dane");
        Set<ConstraintViolation<?>> constraintViolations =
                e.getConstraintViolations();
        String message="";
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            message =  message  + constraintViolation.getMessage() + " ";
        }
        alert.setContentText(message);
        alert.showAndWait();
    }
}
