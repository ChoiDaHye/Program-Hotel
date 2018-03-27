/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.dao;

import com.hotel.gui.*;
import com.hotel.script.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Gedalya Anugrah
 */
public class resep_pesan_dao {
        public Connection con;
    PreparedStatement ps;

    public resep_pesan_dao() {
        try {
            this.con = new koneksi().getCon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean masukDataPesan(resep_pesan_getset gs) {
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan(kode_booking, nik, id_karyawan, harga_total) values (?, ?, ?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getNik());
            ps.setString(3, gs.getKar());
            ps.setString(4, "13");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pemesan Ditambahkan!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Menambah Data!\n" + e);
        }
        return false;
    }
    
    public boolean masukDataIn(resep_pesan_getset gs){
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan_in(kode_booking, tanggal) values (?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getTgli());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Checkin gagal diterima!\n" + e);
        }
        
        return false;
    }
    
    public boolean masukDataOut(resep_pesan_getset gs){
        try {
            ps = con.prepareStatement("INSERT INTO tb_pemesanan_out(kode_booking, tanggal) values (?, ?)");
            ps.setString(1, gs.getBooking());
            ps.setString(2, gs.getTglo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Checkout gagal diterima!\n" + e);
        }
        
        return false;
    }

}
