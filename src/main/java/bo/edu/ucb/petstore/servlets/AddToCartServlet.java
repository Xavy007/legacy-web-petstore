package bo.edu.ucb.petstore.servlets;

import bo.edu.ucb.petstore.dao.DatabaseManager;
import bo.edu.ucb.petstore.model.Pet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long petId = Long.parseLong(req.getParameter("id"));

        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pet WHERE id = ?");
            stmt.setLong(1, petId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pet pet = new Pet(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("species"),
                        rs.getInt("age"),
                        rs.getDouble("price"),
                        rs.getBoolean("available")
                );

                HttpSession session = req.getSession();
                List<Pet> cart = (List<Pet>) session.getAttribute("cart");
                if (cart == null) {
                    cart = new ArrayList<>();
                }
                cart.add(pet);
                session.setAttribute("cart", cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("./pets");
    }
}
