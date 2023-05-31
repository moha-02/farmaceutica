package com.farmacia.clases;

import java.util.Date;

public class Xip {

    private int id;
    private Medicine medicine;
    private Patient patient;
    private Date date;

    @Override
    public String toString() {
        return "Xip{" +
                "id=" + id +
                ", medicine=" + medicine.getId() +
                ", patient=" + patient.getMail() +
                ", date=" + date +
                '}';
    }

    public Xip(int id, Medicine medicine, Patient patient, Date date) {
        this.id = id;
        this.medicine = medicine;
        this.patient = patient;
        this.date = date;
    }
    public Xip() { }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Medicine getMedicine() {
        return medicine;
    }
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public void load(int id) {
        Xip xip = DBmanagement.xipLoad(id);
        setPatient(xip.getPatient());
        setMedicine(xip.getMedicine());
        setDate(xip.getDate());
        setId(xip.getId());
    }


}
