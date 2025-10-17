import java.sql.*;
import javax.swing.*;

public class Database {
    private Connection connection;
    private Statement statement;

    // Constructor: buka koneksi ke database
    public Database() {
        try {
            // Pastikan database "db_product" sudah ada di MySQL
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_product",
                    "root",
                    ""
            );
            statement = connection.createStatement();
            System.out.println("✅ Koneksi ke database berhasil!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal terhubung ke database: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    // SELECT query → hasilnya ResultSet
    public ResultSet selectQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal menjalankan SELECT query:\n" + e.getMessage(),
                    "SQL Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // INSERT, UPDATE, DELETE query
    public int insertUpdatDeleteQuery(String sql) {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Gagal menjalankan query:\n" + e.getMessage(),
                    "SQL Error",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    // Tutup koneksi (opsional, kalau kamu mau dipanggil saat aplikasi keluar)
    public void closeConnection() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("🔒 Koneksi database ditutup.");
        } catch (SQLException e) {
            System.err.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }
}
