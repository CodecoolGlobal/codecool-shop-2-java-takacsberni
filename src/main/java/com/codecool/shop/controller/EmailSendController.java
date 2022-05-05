package com.codecool.shop.controller;

import com.codecool.shop.send_email.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/send_email"})
public class EmailSendController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        SendEmail sendEmail = new SendEmail("takacsberni@gmail.com");

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String data = buffer.toString();
        try {
            SendEmail.sendMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
