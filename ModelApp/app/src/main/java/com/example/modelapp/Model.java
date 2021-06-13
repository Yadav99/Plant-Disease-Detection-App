package com.example.modelapp;

public class Model {

    private int id;
    private String symptoms;
    private String disease;
    private String remedies;

    //constructor


    public Model(int id, String symptoms, String disease, String remedies) {
        this.id = id;
        this.symptoms = symptoms;
        this.disease = disease;
        this.remedies = remedies;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", symptoms='" + symptoms + '\'' +
                ", disease='" + disease + '\'' +
                ", remedies='" + remedies + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getRemedies() {
        return remedies;
    }

    public void setRemedies(String remedies) {
        this.remedies = remedies;
    }
}
