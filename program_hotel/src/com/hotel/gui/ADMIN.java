/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.gui;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import com.hotel.dao.*;

/**
 *
 * @author Gedalya Anugrah
 */
public class ADMIN extends javax.swing.JFrame {

    /**
     * Creates new form ADMIN
     */
    public ADMIN() {
        initComponents();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }
    
    void setColor(JPanel panel) {
        panel.setBackground(new Color(240, 240, 240));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(255, 255, 255));
    }
    
    void toMenu(){
        menu.setVisible(true);
        pegawai.setVisible(false);        
        akun.setVisible(false);
        kamar.setVisible(false);
        laporan.setVisible(false);
        diagram.setVisible(false);
    }
    
    // DATA PEGAWAI
    void tampil_pegawai() {
        DefaultTableModel dt;
        Object[] baris = {"ID", "Nama", "Jenis Kelamin", "Tanggal Lahir", "Alamat", "Jabatan"};
        dt = new DefaultTableModel(null, baris);
        tb_pegawai.setModel(dt);
        String sql = "SELECT * FROM tb_pegawai ORDER BY id_karyawan";

        try {
            Connection konek = new com.hotel.script.koneksi().getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("id_karyawan");
                String NAMA = rs.getString("nama");
                String GENDER = rs.getString("gender");
                String TL = rs.getString("tl");
                String ALAMAT = rs.getString("alamat");
                String LEVEL = rs.getString("level");

                String[] data = {ID, NAMA, GENDER, TL, ALAMAT, LEVEL};
                dt.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    void tulis_pegawai() {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dFormat.format(txt_date_pegawai.getDate());
        
        try {
            rdo_lk.setActionCommand("L");
            rdo_pr.setActionCommand("P");

            if (txt_id_pegawai.getText().isEmpty() || 
                txt_nama_pegawai.getText().isEmpty() || 
                txt_alamat_pegawai.getText().isEmpty() || 
                cmb_posisi_pegawai.getSelectedItem().toString().isEmpty() || 
                gender_group.getSelection().getActionCommand().isEmpty() || 
                txt_date_pegawai.getDateFormatString().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "LENGKAPI DATA ANDA!");
                if      (txt_id_pegawai.getText().isEmpty())        {txt_id_pegawai.requestFocus();} 
                else if (txt_nama_pegawai.getText().isEmpty())      {txt_nama_pegawai.requestFocus();} 
                else if (txt_alamat_pegawai.getText().isEmpty())    {txt_alamat_pegawai.requestFocus();} 
            } else {
                try {
                    Connection konek = new com.hotel.script.koneksi().getCon();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Tidak Dapat Terhubung ke Database! " + e);
                }

                admin_pegawai_dao dao = new admin_pegawai_dao();
                admin_pegawai_getset gs = new admin_pegawai_getset();
                gs.setId_pegawai(txt_id_pegawai.getText());
                gs.setNama_pegawai(txt_nama_pegawai.getText());
                gs.setTl_pegawai(date);
                gs.setAlamat_pegawai(txt_alamat_pegawai.getText());
                gs.setPosisi_pegawai(cmb_posisi_pegawai.getSelectedItem().toString());
                gs.setGender_pegawai(gender_group.getSelection().getActionCommand());
                
                dao.masukDataPegawai(gs);
                tampil_pegawai();
            }
            txt_id_pegawai.setText("");
            txt_nama_pegawai.setText("");
            txt_alamat_pegawai.setText("");
            txt_date_pegawai.setDateFormatString(""); date = "";
            cmb_posisi_pegawai.setSelectedItem(0);
            gender_group.clearSelection();

            txt_id_pegawai.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Sistem!");
        }
    }
    
    void ubah_pegawai() {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dFormat.format(txt_date_pegawai.getDate());
        
        try {
            rdo_lk.setActionCommand("L");
            rdo_pr.setActionCommand("P");

            if (txt_id_pegawai.getText().isEmpty() || 
                txt_nama_pegawai.getText().isEmpty() || 
                txt_alamat_pegawai.getText().isEmpty() || 
                cmb_posisi_pegawai.getSelectedItem().toString().isEmpty() || 
                gender_group.getSelection().getActionCommand().isEmpty() || 
                txt_date_pegawai.getDateFormatString().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "LENGKAPI DATA ANDA!");
                if      (txt_id_pegawai.getText().isEmpty())        {txt_id_pegawai.requestFocus();} 
                else if (txt_nama_pegawai.getText().isEmpty())      {txt_nama_pegawai.requestFocus();} 
                else if (txt_alamat_pegawai.getText().isEmpty())    {txt_alamat_pegawai.requestFocus();} 
            } else {
                try {
                    Connection konek = new com.hotel.script.koneksi().getCon();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Tidak Dapat Terhubung ke Database! " + e);
                }

                admin_pegawai_dao dao = new admin_pegawai_dao();
                admin_pegawai_getset gs = new admin_pegawai_getset();
                gs.setId_pegawai(txt_id_pegawai.getText());
                gs.setNama_pegawai(txt_nama_pegawai.getText());
                gs.setTl_pegawai(date);
                gs.setAlamat_pegawai(txt_alamat_pegawai.getText());
                gs.setPosisi_pegawai(cmb_posisi_pegawai.getSelectedItem().toString());
                gs.setGender_pegawai(gender_group.getSelection().getActionCommand());
                
                dao.ubahDataPegawai(gs);
                tampil_pegawai();
            }
            txt_id_pegawai.setText("");
            txt_nama_pegawai.setText("");
            txt_alamat_pegawai.setText("");
            txt_date_pegawai.setDateFormatString(""); date = "";
            cmb_posisi_pegawai.setSelectedItem(0);
            gender_group.clearSelection();

            btn_save_pegawai.setVisible(true);
            txt_id_pegawai.setEditable(true);
            txt_id_pegawai.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Sistem!");
        }
    }
    
    public void klik_pegawai(){
        btn_save_pegawai.setVisible(false);
        
        int row = tb_pegawai.getSelectedRow();
        txt_id_pegawai.setText(tb_pegawai.getModel().getValueAt(row, 0).toString());
        txt_id_pegawai.setEditable(false);
        txt_nama_pegawai.setText(tb_pegawai.getModel().getValueAt(row, 1).toString());
        txt_date_pegawai.setDateFormatString(tb_pegawai.getModel().getValueAt(row, 3).toString());
        txt_alamat_pegawai.setText(tb_pegawai.getModel().getValueAt(row, 4).toString());
        cmb_posisi_pegawai.setSelectedItem(tb_pegawai.getModel().getValueAt(row, 5).toString());
    }
    
    //DATA AKUN
     void tampil_akun() {
        DefaultTableModel dt;
        Object[] baris = {"ID", "Username"};
        dt = new DefaultTableModel(null, baris);
        tb_akun.setModel(dt);
        String sql = "SELECT b.user, b.password FROM tb_login b WHERE b.id_karyawan = (SELECT a.id_karyawan FROM tb_pegawai a)";        

        try {
            Connection konek = new com.hotel.script.koneksi().getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String ID = rs.getString("a.id_karyawan");
                String USER = rs.getString("b.user");

                String[] data = {ID, USER};
                dt.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    void tulis_akun() {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dFormat.format(txt_date_pegawai.getDate());
        
        try {
            rdo_lk.setActionCommand("L");
            rdo_pr.setActionCommand("P");

            if (txt_id_pegawai.getText().isEmpty() || 
                txt_nama_pegawai.getText().isEmpty() || 
                txt_alamat_pegawai.getText().isEmpty() || 
                cmb_posisi_pegawai.getSelectedItem().toString().isEmpty() || 
                gender_group.getSelection().getActionCommand().isEmpty() || 
                txt_date_pegawai.getDateFormatString().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "LENGKAPI DATA ANDA!");
                if      (txt_id_pegawai.getText().isEmpty())        {txt_id_pegawai.requestFocus();} 
                else if (txt_nama_pegawai.getText().isEmpty())      {txt_nama_pegawai.requestFocus();} 
                else if (txt_alamat_pegawai.getText().isEmpty())    {txt_alamat_pegawai.requestFocus();} 
            } else {
                try {
                    Connection konek = new com.hotel.script.koneksi().getCon();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Tidak Dapat Terhubung ke Database! " + e);
                }

                admin_pegawai_dao dao = new admin_pegawai_dao();
                admin_pegawai_getset gs = new admin_pegawai_getset();
                gs.setId_pegawai(txt_id_pegawai.getText());
                gs.setNama_pegawai(txt_nama_pegawai.getText());
                gs.setTl_pegawai(date);
                gs.setAlamat_pegawai(txt_alamat_pegawai.getText());
                gs.setPosisi_pegawai(cmb_posisi_pegawai.getSelectedItem().toString());
                gs.setGender_pegawai(gender_group.getSelection().getActionCommand());
                
                dao.masukDataPegawai(gs);
                tampil_pegawai();
            }
            txt_id_pegawai.setText("");
            txt_nama_pegawai.setText("");
            txt_alamat_pegawai.setText("");
            txt_date_pegawai.setDateFormatString(""); date = "";
            cmb_posisi_pegawai.setSelectedItem(0);
            gender_group.clearSelection();

            txt_id_pegawai.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Sistem!");
        }
    }
    
    void ubah_akun() {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dFormat.format(txt_date_pegawai.getDate());
        
        try {
            rdo_lk.setActionCommand("L");
            rdo_pr.setActionCommand("P");

            if (txt_id_pegawai.getText().isEmpty() || 
                txt_nama_pegawai.getText().isEmpty() || 
                txt_alamat_pegawai.getText().isEmpty() || 
                cmb_posisi_pegawai.getSelectedItem().toString().isEmpty() || 
                gender_group.getSelection().getActionCommand().isEmpty() || 
                txt_date_pegawai.getDateFormatString().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "LENGKAPI DATA ANDA!");
                if      (txt_id_pegawai.getText().isEmpty())        {txt_id_pegawai.requestFocus();} 
                else if (txt_nama_pegawai.getText().isEmpty())      {txt_nama_pegawai.requestFocus();} 
                else if (txt_alamat_pegawai.getText().isEmpty())    {txt_alamat_pegawai.requestFocus();} 
            } else {
                try {
                    Connection konek = new com.hotel.script.koneksi().getCon();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Tidak Dapat Terhubung ke Database! " + e);
                }

                admin_pegawai_dao dao = new admin_pegawai_dao();
                admin_pegawai_getset gs = new admin_pegawai_getset();
                gs.setId_pegawai(txt_id_pegawai.getText());
                gs.setNama_pegawai(txt_nama_pegawai.getText());
                gs.setTl_pegawai(date);
                gs.setAlamat_pegawai(txt_alamat_pegawai.getText());
                gs.setPosisi_pegawai(cmb_posisi_pegawai.getSelectedItem().toString());
                gs.setGender_pegawai(gender_group.getSelection().getActionCommand());
                
                dao.ubahDataPegawai(gs);
                tampil_pegawai();
            }
            txt_id_pegawai.setText("");
            txt_nama_pegawai.setText("");
            txt_alamat_pegawai.setText("");
            txt_date_pegawai.setDateFormatString(""); date = "";
            cmb_posisi_pegawai.setSelectedItem(0);
            gender_group.clearSelection();

            btn_save_pegawai.setVisible(true);
            txt_id_pegawai.setEditable(true);
            txt_id_pegawai.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Sistem!");
        }
    }
    
    public void klik_akun(){
        btn_save_pegawai.setVisible(false);
        
        int row = tb_pegawai.getSelectedRow();
        txt_id_pegawai.setText(tb_pegawai.getModel().getValueAt(row, 0).toString());
        txt_id_pegawai.setEditable(false);
        txt_nama_pegawai.setText(tb_pegawai.getModel().getValueAt(row, 1).toString());
        txt_date_pegawai.setDateFormatString(tb_pegawai.getModel().getValueAt(row, 3).toString());
        txt_alamat_pegawai.setText(tb_pegawai.getModel().getValueAt(row, 4).toString());
        cmb_posisi_pegawai.setSelectedItem(tb_pegawai.getModel().getValueAt(row, 5).toString());
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
        full_body = new javax.swing.JPanel();
        head = new javax.swing.JLabel();
        main_panel = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        panel_pegawai = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel_akun = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panel_kamar = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panel_laporan = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panel_chart = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_logout = new javax.swing.JLabel();
        pegawai = new javax.swing.JPanel();
        btn_back_pegawai = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pegawai = new javax.swing.JTable();
        txt_id_pegawai = new javax.swing.JTextField();
        txt_nama_pegawai = new javax.swing.JTextField();
        txt_alamat_pegawai = new javax.swing.JTextField();
        rdo_lk = new javax.swing.JRadioButton();
        rdo_pr = new javax.swing.JRadioButton();
        txt_date_pegawai = new com.toedter.calendar.JDateChooser();
        cmb_posisi_pegawai = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btn_save_pegawai = new javax.swing.JLabel();
        btn_edit_pegawai = new javax.swing.JLabel();
        btn_hapus_pegawai = new javax.swing.JLabel();
        akun = new javax.swing.JPanel();
        btn_back_akun = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_akun = new javax.swing.JTable();
        txt_id_kamar = new javax.swing.JTextField();
        txt_username_akun = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        btn_save_akun = new javax.swing.JLabel();
        btn_edit_akun = new javax.swing.JLabel();
        btn_hapus_akun = new javax.swing.JLabel();
        txt_password_akun = new javax.swing.JPasswordField();
        kamar = new javax.swing.JPanel();
        btn_back_kamar = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_pegawai1 = new javax.swing.JTable();
        txt_nomor_kamar = new javax.swing.JTextField();
        txt_lantai_kamar = new javax.swing.JTextField();
        cmb_tipei_kamar = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btn_save_kamar = new javax.swing.JLabel();
        btn_edit_kamar = new javax.swing.JLabel();
        btn_hapus_kamar = new javax.swing.JLabel();
        cmb_status_kamar = new javax.swing.JComboBox<>();
        laporan = new javax.swing.JPanel();
        btn_back_laporan = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        diagram = new javax.swing.JPanel();
        btn_back_diagram = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        full_body.setBackground(new java.awt.Color(255, 255, 255));

        head.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        head.setForeground(new java.awt.Color(50, 55, 61));
        head.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        head.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/icon_admin.png"))); // NOI18N
        head.setText("  Administrator");

        main_panel.setBackground(new java.awt.Color(255, 255, 255));
        main_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                main_panelMouseEntered(evt);
            }
        });
        main_panel.setLayout(new java.awt.CardLayout());

        menu.setBackground(new java.awt.Color(255, 255, 255));

        panel_pegawai.setBackground(new java.awt.Color(255, 255, 255));
        panel_pegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_pegawaiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_pegawaiMouseEntered(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/data_pegawai.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(50, 55, 61));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pegawai");

        javax.swing.GroupLayout panel_pegawaiLayout = new javax.swing.GroupLayout(panel_pegawai);
        panel_pegawai.setLayout(panel_pegawaiLayout);
        panel_pegawaiLayout.setHorizontalGroup(
            panel_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_pegawaiLayout.setVerticalGroup(
            panel_pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pegawaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_akun.setBackground(new java.awt.Color(255, 255, 255));
        panel_akun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_akun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_akunMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_akunMouseEntered(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/data_akun.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(50, 55, 61));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Akun");

        javax.swing.GroupLayout panel_akunLayout = new javax.swing.GroupLayout(panel_akun);
        panel_akun.setLayout(panel_akunLayout);
        panel_akunLayout.setHorizontalGroup(
            panel_akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_akunLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_akunLayout.setVerticalGroup(
            panel_akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_akunLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_kamar.setBackground(new java.awt.Color(255, 255, 255));
        panel_kamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_kamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_kamarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_kamarMouseEntered(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/data_ruang.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(50, 55, 61));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Kamar");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout panel_kamarLayout = new javax.swing.GroupLayout(panel_kamar);
        panel_kamar.setLayout(panel_kamarLayout);
        panel_kamarLayout.setHorizontalGroup(
            panel_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_kamarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_kamarLayout.setVerticalGroup(
            panel_kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_kamarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_laporan.setBackground(new java.awt.Color(255, 255, 255));
        panel_laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_laporanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_laporanMouseEntered(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/data_lapor.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(50, 55, 61));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Laporan");
        jLabel8.setToolTipText("");

        javax.swing.GroupLayout panel_laporanLayout = new javax.swing.GroupLayout(panel_laporan);
        panel_laporan.setLayout(panel_laporanLayout);
        panel_laporanLayout.setHorizontalGroup(
            panel_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_laporanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_laporanLayout.setVerticalGroup(
            panel_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_laporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_chart.setBackground(new java.awt.Color(255, 255, 255));
        panel_chart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_chart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_chartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_chartMouseEntered(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/data_chart.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(50, 55, 61));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Diagram");
        jLabel10.setToolTipText("");

        javax.swing.GroupLayout panel_chartLayout = new javax.swing.GroupLayout(panel_chart);
        panel_chart.setLayout(panel_chartLayout);
        panel_chartLayout.setHorizontalGroup(
            panel_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_chartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_chartLayout.setVerticalGroup(
            panel_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_chartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_logout.png"))); // NOI18N
        btn_logout.setToolTipText("Logout");
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panel_akun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panel_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panel_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panel_chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(641, Short.MAX_VALUE)
                .addComponent(btn_logout)
                .addContainerGap(641, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_akun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(197, 197, 197)
                .addComponent(btn_logout)
                .addGap(25, 25, 25))
        );

        main_panel.add(menu, "card3");

        pegawai.setBackground(new java.awt.Color(255, 255, 255));
        pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pegawaiMouseEntered(evt);
            }
        });

        btn_back_pegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_back.png"))); // NOI18N
        btn_back_pegawai.setToolTipText("Menu");
        btn_back_pegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_back_pegawaiMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(50, 55, 61));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Data Pegawai");

        tb_pegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Tanggal Lahir", "Jenis Kelamin", "Alamat", "Jabatan"
            }
        ));
        tb_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_pegawaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_pegawai);
        if (tb_pegawai.getColumnModel().getColumnCount() > 0) {
            tb_pegawai.getColumnModel().getColumn(4).setHeaderValue("Alamat");
            tb_pegawai.getColumnModel().getColumn(5).setHeaderValue("Jabatan");
        }

        txt_id_pegawai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_id_pegawai.setForeground(new java.awt.Color(50, 55, 61));
        txt_id_pegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        txt_nama_pegawai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_nama_pegawai.setForeground(new java.awt.Color(50, 55, 61));
        txt_nama_pegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        txt_alamat_pegawai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_alamat_pegawai.setForeground(new java.awt.Color(50, 55, 61));
        txt_alamat_pegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        rdo_lk.setBackground(new java.awt.Color(255, 255, 255));
        gender_group.add(rdo_lk);
        rdo_lk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdo_lk.setText("Laki-laki");

        rdo_pr.setBackground(new java.awt.Color(255, 255, 255));
        gender_group.add(rdo_pr);
        rdo_pr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdo_pr.setText("Perempuan");

        txt_date_pegawai.setBackground(new java.awt.Color(255, 255, 255));
        txt_date_pegawai.setForeground(new java.awt.Color(50, 55, 61));
        txt_date_pegawai.setDateFormatString("dd-MMM-yyyy");

        cmb_posisi_pegawai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmb_posisi_pegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Resepsionis", "Chef", "Cleaning Service", "Security", " " }));
        cmb_posisi_pegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_posisi_pegawaiActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("ID Pegawai");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Nama");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tanggal Lahir");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Jenis Kelamin");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Alamat");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Jabatan");

        btn_save_pegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_save_pegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_save.png"))); // NOI18N
        btn_save_pegawai.setToolTipText("Simpan");
        btn_save_pegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_save_pegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_save_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_save_pegawaiMouseClicked(evt);
            }
        });

        btn_edit_pegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_edit_pegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_update.png"))); // NOI18N
        btn_edit_pegawai.setToolTipText("Edit");
        btn_edit_pegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_edit_pegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_pegawaiMouseClicked(evt);
            }
        });

        btn_hapus_pegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_hapus_pegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_delete.png"))); // NOI18N
        btn_hapus_pegawai.setToolTipText("Hapus");
        btn_hapus_pegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_hapus_pegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pegawaiLayout = new javax.swing.GroupLayout(pegawai);
        pegawai.setLayout(pegawaiLayout);
        pegawaiLayout.setHorizontalGroup(
            pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pegawaiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pegawaiLayout.createSequentialGroup()
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(45, 45, 45)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_posisi_pegawai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_alamat_pegawai)
                            .addComponent(txt_date_pegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_nama_pegawai)
                            .addComponent(txt_id_pegawai)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pegawaiLayout.createSequentialGroup()
                        .addComponent(btn_save_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btn_edit_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btn_hapus_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pegawaiLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(rdo_lk)
                        .addGap(35, 35, 35)
                        .addComponent(rdo_pr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pegawaiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back_pegawai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pegawaiLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17});

        pegawaiLayout.setVerticalGroup(
            pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pegawaiLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel11)
                .addGap(30, 30, 30)
                .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pegawaiLayout.createSequentialGroup()
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel12)
                            .addComponent(txt_id_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel13)
                            .addComponent(txt_nama_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_date_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(8, 8, 8)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(rdo_pr)
                            .addComponent(rdo_lk)
                            .addComponent(jLabel15))
                        .addGap(8, 8, 8)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_alamat_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(8, 8, 8)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cmb_posisi_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(15, 15, 15)
                        .addGroup(pegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_save_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_edit_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back_pegawai)
                .addGap(26, 26, 26))
        );

        pegawaiLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmb_posisi_pegawai, txt_alamat_pegawai, txt_date_pegawai});

        pegawaiLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel12, jLabel13, jLabel14, jLabel15, jLabel16, jLabel17, txt_id_pegawai});

        main_panel.add(pegawai, "card3");

        akun.setBackground(new java.awt.Color(255, 255, 255));
        akun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                akunMouseEntered(evt);
            }
        });

        btn_back_akun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_back.png"))); // NOI18N
        btn_back_akun.setToolTipText("Menu");
        btn_back_akun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back_akun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_back_akunMouseClicked(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(50, 55, 61));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Data Akun");

        tb_akun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username"
            }
        ));
        jScrollPane2.setViewportView(tb_akun);

        txt_id_kamar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_id_kamar.setForeground(new java.awt.Color(50, 55, 61));
        txt_id_kamar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        txt_username_akun.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_username_akun.setForeground(new java.awt.Color(50, 55, 61));
        txt_username_akun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setText("ID Pegawai");

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel63.setText("Username");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel64.setText("Password");

        btn_save_akun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_save_akun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_save.png"))); // NOI18N
        btn_save_akun.setToolTipText("Simpan");
        btn_save_akun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_save_akun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btn_edit_akun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_edit_akun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_update.png"))); // NOI18N
        btn_edit_akun.setToolTipText("Edit");
        btn_edit_akun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_edit_akun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btn_hapus_akun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_hapus_akun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_delete.png"))); // NOI18N
        btn_hapus_akun.setToolTipText("Hapus");
        btn_hapus_akun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_hapus_akun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txt_password_akun.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_password_akun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        javax.swing.GroupLayout akunLayout = new javax.swing.GroupLayout(akun);
        akun.setLayout(akunLayout);
        akunLayout.setHorizontalGroup(
            akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(akunLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, akunLayout.createSequentialGroup()
                        .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64))
                        .addGap(58, 58, 58)
                        .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_username_akun, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                            .addComponent(txt_id_kamar)
                            .addComponent(txt_password_akun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, akunLayout.createSequentialGroup()
                        .addComponent(btn_save_akun, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btn_edit_akun, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btn_hapus_akun, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(akunLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back_akun)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        akunLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_password_akun, txt_username_akun});

        akunLayout.setVerticalGroup(
            akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(akunLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel61)
                .addGap(30, 30, 30)
                .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(akunLayout.createSequentialGroup()
                        .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel62)
                            .addComponent(txt_id_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_username_akun, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63))
                        .addGap(8, 8, 8)
                        .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_password_akun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64))
                        .addGap(15, 15, 15)
                        .addGroup(akunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_save_akun, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_edit_akun, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus_akun, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back_akun)
                .addGap(25, 25, 25))
        );

        akunLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txt_password_akun, txt_username_akun});

        main_panel.add(akun, "card3");

        kamar.setBackground(new java.awt.Color(255, 255, 255));

        btn_back_kamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_back.png"))); // NOI18N
        btn_back_kamar.setToolTipText("Menu");
        btn_back_kamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back_kamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_back_kamarMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(50, 55, 61));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Data Kamar");

        tb_pegawai1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor Kamar", "Lantai", "Tipe", "Status"
            }
        ));
        jScrollPane3.setViewportView(tb_pegawai1);

        txt_nomor_kamar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_nomor_kamar.setForeground(new java.awt.Color(50, 55, 61));
        txt_nomor_kamar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        txt_lantai_kamar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_lantai_kamar.setForeground(new java.awt.Color(50, 55, 61));
        txt_lantai_kamar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));

        cmb_tipei_kamar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmb_tipei_kamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Superior Room", "Deluxe Room", "Family Room" }));
        cmb_tipei_kamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_tipei_kamarActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Nomor Kamar");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Lantai");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Tipe");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Status");

        btn_save_kamar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_save_kamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_save.png"))); // NOI18N
        btn_save_kamar.setToolTipText("Simpan");
        btn_save_kamar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_save_kamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btn_edit_kamar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_edit_kamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_update.png"))); // NOI18N
        btn_edit_kamar.setToolTipText("Edit");
        btn_edit_kamar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_edit_kamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btn_hapus_kamar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_hapus_kamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_delete.png"))); // NOI18N
        btn_hapus_kamar.setToolTipText("Hapus");
        btn_hapus_kamar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 55, 61)));
        btn_hapus_kamar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cmb_status_kamar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmb_status_kamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kosong", "Terisi" }));
        cmb_status_kamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_status_kamarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kamarLayout = new javax.swing.GroupLayout(kamar);
        kamar.setLayout(kamarLayout);
        kamarLayout.setHorizontalGroup(
            kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(kamarLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kamarLayout.createSequentialGroup()
                        .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(45, 45, 45)
                        .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmb_tipei_kamar, 0, 492, Short.MAX_VALUE)
                            .addComponent(txt_lantai_kamar)
                            .addComponent(txt_nomor_kamar)
                            .addComponent(cmb_status_kamar, 0, 492, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kamarLayout.createSequentialGroup()
                        .addComponent(btn_save_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btn_edit_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btn_hapus_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(kamarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back_kamar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kamarLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel22, jLabel23, jLabel24, jLabel25});

        kamarLayout.setVerticalGroup(
            kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kamarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(30, 30, 30)
                .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kamarLayout.createSequentialGroup()
                        .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel22)
                            .addComponent(txt_nomor_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel23)
                            .addComponent(txt_lantai_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cmb_tipei_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(8, 8, 8)
                        .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cmb_status_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(15, 15, 15)
                        .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_save_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_edit_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back_kamar)
                .addGap(25, 25, 25))
        );

        kamarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel22, jLabel23, jLabel24, jLabel25, txt_nomor_kamar});

        kamarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmb_status_kamar, cmb_tipei_kamar, txt_lantai_kamar});

        main_panel.add(kamar, "card3");

        laporan.setBackground(new java.awt.Color(255, 255, 255));

        btn_back_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_back.png"))); // NOI18N
        btn_back_laporan.setToolTipText("Menu");
        btn_back_laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_back_laporanMouseClicked(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(50, 55, 61));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Laporan");

        javax.swing.GroupLayout laporanLayout = new javax.swing.GroupLayout(laporan);
        laporan.setLayout(laporanLayout);
        laporanLayout.setHorizontalGroup(
            laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, laporanLayout.createSequentialGroup()
                .addContainerGap(641, Short.MAX_VALUE)
                .addComponent(btn_back_laporan)
                .addContainerGap(641, Short.MAX_VALUE))
            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        laporanLayout.setVerticalGroup(
            laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 466, Short.MAX_VALUE)
                .addComponent(btn_back_laporan)
                .addGap(25, 25, 25))
        );

        laporanLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_back_laporan, jLabel65});

        main_panel.add(laporan, "card3");

        diagram.setBackground(new java.awt.Color(255, 255, 255));

        btn_back_diagram.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hotel/images/btn_back.png"))); // NOI18N
        btn_back_diagram.setToolTipText("Menu");
        btn_back_diagram.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back_diagram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_back_diagramMouseClicked(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(50, 55, 61));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Diagram");

        javax.swing.GroupLayout diagramLayout = new javax.swing.GroupLayout(diagram);
        diagram.setLayout(diagramLayout);
        diagramLayout.setHorizontalGroup(
            diagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagramLayout.createSequentialGroup()
                .addContainerGap(641, Short.MAX_VALUE)
                .addComponent(btn_back_diagram)
                .addContainerGap(641, Short.MAX_VALUE))
            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        diagramLayout.setVerticalGroup(
            diagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagramLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 466, Short.MAX_VALUE)
                .addComponent(btn_back_diagram)
                .addGap(25, 25, 25))
        );

        diagramLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_back_diagram, jLabel66});

        main_panel.add(diagram, "card3");

        javax.swing.GroupLayout full_bodyLayout = new javax.swing.GroupLayout(full_body);
        full_body.setLayout(full_bodyLayout);
        full_bodyLayout.setHorizontalGroup(
            full_bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(head, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        full_bodyLayout.setVerticalGroup(
            full_bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(full_bodyLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(head)
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

    private void panel_pegawaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_pegawaiMouseEntered
        setColor(panel_pegawai);
        resetColor(panel_akun);
        resetColor(panel_kamar);
        resetColor(panel_laporan);
        resetColor(panel_chart);
    }//GEN-LAST:event_panel_pegawaiMouseEntered

    private void panel_akunMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_akunMouseEntered
        resetColor(panel_pegawai);
        setColor(panel_akun);
        resetColor(panel_kamar);
        resetColor(panel_laporan);
        resetColor(panel_chart);
    }//GEN-LAST:event_panel_akunMouseEntered

    private void panel_kamarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_kamarMouseEntered
        resetColor(panel_pegawai);
        resetColor(panel_akun);
        setColor(panel_kamar);
        resetColor(panel_laporan);
        resetColor(panel_chart);
    }//GEN-LAST:event_panel_kamarMouseEntered

    private void panel_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_laporanMouseEntered
        resetColor(panel_pegawai);
        resetColor(panel_akun);
        resetColor(panel_kamar);
        setColor(panel_laporan);
        resetColor(panel_chart);
    }//GEN-LAST:event_panel_laporanMouseEntered

    private void panel_chartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_chartMouseEntered
        resetColor(panel_pegawai);
        resetColor(panel_akun);
        resetColor(panel_kamar);
        resetColor(panel_laporan);
        setColor(panel_chart);
    }//GEN-LAST:event_panel_chartMouseEntered

    private void main_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_main_panelMouseEntered
        resetColor(panel_pegawai);
        resetColor(panel_akun);
        resetColor(panel_kamar);
        resetColor(panel_laporan);
        resetColor(panel_chart);
    }//GEN-LAST:event_main_panelMouseEntered

    private void cmb_posisi_pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_posisi_pegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_posisi_pegawaiActionPerformed

    private void panel_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_pegawaiMouseClicked
        menu.setVisible(false);
        pegawai.setVisible(true);        
        akun.setVisible(false);
        kamar.setVisible(false);
        laporan.setVisible(false);
        diagram.setVisible(false);
    }//GEN-LAST:event_panel_pegawaiMouseClicked

    private void btn_back_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_back_pegawaiMouseClicked
        toMenu();
    }//GEN-LAST:event_btn_back_pegawaiMouseClicked

    private void btn_back_akunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_back_akunMouseClicked
        toMenu();
    }//GEN-LAST:event_btn_back_akunMouseClicked

    private void panel_akunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_akunMouseClicked
        menu.setVisible(false);
        pegawai.setVisible(false);        
        akun.setVisible(true);
        kamar.setVisible(false);
        laporan.setVisible(false);
        diagram.setVisible(false);
    }//GEN-LAST:event_panel_akunMouseClicked

    private void btn_back_kamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_back_kamarMouseClicked
        toMenu();
    }//GEN-LAST:event_btn_back_kamarMouseClicked

    private void cmb_tipei_kamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_tipei_kamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_tipei_kamarActionPerformed

    private void cmb_status_kamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_status_kamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_status_kamarActionPerformed

    private void btn_back_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_back_laporanMouseClicked
        toMenu();
    }//GEN-LAST:event_btn_back_laporanMouseClicked

    private void btn_back_diagramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_back_diagramMouseClicked
        toMenu();
    }//GEN-LAST:event_btn_back_diagramMouseClicked

    private void panel_kamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_kamarMouseClicked
        menu.setVisible(false);
        pegawai.setVisible(false);        
        akun.setVisible(false);
        kamar.setVisible(true);
        laporan.setVisible(false);
        diagram.setVisible(false);
    }//GEN-LAST:event_panel_kamarMouseClicked

    private void panel_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_laporanMouseClicked
        menu.setVisible(false);
        pegawai.setVisible(false);        
        akun.setVisible(false);
        kamar.setVisible(false);
        laporan.setVisible(true);
        diagram.setVisible(false);
    }//GEN-LAST:event_panel_laporanMouseClicked

    private void panel_chartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_chartMouseClicked
        menu.setVisible(false);
        pegawai.setVisible(false);        
        akun.setVisible(false);
        kamar.setVisible(false);
        laporan.setVisible(false);
        diagram.setVisible(true);
    }//GEN-LAST:event_panel_chartMouseClicked

    private void btn_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseClicked
        LOGIN login = new LOGIN();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_logoutMouseClicked

    private void pegawaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pegawaiMouseEntered
        tampil_pegawai();
    }//GEN-LAST:event_pegawaiMouseEntered

    private void btn_save_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_save_pegawaiMouseClicked
        tulis_pegawai();
    }//GEN-LAST:event_btn_save_pegawaiMouseClicked

    private void btn_edit_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_pegawaiMouseClicked
        ubah_pegawai();
    }//GEN-LAST:event_btn_edit_pegawaiMouseClicked

    private void tb_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_pegawaiMouseClicked
        klik_pegawai();
    }//GEN-LAST:event_tb_pegawaiMouseClicked

    private void akunMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_akunMouseEntered
        tampil_akun();
    }//GEN-LAST:event_akunMouseEntered

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
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ADMIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel akun;
    private javax.swing.JLabel btn_back_akun;
    private javax.swing.JLabel btn_back_diagram;
    private javax.swing.JLabel btn_back_kamar;
    private javax.swing.JLabel btn_back_laporan;
    private javax.swing.JLabel btn_back_pegawai;
    private javax.swing.JLabel btn_edit_akun;
    private javax.swing.JLabel btn_edit_kamar;
    private javax.swing.JLabel btn_edit_pegawai;
    private javax.swing.JLabel btn_hapus_akun;
    private javax.swing.JLabel btn_hapus_kamar;
    private javax.swing.JLabel btn_hapus_pegawai;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JLabel btn_save_akun;
    private javax.swing.JLabel btn_save_kamar;
    private javax.swing.JLabel btn_save_pegawai;
    private javax.swing.JComboBox<String> cmb_posisi_pegawai;
    private javax.swing.JComboBox<String> cmb_status_kamar;
    private javax.swing.JComboBox<String> cmb_tipei_kamar;
    private javax.swing.JPanel diagram;
    private javax.swing.JPanel full_body;
    private javax.swing.ButtonGroup gender_group;
    private javax.swing.JLabel head;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel kamar;
    private javax.swing.JPanel laporan;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel panel_akun;
    private javax.swing.JPanel panel_chart;
    private javax.swing.JPanel panel_kamar;
    private javax.swing.JPanel panel_laporan;
    private javax.swing.JPanel panel_pegawai;
    private javax.swing.JPanel pegawai;
    private javax.swing.JRadioButton rdo_lk;
    private javax.swing.JRadioButton rdo_pr;
    private javax.swing.JTable tb_akun;
    private javax.swing.JTable tb_pegawai;
    private javax.swing.JTable tb_pegawai1;
    private javax.swing.JTextField txt_alamat_pegawai;
    private com.toedter.calendar.JDateChooser txt_date_pegawai;
    private javax.swing.JTextField txt_id_kamar;
    private javax.swing.JTextField txt_id_pegawai;
    private javax.swing.JTextField txt_lantai_kamar;
    private javax.swing.JTextField txt_nama_pegawai;
    private javax.swing.JTextField txt_nomor_kamar;
    private javax.swing.JPasswordField txt_password_akun;
    private javax.swing.JTextField txt_username_akun;
    // End of variables declaration//GEN-END:variables
}
