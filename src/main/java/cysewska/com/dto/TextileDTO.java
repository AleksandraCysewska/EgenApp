package cysewska.com.dto;

import cysewska.com.enums.Colors;

import javax.persistence.Column;

/**
 * Created by Ola on 2016-09-10.
 */
public class TextileDTO {
    String name;
    Colors colors;
    Integer textileQuantity;
    Integer textileThickness;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public Integer getTextileQuantity() {
        return textileQuantity;
    }

    public void setTextileQuantity(Integer textileQuantity) {
        this.textileQuantity = textileQuantity;
    }

    public Integer getTextileThickness() {
        return textileThickness;
    }

    public void setTextileThickness(Integer textileThickness) {
        this.textileThickness = textileThickness;
    }

    public TextileDTO(String name, Colors colors, Integer textileQuantity, Integer textileThickness) {

        this.name = name;
        this.colors = colors;
        this.textileQuantity = textileQuantity;
        this.textileThickness = textileThickness;
    }
}
