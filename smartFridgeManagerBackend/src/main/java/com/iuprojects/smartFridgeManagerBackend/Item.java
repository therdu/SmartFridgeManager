package com.iuprojects.smartFridgeManagerBackend;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date purchaseDate;
    private Date bestBeforeDate;
    private Date openingDate;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(Date date) {
        this.purchaseDate = date;
    }

    public Date getBestBeforeDate() {
        return this.bestBeforeDate;
    }

    public void setBestBeforeDate(Date date) {
        this.bestBeforeDate = date;
    }

    public Date getOpeningDate() {
        return this.openingDate;
    }

    public void setOpeningDate(Date date) {
        this.openingDate = date;
    }
}
