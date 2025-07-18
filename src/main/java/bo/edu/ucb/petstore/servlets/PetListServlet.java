package bo.edu.ucb.petstore.servlets;

import bo.edu.ucb.petstore.dao.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PetListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>Pet Store</title></head>");
        out.println("<body>");
        out.println("<h1>Welcome to the Pet Store</h1>");        out.println("<a href=\"./cart\">View Cart</a> | <a href=\"./history\">View Transaction History</a>");
        out.println("<h2>Available Pets</h2>");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Name</th><th>Species</th><th>Age</th><th>Price</th><th>Action</th></tr>");

        try (Connection conn = DatabaseManager.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM pet WHERE available = true");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("species") + "</td>");
                out.println("<td>" + rs.getInt("age") + "</td>");
                out.println("<td>" + rs.getDouble("price") + "</td>");
                out.println("<td><a href=\"./add-to-cart?id=" + rs.getLong("id") + "\">Add to Cart</a></td>");
                out.println("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>Error retrieving pets from the database.</p>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
