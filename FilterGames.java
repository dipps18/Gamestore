package package1;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "FilterGames", urlPatterns = { "/filtergames" })
public class FilterGames extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Filtered Games</title></head>");
        out.println("<body>");
        try{
            Connection con = FuncLib.createConnection();
            PreparedStatement ps=con.prepareStatement("SELECT* FROM GAMES WHERE RELEASE_DATE>=? AND RELEASE_DATE<=? AND GAME_NAME LIKE ? AND SCORE >=?");
            ps.setDate(1, FuncLib.parseDate(request.getParameter("SDate")));
            ps.setDate(2, FuncLib.parseDate(request.getParameter("EDate")));
            ps.setString(3,"%"+request.getParameter("GName")+"%");
            ps.setDouble(4, FuncLib.parseDouble(request.getParameter("MinScore")));
            try{
    
                ResultSet rs=ps.executeQuery();
                out.println("<table border=\"1\" style=\"width:100\">");
                out.println("<tr><th>GAME_ID</th><th>GAME_NAME</th><th>RELEASE_DATE</th><th>PRICE</th><th>SCORE</th>");
                while(rs.next()) {
                    out.println("<tr><td>"+rs.getString("GAME_ID")+"</td>" +
                        "<td>"+rs.getString("GAME_NAME")+"</td>" +
                        "<td>"+rs.getString("RELEASE_DATE")+"</td>" +
                        "<td>"+rs.getString("PRICE")+"</td>"+
                        "<td>"+rs.getString("SCORE")+"</td>" +
                        "<td><a href=\"/Project1/insertorder" +
                        "?G_ID="+rs.getString("GAME_ID")+
                        "&Price="+rs.getString("PRICE")+
                        "&Date="+rs.getString("RELEASE_DATE")+"\">Buy</a></td></tr>");
                }
                out.println("</table>");
                con.close();
                ps.close();
                rs.close();
            }
            catch(SQLException e) {
                out.println("<p>Not updated :( </p>");
                e.printStackTrace();
                out.close();
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            out.close();
            
        }
        out.println("</body></html>");
        out.close();

    }
}
