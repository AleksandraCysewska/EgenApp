package cysewska.com.models.dto;

/**
 * Created by Ola on 2016-10-05.
 */
public class TextileClothDTO {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public TextileClothDTO(String name, Double quantity) {

        this.name = name;
        this.quantity = quantity;
    }

    Double quantity;
}
