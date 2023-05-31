package com.farmacia.clases;

import java.sql.Connection;

public abstract class Persona {
    protected String name;
    protected String mail;

    public Persona(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }
    public Persona(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public abstract void load(String id);

    //carga los datos del doctor que corresponedn al mail

}
