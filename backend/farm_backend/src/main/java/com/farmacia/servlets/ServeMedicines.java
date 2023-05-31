package com.farmacia.servlets;

import com.farmacia.clases.Doctor;
import com.farmacia.clases.Medicine;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

@WebServlet(name = "ServeMedicines", value = "/ServeMedicines")
public class ServeMedicines extends HttpServlet {
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
                for (Medicine med : doctor.listMedicins()) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("id", med.getId());
                    jsonObj.put("name", med.getName());
                    jsonObj.put("tempMax", med.getTmax());
                    jsonObj.put("tempMin", med.getTmin());
                    jsonArray.add(jsonObj);
                }
                response.setContentType("application/json");
                response.getWriter().write(jsonArray.toJSONString());

            }catch (Exception e){
                System.out.println("Error en try de ServeMedicines");
            }
        }else {
            response.getWriter().write(error);
        }
    }

}
