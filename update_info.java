package package1;

import java.io.IOException;
import java.io.PrintWriter;

import java.math.BigDecimal;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "update_info", urlPatterns = { "/update_info" })
public class update_info extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>update_info</title></head>");
        out.println("<body>");
        try{
            Connection con = FuncLib.createConnection();
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            PreparedStatement ps=con.prepareStatement("UPDATE ORDERS " +
                "SET ORDER_DATE=?,GAME_ID=?,NET_AMOUNT=?,DISCOUNT=?,GROSS_AMOUNT=? WHERE ORDER_ID=?");
            ps.setDate(1, FuncLib.parseDate(request.getParameter("Date")));
            ps.setInt(2, FuncLib.parseInteger(request.getParameter("G_ID")));
            ps.setObject(3, new BigDecimal(request.getParameter("Price")));
            ps.setObject(4, new BigDecimal(request.getParameter("Disc")));
            ps.setObject(5, new BigDecimal(request.getParameter("Gross")));
            ps.setInt(6, FuncLib.parseInteger(request.getParameter("O_ID")));
            
            try{
                ps.executeUpdate();
            }
            catch(SQLException e) {
                out.println("<p>Order not updated :( </p>");
                out.println("<p>"+e.getMessage()+"</p>");
                con.commit();
                ps.close();
                con.close();
                e.printStackTrace();
            }
            con.commit();
            ps.close();
            con.close();
            out.println("<p>Order Updated</p>");
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        out.println("</body></html>");
        out.close();
    }
}
