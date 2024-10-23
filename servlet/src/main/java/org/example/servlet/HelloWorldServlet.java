package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Use the @WebServlet annotation to specify the URL pattern to access this servlet
@WebServlet("/")
public class HelloWorldServlet extends HttpServlet {

//dispatcher servlet /**
    // Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the response content type to text/html
        //response.setContentType("text/html");

        // Write the response
        PrintWriter out = response.getWriter();

        out.write( "Hello on main page");

//        out.println("<html><body>");
//        out.println("<h1>Hello, World!</h1>");
//        out.println("</body></html>");
    }
}