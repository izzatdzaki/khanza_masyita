/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */
package laporan;

import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import fungsi.batasInput;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import rekammedis.RMRiwayatPerawatan;
import simrskhanza.DlgCariCaraBayar;
import simrskhanza.DlgCariPoli;

/**
 *
 * @author perpustakaan
 */
public final class DlgStatusDataRM extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2, ps3, ps4, ps5, ps6;
    private ResultSet rs, rs2, rs3, rs4, rs5, rs6;
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgCariCaraBayar penjab = new DlgCariCaraBayar(null, false);
    private int i = 0, adasoapiralan = 0, tidakadasoapiralan = 0, adasoapiranap = 0, tidakadasoapiranap = 0, adaresumeralan = 0, tidakadaresumeralan = 0,
            adaresumeranap = 0, tidakadaresumeranap = 0, adatriaseigd = 0, tidakadatriaseigd = 0, adaaskepigd = 0, tidakadaaskepigd = 0, ada_penilaian_medis_igd = 0, tidak_ada_penilaian_medis_igd = 0, adaicd10 = 0, tidakadaicd10 = 0,
            adaicd9 = 0, tidakadaicd9 = 0, ada_penilaian_awal_keperawatan_ralan = 0, tidak_ada_penilaian_awal_keperawatan_ralan = 0, ada_penilaian_medis_ralan_penyakit_dalam = 0, tidak_ada_penilaian_medis_ralan_penyakit_dalam = 0, ada_penilaian_medis_ralan_mata = 0, tidak_ada_penilaian_medis_ralan_mata = 0, ada_penilaian_awal_keperawatan_gigi = 0, tidak_ada_penilaian_awal_keperawatan_gigi = 0, ada_penilaian_awal_keperawatan_kebidanan = 0, tidak_ada_penilaian_awal_keperawatan_kebidanan = 0, ada_penilaian_medis_ralan_kandungan = 0, tidak_ada_penilaian_medis_ralan_kandungan = 0,
            ada_penilaian_awal_keperawatan_ralan_bayi = 0, tidak_ada_penilaian_awal_keperawatan_ralan_bayi = 0, ada_penilaian_medis_ralan_anak = 0, tidak_ada_penilaian_medis_ralan_anak = 0, ada_penilaian_awal_keperawatan_ralan_psikiatri = 0, tidak_ada_penilaian_awal_keperawatan_ralan_psikiatri = 0,
            ada_penilaian_fisioterapi = 0, tidak_ada_penilaian_fisioterapi = 0, ada_penilaian_medis_ralan_rehab_medik = 0, tidak_ada_penilaian_medis_ralan_rehab_medik = 0,
            ada_penilaian_medis_ralan_psikiatrik = 0, tidak_ada_penilaian_medis_ralan_psikiatrik = 0, ada_penilaian_medis_ralan_bedah = 0, tidak_ada_penilaian_medis_ralan_bedah = 0, ada_penilaian_medis_ralan_neurologi = 0, tidak_ada_penilaian_medis_ralan_neurologi = 0,
            ada_penilaian_medis_ralan_paru = 0, tidak_ada_penilaian_medis_ralan_paru = 0, ada_penilaian_mcu = 0, tidak_ada_penilaian_mcu = 0,
            ada_penilaian_medis_ralan_kulitdankelamin = 0, tidak_ada_penilaian_medis_ralan_kulitdankelamin = 0, ada_penilaian_medis_ralan_orthopedi = 0, tidak_ada_penilaian_medis_ralan_orthopedi = 0, ada_penilaian_medis_hemodialisa = 0, tidak_ada_penilaian_medis_hemodialisa = 0,
            ada_penilaian_medis_ralan_bedah_mulut = 0, tidak_ada_penilaian_medis_ralan_bedah_mulut = 0, ada_penilaian_awal_keperawatan_ralan_geriatri = 0, tidak_ada_penilaian_awal_keperawatan_ralan_geriatri = 0, ada_penilaian_medis_ralan_geriatri = 0, tidak_ada_penilaian_medis_ralan_geriatri = 0,
            ada_penilaian_medis_ralan_tht = 0, tidak_ada_penilaian_medis_ralan_tht = 0, ada_bridging_sep = 0, tidak_ada_bridging_sep = 0, ada_rekonsiliasi = 0, tidak_ada_rekonsiliasi = 0, ada_konseling = 0, tidak_ada_konseling = 0, ada_hasillab = 0, tidak_ada_hasillab = 0,
            ada_gambarradiologi = 0, tidak_ada_gambarradiologi, ada_hasilradiologi, tidak_ada_hasilradiologi, ada_transferantarruang, tidak_ada_transferantarruang, ada_observasiigd, tidak_ada_observasiigd,
            ada_sep = 0, tidak_ada_sep =0, ada_soap = 0, tidak_ada_soap=0, ada_surkondpjp = 0, tidak_ada_surkondpjp = 0, ada_resumerj = 0,tidak_ada_resumerj = 0, ada_icd10 = 0, tidak_ada_icd10 = 0, ada_icd9 =0, tidak_ada_icd9 =0,
            ada_general_concent=0, tidak_ada_general_concent=0,ada_inform_concent=0, tidak_ada_inform_concent=0, ada_transfer_pasien=0, tidak_ada_transfer_pasien=0, ada_pernyataan_umum=0, tidak_ada_pernyataan_umum=0, ada_persetujuan_ranap=0, tidak_ada_persetujuan_ranap=0, ada_triase_ugd=0, tidak_ada_triase_ugd=0, ada_askep_kandungan=0, tidak_ada_askep_kandungan=0, ada_catatan_keperawatan=0, tidak_ada_catatan_keperawatan=0, ada_risiko_jatuh=0, tidak_ada_risiko_jatuh=0, ada_lembar_ews=0, tidak_ada_lembar_ews=0;
    private String soapiralan = "", soapiranap = "", resumeralan = "", resumeranap = "", pilihan = "", triaseigd = "", askepigd = "", penilaian_medis_igd = "", icd10 = "", icd9 = "", penilaian_awal_keperawatan_ralan = "", penilaian_medis_ralan_penyakit_dalam = "", penilaian_medis_ralan_mata = "", penilaian_awal_keperawatan_gigi = "", penilaian_awal_keperawatan_kebidanan = "", penilaian_medis_ralan_kandungan = "",
            penilaian_awal_keperawatan_ralan_bayi = "", penilaian_medis_ralan_anak = "", penilaian_awal_keperawatan_ralan_psikiatri = "", penilaian_fisioterapi = "", penilaian_medis_ralan_rehab_medik = "", penilaian_medis_ralan_psikiatrik = "", penilaian_medis_ralan_bedah = "", penilaian_medis_ralan_neurologi = "", penilaian_medis_ralan_paru = "", penilaian_mcu = "", penilaian_medis_ralan_kulitdankelamin = "", penilaian_medis_ralan_orthopedi = "", penilaian_medis_hemodialisa = "", penilaian_medis_ralan_bedah_mulut = "", penilaian_awal_keperawatan_ralan_geriatri = "", penilaian_medis_ralan_geriatri = "",
            penilaian_medis_ralan_tht = "", bridging_sep = "", rekonsiliasi = "", konseling = "", hasillab = "", gambarradiologi = "", hasilradiologi = "", transferantarruang, observasiigd, sep="", soap="", surkondpjp="", resumerj="", diagnosa10="", prosedur9="",
            generalconcent="",informconcern="",transferpasien="",pernyataanumum="",persetujuanranap="",askepkandungan="",catatankeperawatan="", risikojatuh="",lembarews="";
    private StringBuilder htmlContent;

    /**
     * Creates new form DlgLhtBiaya
     *
     * @param parent
     * @param modal
     */
    public DlgStatusDataRM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(885, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "Tanggal", "Dokter Dituju", "Nomer RM", "Pasien", "Poliklinik", "Status", "SOAPI Ralan", "SOAPI Ranap", "Resume Ralan", "Resume Ranap", "Triase IGD", "Askep IGD", "Asmed IGD", "ICD 10", "ICD 9", "Askep Ralan", "Asmed Ralan IPD", "Asmed Mata", "Askep Gigi", "Askep Obgyn", "Asmed Obgyn",
            "Askep Bayi/Anak", "Asmed Bayi/Anak", "Askep Psikiatri", "Fisioterapi", "Asmed Rehab", "Asmed Psikiatri", "Asmed Bedah", "Asmed Neurologi", "Asmed Paru", "MCU",
            "Asmed Kulit Kelamin", "Asmed Orthopedi", "Asmed Hemodialisa", "Asmed Bedah Mulut", "Askep Geriatri", "Asmed Geratri", "Asmed THT", "SEP"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbBangsal.setModel(tabMode);

        tbBangsal.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 40; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(43);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(78);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(64);
            } else if (i == 12) {
                column.setPreferredWidth(64);
            } else if (i == 13) {
                column.setPreferredWidth(54);
            } else if (i == 14) {
                column.setPreferredWidth(54);
            } else if (i == 15) {
                column.setPreferredWidth(84);
            } else if (i == 16) {
                column.setPreferredWidth(84);
            } else if (i == 17) {
                column.setPreferredWidth(84);
            } else if (i == 18) {
                column.setPreferredWidth(84);
            } else if (i == 19) {
                column.setPreferredWidth(84);
            } else if (i == 20) {
                column.setPreferredWidth(84);
            } else if (i == 21) {
                column.setPreferredWidth(84);
            } else if (i == 22) {
                column.setPreferredWidth(84);
            } else if (i == 23) {
                column.setPreferredWidth(84);
            } else if (i == 24) {
                column.setPreferredWidth(84);
            } else if (i == 25) {
                column.setPreferredWidth(84);
            } else if (i == 26) {
                column.setPreferredWidth(84);
            } else if (i == 27) {
                column.setPreferredWidth(84);
            } else if (i == 28) {
                column.setPreferredWidth(84);
            } else if (i == 29) {
                column.setPreferredWidth(84);
            } else if (i == 30) {
                column.setPreferredWidth(84);
            } else if (i == 31) {
                column.setPreferredWidth(84);
            } else if (i == 32) {
                column.setPreferredWidth(84);
            } else if (i == 33) {
                column.setPreferredWidth(84);
            } else if (i == 34) {
                column.setPreferredWidth(84);
            } else if (i == 35) {
                column.setPreferredWidth(84);
            } else if (i == 36) {
                column.setPreferredWidth(84);
            } else if (i == 37) {
                column.setPreferredWidth(84);
            } else if (i == 38) {
                column.setPreferredWidth(84);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "Tanggal", "Dokter Dituju", "Nomer RM", "Pasien", "Poliklinik", "Status", "Rekonsiliasi", "Konseling"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbBangsal2.setModel(tabMode2);

        tbBangsal2.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbBangsal2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbBangsal2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(43);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            }
        }
        tbBangsal2.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode3 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "Tanggal", "Dokter Dituju", "Nomer RM", "Pasien", "Poliklinik", "Status", "Hasil Lab"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbBangsal3.setModel(tabMode3);

        tbBangsal3.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbBangsal3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbBangsal3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(43);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            }
        }
        tbBangsal3.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode4 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "Tanggal", "Dokter Dituju", "Nomer RM", "Pasien", "Poliklinik", "Status", "Gambar Radiologi", "Expertise Radiologi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbBangsal4.setModel(tabMode4);

        tbBangsal4.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbBangsal4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbBangsal4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(43);
            } else if (i == 7) {
                column.setPreferredWidth(125);
            } else if (i == 8) {
                column.setPreferredWidth(125);
            }
        }
        tbBangsal4.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode5 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "Tanggal", "Dokter Dituju", "Nomer RM", "Pasien", "Poliklinik", "Status", "General Concent", "Inform Concent", 
            "Transfer Pasien", "Pernyataan Umum", "Persetujuan Ranap", "Triase UGD", "Askep Kandungan", "Catatan Keperawatan", "Risiko Jatuh", "Lembar EWS Dewasa"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbBangsal5.setModel(tabMode5);

        tbBangsal5.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbBangsal5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 17; i++) {
            TableColumn column = tbBangsal5.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(43);
            } else if (i == 7) {
                column.setPreferredWidth(110);
            } else if (i == 8) {
                column.setPreferredWidth(110);
            }else if (i == 9) {
                column.setPreferredWidth(95);
            }else if (i == 10) {
                column.setPreferredWidth(115);
            }else if (i == 11) {
                column.setPreferredWidth(115);
            }else if (i == 12) {
                column.setPreferredWidth(95);
            }else if (i == 13) {
                column.setPreferredWidth(95);
            }else if (i == 14) {
                column.setPreferredWidth(120);
            }else if (i == 15) {
                column.setPreferredWidth(90);
            }else if (i == 16) {
                column.setPreferredWidth(120);
            }
        }
        tbBangsal5.setDefaultRenderer(Object.class, new WarnaTable());
        
        
        tabMode6 = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "Tanggal", "Dokter Dituju", "Nomer RM", "Pasien", "Poliklinik", "Status", "SEP", "SOAP", "SURKON DPJP", "RESUME", "ICD 10", "ICD 9"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbBangsal6.setModel(tabMode6);

        tbBangsal6.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbBangsal6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbBangsal6.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(43);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(75);
            }
        }
        tbBangsal6.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((int) 90).getKata(TCari));
        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
            });
        }

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (poli.getTable().getSelectedRow() != -1) {
                    kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                    nmpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                }
                kdpoli.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                poli.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penjab.getTable().getSelectedRow() != -1) {
                    kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                    nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                }
                kdpenjab.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                penjab.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    penjab.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        ChkInput.setSelected(false);
        isForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        kdpoli = new widget.TextBox();
        kdpenjab = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbBangsal2 = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbBangsal3 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbBangsal4 = new widget.Table();
        internalFrame6 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbBangsal5 = new widget.Table();
        internalFrame7 = new widget.InternalFrame();
        Scroll5 = new widget.ScrollPane();
        tbBangsal6 = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.panelisi();
        label17 = new widget.Label();
        nmpoli = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        label19 = new widget.Label();
        nmpenjab = new widget.TextBox();
        BtnSeek3 = new widget.Button();
        jLabel18 = new widget.Label();
        Status = new widget.ComboBox();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        kdpoli.setEditable(false);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(75, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });

        kdpenjab.setEditable(false);
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.setPreferredSize(new java.awt.Dimension(75, 23));
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Status Data Rekam Medis Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass5.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(155, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnAll);

        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(jLabel7);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setName("tbBangsal"); // NOI18N
        tbBangsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsalMouseClicked(evt);
            }
        });
        tbBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBangsal);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Seluruh Status Data RM", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbBangsal2.setName("tbBangsal2"); // NOI18N
        tbBangsal2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsal2MouseClicked(evt);
            }
        });
        tbBangsal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsal2KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbBangsal2);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Status Data RM Farmasi", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbBangsal3.setName("tbBangsal3"); // NOI18N
        tbBangsal3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsal3MouseClicked(evt);
            }
        });
        tbBangsal3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsal3KeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbBangsal3);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Status Data RM Lab", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbBangsal4.setName("tbBangsal4"); // NOI18N
        tbBangsal4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsal4MouseClicked(evt);
            }
        });
        tbBangsal4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsal4KeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbBangsal4);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Status Data RM Radiologi", internalFrame5);

        internalFrame6.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbBangsal5.setName("tbBangsal5"); // NOI18N
        tbBangsal5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsal5MouseClicked(evt);
            }
        });
        tbBangsal5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsal5KeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbBangsal5);

        internalFrame6.add(Scroll4, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("IGD", internalFrame6);

        internalFrame7.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame7.setBorder(null);
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbBangsal6.setName("tbBangsal6"); // NOI18N
        tbBangsal6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsal6MouseClicked(evt);
            }
        });
        tbBangsal6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsal6KeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbBangsal6);

        internalFrame7.add(Scroll5, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("RJ", internalFrame7);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        PanelInput.setBackground(new java.awt.Color(255, 255, 255));
        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 65));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Filter Data");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 104));
        FormInput.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label17.setText("Asal Poli :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label17);

        nmpoli.setEditable(false);
        nmpoli.setName("nmpoli"); // NOI18N
        nmpoli.setPreferredSize(new java.awt.Dimension(190, 23));
        FormInput.add(nmpoli);

        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('3');
        BtnSeek2.setToolTipText("Alt+3");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek2);

        label19.setText("Cara Bayar :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(label19);

        nmpenjab.setEditable(false);
        nmpenjab.setName("nmpenjab"); // NOI18N
        nmpenjab.setPreferredSize(new java.awt.Dimension(190, 23));
        FormInput.add(nmpenjab);

        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek3.setMnemonic('3');
        BtnSeek3.setToolTipText("Alt+3");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        BtnSeek3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek3KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek3);

        jLabel18.setText("Status :");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(75, 23));
        FormInput.add(jLabel18);

        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Ralan", "Ranap" }));
        Status.setLightWeightPopupEnabled(false);
        Status.setName("Status"); // NOI18N
        Status.setPreferredSize(new java.awt.Dimension(92, 23));
        FormInput.add(Status);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnPrint.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                File g = new File("file2.css");
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                        + ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"
                        + ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                        + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                );
                bg.close();

                File f;
                BufferedWriter bw;

                pilihan = (String) JOptionPane.showInputDialog(null, "Silahkan pilih laporan..!", "Pilihan Cetak", JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Laporan 1 (HTML)", "Laporan 2 (WPS)", "Laporan 3 (CSV)"}, "Laporan 1 (HTML)");
                switch (pilihan) {
                    case "Laporan 1 (HTML)":
                        htmlContent = new StringBuilder();
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>No.Rawat</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Tanggal</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Dokter Dituju</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Nomer RM</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Pasien</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>Poliklinik</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='43px'>Status</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ralan</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ranap</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ralan</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ranap</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Triase IGD</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Askep IGD</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed IGD</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 10</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 9</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Ralan</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Ralan IPD</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Mata</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Gigi</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Obgyn</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Obgyn</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Bayi/Anak</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Bayi/Anak</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Psikiatri</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Fisioterapi</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Rehab</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Psikiatri</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Bedah</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Neurologi</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Paru</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>MCU</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Kulit&Kelamin</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Orthopedi</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Hemodialisa</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Bedah Mulut</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Geriatri</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Geriatri</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed THT</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>SEP</td>"
                                + "</tr>"
                        );
                        for (i = 0; i < tabMode.getRowCount(); i++) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 0) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 1) + "</td>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 2) + "</td>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 3) + "</td>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 4) + "</td>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 5) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 6) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 7) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 8) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 9) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 10) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 11) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 12) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 13) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 14) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 15) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 16) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 17) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 18) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 19) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 20) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 21) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 22) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 23) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 24) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 25) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 26) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 27) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 28) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 29) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 30) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 31) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 32) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 33) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 34) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 35) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 36) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 37) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 38) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 39) + "</td>"
                                    + "</tr>"
                            );
                        }

                        f = new File("StatusDataRM.html");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write("<html>"
                                + "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"
                                + "<body>"
                                + "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                + "<tr class='isi2'>"
                                + "<td valign='top' align='center'>"
                                + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                                + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                                + akses.getkontakrs() + ", E-mail : " + akses.getemailrs() + "<br><br>"
                                + "<font size='2' face='Tahoma'>REKAP STATUS DATA RM PERIODE " + Tgl1.getSelectedItem() + " s.d. " + Tgl2.getSelectedItem() + "<br><br></font>"
                                + "</td>"
                                + "</tr>"
                                + "</table>"
                                + "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                + htmlContent.toString()
                                + "</table>"
                                + "</body>"
                                + "</html>"
                        );

                        bw.close();
                        Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 2 (WPS)":
                        htmlContent = new StringBuilder();
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>No.Rawat</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Tanggal</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Dokter Dituju</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Nomer RM</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Pasien</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>Poliklinik</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='43px'>Status</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ralan</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ranap</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ralan</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ranap</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Triase IGD</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Askep IGD</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Asmed IGD</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 10</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 9</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Ralan</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Ralan IPD</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Mata</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Gigi</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Obgyn</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Obgyn</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Bayi/Anak</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Bayi/Anak</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Psikiatri</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Fisioterapi</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Rehab</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Psikiatri</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Bedah</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Neurologi</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Paru</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>MCU</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Kulit&Kelamin</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Orthopedi</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Hemodialisa</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Bedah Mulut</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Askep Geriatri</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed Geriatri</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Asmed THT</td>"
                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>SEP</td>"
                                + "</tr>"
                        );
                        for (i = 0; i < tabMode.getRowCount(); i++) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 0) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 1) + "</td>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 2) + "</td>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 3) + "</td>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 4) + "</td>"
                                    + "<td valign='top'>" + tabMode.getValueAt(i, 5) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 6) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 7) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 8) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 9) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 10) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 11) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 12) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 13) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 14) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 15) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 16) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 17) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 18) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 19) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 20) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 21) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 22) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 23) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 24) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 25) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 26) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 27) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 28) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 29) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 30) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 31) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 32) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 33) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 34) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 35) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 36) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 37) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 38) + "</td>"
                                    + "<td valign='top' align='center'>" + tabMode.getValueAt(i, 39) + "</td>"
                                    + "</tr>"
                            );
                        }

                        f = new File("StatusDataRM.wps");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write("<html>"
                                + "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"
                                + "<body>"
                                + "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                + "<tr class='isi2'>"
                                + "<td valign='top' align='center'>"
                                + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                                + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                                + akses.getkontakrs() + ", E-mail : " + akses.getemailrs() + "<br><br>"
                                + "<font size='2' face='Tahoma'>REKAP STATUS DATA RM PERIODE " + Tgl1.getSelectedItem() + " s.d. " + Tgl2.getSelectedItem() + "<br><br></font>"
                                + "</td>"
                                + "</tr>"
                                + "</table>"
                                + "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                + htmlContent.toString()
                                + "</table>"
                                + "</body>"
                                + "</html>"
                        );

                        bw.close();
                        Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 3 (CSV)":
                        htmlContent = new StringBuilder();
                        htmlContent.append(
                                "\"No.Rawat\";\"Tanggal\";\"Dokter Dituju\";\"Nomer RM\";\"Pasien\";\"Poliklinik\";\"Status\";\"SOAPI Ralan\";\"SOAPI Ranap\";\"Resume Ralan\";\"Resume Ranap\";\"Triase IGD\";\"Askep IGD\";\"Asmed IGD\";\"ICD 10\";\"ICD 9\";\"Askep Ralan\";\"Asmed Ralan IPD\";\"Asmed Mata\";\"Askep Gigi\";\"Askep Obgyn\";"
                                + "\"Asmed Obgyn\";\"Askep Bayi/Anak\";\"Asmed Bayi/Anak\";\"Askep Psikiatri\";\"Fisioterapi\";\"Asmed Rehab\";\"Asmed Psikiatri\";\"Asmed Bedah\";\"Asmed Neurologi\";\"Asmed Paru\";\"MCU\";"
                                + "\"Asmed Kuli&Kelamin\";\"Asmed Orthopedi\";\"Asmed Hemodialisa\";\"Asmed Bedah Mulut\";\"Askep Geriatri\";\"Asmed Geriatri\";\"Asmed THT\";\"SEP\"\n"
                        );
                        for (i = 0; i < tabMode.getRowCount(); i++) {
                            htmlContent.append(
                                    "\"" + tabMode.getValueAt(i, 0) + "\";\"" + tabMode.getValueAt(i, 1) + "\";\"" + tabMode.getValueAt(i, 2) + "\";\"" + tabMode.getValueAt(i, 3) + "\";\"" + tabMode.getValueAt(i, 4) + "\";"
                                    + "\"" + tabMode.getValueAt(i, 5) + "\";\"" + tabMode.getValueAt(i, 6) + "\";\"" + tabMode.getValueAt(i, 7) + "\";\"" + tabMode.getValueAt(i, 8) + "\";\"" + tabMode.getValueAt(i, 9) + "\";"
                                    + "\"" + tabMode.getValueAt(i, 10) + "\";\"" + tabMode.getValueAt(i, 11) + "\";\"" + tabMode.getValueAt(i, 12) + "\";\"" + tabMode.getValueAt(i, 13) + "\";\"" + tabMode.getValueAt(i, 14) + "\";\"" + tabMode.getValueAt(i, 15) + "\";\"" + tabMode.getValueAt(i, 16) + "\";\"" + tabMode.getValueAt(i, 17) + "\";\"" + tabMode.getValueAt(i, 18) + "\";\"" + tabMode.getValueAt(i, 19) + "\";\"" + tabMode.getValueAt(i, 20) + "\";\"" + tabMode.getValueAt(i, 21) + "\";\"" + tabMode.getValueAt(i, 22) + "\";"
                                    + "\"" + tabMode.getValueAt(i, 23) + "\";\"" + tabMode.getValueAt(i, 24) + "\";\"" + tabMode.getValueAt(i, 25) + "\";\"" + tabMode.getValueAt(i, 26) + "\";\"" + tabMode.getValueAt(i, 27) + "\";\"" + tabMode.getValueAt(i, 28) + "\";\"" + tabMode.getValueAt(i, 29) + "\";\"" + tabMode.getValueAt(i, 30) + "\";\"" + tabMode.getValueAt(i, 31) + "\";\"" + tabMode.getValueAt(i, 32) + "\";\"" + tabMode.getValueAt(i, 33) + "\";\"" + tabMode.getValueAt(i, 34) + "\";\"" + tabMode.getValueAt(i, 35) + "\";\"" + tabMode.getValueAt(i, 36) + "\";\"" + tabMode.getValueAt(i, 37) + "\";\"" + tabMode.getValueAt(i, 38) + "\";\"" + tabMode.getValueAt(i, 39) + "\"\n"
                            );
                        }

                        f = new File("StatusDataRM.csv");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write(htmlContent.toString());

                        bw.close();
                        Desktop.getDesktop().browse(f.toURI());
                        break;
                }
            } catch (Exception e) {
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            //Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnKeluar, TKd);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbBangsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsalMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbBangsalMouseClicked

    private void tbBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsalKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbBangsalKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    if (TabRawat.getSelectedIndex() == 0) {
        tampil();
    } else if (TabRawat.getSelectedIndex() == 1) {
        tampil2();
    } else if (TabRawat.getSelectedIndex() == 2) {
        tampil3();
    } else if (TabRawat.getSelectedIndex() == 3) {
        tampil4();
    } else if (TabRawat.getSelectedIndex() == 4) {
        tampil5();
    }else if (TabRawat.getSelectedIndex() == 5) {
        tampil6();
    }
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    } else {
        Valid.pindah(evt, TKd, BtnPrint);
    }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        kdpoli.setText("");
        nmpoli.setText("");
        kdpenjab.setText("");
        nmpenjab.setText("");
        Status.setSelectedIndex(0);
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {

        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpoli, kdpoli.getText());
            BtnAll.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpoli, kdpoli.getText());
            Tgl2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeek2ActionPerformed(null);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setAlwaysOnTop(false);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
        //Valid.pindah(evt,DTPCari2,TCari);
    }//GEN-LAST:event_BtnSeek2KeyPressed

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab, kdpenjab.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab, kdpenjab.getText());
            BtnAll.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab, kdpenjab.getText());
            Tgl2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeek2ActionPerformed(null);
        }
    }//GEN-LAST:event_kdpenjabKeyPressed

    private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setAlwaysOnTop(false);
        penjab.setVisible(true);
    }//GEN-LAST:event_BtnSeek3ActionPerformed

    private void BtnSeek3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek3KeyPressed
        //Valid.pindah(evt,DTPCari2,TCari);
    }//GEN-LAST:event_BtnSeek3KeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
            tampil2();
        }else if(TabRawat.getSelectedIndex()==2){
            tampil3();
        }else if(TabRawat.getSelectedIndex()==3){
            tampil4();
        }else if(TabRawat.getSelectedIndex()==4){
            tampil5();
        }else if(TabRawat.getSelectedIndex()==5){
            tampil6();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbBangsal2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsal2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal2MouseClicked

    private void tbBangsal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsal2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal2KeyPressed

    private void tbBangsal3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsal3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal3MouseClicked

    private void tbBangsal3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsal3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal3KeyPressed

    private void tbBangsal4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsal4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal4MouseClicked

    private void tbBangsal4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsal4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal4KeyPressed

    private void tbBangsal5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsal5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal5MouseClicked

    private void tbBangsal5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsal5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal5KeyPressed

    private void tbBangsal6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsal6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal6MouseClicked

    private void tbBangsal6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsal6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal6KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgStatusDataRM dialog = new DlgStatusDataRM(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek2;
    private widget.Button BtnSeek3;
    private widget.CekBox ChkInput;
    private widget.panelisi FormInput;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ComboBox Status;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.Label jLabel18;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.TextBox kdpenjab;
    private widget.TextBox kdpoli;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.TextBox nmpenjab;
    private widget.TextBox nmpoli;
    private widget.panelisi panelGlass5;
    private widget.Table tbBangsal;
    private widget.Table tbBangsal2;
    private widget.Table tbBangsal3;
    private widget.Table tbBangsal4;
    private widget.Table tbBangsal5;
    private widget.Table tbBangsal6;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode);
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,poliklinik.nm_poli,reg_periksa.status_lanjut "
                    + "from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli inner join penjab on reg_periksa.kd_pj=penjab.kd_pj where  "
                    + "concat(reg_periksa.kd_poli,poliklinik.nm_poli) like ? and concat(reg_periksa.kd_pj,penjab.png_jawab) like ? "
                    + "and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.status_lanjut like ? "
                    + (TCari.getText().equals("") ? "" : "and (reg_periksa.no_rawat like ? or dokter.nm_dokter like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or poliklinik.nm_poli like ? or penjab.png_jawab like ?) ") + "order by reg_periksa.tgl_registrasi");
            try {
                ps.setString(1, "%" + kdpoli.getText() + nmpoli.getText() + "%");
                ps.setString(2, "%" + kdpenjab.getText() + nmpenjab.getText() + "%");
                ps.setString(3, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(4, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(5, "%" + Status.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                if (!TCari.getText().trim().equals("")) {
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + TCari.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");
                    ps.setString(11, "%" + TCari.getText().trim() + "%");
                }

                rs = ps.executeQuery();
                adasoapiralan = 0;
                tidakadasoapiralan = 0;
                adasoapiranap = 0;
                tidakadasoapiranap = 0;
                adaresumeralan = 0;
                tidakadaresumeralan = 0;
                adaresumeranap = 0;
                tidakadaresumeranap = 0;
                adatriaseigd = 0;
                tidakadatriaseigd = 0;
                adaaskepigd = 0;
                tidakadaaskepigd = 0;
                ada_penilaian_medis_igd = 0;
                tidak_ada_penilaian_medis_igd = 0;
                adaicd10 = 0;
                tidakadaicd10 = 0;
                adaicd9 = 0;
                tidakadaicd9 = 0;
                ada_penilaian_awal_keperawatan_ralan = 0;
                tidak_ada_penilaian_awal_keperawatan_ralan = 0;
                ada_penilaian_medis_ralan_penyakit_dalam = 0;
                tidak_ada_penilaian_medis_ralan_penyakit_dalam = 0;
                ada_penilaian_medis_ralan_mata = 0;
                tidak_ada_penilaian_medis_ralan_mata = 0;
                ada_penilaian_awal_keperawatan_gigi = 0;
                tidak_ada_penilaian_awal_keperawatan_gigi = 0;
                ada_penilaian_awal_keperawatan_kebidanan = 0;
                tidak_ada_penilaian_awal_keperawatan_kebidanan = 0;
                ada_penilaian_medis_ralan_kandungan = 0;
                tidak_ada_penilaian_medis_ralan_kandungan = 0;
                ada_penilaian_awal_keperawatan_ralan_bayi = 0;
                tidak_ada_penilaian_awal_keperawatan_ralan_bayi = 0;
                ada_penilaian_medis_ralan_anak = 0;
                tidak_ada_penilaian_medis_ralan_anak = 0;
                ada_penilaian_awal_keperawatan_ralan_psikiatri = 0;
                tidak_ada_penilaian_awal_keperawatan_ralan_psikiatri = 0;
                ada_penilaian_fisioterapi = 0;
                tidak_ada_penilaian_fisioterapi = 0;
                ada_penilaian_medis_ralan_rehab_medik = 0;
                tidak_ada_penilaian_medis_ralan_rehab_medik = 0;
                ada_penilaian_medis_ralan_psikiatrik = 0;
                tidak_ada_penilaian_medis_ralan_psikiatrik = 0;
                ada_penilaian_medis_ralan_bedah = 0;
                tidak_ada_penilaian_medis_ralan_bedah = 0;
                ada_penilaian_medis_ralan_neurologi = 0;
                tidak_ada_penilaian_medis_ralan_neurologi = 0;
                ada_penilaian_medis_ralan_paru = 0;
                tidak_ada_penilaian_medis_ralan_paru = 0;
                ada_penilaian_mcu = 0;
                tidak_ada_penilaian_mcu = 0;
                ada_penilaian_medis_ralan_kulitdankelamin = 0;
                tidak_ada_penilaian_medis_ralan_kulitdankelamin = 0;
                ada_penilaian_medis_ralan_orthopedi = 0;
                tidak_ada_penilaian_medis_ralan_orthopedi = 0;
                ada_penilaian_medis_hemodialisa = 0;
                tidak_ada_penilaian_medis_hemodialisa = 0;
                ada_penilaian_medis_ralan_bedah_mulut = 0;
                tidak_ada_penilaian_medis_ralan_bedah_mulut = 0;
                ada_penilaian_awal_keperawatan_ralan_geriatri = 0;
                tidak_ada_penilaian_medis_ralan_geriatri = 0;
                ada_penilaian_medis_ralan_tht = 0;
                tidak_ada_penilaian_medis_ralan_tht = 0;
                ada_bridging_sep = 0;
                tidak_ada_bridging_sep = 0;
                while (rs.next()) {
                    soapiralan = Sequel.cariIsi("select if(count(pemeriksaan_ralan.no_rawat)>0,'Ada','Tidak Ada') from pemeriksaan_ralan where pemeriksaan_ralan.no_rawat=?", rs.getString("no_rawat"));
                    if (soapiralan.equals("Ada")) {
                        adasoapiralan++;
                    } else {
                        tidakadasoapiralan++;
                    }
                    soapiranap = Sequel.cariIsi("select if(count(pemeriksaan_ranap.no_rawat)>0,'Ada','Tidak Ada') from pemeriksaan_ranap where pemeriksaan_ranap.no_rawat=?", rs.getString("no_rawat"));
                    if (soapiranap.equals("Ada")) {
                        adasoapiranap++;
                    } else {
                        tidakadasoapiranap++;
                    }
                    resumeralan = Sequel.cariIsi("select if(count(resume_pasien.no_rawat)>0,'Ada','Tidak Ada') from resume_pasien where resume_pasien.no_rawat=?", rs.getString("no_rawat"));
                    if (resumeralan.equals("Ada")) {
                        adaresumeralan++;
                    } else {
                        tidakadaresumeralan++;
                    }
                    resumeranap = Sequel.cariIsi("select if(count(resume_pasien_ranap.no_rawat)>0,'Ada','Tidak Ada') from resume_pasien_ranap where resume_pasien_ranap.no_rawat=?", rs.getString("no_rawat"));
                    if (resumeranap.equals("Ada")) {
                        adaresumeranap++;
                    } else {
                        tidakadaresumeranap++;
                    }
                    triaseigd = Sequel.cariIsi("select if(count(data_triase_igd.no_rawat)>0,'Ada','Tidak Ada') from data_triase_igd where data_triase_igd.no_rawat=?", rs.getString("no_rawat"));
                    if (triaseigd.equals("Ada")) {
                        adatriaseigd++;
                    } else {
                        tidakadatriaseigd++;
                    }
                    askepigd = Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_igd.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_igd where penilaian_awal_keperawatan_igd.no_rawat=?", rs.getString("no_rawat"));
                    if (askepigd.equals("Ada")) {
                        adaaskepigd++;
                    } else {
                        tidakadaaskepigd++;
                    }
                    penilaian_medis_igd = Sequel.cariIsi("select if(count(penilaian_medis_igd.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_igd where penilaian_medis_igd.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_igd.equals("Ada")) {
                        ada_penilaian_medis_igd++;
                    } else {
                        tidak_ada_penilaian_medis_igd++;
                    }
                    icd10 = Sequel.cariIsi("select if(count(diagnosa_pasien.no_rawat)>0,'Ada','Tidak Ada') from diagnosa_pasien where diagnosa_pasien.no_rawat=?", rs.getString("no_rawat"));
                    if (icd10.equals("Ada")) {
                        adaicd10++;
                    } else {
                        tidakadaicd10++;
                    }
                    icd9 = Sequel.cariIsi("select if(count(prosedur_pasien.no_rawat)>0,'Ada','Tidak Ada') from prosedur_pasien where prosedur_pasien.no_rawat=?", rs.getString("no_rawat"));
                    if (icd9.equals("Ada")) {
                        adaicd9++;
                    } else {
                        tidakadaicd9++;
                    }

                    penilaian_awal_keperawatan_ralan = Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_ralan.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_ralan where penilaian_awal_keperawatan_ralan.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_awal_keperawatan_ralan.equals("Ada")) {
                        ada_penilaian_awal_keperawatan_ralan++;
                    } else {
                        tidak_ada_penilaian_awal_keperawatan_ralan++;
                    }

                    penilaian_medis_ralan_penyakit_dalam = Sequel.cariIsi("select if(count(penilaian_medis_ralan_penyakit_dalam.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_penyakit_dalam where penilaian_medis_ralan_penyakit_dalam.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_penyakit_dalam.equals("Ada")) {
                        ada_penilaian_medis_ralan_penyakit_dalam++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_penyakit_dalam++;
                    }
                    // tambah askep mata
                    penilaian_medis_ralan_mata = Sequel.cariIsi("select if(count(penilaian_medis_ralan_mata.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_mata where penilaian_medis_ralan_mata.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_mata.equals("Ada")) {
                        ada_penilaian_medis_ralan_mata++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_mata++;
                    }

                    penilaian_awal_keperawatan_gigi = Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_gigi.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_gigi where penilaian_awal_keperawatan_gigi.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_awal_keperawatan_gigi.equals("Ada")) {
                        ada_penilaian_awal_keperawatan_gigi++;
                    } else {
                        tidak_ada_penilaian_awal_keperawatan_gigi++;
                    }

                    penilaian_awal_keperawatan_kebidanan = Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_kebidanan.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_kebidanan where penilaian_awal_keperawatan_kebidanan.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_awal_keperawatan_kebidanan.equals("Ada")) {
                        ada_penilaian_awal_keperawatan_kebidanan++;
                    } else {
                        tidak_ada_penilaian_awal_keperawatan_kebidanan++;
                    }

                    penilaian_medis_ralan_kandungan = Sequel.cariIsi("select if(count(penilaian_medis_ralan_kandungan.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_kandungan where penilaian_medis_ralan_kandungan.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_kandungan.equals("Ada")) {
                        ada_penilaian_medis_ralan_kandungan++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_kandungan++;
                    }
                    // Tambahan Pantau Status RM
                    penilaian_awal_keperawatan_ralan_bayi = Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_ralan_bayi.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_ralan_bayi where penilaian_awal_keperawatan_ralan_bayi.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_awal_keperawatan_ralan_bayi.equals("Ada")) {
                        ada_penilaian_awal_keperawatan_ralan_bayi++;
                    } else {
                        tidak_ada_penilaian_awal_keperawatan_ralan_bayi++;
                    }

                    penilaian_medis_ralan_anak = Sequel.cariIsi("select if(count(penilaian_medis_ralan_anak.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_anak where penilaian_medis_ralan_anak.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_anak.equals("Ada")) {
                        ada_penilaian_medis_ralan_anak++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_anak++;
                    }

                    penilaian_awal_keperawatan_ralan_psikiatri = Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_ralan_psikiatri.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_ralan_psikiatri where penilaian_awal_keperawatan_ralan_psikiatri.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_awal_keperawatan_ralan_psikiatri.equals("Ada")) {
                        ada_penilaian_awal_keperawatan_ralan_psikiatri++;
                    } else {
                        tidak_ada_penilaian_awal_keperawatan_ralan_psikiatri++;
                    }

                    penilaian_fisioterapi = Sequel.cariIsi("select if(count(penilaian_fisioterapi.no_rawat)>0,'Ada','Tidak Ada') from penilaian_fisioterapi where penilaian_fisioterapi.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_fisioterapi.equals("Ada")) {
                        ada_penilaian_fisioterapi++;
                    } else {
                        tidak_ada_penilaian_fisioterapi++;
                    }

                    penilaian_medis_ralan_rehab_medik = Sequel.cariIsi("select if(count(penilaian_medis_ralan_rehab_medik.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_rehab_medik where penilaian_medis_ralan_rehab_medik.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_rehab_medik.equals("Ada")) {
                        ada_penilaian_medis_ralan_rehab_medik++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_rehab_medik++;
                    }
                    // tambahanan asmed psikiatri,bedah,neurologi,paru,mcu
                    penilaian_medis_ralan_psikiatrik = Sequel.cariIsi("select if(count(penilaian_medis_ralan_psikiatrik.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_psikiatrik where penilaian_medis_ralan_psikiatrik.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_psikiatrik.equals("Ada")) {
                        ada_penilaian_medis_ralan_psikiatrik++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_psikiatrik++;
                    }

                    penilaian_medis_ralan_bedah = Sequel.cariIsi("select if(count(penilaian_medis_ralan_bedah.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_bedah where penilaian_medis_ralan_bedah.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_bedah.equals("Ada")) {
                        ada_penilaian_medis_ralan_bedah++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_bedah++;
                    }

                    penilaian_medis_ralan_neurologi = Sequel.cariIsi("select if(count(penilaian_medis_ralan_neurologi.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_neurologi where penilaian_medis_ralan_neurologi.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_neurologi.equals("Ada")) {
                        ada_penilaian_medis_ralan_neurologi++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_neurologi++;
                    }

                    penilaian_medis_ralan_paru = Sequel.cariIsi("select if(count(penilaian_medis_ralan_paru.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_paru where penilaian_medis_ralan_paru.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_paru.equals("Ada")) {
                        ada_penilaian_medis_ralan_paru++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_paru++;
                    }

                    penilaian_mcu = Sequel.cariIsi("select if(count(penilaian_mcu.no_rawat)>0,'Ada','Tidak Ada') from penilaian_mcu where penilaian_mcu.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_mcu.equals("Ada")) {
                        ada_penilaian_mcu++;
                    } else {
                        tidak_ada_penilaian_mcu++;
                    }
                    // tambahan kulit,ortopedi,hemo,bedah mulut,askep geriatri,medis geriatri
                    penilaian_medis_ralan_kulitdankelamin = Sequel.cariIsi("select if(count(penilaian_medis_ralan_kulitdankelamin.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_kulitdankelamin where penilaian_medis_ralan_kulitdankelamin.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_kulitdankelamin.equals("Ada")) {
                        ada_penilaian_medis_ralan_kulitdankelamin++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_kulitdankelamin++;
                    }

                    penilaian_medis_ralan_orthopedi = Sequel.cariIsi("select if(count(penilaian_medis_ralan_orthopedi.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_orthopedi where penilaian_medis_ralan_orthopedi.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_orthopedi.equals("Ada")) {
                        ada_penilaian_medis_ralan_orthopedi++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_orthopedi++;
                    }

                    penilaian_medis_hemodialisa = Sequel.cariIsi("select if(count(penilaian_medis_hemodialisa.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_hemodialisa where penilaian_medis_hemodialisa.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_hemodialisa.equals("Ada")) {
                        ada_penilaian_medis_hemodialisa++;
                    } else {
                        tidak_ada_penilaian_medis_hemodialisa++;
                    }

                    penilaian_medis_ralan_bedah_mulut = Sequel.cariIsi("select if(count(penilaian_medis_ralan_bedah_mulut.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_bedah_mulut where penilaian_medis_ralan_bedah_mulut.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_bedah_mulut.equals("Ada")) {
                        ada_penilaian_medis_ralan_bedah_mulut++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_bedah_mulut++;
                    }

                    penilaian_awal_keperawatan_ralan_geriatri = Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_ralan_geriatri.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_ralan_geriatri where penilaian_awal_keperawatan_ralan_geriatri.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_awal_keperawatan_ralan_geriatri.equals("Ada")) {
                        ada_penilaian_awal_keperawatan_ralan_geriatri++;
                    } else {
                        tidak_ada_penilaian_awal_keperawatan_ralan_geriatri++;
                    }

                    penilaian_medis_ralan_geriatri = Sequel.cariIsi("select if(count(penilaian_medis_ralan_geriatri.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_geriatri where penilaian_medis_ralan_geriatri.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_geriatri.equals("Ada")) {
                        ada_penilaian_medis_ralan_geriatri++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_geriatri++;
                    }
                    // tambahan THT dan SEP
                    penilaian_medis_ralan_tht = Sequel.cariIsi("select if(count(penilaian_medis_ralan_tht.no_rawat)>0,'Ada','Tidak Ada') from penilaian_medis_ralan_tht where penilaian_medis_ralan_tht.no_rawat=?", rs.getString("no_rawat"));
                    if (penilaian_medis_ralan_tht.equals("Ada")) {
                        ada_penilaian_medis_ralan_tht++;
                    } else {
                        tidak_ada_penilaian_medis_ralan_tht++;
                    }

                    bridging_sep = Sequel.cariIsi("select if(count(bridging_sep.no_rawat)>0,'Ada','Tidak Ada') from bridging_sep where bridging_sep.no_rawat=?", rs.getString("no_rawat"));
                    if (bridging_sep.equals("Ada")) {
                        ada_bridging_sep++;
                    } else {
                        tidak_ada_bridging_sep++;
                    }

                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), rs.getString("tgl_registrasi"), rs.getString("nm_dokter"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("nm_poli"), rs.getString("status_lanjut"),
                        soapiralan, soapiranap, resumeralan, resumeranap, triaseigd, askepigd, penilaian_medis_igd, icd10, icd9, penilaian_awal_keperawatan_ralan, penilaian_medis_ralan_penyakit_dalam, penilaian_medis_ralan_mata,
                        penilaian_awal_keperawatan_gigi, penilaian_awal_keperawatan_kebidanan, penilaian_medis_ralan_kandungan, penilaian_awal_keperawatan_ralan_bayi, penilaian_medis_ralan_anak, penilaian_awal_keperawatan_ralan_psikiatri, penilaian_fisioterapi, penilaian_medis_ralan_rehab_medik,
                        penilaian_medis_ralan_psikiatrik, penilaian_medis_ralan_bedah, penilaian_medis_ralan_neurologi, penilaian_medis_ralan_paru, penilaian_mcu, penilaian_medis_ralan_kulitdankelamin, penilaian_medis_ralan_orthopedi, penilaian_medis_hemodialisa, penilaian_medis_ralan_bedah_mulut,
                        penilaian_awal_keperawatan_ralan_geriatri, penilaian_medis_ralan_geriatri, penilaian_medis_ralan_tht, bridging_sep
                    });
                }
                if (tabMode.getRowCount() > 0) {
                    tabMode.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Ada", ":", adasoapiralan, adasoapiranap, adaresumeralan, adaresumeranap, adatriaseigd, adaaskepigd, ada_penilaian_medis_igd, adaicd10, adaicd9, ada_penilaian_awal_keperawatan_ralan, ada_penilaian_medis_ralan_penyakit_dalam, ada_penilaian_medis_ralan_mata, ada_penilaian_awal_keperawatan_gigi, ada_penilaian_awal_keperawatan_kebidanan, ada_penilaian_medis_ralan_kandungan,
                        ada_penilaian_awal_keperawatan_ralan_bayi, ada_penilaian_medis_ralan_anak, ada_penilaian_awal_keperawatan_ralan_psikiatri, ada_penilaian_fisioterapi, ada_penilaian_medis_ralan_rehab_medik,
                        ada_penilaian_medis_ralan_psikiatrik, ada_penilaian_medis_ralan_bedah, ada_penilaian_medis_ralan_neurologi, ada_penilaian_medis_ralan_paru, ada_penilaian_mcu,
                        ada_penilaian_medis_ralan_kulitdankelamin, ada_penilaian_medis_ralan_orthopedi, ada_penilaian_medis_hemodialisa, ada_penilaian_medis_ralan_bedah_mulut, ada_penilaian_awal_keperawatan_ralan_geriatri, ada_penilaian_medis_ralan_geriatri,
                        ada_penilaian_medis_ralan_tht, ada_bridging_sep
                    });
                    tabMode.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Tidak Ada", ":", tidakadasoapiralan, tidakadasoapiranap, tidakadaresumeralan, tidakadaresumeranap, tidakadatriaseigd, tidakadaaskepigd, tidak_ada_penilaian_medis_igd, tidakadaicd10, tidakadaicd9, tidak_ada_penilaian_awal_keperawatan_ralan, tidak_ada_penilaian_medis_ralan_penyakit_dalam, tidak_ada_penilaian_medis_ralan_mata, tidak_ada_penilaian_awal_keperawatan_gigi, tidak_ada_penilaian_awal_keperawatan_kebidanan, tidak_ada_penilaian_medis_ralan_kandungan,
                        tidak_ada_penilaian_awal_keperawatan_ralan_bayi, tidak_ada_penilaian_medis_ralan_anak, tidak_ada_penilaian_awal_keperawatan_ralan_psikiatri, tidak_ada_penilaian_fisioterapi, tidak_ada_penilaian_medis_ralan_rehab_medik,
                        tidak_ada_penilaian_medis_ralan_psikiatrik, tidak_ada_penilaian_medis_ralan_bedah, tidak_ada_penilaian_medis_ralan_neurologi, tidak_ada_penilaian_medis_ralan_paru, tidak_ada_penilaian_mcu,
                        tidak_ada_penilaian_medis_ralan_kulitdankelamin, tidak_ada_penilaian_medis_ralan_orthopedi, tidak_ada_penilaian_medis_hemodialisa, tidak_ada_penilaian_medis_ralan_bedah_mulut, tidak_ada_penilaian_awal_keperawatan_ralan_geriatri,
                        tidak_ada_penilaian_medis_ralan_geriatri, tidak_ada_penilaian_medis_ralan_tht, tidak_ada_bridging_sep
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void getData() {
        int row = tbBangsal.getSelectedRow();
        if (row != -1) {
            TKd.setText(tabMode.getValueAt(row, 0).toString());
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 65));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void tampil2() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode2);
            ps2 = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,poliklinik.nm_poli,reg_periksa.status_lanjut "
                    + "from resep_obat inner join reg_periksa on resep_obat.no_rawat=reg_periksa.no_rawat inner join dokter on resep_obat.kd_dokter=dokter.kd_dokter inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli inner join penjab on reg_periksa.kd_pj=penjab.kd_pj where  "
                    + "concat(reg_periksa.kd_poli,poliklinik.nm_poli) like ? and concat(reg_periksa.kd_pj,penjab.png_jawab) like ? "
                    + "and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.status_lanjut like ? "
                    + (TCari.getText().equals("") ? "" : "and (reg_periksa.no_rawat like ? or dokter.nm_dokter like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or poliklinik.nm_poli like ? or penjab.png_jawab like ?) ") + "order by reg_periksa.tgl_registrasi");
            try {
                ps2.setString(1, "%" + kdpoli.getText() + nmpoli.getText() + "%");
                ps2.setString(2, "%" + kdpenjab.getText() + nmpenjab.getText() + "%");
                ps2.setString(3, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps2.setString(4, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps2.setString(5, "%" + Status.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                if (!TCari.getText().trim().equals("")) {
                    ps2.setString(6, "%" + TCari.getText().trim() + "%");
                    ps2.setString(7, "%" + TCari.getText().trim() + "%");
                    ps2.setString(8, "%" + TCari.getText().trim() + "%");
                    ps2.setString(9, "%" + TCari.getText().trim() + "%");
                    ps2.setString(10, "%" + TCari.getText().trim() + "%");
                    ps2.setString(11, "%" + TCari.getText().trim() + "%");
                }

                rs2 = ps2.executeQuery();
                ada_rekonsiliasi = 0;
                tidak_ada_rekonsiliasi = 0;
                ada_konseling = 0;
                tidak_ada_konseling = 0;
                while (rs2.next()) {
                    rekonsiliasi = Sequel.cariIsi("select if(count(rekonsiliasi_obat.no_rawat)>0,'Ada','Tidak Ada') from rekonsiliasi_obat where rekonsiliasi_obat.no_rawat=?", rs2.getString("no_rawat"));
                    if (rekonsiliasi.equals("Ada")) {
                        ada_rekonsiliasi++;
                    } else {
                        tidak_ada_rekonsiliasi++;
                    }

                    konseling = Sequel.cariIsi("select if(count(konseling_farmasi.no_rawat)>0,'Ada','Tidak Ada') from konseling_farmasi where konseling_farmasi.no_rawat=?", rs2.getString("no_rawat"));
                    if (konseling.equals("Ada")) {
                        ada_konseling++;
                    } else {
                        tidak_ada_konseling++;
                    }

                    tabMode2.addRow(new Object[]{
                        rs2.getString("no_rawat"), rs2.getString("tgl_registrasi"), rs2.getString("nm_dokter"), rs2.getString("no_rkm_medis"), rs2.getString("nm_pasien"), rs2.getString("nm_poli"), rs2.getString("status_lanjut"),
                        rekonsiliasi, konseling
                    });
                }
                if (tabMode2.getRowCount() > 0) {
                    tabMode2.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Ada", ":", ada_rekonsiliasi, ada_konseling
                    });
                    tabMode2.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Tidak Ada", ":", tidak_ada_rekonsiliasi, tidak_ada_konseling
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampil3() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode3);
            ps3 = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,poliklinik.nm_poli,reg_periksa.status_lanjut "
                    + "from permintaan_lab inner join reg_periksa on permintaan_lab.no_rawat=reg_periksa.no_rawat inner join dokter on permintaan_lab.dokter_perujuk=dokter.kd_dokter inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli inner join penjab on reg_periksa.kd_pj=penjab.kd_pj where  "
                    + "concat(reg_periksa.kd_poli,poliklinik.nm_poli) like ? and concat(reg_periksa.kd_pj,penjab.png_jawab) like ? "
                    + "and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.status_lanjut like ? "
                    + (TCari.getText().equals("") ? "" : "and (reg_periksa.no_rawat like ? or dokter.nm_dokter like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or poliklinik.nm_poli like ? or penjab.png_jawab like ?) ") + "order by reg_periksa.tgl_registrasi");
            //+ "from permintaan_lab inner join reg_periksa on permintaan_lab.no_rawat=reg_periksa.no_rawat inner join dokter on permintaan_lab.dokter_perujuk=dokter.kd_dokter inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
            try {
                ps3.setString(1, "%" + kdpoli.getText() + nmpoli.getText() + "%");
                ps3.setString(2, "%" + kdpenjab.getText() + nmpenjab.getText() + "%");
                ps3.setString(3, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps3.setString(4, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps3.setString(5, "%" + Status.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                if (!TCari.getText().trim().equals("")) {
                    ps3.setString(6, "%" + TCari.getText().trim() + "%");
                    ps3.setString(7, "%" + TCari.getText().trim() + "%");
                    ps3.setString(8, "%" + TCari.getText().trim() + "%");
                    ps3.setString(9, "%" + TCari.getText().trim() + "%");
                    ps3.setString(10, "%" + TCari.getText().trim() + "%");
                    ps3.setString(11, "%" + TCari.getText().trim() + "%");
                }

                rs3 = ps3.executeQuery();
                ada_hasillab = 0;
                tidak_ada_hasillab = 0;
                while (rs3.next()) {
                    hasillab = Sequel.cariIsi("select if(count(periksa_lab.no_rawat)>0,'Ada','Tidak Ada') from periksa_lab where periksa_lab.no_rawat=?", rs3.getString("no_rawat"));
                    if (hasillab.equals("Ada")) {
                        ada_hasillab++;
                    } else {
                        tidak_ada_hasillab++;
                    }

                    tabMode3.addRow(new Object[]{
                        rs3.getString("no_rawat"), rs3.getString("tgl_registrasi"), rs3.getString("nm_dokter"), rs3.getString("no_rkm_medis"), rs3.getString("nm_pasien"), rs3.getString("nm_poli"), rs3.getString("status_lanjut"),
                        hasillab
                    });
                }
                if (tabMode3.getRowCount() > 0) {
                    tabMode3.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Ada", ":", ada_hasillab
                    });
                    tabMode3.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Tidak Ada", ":", tidak_ada_hasillab
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampil4() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode4);
            ps4 = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,poliklinik.nm_poli,reg_periksa.status_lanjut "
                    + "from permintaan_radiologi inner join reg_periksa on permintaan_radiologi.no_rawat=reg_periksa.no_rawat inner join dokter on permintaan_radiologi.dokter_perujuk=dokter.kd_dokter inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli inner join penjab on reg_periksa.kd_pj=penjab.kd_pj where  "
                    + "concat(reg_periksa.kd_poli,poliklinik.nm_poli) like ? and concat(reg_periksa.kd_pj,penjab.png_jawab) like ? "
                    + "and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.status_lanjut like ? "
                    + (TCari.getText().equals("") ? "" : "and (reg_periksa.no_rawat like ? or dokter.nm_dokter like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or poliklinik.nm_poli like ? or penjab.png_jawab like ?) ") + "order by reg_periksa.tgl_registrasi"); 
                    
            try {
                ps4.setString(1, "%" + kdpoli.getText() + nmpoli.getText() + "%");
                ps4.setString(2, "%" + kdpenjab.getText() + nmpenjab.getText() + "%");
                ps4.setString(3, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps4.setString(4, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps4.setString(5, "%" + Status.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                if (!TCari.getText().trim().equals("")) {
                    ps4.setString(6, "%" + TCari.getText().trim() + "%");
                    ps4.setString(7, "%" + TCari.getText().trim() + "%");
                    ps4.setString(8, "%" + TCari.getText().trim() + "%");
                    ps4.setString(9, "%" + TCari.getText().trim() + "%");
                    ps4.setString(10, "%" + TCari.getText().trim() + "%");
                    ps4.setString(11, "%" + TCari.getText().trim() + "%");
                }

                rs4 = ps4.executeQuery();
                ada_gambarradiologi = 0;
                tidak_ada_gambarradiologi = 0;
                ada_hasilradiologi = 0;
                tidak_ada_hasilradiologi = 0;
                while (rs4.next()) {
                    gambarradiologi = Sequel.cariIsi("select if(count(gambar_radiologi.no_rawat)>0,'Ada','Tidak Ada') from gambar_radiologi where gambar_radiologi.no_rawat=?", rs4.getString("no_rawat"));
                    if (gambarradiologi.equals("Ada")) {
                        ada_gambarradiologi++;
                    } else {
                        tidak_ada_gambarradiologi++;
                    }

                    hasilradiologi = Sequel.cariIsi("select if(count(hasil_radiologi.no_rawat)>0,'Ada','Tidak Ada') from hasil_radiologi where hasil_radiologi.no_rawat=?", rs4.getString("no_rawat"));
                    if (hasilradiologi.equals("Ada")) {
                        ada_hasilradiologi++;
                    } else {
                        tidak_ada_hasilradiologi++;
                    }

                    tabMode4.addRow(new Object[]{
                        rs4.getString("no_rawat"), rs4.getString("tgl_registrasi"), rs4.getString("nm_dokter"), rs4.getString("no_rkm_medis"), rs4.getString("nm_pasien"), rs4.getString("nm_poli"), rs4.getString("status_lanjut"),
                        gambarradiologi, hasilradiologi
                    });
                }
                if (tabMode4.getRowCount() > 0) {
                    tabMode4.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Ada", ":", ada_gambarradiologi, ada_hasilradiologi
                    });
                    tabMode4.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Tidak Ada", ":", tidak_ada_gambarradiologi, tidak_ada_hasilradiologi
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }

    public void tampil5() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode5);
            ps5 = koneksi.prepareStatement(
            "select reg_periksa.no_rawat, reg_periksa.tgl_registrasi, dokter.nm_dokter, reg_periksa.no_rkm_medis, pasien.nm_pasien, poliklinik.nm_poli, reg_periksa.status_lanjut "
            + "from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
            + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
            + "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "
            + "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
            + "where concat(reg_periksa.kd_poli, poliklinik.nm_poli) like ? "
            + "and concat(reg_periksa.kd_pj, penjab.png_jawab) like ? "
            + "and reg_periksa.tgl_registrasi between ? and ? "
            + "and reg_periksa.status_lanjut like ? "
            + (TCari.getText().equals("") ? "" : 
            "and (reg_periksa.no_rawat like ? or dokter.nm_dokter like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? "
            + "or penjab.png_jawab like ?) ") 
            + "and poliklinik.nm_poli like 'IGD' or poliklinik.nm_poli like 'Unit IGD' "
            + "order by reg_periksa.tgl_registrasi");

            try {
                ps5.setString(1, "%" + kdpoli.getText() + nmpoli.getText() + "%");
                ps5.setString(2, "%" + kdpenjab.getText() + nmpenjab.getText() + "%");
                ps5.setString(3, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps5.setString(4, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps5.setString(5, "%" + Status.getSelectedItem().toString().replaceAll("Semua", "") + "%");

                if (!TCari.getText().trim().equals("")) {
                    ps5.setString(6, "%" + TCari.getText().trim() + "%");
                    ps5.setString(7, "%" + TCari.getText().trim() + "%");
                    ps5.setString(8, "%" + TCari.getText().trim() + "%");
                    ps5.setString(9, "%" + TCari.getText().trim() + "%");
                    ps5.setString(10, "%" + TCari.getText().trim() + "%");
                }

                rs5 = ps5.executeQuery();
                ada_general_concent=0;
                tidak_ada_general_concent=0;
                ada_inform_concent=0;
                tidak_ada_inform_concent=0;
                ada_transfer_pasien=0;
                tidak_ada_transfer_pasien=0;
                ada_pernyataan_umum=0;
                tidak_ada_pernyataan_umum=0;
                ada_persetujuan_ranap=0;
                tidak_ada_persetujuan_ranap=0;
                ada_triase_ugd=0;
                tidak_ada_triase_ugd=0;
                ada_askep_kandungan=0;
                tidak_ada_askep_kandungan=0;
                ada_catatan_keperawatan=0;
                tidak_ada_catatan_keperawatan=0;
                ada_risiko_jatuh=0;
                tidak_ada_risiko_jatuh=0;
                ada_lembar_ews=0;
                tidak_ada_lembar_ews=0;
                while (rs5.next()) {
                    generalconcent = Sequel.cariIsi("select if(count(surat_persetujuan_umum.no_rawat)>0,'Ada','Tidak Ada') from surat_persetujuan_umum where surat_persetujuan_umum.no_rawat=?", rs5.getString("no_rawat"));
                    if (generalconcent.equals("Ada")) {
                        ada_general_concent++;
                    } else {
                        tidak_ada_general_concent++;
                    }

                    informconcern = Sequel.cariIsi("select if(count(persetujuan_penolakan_tindakan.no_rawat)>0,'Ada','Tidak Ada') from persetujuan_penolakan_tindakan where persetujuan_penolakan_tindakan.no_rawat=?", rs5.getString("no_rawat"));
                    if (informconcern.equals("Ada")) {
                        ada_inform_concent++;
                    } else {
                        tidak_ada_inform_concent++;
                    }
                    
                    transferpasien = Sequel.cariIsi("select if(count(transfer_pasien_antar_ruang.no_rawat)>0,'Ada','Tidak Ada') from transfer_pasien_antar_ruang where transfer_pasien_antar_ruang.no_rawat=?", rs5.getString("no_rawat"));
                    if (transferpasien.equals("Ada")) {
                        ada_transfer_pasien++;
                    } else {
                        tidak_ada_transfer_pasien++;
                    }
                    
                    observasiigd = Sequel.cariIsi("select if(count(catatan_observasi_igd.no_rawat)>0,'Ada','Tidak Ada') from catatan_observasi_igd where catatan_observasi_igd.no_rawat=?", rs5.getString("no_rawat"));
                    if (observasiigd.equals("Ada")) {
                        ada_observasiigd++;
                    } else {
                        tidak_ada_observasiigd++;
                    }
                    
                    observasiigd = Sequel.cariIsi("select if(count(catatan_observasi_igd.no_rawat)>0,'Ada','Tidak Ada') from catatan_observasi_igd where catatan_observasi_igd.no_rawat=?", rs5.getString("no_rawat"));
                    if (observasiigd.equals("Ada")) {
                        ada_observasiigd++;
                    } else {
                        tidak_ada_observasiigd++;
                    }
                    
                    pernyataanumum = Sequel.cariIsi("select if(count(surat_pernyataan_pasien_umum.no_rawat)>0,'Ada','Tidak Ada') from surat_pernyataan_pasien_umum where surat_pernyataan_pasien_umum.no_rawat=?", rs5.getString("no_rawat"));
                    if (pernyataanumum.equals("Ada")) {
                        ada_pernyataan_umum++;
                    } else {
                        tidak_ada_pernyataan_umum++;
                    }
                    
                    persetujuanranap = Sequel.cariIsi("select if(count(surat_persetujuan_rawat_inap.no_rawat)>0,'Ada','Tidak Ada') from surat_persetujuan_rawat_inap where surat_persetujuan_rawat_inap.no_rawat=?", rs5.getString("no_rawat"));
                    if (persetujuanranap.equals("Ada")) {
                        ada_persetujuan_ranap++;
                    } else {
                        tidak_ada_persetujuan_ranap++;
                    }
                    
                    triaseigd = Sequel.cariIsi("select if(count(data_triase_igd.no_rawat)>0,'Ada','Tidak Ada') from data_triase_igd where data_triase_igd.no_rawat=?", rs5.getString("no_rawat"));
                    if (triaseigd.equals("Ada")) {
                        ada_triase_ugd++;
                    } else {
                        tidak_ada_triase_ugd++;
                    }
                    
                    askepkandungan = Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_kebidanan.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_kebidanan where penilaian_awal_keperawatan_kebidanan.no_rawat=?", rs5.getString("no_rawat"));
                    if (askepkandungan.equals("Ada")) {
                        ada_askep_kandungan++;
                    } else {
                        tidak_ada_askep_kandungan++;
                    }
                    
                    catatankeperawatan = Sequel.cariIsi("select if(count(catatan_keperawatan_ralan.no_rawat)>0,'Ada','Tidak Ada') from catatan_keperawatan_ralan where catatan_keperawatan_ralan.no_rawat=?", rs5.getString("no_rawat"));
                    if (catatankeperawatan.equals("Ada")) {
                        ada_catatan_keperawatan++;
                    } else {
                        tidak_ada_catatan_keperawatan++;
                    }
                    
                    risikojatuh = Sequel.cariIsi("select if(count(penilaian_lanjutan_resiko_jatuh_dewasa.no_rawat)>0,'Ada','Tidak Ada') from penilaian_lanjutan_resiko_jatuh_dewasa where penilaian_lanjutan_resiko_jatuh_dewasa.no_rawat=?", rs5.getString("no_rawat"));
                    if (risikojatuh.equals("Ada")) {
                        ada_risiko_jatuh++;
                    } else {
                        tidak_ada_risiko_jatuh++;
                    }
                    
                    lembarews = Sequel.cariIsi("select if(count(pemantauan_pews_dewasa.no_rawat)>0,'Ada','Tidak Ada') from pemantauan_pews_dewasa where pemantauan_pews_dewasa.no_rawat=?", rs5.getString("no_rawat"));
                    if (lembarews.equals("Ada")) {
                        ada_lembar_ews++;
                    } else {
                        tidak_ada_lembar_ews++;
                    }
                    
                    observasiigd = Sequel.cariIsi("select if(count(catatan_observasi_igd.no_rawat)>0,'Ada','Tidak Ada') from catatan_observasi_igd where catatan_observasi_igd.no_rawat=?", rs5.getString("no_rawat"));
                    if (observasiigd.equals("Ada")) {
                        ada_observasiigd++;
                    } else {
                        tidak_ada_observasiigd++;
                    }

                    tabMode5.addRow(new Object[]{
                        rs5.getString("no_rawat"), rs5.getString("tgl_registrasi"), rs5.getString("nm_dokter"), rs5.getString("no_rkm_medis"), rs5.getString("nm_pasien"), rs5.getString("nm_poli"), rs5.getString("status_lanjut"),
                        generalconcent, informconcern, transferpasien, pernyataanumum,persetujuanranap,triaseigd,askepkandungan,catatankeperawatan,risikojatuh,lembarews
                    });
                }
                if (tabMode5.getRowCount() > 0) {
                    tabMode5.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Ada", ":", ada_general_concent, ada_inform_concent,ada_transfer_pasien,ada_pernyataan_umum,ada_persetujuan_ranap,ada_triase_ugd,ada_askep_kandungan,ada_catatan_keperawatan,ada_risiko_jatuh,ada_lembar_ews
                    });
                    tabMode5.addRow(new Object[]{
                        "", "", "", "", "", "Status Data Tidak Ada", ":", tidak_ada_general_concent, tidak_ada_inform_concent,tidak_ada_transfer_pasien,tidak_ada_pernyataan_umum,tidak_ada_persetujuan_ranap,tidak_ada_triase_ugd,tidak_ada_askep_kandungan,tidak_ada_catatan_keperawatan,tidak_ada_risiko_jatuh,tidak_ada_lembar_ews
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }
    
    public void tampil6() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode6);
            ps6 = koneksi.prepareStatement(
            "select reg_periksa.no_rawat, reg_periksa.tgl_registrasi, dokter.nm_dokter, reg_periksa.no_rkm_medis, pasien.nm_pasien, poliklinik.nm_poli, reg_periksa.status_lanjut "
            + "from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
            + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
            + "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "
            + "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
            + "where concat(reg_periksa.kd_poli, poliklinik.nm_poli) like ? "
            + "and concat(reg_periksa.kd_pj, penjab.png_jawab) like ? "
            + "and reg_periksa.tgl_registrasi between ? and ? "
            + "and reg_periksa.status_lanjut like ? "
            + (TCari.getText().equals("") ? "" : 
            "and (reg_periksa.no_rawat like ? or dokter.nm_dokter like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ? "
            + "or penjab.png_jawab like ?) ") 
            + "and poliklinik.nm_poli not like 'IGD' and poliklinik.nm_poli not like 'Unit IGD' "  // Mengecualikan IGD
            + "order by reg_periksa.tgl_registrasi");

            try {
                ps6.setString(1, "%" + kdpoli.getText() + nmpoli.getText() + "%");
                ps6.setString(2, "%" + kdpenjab.getText() + nmpenjab.getText() + "%");
                ps6.setString(3, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps6.setString(4, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps6.setString(5, "%" + Status.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                if (!TCari.getText().trim().equals("")) {
                    ps6.setString(6, "%" + TCari.getText().trim() + "%");
                    ps6.setString(7, "%" + TCari.getText().trim() + "%");
                    ps6.setString(8, "%" + TCari.getText().trim() + "%");
                    ps6.setString(9, "%" + TCari.getText().trim() + "%");
                    ps6.setString(10, "%" + TCari.getText().trim() + "%");
                }
                rs6 = ps6.executeQuery();
                ada_sep = 0;
                tidak_ada_sep =0;
                ada_soap = 0;
                tidak_ada_soap =0;
                ada_surkondpjp = 0;
                tidak_ada_surkondpjp = 0;
                ada_resumerj = 0;
                tidak_ada_resumerj = 0;
                ada_icd10 = 0;
                tidak_ada_icd10 = 0;
                ada_icd9 =0;
                tidak_ada_icd9=0;
                
                while (rs6.next()) {
                    sep = Sequel.cariIsi("select if(count(bridging_sep.no_rawat)>0,'Ada','Tidak Ada') from bridging_sep where bridging_sep.no_rawat=?", rs6.getString("no_rawat"));
                    if (sep.equals("Ada")) {
                        ada_sep++;
                    } else {
                        tidak_ada_sep++;
                    }

                    soap = Sequel.cariIsi("select if(count(pemeriksaan_ralan.no_rawat)>0,'Ada','Tidak Ada') from pemeriksaan_ralan where pemeriksaan_ralan.no_rawat=?", rs6.getString("no_rawat"));
                    if (soap.equals("Ada")) {
                        ada_soap++;
                    } else {
                        tidak_ada_soap++;
                    }
                    
                    surkondpjp = Sequel.cariIsi("select if(count(skdp_bpjs.no_rkm_medis)>0,'Ada','Tidak Ada') from skdp_bpjs where skdp_bpjs.no_rkm_medis=?", rs6.getString("no_rkm_medis"));
                    if (surkondpjp.equals("Ada")) {
                        ada_surkondpjp++;
                    } else {
                        tidak_ada_surkondpjp++;
                    }
                    
                    resumerj = Sequel.cariIsi("select if(count(resume_pasien.no_rawat)>0,'Ada','Tidak Ada') from resume_pasien where resume_pasien.no_rawat=?", rs6.getString("no_rawat"));
                    if (resumerj.equals("Ada")) {
                        ada_resumerj++;
                    } else {
                        tidak_ada_resumerj++;
                    }
                    
                    diagnosa10 = Sequel.cariIsi("select if(count(diagnosa_pasien.no_rawat)>0,'Ada','Tidak Ada') from diagnosa_pasien where diagnosa_pasien.no_rawat=?", rs6.getString("no_rawat"));
                    if (diagnosa10.equals("Ada")) {
                        ada_icd10++;
                    } else {
                        tidak_ada_icd10++;
                    }
                    
                    prosedur9 = Sequel.cariIsi("select if(count(prosedur_pasien.no_rawat)>0,'Ada','Tidak Ada') from prosedur_pasien where prosedur_pasien.no_rawat=?", rs6.getString("no_rawat"));
                    if (prosedur9.equals("Ada")) {
                        ada_icd9++;
                    } else {
                        tidak_ada_icd9++;
                    }
                    

                    tabMode6.addRow(new Object[]{
                        rs6.getString("no_rawat"), rs6.getString("tgl_registrasi"), rs6.getString("nm_dokter"), rs6.getString("no_rkm_medis"), rs6.getString("nm_pasien"), rs6.getString("nm_poli"), rs6.getString("status_lanjut"),
                        sep, soap, surkondpjp, resumerj,diagnosa10,prosedur9
                    });
                }
                if (tabMode6.getRowCount() > 0) {
                    tabMode6.addRow(new Object[]{"", "", "", "", "", "Status Data Ada", ":", ada_sep, ada_soap,ada_surkondpjp,ada_resumerj,ada_icd10,ada_icd9});
                    tabMode6.addRow(new Object[]{"", "", "", "", "", "Status Data Tidak Ada", ":", tidak_ada_sep, tidak_ada_soap,tidak_ada_surkondpjp,tidak_ada_resumerj,tidak_ada_icd10,tidak_ada_icd9});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }

}
