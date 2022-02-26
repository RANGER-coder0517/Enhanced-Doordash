package com.onlineOrder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineOrder.entity.Customer;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "HelloServlet", value = "/hello-servlet")
// the web address in the browser is: localhost:8080/hello-servlet;
// 'localhost' is the domain of the Tomcat on the PC;
// resource path: /hello-servlet; it means the request will be sent to the class with the value equal to "/hello-servlet";
//
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    //doGet function has two input parameters: request and response;
    //Tomcat will handle the response sending, we only need to finish the body of the response;
    //variable 'out' is the response;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //the below expression will tell the frontend the format of the response;
        //Application/JSON is usually seen in the brackets;
        response.setContentType("application/json");

        Customer customer = new Customer();
        customer.setEmail("sun@laioffer.com");
        ObjectMapper mapper = new ObjectMapper();

        PrintWriter customerPrint = response.getWriter();
        customerPrint.print(mapper.writeValueAsString(customer));

        JSONObject obj = new JSONObject();
        //the format of putting information into the output object is the key - value pair, like hashmap, the bottom implementation
        // of this class is exactly the hashmap; note that the sequence of the content displayed may not be consistent with the output on the web page,
        // so we cannot judge whether the code is valid through the sequence of the contents;
        obj.put("name", "Ranger Kang");
        obj.put("age", 25);
        obj.put("gender", "male");
        obj.put("email", "rangerzqkang@hotmail.com");

        PrintWriter print = response.getWriter();
        print.print(obj);

        // Hello;
        String customerTest = request.getParameter("customer");
        if (customerTest == null) {
            return;
        }
        //variable 'out' comes from below expression;
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        //<h1> means the message will be displayed with title manner, bold or larger;
        out.println("<h1>" + "Hello " + customer + "</h1>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Common IO can read the body of the request from the frontend;
        JSONObject obj = new JSONObject(IOUtils.toString(request.getReader()));
        String name = obj.getString("name");
        String gender = obj.getString("gender");
        int age = obj.getInt("age");
    }

    public void destroy() {
    }
}