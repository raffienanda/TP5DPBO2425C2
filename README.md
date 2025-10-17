# JANJI
Saya Raffie Arsy Ananda dengan NIM 2405916 mengerjakan Tugas Praktikum 3 dalam mata kuliah Desain Pemrograman Berbasis Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# 📌 DESAIN PROGRAM
Program ini merupakan aplikasi CRUD (Create, Read, Update, Delete) berbasis Java Swing yang digunakan untuk mengelola data produk dalam sebuah database MySQL.
Data produk meliputi:
- ID Produk
- Nama Produk
- Harga Produk
- Kategori Produk
- Stok Produk

# 🔄 Penjelasan Alur Program
1. Program dijalankan
Saat program ProductMenu dijalankan, konstruktor dari kelas tersebut otomatis dipanggil. Di dalam konstruktor, program membuat koneksi ke database menggunakan kelas Database, kemudian memanggil fungsi loadTableData() untuk menampilkan seluruh data produk dari tabel product di MySQL. Selain itu, konstruktor juga mengatur isi pada combo box kategori, menyiapkan tombol-tombol aksi seperti Add, Update, Delete, dan Cancel, serta mengaktifkan fitur filter kategori menggunakan radio button.

3. Menampilkan data dari database
Proses penampilan data dilakukan melalui fungsi loadTableData(). Fungsi ini menjalankan perintah SQL SELECT * FROM product untuk mengambil seluruh isi tabel dari database. Hasil query tersebut dibaca baris demi baris, kemudian setiap baris ditampilkan ke dalam tabel (JTable) pada tampilan GUI. Setiap baris tabel berisi nomor urut, ID produk, nama produk, harga, kategori, dan jumlah stok.

3. Menambah data baru (Add)
Ketika pengguna mengisi seluruh kolom input (ID, Nama, Harga, Kategori, dan Stok) lalu menekan tombol Add, program terlebih dahulu memeriksa apakah semua kolom sudah terisi menggunakan fungsi isFormEmpty(). Setelah itu, program mengecek apakah ID produk sudah terdaftar di database dengan fungsi isDuplicateID(). Jika semua validasi lolos, program akan menjalankan perintah SQL INSERT INTO product VALUES (...) untuk menyimpan data baru ke tabel product. Setelah berhasil, data baru akan langsung muncul di tabel tampilan.

4. Memilih data untuk diedit
Saat pengguna mengklik salah satu baris pada tabel produk, data dari baris tersebut secara otomatis ditampilkan kembali ke form input di bagian atas. Pada kondisi ini, tombol Add akan berubah menjadi Update, dan tombol Delete akan muncul. Hal ini menunjukkan bahwa pengguna sedang berada dalam mode edit dan siap untuk memperbarui atau menghapus data yang dipilih.

5. Mengubah data (Update)
Setelah pengguna mengedit data pada form (misalnya mengganti nama, harga, atau stok), pengguna dapat menekan tombol Update untuk menyimpan perubahan. Program kemudian menjalankan perintah SQL UPDATE product SET ... WHERE id='...' untuk memperbarui data produk di database berdasarkan ID-nya. Jika proses update berhasil, tabel akan dimuat ulang agar menampilkan data terbaru, dan form input dikosongkan kembali.

6. Menghapus data (Delete)
Ketika pengguna ingin menghapus data produk, pengguna dapat menekan tombol Delete. Sebelum proses penghapusan dilakukan, program akan menampilkan dialog konfirmasi untuk memastikan pilihan pengguna. Jika pengguna menekan “Yes”, maka perintah SQL DELETE FROM product WHERE id='...' akan dijalankan untuk menghapus data dari database. Setelah data berhasil dihapus, tabel akan diperbarui dan form kembali ke kondisi awal.

7. Membatalkan input (Cancel)
Tombol Cancel berfungsi untuk menghapus semua isi dari form input dan mengembalikan tampilan ke mode semula. Dalam mode ini, tombol Add aktif kembali, tombol Delete disembunyikan, dan tidak ada data yang sedang dipilih di tabel. Fitur ini membantu pengguna agar dapat memulai input baru tanpa harus menutup program.

8. Menyaring data berdasarkan kategori (Filter)
Program juga menyediakan fitur penyaringan data menggunakan radio button yang mewakili setiap kategori produk seperti Elektronik, Makanan, Minuman, Pakaian, dan Alat Tulis. Ketika salah satu radio button dipilih, fungsi filterTable() akan dijalankan untuk menampilkan hanya data dengan kategori yang sesuai. Jika pengguna memilih opsi “Semua”, maka seluruh data produk dari tabel akan ditampilkan kembali tanpa filter.

# DOKUMENTASI
[🎬 Lihat Demo Video](./Dokumentasi/CRUD.mp4)
