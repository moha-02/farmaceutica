package com.farmacia.servlets;
import com.farmacia.clases.DBmanagement;
import com.farmacia.clases.Doctor;
import com.farmacia.clases.Xip;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.sql.Connection;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Doctor doctor = new Doctor();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        doctor.login(email,password);
        int sesion = doctor.getSesison();

        if (sesion != 0){
            response.getWriter().write(String.valueOf(sesion));
        }
        else{
            String nulll = null;
            response.getWriter().write(nulll);
        }








    }
}
