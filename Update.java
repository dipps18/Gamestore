package package1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Update", urlPatterns = { "/update" })
public class Update extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head><title>Update</title></head>");
        out.println("<body>");
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" + 
        "<form name=\"FilterForm\" action=\"update_info\" method=\"get\">" + 
        "<table cellspacing=\"2\" cellpadding=\"3\" border=\"1\" width=\"50%\">" + 
        "<tr><td width=\"50%\">ORDER_ID</td><td width=\"50%\">"+request.getParameter("O_ID")+"<input type=\"hidden\"name=\"O_ID\"value=\""+request.getParameter("O_ID")+"\"></td></tr>" +
        "<tr><td width=\"50%\">ORDER_DATE</td><td width=\"50%\">\n<input type=\"text\" name=\"Date\"value=\""+request.getParameter("Date")+"\"></td></tr>" + 
        "<tr><td width=\"50%\">GAME_ID</td><td width=\"50%\"><input type=\"text\" name=\"G_ID\"/value=\""+request.getParameter("G_ID")+"\"></td></tr>" +
        "<tr><td width=\"50%\">NET_AMOUNT</td><td width=\"50%\"><input type=\"text\" name=\"Price\"/value=\""+request.getParameter("Price")+"\"></td></tr>"+ 
        "<tr><td width=\"50%\">DISCOUNT</td><td width=\"50%\"><input type=\"text\" name=\"Disc\" value=\""+request.getParameter("Disc")+"\"></td></tr>" +
        "<tr><td width=\"50%\">GROSS_AMOUNT</td><td width=\"50%\"><input type=\"text\" name=\"Gross\" value=\""+request.getParameter("Gross")+"\"></td></tr>" +
        "<td width=\"50%\">&nbsp;</td><td width=\"50%\"><input type=\"submit\" name=\"Submit\"/></td></tr>"+ 
        "</table>" + 
        "</form>");
        out.println("</body></html>");
        out.close();
    }
}
