package cysewska.com.models.dto;

/**
 * Created by Ola on 2016-10-27.
 */
public class TextilePWDTO {
    Double quantity;
    String name;

    @Override
    public String toString() {
        return "TextilePWDTO{" +
                "quantity=" + quantity +
                ", name='" + name + '\'' +
                '}';
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextilePWDTO() {

    }

    public TextilePWDTO(Double quantity, String name) {

        this.quantity = quantity;
        this.name = name;
    }
}
