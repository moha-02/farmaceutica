package com.farmacia.servlets;

import com.farmacia.clases.DBmanagement;
import com.sun.jdi.event.ExceptionEvent;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServRel", value = "/ServRel")
public class ServRel extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("mail");
        int session = Integer.parseInt(request.getParameter("session"));
        int xip = Integer.parseInt(request.getParameter("xip"));
        String pacientes = request.getParameter("pacientes");
        int medicamentos = Integer.parseInt(request.getParameter("medicamentos"));
        String date = request.getParameter("date");

        String devolver = "null";
        boolean verify= false;
        try {
            DBmanagement.setXip(email,session,xip,pacientes,medicamentos,date);
            verify = DBmanagement.setXip(email,session,xip,pacientes,medicamentos,date);;
            if (verify == true){
                devolver ="correcto";
            }
        }catch (Exception e){
            System.out.println("Error en verify");
        }

        response.getWriter().write(devolver);

    }
}
