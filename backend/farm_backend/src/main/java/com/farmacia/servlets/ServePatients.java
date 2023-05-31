package com.farmacia.servlets;

import com.farmacia.clases.Doctor;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet(name = "ServePatients", value = "/ServePatients")
public class ServePatients extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        int session = Integer.parseInt(request.getParameter("session"));
        Doctor doctor = new Doctor();
        boolean verif = doctor.isLogged(email,session);
        String error = null;

        if (verif == true){
            JSONArray jsonArray = new JSONArray();
            try {
                for (String str : doctor.listPatients()) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("email", str);
                    jsonArray.add(jsonObj);
                }
                response.setContentType("application/json");
                response.getWriter().write(jsonArray.toJSONString());

            }catch (Exception e){
                System.out.println("Error en try de ServePatients");
            }
        }else {
            response.getWriter().write(error);
        }
    }
}
