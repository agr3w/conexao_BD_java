package config;

import util.ErrorHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//** Classe para gerenciar a conexão com o banco de dados **/
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_usuarios"; // Altere para o seu banco
    private static final String USER = "root";
    private static final String PASSWORD = "agr3w";

    //** Método que retorna uma conexão com o banco de dados, lança SQLException **/
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //** Método alternativo com tratamento de erro interno **/
    public static Connection getSafeConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            ErrorHandler.handle(e, "Erro ao conectar com o banco de dados");
            return null;
        }
    }
}
