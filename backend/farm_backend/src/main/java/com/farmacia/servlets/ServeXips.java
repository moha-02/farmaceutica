package com.farmacia.servlets;

import com.farmacia.clases.Doctor;
import com.farmacia.clases.Xip;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServeXips", value = "/ServeXips")
public class ServeXips extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        int session = Integer.parseInt(request.getParameter("session"));


        Doctor doctor = new Doctor();
        boolean verif = doctor.isLogged(email,session);

        String resp = null;

        if (verif = true){
            try {
                doctor.load(email);
                doctor.loadReleaseList();
                resp = doctor.getTable();
                response.getWriter().write(resp);
            }catch (Exception e){
                System.out.println("Error en carga de datos");
            }
        }else {
            response.getWriter().write(resp);
        }








    }
}
