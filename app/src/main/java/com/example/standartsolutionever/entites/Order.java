package com.example.standartsolutionever.entites;

public class Order {

    private Integer Id;
    private Double quantity;
    private String provider;
    private String carrier;
    private String kindRwm;
    private String typeRwm;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getKindRwm() {
        return kindRwm;
    }

    public void setKindRwm(String kindRwm) {
        this.kindRwm = kindRwm;
    }

    public String getTypeRwm() {
        return typeRwm;
    }

    public void setTypeRwm(String typeRwm) {
        this.typeRwm = typeRwm;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

   ;
}
