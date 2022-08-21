package package1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "Games", urlPatterns = { "/games" })
public class Games extends HttpServlet {
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
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(
              "SELECT* FROM GAMES");
            out.println("<table border=\"1\" style=\"width:100\">");
            out.println("<tr><th>GAME_ID</th><th>GAME_NAME</th><th>RELEASE_DATE</th><th>PRICE</th><th>SCORE</th>");
            while(rs.next()) {
                out.println("<tr><td>"+rs.getString("GAME_ID")+"</td>" +
                    "<td>"+rs.getString("GAME_NAME")+"</td>" +
                    "<td>"+rs.getString("RELEASE_DATE")+"</td>" +
                    "<td>"+rs.getString("PRICE")+"</td>"+
                    "<td>"+rs.getString("SCORE")+"</tr>");
        
            }
        out.println("</table>");
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
