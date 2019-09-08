package com.example.medicine;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Patient extends MedicalHuman {
    private String diagnosis;
    private List<Therapy> therapy = new ArrayList<>();

    public Patient(){

    }

    public Patient(String FIO, String phoneNumber, Date dateFirstVisited) {
        super(FIO, phoneNumber, dateFirstVisited);
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Therapy> getTherapy() {
        return therapy;
    }

    public void setTherapy(List<Therapy> therapy) {
        this.therapy = therapy;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "ID = " + getId() + '\'' +
                "name='" + getFIO() + '\'' +
                "phone='" + getPhoneNumber() + '\'' +
                "diagnosis='" + diagnosis + '\'' +
                ", therapy=" + therapy.toString() +
                '}';
    }


}
