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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TextileClothDTO(String name, Integer quantity) {

        this.name = name;
        this.quantity = quantity;
    }

    Integer quantity;
}
