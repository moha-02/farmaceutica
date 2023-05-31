package com.farmacia.clases;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor extends Persona{

    private String pass;
    private LocalDate lastLog;
    private int sesison;
    private ArrayList<Xip> releaseList;

    public Doctor(String name, String email, String pass, LocalDate lastlog, int session, ArrayList<Xip> relaseList) {
        super(name, email);
        this.pass = pass;
        this.lastLog = lastlog;
        this.sesison = session;
        this.releaseList = relaseList;
    }
    public Doctor(){
        super();
    }

    public Doctor(String name, String email, String pass, LocalDate lastlog, int session) {
        super(name, email);
        this.pass = pass;
        this.lastLog = lastlog;
        this.sesison = session;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LocalDate getLastLog() {
        return lastLog;
    }

    public void setLastLog(LocalDate lastLog) {
        this.lastLog = lastLog;
    }

    public int getSesison() {
        return sesison;
    }

    public void setSesison(int sesison) {
        this.sesison = sesison;
    }

    public ArrayList<Xip> getReleaseList() {
        return releaseList;
    }

    public void setReleaseList(ArrayList<Xip> releaseList) {
        this.releaseList = releaseList;
    }


    //devuelve true si el usuario es correcto y carga los datos al doctor, sino devuelve false
    public  boolean isLogged(String mail,int session) {
        boolean logged = false;
        Connection connection = DBmanagement.openconnection();
        logged = DBmanagement.checkTable(connection,mail,session);
        return logged;
    }

    public void  login(String mail, String pass){
        boolean logged =false;
        Connection connection = DBmanagement.openconnection();
        logged = DBmanagement.checkLog(connection,mail,pass);
        if (logged == true){
            load(mail);
            loadReleaseList();
        }
    }

    //carga los datos del doctor que corresponedn al mail introducido
    @Override
    public void load(String id) {
        Connection connection = DBmanagement.openconnection();
        Doctor doc = DBmanagement.cargarDoc(connection,id);
        setMail(doc.getMail());
        setSesison(doc.getSesison());
        setName(doc.getName());
        setPass(doc.getPass());
        setLastLog(doc.getLastLog());
    }

    // carga al array del doctor los xips de la base de datos que le corresponden
    public void  loadReleaseList(){
        Connection connection = DBmanagement.openconnection();
        setReleaseList(DBmanagement.cargarChip(connection,this.getMail()));

    }
    public static void   loadReleaseList(String mail){
        Connection connection = DBmanagement.openconnection();
        ArrayList<Xip> xips =
        DBmanagement.cargarChip(connection,mail);

    }

    //devuelve String que corresponde a una Tabla en HTML de todos los xips de alta vigentes del doctor
    public  String getTable(){

        StringBuilder tabla = new StringBuilder();
        tabla.append("<table>");
        tabla.append("<thead>");
        tabla.append("<tr>" +
                "<th>Id</th>" +
                "<th>Doctor_Mail</th>" +
                "<th>Id_Medicina</th>" +
                "<th>Id_Paciente</th>" +
                "<th>Fecha</th>" +
                "</tr>");
        tabla.append("</thead>");
        tabla.append("<tbody>");
        // Recorre la lista de xips y agrega filas a la tabla
        for (Xip xip : this.getReleaseList()) {
            tabla.append("<tr>" +
                    "<td>'"+xip.getId()+"'</th>" +
                    "<td>'"+getMail()+"'</th>" +
                    "<td>'"+xip.getMedicine().getId()+"'</th>" +
                    "<td>'"+xip.getPatient().getMail()+"'</th>" +
                    "<td>'"+xip.getDate()+"'</th>" +
                    "</tr>");
        }
        tabla.append("</tbody>");
        tabla.append("</table>");
        return tabla.toString();
    }

    public ArrayList<String> listPatients(){
        ArrayList<String> listPatient = DBmanagement.listPatient();

        return listPatient;
    }

    public ArrayList<Medicine> listMedicins(){
        ArrayList<Medicine> listMedicnins = DBmanagement.listMedicine();
        return listMedicnins;
    }

    public boolean setXip(){
        boolean verify = false;

        return verify;
    }
}
