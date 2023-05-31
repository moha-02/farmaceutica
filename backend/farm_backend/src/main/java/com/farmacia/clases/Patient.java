package com.farmacia.clases;

import java.sql.Connection;
import java.util.ArrayList;

public class Patient extends Persona{

    @Override
    public void load(String id) {
        Patient patient = DBmanagement.patientLoad(id);
        setMail(patient.getMail());
        setName(patient.getName());
    }

    public Patient(String name, String mail) {
        super(name, mail);
    }

    public Patient() {}

}
