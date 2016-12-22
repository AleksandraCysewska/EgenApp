package cysewska.com.models.dto;

import cysewska.com.models.enums.Colors;

/**
 * Created by Ola on 2016-09-10.
 */
public class TextileDTO {
    String name;
    String colors;
    Double textileQuantity;
    Double textileThickness;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Double getTextileQuantity() {
        return textileQuantity;
    }

    public void setTextileQuantity(Double textileQuantity) {
        this.textileQuantity = textileQuantity;
    }

    public Double getTextileThickness() {
        return textileThickness;
    }

    public void setTextileThickness(Double textileThickness) {
        this.textileThickness = textileThickness;
    }

    public TextileDTO(String name, String colors, Double textileQuantity, Double textileThickness) {

        this.name = name;
        this.colors = colors;
        this.textileQuantity = textileQuantity;
        this.textileThickness = textileThickness;
    }
}
