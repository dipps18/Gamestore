package package1;

import java.io.IOException;
import java.io.PrintWriter;

import java.math.BigDecimal;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "InsertOrder", urlPatterns = { "/insertorder" })
public class InsertOrder extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>InsertOrder</title></head>");
        out.println("<body>");
        try{
            Connection con = FuncLib.createConnection();
            PreparedStatement ps=con.prepareStatement("INSERT INTO ORDERS(ORDER_DATE,GAME_ID,NET_AMOUNT,DISCOUNT,GROSS_AMOUNT) VALUES(?,?,?,?,?)");
            con.setAutoCommit(true);
            ps.setDate(1, FuncLib.parseDate(FuncLib.getCurDate()));
            ps.setInt(2, FuncLib.parseInteger(request.getParameter("G_ID")));
            ps.setObject(3, new BigDecimal(request.getParameter("Price")));
            ps.setObject(4, FuncLib.getDiscount(request.getParameter("Date")));
            
            ps.setObject(5,FuncLib.getGrossAmt(new BigDecimal(request.getParameter("Price")),FuncLib.getDiscount(request.getParameter("Date"))));
            try{
                ps.executeUpdate();
            }
            catch(SQLException e)
            {
                out.println("<p> Order not Added!  </p>");
                out.println("<p> " + e.getMessage() + " </p>");
                con.commit();
                con.close();
                ps.close();
                
                return;
            }
            ps.close();
            con.close();
            out.println("<p>Order Created</p>");
        }
        catch(Exception e){
            e.printStackTrace();
            out.println("<p>Order not created :( </p>");
        }
                            
        out.println("</body></html>");
        out.close();
    }
}
