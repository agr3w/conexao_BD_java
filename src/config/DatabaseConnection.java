package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//** Classe para gerenciar a conexão com o banco de dados
public class DatabaseConnection {
    //** Configure a URL, usuário e senha conforme o seu ambiente de banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_usuarios"; // altere para o seu banco
    private static final String USER = "root";
    private static final String PASSWORD = "agr3w123";

    //** Método que retorna uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
