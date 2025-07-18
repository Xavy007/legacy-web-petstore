package bo.edu.ucb.petstore.servlets;

import bo.edu.ucb.petstore.dao.DatabaseManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseManager.initializeDatabase();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // No es necesario hacer nada al destruir el contexto
    }
}
