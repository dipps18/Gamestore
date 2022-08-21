package package1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "Orders", urlPatterns = { "/orders" })
public class Orders extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(FuncLib.CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        try {
            Connection con = FuncLib.createConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(
              "SELECT* FROM ORDERS");
            out.println("<table border=\"1\" style=\"width:100\">");
            out.println("<tr><th>ORDER_ID</th><th>ORDER_DATE</th><th>GAME_ID</th><th>NET_AMOUNT</th><th>DISCOUNT</th><th>GROSS_AMOUNT</th>");
            while(rs.next()) {
                out.println("<tr><td>"+rs.getString("ORDER_ID")+"</td>" +
                    "<td>"+rs.getString("ORDER_DATE")+"</td>" +
                    "<td>"+rs.getString("GAME_ID")+"</td>" +
                    "<td>"+rs.getString("NET_AMOUNT")+"</td>"+
                    "<td>"+rs.getString("DISCOUNT")+"</td>" +
                    "<td>"+rs.getString("GROSS_AMOUNT")+"</td>" +
                    "<td><a href=\"/Project1/update" +
                    "?O_ID="+rs.getString("ORDER_ID")+
                    "&Date="+rs.getString("ORDER_DATE")+
                    "&G_ID="+rs.getString("GAME_ID")+
                    "&Price="+rs.getString("NET_AMOUNT")+
                    "&Disc="+rs.getString("DISCOUNT")+
                    "&Gross="+rs.getString("GROSS_AMOUNT")+"\">Update</a></td>" +
                    "<td><a href=\"/Project1/delete" +
                    "?O_ID="+rs.getString("ORDER_ID")+
                    "&Date="+rs.getString("ORDER_DATE")+
                    "&G_ID="+rs.getString("GAME_ID")+
                    "&Price="+rs.getString("NET_AMOUNT")+
                    "&Disc="+rs.getString("DISCOUNT")+
                    "&Gross="+rs.getString("GROSS_AMOUNT")+"\">Delete</a></td></tr>");
            }
        out.println("</table>");
        con.commit();
        con.close();
        rs.close();
        statement.close();
        }
        catch(Exception e) {
            out.println("Error :(");
            e.printStackTrace();
        }
   
        out.println("</body></html>");
        out.close();
    }
}
