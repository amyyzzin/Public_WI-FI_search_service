package com.example.project_01.servlet;

import com.example.project_01.service.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class WebHistoryDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id != null) {
            try {
                DBUtil.deleteWifiHistory(Integer.parseInt(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
