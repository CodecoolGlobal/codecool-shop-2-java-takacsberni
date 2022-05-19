package com.codecool.shop.controller;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.database_implementation.DatabaseManager;
import com.codecool.shop.dao.implementation.LineItemDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.utils.HTMLEmailTemplate;
import com.codecool.shop.utils.SendEmail;
import com.codecool.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(urlPatterns = {"/send_email"})
public class EmailSendController extends HttpServlet {
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    OrderDao orderDataStore = databaseManager.getOrderDataStore();
    LineItemDao lineItemDataStore = databaseManager.getLineItemDataStore();
    OrderService orderservice = new OrderService(orderDataStore, lineItemDataStore);

    public EmailSendController() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = orderservice.getOrderById(1);
        HashMap orderDetails = order.getCustomerData();
        order.setCustomerData(orderDetails);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw=response.getWriter();

        SendEmail sendEmail = new SendEmail(String.valueOf(orderDetails.get("email"))); //email address given by the user / form

        Map customerDataMap = request.getParameterMap();
        String emailData = convertMapToHTML(customerDataMap, orderDetails);

        String table = HTMLEmailTemplate.EMAIL_TEMPLATE.toString(); //TODO használhatjuk ezt is majd, ha szeretnénk / lesz rá idő


        try {
            SendEmail.sendMail(emailData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/confirmation");

        pw.close();
    }

    private String convertMapToHTML(Map customerDataMap, HashMap customerData){

        //TODO: akkor itt meg kéne megoldani, hogy HTML formátumot adjon vissza
        StringBuilder htmlBuilder = new StringBuilder();
        Iterator<Map.Entry<Integer, Integer>> entries = customerData.entrySet().iterator();
        htmlBuilder.append("<h3> Thank you for your order! </h3>" +
                "<table>");
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            htmlBuilder.append("<tr> <td>" +entry.getKey() + " = " + entry.getValue() + "</td> </tr>");
        }
        htmlBuilder.append("</table>");
        return htmlBuilder.toString();
    }

    private String buildTable(){
        String table =
                "<table class=\"table\">\n" +
                "  <thead class=\"thead-dark\">\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">#</th>\n" +
                "      <th scope=\"col\">First</th>\n" +
                "      <th scope=\"col\">Last</th>\n" +
                "      <th scope=\"col\">Handle</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">1</th>\n" +
                "      <td>Mark</td>\n" +
                "      <td>Otto</td>\n" +
                "      <td>@mdo</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">2</th>\n" +
                "      <td>Jacob</td>\n" +
                "      <td>Thornton</td>\n" +
                "      <td>@fat</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">3</th>\n" +
                "      <td>Larry</td>\n" +
                "      <td>the Bird</td>\n" +
                "      <td>@twitter</td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "\n" +
                "<table class=\"table\">\n" +
                "  <thead class=\"thead-light\">\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">#</th>\n" +
                "      <th scope=\"col\">First</th>\n" +
                "      <th scope=\"col\">Last</th>\n" +
                "      <th scope=\"col\">Handle</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">1</th>\n" +
                "      <td>Mark</td>\n" +
                "      <td>Otto</td>\n" +
                "      <td>@mdo</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">2</th>\n" +
                "      <td>Jacob</td>\n" +
                "      <td>Thornton</td>\n" +
                "      <td>@fat</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <th scope=\"row\">3</th>\n" +
                "      <td>Larry</td>\n" +
                "      <td>the Bird</td>\n" +
                "      <td>@twitter</td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>";


        return table;
    }

}
