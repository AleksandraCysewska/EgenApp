package cysewska.com.services.validators.services;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.DefaultProperty;
import javafx.scene.control.TextInputControl;

/**
 * Created by Ola on 2016-12-15.
 */


@DefaultProperty("icon")
public class StringValidator extends ValidatorBase {

    public StringValidator() {
    }
    protected void eval() {
        if(this.srcControl.get() instanceof TextInputControl) {
            this.evalTextInputField();
        }
        if(this.srcControl.get() instanceof JFXComboBox) {
            this.evalComboBoxField();
        }

    }
    private void evalTextInputField() {
        TextInputControl textField = (TextInputControl)this.srcControl.get();
        if(textField.getText()!= null &&  textField.getText().matches("[a-z, A-Z]+")){
            this.hasErrors.set(false);}
        else{

            this.hasErrors.set(true);

        }

    }
    private void evalComboBoxField() {
        JFXComboBox comboField = (JFXComboBox)this.srcControl.get();
        boolean valid = comboField.getValue() != null;
        valid |= comboField.isEditable() && comboField.getJFXEditor().getText() != null && !comboField.getJFXEditor().getText().isEmpty();
        if(valid) {
            this.hasErrors.set(false);
        } else {
            this.hasErrors.set(true);
        }

    }
}
