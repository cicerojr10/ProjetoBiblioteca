package br.com.ciseravjorn.catalogo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


    private static final String URL =
            "jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true";

    private static final String USER = "root";

    private static final String PASSWORD = "123456";

    public static Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados: ", e);
        }
    }
}