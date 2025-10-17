import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductMenu extends JFrame {
    public static void main(String[] args) {
        ProductMenu menu = new ProductMenu();
        menu.setSize(700, 600);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int selectedIndex = -1;
    private Database database;

    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTextField stokField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel kategoriLabel;
    private JRadioButton elektronikRadioButton;
    private JRadioButton alatTulisRadioButton;
    private JRadioButton makananRadioButton;
    private JRadioButton pakaianRadioButton;
    private JRadioButton minumanRadioButton;
    private JRadioButton semuaRadioButton;

    public ProductMenu() {
        database = new Database();
        productTable.setModel(loadTableData());
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        String[] kategoriData = {"???", "Elektronik", "Makanan", "Minuman", "Pakaian", "Alat Tulis"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));
        deleteButton.setVisible(false);

        // Tombol tambah/update
        addUpdateButton.addActionListener(e -> {
            if (selectedIndex == -1) {
                insertData();
            } else {
                updateData();
            }
        });

        // Tombol hapus
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Yakin mau dihapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) deleteData();
        });

        // Tombol batal
        cancelButton.addActionListener(e -> clearForm());

        // Klik pada tabel
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = productTable.getSelectedRow();
                if (row == -1) return;

                idField.setText(productTable.getValueAt(row, 1).toString());
                namaField.setText(productTable.getValueAt(row, 2).toString());
                hargaField.setText(productTable.getValueAt(row, 3).toString());
                kategoriComboBox.setSelectedItem(productTable.getValueAt(row, 4).toString());
                stokField.setText(productTable.getValueAt(row, 5).toString());

                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
                selectedIndex = row;
            }
        });

        // Grup filter
        ButtonGroup group = new ButtonGroup();
        group.add(elektronikRadioButton);
        group.add(alatTulisRadioButton);
        group.add(makananRadioButton);
        group.add(pakaianRadioButton);
        group.add(minumanRadioButton);
        group.add(semuaRadioButton);

        ActionListener filterListener = e -> {
            String kategori = ((JRadioButton) e.getSource()).getText();
            filterTable(kategori);
        };

        elektronikRadioButton.addActionListener(filterListener);
        alatTulisRadioButton.addActionListener(filterListener);
        makananRadioButton.addActionListener(filterListener);
        pakaianRadioButton.addActionListener(filterListener);
        minumanRadioButton.addActionListener(filterListener);
        semuaRadioButton.addActionListener(filterListener);

        semuaRadioButton.setSelected(true);
        filterTable("Semua");
    }

    /** Load data tabel dari database */
    private DefaultTableModel loadTableData() {
        Object[] cols = {"No", "ID Produk", "Nama", "Harga", "Kategori", "Stok"};
        DefaultTableModel model = new DefaultTableModel(null, cols);

        try {
            ResultSet rs = database.selectQuery("SELECT * FROM product");
            int no = 1;
            while (rs.next()) {
                Object[] row = {
                        no++,
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga"),
                        rs.getString("kategori"),
                        rs.getInt("stok")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data: " + e.getMessage());
        }

        return model;
    }

    /** Cek apakah input kosong */
    private boolean isFormEmpty() {
        return idField.getText().trim().isEmpty() ||
                namaField.getText().trim().isEmpty() ||
                hargaField.getText().trim().isEmpty() ||
                stokField.getText().trim().isEmpty() ||
                kategoriComboBox.getSelectedIndex() == 0;
    }

    /** Cek apakah ID sudah ada di database */
    private boolean isDuplicateID(String id) {
        try {
            ResultSet rs = database.selectQuery("SELECT id FROM product WHERE id='" + id + "'");
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saat mengecek ID: " + e.getMessage());
            return true;
        }
    }

    /** INSERT ke database */
    private void insertData() {
        if (isFormEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua kolom wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = idField.getText();
        if (isDuplicateID(id)) {
            JOptionPane.showMessageDialog(null, "ID sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            int stok = Integer.parseInt(stokField.getText());

            if (harga < 0 || stok < 0) {
                JOptionPane.showMessageDialog(null, "Harga dan stok tidak boleh negatif!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "INSERT INTO product VALUES ('" + id + "', '" + nama + "', " + harga + ", '" + kategori + "', " + stok + ")";
            database.insertUpdatDeleteQuery(sql);

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
            productTable.setModel(loadTableData());
            clearForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga dan stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** UPDATE data di database */
    private void updateData() {
        if (isFormEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua kolom wajib diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String id = idField.getText();
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            int stok = Integer.parseInt(stokField.getText());

            String sql = "UPDATE product SET nama='" + nama + "', harga=" + harga + ", kategori='" + kategori + "', stok=" + stok + " WHERE id='" + id + "'";
            database.insertUpdatDeleteQuery(sql);

            JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
            productTable.setModel(loadTableData());
            clearForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga dan stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** DELETE data dari database */
    private void deleteData() {
        String id = idField.getText();
        String sql = "DELETE FROM product WHERE id='" + id + "'";
        database.insertUpdatDeleteQuery(sql);

        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        productTable.setModel(loadTableData());
        clearForm();
    }

    /** Bersihkan form input */
    private void clearForm() {
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        stokField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }

    /** Filter tabel berdasarkan kategori */
    private void filterTable(String kategori) {
        if (kategori.equalsIgnoreCase("Semua")) {
            productTable.setModel(loadTableData());
            return;
        }

        Object[] cols = {"No", "ID Produk", "Nama", "Harga", "Kategori", "Stok"};
        DefaultTableModel model = new DefaultTableModel(null, cols);

        try {
            ResultSet rs = database.selectQuery("SELECT * FROM product WHERE kategori='" + kategori + "'");
            int no = 1;
            while (rs.next()) {
                Object[] row = {
                        no++,
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga"),
                        rs.getString("kategori"),
                        rs.getInt("stok")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal memfilter data: " + e.getMessage());
        }

        productTable.setModel(model);
    }
}
