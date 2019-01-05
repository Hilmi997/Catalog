<?php
require "inc/config.php";//include file connect.php untuk menyambungkan file create.php dengan database
if($_SERVER['REQUEST_METHOD']=='GET') {

//inisialiasi query READ	

  $query = "SELECT * FROM barang";


  $sql = mysqli_query($koneksi, $query);

//pemanggilan fungsi mysqli_query untuk mengirimkan perintah sesuai parameter yang diisi
  $result = array(); //inisialisasi array dengan variabel $result
  $response ["data"] = array();


  while($row = mysqli_fetch_array($sql)){
    array_push($response["data"], array(
    	'id'=>$row[0], 
    	'foto'=>$row[1], 
    	'title'=>$row[2], 
    	'harga'=>$row[3]));
  }//melakukan pengulangan dengan while untuk membaca seluruh data pada tabel mahasiswa, dan disimpan didalam row/baris. urutan row harus sesuai urutan pada database
  echo json_encode($response); //mengeluarkan data dalam bentuk json
  mysqli_close($koneksi); 
//tutup koneksi
}
else{
  $response["message"]="tidak ada data";
  echo json_encode($response);
}
?>