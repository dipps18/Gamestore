package package1;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Delete", urlPatterns = { "/delete" })
public class Delete extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Delete</title></head>");
        out.println("<body>");
        try{
            Connection con = FuncLib.createConnection();
            PreparedStatement ps=con.prepareStatement("DELETE FROM ORDERS WHERE ORDER_ID=?");
            ps.setInt(1, FuncLib.parseInteger(request.getParameter("O_ID")));
            try{
                ps.executeUpdate();
            }
            catch(SQLException e) {
                out.println("<p>Order not Deleted :( </p>");
                out.println("<p>"+e.getMessage()+"</p>");
                con.commit();
                ps.close();
                con.close();
                e.printStackTrace();
            }
            con.commit();
            ps.close();
            con.close();
            out.println("<p>Order Deleted</p>");
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        out.println("</body></html>");
        out.close();
    }
}
