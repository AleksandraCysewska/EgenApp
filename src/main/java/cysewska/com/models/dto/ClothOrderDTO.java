package cysewska.com.models.dto;

/**
 * Created by Ola on 2016-10-06.
 */
public class ClothOrderDTO {
    String name;
    Integer quantity;

    public ClothOrderDTO() {
    }

    public ClothOrderDTO(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

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
}
