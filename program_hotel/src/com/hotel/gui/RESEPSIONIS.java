/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.gui;

import java.awt.Color;
import javax.swing.JPanel;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.swing.JOptionPane;
import com.hotel.dao.*;
import com.hotel.script.koneksi;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Gedalya Anugrah
 */
public class RESEPSIONIS extends javax.swing.JFrame {

    private static final Random RND = new Random(System.currentTimeMillis());
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String idku, namaku;

    /**
     * Creates new form ADMIN
     */
    public RESEPSIONIS(String id) {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        tb_sr();
        tb_dr();
        tb_fr();
        
        try {
            String sql = "SELECT id_karyawan, nama FROM tb_pegawai WHERE id_karyawan = '"+id+"'";
            Connection konek = new com.hotel.script.koneksi().getCon();

            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
               idku = rs.getString("id_karyawan");
               namaku = rs.getString("nama");
            }
        } catch (Exception e) {
        }
    }

    void setColor(JPanel panel) {
        panel.setBackground(new Color(240, 240, 240));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(255, 255, 255));
    }

    void toMenu() {
        menu.setVisible(true);
        info.setVisible(false);
        pesan.setVisible(false);
        bayar.setVisible(false);
        katalog.setVisible(false);
    }

    void info() {
        //INFO KAMAR
        //TOTAL
        String sql1 = "SELECT SUM(jumlah)FROM tb_kamar WHERE id_tipe = 1";
        String sql2 = "SELECT SUM(jumlah)FROM tb_kamar WHERE id_tipe = 2";
        String sql3 = "SELECT SUM(jumlah)FROM tb_kamar WHERE id_tipe = 3";
        //TERISI
        String sql4 = "SELECT SUM(jumlah)FROM tb_kamar WHERE id_tipe = 1 AND status = 2";
        String sql5 = "SELECT SUM(jumlah)FROM tb_kamar WHERE id_tipe = 2 AND status = 2";
        String sql6 = "SELECT SUM(jumlah)FROM tb_kamar WHERE id_tipe = 3 AND status = 2";
        //KOSONG
        String sql7 = "SELECT SUM(jumlah)FROM tb_kamar WHERE id_tipe = 1 AND status = 1";
        String sql8 = "SELECT SUM(jumlah)FROM tb_kamar WHERE id_tipe = 2 AND status = 1";
        String sql9 = "SELECT SUM(jumlah)FROM tb_kamar WHERE id_tipe = 3 AND status = 1";

        //TRACK RECORD
        LocalDate localDate = LocalDate.now();
        String hriini = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
        String sql10 = "SELECT COUNT(*) FROM tb_pemesanan WHERE t_r = '" + hriini + "'";
        String sql11 = "SELECT COUNT(*) FROM tb_pemesanan WHERE t_in = '" + hriini + "'";
        String sql12 = "SELECT COUNT(*) FROM tb_pemesanan WHERE t_out = '" + hriini + "'";

        try {
            Connection konek = new com.hotel.script.koneksi().getCon();

            //TOTAL
            Statement st1 = konek.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            Statement st2 = konek.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            Statement st3 = konek.createStatement();
            ResultSet rs3 = st3.executeQuery(sql3);
            while (rs1.next()) {
                txt_tot_sr.setText(rs1.getString(1));
            }
            while (rs2.next()) {
                txt_tot_dr.setText(rs2.getString(1));
            }
            while (rs3.next()) {
                txt_tot_fr.setText(rs3.getString(1));
            }

            //TERISI
            Statement st4 = konek.createStatement();
            ResultSet rs4 = st1.executeQuery(sql4);
            Statement st5 = konek.createStatement();
            ResultSet rs5 = st2.executeQuery(sql5);
            Statement st6 = konek.createStatement();
            ResultSet rs6 = st3.executeQuery(sql6);
            while (rs4.next()) {
                if (rs4.getInt(1) == 0) {
                    txt_tr_sr.setText("0");
                } else {
                    txt_tr_sr.setText(rs4.getString(1));
                }
            }
            while (rs5.next()) {
                if (rs5.getInt(1) == 0) {
                    txt_tr_dr.setText("0");
                } else {
                    txt_tr_dr.setText(rs5.getString(1));
                }
            }
            while (rs6.next()) {
                if (rs6.getInt(1) == 0) {
                    txt_tr_fr.setText("0");
                } else {
                    txt_tr_fr.setText(rs6.getString(1));
                }
            }

            //KOSONG
            Statement st7 = konek.createStatement();
            ResultSet rs7 = st1.executeQuery(sql7);
            Statement st8 = konek.createStatement();
            ResultSet rs8 = st2.executeQuery(sql8);
            Statement st9 = konek.createStatement();
            ResultSet rs9 = st3.executeQuery(sql9);
            while (rs7.next()) {
                if (rs7.getInt(1) == 0) {
                    txt_sp_sr.setText("0");
                } else {
                    txt_sp_sr.setText(rs7.getString(1));
                }
            }
            while (rs8.next()) {
                if (rs8.getInt(1) == 0) {
                    txt_sp_dr.setText("0");
                } else {
                    txt_sp_dr.setText(rs8.getString(1));
                }
            }
            while (rs9.next()) {
                if (rs9.getInt(1) == 0) {
                    txt_sp_fr.setText("0");
                } else {
                    txt_sp_fr.setText(rs9.getString(1));
                }
            }

            //TRACK RECORD
            Statement st10 = konek.createStatement();
            ResultSet rs10 = st10.executeQuery(sql10);
            Statement st11 = konek.createStatement();
            ResultSet rs11 = st11.executeQuery(sql11);
            Statement st12 = konek.createStatement();
            ResultSet rs12 = st12.executeQuery(sql12);

            while (rs10.next()) {
                if (rs10.getInt("COUNT(*)") == 0) {
                    txt_r.setText("0");
                } else {
                    txt_r.setText(Integer.toString(rs10.getInt("COUNT(*)")));
                }
            }
            while (rs11.next()) {
                if (rs11.getInt("COUNT(*)") == 0) {
                    txt_in.setText("0");
                } else {
                    txt_in.setText(Integer.toString(rs11.getInt("COUNT(*)")));
                }
            }
            while (rs12.next()) {
                if (rs12.getInt("COUNT(*)") == 0) {
                    txt_out.setText("0");
                } else {
                    txt_out.setText(Integer.toString(rs12.getInt("COUNT(*)")));
                }
            }

            //PEMBERITAHUAN
            String sql13 = "SELECT COUNT(*) FROM tb_pemesanan_detail WHERE kode_booking IN (SELECT kode_booking FROM tb_pemesanan WHERE t_in = '" + hriini + "' AND status = '0')";
            Statement st13 = konek.createStatement();
            ResultSet rs13 = st13.executeQuery(sql13);
            String sql14 = "SELECT COUNT(*) FROM tb_pemesanan_detail WHERE kode_booking IN (SELECT kode_booking FROM tb_pemesanan WHERE t_out = '" + hriini + "' AND status = '0')";
            Statement st14 = konek.createStatement();
            ResultSet rs14 = st14.executeQuery(sql14);
            String sql15 = "SELECT id_kamar FROM tb_pemesanan_detail WHERE kode_booking IN (SELECT kode_booking FROM tb_pemesanan WHERE t_in = '" + hriini + "' AND status = '0')";
            Statement st15 = konek.createStatement();
            ResultSet rs15 = st15.executeQuery(sql15);
            String sql16 = "SELECT id_kamar FROM tb_pemesanan_detail WHERE kode_booking IN (SELECT kode_booking FROM tb_pemesanan WHERE t_out = '" + hriini + "' AND status = '0')";
            Statement st16 = konek.createStatement();
            ResultSet rs16 = st16.executeQuery(sql16);

            int row1 = 0, row2 = 0;
            while (rs13.next()) {
                row1 = rs13.getInt("COUNT(*)");
            }
            while (rs14.next()) {
                row2 = rs14.getInt("COUNT(*)");
            }

            if (row1 != 0 && row2 != 0) {
                fofo2.append("Today's Check In Room: \n");
                for (int i = 1; i <= row1; i++) {
                    while (rs15.next()) {
                        String cetak1 = rs15.getString("id_kamar") + "\n";
                        fofo2.append(cetak1);
                    }
                }
                fofo2.append("\nToday's Check Out Room: \n");
                for (int i = 1; i <= row2; i++) {
                    while (rs16.next()) {
                        String cetak1 = rs16.getString("id_kamar") + "\n";
                        fofo2.append(cetak1);
                    }
                }
            } else if (row1 == 0 && row2 != 0) {
                fofo2.append("Today's Check Out Room: \n");
                for (int i = 1; i <= row2; i++) {
                    while (rs16.next()) {
                        String cetak1 = rs16.getString("id_kamar") + "\n";
                        fofo2.append(cetak1);
                    }
                }
            } else if (row1 != 0 && row2 == 0) {
                fofo2.append("Today's Check In Room: \n");
                for (int i = 1; i <= row1; i++) {
                    while (rs15.next()) {
                        String cetak1 = rs15.getString("id_kamar") + "\n";
                        fofo2.append(cetak1);
                    }
                }
            } else if (row1 == 0 && row2 == 0) {
                fofo2.append("Tidak ada pemberitahuan terbaru.\n");
            }
        } catch (Exception e) {
        }
    }

    void pesan() {
        rdo_lk.setActionCommand("L");
        rdo_pr.setActionCommand("P");

        if (txt_nik.getText().isEmpty()
                || txt_nama.getText().isEmpty()
                || txt_alamat.getText().isEmpty()
                || txt_telp.getText().isEmpty()
                || date_in.getSelectedItem().toString().equals("Tanggal")
                || date_out.getSelectedItem().toString().equals("Tanggal")
                || month_in.getSelectedItem().toString().equals("Bulan")
                || month_out.getSelectedItem().toString().equals("Bulan")
                || year_in.getSelectedItem().toString().equals("Tahun")
                || year_out.getSelectedItem().toString().equals("Tahun")
                || gender_group.getSelection().getActionCommand().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Periksa kelengkapan data anda!");
        } else {
            try {
                Connection konek = new com.hotel.script.koneksi().getCon();
                String in = year_in.getSelectedItem().toString() + "-" + month_in.getSelectedItem().toString() + "-" + date_in.getSelectedItem().toString();
                String out = year_out.getSelectedItem().toString() + "-" + month_out.getSelectedItem().toString() + "-" + date_out.getSelectedItem().toString();
                int lama;

                Date d1 = format.parse(in);
                Date d2 = format.parse(out);

                LocalDate localDate = LocalDate.now();

                long diff = d2.getTime() - d1.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);
                lama = (int) diffDays;

                resep_pesan_dao dao = new resep_pesan_dao();
                resep_pesan_getset gs = new resep_pesan_getset();

                gs.setNik(txt_nik.getText());
                gs.setNama(txt_nama.getText());
                gs.setGender(gender_group.getSelection().getActionCommand());
                gs.setAlamat(txt_alamat.getText());
                gs.setNotelp(txt_telp.getText());

                gs.setBooking(txt_code.getText());
                gs.setTglin(in);
                gs.setTglout(out);
                gs.setSt("0");
                gs.setLm(lama);
                gs.setTglr(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));

                dao.masukDataTamu(gs);
                dao.masukDataPemesanan(gs);
                dao.hapusDataTmp();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak Dapat Terhubung ke Database! " + e);
            }
        }
        DefaultTableModel dt = (DefaultTableModel) tb_tot.getModel();
        txt_nik.setText("");
        txt_nama.setText("");
        txt_alamat.setText("");
        txt_telp.setText("");
        gender_group.clearSelection();
        date_in.setSelectedIndex(0);
        month_in.setSelectedIndex(0);
        year_in.setSelectedIndex(0);
        date_out.setSelectedIndex(0);
        month_out.setSelectedIndex(0);
        year_out.setSelectedIndex(0);

        tb_tot();
        tb_sr();
        tb_dr();
        tb_fr();
    }

    void pesan_ticket() {
        try {
            String sql = "SELECT a.kode_booking, a.nik, a.t_r, a.t_in, a.t_out, a.lama, b.nama FROM tb_pemesanan a, tb_tamu b WHERE a.kode_booking = '" + txt_code.getText() + "' AND a.nik = b.nik";
            Connection konek = new com.hotel.script.koneksi().getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);

            String sql1 = "SELECT c.id_kamar, e.nama_tipe, d.lantai FROM tb_pemesanan_detail c, tb_kamar d, tb_kamar_tipe e WHERE c.kode_booking = '" + txt_code.getText() + "' AND c.id_kamar = d.id_kamar AND d.id_tipe = e.id_tipe";
            Statement st1 = konek.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);

            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\hotel\\booking\\" + txt_code.getText() + ".txt"));

            while (rs.next()) {
                bw.write("====================================================");
                bw.newLine();
                bw.write("                 GRAND CLARION HOTEL                ");
                bw.newLine();
                bw.write("Jl. Andi Pangeran Pettarani No.3, Tamalate, Makassar");
                bw.newLine();
                bw.write("====================================================");
                bw.newLine();
                bw.write("KODE BOOKING  : " + rs.getString("a.kode_booking") + "\t IN  : " + rs.getString("a.t_in"));
                bw.newLine();
                bw.write("TGL. RESERVASI: " + rs.getString("a.t_r") + "\t OUT : " + rs.getString("a.t_out"));
                bw.newLine();
                bw.write("PEMESAN       : " + rs.getString("b.nama"));
                bw.newLine();
                bw.write("LAMA INAP     : " + rs.getString("a.lama"));
                bw.newLine();
                bw.write("DILAYANI OLEH : " + idku + " - " + namaku.toUpperCase());
                bw.newLine();
                bw.write("----------------------------------------------------");
                bw.newLine();
            }
            while (rs1.next()) {
                bw.write("NO. KAMAR : " + rs1.getString("c.id_kamar"));
                bw.newLine();
                bw.write("TIPE      : " + rs1.getString("e.nama_tipe"));
                bw.newLine();
                bw.write("LANTAI    : " + rs1.getString("d.lantai"));
                bw.newLine();
                bw.newLine();
            }
            bw.write("====================================================");
            bw.newLine();
            bw.write("                 Enjoy Our Service ;)               ");
            bw.newLine();
            bw.write("====================================================");
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void bayar() {
        try {
            String sql = "SELECT a.kode_booking, a.nik, a.t_r, a.t_in, a.t_out, a.lama, a.harga_total, b.nama FROM tb_pemesanan a, tb_tamu b WHERE a.kode_booking = '" + txt_cari_kode.getText() + "' AND a.nik = b.nik";
            Connection konek = new com.hotel.script.koneksi().getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);

            String sql1 = "SELECT c.id_kamar, e.nama_tipe, c.harga FROM tb_pemesanan_detail c, tb_kamar d, tb_kamar_tipe e WHERE c.kode_booking = '" + txt_cari_kode.getText() + "' AND c.id_kamar = d.id_kamar AND  d.id_tipe = e.id_tipe";
            Statement st1 = konek.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);

            String sql2 = "SELECT COUNT(*) FROM tb_pemesanan_detail WHERE kode_booking = '" + txt_cari_kode.getText() + "'";
            Statement st2 = konek.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);

            int row = 0;
            while (rs2.next()) {
                row = rs2.getInt("COUNT(*)");
            }

            String cetak = "", cetak1 = "", cetak2 = "";

            while (rs.next()) {
                cetak = "==========================================\n"
                        + "\tGRAND CLARION HOTEL                \n"
                        + "Jl. Andi Pangeran Pettarani No.3, Tamalate, Makassar\n"
                        + "==========================================\n"
                        + "KODE BOOKING\t: " + rs.getString("a.kode_booking") + "\n"
                        + "TGL. RESERVASI\t: " + rs.getString("a.t_r") + "\n"
                        + "CHECK INt\t\t: " + rs.getString("a.t_in") + "\n"
                        + "CHECK OUT\t\t: " + rs.getString("a.t_out") + "\n"
                        + "PEMESAN\t\t: " + rs.getString("b.nama") + "\n"
                        + "LAMA INAP\t\t: " + rs.getString("a.lama") + " Hari\n"
                        + "DILAYANI OLEH\t: " + idku + " - " + namaku.toUpperCase() + " Hari\n"
                        + "==========================================\n";

                cetak2 = "-------------------------------------------------------------------------\n"
                        + "TOTAL\t: Rp. " + rs.getString("a.harga_total") + "\n\n"
                        + "==========================================\n"
                        + "\tEnjoy Our Service ;)\n"
                        + "==========================================\n";
            }

            fofo.append(cetak);

            for (int i = 1; i <= row; i++) {
                while (rs1.next()) {
                    cetak1 = "NO. KAMAR\t: " + rs1.getString("c.id_kamar") + "\n"
                            + "TIPE\t: " + rs1.getString("e.nama_tipe") + "\n"
                            + "HARGA\t: " + rs1.getString("c.harga") + "\n\n";
                    fofo.append(cetak1);
                }
            }

            fofo.append(cetak2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan! " + e);
        }
    }

    void bayar_ticket() {
        try {
            String sql = "SELECT a.kode_booking, a.nik, a.t_r, a.t_in, a.t_out, a.lama, a.harga_total, b.nama FROM tb_pemesanan a, tb_tamu b WHERE a.kode_booking = '" + txt_cari_kode.getText() + "' AND a.nik = b.nik";
            Connection konek = new com.hotel.script.koneksi().getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);

            String sql1 = "SELECT c.id_kamar, e.nama_tipe, c.harga FROM tb_pemesanan_detail c, tb_kamar d, tb_kamar_tipe e WHERE c.kode_booking = '" + txt_cari_kode.getText() + "' AND c.id_kamar = d.id_kamar AND  d.id_tipe = e.id_tipe";
            Statement st1 = konek.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);

            String sql2 = "SELECT COUNT(*) FROM tb_pemesanan_detail WHERE kode_booking = '" + txt_cari_kode.getText() + "'";
            Statement st2 = konek.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);

            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\hotel\\paid\\" + txt_cari_kode.getText() + ".txt"));

            while (rs.next()) {
                bw.write("====================================================");
                bw.newLine();
                bw.write("                 GRAND CLARION HOTEL                ");
                bw.newLine();
                bw.write("Jl. Andi Pangeran Pettarani No.3, Tamalate, Makassar");
                bw.newLine();
                bw.write("====================================================");
                bw.newLine();
                bw.write("KODE BOOKING      : " + rs.getString("a.kode_booking"));
                bw.newLine();
                bw.write("TGL. RESERVASI    : " + rs.getString("a.t_r"));
                bw.newLine();
                bw.write("CHECK IN          : " + rs.getString("a.t_in"));
                bw.newLine();
                bw.write("CHECK OUT         : " + rs.getString("a.t_out"));
                bw.newLine();
                bw.write("PEMESAN           : " + rs.getString("b.nama"));
                bw.newLine();
                bw.write("LAMA INAP         : " + rs.getString("a.lama") + " Hari");
                bw.newLine();
                bw.write("DILAYANI OLEH     : " + idku + " - " + namaku.toUpperCase());
                bw.newLine();
                bw.write("====================================================");
                bw.newLine();
                while (rs1.next()) {
                    bw.write("NO. KAMAR     : " + rs1.getString("c.id_kamar"));
                    bw.newLine();
                    bw.write("TIPE KAMAR    : " + rs1.getString("e.nama_tipe"));
                    bw.newLine();
                    bw.write("HARGA         : " + rs1.getString("c.harga"));
                    bw.newLine();
                    bw.newLine();
                }
                bw.write("----------------------------------------------------");
                bw.newLine();
                bw.write("TOTAL         : Rp. " + rs.getString("a.harga_total"));
                bw.newLine();
                bw.write("STATUS        : LUNAS");
                bw.newLine();
                bw.write("====================================================");
                bw.newLine();
                bw.write("                   See you soon ;)                  ");
                bw.newLine();
                bw.write("====================================================");
                bw.close();
            }

            resep_bayar_dao dao = new resep_bayar_dao();
            resep_pesan_getset gs = new resep_pesan_getset();

            gs.setBooking2(txt_cari_kode.getText());
            dao.ubahBayar(gs);

            JOptionPane.showMessageDialog(this, "Struck dicetak.");
            fofo.append("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan! " + e);
        }
    }

    int hr_sr;

    void tb_sr() {
        DefaultTableModel dt;
        Object[] baris = {"No.", "L"};
        dt = new DefaultTableModel(null, baris);
        tb_sr.setModel(dt);
        String sql = "SELECT a.id_kamar, a.lantai, a.id_tipe, b.harga FROM tb_kamar a, tb_kamar_tipe b WHERE a.id_tipe = b.id_tipe AND a.id_tipe = '1' AND a.status = '1' ORDER BY a.id_kamar";

        try {
            Connection konek = new com.hotel.script.koneksi().getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String NOMOR = rs.getString("a.id_kamar");
                String LANTAI = rs.getString("a.lantai");
                hr_sr = rs.getInt("b.harga");
                String[] data = {NOMOR, LANTAI};
                dt.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    void tb_sr_klik() {
        try {
            int isi = tb_sr.getSelectedRow();

            int id = Integer.parseInt(tb_sr.getModel().getValueAt(isi, 0).toString());
            String sql = "INSERT INTO tb_pemesanan_tmp(id_kamar, harga) VALUES ('" + id + "', '" + hr_sr + "')";

            Connection con = new koneksi().getCon();;
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.execute();

            JOptionPane.showMessageDialog(null, "Save");
            tb_tot();

            DefaultTableModel dt;
            dt = (DefaultTableModel) tb_sr.getModel();
            dt.removeRow(tb_sr.getSelectedRow());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    int hr_dr;

    void tb_dr() {
        DefaultTableModel dt;
        Object[] baris = {"No.", "L"};
        dt = new DefaultTableModel(null, baris);
        tb_dr.setModel(dt);
        String sql = "SELECT a.id_kamar, a.lantai, a.status, a.id_tipe, b.harga FROM tb_kamar a, tb_kamar_tipe b WHERE a.id_tipe = b.id_tipe AND a.id_tipe = '2' AND a.status = '1' ORDER BY a.id_kamar";

        try {
            Connection konek = new com.hotel.script.koneksi().getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String NOMOR = rs.getString("a.id_kamar");
                String LANTAI = rs.getString("a.lantai");
                hr_dr = rs.getInt("b.harga");
                String[] data = {NOMOR, LANTAI};
                dt.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    void tb_dr_klik() {
        try {
            int isi = tb_dr.getSelectedRow();

            int id = Integer.parseInt(tb_dr.getModel().getValueAt(isi, 0).toString());
            String sql = "INSERT INTO tb_pemesanan_tmp(id_kamar, harga) VALUES ('" + id + "', '" + hr_dr + "')";

            Connection con = new koneksi().getCon();;
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.execute();

            JOptionPane.showMessageDialog(null, "Save");
            tb_tot();

            DefaultTableModel dt;
            dt = (DefaultTableModel) tb_dr.getModel();
            dt.removeRow(tb_dr.getSelectedRow());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    int hr_fr;

    void tb_fr() {
        DefaultTableModel dt;
        Object[] baris = {"No.", "L"};
        dt = new DefaultTableModel(null, baris);
        tb_fr.setModel(dt);
        String sql = "SELECT a.id_kamar, a.lantai, a.id_tipe, b.harga FROM tb_kamar a, tb_kamar_tipe b WHERE a.id_tipe = b.id_tipe AND a.id_tipe = '3' AND a.status = '1' ORDER BY a.id_kamar";

        try {
            Connection konek = new com.hotel.script.koneksi().getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String NOMOR = rs.getString("a.id_kamar");
                String LANTAI = rs.getString("a.lantai");
                hr_fr = rs.getInt("b.harga");

                String[] data = {NOMOR, LANTAI};
                dt.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    void tb_fr_klik() {
        try {
            int isi = tb_fr.getSelectedRow();

            int id = Integer.parseInt(tb_fr.getModel().getValueAt(isi, 0).toString());
            String sql = "INSERT INTO tb_pemesanan_tmp(id_kamar, harga) VALUES ('" + id + "', '" + hr_fr + "')";

            Connection con = new koneksi().getCon();;
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.execute();

            JOptionPane.showMessageDialog(null, "Save");
            tb_tot();

            DefaultTableModel dt;
            dt = (DefaultTableModel) tb_fr.getModel();
            dt.removeRow(tb_fr.getSelectedRow());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void tb_tot() {
        DefaultTableModel dt;
        Object[] baris = {"No. Kamar"};
        dt = new DefaultTableModel(null, baris);
        tb_tot.setModel(dt);
        String sql = "SELECT id_kamar FROM tb_pemesanan_tmp";

        try {
            Connection konek = new com.hotel.script.koneksi().getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String NOMOR = rs.getString("id_kamar");
                String[] data = {NOMOR};
                dt.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gender_group = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        full_body = new javax.swing.JPanel();
        head = new javax.swing.JLabel();
        main_panel = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        panel_info = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel_pesan = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panel_bayar = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panel_bayar1 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        info = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        data_kamar = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_tot_sr = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_tr_sr = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_sp_sr = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_tot_dr = new javax.swing.JTextField();
        txt_tr_dr = new javax.swing.JTextField();
        txt_sp_dr = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_tot_fr = new javax.swing.JTextField();
        txt_tr_fr = new javax.swing.JTextField();
        txt_sp_fr = new javax.swing.JTextField();
        pilih_kamar = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_r = new javax.swing.JTextField();
        txt_in = new javax.swing.JTextField();
        txt_out = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        fofo2 = new javax.swing.JTextArea();
        pesan = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        data_pemesan = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_nik = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        rdo_lk = new javax.swing.JRadioButton();
        rdo_pr = new javax.swing.JRadioButton();
        jLabel45 = new javax.swing.JLabel();
        txt_telp = new javax.swing.JTextField();
        pilih_kamar1 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btn_save = new javax.swing.JLabel();
        month_in = new javax.swing.JComboBox<>();
        year_in = new javax.swing.JComboBox<>();
        year_out = new javax.swing.JComboBox<>();
        month_out = new javax.swing.JComboBox<>();
        txt_code = new javax.swing.JLabel();
        date_in = new javax.swing.JComboBox<>();
        date_out = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_sr = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_dr = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_fr = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_tot = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        bayar = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txt_cari_kode = new javax.swing.JTextField();
        btn_cari = new javax.swing.JLabel();
        btn_print2_struck = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        fofo = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        katalog = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        btn_out = new javax.swing.JLabel();
        btn_back = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        full_body.setBackground(new java.awt.Color(255, 255, 255));

        head.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        head.setForeground(new java.awt.Color(50, 55, 61));
        head.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        head.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/icon_resep.png"))); // NOI18N
        head.setText("  Resepsionis");

        main_panel.setBackground(new java.awt.Color(255, 255, 255));
        main_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                main_panelMouseEntered(evt);
            }
        });
        main_panel.setLayout(new java.awt.CardLayout());

        menu.setBackground(new java.awt.Color(255, 255, 255));

        panel_info.setBackground(new java.awt.Color(255, 255, 255));
        panel_info.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_infoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_infoMouseEntered(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/data_info.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(50, 55, 61));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Informasi");

        javax.swing.GroupLayout panel_infoLayout = new javax.swing.GroupLayout(panel_info);
        panel_info.setLayout(panel_infoLayout);
        panel_infoLayout.setHorizontalGroup(
            panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_infoLayout.setVerticalGroup(
            panel_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_infoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_pesan.setBackground(new java.awt.Color(255, 255, 255));
        panel_pesan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_pesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_pesanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_pesanMouseEntered(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/data_beli.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(50, 55, 61));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pemesanan");

        javax.swing.GroupLayout panel_pesanLayout = new javax.swing.GroupLayout(panel_pesan);
        panel_pesan.setLayout(panel_pesanLayout);
        panel_pesanLayout.setHorizontalGroup(
            panel_pesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pesanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_pesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_pesanLayout.setVerticalGroup(
            panel_pesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pesanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_bayar.setBackground(new java.awt.Color(255, 255, 255));
        panel_bayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_bayarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_bayarMouseEntered(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/data_bayar.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(50, 55, 61));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Pembayaran");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout panel_bayarLayout = new javax.swing.GroupLayout(panel_bayar);
        panel_bayar.setLayout(panel_bayarLayout);
        panel_bayarLayout.setHorizontalGroup(
            panel_bayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bayarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_bayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_bayarLayout.setVerticalGroup(
            panel_bayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bayarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_bayar1.setBackground(new java.awt.Color(255, 255, 255));
        panel_bayar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_bayar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_bayar1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_bayar1MouseEntered(evt);
            }
        });

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/data_brosur.png"))); // NOI18N

        jLabel47.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(50, 55, 61));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Katalog");
        jLabel47.setToolTipText("");

        javax.swing.GroupLayout panel_bayar1Layout = new javax.swing.GroupLayout(panel_bayar1);
        panel_bayar1.setLayout(panel_bayar1Layout);
        panel_bayar1Layout.setHorizontalGroup(
            panel_bayar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bayar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_bayar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_bayar1Layout.setVerticalGroup(
            panel_bayar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bayar1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panel_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panel_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panel_bayar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(198, Short.MAX_VALUE)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_bayar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(266, Short.MAX_VALUE))
        );

        main_panel.add(menu, "card3");

        info.setBackground(new java.awt.Color(255, 255, 255));
        info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                infoMouseEntered(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(50, 55, 61));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Informasi");

        data_kamar.setBackground(new java.awt.Color(255, 255, 255));
        data_kamar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DATA KAMAR");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Total");

        txt_tot_sr.setEditable(false);
        txt_tot_sr.setBackground(new java.awt.Color(255, 255, 255));
        txt_tot_sr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_tot_sr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tot_sr.setText("0");
        txt_tot_sr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Terisi");

        txt_tr_sr.setEditable(false);
        txt_tr_sr.setBackground(new java.awt.Color(255, 255, 255));
        txt_tr_sr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_tr_sr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tr_sr.setText("0");
        txt_tr_sr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Siap Pesan");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Superior Room");

        txt_sp_sr.setEditable(false);
        txt_sp_sr.setBackground(new java.awt.Color(255, 255, 255));
        txt_sp_sr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_sp_sr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_sp_sr.setText("0");
        txt_sp_sr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Deluxe Room");

        txt_tot_dr.setEditable(false);
        txt_tot_dr.setBackground(new java.awt.Color(255, 255, 255));
        txt_tot_dr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_tot_dr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tot_dr.setText("0");
        txt_tot_dr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        txt_tr_dr.setEditable(false);
        txt_tr_dr.setBackground(new java.awt.Color(255, 255, 255));
        txt_tr_dr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_tr_dr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tr_dr.setText("0");
        txt_tr_dr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        txt_sp_dr.setEditable(false);
        txt_sp_dr.setBackground(new java.awt.Color(255, 255, 255));
        txt_sp_dr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_sp_dr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_sp_dr.setText("0");
        txt_sp_dr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Family Room");

        txt_tot_fr.setEditable(false);
        txt_tot_fr.setBackground(new java.awt.Color(255, 255, 255));
        txt_tot_fr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_tot_fr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tot_fr.setText("0");
        txt_tot_fr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        txt_tr_fr.setEditable(false);
        txt_tr_fr.setBackground(new java.awt.Color(255, 255, 255));
        txt_tr_fr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_tr_fr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tr_fr.setText("0");
        txt_tr_fr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        txt_sp_fr.setEditable(false);
        txt_sp_fr.setBackground(new java.awt.Color(255, 255, 255));
        txt_sp_fr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_sp_fr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_sp_fr.setText("0");
        txt_sp_fr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout data_kamarLayout = new javax.swing.GroupLayout(data_kamar);
        data_kamar.setLayout(data_kamarLayout);
        data_kamarLayout.setHorizontalGroup(
            data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_kamarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(data_kamarLayout.createSequentialGroup()
                        .addGroup(data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(40, 40, 40)
                        .addGroup(data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_tr_sr)
                            .addComponent(txt_tot_sr, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_sp_sr, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_tot_dr, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_tr_dr)
                            .addComponent(txt_sp_dr, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txt_tot_fr, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_tr_fr)
                            .addComponent(txt_sp_fr, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );
        data_kamarLayout.setVerticalGroup(
            data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_kamarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(data_kamarLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_tot_sr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_tr_sr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(data_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel10)
                            .addComponent(txt_sp_sr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(data_kamarLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_tot_dr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_tr_dr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_sp_dr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(data_kamarLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_tot_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_tr_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_sp_fr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pilih_kamar.setBackground(new java.awt.Color(255, 255, 255));
        pilih_kamar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("TODAY'S TRACK RECORD ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Reservasi");
        jLabel14.setToolTipText("");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Tamu Check In");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Tamu Check Out");

        txt_r.setEditable(false);
        txt_r.setBackground(new java.awt.Color(255, 255, 255));
        txt_r.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_r.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_r.setText("0");
        txt_r.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        txt_in.setEditable(false);
        txt_in.setBackground(new java.awt.Color(255, 255, 255));
        txt_in.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_in.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_in.setText("0");
        txt_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        txt_out.setEditable(false);
        txt_out.setBackground(new java.awt.Color(255, 255, 255));
        txt_out.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_out.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_out.setText("0");
        txt_out.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout pilih_kamarLayout = new javax.swing.GroupLayout(pilih_kamar);
        pilih_kamar.setLayout(pilih_kamarLayout);
        pilih_kamarLayout.setHorizontalGroup(
            pilih_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pilih_kamarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pilih_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pilih_kamarLayout.createSequentialGroup()
                        .addGroup(pilih_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addGroup(pilih_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_r, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_in, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_out, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pilih_kamarLayout.setVerticalGroup(
            pilih_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pilih_kamarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(pilih_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_r, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pilih_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel15)
                    .addComponent(txt_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pilih_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(txt_out, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("PEMBERITAHUAN");

        jScrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        fofo2.setEditable(false);
        fofo2.setColumns(20);
        fofo2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fofo2.setRows(5);
        jScrollPane8.setViewportView(fofo2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout infoLayout = new javax.swing.GroupLayout(info);
        info.setLayout(infoLayout);
        infoLayout.setHorizontalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pilih_kamar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data_kamar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        infoLayout.setVerticalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel11)
                .addGap(30, 30, 30)
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(infoLayout.createSequentialGroup()
                        .addComponent(data_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(pilih_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        main_panel.add(info, "card3");

        pesan.setBackground(new java.awt.Color(255, 255, 255));
        pesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pesanMouseEntered(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(50, 55, 61));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Pemesanan");

        data_pemesan.setBackground(new java.awt.Color(255, 255, 255));
        data_pemesan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jLabel20.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        jLabel20.setText("DATA PEMESAN");

        jLabel22.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel22.setText("* Mohon diisi dengan data yang valid.");

        jLabel23.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel23.setText("NIK");

        txt_nik.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_nik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel24.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel24.setText("Nama");

        txt_nama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_nama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel25.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel25.setText("Jenis Kelamin");

        jLabel26.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel26.setText("Alamat");

        txt_alamat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_alamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        rdo_lk.setBackground(new java.awt.Color(255, 255, 255));
        gender_group.add(rdo_lk);
        rdo_lk.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        rdo_lk.setText("Laki-laki");

        rdo_pr.setBackground(new java.awt.Color(255, 255, 255));
        gender_group.add(rdo_pr);
        rdo_pr.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        rdo_pr.setText("Perempuan");

        jLabel45.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel45.setText("No. Telepon");

        txt_telp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_telp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout data_pemesanLayout = new javax.swing.GroupLayout(data_pemesan);
        data_pemesan.setLayout(data_pemesanLayout);
        data_pemesanLayout.setHorizontalGroup(
            data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_pemesanLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel20)
                    .addGroup(data_pemesanLayout.createSequentialGroup()
                        .addGroup(data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23))
                        .addGap(44, 44, 44)
                        .addGroup(data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nik)
                            .addComponent(txt_nama)
                            .addGroup(data_pemesanLayout.createSequentialGroup()
                                .addComponent(rdo_lk)
                                .addGap(18, 18, 18)
                                .addComponent(rdo_pr)
                                .addGap(0, 54, Short.MAX_VALUE))
                            .addComponent(txt_alamat)
                            .addComponent(txt_telp))))
                .addGap(20, 20, 20))
        );
        data_pemesanLayout.setVerticalGroup(
            data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(data_pemesanLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addGroup(data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txt_nik, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(rdo_lk)
                    .addComponent(rdo_pr))
                .addGap(18, 18, 18)
                .addGroup(data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(data_pemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txt_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pilih_kamar1.setBackground(new java.awt.Color(255, 255, 255));
        pilih_kamar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jLabel27.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        jLabel27.setText("PEMILIHAN KAMAR");

        jLabel28.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel28.setText("* Mohon diisi dengan data yang valid.");

        jLabel29.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel29.setText("Tipe Kamar");

        jLabel35.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel35.setText("Check In");

        jLabel36.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel36.setText("Check Out");

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_save.png"))); // NOI18N
        btn_save.setToolTipText("Simpan");
        btn_save.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_saveMouseClicked(evt);
            }
        });

        month_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bulan", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        year_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tahun", "2018", "2019" }));
        year_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                year_inActionPerformed(evt);
            }
        });

        year_out.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tahun", "2018", "2019" }));

        month_out.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bulan", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        month_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                month_outActionPerformed(evt);
            }
        });

        txt_code.setFont(new java.awt.Font("Impact", 0, 33)); // NOI18N
        txt_code.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        date_in.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tanggal", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        date_out.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tanggal", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        date_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_outActionPerformed(evt);
            }
        });

        tb_sr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "L"
            }
        ));
        tb_sr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_srMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_sr);

        tb_dr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "L"
            }
        ));
        tb_dr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_drMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_dr);

        tb_fr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "L"
            }
        ));
        tb_fr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_frMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_fr);

        tb_tot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Kamar"
            }
        ));
        jScrollPane5.setViewportView(tb_tot);

        jLabel30.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel30.setText("Superior Room");

        jLabel31.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel31.setText("Deluxe Room");

        jLabel32.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel32.setText("Family Room");
        jLabel32.setToolTipText("");

        javax.swing.GroupLayout pilih_kamar1Layout = new javax.swing.GroupLayout(pilih_kamar1);
        pilih_kamar1.setLayout(pilih_kamar1Layout);
        pilih_kamar1Layout.setHorizontalGroup(
            pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pilih_kamar1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pilih_kamar1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pilih_kamar1Layout.createSequentialGroup()
                                .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pilih_kamar1Layout.createSequentialGroup()
                                            .addComponent(date_out, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(month_out, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pilih_kamar1Layout.createSequentialGroup()
                                            .addGap(198, 198, 198)
                                            .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btn_save)
                                                .addComponent(year_out, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pilih_kamar1Layout.createSequentialGroup()
                                            .addComponent(date_in, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(20, 20, 20)
                                            .addComponent(month_in, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(20, 20, 20)
                                            .addComponent(year_in, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pilih_kamar1Layout.createSequentialGroup()
                                            .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel36)
                                                .addComponent(jLabel35))
                                            .addGap(212, 212, 212)))
                                    .addComponent(txt_code, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pilih_kamar1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane2, jScrollPane3, jScrollPane4});

        pilih_kamar1Layout.setVerticalGroup(
            pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pilih_kamar1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addGap(21, 21, 21)
                .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pilih_kamar1Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel30)
                        .addGap(6, 6, 6)
                        .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pilih_kamar1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31)
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(pilih_kamar1Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(date_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(month_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(year_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pilih_kamar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(date_out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(month_out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(year_out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_code, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pilih_kamar1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {month_in, month_out, year_in, year_out});

        javax.swing.GroupLayout pesanLayout = new javax.swing.GroupLayout(pesan);
        pesan.setLayout(pesanLayout);
        pesanLayout.setHorizontalGroup(
            pesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pesanLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(data_pemesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(pilih_kamar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pesanLayout.setVerticalGroup(
            pesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pesanLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel61)
                .addGap(30, 30, 30)
                .addGroup(pesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pilih_kamar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(data_pemesan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        main_panel.add(pesan, "card3");

        bayar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(50, 55, 61));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Pembayaran");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel39.setText("Kode Booking");
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 82, -1, -1));

        jLabel40.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel40.setText("* Mohon diisi dengan data yang valid.");
        jPanel2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 48, -1, -1));

        jLabel41.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        jLabel41.setText("KODE BOOKING");
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 21, -1, -1));

        txt_cari_kode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_cari_kode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        jPanel2.add(txt_cari_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 74, 153, 34));

        btn_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_search.png"))); // NOI18N
        btn_cari.setToolTipText("Cari Data");
        btn_cari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_cari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cariMouseClicked(evt);
            }
        });
        jPanel2.add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 74, -1, -1));

        btn_print2_struck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_print.png"))); // NOI18N
        btn_print2_struck.setToolTipText("Cetak Struck");
        btn_print2_struck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_print2_struck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_print2_struck.setEnabled(false);
        btn_print2_struck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_print2_struckMouseClicked(evt);
            }
        });
        jPanel2.add(btn_print2_struck, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 74, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jScrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        fofo.setEditable(false);
        fofo.setColumns(20);
        fofo.setRows(5);
        jScrollPane6.setViewportView(fofo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/gray map.png"))); // NOI18N

        javax.swing.GroupLayout bayarLayout = new javax.swing.GroupLayout(bayar);
        bayar.setLayout(bayarLayout);
        bayarLayout.setHorizontalGroup(
            bayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bayarLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(bayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(52, 52, 52)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        bayarLayout.setVerticalGroup(
            bayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bayarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel21)
                .addGap(30, 30, 30)
                .addGroup(bayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bayarLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel33))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        main_panel.add(bayar, "card3");

        katalog.setBackground(new java.awt.Color(255, 255, 255));

        jLabel48.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(50, 55, 61));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Katalog");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/hakl_superior_room_.jpg"))); // NOI18N

        jLabel55.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("Superior Room");

        jLabel50.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel50.setText("AC - TV lokal channel - Free Wi-Fi");

        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel57.setText("Kamar mandi dengan shower air panas");

        jLabel59.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel59.setText("Kamar dengan pemandangan kebun");

        jLabel60.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel60.setText("Pelayanan Kamar");

        jLabel62.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel62.setText("Tersedia versi Single Bed & Dual Bed");

        jLabel64.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel64.setText("Meja rias - Teras pribadi");

        jLabel87.setFont(new java.awt.Font("Segoe UI Light", 0, 33)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("IDR 745,000");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 345, Short.MAX_VALUE)
                        .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel50, jLabel57, jLabel59, jLabel60, jLabel62, jLabel64});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel57)
                .addGap(6, 6, 6)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel62)
                .addGap(32, 32, 32)
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/hakl_deluxe_room_.jpg"))); // NOI18N

        jLabel65.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel65.setText("Tersedia versi Single Bed & Dual Bed");

        jLabel66.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel66.setText("Pelayanan Kamar");

        jLabel67.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel67.setText("Kamar dengan pemandangan kebun");

        jLabel68.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel69.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel70.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel70.setText("Free Wi-Fi");

        jLabel71.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel71.setText("Kamar mandi dengan shower air panas");

        jLabel72.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel72.setText("TV lokal channel + Netflix - Teras pribadi");
        jLabel72.setToolTipText("");

        jLabel73.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel73.setText("AC - Meja rias - DVD player - Kulkas - Minibar");

        jLabel74.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Deluxe Room");
        jLabel74.setToolTipText("");

        jLabel75.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel76.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel77.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel85.setFont(new java.awt.Font("Segoe UI Light", 0, 33)); // NOI18N
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("IDR 1,235,000");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel68)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel75)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel76)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel77))
                                    .addComponent(jLabel69))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel51)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(jLabel68)
                            .addComponent(jLabel75)
                            .addComponent(jLabel76)
                            .addComponent(jLabel77))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel70)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel65)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/hakl_family_room_.jpg"))); // NOI18N

        jLabel78.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("Family Room");
        jLabel78.setToolTipText("");

        jLabel79.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel79.setText("AC - Meja rias - Free Wi-Fi");

        jLabel80.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel81.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel81.setText("Kamar mandi dengan shower air panas");

        jLabel82.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel83.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel84.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel86.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel86.setText("Kulkas - TV lokal channel - Teras pribadi");
        jLabel86.setToolTipText("");

        jLabel88.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel88.setText("Kamar dengan pemandangan kebun");

        jLabel89.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel89.setText("Pelayanan Kamar");

        jLabel90.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel90.setText("Tersedia Triple Bed");

        jLabel91.setFont(new java.awt.Font("Segoe UI Light", 0, 33)); // NOI18N
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("IDR 815,000");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel86)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel80)
                                    .addComponent(jLabel84))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel83)
                                    .addComponent(jLabel82))))
                        .addGap(25, 25, 25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel54))
            .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(176, 176, 176))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79)
                            .addComponent(jLabel84)
                            .addComponent(jLabel82))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel86)
                            .addComponent(jLabel80)
                            .addComponent(jLabel83))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel81)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel89)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel90)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout katalogLayout = new javax.swing.GroupLayout(katalog);
        katalog.setLayout(katalogLayout);
        katalogLayout.setHorizontalGroup(
            katalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(katalogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        katalogLayout.setVerticalGroup(
            katalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(katalogLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel48)
                .addGap(25, 25, 25)
                .addGroup(katalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(katalogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        main_panel.add(katalog, "card3");

        btn_out.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_logout.png"))); // NOI18N
        btn_out.setToolTipText("Keluar");
        btn_out.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_outMouseClicked(evt);
            }
        });

        btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_back.png"))); // NOI18N
        btn_back.setToolTipText("Kembali");
        btn_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_backMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout full_bodyLayout = new javax.swing.GroupLayout(full_body);
        full_body.setLayout(full_bodyLayout);
        full_bodyLayout.setHorizontalGroup(
            full_bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(full_bodyLayout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(full_bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(btn_back)
                .addGap(20, 20, 20)
                .addComponent(btn_out)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        full_bodyLayout.setVerticalGroup(
            full_bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(full_bodyLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(full_bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_back)
                    .addComponent(btn_out)
                    .addComponent(head))
                .addGap(30, 30, 30)
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(full_body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(full_body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel_infoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_infoMouseEntered
        setColor(panel_info);
        resetColor(panel_pesan);
        resetColor(panel_bayar);
        resetColor(panel_bayar1);
    }//GEN-LAST:event_panel_infoMouseEntered

    private void panel_pesanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_pesanMouseEntered
        resetColor(panel_info);
        setColor(panel_pesan);
        resetColor(panel_bayar);
        resetColor(panel_bayar1);
    }//GEN-LAST:event_panel_pesanMouseEntered

    private void panel_bayarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_bayarMouseEntered
        resetColor(panel_info);
        resetColor(panel_pesan);
        setColor(panel_bayar);
        resetColor(panel_bayar1);
    }//GEN-LAST:event_panel_bayarMouseEntered

    private void main_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_main_panelMouseEntered
        resetColor(panel_info);
        resetColor(panel_pesan);
        resetColor(panel_bayar);
        resetColor(panel_bayar1);
    }//GEN-LAST:event_main_panelMouseEntered

    private void panel_infoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_infoMouseClicked
        menu.setVisible(false);
        info.setVisible(true);
        pesan.setVisible(false);
        bayar.setVisible(false);
        katalog.setVisible(false);
        info();
    }//GEN-LAST:event_panel_infoMouseClicked

    private void panel_pesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_pesanMouseClicked
        menu.setVisible(false);
        info.setVisible(false);
        pesan.setVisible(true);
        bayar.setVisible(false);
        katalog.setVisible(false);
    }//GEN-LAST:event_panel_pesanMouseClicked

    private void panel_bayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_bayarMouseClicked
        katalog.setVisible(false);
        menu.setVisible(false);
        info.setVisible(false);
        pesan.setVisible(false);
        bayar.setVisible(true);
    }//GEN-LAST:event_panel_bayarMouseClicked

    private void infoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoMouseEntered

    }//GEN-LAST:event_infoMouseEntered

    private void btn_saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_saveMouseClicked
        pesan();
        int pilih = JOptionPane.showConfirmDialog(null, "Cetak struck sementara?", "Cetak Struck", JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
            pesan_ticket();
            tb_sr();
            tb_dr();
            tb_fr();
        }
    }//GEN-LAST:event_btn_saveMouseClicked

    private void pesanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesanMouseEntered
        String code = "B" + new SimpleDateFormat("ddMMyyHHmm").format(Calendar.getInstance().getTime());
        txt_code.setText(code);
    }//GEN-LAST:event_pesanMouseEntered

    private void panel_bayar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_bayar1MouseClicked
        menu.setVisible(false);
        info.setVisible(false);
        pesan.setVisible(false);
        bayar.setVisible(false);
        katalog.setVisible(true);
    }//GEN-LAST:event_panel_bayar1MouseClicked

    private void panel_bayar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_bayar1MouseEntered
        setColor(panel_bayar1);
        resetColor(panel_info);
        resetColor(panel_pesan);
        resetColor(panel_bayar);
    }//GEN-LAST:event_panel_bayar1MouseEntered

    private void btn_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_outMouseClicked
        LOGIN login = new LOGIN();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_outMouseClicked

    private void btn_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backMouseClicked
        toMenu();
        fofo2.setText("");
    }//GEN-LAST:event_btn_backMouseClicked

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

    }//GEN-LAST:event_formMouseEntered

    private void year_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_year_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_year_inActionPerformed

    private void month_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_month_outActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_month_outActionPerformed

    private void date_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_outActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_date_outActionPerformed

    private void tb_srMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_srMouseClicked
        tb_sr_klik();
    }//GEN-LAST:event_tb_srMouseClicked

    private void tb_drMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_drMouseClicked
        tb_dr_klik();
    }//GEN-LAST:event_tb_drMouseClicked

    private void tb_frMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_frMouseClicked
        tb_fr_klik();
    }//GEN-LAST:event_tb_frMouseClicked

    private void btn_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cariMouseClicked
        if (txt_cari_kode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Isi kode booking!");
            txt_cari_kode.requestFocus();
        } else {
            bayar();
            btn_print2_struck.setEnabled(true);
            btn_cari.setEnabled(false);
        }
    }//GEN-LAST:event_btn_cariMouseClicked

    private void btn_print2_struckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_print2_struckMouseClicked
        int pilih = JOptionPane.showConfirmDialog(null, "Cetak struck?", "Cetak Struck", JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
            bayar_ticket();
            btn_print2_struck.setEnabled(false);
            btn_cari.setEnabled(true);
            txt_cari_kode.setText("");
        }
    }//GEN-LAST:event_btn_print2_struckMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RESEPSIONIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RESEPSIONIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RESEPSIONIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RESEPSIONIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bayar;
    private javax.swing.JLabel btn_back;
    private javax.swing.JLabel btn_cari;
    private javax.swing.JLabel btn_out;
    private javax.swing.JLabel btn_print2_struck;
    private javax.swing.JLabel btn_save;
    private javax.swing.JPanel data_kamar;
    private javax.swing.JPanel data_pemesan;
    private javax.swing.JComboBox<String> date_in;
    private javax.swing.JComboBox<String> date_out;
    private javax.swing.JTextArea fofo;
    private javax.swing.JTextArea fofo2;
    private javax.swing.JPanel full_body;
    private javax.swing.ButtonGroup gender_group;
    private javax.swing.JLabel head;
    private javax.swing.JPanel info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel katalog;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel menu;
    private javax.swing.JComboBox<String> month_in;
    private javax.swing.JComboBox<String> month_out;
    private javax.swing.JPanel panel_bayar;
    private javax.swing.JPanel panel_bayar1;
    private javax.swing.JPanel panel_info;
    private javax.swing.JPanel panel_pesan;
    private javax.swing.JPanel pesan;
    private javax.swing.JPanel pilih_kamar;
    private javax.swing.JPanel pilih_kamar1;
    private javax.swing.JRadioButton rdo_lk;
    private javax.swing.JRadioButton rdo_pr;
    private javax.swing.JTable tb_dr;
    private javax.swing.JTable tb_fr;
    private javax.swing.JTable tb_sr;
    private javax.swing.JTable tb_tot;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_cari_kode;
    private javax.swing.JLabel txt_code;
    private javax.swing.JTextField txt_in;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nik;
    private javax.swing.JTextField txt_out;
    private javax.swing.JTextField txt_r;
    private javax.swing.JTextField txt_sp_dr;
    private javax.swing.JTextField txt_sp_fr;
    private javax.swing.JTextField txt_sp_sr;
    private javax.swing.JTextField txt_telp;
    private javax.swing.JTextField txt_tot_dr;
    private javax.swing.JTextField txt_tot_fr;
    private javax.swing.JTextField txt_tot_sr;
    private javax.swing.JTextField txt_tr_dr;
    private javax.swing.JTextField txt_tr_fr;
    private javax.swing.JTextField txt_tr_sr;
    private javax.swing.JComboBox<String> year_in;
    private javax.swing.JComboBox<String> year_out;
    // End of variables declaration//GEN-END:variables
}
