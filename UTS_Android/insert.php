<?php
 require_once 'koneksi.php';
 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
     $nama_makanan = $_POST['Nama Makanan'];
     $kode_makanan = $_POST['Kode Makanan'];
     $kategori = $_POST['Kategori'];
     $query = "INSERTINTO tb_makanan (Nama Makanan, Kode Makanan, Kategori) VALUES ('$nama_makanan','$kode_makanan','$kategori')";
     $exeQuery = mysqli_query($konek, $query); 
     echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil menambahkan data')) :  json_encode(array('kode' =>2, 'pesan' => 'data gagal ditambahkan'));
    }
    else
    {
        echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
}
?>