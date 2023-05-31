package com.farmacia.clases;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import javax.print.Doc;
import javax.sound.midi.Soundbank;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class DBmanagement {

    //Permite conectares a la base de datos
    public static Connection openconnection() {
        Connection connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/farmacia", "root", "simo123");

        } catch (Exception e) {
            System.out.println("Error en el metodo de conexion");
        }
        return connection;
    }

    //Metodo para comparar mail y session  introducidos coinciden con las base de datos
    public static boolean checkTable(Connection connection, String email,int session){
        boolean verify = false;

        String query = "select*from farmacia.doctor  where doctor.mail = '"+email+"'and doctor.session = '"+session+"'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs  = stmt.executeQuery(query);
            if (rs.next() == false){
                verify = false;
            }
            else {
                verify = true;
            }
        }catch (SQLException e){
            System.out.println("Error en checkTable");
        }
        return verify;
    }

    //Metodo para introducir el date y la sesion de un docotor
    public static boolean checkLog(Connection connection, String email, String password){
        boolean verify = false;
        Random rand = new Random();
        int session = rand.nextInt((int) 10000000000L);
        LocalDate date = LocalDate.now();
        String query = "UPDATE farmacia.doctor set doctor.session = '"+session+"', doctor.last_log = '"+date+"' where doctor.mail = '"+email+"' and doctor.pass = '"+password+"'";
        try {
            Statement stmt = connection.createStatement();
            int i  = stmt.executeUpdate(query);
            if (i > 0){
                verify = true;
            }
            else {
                verify = false;
            }
        }catch (SQLException e){
            System.out.println("Error en checklog");
        }
        return  verify;
    }

    //Metodo para cargar los chips del doctor
    public static ArrayList<Xip> cargarChip(Connection con, String doctor_mail){
        ArrayList<Xip> xips = new ArrayList<Xip>();
        String query = "SELECT\n" +
                "xip.id,xip.`date`,medicine.id,medicine.name,medicine.tmax,medicine.tmin,patient.mail,patient.name\n" +
                "FROM \n" +
                "farmacia.xip\n" +
                "INNER JOIN farmacia.medicine ON medicine.id = xip.id_medicine\n" +
                "INNER JOIN farmacia.patient ON patient.mail = xip.id_patient\n" +
                "WHERE \n" +
                "xip.doctor_mail = '"+doctor_mail+"';";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Xip xip = null;
            Medicine medicine = null;
            Patient patient = null;
            while (rs.next()) {
                xip = new Xip();
                medicine = new Medicine();
                patient = new Patient();
                xip.setId(rs.getInt(1));
                xip.setDate(rs.getDate(2));
                medicine.setId(rs.getInt(3));
                medicine.setName(rs.getString(4));
                medicine.setTmax(rs.getFloat(5));
                medicine.setTmin(rs.getFloat(6));
                xip.setMedicine(medicine);
                patient.setMail(rs.getString(7));
                patient.setName(rs.getString(8));
                xip.setPatient(patient);
                xips.add(xip);
            }
        } catch (SQLException e) {
            System.out.println("Error en metodo query CargarChip");
        }
        finally {
            return xips;
        }
    }
    //Metodo para cargar los datos del doctor
    public static Doctor cargarDoc(Connection con, String id){
        Doctor doc = new Doctor();
        String query = "select*from farmacia.doctor where doctor.mail = '"+id+"';";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                doc.setMail(rs.getString(1));
                doc.setPass(rs.getString(2));
                doc.setName(rs.getString(3));
                doc.setLastLog(rs.getDate(4).toLocalDate());
                doc.setSesison(rs.getInt(5));
            }

        } catch (Exception e) {
            System.out.println("Error en metodo query doctor cargarDoc");
        }
        return doc;
    }

    public static Xip xipLoad(int id){
        Connection con = DBmanagement.openconnection();
        Xip xip = new Xip();
        String query = "SELECT\n" +
                "xip.id,xip.`date`,medicine.id,medicine.name,medicine.tmax,medicine.tmin,patient.mail,patient.name\n" +
                "FROM\n" +
                "farmacia.xip\n" +
                "INNER JOIN farmacia.medicine ON medicine.id = xip.id_medicine\n" +
                "INNER JOIN farmacia.patient ON patient.mail = xip.id_patient\n" +
                "WHERE\n" +
                "xip.id = '"+id+"'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Medicine medicine = null;
            Patient patient = null;
            while (rs.next()){
                medicine = new Medicine();
                patient = new Patient();
                xip.setId(rs.getInt(1));
                xip.setDate(rs.getDate(2));
                medicine.setId(rs.getInt(3));
                medicine.setName(rs.getString(4));
                medicine.setTmax(rs.getFloat(5));
                medicine.setTmin(rs.getFloat(6));
                xip.setMedicine(medicine);
                patient.setMail(rs.getString(7));
                patient.setName(rs.getString(8));
                xip.setPatient(patient);
            }
        }catch (Exception e){
            System.out.println("Error en query patientLoad");
        }
        return xip;
    }

    public static Patient  patientLoad(String id){
        Connection con = DBmanagement.openconnection();
        Patient patient = new Patient();
        String query = "SELECT patient.mail,patient.name\n" +
                "FROM patient\n" +
                "INNER JOIN xip ON patient.mail = xip.id_patient\n" +
                "WHERE xip.doctor_mail = '"+id+"';";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                patient.setMail(rs.getString(1));
                patient.setName(rs.getString(2));
            }
        }catch (Exception e){
            System.out.println("Error en query patientLoad");
        }
        return patient;
    }

    public static Medicine medicineLoad(int id){
        Medicine medicine = new Medicine();
        Connection connection = DBmanagement.openconnection();
        String query = "SELECT medicine.id,medicine.name,medicine.tmax,medicine.tmin\n" +
                "FROM medicine\n" +
                "WHERE medicine.id = '"+id+"';";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
               medicine.setId(rs.getInt(1));
               medicine.setName(rs.getString(2));
               medicine.setTmax(rs.getFloat(3));
               medicine.setTmin(rs.getFloat(4));
            }
        }catch (Exception e){
            System.out.println("Error en medicineLoad");
        }
        return medicine;
    }

    public static ArrayList<String> listPatient(){
        Connection con = DBmanagement.openconnection();
        ArrayList<String> listaPatient = new ArrayList<>();
        String query = "SELECT patient.mail\n" +
                "FROM patient;";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
               listaPatient.add(rs.getString(1));
            }
        }catch (Exception e){
            System.out.println("Error en listPatient");
        }

        return  listaPatient;
    }

    public static ArrayList<Medicine> listMedicine(){
        Connection con = DBmanagement.openconnection();
        ArrayList<Medicine> medicinas = new ArrayList<>();
        String query = "SELECT * FROM medicine;";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Medicine medicine = null;
            while (rs.next()){
                medicine = new Medicine();
                medicine.setId(rs.getInt(1));
                medicine.setName(rs.getString(2));
                medicine.setTmax(rs.getFloat(3));
                medicine.setTmin(rs.getFloat(4));
                medicinas.add(medicine);
            }
        }catch (Exception e){
            System.out.println("Error en query listMedicine");
        }

        return  medicinas;
    }

    public static boolean setXip(String email,int session,int xip,String pacientes,int medicamentos,String date){
        Connection connection = DBmanagement.openconnection();
        boolean verify = false;
        String query = "INSERT INTO farmacia.xip (id, doctor_mail, id_medicine, id_patient,`date`)\n" +
                "VALUES ('"+xip+"', '"+email+"', '"+medicamentos+"', '"+pacientes+"', '"+date+"');";

        try {
            Statement stmt = connection.createStatement();
            int i  = stmt.executeUpdate(query);
            if (i > 0){
                verify = true;
            }
            else {
                verify = false;
            }
        }catch (SQLException e){
            System.out.println();
        }


        return verify;
    }

}
