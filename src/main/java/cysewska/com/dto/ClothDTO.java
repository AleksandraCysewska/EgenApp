package cysewska.com.dto;

import cysewska.com.enums.Model;

import java.util.Arrays;

/**
 * Created by cysewskaa on 2016-09-07.
 */
public class ClothDTO {

    Model model;
    String clothNamePL;
    String clothNameNO;
    String clothNameENG;
    Integer priceEuro;
    Integer pricePl;



    @Override
    public String toString() {
        return "ClothDTO{" +

                ", model=" + model +
                ", clothNamePL='" + clothNamePL + '\'' +
                ", clothNameNO='" + clothNameNO + '\'' +
                ", clothNameENG='" + clothNameENG + '\'' +
                ", priceEuro=" + priceEuro +
                ", pricePl=" + pricePl +
                '}';
    }



    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getClothNamePL() {
        return clothNamePL;
    }

    public void setClothNamePL(String clothNamePL) {
        this.clothNamePL = clothNamePL;
    }

    public String getClothNameNO() {
        return clothNameNO;
    }

    public void setClothNameNO(String clothNameNO) {
        this.clothNameNO = clothNameNO;
    }

    public String getClothNameENG() {
        return clothNameENG;
    }

    public void setClothNameENG(String clothNameENG) {
        this.clothNameENG = clothNameENG;
    }

    public Integer getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(Integer priceEuro) {
        this.priceEuro = priceEuro;
    }

    public Integer getPricePl() {
        return pricePl;
    }

    public void setPricePl(Integer pricePl) {
        this.pricePl = pricePl;
    }

    public ClothDTO( Model model, String clothNamePL, String clothNameNO, String clothNameENG,  Integer priceEuro, Integer pricePl) {


        this.model = model;
        this.clothNamePL = clothNamePL;
        this.clothNameNO = clothNameNO;
        this.clothNameENG = clothNameENG;
        this.priceEuro = priceEuro;
        this.pricePl = pricePl;
    }
}
