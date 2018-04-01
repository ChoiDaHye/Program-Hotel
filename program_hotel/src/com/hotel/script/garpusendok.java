package com.hotel.script;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class garpusendok {

    public Connection con;
    PreparedStatement ps;

    public garpusendok() {
        try {
            this.con = new koneksi().getCon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void wow(){
        try {
            for (int i = 1; i <= 10; i++) {
                ps = con.prepareStatement("INSERT INTO tb_kamar (lantai, id_tipe, status, jumlah) VALUES('3','2','1','1')");
                ps.executeUpdate();
                System.out.println("berhasil");
            }
        } catch (Exception e) {
            System.out.println("gagal"+e);
        }
    }
    
    public static void main(String[] args) {
        garpusendok a = new garpusendok();
        a.wow();
    }
}
