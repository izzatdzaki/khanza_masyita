package simrskhanza;
import kepegawaian.DlgCariPegawai;
import bridging.DUKCAPILJakartaCekNik;
import bridging.DUKCAPILJakartaPostLahir;
import fungsi.WarnaTable;
import fungsi.WarnaTable5;
import fungsi.batasInput;
import grafikanalisa.grafikberat;
import grafikanalisa.grafikjkelbayi;
import grafikanalisa.grafiklahirbulan;
import grafikanalisa.grafiklahirtahun;
import grafikanalisa.grafikpanjang;
import grafikanalisa.grafikproses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DlgIKBBayi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabModeAPGAR;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private Date lahir;
    private PreparedStatement ps;
    private ResultSet rs;
    private LocalDate today=LocalDate.now();
    private LocalDate birthday;
    private Period p;
    private long p2;
    private DlgCariPegawai pegawai=new DlgCariPegawai(null,false);
    private DUKCAPILJakartaCekNik cekViaDukcapilJakarta=new DUKCAPILJakartaCekNik();
    private DUKCAPILJakartaPostLahir postlahir=new DUKCAPILJakartaPostLahir();
    private String pengurutan="",bulan="",tahun="",awalantahun="",awalanbulan="",posisitahun="",
            nokk="",nmbayi="",tgllhr="",jamlhr="",jk="",jnslhr="",lahirke="",brt="",
            pjg="",pnlglhr="",nikibu="",nmibu="",alamatibu="",kerjaibu="",nikayah="",
            nmayah="",alamatayah="",kerjaayah="",noskl="",pnlgnama="",tindaklhr="",
            bpjsibu="",bpjsayah="",notlp="",bpjsby="",nikplpr="",nmplpr="",almtplpr="",
            krjplpr="",niks1="",nms1="",almts1="",krjs1="",niks2="",nms2="",almts2="",
            krjs2="",umribu="",umrayah="",umrplpr="",umrs1="",umrs2="", finger="";

    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgIKBBayi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.RM","Nama Anak/Bayi","J.K","Tgl.Lahir","Jam Lahir",
            "Umur","Tgl.Daftar","Nama Ibu","Umur Ibu","Nama Ayah",
            "Umur Ayah","Alamat Ibu","B.B.","P.B.",
            "Lk.Kepala","Proses Lahir","Kelahiran Ke","Keterangan",
            "Diagnosa","Penyulit Kehamilan","Ketuban","Lk.Perut",
            "Lk.Dada","Penolong","No.SKL","G","P","A","F 1","U 1",
            "T 1","R 1","W 1","N 1'","F 5","U 5","T 5","R 5","W 5",
            "N 5'","F 10","U 10","T 10","R 10","W 10","N 10'","Resusitas", 
            "Obat Yang Diberikan","Mikasi","Mikonium"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbDokter.setModel(tabMode);
        tbDokter.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 50; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(150);
            }else if(i==2){
                column.setPreferredWidth(30);
            }else if(i==3){
                column.setPreferredWidth(75);
            }else if(i==4){
                column.setPreferredWidth(65);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(70);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(60);
            }else if(i==9){
                column.setPreferredWidth(150);
            }else if(i==10){
                column.setPreferredWidth(60);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(30);
            }else if(i==13){
                column.setPreferredWidth(30);
            }else if(i==14){
                column.setPreferredWidth(60);
            }else if(i==15){
                column.setPreferredWidth(90);
            }else if(i==16){
                column.setPreferredWidth(70);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(100);
            }else if(i==19){
                column.setPreferredWidth(100);
            }else if(i==20){
                column.setPreferredWidth(100);
            }else if(i==21){
                column.setPreferredWidth(50);
            }else if(i==22){
                column.setPreferredWidth(50);
            }else if(i==23){
                column.setPreferredWidth(150);
            }else if(i==24){
                column.setPreferredWidth(150);
            }else if(i==25){
                column.setPreferredWidth(30);
            }else if(i==26){
                column.setPreferredWidth(30);
            }else if(i==27){
                column.setPreferredWidth(30);
            }else if(i==28){
                column.setPreferredWidth(25);
            }else if(i==29){
                column.setPreferredWidth(25);
            }else if(i==30){
                column.setPreferredWidth(25);
            }else if(i==31){
                column.setPreferredWidth(25);
            }else if(i==32){
                column.setPreferredWidth(25);
            }else if(i==33){
                column.setPreferredWidth(30);
            }else if(i==34){
                column.setPreferredWidth(25);
            }else if(i==35){
                column.setPreferredWidth(25);
            }else if(i==36){
                column.setPreferredWidth(25);
            }else if(i==37){
                column.setPreferredWidth(25);
            }else if(i==38){
                column.setPreferredWidth(25);
            }else if(i==39){
                column.setPreferredWidth(30);
            }else if(i==40){
                column.setPreferredWidth(30);
            }else if(i==41){
                column.setPreferredWidth(30);
            }else if(i==42){
                column.setPreferredWidth(30);
            }else if(i==43){
                column.setPreferredWidth(30);
            }else if(i==44){
                column.setPreferredWidth(33);
            }else if(i==45){
                column.setPreferredWidth(35);
            }else if(i==46){
                column.setPreferredWidth(150);
            }else if(i==47){
                column.setPreferredWidth(150);
            }else if(i==48){
                column.setPreferredWidth(150);
            }else if(i==49){
                column.setPreferredWidth(150);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeAPGAR=new DefaultTableModel(null,new Object[]{
                "Tanda","0","1","2","N 1'","N 5'","N 10'"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                    boolean a = false;
                    if ((colIndex==4)||(colIndex==5)||(colIndex==6)) {
                        a=true;
                    }
                    return a;
             }
             Class[] types = new Class[] {
                java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,
                java.lang.String.class,java.lang.String.class,java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }  
        };
        tbAPGAR.setModel(tabModeAPGAR);
        tbAPGAR.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 7; i++) {
            TableColumn column = tbAPGAR.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(55);
            }else if(i==1){
                column.setPreferredWidth(61);
            }else if(i==2){
                column.setPreferredWidth(120);
            }else if(i==3){
                column.setPreferredWidth(63);
            }else if(i==4){
                column.setPreferredWidth(28);
            }else if(i==5){
                column.setPreferredWidth(28);
            }else if(i==6){
                column.setPreferredWidth(33);              
            }
        }
        
        tbAPGAR.setRowHeight(34);
        tbAPGAR.setDefaultRenderer(Object.class, new WarnaTable5());
        tabModeAPGAR.addRow(new Object[]{"Frekuensi Jantung","Tidak Ada","< 100","> 100","","",""});
        tabModeAPGAR.addRow(new Object[]{"Usaha Nafas","Tidak Ada","Lambat Tak Teratur","Menangis Kuat","","",""});
        tabModeAPGAR.addRow(new Object[]{"Tanus Otot","Lumpuh","Ext. Fleksi Sedikit","Gerakan Aktif","","",""});
        tabModeAPGAR.addRow(new Object[]{"Refleks","Tidak Ada Respon","Pergerakan Sedikit","Menangis","","",""});
        tabModeAPGAR.addRow(new Object[]{"Warna","Biru Pucat","Tubuh Kemerahan, Tangan & Kaki Biru","Kemerahan","","",""});

        NoRm.setDocument(new batasInput((byte)15).getKata(NoRm));
        NmBayi.setDocument(new batasInput((byte)40).getKata(NmBayi));
        AlamatIbu.setDocument(new batasInput((int)200).getKata(AlamatIbu));
        Nmibu.setDocument(new batasInput((byte)50).getKata(Nmibu));
        UmurIbu.setDocument(new batasInput((byte)8).getKata(UmurIbu));
        NmAyah.setDocument(new batasInput((byte)50).getKata(NmAyah));
        UmurAyah.setDocument(new batasInput((byte)8).getKata(UmurAyah));
        Proses.setDocument(new batasInput((byte)60).getKata(Proses));
        Diagnosa.setDocument(new batasInput((byte)60).getKata(Diagnosa));
        Ketuban.setDocument(new batasInput((byte)60).getKata(Ketuban));
        PenyulitKehamilan.setDocument(new batasInput((byte)60).getKata(PenyulitKehamilan));
        Berat.setDocument(new batasInput((byte)10).getOnlyAngka(Berat));
        Panjang.setDocument(new batasInput((byte)10).getKata(Panjang));
        LingkarKepala.setDocument(new batasInput((byte)10).getKata(LingkarKepala));
        LingkarPerut.setDocument(new batasInput((byte)10).getKata(LingkarPerut));
        LingkarDada.setDocument(new batasInput((byte)10).getKata(LingkarDada));
        Keterangan.setDocument(new batasInput((byte)50).getKata(Keterangan));
        NoSKL.setDocument(new batasInput((byte)30).getKata(NoSKL));
        Anakke.setDocument(new batasInput((byte)2).getOnlyAngka(Anakke));
        NIKAyah.setDocument(new batasInput((byte)40).getKata(NIKAyah));
        NIKIbu.setDocument(new batasInput((byte)40).getKata(NIKIbu));
        TelpOrtu.setDocument(new batasInput((byte)15).getKata(TelpOrtu));
        BPJSAyah.setDocument(new batasInput((byte)40).getKata(BPJSAyah));
        BPJSIbu.setDocument(new batasInput((byte)40).getKata(BPJSIbu));
        BPJSBayi.setDocument(new batasInput((byte)40).getKata(BPJSBayi));
        G.setDocument(new batasInput((byte)10).getKata(G));
        P.setDocument(new batasInput((byte)10).getKata(P));
        A.setDocument(new batasInput((byte)10).getKata(A));
        Resusitas.setDocument(new batasInput((int)100).getKata(Resusitas));
        ObatDiberikan.setDocument(new batasInput((int)300).getKata(ObatDiberikan));
        Mikasi.setDocument(new batasInput((int)100).getKata(Mikasi));
        Mikonium.setDocument(new batasInput((int)100).getKata(Mikonium));
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        } 
        
        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pegawai.getTable().getSelectedRow()!= -1){                   
                    KdPenolong.setText(pegawai.tbKamar.getValueAt(pegawai.tbKamar.getSelectedRow(),0).toString());
                    NmPenolong.setText(pegawai.tbKamar.getValueAt(pegawai.tbKamar.getSelectedRow(),1).toString());
                }   
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        pengurutan=Sequel.cariIsi("select urutan from set_urut_no_rkm_medis");
        tahun=Sequel.cariIsi("select tahun from set_urut_no_rkm_medis");
        bulan=Sequel.cariIsi("select bulan from set_urut_no_rkm_medis");
        posisitahun=Sequel.cariIsi("select posisi_tahun_bulan from set_urut_no_rkm_medis");
    }
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private String[] hlm;
    private String jkelcari="",tglcari="";
    /*
    private DlgIbu ibu=new DlgIbu(null,false);
    private DlgInapBayi ranap=new DlgInapBayi(null,false);
    private DlgJalanBayi ralan=new DlgJalanBayi(null,false);*/

    /** This method is called from within the constructor to
     * initialize the forem.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppGrafikjkbayi = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        ppGrafikberat = new javax.swing.JMenuItem();
        ppGrafikberatlaki = new javax.swing.JMenuItem();
        ppGrafikberatwn = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ppGrafikpanjang = new javax.swing.JMenuItem();
        ppGrafikpanjanglaki = new javax.swing.JMenuItem();
        ppGrafikpanjangwn = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        ppGrafiklahirtahun = new javax.swing.JMenuItem();
        ppGrafiklahirtahunlaki = new javax.swing.JMenuItem();
        ppGrafiklahirtahunwn = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        ppGrafiklahirbulan = new javax.swing.JMenuItem();
        ppGrafiklahirbulanlaki = new javax.swing.JMenuItem();
        ppGrafiklahirbulanwn = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        ppGrafikproseslahir = new javax.swing.JMenuItem();
        ppGrafikproseslahirlaki = new javax.swing.JMenuItem();
        ppGrafikproseslahirwn = new javax.swing.JMenuItem();
        MnKartu = new javax.swing.JMenuItem();
        MnInformasiBayi = new javax.swing.JMenuItem();
        MnSKL = new javax.swing.JMenuItem();
        MnSKL1 = new javax.swing.JMenuItem();
        MnSKL2 = new javax.swing.JMenuItem();
        Kd2 = new widget.TextBox();
        DlgBridgingLahir = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        scrollPane3 = new widget.ScrollPane();
        panelBiasa2 = new widget.PanelBiasa();
        BtnKeluar2 = new widget.Button();
        label17 = new widget.Label();
        NIKIbu = new widget.TextBox();
        BtnCari1 = new widget.Button();
        NIKAyah = new widget.TextBox();
        BtnCari2 = new widget.Button();
        label42 = new widget.Label();
        label43 = new widget.Label();
        JenisLahir = new widget.ComboBox();
        PenolongLahir = new widget.ComboBox();
        label44 = new widget.Label();
        label45 = new widget.Label();
        CaraLahir = new widget.ComboBox();
        BtnSimpan1 = new widget.Button();
        label46 = new widget.Label();
        TelpOrtu = new widget.TextBox();
        label47 = new widget.Label();
        BPJSAyah = new widget.TextBox();
        label48 = new widget.Label();
        BPJSIbu = new widget.TextBox();
        label49 = new widget.Label();
        BPJSBayi = new widget.TextBox();
        label50 = new widget.Label();
        NIKPelapor = new widget.TextBox();
        BtnCari3 = new widget.Button();
        label51 = new widget.Label();
        NamaPelapor = new widget.TextBox();
        label52 = new widget.Label();
        PekerjaanPelapor = new widget.ComboBox();
        label53 = new widget.Label();
        AlamatPelapor = new widget.TextBox();
        label54 = new widget.Label();
        UmurPelapor = new widget.TextBox();
        label55 = new widget.Label();
        NIKSaksi1 = new widget.TextBox();
        BtnCari4 = new widget.Button();
        label56 = new widget.Label();
        NamaSaksi1 = new widget.TextBox();
        AlamatSaksi1 = new widget.TextBox();
        label57 = new widget.Label();
        label58 = new widget.Label();
        PekerjaanSaksi1 = new widget.ComboBox();
        UmurSaksi1 = new widget.TextBox();
        label59 = new widget.Label();
        label60 = new widget.Label();
        UmurAyah2 = new widget.TextBox();
        label61 = new widget.Label();
        NIKSaksi2 = new widget.TextBox();
        BtnCari5 = new widget.Button();
        label62 = new widget.Label();
        NamaSaksi2 = new widget.TextBox();
        label63 = new widget.Label();
        AlamatSaksi2 = new widget.TextBox();
        label64 = new widget.Label();
        PekerjaanSaksi2 = new widget.ComboBox();
        label65 = new widget.Label();
        UmurSaksi2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelisi2 = new widget.panelisi();
        label29 = new widget.Label();
        cmbCrJk = new widget.ComboBox();
        ckTglCari = new widget.CekBox();
        DTPCari1 = new widget.Tanggal();
        label33 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        cmbHlm = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        label12 = new widget.Label();
        NoRm = new widget.TextBox();
        label18 = new widget.Label();
        label22 = new widget.Label();
        label24 = new widget.Label();
        Proses = new widget.TextBox();
        Anakke = new widget.TextBox();
        label25 = new widget.Label();
        LingkarKepala = new widget.TextBox();
        label27 = new widget.Label();
        label28 = new widget.Label();
        JKel = new widget.ComboBox();
        label23 = new widget.Label();
        label30 = new widget.Label();
        Lahir = new widget.Tanggal();
        label31 = new widget.Label();
        Diagnosa = new widget.TextBox();
        Nmibu = new widget.TextBox();
        label26 = new widget.Label();
        label19 = new widget.Label();
        NmAyah = new widget.TextBox();
        label20 = new widget.Label();
        UmurIbu = new widget.TextBox();
        label21 = new widget.Label();
        AlamatIbu = new widget.TextBox();
        label32 = new widget.Label();
        jam = new widget.ComboBox();
        menit = new widget.ComboBox();
        detik = new widget.ComboBox();
        label34 = new widget.Label();
        Berat = new widget.TextBox();
        Panjang = new widget.TextBox();
        Daftar = new widget.Tanggal();
        scrollPane2 = new widget.ScrollPane();
        Keterangan = new widget.TextArea();
        NmBayi = new widget.TextBox();
        label35 = new widget.Label();
        UmurAyah = new widget.TextBox();
        label36 = new widget.Label();
        UmurBayi = new widget.TextBox();
        label37 = new widget.Label();
        PenyulitKehamilan = new widget.TextBox();
        label38 = new widget.Label();
        Ketuban = new widget.TextBox();
        label39 = new widget.Label();
        LingkarDada = new widget.TextBox();
        LingkarPerut = new widget.TextBox();
        label40 = new widget.Label();
        label41 = new widget.Label();
        NoSKL = new widget.TextBox();
        jLabel24 = new widget.Label();
        KdPenolong = new widget.TextBox();
        NmPenolong = new widget.TextBox();
        BtnPenjab = new widget.Button();
        BtnKelurahan1 = new widget.Button();
        label66 = new widget.Label();
        jLabel101 = new widget.Label();
        G = new widget.TextBox();
        jLabel102 = new widget.Label();
        P = new widget.TextBox();
        jLabel103 = new widget.Label();
        A = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        tbAPGAR = new widget.Table();
        label13 = new widget.Label();
        label67 = new widget.Label();
        Resusitas = new widget.TextBox();
        label68 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        ObatDiberikan = new widget.TextArea();
        label69 = new widget.Label();
        Mikasi = new widget.TextBox();
        label70 = new widget.Label();
        Mikonium = new widget.TextBox();
        label71 = new widget.Label();
        N10 = new widget.TextBox2();
        N5 = new widget.TextBox2();
        N1 = new widget.TextBox2();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();

        Popup.setName("Popup"); // NOI18N

        ppGrafikjkbayi.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikjkbayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikjkbayi.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikjkbayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikjkbayi.setText("Grafik Jns.Kelamin Bayi");
        ppGrafikjkbayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikjkbayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikjkbayi.setName("ppGrafikjkbayi"); // NOI18N
        ppGrafikjkbayi.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikjkbayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikjkbayiActionPerformed(evt);
            }
        });
        Popup.add(ppGrafikjkbayi);

        jMenu1.setBackground(new java.awt.Color(250, 255, 245));
        jMenu1.setForeground(new java.awt.Color(50, 50, 50));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu1.setText("Grafik Berat Bayi");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.setPreferredSize(new java.awt.Dimension(180, 30));

        ppGrafikberat.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikberat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikberat.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikberat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikberat.setText("Keseluruhan");
        ppGrafikberat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikberat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikberat.setName("ppGrafikberat"); // NOI18N
        ppGrafikberat.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikberat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikberatActionPerformed(evt);
            }
        });
        jMenu1.add(ppGrafikberat);

        ppGrafikberatlaki.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikberatlaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikberatlaki.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikberatlaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikberatlaki.setText("Laki-Laki");
        ppGrafikberatlaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikberatlaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikberatlaki.setName("ppGrafikberatlaki"); // NOI18N
        ppGrafikberatlaki.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikberatlaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikberatlakiActionPerformed(evt);
            }
        });
        jMenu1.add(ppGrafikberatlaki);

        ppGrafikberatwn.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikberatwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikberatwn.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikberatwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikberatwn.setText("Perempuan");
        ppGrafikberatwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikberatwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikberatwn.setName("ppGrafikberatwn"); // NOI18N
        ppGrafikberatwn.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikberatwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikberatwnActionPerformed(evt);
            }
        });
        jMenu1.add(ppGrafikberatwn);

        Popup.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(250, 255, 245));
        jMenu2.setForeground(new java.awt.Color(50, 50, 50));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu2.setText("Grafik Panjang Bayi");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.setPreferredSize(new java.awt.Dimension(180, 30));

        ppGrafikpanjang.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikpanjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikpanjang.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikpanjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikpanjang.setText("Keseluruhan");
        ppGrafikpanjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikpanjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikpanjang.setName("ppGrafikpanjang"); // NOI18N
        ppGrafikpanjang.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikpanjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikpanjangActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikpanjang);

        ppGrafikpanjanglaki.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikpanjanglaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikpanjanglaki.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikpanjanglaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikpanjanglaki.setText("Laki-Laki");
        ppGrafikpanjanglaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikpanjanglaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikpanjanglaki.setName("ppGrafikpanjanglaki"); // NOI18N
        ppGrafikpanjanglaki.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikpanjanglaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikpanjanglakiActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikpanjanglaki);

        ppGrafikpanjangwn.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikpanjangwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikpanjangwn.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikpanjangwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikpanjangwn.setText("Perempuan");
        ppGrafikpanjangwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikpanjangwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikpanjangwn.setName("ppGrafikpanjangwn"); // NOI18N
        ppGrafikpanjangwn.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikpanjangwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikpanjangwnActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikpanjangwn);

        Popup.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(250, 255, 245));
        jMenu3.setForeground(new java.awt.Color(50, 50, 50));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu3.setText("Grafik Lahir/Tahun");
        jMenu3.setActionCommand("Grafik Kelahiran Tahunan");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N
        jMenu3.setPreferredSize(new java.awt.Dimension(180, 30));

        ppGrafiklahirtahun.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafiklahirtahun.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirtahun.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafiklahirtahun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirtahun.setText("Keseluruhan");
        ppGrafiklahirtahun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirtahun.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirtahun.setName("ppGrafiklahirtahun"); // NOI18N
        ppGrafiklahirtahun.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafiklahirtahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirtahunActionPerformed(evt);
            }
        });
        jMenu3.add(ppGrafiklahirtahun);

        ppGrafiklahirtahunlaki.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafiklahirtahunlaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirtahunlaki.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafiklahirtahunlaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirtahunlaki.setText("Laki-Laki");
        ppGrafiklahirtahunlaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirtahunlaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirtahunlaki.setName("ppGrafiklahirtahunlaki"); // NOI18N
        ppGrafiklahirtahunlaki.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafiklahirtahunlaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirtahunlakiActionPerformed(evt);
            }
        });
        jMenu3.add(ppGrafiklahirtahunlaki);

        ppGrafiklahirtahunwn.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafiklahirtahunwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirtahunwn.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafiklahirtahunwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirtahunwn.setText("Perempuan");
        ppGrafiklahirtahunwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirtahunwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirtahunwn.setName("ppGrafiklahirtahunwn"); // NOI18N
        ppGrafiklahirtahunwn.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafiklahirtahunwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirtahunwnActionPerformed(evt);
            }
        });
        jMenu3.add(ppGrafiklahirtahunwn);

        Popup.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(250, 255, 245));
        jMenu4.setForeground(new java.awt.Color(50, 50, 50));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu4.setText("Grafik Lahir/Bulan");
        jMenu4.setActionCommand("Grafik Kelahiran Tahunan");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu4.setName("jMenu4"); // NOI18N
        jMenu4.setPreferredSize(new java.awt.Dimension(180, 30));

        ppGrafiklahirbulan.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafiklahirbulan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirbulan.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafiklahirbulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirbulan.setText("Keseluruhan");
        ppGrafiklahirbulan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirbulan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirbulan.setName("ppGrafiklahirbulan"); // NOI18N
        ppGrafiklahirbulan.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafiklahirbulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirbulanActionPerformed(evt);
            }
        });
        jMenu4.add(ppGrafiklahirbulan);

        ppGrafiklahirbulanlaki.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafiklahirbulanlaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirbulanlaki.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafiklahirbulanlaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirbulanlaki.setText("Laki-Laki");
        ppGrafiklahirbulanlaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirbulanlaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirbulanlaki.setName("ppGrafiklahirbulanlaki"); // NOI18N
        ppGrafiklahirbulanlaki.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafiklahirbulanlaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirbulanlakiActionPerformed(evt);
            }
        });
        jMenu4.add(ppGrafiklahirbulanlaki);

        ppGrafiklahirbulanwn.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafiklahirbulanwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirbulanwn.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafiklahirbulanwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirbulanwn.setText("Perempuan");
        ppGrafiklahirbulanwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirbulanwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirbulanwn.setName("ppGrafiklahirbulanwn"); // NOI18N
        ppGrafiklahirbulanwn.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafiklahirbulanwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirbulanwnActionPerformed(evt);
            }
        });
        jMenu4.add(ppGrafiklahirbulanwn);

        Popup.add(jMenu4);

        jMenu5.setBackground(new java.awt.Color(250, 255, 245));
        jMenu5.setForeground(new java.awt.Color(50, 50, 50));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu5.setText("Grafik Proses Lahir");
        jMenu5.setActionCommand("Grafik Kelahiran Tahunan");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu5.setName("jMenu5"); // NOI18N
        jMenu5.setPreferredSize(new java.awt.Dimension(180, 30));

        ppGrafikproseslahir.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikproseslahir.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikproseslahir.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikproseslahir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikproseslahir.setText("Keseluruhan");
        ppGrafikproseslahir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikproseslahir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikproseslahir.setName("ppGrafikproseslahir"); // NOI18N
        ppGrafikproseslahir.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikproseslahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikproseslahirActionPerformed(evt);
            }
        });
        jMenu5.add(ppGrafikproseslahir);

        ppGrafikproseslahirlaki.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikproseslahirlaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikproseslahirlaki.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikproseslahirlaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikproseslahirlaki.setText("Laki-Laki");
        ppGrafikproseslahirlaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikproseslahirlaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikproseslahirlaki.setName("ppGrafikproseslahirlaki"); // NOI18N
        ppGrafikproseslahirlaki.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikproseslahirlaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikproseslahirlakiActionPerformed(evt);
            }
        });
        jMenu5.add(ppGrafikproseslahirlaki);

        ppGrafikproseslahirwn.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikproseslahirwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikproseslahirwn.setForeground(new java.awt.Color(50, 50, 50));
        ppGrafikproseslahirwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikproseslahirwn.setText("Perempuan");
        ppGrafikproseslahirwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikproseslahirwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikproseslahirwn.setName("ppGrafikproseslahirwn"); // NOI18N
        ppGrafikproseslahirwn.setPreferredSize(new java.awt.Dimension(180, 30));
        ppGrafikproseslahirwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikproseslahirwnActionPerformed(evt);
            }
        });
        jMenu5.add(ppGrafikproseslahirwn);

        Popup.add(jMenu5);

        MnKartu.setBackground(new java.awt.Color(255, 255, 254));
        MnKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu.setForeground(new java.awt.Color(50, 50, 50));
        MnKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu.setText("Kartu Berobat");
        MnKartu.setName("MnKartu"); // NOI18N
        MnKartu.setPreferredSize(new java.awt.Dimension(250, 25));
        MnKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuActionPerformed(evt);
            }
        });
        Popup.add(MnKartu);

        MnInformasiBayi.setBackground(new java.awt.Color(255, 255, 254));
        MnInformasiBayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInformasiBayi.setForeground(new java.awt.Color(50, 50, 50));
        MnInformasiBayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInformasiBayi.setText("Label Informasi Bayi");
        MnInformasiBayi.setName("MnInformasiBayi"); // NOI18N
        MnInformasiBayi.setPreferredSize(new java.awt.Dimension(250, 28));
        MnInformasiBayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInformasiBayiActionPerformed(evt);
            }
        });
        Popup.add(MnInformasiBayi);

        MnSKL.setBackground(new java.awt.Color(255, 255, 254));
        MnSKL.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKL.setForeground(new java.awt.Color(50, 50, 50));
        MnSKL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKL.setText("Surat Keterangan Lahir 1");
        MnSKL.setName("MnSKL"); // NOI18N
        MnSKL.setPreferredSize(new java.awt.Dimension(250, 28));
        MnSKL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKLActionPerformed(evt);
            }
        });
        Popup.add(MnSKL);

        MnSKL1.setBackground(new java.awt.Color(255, 255, 254));
        MnSKL1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKL1.setForeground(new java.awt.Color(50, 50, 50));
        MnSKL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKL1.setText("Surat Keterangan Lahir 2");
        MnSKL1.setName("MnSKL1"); // NOI18N
        MnSKL1.setPreferredSize(new java.awt.Dimension(250, 28));
        MnSKL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKL1ActionPerformed(evt);
            }
        });
        Popup.add(MnSKL1);

        MnSKL2.setBackground(new java.awt.Color(255, 255, 254));
        MnSKL2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKL2.setForeground(new java.awt.Color(50, 50, 50));
        MnSKL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKL2.setText("Surat Keterangan Lahir 3");
        MnSKL2.setName("MnSKL2"); // NOI18N
        MnSKL2.setPreferredSize(new java.awt.Dimension(250, 28));
        MnSKL2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKL2ActionPerformed(evt);
            }
        });
        Popup.add(MnSKL2);

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        DlgBridgingLahir.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgBridgingLahir.setName("DlgBridgingLahir"); // NOI18N
        DlgBridgingLahir.setUndecorated(true);
        DlgBridgingLahir.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Bridging Dukcapil Kelahiran Bayi Wilayah DKI Jakarta ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setOpaque(true);
        scrollPane3.setPreferredSize(new java.awt.Dimension(1093, 138));

        panelBiasa2.setBackground(new java.awt.Color(255, 255, 255));
        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluar2);
        BtnKeluar2.setBounds(880, 270, 100, 30);

        label17.setText("NIK Ibu :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label17);
        label17.setBounds(0, 10, 90, 23);

        NIKIbu.setName("NIKIbu"); // NOI18N
        NIKIbu.setPreferredSize(new java.awt.Dimension(70, 23));
        NIKIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIKIbuKeyPressed(evt);
            }
        });
        panelBiasa2.add(NIKIbu);
        NIKIbu.setBounds(93, 10, 148, 23);

        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnCari1);
        BtnCari1.setBounds(245, 10, 28, 23);

        NIKAyah.setName("NIKAyah"); // NOI18N
        NIKAyah.setPreferredSize(new java.awt.Dimension(70, 23));
        NIKAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIKAyahKeyPressed(evt);
            }
        });
        panelBiasa2.add(NIKAyah);
        NIKAyah.setBounds(93, 100, 148, 23);

        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('2');
        BtnCari2.setToolTipText("Alt+2");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnCari2);
        BtnCari2.setBounds(245, 100, 28, 23);

        label42.setText("NIK Ayah :");
        label42.setName("label42"); // NOI18N
        label42.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label42);
        label42.setBounds(0, 100, 90, 23);

        label43.setText("Jenis Lahir :");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(65, 23));
        panelBiasa2.add(label43);
        label43.setBounds(0, 250, 90, 23);

        JenisLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Tunggal", "2. Kembar Dua", "3. Kembar Tiga", "4. Kembar Empat", "5. Kembar Banyak/Lainnya (Number)" }));
        JenisLahir.setName("JenisLahir"); // NOI18N
        JenisLahir.setPreferredSize(new java.awt.Dimension(100, 23));
        JenisLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisLahirKeyPressed(evt);
            }
        });
        panelBiasa2.add(JenisLahir);
        JenisLahir.setBounds(93, 250, 180, 23);

        PenolongLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Dokter", "2. Bidan/Perawat", "3. Dukun", "4. Lainnya (Number)" }));
        PenolongLahir.setName("PenolongLahir"); // NOI18N
        PenolongLahir.setPreferredSize(new java.awt.Dimension(100, 23));
        PenolongLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PenolongLahirActionPerformed(evt);
            }
        });
        PenolongLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenolongLahirKeyPressed(evt);
            }
        });
        panelBiasa2.add(PenolongLahir);
        PenolongLahir.setBounds(93, 220, 180, 23);

        label44.setText("Penolong Lahir :");
        label44.setName("label44"); // NOI18N
        label44.setPreferredSize(new java.awt.Dimension(65, 23));
        panelBiasa2.add(label44);
        label44.setBounds(0, 220, 90, 23);

        label45.setText("Cara Lahir :");
        label45.setName("label45"); // NOI18N
        label45.setPreferredSize(new java.awt.Dimension(65, 23));
        panelBiasa2.add(label45);
        label45.setBounds(0, 280, 90, 23);

        CaraLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Normal", "2. Caesar (Number)" }));
        CaraLahir.setName("CaraLahir"); // NOI18N
        CaraLahir.setPreferredSize(new java.awt.Dimension(100, 23));
        CaraLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraLahirKeyPressed(evt);
            }
        });
        panelBiasa2.add(CaraLahir);
        CaraLahir.setBounds(93, 280, 180, 23);

        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnSimpan1);
        BtnSimpan1.setBounds(750, 270, 100, 30);

        label46.setText("Telp. Ortu :");
        label46.setName("label46"); // NOI18N
        label46.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label46);
        label46.setBounds(0, 190, 90, 23);

        TelpOrtu.setName("TelpOrtu"); // NOI18N
        TelpOrtu.setPreferredSize(new java.awt.Dimension(70, 23));
        TelpOrtu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TelpOrtuKeyPressed(evt);
            }
        });
        panelBiasa2.add(TelpOrtu);
        TelpOrtu.setBounds(93, 190, 148, 23);

        label47.setText("BPJS Ayah :");
        label47.setName("label47"); // NOI18N
        label47.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label47);
        label47.setBounds(0, 130, 90, 23);

        BPJSAyah.setName("BPJSAyah"); // NOI18N
        BPJSAyah.setPreferredSize(new java.awt.Dimension(70, 23));
        BPJSAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BPJSAyahKeyPressed(evt);
            }
        });
        panelBiasa2.add(BPJSAyah);
        BPJSAyah.setBounds(93, 130, 148, 23);

        label48.setText("BPJS Ibu :");
        label48.setName("label48"); // NOI18N
        label48.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label48);
        label48.setBounds(0, 40, 90, 23);

        BPJSIbu.setName("BPJSIbu"); // NOI18N
        BPJSIbu.setPreferredSize(new java.awt.Dimension(70, 23));
        BPJSIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BPJSIbuKeyPressed(evt);
            }
        });
        panelBiasa2.add(BPJSIbu);
        BPJSIbu.setBounds(93, 40, 148, 23);

        label49.setText("BPJS Bayi :");
        label49.setName("label49"); // NOI18N
        label49.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label49);
        label49.setBounds(0, 70, 90, 23);

        BPJSBayi.setName("BPJSBayi"); // NOI18N
        BPJSBayi.setPreferredSize(new java.awt.Dimension(70, 23));
        BPJSBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BPJSBayiKeyPressed(evt);
            }
        });
        panelBiasa2.add(BPJSBayi);
        BPJSBayi.setBounds(93, 70, 148, 23);

        label50.setText("NIK Pelapor :");
        label50.setName("label50"); // NOI18N
        label50.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label50);
        label50.setBounds(290, 10, 110, 23);

        NIKPelapor.setName("NIKPelapor"); // NOI18N
        NIKPelapor.setPreferredSize(new java.awt.Dimension(70, 23));
        NIKPelapor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIKPelaporKeyPressed(evt);
            }
        });
        panelBiasa2.add(NIKPelapor);
        NIKPelapor.setBounds(403, 10, 148, 23);

        BtnCari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari3.setMnemonic('2');
        BtnCari3.setToolTipText("Alt+2");
        BtnCari3.setName("BtnCari3"); // NOI18N
        BtnCari3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari3ActionPerformed(evt);
            }
        });
        BtnCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari3KeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnCari3);
        BtnCari3.setBounds(554, 10, 28, 23);

        label51.setText("Nama Pelapor :");
        label51.setName("label51"); // NOI18N
        label51.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label51);
        label51.setBounds(290, 40, 110, 23);

        NamaPelapor.setName("NamaPelapor"); // NOI18N
        NamaPelapor.setPreferredSize(new java.awt.Dimension(70, 23));
        NamaPelapor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaPelaporKeyPressed(evt);
            }
        });
        panelBiasa2.add(NamaPelapor);
        NamaPelapor.setBounds(403, 40, 230, 23);

        label52.setText("Pekerjaan Pelapor :");
        label52.setName("label52"); // NOI18N
        label52.setPreferredSize(new java.awt.Dimension(65, 23));
        panelBiasa2.add(label52);
        label52.setBounds(290, 100, 110, 23);

        PekerjaanPelapor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum/Tidak Bekerja", "Mengurus Rumah Tangga", "Pelajar/Mahasiswa", "Pensiunan", "Pegawai Negeri Sipil (PNS)", "Tentara Nasional Indonesia (TNI)", "Kepolisian RI (POLRI)", "Perdagangan", "Petani/Pekebun", "Peternak", "Nelayan/Perikanan", "Industri", "Konstruksi", "Transportasi", "Karyawan Swasta", "Karyawan BUMN", "Karyawan BUMD", "Karyawan Honorer", "Buruh Harian Lepas", "Buruh Tani/Perkebunan", "Buruh Nelayan/Perikanan", "Buruh Peternakan", "Pembantu Rumah Tangga", "Tukang Cukur", "Tukang Listrik", "Tukang Batu", "Tukang Kayu", "Tukang Sol Sepatu", "Tukang Las/Pandai Besi", "Tukang Jahit", "Tukang Gigi", "Penata Rias", "Penata Busana", "Penata Rambut", "Mekanik", "Seniman", "Tabib", "Paraji", "Perancang Busana", "Penterjemah", "Imam Masjid", "Pendeta", "Pastor", "Wartawan", "Ustadz/Mubaligh", "Juru Masak", "Promotor Acara", "Anggota DPR RI", "Anggota DPD", "Anggota BPK", "Presiden", "Wakil Presiden", "Anggota Mahkamah Agung/Konstitusi", "Anggota Kabinet Kementrian", "Duta Besar", "Gubernur", "Wakil Gubernur", "Bupati", "Wakil Bupati", "Walikota", "Wakil Walikota", "Anggota DPRD Prop.", "Anggota DPRD Kab.", "Dosen", "Guru", "Pilot", "Pengacara", "Notaris", "Arsitek", "Akuntan", "Konsultan", "Dokter", "Bidan", "Perawat", "Apoteker", "Psikiater/Psikolog", "Penyiar Televisi", "Penyiar Radio", "Pelaut", "Peneliti", "Sopir", "Pialang", "Paranormal", "Pedagang", "Perangkat Desa", "Kepala Desa", "Biarawati", "Wiraswasta", "Pekerjaan Lainnya", "Hakim", "Hakim Agung" }));
        PekerjaanPelapor.setName("PekerjaanPelapor"); // NOI18N
        PekerjaanPelapor.setPreferredSize(new java.awt.Dimension(100, 23));
        PekerjaanPelapor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PekerjaanPelaporKeyPressed(evt);
            }
        });
        panelBiasa2.add(PekerjaanPelapor);
        PekerjaanPelapor.setBounds(403, 100, 230, 23);

        label53.setText("Alamat Pelapor :");
        label53.setName("label53"); // NOI18N
        label53.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label53);
        label53.setBounds(290, 70, 110, 23);

        AlamatPelapor.setName("AlamatPelapor"); // NOI18N
        AlamatPelapor.setPreferredSize(new java.awt.Dimension(70, 23));
        AlamatPelapor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatPelaporKeyPressed(evt);
            }
        });
        panelBiasa2.add(AlamatPelapor);
        AlamatPelapor.setBounds(403, 70, 230, 23);

        label54.setText("Umur Pelapor :");
        label54.setName("label54"); // NOI18N
        label54.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label54);
        label54.setBounds(290, 130, 110, 23);

        UmurPelapor.setName("UmurPelapor"); // NOI18N
        UmurPelapor.setPreferredSize(new java.awt.Dimension(70, 23));
        UmurPelapor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurPelaporKeyPressed(evt);
            }
        });
        panelBiasa2.add(UmurPelapor);
        UmurPelapor.setBounds(403, 130, 70, 23);

        label55.setText("NIK Saksi 1 :");
        label55.setName("label55"); // NOI18N
        label55.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label55);
        label55.setBounds(290, 160, 110, 23);

        NIKSaksi1.setName("NIKSaksi1"); // NOI18N
        NIKSaksi1.setPreferredSize(new java.awt.Dimension(70, 23));
        NIKSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIKSaksi1KeyPressed(evt);
            }
        });
        panelBiasa2.add(NIKSaksi1);
        NIKSaksi1.setBounds(403, 160, 148, 23);

        BtnCari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari4.setMnemonic('2');
        BtnCari4.setToolTipText("Alt+2");
        BtnCari4.setName("BtnCari4"); // NOI18N
        BtnCari4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari4ActionPerformed(evt);
            }
        });
        BtnCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari4KeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnCari4);
        BtnCari4.setBounds(554, 160, 28, 23);

        label56.setText("Nama Saksi 1 :");
        label56.setName("label56"); // NOI18N
        label56.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label56);
        label56.setBounds(290, 190, 110, 23);

        NamaSaksi1.setName("NamaSaksi1"); // NOI18N
        NamaSaksi1.setPreferredSize(new java.awt.Dimension(70, 23));
        NamaSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaSaksi1KeyPressed(evt);
            }
        });
        panelBiasa2.add(NamaSaksi1);
        NamaSaksi1.setBounds(403, 190, 230, 23);

        AlamatSaksi1.setName("AlamatSaksi1"); // NOI18N
        AlamatSaksi1.setPreferredSize(new java.awt.Dimension(70, 23));
        AlamatSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatSaksi1KeyPressed(evt);
            }
        });
        panelBiasa2.add(AlamatSaksi1);
        AlamatSaksi1.setBounds(403, 220, 230, 23);

        label57.setText("Alamat Saksi 1 :");
        label57.setName("label57"); // NOI18N
        label57.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label57);
        label57.setBounds(290, 220, 110, 23);

        label58.setText("Pekerjaan Saksi 1 :");
        label58.setName("label58"); // NOI18N
        label58.setPreferredSize(new java.awt.Dimension(65, 23));
        panelBiasa2.add(label58);
        label58.setBounds(290, 250, 110, 23);

        PekerjaanSaksi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum/Tidak Bekerja", "Mengurus Rumah Tangga", "Pelajar/Mahasiswa", "Pensiunan", "Pegawai Negeri Sipil (PNS)", "Tentara Nasional Indonesia (TNI)", "Kepolisian RI (POLRI)", "Perdagangan", "Petani/Pekebun", "Peternak", "Nelayan/Perikanan", "Industri", "Konstruksi", "Transportasi", "Karyawan Swasta", "Karyawan BUMN", "Karyawan BUMD", "Karyawan Honorer", "Buruh Harian Lepas", "Buruh Tani/Perkebunan", "Buruh Nelayan/Perikanan", "Buruh Peternakan", "Pembantu Rumah Tangga", "Tukang Cukur", "Tukang Listrik", "Tukang Batu", "Tukang Kayu", "Tukang Sol Sepatu", "Tukang Las/Pandai Besi", "Tukang Jahit", "Tukang Gigi", "Penata Rias", "Penata Busana", "Penata Rambut", "Mekanik", "Seniman", "Tabib", "Paraji", "Perancang Busana", "Penterjemah", "Imam Masjid", "Pendeta", "Pastor", "Wartawan", "Ustadz/Mubaligh", "Juru Masak", "Promotor Acara", "Anggota DPR RI", "Anggota DPD", "Anggota BPK", "Presiden", "Wakil Presiden", "Anggota Mahkamah Agung/Konstitusi", "Anggota Kabinet Kementrian", "Duta Besar", "Gubernur", "Wakil Gubernur", "Bupati", "Wakil Bupati", "Walikota", "Wakil Walikota", "Anggota DPRD Prop.", "Anggota DPRD Kab.", "Dosen", "Guru", "Pilot", "Pengacara", "Notaris", "Arsitek", "Akuntan", "Konsultan", "Dokter", "Bidan", "Perawat", "Apoteker", "Psikiater/Psikolog", "Penyiar Televisi", "Penyiar Radio", "Pelaut", "Peneliti", "Sopir", "Pialang", "Paranormal", "Pedagang", "Perangkat Desa", "Kepala Desa", "Biarawati", "Wiraswasta", "Pekerjaan Lainnya", "Hakim", "Hakim Agung" }));
        PekerjaanSaksi1.setName("PekerjaanSaksi1"); // NOI18N
        PekerjaanSaksi1.setPreferredSize(new java.awt.Dimension(100, 23));
        PekerjaanSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PekerjaanSaksi1KeyPressed(evt);
            }
        });
        panelBiasa2.add(PekerjaanSaksi1);
        PekerjaanSaksi1.setBounds(403, 250, 230, 23);

        UmurSaksi1.setName("UmurSaksi1"); // NOI18N
        UmurSaksi1.setPreferredSize(new java.awt.Dimension(70, 23));
        UmurSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurSaksi1KeyPressed(evt);
            }
        });
        panelBiasa2.add(UmurSaksi1);
        UmurSaksi1.setBounds(403, 280, 70, 23);

        label59.setText("Umur Saksi 1 :");
        label59.setName("label59"); // NOI18N
        label59.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label59);
        label59.setBounds(290, 280, 110, 23);

        label60.setText("Umur Ayah :");
        label60.setName("label60"); // NOI18N
        label60.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label60);
        label60.setBounds(0, 160, 90, 23);

        UmurAyah2.setName("UmurAyah2"); // NOI18N
        UmurAyah2.setPreferredSize(new java.awt.Dimension(70, 23));
        UmurAyah2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurAyah2KeyPressed(evt);
            }
        });
        panelBiasa2.add(UmurAyah2);
        UmurAyah2.setBounds(93, 160, 70, 23);

        label61.setText("NIK Saksi 2 :");
        label61.setName("label61"); // NOI18N
        label61.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label61);
        label61.setBounds(647, 10, 110, 23);

        NIKSaksi2.setName("NIKSaksi2"); // NOI18N
        NIKSaksi2.setPreferredSize(new java.awt.Dimension(70, 23));
        NIKSaksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIKSaksi2KeyPressed(evt);
            }
        });
        panelBiasa2.add(NIKSaksi2);
        NIKSaksi2.setBounds(760, 10, 148, 23);

        BtnCari5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari5.setMnemonic('2');
        BtnCari5.setToolTipText("Alt+2");
        BtnCari5.setName("BtnCari5"); // NOI18N
        BtnCari5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari5ActionPerformed(evt);
            }
        });
        BtnCari5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari5KeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnCari5);
        BtnCari5.setBounds(910, 10, 28, 23);

        label62.setText("Nama Saksi 2 :");
        label62.setName("label62"); // NOI18N
        label62.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label62);
        label62.setBounds(647, 40, 110, 23);

        NamaSaksi2.setName("NamaSaksi2"); // NOI18N
        NamaSaksi2.setPreferredSize(new java.awt.Dimension(70, 23));
        NamaSaksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaSaksi2KeyPressed(evt);
            }
        });
        panelBiasa2.add(NamaSaksi2);
        NamaSaksi2.setBounds(760, 40, 230, 23);

        label63.setText("Alamat Saksi 2 :");
        label63.setName("label63"); // NOI18N
        label63.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label63);
        label63.setBounds(647, 70, 110, 23);

        AlamatSaksi2.setName("AlamatSaksi2"); // NOI18N
        AlamatSaksi2.setPreferredSize(new java.awt.Dimension(70, 23));
        AlamatSaksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatSaksi2KeyPressed(evt);
            }
        });
        panelBiasa2.add(AlamatSaksi2);
        AlamatSaksi2.setBounds(760, 70, 230, 23);

        label64.setText("Pekerjaan Saksi 2 :");
        label64.setName("label64"); // NOI18N
        label64.setPreferredSize(new java.awt.Dimension(65, 23));
        panelBiasa2.add(label64);
        label64.setBounds(647, 100, 110, 23);

        PekerjaanSaksi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum/Tidak Bekerja", "Mengurus Rumah Tangga", "Pelajar/Mahasiswa", "Pensiunan", "Pegawai Negeri Sipil (PNS)", "Tentara Nasional Indonesia (TNI)", "Kepolisian RI (POLRI)", "Perdagangan", "Petani/Pekebun", "Peternak", "Nelayan/Perikanan", "Industri", "Konstruksi", "Transportasi", "Karyawan Swasta", "Karyawan BUMN", "Karyawan BUMD", "Karyawan Honorer", "Buruh Harian Lepas", "Buruh Tani/Perkebunan", "Buruh Nelayan/Perikanan", "Buruh Peternakan", "Pembantu Rumah Tangga", "Tukang Cukur", "Tukang Listrik", "Tukang Batu", "Tukang Kayu", "Tukang Sol Sepatu", "Tukang Las/Pandai Besi", "Tukang Jahit", "Tukang Gigi", "Penata Rias", "Penata Busana", "Penata Rambut", "Mekanik", "Seniman", "Tabib", "Paraji", "Perancang Busana", "Penterjemah", "Imam Masjid", "Pendeta", "Pastor", "Wartawan", "Ustadz/Mubaligh", "Juru Masak", "Promotor Acara", "Anggota DPR RI", "Anggota DPD", "Anggota BPK", "Presiden", "Wakil Presiden", "Anggota Mahkamah Agung/Konstitusi", "Anggota Kabinet Kementrian", "Duta Besar", "Gubernur", "Wakil Gubernur", "Bupati", "Wakil Bupati", "Walikota", "Wakil Walikota", "Anggota DPRD Prop.", "Anggota DPRD Kab.", "Dosen", "Guru", "Pilot", "Pengacara", "Notaris", "Arsitek", "Akuntan", "Konsultan", "Dokter", "Bidan", "Perawat", "Apoteker", "Psikiater/Psikolog", "Penyiar Televisi", "Penyiar Radio", "Pelaut", "Peneliti", "Sopir", "Pialang", "Paranormal", "Pedagang", "Perangkat Desa", "Kepala Desa", "Biarawati", "Wiraswasta", "Pekerjaan Lainnya", "Hakim", "Hakim Agung" }));
        PekerjaanSaksi2.setName("PekerjaanSaksi2"); // NOI18N
        PekerjaanSaksi2.setPreferredSize(new java.awt.Dimension(100, 23));
        PekerjaanSaksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PekerjaanSaksi2KeyPressed(evt);
            }
        });
        panelBiasa2.add(PekerjaanSaksi2);
        PekerjaanSaksi2.setBounds(760, 100, 230, 23);

        label65.setText("Umur Saksi 2 :");
        label65.setName("label65"); // NOI18N
        label65.setPreferredSize(new java.awt.Dimension(70, 23));
        panelBiasa2.add(label65);
        label65.setBounds(647, 130, 110, 23);

        UmurSaksi2.setName("UmurSaksi2"); // NOI18N
        UmurSaksi2.setPreferredSize(new java.awt.Dimension(70, 23));
        UmurSaksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurSaksi2KeyPressed(evt);
            }
        });
        panelBiasa2.add(UmurSaksi2);
        UmurSaksi2.setBounds(760, 130, 70, 23);

        scrollPane3.setViewportView(panelBiasa2);

        internalFrame3.add(scrollPane3, java.awt.BorderLayout.CENTER);

        DlgBridgingLahir.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Kelahiran Bayi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi2.setBackground(new java.awt.Color(255, 150, 255));
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label29.setText("J.K. :");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi2.add(label29);

        cmbCrJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEMUA", "LAKI-LAKI", "PEREMPUAN" }));
        cmbCrJk.setName("cmbCrJk"); // NOI18N
        cmbCrJk.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbCrJk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCrJkKeyPressed(evt);
            }
        });
        panelisi2.add(cmbCrJk);

        ckTglCari.setSelected(true);
        ckTglCari.setText("Tgl.Lahir :");
        ckTglCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ckTglCari.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ckTglCari.setName("ckTglCari"); // NOI18N
        ckTglCari.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi2.add(ckTglCari);

        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelisi2.add(DTPCari1);

        label33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label33.setText("S.D.");
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(27, 23));
        panelisi2.add(label33);

        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelisi2.add(DTPCari2);

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi2.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(155, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi2.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
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
        panelisi2.add(BtnCari);

        jLabel7.setText("Halaman :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi2.add(jLabel7);

        cmbHlm.setName("cmbHlm"); // NOI18N
        cmbHlm.setPreferredSize(new java.awt.Dimension(75, 23));
        panelisi2.add(cmbHlm);

        jPanel2.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelisi1.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelisi1.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed1(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelisi1.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed1(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelisi1.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+PT");
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
        panelisi1.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelisi1.add(BtnAll);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelisi1.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
        panelisi1.add(LCount);

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
        panelisi1.add(BtnKeluar);

        jPanel2.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(880, 490));
        FormInput.setLayout(null);

        label12.setText("No.RM Bayi :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label12);
        label12.setBounds(0, 12, 85, 23);

        NoRm.setName("NoRm"); // NOI18N
        NoRm.setPreferredSize(new java.awt.Dimension(207, 23));
        NoRm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRmKeyPressed(evt);
            }
        });
        FormInput.add(NoRm);
        NoRm.setBounds(89, 12, 100, 23);

        label18.setText("Ibu Bayi :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label18);
        label18.setBounds(0, 42, 85, 23);

        label22.setText("J.K.Bayi :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label22);
        label22.setBounds(0, 132, 85, 23);

        label24.setText("P.B. :");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label24);
        label24.setBounds(208, 132, 50, 23);

        Proses.setName("Proses"); // NOI18N
        Proses.setPreferredSize(new java.awt.Dimension(207, 23));
        Proses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsesKeyPressed(evt);
            }
        });
        FormInput.add(Proses);
        Proses.setBounds(130, 422, 335, 23);

        Anakke.setName("Anakke"); // NOI18N
        Anakke.setPreferredSize(new java.awt.Dimension(207, 23));
        Anakke.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnakkeKeyPressed(evt);
            }
        });
        FormInput.add(Anakke);
        Anakke.setBounds(130, 452, 50, 23);

        label25.setText("Kelahiran Ke :");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label25);
        label25.setBounds(0, 450, 126, 23);

        LingkarKepala.setName("LingkarKepala"); // NOI18N
        LingkarKepala.setPreferredSize(new java.awt.Dimension(207, 23));
        LingkarKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarKepalaKeyPressed(evt);
            }
        });
        FormInput.add(LingkarKepala);
        LingkarKepala.setBounds(252, 162, 50, 23);

        label27.setText("Keterangan :");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label27);
        label27.setBounds(0, 302, 126, 23);

        label28.setText("Daftar :");
        label28.setName("label28"); // NOI18N
        label28.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label28);
        label28.setBounds(321, 222, 50, 23);

        JKel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        JKel.setSelectedIndex(1);
        JKel.setName("JKel"); // NOI18N
        JKel.setPreferredSize(new java.awt.Dimension(100, 23));
        JKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JKelKeyPressed(evt);
            }
        });
        FormInput.add(JKel);
        JKel.setBounds(89, 132, 110, 23);

        label23.setText("B.B.(gram) :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label23);
        label23.setBounds(321, 132, 90, 23);

        label30.setText("Tgl. Lahir :");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label30);
        label30.setBounds(0, 192, 85, 23);

        Lahir.setDisplayFormat("dd-MM-yyyy");
        Lahir.setName("Lahir"); // NOI18N
        Lahir.setVerifyInputWhenFocusTarget(false);
        Lahir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                LahirItemStateChanged(evt);
            }
        });
        Lahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LahirKeyPressed(evt);
            }
        });
        FormInput.add(Lahir);
        Lahir.setBounds(89, 192, 90, 23);

        label31.setText("Diagnosa :");
        label31.setName("label31"); // NOI18N
        label31.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label31);
        label31.setBounds(0, 362, 126, 23);

        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.setPreferredSize(new java.awt.Dimension(207, 23));
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(130, 362, 135, 23);

        Nmibu.setName("Nmibu"); // NOI18N
        Nmibu.setPreferredSize(new java.awt.Dimension(207, 23));
        Nmibu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmibuKeyPressed(evt);
            }
        });
        FormInput.add(Nmibu);
        Nmibu.setBounds(89, 42, 230, 23);

        label26.setText("Lingkar Kepala :");
        label26.setName("label26"); // NOI18N
        label26.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label26);
        label26.setBounds(163, 162, 85, 23);

        label19.setText("Nama Ayah :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label19);
        label19.setBounds(0, 72, 85, 23);

        NmAyah.setName("NmAyah"); // NOI18N
        NmAyah.setPreferredSize(new java.awt.Dimension(207, 23));
        NmAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmAyahKeyPressed(evt);
            }
        });
        FormInput.add(NmAyah);
        NmAyah.setBounds(89, 72, 230, 23);

        label20.setText("Umur Ibu :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label20);
        label20.setBounds(321, 42, 70, 23);

        UmurIbu.setName("UmurIbu"); // NOI18N
        UmurIbu.setPreferredSize(new java.awt.Dimension(207, 23));
        UmurIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurIbuKeyPressed(evt);
            }
        });
        FormInput.add(UmurIbu);
        UmurIbu.setBounds(395, 42, 70, 23);

        label21.setText("Alamat Ibu :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label21);
        label21.setBounds(0, 102, 85, 23);

        AlamatIbu.setName("AlamatIbu"); // NOI18N
        AlamatIbu.setPreferredSize(new java.awt.Dimension(207, 23));
        AlamatIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatIbuKeyPressed(evt);
            }
        });
        FormInput.add(AlamatIbu);
        AlamatIbu.setBounds(89, 102, 376, 23);

        label32.setText("Jam Lahir :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label32);
        label32.setBounds(189, 192, 80, 23);

        jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jam.setName("jam"); // NOI18N
        jam.setPreferredSize(new java.awt.Dimension(100, 23));
        jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamKeyPressed(evt);
            }
        });
        FormInput.add(jam);
        jam.setBounds(273, 192, 62, 23);

        menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        menit.setName("menit"); // NOI18N
        menit.setPreferredSize(new java.awt.Dimension(100, 23));
        menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menitKeyPressed(evt);
            }
        });
        FormInput.add(menit);
        menit.setBounds(338, 192, 62, 23);

        detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        detik.setName("detik"); // NOI18N
        detik.setPreferredSize(new java.awt.Dimension(100, 23));
        detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                detikKeyPressed(evt);
            }
        });
        FormInput.add(detik);
        detik.setBounds(403, 192, 62, 23);

        label34.setText("Proses Kelahiran :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label34);
        label34.setBounds(0, 422, 126, 23);

        Berat.setName("Berat"); // NOI18N
        Berat.setPreferredSize(new java.awt.Dimension(207, 23));
        Berat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BeratKeyPressed(evt);
            }
        });
        FormInput.add(Berat);
        Berat.setBounds(415, 132, 50, 23);

        Panjang.setName("Panjang"); // NOI18N
        Panjang.setPreferredSize(new java.awt.Dimension(207, 23));
        Panjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanjangKeyPressed(evt);
            }
        });
        FormInput.add(Panjang);
        Panjang.setBounds(262, 132, 50, 23);

        Daftar.setDisplayFormat("dd-MM-yyyy");
        Daftar.setName("Daftar"); // NOI18N
        Daftar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DaftarKeyPressed(evt);
            }
        });
        FormInput.add(Daftar);
        Daftar.setBounds(375, 222, 90, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Keterangan.setColumns(20);
        Keterangan.setRows(5);
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Keterangan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(130, 302, 335, 52);

        NmBayi.setName("NmBayi"); // NOI18N
        NmBayi.setPreferredSize(new java.awt.Dimension(207, 23));
        NmBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmBayiKeyPressed(evt);
            }
        });
        FormInput.add(NmBayi);
        NmBayi.setBounds(191, 12, 244, 23);

        label35.setText("Umur Ayah :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label35);
        label35.setBounds(321, 72, 70, 23);

        UmurAyah.setName("UmurAyah"); // NOI18N
        UmurAyah.setPreferredSize(new java.awt.Dimension(207, 23));
        UmurAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurAyahKeyPressed(evt);
            }
        });
        FormInput.add(UmurAyah);
        UmurAyah.setBounds(395, 72, 70, 23);

        label36.setText("Umur Bayi :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label36);
        label36.setBounds(0, 222, 85, 23);

        UmurBayi.setName("UmurBayi"); // NOI18N
        UmurBayi.setPreferredSize(new java.awt.Dimension(207, 23));
        UmurBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurBayiKeyPressed(evt);
            }
        });
        FormInput.add(UmurBayi);
        UmurBayi.setBounds(89, 222, 45, 23);

        label37.setText("Penyulit Kehamilan :");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label37);
        label37.setBounds(0, 392, 126, 23);

        PenyulitKehamilan.setName("PenyulitKehamilan"); // NOI18N
        PenyulitKehamilan.setPreferredSize(new java.awt.Dimension(207, 23));
        PenyulitKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyulitKehamilanKeyPressed(evt);
            }
        });
        FormInput.add(PenyulitKehamilan);
        PenyulitKehamilan.setBounds(130, 392, 335, 23);

        label38.setText("Ketuban :");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label38);
        label38.setBounds(279, 362, 57, 23);

        Ketuban.setName("Ketuban"); // NOI18N
        Ketuban.setPreferredSize(new java.awt.Dimension(207, 23));
        Ketuban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetubanKeyPressed(evt);
            }
        });
        FormInput.add(Ketuban);
        Ketuban.setBounds(340, 362, 125, 23);

        label39.setText("Lingkar Dada :");
        label39.setName("label39"); // NOI18N
        label39.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label39);
        label39.setBounds(0, 162, 85, 23);

        LingkarDada.setName("LingkarDada"); // NOI18N
        LingkarDada.setPreferredSize(new java.awt.Dimension(207, 23));
        LingkarDada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarDadaKeyPressed(evt);
            }
        });
        FormInput.add(LingkarDada);
        LingkarDada.setBounds(89, 162, 50, 23);

        LingkarPerut.setName("LingkarPerut"); // NOI18N
        LingkarPerut.setPreferredSize(new java.awt.Dimension(207, 23));
        LingkarPerut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarPerutKeyPressed(evt);
            }
        });
        FormInput.add(LingkarPerut);
        LingkarPerut.setBounds(415, 162, 50, 23);

        label40.setText("Lingkar Perut :");
        label40.setName("label40"); // NOI18N
        label40.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label40);
        label40.setBounds(321, 162, 90, 23);

        label41.setText("No.SKL :");
        label41.setName("label41"); // NOI18N
        label41.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label41);
        label41.setBounds(135, 222, 50, 23);

        NoSKL.setName("NoSKL"); // NOI18N
        NoSKL.setPreferredSize(new java.awt.Dimension(207, 23));
        NoSKL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSKLKeyPressed(evt);
            }
        });
        FormInput.add(NoSKL);
        NoSKL.setBounds(189, 222, 130, 23);

        jLabel24.setText("Penolong :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 272, 126, 23);

        KdPenolong.setEditable(false);
        KdPenolong.setHighlighter(null);
        KdPenolong.setName("KdPenolong"); // NOI18N
        FormInput.add(KdPenolong);
        KdPenolong.setBounds(130, 272, 100, 23);

        NmPenolong.setEditable(false);
        NmPenolong.setName("NmPenolong"); // NOI18N
        FormInput.add(NmPenolong);
        NmPenolong.setBounds(232, 272, 201, 23);

        BtnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenjab.setMnemonic('1');
        BtnPenjab.setToolTipText("ALt+1");
        BtnPenjab.setName("BtnPenjab"); // NOI18N
        BtnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenjabActionPerformed(evt);
            }
        });
        BtnPenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPenjabKeyPressed(evt);
            }
        });
        FormInput.add(BtnPenjab);
        BtnPenjab.setBounds(437, 272, 28, 23);

        BtnKelurahan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/011.png"))); // NOI18N
        BtnKelurahan1.setMnemonic('2');
        BtnKelurahan1.setToolTipText("ALt+2");
        BtnKelurahan1.setName("BtnKelurahan1"); // NOI18N
        BtnKelurahan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahan1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahan1);
        BtnKelurahan1.setBounds(437, 12, 28, 23);

        label66.setText("Riwayat Persalinan :");
        label66.setName("label66"); // NOI18N
        label66.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label66);
        label66.setBounds(0, 252, 117, 23);

        jLabel101.setText("G :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(201, 452, 40, 23);

        G.setFocusTraversalPolicyProvider(true);
        G.setName("G"); // NOI18N
        G.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GKeyPressed(evt);
            }
        });
        FormInput.add(G);
        G.setBounds(245, 452, 50, 23);

        jLabel102.setText("P :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(296, 452, 30, 23);

        P.setFocusTraversalPolicyProvider(true);
        P.setName("P"); // NOI18N
        P.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PKeyPressed(evt);
            }
        });
        FormInput.add(P);
        P.setBounds(330, 452, 50, 23);

        jLabel103.setText("A :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(381, 452, 30, 23);

        A.setFocusTraversalPolicyProvider(true);
        A.setName("A"); // NOI18N
        A.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AKeyPressed(evt);
            }
        });
        FormInput.add(A);
        A.setBounds(415, 452, 50, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 150)));
        Scroll2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scroll2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        Scroll2.setName("Scroll2"); // NOI18N

        tbAPGAR.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbAPGAR.setAutoscrolls(false);
        tbAPGAR.setGridColor(new java.awt.Color(150, 150, 150));
        tbAPGAR.setName("tbAPGAR"); // NOI18N
        tbAPGAR.setRowHeight(150);
        tbAPGAR.getTableHeader().setResizingAllowed(false);
        tbAPGAR.getTableHeader().setReorderingAllowed(false);
        tbAPGAR.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbAPGARPropertyChange(evt);
            }
        });
        Scroll2.setViewportView(tbAPGAR);

        FormInput.add(Scroll2);
        Scroll2.setBounds(486, 32, 389, 187);

        label13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label13.setText("Nilai APGAR :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label13);
        label13.setBounds(486, 12, 120, 23);

        label67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label67.setText("Resusitas :");
        label67.setName("label67"); // NOI18N
        label67.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label67);
        label67.setBounds(486, 252, 65, 23);

        Resusitas.setName("Resusitas"); // NOI18N
        Resusitas.setPreferredSize(new java.awt.Dimension(207, 23));
        Resusitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ResusitasKeyPressed(evt);
            }
        });
        FormInput.add(Resusitas);
        Resusitas.setBounds(486, 272, 389, 23);

        label68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label68.setText("Obat Yang Diberikan :");
        label68.setName("label68"); // NOI18N
        label68.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label68);
        label68.setBounds(486, 302, 280, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        ObatDiberikan.setColumns(20);
        ObatDiberikan.setRows(5);
        ObatDiberikan.setName("ObatDiberikan"); // NOI18N
        ObatDiberikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatDiberikanKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(ObatDiberikan);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(486, 322, 389, 52);

        label69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label69.setText("Mikasi :");
        label69.setName("label69"); // NOI18N
        label69.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label69);
        label69.setBounds(486, 382, 280, 23);

        Mikasi.setName("Mikasi"); // NOI18N
        Mikasi.setPreferredSize(new java.awt.Dimension(207, 23));
        Mikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MikasiKeyPressed(evt);
            }
        });
        FormInput.add(Mikasi);
        Mikasi.setBounds(486, 402, 389, 23);

        label70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label70.setText("Mikonium :");
        label70.setName("label70"); // NOI18N
        label70.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label70);
        label70.setBounds(486, 432, 280, 23);

        Mikonium.setName("Mikonium"); // NOI18N
        Mikonium.setPreferredSize(new java.awt.Dimension(207, 23));
        Mikonium.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MikoniumKeyPressed(evt);
            }
        });
        FormInput.add(Mikonium);
        Mikonium.setBounds(486, 452, 389, 23);

        label71.setText("Jumlah Nilai :");
        label71.setName("label71"); // NOI18N
        label71.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label71);
        label71.setBounds(681, 218, 100, 27);

        N10.setEditable(false);
        N10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 150)));
        N10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        N10.setName("N10"); // NOI18N
        FormInput.add(N10);
        N10.setBounds(841, 218, 34, 27);

        N5.setEditable(false);
        N5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 150)));
        N5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        N5.setName("N5"); // NOI18N
        FormInput.add(N5);
        N5.setBounds(813, 218, 29, 27);

        N1.setEditable(false);
        N1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 150)));
        N1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        N1.setName("N1"); // NOI18N
        FormInput.add(N1);
        N1.setBounds(785, 218, 29, 27);

        Scroll1.setViewportView(FormInput);

        TabRawat.addTab("Input Data Kelahiran Bayi", Scroll1);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        scrollPane1.setComponentPopupMenu(Popup);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbDokter.setAutoCreateRowSorter(true);
        tbDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDokter.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDokter.setComponentPopupMenu(Popup);
        tbDokter.setName("tbDokter"); // NOI18N
        tbDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDokterMouseClicked(evt);
            }
        });
        tbDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDokterKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbDokter);

        TabRawat.addTab("Data Kelahiran Bayi", scrollPane1);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void tbDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDokterMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbDokter.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbDokterMouseClicked

    private void tbDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDokterKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbDokterKeyPressed

    private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKeyPressed
        //Valid.pindah(evt,BtnClose,no_rm_ibu);
}//GEN-LAST:event_KdKeyPressed

    private void ProsesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsesKeyPressed
         Valid.pindah(evt,PenyulitKehamilan,Anakke);
    }//GEN-LAST:event_ProsesKeyPressed

    private void AnakkeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnakkeKeyPressed
        Valid.pindah(evt,Proses,G);
    }//GEN-LAST:event_AnakkeKeyPressed


    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            jkelcari=""; tglcari="";
            if(! cmbCrJk.getSelectedItem().toString().equals("SEMUA")){
                jkelcari=" pasien.jk='"+cmbCrJk.getSelectedItem().toString().substring(0,1)+"' and ";
            }

            if(ckTglCari.isSelected()==true){
                    tglcari=" pasien.tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
            }  
            
            
            Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 

            String sql="select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "+
                   "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "+
                   "pasien.tgl_daftar,pasien.nm_ibu,pasien_bayi.umur_ibu, "+
                   "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,"+
                   "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, "+
                   "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "+
                   "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan, "+
                   "pasien_bayi.diagnosa,pasien_bayi.penyulit_kehamilan,pasien_bayi.ketuban,"+
                   "pasien_bayi.lingkar_perut,pasien_bayi.lingkar_dada,pegawai.nama,"+
                   "pasien_bayi.no_skl from pasien inner join pegawai inner join pasien_bayi "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis and pasien_bayi.penolong=pegawai.nik "+
                   "and pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                   "where "+jkelcari+tglcari+" pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.nm_pasien like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.tgl_lahir like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.namakeluarga like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.alamat like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pegawai.nama like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien_bayi.diagnosa like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.nm_ibu like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien_bayi.proses_lahir like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien_bayi.penyulit_kehamilan like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien_bayi.ketuban like '%"+TCari.getText().trim()+"%'  order by pasien.no_rkm_medis desc";               
                Valid.MyReportqry("rptPasienbayi.jasper","report","::[ Data Bayi ]::",sql,param);            
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnEdit,BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        DlgBridgingLahir.dispose();
        dispose();  
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();  
        }else{Valid.pindah(evt,BtnAll,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void LingkarKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarKepalaKeyPressed
         Valid.pindah(evt,LingkarDada,LingkarPerut);
    }//GEN-LAST:event_LingkarKepalaKeyPressed

private void JKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JKelKeyPressed
        Valid.pindah(evt,AlamatIbu,Panjang);
}//GEN-LAST:event_JKelKeyPressed

private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        Valid.pindah(evt,Keterangan,Ketuban);
}//GEN-LAST:event_DiagnosaKeyPressed

private void NoRmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRmKeyPressed
    Valid.pindah(evt,TCari,NmBayi);
}//GEN-LAST:event_NoRmKeyPressed

private void LahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LahirKeyPressed
   Valid.pindah2(evt,LingkarPerut,jam);
}//GEN-LAST:event_LahirKeyPressed

private void cmbCrJkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCrJkKeyPressed
   Valid.pindah(evt,BtnAll, DTPCari1);
}//GEN-LAST:event_cmbCrJkKeyPressed

private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed
   Valid.pindah(evt,Proses,Diagnosa);
}//GEN-LAST:event_DTPCari1KeyPressed

private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_DTPCari2KeyPressed

private void jamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamKeyPressed
   Valid.pindah(evt,Lahir, menit);
}//GEN-LAST:event_jamKeyPressed

private void menitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menitKeyPressed
   Valid.pindah(evt,jam,detik);
}//GEN-LAST:event_menitKeyPressed

private void detikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_detikKeyPressed
   Valid.pindah(evt,menit,UmurBayi);
}//GEN-LAST:event_detikKeyPressed

private void BeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BeratKeyPressed
   Valid.pindah(evt,Panjang,LingkarDada);
}//GEN-LAST:event_BeratKeyPressed

private void PanjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PanjangKeyPressed
   Valid.pindah(evt,JKel,Berat);
}//GEN-LAST:event_PanjangKeyPressed

private void DaftarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DaftarKeyPressed
   Valid.pindah2(evt,NoSKL,BtnPenjab);
}//GEN-LAST:event_DaftarKeyPressed

private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
   Valid.pindah(evt,BtnPenjab,Diagnosa);
}//GEN-LAST:event_KeteranganKeyPressed

private void LahirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_LahirItemStateChanged
    lahir = Lahir.getDate();    
    birthday = lahir.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    p = Period.between(birthday,today);
    p2 =ChronoUnit.DAYS.between(birthday,today);
    UmurBayi.setText(String.valueOf(p.getYears())+" Th "+String.valueOf(p.getMonths())+" Bl "+String.valueOf(p.getDays())+" Hr");
}//GEN-LAST:event_LahirItemStateChanged

private void ppGrafikberatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikberatActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say="inner join pasien on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikberat kas=new grafikberat("Grafik Berat Badan Bayi "+tgl,say+" ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikberatActionPerformed

private void ppGrafikberatlakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikberatlakiActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikberat kas=new grafikberat("Grafik Berat Badan Bayi Laki-Laki "+tgl,"inner join pasien "+
                        "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where "+say+" pasien.jk='L' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikberatlakiActionPerformed

private void ppGrafikberatwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikberatwnActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikberat kas=new grafikberat("Grafik Berat Badan Bayi Perempuan "+tgl,"inner join pasien "+
                        "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where "+say+" pasien.jk='P' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikberatwnActionPerformed

private void ppGrafikjkbayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikjkbayiActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" where tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikjkelbayi kas=new grafikjkelbayi("Grafik Jenis Kelamin Bayi "+tgl,say+" ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikjkbayiActionPerformed

private void ppGrafikpanjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikpanjangActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say="inner join pasien on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikpanjang kas=new grafikpanjang("Grafik Panjang Badan Bayi "+tgl,say+" ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikpanjangActionPerformed

private void ppGrafikpanjanglakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikpanjanglakiActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikpanjang kas=new grafikpanjang("Grafik Panjang Badan Bayi Laki-Laki "+tgl,"inner join pasien "+
                        "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where "+say+" jk='L' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikpanjanglakiActionPerformed

private void ppGrafikpanjangwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikpanjangwnActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikpanjang kas=new grafikpanjang("Grafik Panjang Badan Bayi Perempuan "+tgl,"inner join pasien "+
                        "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where "+say+" jk='P' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikpanjangwnActionPerformed

private void ppGrafiklahirtahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirtahunActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" where tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafiklahirtahun kas=new grafiklahirtahun("Grafik Kelahiran Pertahun Bayi "+tgl,say+" ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirtahunActionPerformed

private void ppGrafiklahirtahunlakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirtahunlakiActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafiklahirtahun kas=new grafiklahirtahun("Grafik Kelahiran Pertahun Bayi Laki-Laki "+tgl," where "+say+" jk='L' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirtahunlakiActionPerformed

private void ppGrafiklahirtahunwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirtahunwnActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafiklahirtahun kas=new grafiklahirtahun("Grafik Kelahiran Pertahun Bayi Perempuan "+tgl,"  where "+say+" jk='P' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirtahunwnActionPerformed

private void ppGrafiklahirbulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirbulanActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" where tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafiklahirbulan kas=new grafiklahirbulan("Grafik Kelahiran Perbulan Bayi "+tgl,say+" ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirbulanActionPerformed

private void ppGrafiklahirbulanlakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirbulanlakiActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafiklahirbulan kas=new grafiklahirbulan("Grafik Kelahiran Perbulan Bayi Laki-Laki "+tgl," where "+say+" jk='L' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirbulanlakiActionPerformed

private void ppGrafiklahirbulanwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirbulanwnActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafiklahirbulan kas=new grafiklahirbulan("Grafik Kelahiran Perbulan Bayi Perempuan "+tgl," where "+say+" jk='P' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirbulanwnActionPerformed

private void ppGrafikproseslahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikproseslahirActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" where tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikproses kas=new grafikproses("Grafik Proses Lahir Bayi "+tgl,say+" ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikproseslahirActionPerformed

private void ppGrafikproseslahirlakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikproseslahirlakiActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikproses kas=new grafikproses("Grafik Proses Lahir Bayi Laki-Laki "+tgl," where "+say+" jk='L' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikproseslahirlakiActionPerformed

private void ppGrafikproseslahirwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikproseslahirwnActionPerformed
       String say="";
       String tgl="";
       if(ckTglCari.isSelected()==true){
           say=" tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
           tgl=" Antara Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" ";
       }
       grafikproses kas=new grafikproses("Grafik Proses Lahir Bayi Perempuan "+tgl," where "+say+" jk='P' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikproseslahirwnActionPerformed

private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(NoRm.getText().trim().equals("")){
            Valid.textKosong(NoRm,"No.Rekam Medis");
        }else if(NmBayi.getText().trim().equals("")){
            Valid.textKosong(NmBayi,"nama bayi");
        }else if(KdPenolong.getText().trim().equals("")||NmPenolong.getText().trim().equals("")){
            Valid.textKosong(KdPenolong,"penolong");
        }else if(NoSKL.getText().trim().equals("")){
            Valid.textKosong(NoSKL,"No.SKL");
        }else if(tbAPGAR.getValueAt(0,4).toString().equals("")||tbAPGAR.getValueAt(1,4).toString().equals("")||tbAPGAR.getValueAt(2,4).toString().equals("")||
                tbAPGAR.getValueAt(3,4).toString().equals("")||tbAPGAR.getValueAt(4,4).toString().equals("")||tbAPGAR.getValueAt(0,5).toString().equals("")||
                tbAPGAR.getValueAt(1,5).toString().equals("")||tbAPGAR.getValueAt(2,5).toString().equals("")||tbAPGAR.getValueAt(3,5).toString().equals("")||
                tbAPGAR.getValueAt(4,5).toString().equals("")||tbAPGAR.getValueAt(0,6).toString().equals("")||tbAPGAR.getValueAt(1,6).toString().equals("")||
                tbAPGAR.getValueAt(2,6).toString().equals("")||tbAPGAR.getValueAt(3,6).toString().equals("")||tbAPGAR.getValueAt(4,6).toString().equals("")){
            JOptionPane.showMessageDialog(null,"Nilai APGAR harus valid...!!!");
        }else{
            if(Sequel.cariIsi("select pasien.no_rkm_medis from pasien where pasien.no_rkm_medis=?",NoRm.getText()).isEmpty()){ 
                Sequel.queryu4("insert into cacat_fisik values(?,?)",2,new String[]{"0","-"});
                Sequel.queryu4("insert into penjab values(?,?)",2,new String[]{"-","-"});
                Sequel.queryu4("insert ignore into kelurahan values(?,?)",2,new String[]{"0","-"});
                Sequel.queryu4("insert ignore into kecamatan values(?,?)",2,new String[]{"0","-"});
                Sequel.queryu4("insert ignore into kabupaten values(?,?)",2,new String[]{"0","-"});
                Sequel.queryu4("insert ignore into propinsi values(?,?)",2,new String[]{"0","-"});
                Sequel.queryu4("insert into bahasa_pasien values(?,?)",2,new String[]{"0","-"});
                Sequel.queryu4("insert into suku_bangsa values(?,?)",2,new String[]{"0","-"});
                Sequel.queryu4("insert into perusahaan_pasien values(?,?,?,?,?)",2,new String[]{"-","-","-","-","-"});
                if(Sequel.menyimpantf("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                    NoRm.getText(),NmBayi.getText(),"-",JKel.getSelectedItem().toString().substring(0,1),"-",Valid.SetTgl(Lahir.getSelectedItem()+""),Nmibu.getText(),AlamatIbu.getText(),"-","-","BELUM MENIKAH","-",
                    Valid.SetTgl(Daftar.getSelectedItem()+""),"0",UmurBayi.getText(),"-","AYAH",NmAyah.getText(),"-","-",Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?","-"),
                    Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?","-"),Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?","-"),"-",AlamatIbu.getText(),"-","-","-","-",
                    Sequel.cariIsi("select suku_bangsa.id from suku_bangsa where suku_bangsa.nama_suku_bangsa=?","-"),Sequel.cariIsi("select bahasa_pasien.id from bahasa_pasien where bahasa_pasien.nama_bahasa=?","-"),
                    Sequel.cariIsi("select cacat_fisik.id from cacat_fisik where cacat_fisik.nama_cacat=?","-"),"-","-",Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?","-"),"-"
                })==true){
                    try {
                        if(Sequel.menyimpantf("pasien_bayi","'"+NoRm.getText()+"','"+
                            UmurIbu.getText()+"','"+NmAyah.getText()+"','"+UmurAyah.getText()+"','"+Berat.getText()+"','"+Panjang.getText()+"','"+
                            LingkarKepala.getText()+"','"+Proses.getText()+"','"+Anakke.getText()+"','"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"','"+
                            Keterangan.getText()+"','"+Diagnosa.getText()+"','"+PenyulitKehamilan.getText()+"','"+Ketuban.getText()+"','"+LingkarPerut.getText()+"','"+LingkarDada.getText()+"','"+
                            KdPenolong.getText()+"','"+NoSKL.getText()+"','"+G.getText()+"','"+P.getText()+"','"+A.getText()+"','"+tbAPGAR.getValueAt(0,4).toString()+"','"+
                            tbAPGAR.getValueAt(1,4).toString()+"','"+tbAPGAR.getValueAt(2,4).toString()+"','"+tbAPGAR.getValueAt(3,4).toString()+"','"+
                            tbAPGAR.getValueAt(4,4).toString()+"','"+N1.getText()+"','"+tbAPGAR.getValueAt(0,5).toString()+"','"+tbAPGAR.getValueAt(1,5).toString()+"','"+
                            tbAPGAR.getValueAt(2,5).toString()+"','"+tbAPGAR.getValueAt(3,5).toString()+"','"+tbAPGAR.getValueAt(4,5).toString()+"','"+N5.getText()+"','"+
                            tbAPGAR.getValueAt(0,6).toString()+"','"+tbAPGAR.getValueAt(1,6).toString()+"','"+tbAPGAR.getValueAt(2,6).toString()+"','"+
                            tbAPGAR.getValueAt(3,6).toString()+"','"+tbAPGAR.getValueAt(4,6).toString()+"','"+N10.getText()+"','"+Resusitas.getText()+"','"+ObatDiberikan.getText()+"','"+
                            Mikasi.getText()+"','"+Mikonium.getText()+"'","No.RM/No.SKL")==true){
                                Sequel.queryu2("delete from set_no_rkm_medis");
                                Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{NoRm.getText()}); 
                                emptTeks();
                        }else{
                            Sequel.meghapus("pasien","no_rkm_medis",NoRm.getText());
                        }  
                    } catch (Exception e) {
                        Sequel.meghapus("pasien","no_rkm_medis",NoRm.getText());
                        JOptionPane.showMessageDialog(null,"Gagal menyimpan kelahiran bayi, silahkan periksa data yang mau disimpan..!!");
                    }    
                }else{
                    autoNomor();
                    autoSKL();
                    if(Sequel.menyimpantf("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                            NoRm.getText(),NmBayi.getText(),"-",JKel.getSelectedItem().toString().substring(0,1),"-",Valid.SetTgl(Lahir.getSelectedItem()+""),Nmibu.getText(),AlamatIbu.getText(),"-","-","BELUM MENIKAH","-",
                            Valid.SetTgl(Daftar.getSelectedItem()+""),"0",UmurBayi.getText(),"-","AYAH",NmAyah.getText(),"-","-",Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?","-"),
                            Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?","-"),Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?","-"),"-",AlamatIbu.getText(),"-","-","-","-",
                            Sequel.cariIsi("select suku_bangsa.id from suku_bangsa where suku_bangsa.nama_suku_bangsa=?","-"),Sequel.cariIsi("select bahasa_pasien.id from bahasa_pasien where bahasa_pasien.nama_bahasa=?","-"),
                            Sequel.cariIsi("select cacat_fisik.id from cacat_fisik where cacat_fisik.nama_cacat=?","-"),"-","-",Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?","-"),"-"
                        })==true){
                            try {
                                if(Sequel.menyimpantf("pasien_bayi","'"+NoRm.getText()+"','"+
                                    UmurIbu.getText()+"','"+NmAyah.getText()+"','"+UmurAyah.getText()+"','"+Berat.getText()+"','"+Panjang.getText()+"','"+
                                    LingkarKepala.getText()+"','"+Proses.getText()+"','"+Anakke.getText()+"','"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"','"+
                                    Keterangan.getText()+"','"+Diagnosa.getText()+"','"+PenyulitKehamilan.getText()+"','"+Ketuban.getText()+"','"+LingkarPerut.getText()+"','"+LingkarDada.getText()+"','"+
                                    KdPenolong.getText()+"','"+NoSKL.getText()+"','"+G.getText()+"','"+P.getText()+"','"+A.getText()+"','"+Valid.SetInteger(tbAPGAR.getValueAt(0,4).toString())+"','"+
                                    Valid.SetInteger(tbAPGAR.getValueAt(1,4).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(2,4).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(3,4).toString())+"','"+
                                    Valid.SetInteger(tbAPGAR.getValueAt(4,4).toString())+"','"+N1.getText()+"','"+Valid.SetInteger(tbAPGAR.getValueAt(0,5).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(1,5).toString())+"','"+
                                    Valid.SetInteger(tbAPGAR.getValueAt(2,5).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(3,5).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(4,5).toString())+"','"+N5.getText()+"','"+
                                    Valid.SetInteger(tbAPGAR.getValueAt(0,6).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(1,6).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(2,6).toString())+"','"+
                                    Valid.SetInteger(tbAPGAR.getValueAt(3,6).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(4,6).toString())+"','"+N10.getText()+"','"+Resusitas.getText()+"','"+ObatDiberikan.getText()+"','"+
                                    Mikasi.getText()+"','"+Mikonium.getText()+"'","No.RM/No.SKL")==true){
                                        Sequel.queryu2("delete from set_no_rkm_medis");
                                        Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{NoRm.getText()}); 
                                        emptTeks();
                                }else{
                                    Sequel.meghapus("pasien","no_rkm_medis",NoRm.getText());
                                }  
                            } catch (Exception e) {
                                Sequel.meghapus("pasien","no_rkm_medis",NoRm.getText());
                                JOptionPane.showMessageDialog(null,"Gagal menyimpan kelahiran bayi, silahkan periksa data yang mau disimpan..!!");
                            }  
                    }else{
                        autoNomor();
                        autoSKL();
                        if(Sequel.menyimpantf("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                                NoRm.getText(),NmBayi.getText(),"-",JKel.getSelectedItem().toString().substring(0,1),"-",Valid.SetTgl(Lahir.getSelectedItem()+""),Nmibu.getText(),AlamatIbu.getText(),"-","-","BELUM MENIKAH","-",
                                Valid.SetTgl(Daftar.getSelectedItem()+""),"0",UmurBayi.getText(),"-","AYAH",NmAyah.getText(),"-","-",Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?","-"),
                                Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?","-"),Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?","-"),"-",AlamatIbu.getText(),"-","-","-","-",
                                Sequel.cariIsi("select suku_bangsa.id from suku_bangsa where suku_bangsa.nama_suku_bangsa=?","-"),Sequel.cariIsi("select bahasa_pasien.id from bahasa_pasien where bahasa_pasien.nama_bahasa=?","-"),
                                Sequel.cariIsi("select cacat_fisik.id from cacat_fisik where cacat_fisik.nama_cacat=?","-"),"-","-",Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?","-"),"-"
                            })==true){
                                try {
                                    if(Sequel.menyimpantf("pasien_bayi","'"+NoRm.getText()+"','"+
                                        UmurIbu.getText()+"','"+NmAyah.getText()+"','"+UmurAyah.getText()+"','"+Berat.getText()+"','"+Panjang.getText()+"','"+
                                        LingkarKepala.getText()+"','"+Proses.getText()+"','"+Anakke.getText()+"','"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"','"+
                                        Keterangan.getText()+"','"+Diagnosa.getText()+"','"+PenyulitKehamilan.getText()+"','"+Ketuban.getText()+"','"+LingkarPerut.getText()+"','"+LingkarDada.getText()+"','"+
                                        KdPenolong.getText()+"','"+NoSKL.getText()+"','"+G.getText()+"','"+P.getText()+"','"+A.getText()+"','"+Valid.SetInteger(tbAPGAR.getValueAt(0,4).toString())+"','"+
                                        Valid.SetInteger(tbAPGAR.getValueAt(1,4).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(2,4).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(3,4).toString())+"','"+
                                        Valid.SetInteger(tbAPGAR.getValueAt(4,4).toString())+"','"+N1.getText()+"','"+Valid.SetInteger(tbAPGAR.getValueAt(0,5).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(1,5).toString())+"','"+
                                        Valid.SetInteger(tbAPGAR.getValueAt(2,5).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(3,5).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(4,5).toString())+"','"+N5.getText()+"','"+
                                        Valid.SetInteger(tbAPGAR.getValueAt(0,6).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(1,6).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(2,6).toString())+"','"+
                                        Valid.SetInteger(tbAPGAR.getValueAt(3,6).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(4,6).toString())+"','"+N10.getText()+"','"+Resusitas.getText()+"','"+ObatDiberikan.getText()+"','"+
                                        Mikasi.getText()+"','"+Mikonium.getText()+"'","No.RM/No.SKL")==true){
                                            Sequel.queryu2("delete from set_no_rkm_medis");
                                            Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{NoRm.getText()}); 
                                            emptTeks();
                                    }else{
                                        Sequel.meghapus("pasien","no_rkm_medis",NoRm.getText());
                                    }  
                                } catch (Exception e) {
                                    Sequel.meghapus("pasien","no_rkm_medis",NoRm.getText());
                                    JOptionPane.showMessageDialog(null,"Gagal menyimpan kelahiran bayi, silahkan periksa data yang mau disimpan..!!");
                                }
                        }
                    }
                }
            }else{
                try {
                    if(Sequel.menyimpantf("pasien_bayi","'"+NoRm.getText()+"','"+
                            UmurIbu.getText()+"','"+NmAyah.getText()+"','"+UmurAyah.getText()+"','"+Berat.getText()+"','"+Panjang.getText()+"','"+
                            LingkarKepala.getText()+"','"+Proses.getText()+"','"+Anakke.getText()+"','"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"','"+
                            Keterangan.getText()+"','"+Diagnosa.getText()+"','"+PenyulitKehamilan.getText()+"','"+Ketuban.getText()+"','"+LingkarPerut.getText()+"','"+LingkarDada.getText()+"','"+
                            KdPenolong.getText()+"','"+NoSKL.getText()+"','"+G.getText()+"','"+P.getText()+"','"+A.getText()+"','"+Valid.SetInteger(tbAPGAR.getValueAt(0,4).toString())+"','"+
                            Valid.SetInteger(tbAPGAR.getValueAt(1,4).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(2,4).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(3,4).toString())+"','"+
                            Valid.SetInteger(tbAPGAR.getValueAt(4,4).toString())+"','"+N1.getText()+"','"+Valid.SetInteger(tbAPGAR.getValueAt(0,5).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(1,5).toString())+"','"+
                            Valid.SetInteger(tbAPGAR.getValueAt(2,5).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(3,5).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(4,5).toString())+"','"+N5.getText()+"','"+
                            Valid.SetInteger(tbAPGAR.getValueAt(0,6).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(1,6).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(2,6).toString())+"','"+
                            Valid.SetInteger(tbAPGAR.getValueAt(3,6).toString())+"','"+Valid.SetInteger(tbAPGAR.getValueAt(4,6).toString())+"','"+N10.getText()+"','"+Resusitas.getText()+"','"+ObatDiberikan.getText()+"','"+
                            Mikasi.getText()+"','"+Mikonium.getText()+"'","No.RM/No.SKL")==true){
                            emptTeks();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Gagal menyimpan kelahiran bayi, silahkan periksa data yang mau disimpan..!!");
                }            
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Mikonium,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

private void BtnEditActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed1
        if(NoRm.getText().trim().equals("")){
            Valid.textKosong(NoRm,"No.Rekam Medis");
        }else if(NmBayi.getText().trim().equals("")){
            Valid.textKosong(NmBayi,"nama bayi");
        }else if(KdPenolong.getText().trim().equals("")||NmPenolong.getText().trim().equals("")){
            Valid.textKosong(KdPenolong,"penolong");
        }else if(NoSKL.getText().trim().equals("")){
            Valid.textKosong(NoSKL,"No.SKL");
        }else if(tbAPGAR.getValueAt(0,4).toString().equals("")||tbAPGAR.getValueAt(1,4).toString().equals("")||tbAPGAR.getValueAt(2,4).toString().equals("")||
                tbAPGAR.getValueAt(3,4).toString().equals("")||tbAPGAR.getValueAt(4,4).toString().equals("")||tbAPGAR.getValueAt(0,5).toString().equals("")||
                tbAPGAR.getValueAt(1,5).toString().equals("")||tbAPGAR.getValueAt(2,5).toString().equals("")||tbAPGAR.getValueAt(3,5).toString().equals("")||
                tbAPGAR.getValueAt(4,5).toString().equals("")||tbAPGAR.getValueAt(0,6).toString().equals("")||tbAPGAR.getValueAt(1,6).toString().equals("")||
                tbAPGAR.getValueAt(2,6).toString().equals("")||tbAPGAR.getValueAt(3,6).toString().equals("")||tbAPGAR.getValueAt(4,6).toString().equals("")){
            JOptionPane.showMessageDialog(null,"Nilai APGAR harus valid...!!!");
        }else{
            Valid.editTable(tabMode,"pasien","no_rkm_medis",Kd2,"no_rkm_medis='"+NoRm.getText()+
                    "',nm_pasien='"+NmBayi.getText()+
                    "',jk='"+JKel.getSelectedItem().toString().substring(0,1)+
                    "',tgl_lahir='"+Valid.SetTgl(Lahir.getSelectedItem()+"")+
                    "',tgl_daftar='"+Valid.SetTgl(Daftar.getSelectedItem()+"")+
                    "',umur='"+UmurBayi.getText()+
                    "',nm_ibu='"+Nmibu.getText()+
                    "',namakeluarga='"+NmAyah.getText()+"'");
            Valid.editTable(tabMode,"pasien_bayi","no_rkm_medis",Kd2,"umur_ibu='"+UmurIbu.getText()+"',nama_ayah='"+NmAyah.getText()+"',umur_ayah='"+UmurAyah.getText()+"',"+
                    "berat_badan='"+Berat.getText()+"',panjang_badan='"+Panjang.getText()+"',lingkar_kepala='"+LingkarKepala.getText()+"',proses_lahir='"+Proses.getText()+"',"+
                    "anakke='"+Anakke.getText()+"',jam_lahir='"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"',keterangan='"+Keterangan.getText()+"',"+
                    "diagnosa='"+Diagnosa.getText()+"',penyulit_kehamilan='"+PenyulitKehamilan.getText()+"',ketuban='"+Ketuban.getText()+"',lingkar_perut='"+LingkarPerut.getText()+"',"+
                    "lingkar_dada='"+LingkarDada.getText()+"',penolong='"+KdPenolong.getText()+"',no_skl='"+NoSKL.getText()+"',g='"+G.getText()+"',p='"+P.getText()+"',a='"+A.getText()+"',"+
                    "f1='"+Valid.SetInteger(tbAPGAR.getValueAt(0,4).toString())+"',u1='"+Valid.SetInteger(tbAPGAR.getValueAt(1,4).toString())+"',t1='"+Valid.SetInteger(tbAPGAR.getValueAt(2,4).toString())+"',"+
                    "r1='"+Valid.SetInteger(tbAPGAR.getValueAt(3,4).toString())+"',w1='"+Valid.SetInteger(tbAPGAR.getValueAt(4,4).toString())+"',n1='"+N1.getText()+"',"+
                    "f5='"+Valid.SetInteger(tbAPGAR.getValueAt(0,5).toString())+"',u5='"+Valid.SetInteger(tbAPGAR.getValueAt(1,5).toString())+"',t5='"+Valid.SetInteger(tbAPGAR.getValueAt(2,5).toString())+"',"+
                    "r5='"+Valid.SetInteger(tbAPGAR.getValueAt(3,5).toString())+"',w5='"+Valid.SetInteger(tbAPGAR.getValueAt(4,5).toString())+"',n5='"+N5.getText()+"',"+
                    "f10='"+Valid.SetInteger(tbAPGAR.getValueAt(0,6).toString())+"',u10='"+Valid.SetInteger(tbAPGAR.getValueAt(1,6).toString())+"',t10='"+Valid.SetInteger(tbAPGAR.getValueAt(2,6).toString())+"',"+
                    "r10='"+Valid.SetInteger(tbAPGAR.getValueAt(3,6).toString())+"',w10='"+Valid.SetInteger(tbAPGAR.getValueAt(4,6).toString())+"',n10='"+N10.getText()+"',resusitas='"+Resusitas.getText()+"',"+
                    "obat_diberikan='"+ObatDiberikan.getText()+"',mikasi='"+Mikasi.getText()+"',mikonium='"+Mikonium.getText()+"'");
            if(tabMode.getRowCount()!=0){tampil();TabRawat.setSelectedIndex(1);}
        }
}//GEN-LAST:event_BtnEditActionPerformed1

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_SPACE){
           BtnEditActionPerformed1(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

private void BtnHapusActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed1
   Valid.hapusTable(tabMode,NoRm,"pasien_bayi","no_rkm_medis");
   Valid.hapusTable(tabMode,NoRm,"bridging_dukcapil","no_rkm_medis");
   tampil();
}//GEN-LAST:event_BtnHapusActionPerformed1

private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed1(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

private void NmBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmBayiKeyPressed
   Valid.pindah(evt, NoRm,Nmibu);
}//GEN-LAST:event_NmBayiKeyPressed

private void NmibuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmibuKeyPressed
   Valid.pindah(evt, NmBayi,UmurIbu);
}//GEN-LAST:event_NmibuKeyPressed

private void UmurIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurIbuKeyPressed
   Valid.pindah(evt, Nmibu,NmAyah);
}//GEN-LAST:event_UmurIbuKeyPressed

private void NmAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmAyahKeyPressed
   Valid.pindah(evt, Nmibu,UmurAyah);
}//GEN-LAST:event_NmAyahKeyPressed

private void UmurAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurAyahKeyPressed
    Valid.pindah(evt, NmAyah,AlamatIbu);
}//GEN-LAST:event_UmurAyahKeyPressed

private void AlamatIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatIbuKeyPressed
    Valid.pindah(evt, UmurAyah,JKel);
}//GEN-LAST:event_AlamatIbuKeyPressed

private void MnKartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            NoRm.requestFocus();
        }else if(NmBayi.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien yang mau pulang dengan menklik data pada table...!!!");
            tbDokter.requestFocus();
        }else{
            Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());  
            Valid.MyReportqry("rptKartuBerobat.jasper","report","::[ Kartu Periksa Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir, pasien.alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga from pasien where pasien.no_rkm_medis='"+NoRm.getText()+"' ",param);
        }
}//GEN-LAST:event_MnKartuActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnInformasiBayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInformasiBayiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptInformasiBayi.jasper","report","::[ Data Informasi Bayi ]::",
                       "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "+
                       "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "+
                       "pasien.tgl_daftar,pasien.nm_ibu as namakeluarga,pasien_bayi.umur_ibu, "+
                       "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,pasien.alamat, "+
                       "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "+
                       "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan from pasien "+
                       "inner join pasien_bayi on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis "+
                       "where pasien_bayi.no_rkm_medis='"+NoRm.getText()+"'",param);            
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInformasiBayiActionPerformed

    private void MnSKLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKLActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Locale locale = new Locale ("id", "ID"); 
            Locale.setDefault(locale);
            Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("nomor",NoSKL.getText());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                param.put("logo2",Sequel.cariGambar("select setting.logo from setting"));
                param.put("finger",Sequel.cariIsi("select * from pasien_bayi where no_rkm_medis='061172'"));
                Valid.MyReportqry("rptSKL.jasper","report","::[ Surat Kelahiran Bayi ]::",
                       "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "+
                        "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "+
                        "pasien.tgl_daftar,pasien.nm_ibu,pasien_bayi.umur_ibu, "+
                        "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,"+
                        "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, "+
                        "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "+
                        "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan, "+
                        "pasien_bayi.diagnosa,pasien_bayi.penyulit_kehamilan,pasien_bayi.ketuban,"+
                        "pasien_bayi.lingkar_perut,pasien_bayi.lingkar_dada,pegawai.nama,"+
                        "pasien_bayi.no_skl from pasien inner join pegawai inner join pasien_bayi "+
                        "inner join kelurahan inner join kecamatan inner join kabupaten "+
                        "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis and pasien_bayi.penolong=pegawai.nik "+
                        "and pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                        "where pasien_bayi.no_rkm_medis='"+NoRm.getText()+"'",param);            
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSKLActionPerformed

    private void UmurBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurBayiKeyPressed
        Valid.pindah(evt,detik,NoSKL);
    }//GEN-LAST:event_UmurBayiKeyPressed

    private void PenyulitKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyulitKehamilanKeyPressed
        Valid.pindah(evt,Ketuban,Proses);
    }//GEN-LAST:event_PenyulitKehamilanKeyPressed

    private void KetubanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetubanKeyPressed
        Valid.pindah(evt,Diagnosa,PenyulitKehamilan);
    }//GEN-LAST:event_KetubanKeyPressed

    private void LingkarDadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarDadaKeyPressed
        Valid.pindah(evt,Berat,LingkarKepala);
    }//GEN-LAST:event_LingkarDadaKeyPressed

    private void LingkarPerutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarPerutKeyPressed
        Valid.pindah(evt,LingkarKepala,Lahir);
    }//GEN-LAST:event_LingkarPerutKeyPressed

    private void NoSKLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSKLKeyPressed
        Valid.pindah(evt,UmurBayi,Daftar);
    }//GEN-LAST:event_NoSKLKeyPressed

    private void BtnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenjabActionPerformed
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setAlwaysOnTop(false);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPenjabActionPerformed

    private void BtnKelurahan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahan1ActionPerformed
        if(NoRm.getText().trim().equals("")){
            Valid.textKosong(NoRm,"No.Rekam Medis");
        }else if(NmBayi.getText().trim().equals("")){
            Valid.textKosong(NmBayi,"nama bayi");
        }else if(KdPenolong.getText().trim().equals("")||NmPenolong.getText().trim().equals("")){
            Valid.textKosong(KdPenolong,"penolong");
        }else if(NoSKL.getText().trim().equals("")){
            Valid.textKosong(NoSKL,"No.SKL");
        }else if(Anakke.getText().trim().equals("")){
            Valid.textKosong(Anakke,"Kelahiran");
        }else if(Berat.getText().trim().equals("")){
            Valid.textKosong(Berat,"Berat");
        }else if(Panjang.getText().trim().equals("")){
            Valid.textKosong(Panjang,"Panjang");
        }else{
            if(internalFrame1.getWidth()>1015){
                DlgBridgingLahir.setSize(1015,337);
            }else{
                DlgBridgingLahir.setSize(internalFrame1.getWidth()-20,345);
            }
            
            if(DlgBridgingLahir.getWidth()<1010){
                scrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                panelBiasa2.setPreferredSize(new Dimension(1013,335));
            }else{
                scrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            }
            
            DlgBridgingLahir.setLocationRelativeTo(internalFrame1);
            DlgBridgingLahir.setVisible(true);
        }            
    }//GEN-LAST:event_BtnKelurahan1ActionPerformed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        DlgBridgingLahir.dispose();
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void NIKIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIKIbuKeyPressed
        
    }//GEN-LAST:event_NIKIbuKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        if(NIKIbu.getText().trim().equals("")){
            NIKIbu.requestFocus();
            JOptionPane.showMessageDialog(null,"Silahkan isi terlebih dahulu NIK Ibu..!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            cekViaDukcapilJakarta.tampil(NIKIbu.getText());
            nokk=cekViaDukcapilJakarta.NO_KK;
            nikibu=cekViaDukcapilJakarta.NIK;
            nmibu=cekViaDukcapilJakarta.NAMA_LGKP;
            Nmibu.setText(cekViaDukcapilJakarta.NAMA_LGKP);
            alamatibu=cekViaDukcapilJakarta.ALAMAT+" "+cekViaDukcapilJakarta.NO_RT+" "+cekViaDukcapilJakarta.NO_RW+" "+cekViaDukcapilJakarta.NM_KEL+" "+cekViaDukcapilJakarta.NM_KEC+" "+cekViaDukcapilJakarta.NM_KAB+" "+cekViaDukcapilJakarta.NM_PROP;
            AlamatIbu.setText(cekViaDukcapilJakarta.ALAMAT+" RT "+cekViaDukcapilJakarta.NO_RT+" RW "+cekViaDukcapilJakarta.NO_RW+", "+cekViaDukcapilJakarta.NM_KEL+", "+cekViaDukcapilJakarta.NM_KEC+", "+cekViaDukcapilJakarta.NM_KAB+", "+cekViaDukcapilJakarta.NM_PROP);
            umribu=cekViaDukcapilJakarta.UMUR;
            UmurIbu.setText(cekViaDukcapilJakarta.UMUR);
            kerjaibu=cekViaDukcapilJakarta.JENIS_PKRJN;
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, NIKIbu, BtnAll);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void NIKAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIKAyahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIKAyahKeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        if(NIKIbu.getText().trim().equals("")){
            NIKIbu.requestFocus();
            JOptionPane.showMessageDialog(null,"Silahkan isi terlebih dahulu NIK Ibu..!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            cekViaDukcapilJakarta.tampil(NIKAyah.getText());
            nikayah=cekViaDukcapilJakarta.NIK;
            nmayah=cekViaDukcapilJakarta.NAMA_LGKP;
            NmAyah.setText(cekViaDukcapilJakarta.NAMA_LGKP);
            UmurAyah.setText(cekViaDukcapilJakarta.UMUR);
            umrayah=cekViaDukcapilJakarta.UMUR;
            UmurAyah2.setText(cekViaDukcapilJakarta.UMUR);
            alamatayah=cekViaDukcapilJakarta.ALAMAT+" RT "+cekViaDukcapilJakarta.NO_RT+" RW "+cekViaDukcapilJakarta.NO_RW+", "+cekViaDukcapilJakarta.NM_KEL+", "+cekViaDukcapilJakarta.NM_KEC+", "+cekViaDukcapilJakarta.NM_KAB+", "+cekViaDukcapilJakarta.NM_PROP;
            kerjaayah=cekViaDukcapilJakarta.JENIS_PKRJN;
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void JenisLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisLahirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisLahirKeyPressed

    private void PenolongLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenolongLahirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenolongLahirKeyPressed

    private void CaraLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraLahirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CaraLahirKeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if(NoRm.getText().trim().equals("")){
            Valid.textKosong(NoRm,"No.Rekam Medis");
        }else if(NmBayi.getText().trim().equals("")){
            Valid.textKosong(NmBayi,"nama bayi");
        }else if(KdPenolong.getText().trim().equals("")||NmPenolong.getText().trim().equals("")){
            Valid.textKosong(KdPenolong,"penolong");
        }else if(NoSKL.getText().trim().equals("")){
            Valid.textKosong(NoSKL,"No.SKL");
        }else if(Anakke.getText().trim().equals("")){
            Valid.textKosong(Anakke,"Kelahiran");
        }else if(Berat.getText().trim().equals("")){
            Valid.textKosong(Berat,"Berat");
        }else if(Panjang.getText().trim().equals("")){
            Valid.textKosong(Panjang,"Panjang");
        }else if(NIKAyah.getText().trim().equals("")){
            Valid.textKosong(NIKAyah,"NIK Ayah");
        }else if(NIKIbu.getText().trim().equals("")){
            Valid.textKosong(NIKIbu,"NIK Ibu");
        }else if(TelpOrtu.getText().trim().equals("")){
            Valid.textKosong(TelpOrtu,"Telp Ortu");
        }else{            
            if(Sequel.cariInteger("select count(no_rkm_medis) from bridging_dukcapil where no_rkm_medis=?",NoRm.getText())==0){
                tgllhr=Lahir.getSelectedItem().toString().replaceAll("-","/");
                jamlhr=jam.getSelectedItem()+":"+menit.getSelectedItem();
                jk=JKel.getSelectedItem().toString().replaceAll("LAKI-LAKI","1").replaceAll("PEREMPUAN","2");
                jnslhr=JenisLahir.getSelectedItem().toString().substring(0,1);
                lahirke=Anakke.getText();
                brt=Berat.getText();
                pjg=Panjang.getText();
                pnlglhr=PenolongLahir.getSelectedItem().toString().substring(0,1);
                bpjsibu=BPJSIbu.getText();
                noskl=NoSKL.getText();
                pnlgnama=NmPenolong.getText();         
                bpjsayah=BPJSAyah.getText();
                bpjsby=BPJSBayi.getText();
                nmbayi=NmBayi.getText();
                tindaklhr=CaraLahir.getSelectedItem().toString().substring(0,1);
                krjplpr=(PekerjaanPelapor.getSelectedIndex()+1)+"";
                krjs1=(PekerjaanSaksi1.getSelectedIndex()+1)+"";
                krjs2=(PekerjaanSaksi2.getSelectedIndex()+1)+"";
                notlp=TelpOrtu.getText();
                
                if(postlahir.post(nokk, nmbayi, tgllhr, jamlhr, jk, jnslhr, lahirke, brt, pjg, 
                        pnlglhr, nikibu, nmibu, alamatibu, kerjaibu, nikayah, nmayah, alamatayah, 
                        kerjaayah, noskl, pnlgnama, tindaklhr, bpjsibu, bpjsayah, notlp, bpjsby,
                        nikplpr,nmplpr,almtplpr,krjplpr,niks1,nms1,almts1,krjs1,niks2,nms2,almts2,
                        krjs2,umribu,umrayah,umrplpr,umrs1,umrs2)==true){
                    if(Sequel.cariIsi("select pasien.no_rkm_medis from pasien where pasien.no_rkm_medis=?",NoRm.getText()).equals("")){   
                        
                        Sequel.queryu4("insert into cacat_fisik values(?,?)",2,new String[]{"0","-"});
                        Sequel.queryu4("insert into penjab values(?,?)",2,new String[]{"-","-"});
                        Sequel.queryu4("insert ignore into kelurahan values(?,?)",2,new String[]{"0","-"});
                        Sequel.queryu4("insert ignore into kecamatan values(?,?)",2,new String[]{"0","-"});
                        Sequel.queryu4("insert ignore into kabupaten values(?,?)",2,new String[]{"0","-"});
                        Sequel.queryu4("insert ignore into propinsi values(?,?)",2,new String[]{"0","-"});
                        Sequel.queryu4("insert into bahasa_pasien values(?,?)",2,new String[]{"0","-"});
                        Sequel.queryu4("insert into suku_bangsa values(?,?)",2,new String[]{"0","-"});
                        Sequel.queryu4("insert into perusahaan_pasien values(?,?,?,?,?)",2,new String[]{"-","-","-","-","-"});
                        if(Sequel.menyimpantf("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                                NoRm.getText(),NmBayi.getText(),"-",
                                JKel.getSelectedItem().toString().substring(0,1),"-",
                                Valid.SetTgl(Lahir.getSelectedItem()+""),
                                Nmibu.getText(),AlamatIbu.getText(),"-","-","BELUM MENIKAH","-",
                                Valid.SetTgl(Daftar.getSelectedItem()+""),"0",UmurBayi.getText(),
                                "-","AYAH",NmAyah.getText(),"-","-",
                                Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?","-"),
                                Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?","-"),
                                Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?","-"),
                                "-",AlamatIbu.getText(),"-","-","-","-",
                                Sequel.cariIsi("select suku_bangsa.id from suku_bangsa where suku_bangsa.nama_suku_bangsa=?","-"),
                                Sequel.cariIsi("select bahasa_pasien.id from bahasa_pasien where bahasa_pasien.nama_bahasa=?","-"),
                                Sequel.cariIsi("select cacat_fisik.id from cacat_fisik where cacat_fisik.nama_cacat=?","-"),
                                "-","-",Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?","-"),"-"
                            })==true){
                                if(Sequel.menyimpantf("pasien_bayi","'"+NoRm.getText()+"','"+
                                    UmurIbu.getText()+"','"+NmAyah.getText()+"','"+UmurAyah.getText()+"','"+Berat.getText()+"','"+Panjang.getText()+"','"+
                                    LingkarKepala.getText()+"','"+Proses.getText()+"','"+Anakke.getText()+"','"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"','"+
                                    Keterangan.getText()+"','"+Diagnosa.getText()+"','"+PenyulitKehamilan.getText()+"','"+Ketuban.getText()+"','"+LingkarPerut.getText()+"','"+LingkarDada.getText()+"','"+
                                    KdPenolong.getText()+"','"+NoSKL.getText()+"','"+G.getText()+"','"+P.getText()+"','"+A.getText()+"','"+tbAPGAR.getValueAt(0,4).toString()+"','"+
                                    tbAPGAR.getValueAt(1,4).toString()+"','"+tbAPGAR.getValueAt(2,4).toString()+"','"+tbAPGAR.getValueAt(3,4).toString()+"','"+
                                    tbAPGAR.getValueAt(4,4).toString()+"','"+N1.getText()+"','"+tbAPGAR.getValueAt(0,5).toString()+"','"+tbAPGAR.getValueAt(1,5).toString()+"','"+
                                    tbAPGAR.getValueAt(2,5).toString()+"','"+tbAPGAR.getValueAt(3,5).toString()+"','"+tbAPGAR.getValueAt(4,5).toString()+"','"+N5.getText()+"','"+
                                    tbAPGAR.getValueAt(0,6).toString()+"','"+tbAPGAR.getValueAt(1,6).toString()+"','"+tbAPGAR.getValueAt(2,6).toString()+"','"+
                                    tbAPGAR.getValueAt(3,6).toString()+"','"+tbAPGAR.getValueAt(4,6).toString()+"','"+N10.getText()+"','"+Resusitas.getText()+"','"+ObatDiberikan.getText()+"','"+
                                    Mikasi.getText()+"','"+Mikonium.getText()+"'","No.RM/No.SKL")==true){
                                        Sequel.queryu2("delete from set_no_rkm_medis");
                                        Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{NoRm.getText()});
                                        Sequel.menyimpan("bridging_dukcapil","?,?",2,new String[]{NoRm.getText(),postlahir.UID});
                                        emptTeks();
                                }                                           
                        }else{
                            autoNomor();
                            autoSKL();
                            if(Sequel.menyimpantf("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                                    NoRm.getText(),NmBayi.getText(),"-",
                                    JKel.getSelectedItem().toString().substring(0,1),"-",
                                    Valid.SetTgl(Lahir.getSelectedItem()+""),
                                    Nmibu.getText(),AlamatIbu.getText(),"-","-","BELUM MENIKAH","-",
                                    Valid.SetTgl(Daftar.getSelectedItem()+""),"0",UmurBayi.getText(),
                                    "-","AYAH",NmAyah.getText(),"-","-",
                                    Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?","-"),
                                    Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?","-"),
                                    Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?","-"),
                                    "-",AlamatIbu.getText(),"-","-","-","-",
                                    Sequel.cariIsi("select suku_bangsa.id from suku_bangsa where suku_bangsa.nama_suku_bangsa=?","-"),
                                    Sequel.cariIsi("select bahasa_pasien.id from bahasa_pasien where bahasa_pasien.nama_bahasa=?","-"),
                                    Sequel.cariIsi("select cacat_fisik.id from cacat_fisik where cacat_fisik.nama_cacat=?","-"),
                                    "-","-",Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?","-"),"-"
                                })==true){
                                    if(Sequel.menyimpantf("pasien_bayi","'"+NoRm.getText()+"','"+
                                        UmurIbu.getText()+"','"+NmAyah.getText()+"','"+UmurAyah.getText()+"','"+Berat.getText()+"','"+Panjang.getText()+"','"+
                                        LingkarKepala.getText()+"','"+Proses.getText()+"','"+Anakke.getText()+"','"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"','"+
                                        Keterangan.getText()+"','"+Diagnosa.getText()+"','"+PenyulitKehamilan.getText()+"','"+Ketuban.getText()+"','"+LingkarPerut.getText()+"','"+LingkarDada.getText()+"','"+
                                        KdPenolong.getText()+"','"+NoSKL.getText()+"','"+G.getText()+"','"+P.getText()+"','"+A.getText()+"','"+tbAPGAR.getValueAt(0,4).toString()+"','"+
                                        tbAPGAR.getValueAt(1,4).toString()+"','"+tbAPGAR.getValueAt(2,4).toString()+"','"+tbAPGAR.getValueAt(3,4).toString()+"','"+
                                        tbAPGAR.getValueAt(4,4).toString()+"','"+N1.getText()+"','"+tbAPGAR.getValueAt(0,5).toString()+"','"+tbAPGAR.getValueAt(1,5).toString()+"','"+
                                        tbAPGAR.getValueAt(2,5).toString()+"','"+tbAPGAR.getValueAt(3,5).toString()+"','"+tbAPGAR.getValueAt(4,5).toString()+"','"+N5.getText()+"','"+
                                        tbAPGAR.getValueAt(0,6).toString()+"','"+tbAPGAR.getValueAt(1,6).toString()+"','"+tbAPGAR.getValueAt(2,6).toString()+"','"+
                                        tbAPGAR.getValueAt(3,6).toString()+"','"+tbAPGAR.getValueAt(4,6).toString()+"','"+N10.getText()+"','"+Resusitas.getText()+"','"+ObatDiberikan.getText()+"','"+
                                        Mikasi.getText()+"','"+Mikonium.getText()+"'","No.RM/No.SKL")==true){
                                            Sequel.queryu2("delete from set_no_rkm_medis");
                                            Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{NoRm.getText()});
                                            Sequel.menyimpan("bridging_dukcapil","?,?",2,new String[]{NoRm.getText(),postlahir.UID});
                                            emptTeks();
                                    }  
                            }else{
                                autoNomor();
                                autoSKL();
                                if(Sequel.menyimpantf("pasien","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rekam Medis Pasien",36,new String[]{
                                        NoRm.getText(),NmBayi.getText(),"-",
                                        JKel.getSelectedItem().toString().substring(0,1),"-",
                                        Valid.SetTgl(Lahir.getSelectedItem()+""),
                                        Nmibu.getText(),AlamatIbu.getText(),"-","-","BELUM MENIKAH","-",
                                        Valid.SetTgl(Daftar.getSelectedItem()+""),"0",UmurBayi.getText(),
                                        "-","AYAH",NmAyah.getText(),"-","-",
                                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?","-"),
                                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?","-"),
                                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?","-"),
                                        "-",AlamatIbu.getText(),"-","-","-","-",
                                        Sequel.cariIsi("select suku_bangsa.id from suku_bangsa where suku_bangsa.nama_suku_bangsa=?","-"),
                                        Sequel.cariIsi("select bahasa_pasien.id from bahasa_pasien where bahasa_pasien.nama_bahasa=?","-"),
                                        Sequel.cariIsi("select cacat_fisik.id from cacat_fisik where cacat_fisik.nama_cacat=?","-"),
                                        "-","-",Sequel.cariIsi("select propinsi.kd_prop from propinsi where propinsi.nm_prop=?","-"),"-"
                                    })==true){
                                        if(Sequel.menyimpantf("pasien_bayi","'"+NoRm.getText()+"','"+
                                            UmurIbu.getText()+"','"+NmAyah.getText()+"','"+UmurAyah.getText()+"','"+Berat.getText()+"','"+Panjang.getText()+"','"+
                                            LingkarKepala.getText()+"','"+Proses.getText()+"','"+Anakke.getText()+"','"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"','"+
                                            Keterangan.getText()+"','"+Diagnosa.getText()+"','"+PenyulitKehamilan.getText()+"','"+Ketuban.getText()+"','"+LingkarPerut.getText()+"','"+LingkarDada.getText()+"','"+
                                            KdPenolong.getText()+"','"+NoSKL.getText()+"','"+G.getText()+"','"+P.getText()+"','"+A.getText()+"','"+tbAPGAR.getValueAt(0,4).toString()+"','"+
                                            tbAPGAR.getValueAt(1,4).toString()+"','"+tbAPGAR.getValueAt(2,4).toString()+"','"+tbAPGAR.getValueAt(3,4).toString()+"','"+
                                            tbAPGAR.getValueAt(4,4).toString()+"','"+N1.getText()+"','"+tbAPGAR.getValueAt(0,5).toString()+"','"+tbAPGAR.getValueAt(1,5).toString()+"','"+
                                            tbAPGAR.getValueAt(2,5).toString()+"','"+tbAPGAR.getValueAt(3,5).toString()+"','"+tbAPGAR.getValueAt(4,5).toString()+"','"+N5.getText()+"','"+
                                            tbAPGAR.getValueAt(0,6).toString()+"','"+tbAPGAR.getValueAt(1,6).toString()+"','"+tbAPGAR.getValueAt(2,6).toString()+"','"+
                                            tbAPGAR.getValueAt(3,6).toString()+"','"+tbAPGAR.getValueAt(4,6).toString()+"','"+N10.getText()+"','"+Resusitas.getText()+"','"+ObatDiberikan.getText()+"','"+
                                            Mikasi.getText()+"','"+Mikonium.getText()+"'","No.RM/No.SKL")==true){
                                                Sequel.queryu2("delete from set_no_rkm_medis");
                                                Sequel.queryu2("insert into set_no_rkm_medis values(?)",1,new String[]{NoRm.getText()});
                                                Sequel.menyimpan("bridging_dukcapil","?,?",2,new String[]{NoRm.getText(),postlahir.UID});
                                                emptTeks();
                                        }                                                  
                                }else{
                                    if(Sequel.menyimpantf("pasien_bayi","'"+NoRm.getText()+"','"+
                                        UmurIbu.getText()+"','"+NmAyah.getText()+"','"+UmurAyah.getText()+"','"+Berat.getText()+"','"+Panjang.getText()+"','"+
                                        LingkarKepala.getText()+"','"+Proses.getText()+"','"+Anakke.getText()+"','"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"','"+
                                        Keterangan.getText()+"','"+Diagnosa.getText()+"','"+PenyulitKehamilan.getText()+"','"+Ketuban.getText()+"','"+LingkarPerut.getText()+"','"+LingkarDada.getText()+"','"+
                                        KdPenolong.getText()+"','"+NoSKL.getText()+"','"+G.getText()+"','"+P.getText()+"','"+A.getText()+"','"+tbAPGAR.getValueAt(0,4).toString()+"','"+
                                        tbAPGAR.getValueAt(1,4).toString()+"','"+tbAPGAR.getValueAt(2,4).toString()+"','"+tbAPGAR.getValueAt(3,4).toString()+"','"+
                                        tbAPGAR.getValueAt(4,4).toString()+"','"+N1.getText()+"','"+tbAPGAR.getValueAt(0,5).toString()+"','"+tbAPGAR.getValueAt(1,5).toString()+"','"+
                                        tbAPGAR.getValueAt(2,5).toString()+"','"+tbAPGAR.getValueAt(3,5).toString()+"','"+tbAPGAR.getValueAt(4,5).toString()+"','"+N5.getText()+"','"+
                                        tbAPGAR.getValueAt(0,6).toString()+"','"+tbAPGAR.getValueAt(1,6).toString()+"','"+tbAPGAR.getValueAt(2,6).toString()+"','"+
                                        tbAPGAR.getValueAt(3,6).toString()+"','"+tbAPGAR.getValueAt(4,6).toString()+"','"+N10.getText()+"','"+Resusitas.getText()+"','"+ObatDiberikan.getText()+"','"+
                                        Mikasi.getText()+"','"+Mikonium.getText()+"'","No.RM/No.SKL")==true){
                                                Sequel.menyimpan("bridging_dukcapil","?,?",2,new String[]{NoRm.getText(),postlahir.UID});
                                                emptTeks();
                                    } 
                                }
                            }
                        }
                        
                    }else{
                        if(Sequel.menyimpantf("pasien_bayi","'"+NoRm.getText()+"','"+
                            UmurIbu.getText()+"','"+NmAyah.getText()+"','"+UmurAyah.getText()+"','"+Berat.getText()+"','"+Panjang.getText()+"','"+
                            LingkarKepala.getText()+"','"+Proses.getText()+"','"+Anakke.getText()+"','"+jam.getSelectedItem()+":"+menit.getSelectedItem()+":"+detik.getSelectedItem()+"','"+
                            Keterangan.getText()+"','"+Diagnosa.getText()+"','"+PenyulitKehamilan.getText()+"','"+Ketuban.getText()+"','"+LingkarPerut.getText()+"','"+LingkarDada.getText()+"','"+
                            KdPenolong.getText()+"','"+NoSKL.getText()+"','"+G.getText()+"','"+P.getText()+"','"+A.getText()+"','"+tbAPGAR.getValueAt(0,4).toString()+"','"+
                            tbAPGAR.getValueAt(1,4).toString()+"','"+tbAPGAR.getValueAt(2,4).toString()+"','"+tbAPGAR.getValueAt(3,4).toString()+"','"+
                            tbAPGAR.getValueAt(4,4).toString()+"','"+N1.getText()+"','"+tbAPGAR.getValueAt(0,5).toString()+"','"+tbAPGAR.getValueAt(1,5).toString()+"','"+
                            tbAPGAR.getValueAt(2,5).toString()+"','"+tbAPGAR.getValueAt(3,5).toString()+"','"+tbAPGAR.getValueAt(4,5).toString()+"','"+N5.getText()+"','"+
                            tbAPGAR.getValueAt(0,6).toString()+"','"+tbAPGAR.getValueAt(1,6).toString()+"','"+tbAPGAR.getValueAt(2,6).toString()+"','"+
                            tbAPGAR.getValueAt(3,6).toString()+"','"+tbAPGAR.getValueAt(4,6).toString()+"','"+N10.getText()+"','"+Resusitas.getText()+"','"+ObatDiberikan.getText()+"','"+
                            Mikasi.getText()+"','"+Mikonium.getText()+"'","No.RM/No.SKL")==true){
                                    Sequel.menyimpan("bridging_dukcapil","?,?",2,new String[]{NoRm.getText(),postlahir.UID});
                                    emptTeks();
                        }             
                    }                    
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Pasien sudah terbridging dukcapil sebelumnya..!!");
            }
                
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void TelpOrtuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TelpOrtuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelpOrtuKeyPressed

    private void BPJSAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BPJSAyahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BPJSAyahKeyPressed

    private void BPJSIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BPJSIbuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BPJSIbuKeyPressed

    private void PenolongLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PenolongLahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenolongLahirActionPerformed

    private void BPJSBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BPJSBayiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BPJSBayiKeyPressed

    private void NIKPelaporKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIKPelaporKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIKPelaporKeyPressed

    private void BtnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari3ActionPerformed
        if(NIKPelapor.getText().trim().equals("")){
            NIKPelapor.requestFocus();
            JOptionPane.showMessageDialog(null,"Silahkan isi terlebih dahulu NIK Pelapor..!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            cekViaDukcapilJakarta.tampil(NIKPelapor.getText());
            nikplpr=cekViaDukcapilJakarta.NIK;
            nmplpr=cekViaDukcapilJakarta.NAMA_LGKP;
            NamaPelapor.setText(cekViaDukcapilJakarta.NAMA_LGKP);
            almtplpr=cekViaDukcapilJakarta.ALAMAT+" RT "+cekViaDukcapilJakarta.NO_RT+" RW "+cekViaDukcapilJakarta.NO_RW+", "+cekViaDukcapilJakarta.NM_KEL+", "+cekViaDukcapilJakarta.NM_KEC+", "+cekViaDukcapilJakarta.NM_KAB+", "+cekViaDukcapilJakarta.NM_PROP;
            AlamatPelapor.setText(cekViaDukcapilJakarta.ALAMAT+" RT "+cekViaDukcapilJakarta.NO_RT+" RW "+cekViaDukcapilJakarta.NO_RW+", "+cekViaDukcapilJakarta.NM_KEL+", "+cekViaDukcapilJakarta.NM_KEC+", "+cekViaDukcapilJakarta.NM_KAB+", "+cekViaDukcapilJakarta.NM_PROP);
            umrplpr=cekViaDukcapilJakarta.UMUR;
            UmurPelapor.setText(cekViaDukcapilJakarta.UMUR);
            krjplpr=cekViaDukcapilJakarta.JENIS_PKRJN;
            PekerjaanPelapor.setSelectedItem(cekViaDukcapilJakarta.DSC_JENIS_PKRJN);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCari3ActionPerformed

    private void BtnCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari3KeyPressed

    private void NamaPelaporKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaPelaporKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaPelaporKeyPressed

    private void PekerjaanPelaporKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PekerjaanPelaporKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PekerjaanPelaporKeyPressed

    private void AlamatPelaporKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatPelaporKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatPelaporKeyPressed

    private void UmurPelaporKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurPelaporKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UmurPelaporKeyPressed

    private void NIKSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIKSaksi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIKSaksi1KeyPressed

    private void BtnCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari4ActionPerformed
        if(NIKSaksi1.getText().trim().equals("")){
            NIKSaksi1.requestFocus();
            JOptionPane.showMessageDialog(null,"Silahkan isi terlebih dahulu NIK Saksi 1..!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            cekViaDukcapilJakarta.tampil(NIKSaksi1.getText());
            niks1=cekViaDukcapilJakarta.NIK;
            nms1=cekViaDukcapilJakarta.NAMA_LGKP;
            NamaSaksi1.setText(cekViaDukcapilJakarta.NAMA_LGKP);
            almts1=cekViaDukcapilJakarta.ALAMAT+" RT "+cekViaDukcapilJakarta.NO_RT+" RW "+cekViaDukcapilJakarta.NO_RW+", "+cekViaDukcapilJakarta.NM_KEL+", "+cekViaDukcapilJakarta.NM_KEC+", "+cekViaDukcapilJakarta.NM_KAB+", "+cekViaDukcapilJakarta.NM_PROP;
            AlamatSaksi1.setText(cekViaDukcapilJakarta.ALAMAT+" RT "+cekViaDukcapilJakarta.NO_RT+" RW "+cekViaDukcapilJakarta.NO_RW+", "+cekViaDukcapilJakarta.NM_KEL+", "+cekViaDukcapilJakarta.NM_KEC+", "+cekViaDukcapilJakarta.NM_KAB+", "+cekViaDukcapilJakarta.NM_PROP);
            umrs1=cekViaDukcapilJakarta.UMUR;
            UmurSaksi1.setText(cekViaDukcapilJakarta.UMUR);
            krjs1=cekViaDukcapilJakarta.JENIS_PKRJN;
            PekerjaanSaksi1.setSelectedItem(cekViaDukcapilJakarta.DSC_JENIS_PKRJN);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCari4ActionPerformed

    private void BtnCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari4KeyPressed

    private void NamaSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaSaksi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaSaksi1KeyPressed

    private void AlamatSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatSaksi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatSaksi1KeyPressed

    private void PekerjaanSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PekerjaanSaksi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PekerjaanSaksi1KeyPressed

    private void UmurSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurSaksi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UmurSaksi1KeyPressed

    private void UmurAyah2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurAyah2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UmurAyah2KeyPressed

    private void NIKSaksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIKSaksi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIKSaksi2KeyPressed

    private void BtnCari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari5ActionPerformed
        if(NIKSaksi2.getText().trim().equals("")){
            NIKSaksi2.requestFocus();
            JOptionPane.showMessageDialog(null,"Silahkan isi terlebih dahulu NIK Saksi 2..!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            cekViaDukcapilJakarta.tampil(NIKSaksi2.getText());
            niks2=cekViaDukcapilJakarta.NIK;
            nms2=cekViaDukcapilJakarta.NAMA_LGKP;
            NamaSaksi2.setText(cekViaDukcapilJakarta.NAMA_LGKP);
            almts2=cekViaDukcapilJakarta.ALAMAT+" RT "+cekViaDukcapilJakarta.NO_RT+" RW "+cekViaDukcapilJakarta.NO_RW+", "+cekViaDukcapilJakarta.NM_KEL+", "+cekViaDukcapilJakarta.NM_KEC+", "+cekViaDukcapilJakarta.NM_KAB+", "+cekViaDukcapilJakarta.NM_PROP;
            AlamatSaksi2.setText(cekViaDukcapilJakarta.ALAMAT+" RT "+cekViaDukcapilJakarta.NO_RT+" RW "+cekViaDukcapilJakarta.NO_RW+", "+cekViaDukcapilJakarta.NM_KEL+", "+cekViaDukcapilJakarta.NM_KEC+", "+cekViaDukcapilJakarta.NM_KAB+", "+cekViaDukcapilJakarta.NM_PROP);
            umrs2=cekViaDukcapilJakarta.UMUR;
            UmurSaksi2.setText(cekViaDukcapilJakarta.UMUR);
            krjs2=cekViaDukcapilJakarta.JENIS_PKRJN;
            PekerjaanSaksi2.setSelectedItem(cekViaDukcapilJakarta.DSC_JENIS_PKRJN);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCari5ActionPerformed

    private void BtnCari5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari5KeyPressed

    private void NamaSaksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaSaksi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaSaksi2KeyPressed

    private void AlamatSaksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatSaksi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatSaksi2KeyPressed

    private void PekerjaanSaksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PekerjaanSaksi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PekerjaanSaksi2KeyPressed

    private void UmurSaksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurSaksi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UmurSaksi2KeyPressed

    private void MnSKL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKL1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Locale locale = new Locale ("id", "ID"); 
            Locale.setDefault(locale);
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("nomor",NoSKL.getText());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            param.put("logo2",Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptSKL2.jasper","report","::[ Surat Kelahiran Bayi ]::",
                "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "+
                "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "+
                "pasien.tgl_daftar,pasien.nm_ibu,pasien_bayi.umur_ibu,pasien.pekerjaanpj, "+
                "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,pasien.no_ktp,"+
                "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, "+
                "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "+
                "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan, "+
                "pasien_bayi.diagnosa,pasien_bayi.penyulit_kehamilan,pasien_bayi.ketuban,"+
                "pasien_bayi.lingkar_perut,pasien_bayi.lingkar_dada,pegawai.nama,"+
                "pasien_bayi.no_skl from pasien inner join pegawai inner join pasien_bayi "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis and pasien_bayi.penolong=pegawai.nik "+
                "and pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                "where pasien_bayi.no_rkm_medis='"+NoRm.getText()+"'",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSKL1ActionPerformed

    private void MnSKL2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKL2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Locale locale = new Locale ("id", "ID"); 
            Locale.setDefault(locale); 
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("nomor",NoSKL.getText());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            param.put("logo2",Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptSKL3.jasper","report","::[ Surat Kelahiran Bayi ]::",
                "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "+
                "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "+
                "pasien.tgl_daftar,pasien.nm_ibu,pasien_bayi.umur_ibu,pasien.pekerjaanpj, "+
                "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,pasien.no_ktp,"+
                "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, "+
                "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "+
                "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan, "+
                "pasien_bayi.diagnosa,pasien_bayi.penyulit_kehamilan,pasien_bayi.ketuban,"+
                "pasien_bayi.lingkar_perut,pasien_bayi.lingkar_dada,pegawai.nama,"+
                "pasien_bayi.no_skl from pasien inner join pegawai inner join pasien_bayi "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis and pasien_bayi.penolong=pegawai.nik "+
                "and pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                "where pasien_bayi.no_rkm_medis='"+NoRm.getText()+"'",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSKL2ActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void GKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GKeyPressed
        Valid.pindah(evt,Anakke,P);
    }//GEN-LAST:event_GKeyPressed

    private void PKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PKeyPressed
        //Valid.pindah(evt,G,A);
    }//GEN-LAST:event_PKeyPressed

    private void AKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AKeyPressed
        Valid.pindah(evt,P,Resusitas);
    }//GEN-LAST:event_AKeyPressed

    private void ResusitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResusitasKeyPressed
        Valid.pindah(evt,A,ObatDiberikan);
    }//GEN-LAST:event_ResusitasKeyPressed

    private void ObatDiberikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatDiberikanKeyPressed
        Valid.pindah(evt,Resusitas,Mikasi);
    }//GEN-LAST:event_ObatDiberikanKeyPressed

    private void MikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MikasiKeyPressed
        Valid.pindah(evt,ObatDiberikan,Mikonium);
    }//GEN-LAST:event_MikasiKeyPressed

    private void MikoniumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MikoniumKeyPressed
        Valid.pindah(evt,Mikasi,BtnSimpan);
    }//GEN-LAST:event_MikoniumKeyPressed

    private void BtnPenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPenjabKeyPressed
        Valid.pindah(evt,Daftar,Keterangan);
    }//GEN-LAST:event_BtnPenjabKeyPressed

    private void tbAPGARPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbAPGARPropertyChange
        if(this.isVisible()==true){
              getDataApgar();
        }
    }//GEN-LAST:event_tbAPGARPropertyChange

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgIKBBayi dialog = new DlgIKBBayi(new javax.swing.JFrame(), true);
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
    private widget.TextBox A;
    private widget.TextBox AlamatIbu;
    private widget.TextBox AlamatPelapor;
    private widget.TextBox AlamatSaksi1;
    private widget.TextBox AlamatSaksi2;
    private widget.TextBox Anakke;
    private widget.TextBox BPJSAyah;
    private widget.TextBox BPJSBayi;
    private widget.TextBox BPJSIbu;
    private widget.TextBox Berat;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCari3;
    private widget.Button BtnCari4;
    private widget.Button BtnCari5;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKelurahan1;
    private widget.Button BtnPenjab;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.ComboBox CaraLahir;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal Daftar;
    private widget.TextBox Diagnosa;
    private javax.swing.JDialog DlgBridgingLahir;
    private widget.PanelBiasa FormInput;
    private widget.TextBox G;
    private widget.ComboBox JKel;
    private widget.ComboBox JenisLahir;
    private widget.TextBox Kd2;
    private widget.TextBox KdPenolong;
    private widget.TextArea Keterangan;
    private widget.TextBox Ketuban;
    private widget.Label LCount;
    private widget.Tanggal Lahir;
    private widget.TextBox LingkarDada;
    private widget.TextBox LingkarKepala;
    private widget.TextBox LingkarPerut;
    private widget.TextBox Mikasi;
    private widget.TextBox Mikonium;
    private javax.swing.JMenuItem MnInformasiBayi;
    private javax.swing.JMenuItem MnKartu;
    private javax.swing.JMenuItem MnSKL;
    private javax.swing.JMenuItem MnSKL1;
    private javax.swing.JMenuItem MnSKL2;
    private widget.TextBox2 N1;
    private widget.TextBox2 N10;
    private widget.TextBox2 N5;
    private widget.TextBox NIKAyah;
    private widget.TextBox NIKIbu;
    private widget.TextBox NIKPelapor;
    private widget.TextBox NIKSaksi1;
    private widget.TextBox NIKSaksi2;
    private widget.TextBox NamaPelapor;
    private widget.TextBox NamaSaksi1;
    private widget.TextBox NamaSaksi2;
    private widget.TextBox NmAyah;
    private widget.TextBox NmBayi;
    private widget.TextBox NmPenolong;
    private widget.TextBox Nmibu;
    private widget.TextBox NoRm;
    private widget.TextBox NoSKL;
    private widget.TextArea ObatDiberikan;
    private widget.TextBox P;
    private widget.TextBox Panjang;
    private widget.ComboBox PekerjaanPelapor;
    private widget.ComboBox PekerjaanSaksi1;
    private widget.ComboBox PekerjaanSaksi2;
    private widget.ComboBox PenolongLahir;
    private widget.TextBox PenyulitKehamilan;
    private javax.swing.JPopupMenu Popup;
    private widget.TextBox Proses;
    private widget.TextBox Resusitas;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TelpOrtu;
    private widget.TextBox UmurAyah;
    private widget.TextBox UmurAyah2;
    private widget.TextBox UmurBayi;
    private widget.TextBox UmurIbu;
    private widget.TextBox UmurPelapor;
    private widget.TextBox UmurSaksi1;
    private widget.TextBox UmurSaksi2;
    private widget.CekBox ckTglCari;
    private widget.ComboBox cmbCrJk;
    private widget.ComboBox cmbHlm;
    private widget.ComboBox detik;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel24;
    private widget.Label jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JPanel jPanel2;
    private widget.ComboBox jam;
    private widget.Label label10;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label27;
    private widget.Label label28;
    private widget.Label label29;
    private widget.Label label30;
    private widget.Label label31;
    private widget.Label label32;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label38;
    private widget.Label label39;
    private widget.Label label40;
    private widget.Label label41;
    private widget.Label label42;
    private widget.Label label43;
    private widget.Label label44;
    private widget.Label label45;
    private widget.Label label46;
    private widget.Label label47;
    private widget.Label label48;
    private widget.Label label49;
    private widget.Label label50;
    private widget.Label label51;
    private widget.Label label52;
    private widget.Label label53;
    private widget.Label label54;
    private widget.Label label55;
    private widget.Label label56;
    private widget.Label label57;
    private widget.Label label58;
    private widget.Label label59;
    private widget.Label label60;
    private widget.Label label61;
    private widget.Label label62;
    private widget.Label label63;
    private widget.Label label64;
    private widget.Label label65;
    private widget.Label label66;
    private widget.Label label67;
    private widget.Label label68;
    private widget.Label label69;
    private widget.Label label70;
    private widget.Label label71;
    private widget.Label label9;
    private widget.ComboBox menit;
    private widget.PanelBiasa panelBiasa2;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private javax.swing.JMenuItem ppGrafikberat;
    private javax.swing.JMenuItem ppGrafikberatlaki;
    private javax.swing.JMenuItem ppGrafikberatwn;
    private javax.swing.JMenuItem ppGrafikjkbayi;
    private javax.swing.JMenuItem ppGrafiklahirbulan;
    private javax.swing.JMenuItem ppGrafiklahirbulanlaki;
    private javax.swing.JMenuItem ppGrafiklahirbulanwn;
    private javax.swing.JMenuItem ppGrafiklahirtahun;
    private javax.swing.JMenuItem ppGrafiklahirtahunlaki;
    private javax.swing.JMenuItem ppGrafiklahirtahunwn;
    private javax.swing.JMenuItem ppGrafikpanjang;
    private javax.swing.JMenuItem ppGrafikpanjanglaki;
    private javax.swing.JMenuItem ppGrafikpanjangwn;
    private javax.swing.JMenuItem ppGrafikproseslahir;
    private javax.swing.JMenuItem ppGrafikproseslahirlaki;
    private javax.swing.JMenuItem ppGrafikproseslahirwn;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.Table tbAPGAR;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        jkelcari=""; tglcari="";
        if(! cmbCrJk.getSelectedItem().toString().equals("SEMUA")){
            jkelcari=" pasien.jk='"+cmbCrJk.getSelectedItem().toString().substring(0,1)+"' and ";
        }
        
        if(ckTglCari.isSelected()==true){
                tglcari=" pasien.tgl_lahir between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and ";
        }        
        
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "+
                   "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "+
                   "pasien.tgl_daftar,pasien.nm_ibu,pasien_bayi.umur_ibu, "+
                   "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,"+
                   "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, "+
                   "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "+
                   "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan, "+
                   "pasien_bayi.diagnosa,pasien_bayi.penyulit_kehamilan,pasien_bayi.ketuban,"+
                   "pasien_bayi.lingkar_perut,pasien_bayi.lingkar_dada,pasien_bayi.penolong,"+
                   "pasien_bayi.no_skl,pasien_bayi.g,pasien_bayi.p,pasien_bayi.a,pasien_bayi.f1,pasien_bayi.u1,pasien_bayi.t1,"+
                   "pasien_bayi.r1,pasien_bayi.w1,pasien_bayi.n1,pasien_bayi.f5,pasien_bayi.u5,"+
                   "pasien_bayi.t5,pasien_bayi.r5,pasien_bayi.w5,pasien_bayi.n5,pasien_bayi.f10,"+
                   "pasien_bayi.u10,pasien_bayi.t10,pasien_bayi.r10,pasien_bayi.w10,pasien_bayi.n10,"+
                   "pasien_bayi.resusitas,pasien_bayi.obat_diberikan,pasien_bayi.mikasi,pasien_bayi.mikonium "+
                   "from pasien inner join pegawai inner join pasien_bayi "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis and pasien_bayi.penolong=pegawai.nik "+
                   "and pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                   "where "+jkelcari+tglcari+" pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.nm_pasien like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.tgl_lahir like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.namakeluarga like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.alamat like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pegawai.nama like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien_bayi.diagnosa like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien.nm_ibu like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien_bayi.proses_lahir like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien_bayi.penyulit_kehamilan like '%"+TCari.getText().trim()+"%' "+
                   "or "+jkelcari+tglcari+" pasien_bayi.ketuban like '%"+TCari.getText().trim()+"%'  order by pasien.no_rkm_medis desc");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
                        rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),
                        rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),
                        rs.getString(21),rs.getString(22),rs.getString(23),rs.getString(24),
                        rs.getString(25),rs.getString(26),rs.getString(27),rs.getString(28),
                        rs.getString(29),rs.getString(30),rs.getString(31),rs.getString(32),
                        rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),
                        rs.getString(37),rs.getString(38),rs.getString(39),rs.getString(40),
                        rs.getString(41),rs.getString(42),rs.getString(43),rs.getString(44),
                        rs.getString(45),rs.getString(46),rs.getString(47),rs.getString(48),
                        rs.getString(49),rs.getString(50)
                    });
               
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        int b=tabMode.getRowCount();
        LCount.setText(""+b);
    }
    

    public void emptTeks() {
        NoRm.setText("");
        NmBayi.setText("");
        Nmibu.setText("");
        UmurIbu.setText("");
        NmAyah.setText("");
        UmurAyah.setText("");
        AlamatIbu.setText("");
        JKel.setSelectedIndex(0);
        Berat.setText("");
        Lahir.setDate(new Date());
        jam.setSelectedIndex(0);
        menit.setSelectedIndex(0);
        detik.setSelectedIndex(0);
        Panjang.setText("");
        LingkarKepala.setText("");
        UmurBayi.setText("");
        Proses.setText("");
        LingkarPerut.setText("");
        LingkarDada.setText("");
        Daftar.setDate(new Date());
        Anakke.setText("");
        Diagnosa.setText("");
        PenyulitKehamilan.setText("");
        Ketuban.setText("");
        Keterangan.setText("");
        G.setText("");
        P.setText("");
        A.setText("");
        tbAPGAR.setValueAt("",0,4);
        tbAPGAR.setValueAt("",1,4);
        tbAPGAR.setValueAt("",2,4);
        tbAPGAR.setValueAt("",3,4);
        tbAPGAR.setValueAt("",4,4);
        N1.setText("");
        tbAPGAR.setValueAt("",0,5);
        tbAPGAR.setValueAt("",1,5);
        tbAPGAR.setValueAt("",2,5);
        tbAPGAR.setValueAt("",3,5);
        tbAPGAR.setValueAt("",4,5);
        N5.setText("");
        tbAPGAR.setValueAt("",0,6);
        tbAPGAR.setValueAt("",1,6);
        tbAPGAR.setValueAt("",2,6);
        tbAPGAR.setValueAt("",3,6);
        tbAPGAR.setValueAt("",4,6);
        N10.setText("");
        Resusitas.setText("");
        ObatDiberikan.setText("");
        Mikasi.setText("");
        Mikonium.setText("");
        autoNomor();        
        autoSKL();
        NoRm.requestFocus();
        TabRawat.setSelectedIndex(0);
    }
    

    private void getData() {
        if(tbDokter.getSelectedRow()!= -1){
            NoRm.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),0).toString());
            Kd2.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),0).toString());
            NmBayi.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),1).toString());
            if(tbDokter.getValueAt(tbDokter.getSelectedRow(),2).toString().equals("L")){
                JKel.setSelectedItem("LAKI-LAKI");
            }else if(tbDokter.getValueAt(tbDokter.getSelectedRow(),2).toString().equals("P")){
                JKel.setSelectedItem("PEREMPUAN");
            }
            Valid.SetTgl(Lahir,tbDokter.getValueAt(tbDokter.getSelectedRow(),3).toString());            
            jam.setSelectedItem(tbDokter.getValueAt(tbDokter.getSelectedRow(),4).toString().substring(0,2));
            menit.setSelectedItem(tbDokter.getValueAt(tbDokter.getSelectedRow(),4).toString().substring(3,5));
            detik.setSelectedItem(tbDokter.getValueAt(tbDokter.getSelectedRow(),4).toString().substring(6,8));
            UmurBayi.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),5).toString());
            Valid.SetTgl(Daftar,tbDokter.getValueAt(tbDokter.getSelectedRow(),6).toString()); 
            Nmibu.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),7).toString());
            UmurIbu.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),8).toString());
            NmAyah.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),9).toString());
            UmurAyah.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),10).toString());
            AlamatIbu.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),11).toString());
            Berat.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),12).toString());
            Panjang.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),13).toString());
            LingkarKepala.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),14).toString());
            Proses.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),15).toString());
            Anakke.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),16).toString());
            Keterangan.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),17).toString());    
            Diagnosa.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),18).toString());    
            PenyulitKehamilan.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),19).toString());    
            Ketuban.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),20).toString());    
            LingkarPerut.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString());    
            LingkarDada.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),22).toString());    
            NmPenolong.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),23).toString());  
            NoSKL.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),24).toString());
            G.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),25).toString());
            P.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),26).toString());
            A.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),27).toString());
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),28).toString(),0,4);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),29).toString(),1,4);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),30).toString(),2,4);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),31).toString(),3,4);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),32).toString(),4,4);
            N1.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),33).toString());
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),34).toString(),0,5);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),35).toString(),1,5);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),36).toString(),2,5);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),37).toString(),3,5);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),38).toString(),4,5);
            N5.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),39).toString());
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),40).toString(),0,6);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),41).toString(),1,6);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),42).toString(),2,6);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),43).toString(),3,6);
            tbAPGAR.setValueAt(tbDokter.getValueAt(tbDokter.getSelectedRow(),44).toString(),4,6);
            N10.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),45).toString());
            Resusitas.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),46).toString());
            ObatDiberikan.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),47).toString());
            Mikasi.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),48).toString());
            Mikonium.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),49).toString());
            Sequel.cariIsi("select pasien_bayi.penolong from pasien_bayi where pasien_bayi.no_rkm_medis=?",KdPenolong,tbDokter.getValueAt(tbDokter.getSelectedRow(),0).toString());
        }
    }


    public JTextField getTextField(){
        return NoRm;
    }

    public JButton getButton(){
        return BtnKeluar;
    }

    public void setNoRM(String norm,String nama,String ibubayi,String alamatibu,
            String jkel,String umur,Date tgllhir,Date daftar){
        NoRm.setText(norm);
        NmBayi.setText(nama);
        Nmibu.setText(ibubayi);
        AlamatIbu.setText(alamatibu);
        JKel.setSelectedItem(jkel);
        UmurBayi.setText(umur);
        Lahir.setDate(tgllhir);
        Daftar.setDate(daftar); 
        
        if(Sequel.cariIsi("select pasien.keluarga from pasien where pasien.no_rkm_medis=?",norm).equals("AYAH")){
            Sequel.cariIsi("select pasien.namakeluarga from pasien where pasien.no_rkm_medis=?",NmAyah,norm);
        }
        autoSKL();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getkelahiran_bayi());
        BtnHapus.setEnabled(akses.getkelahiran_bayi());
        BtnEdit.setEnabled(akses.getkelahiran_bayi());
        BtnPrint.setEnabled(akses.getkelahiran_bayi());
    }
    
    private void autoNomor() {  
        if(Kd2.getText().equals("")){            
            if(tahun.equals("Yes")){
                awalantahun=Daftar.getSelectedItem().toString().substring(8,10);
            }else{
                awalantahun="";
            }

            if(bulan.equals("Yes")){
                awalanbulan=Daftar.getSelectedItem().toString().substring(3,5);
            }else{
                awalanbulan="";
            }

            if(posisitahun.equals("Depan")){
                switch (pengurutan) {
                    case "Straight":
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(set_no_rkm_medis.no_rkm_medis,6),signed)),0) from set_no_rkm_medis","",6,NoRm);
                        break;
                    case "Terminal":
                        Valid.autoNomer4("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),5,2),SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),3,2),SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),1,2)),signed)),0) from set_no_rkm_medis","",6,NoRm);
                        break;
                    case "Middle":
                        Valid.autoNomer5("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),3,2),SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),1,2),SUBSTRING(RIGHT(set_no_rkm_medis.no_rkm_medis,6),5,2)),signed)),0) from set_no_rkm_medis","",6,NoRm);
                        break;
                }
            }else if(posisitahun.equals("Belakang")){
                switch (pengurutan) {
                    case "Straight":
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(LEFT(set_no_rkm_medis.no_rkm_medis,6),signed)),0) from set_no_rkm_medis","",6,NoRm);
                        break;
                    case "Terminal":
                        Valid.autoNomer4("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),5,2),SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),3,2),SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),1,2)),signed)),0) from set_no_rkm_medis","",6,NoRm);
                        break;
                    case "Middle":
                        Valid.autoNomer5("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),3,2),SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),1,2),SUBSTRING(LEFT(set_no_rkm_medis.no_rkm_medis,6),5,2)),signed)),0) from set_no_rkm_medis","",6,NoRm);
                        break;
                }            
            }

            if(posisitahun.equals("Depan")){
                NoRm.setText(awalantahun+awalanbulan+NoRm.getText());
            }else if(posisitahun.equals("Belakang")){
                if(!(awalanbulan+awalantahun).equals("")){
                    NoRm.setText(NoRm.getText()+"-"+awalanbulan+awalantahun);
                }else{
                    NoRm.setText(NoRm.getText());
                }            
            }
        }        
    }
    
    public JTable getTable(){
        return tbDokter;
    }

    private void autoSKL() {
        Valid.autoNomer6("select ifnull(MAX(CONVERT(LEFT(pasien_bayi.no_skl,4),signed)),0) from pasien inner join pasien_bayi on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where "+
                       "pasien.tgl_lahir like '%"+Valid.SetTgl(Lahir.getSelectedItem()+"").substring(0,7)+"%' ","/RM-SKL/"+Valid.SetTgl(Lahir.getSelectedItem()+"").substring(5,7)+
                        "/"+Valid.SetTgl(Lahir.getSelectedItem()+"").substring(0,4),4,NoSKL);         
    }

    private void getDataApgar() {
        try {
            if(tbAPGAR.getValueAt(0,4).toString().equals("")||tbAPGAR.getValueAt(1,4).toString().equals("")||tbAPGAR.getValueAt(2,4).toString().equals("")||
                tbAPGAR.getValueAt(3,4).toString().equals("")||tbAPGAR.getValueAt(4,4).toString().equals("")){
                N1.setText("");
            }else{
                N1.setText((Valid.SetInteger(tbAPGAR.getValueAt(0,4).toString())+Valid.SetInteger(tbAPGAR.getValueAt(1,4).toString())+Valid.SetInteger(tbAPGAR.getValueAt(2,4).toString())+Valid.SetInteger(tbAPGAR.getValueAt(3,4).toString())+Valid.SetInteger(tbAPGAR.getValueAt(4,4).toString()))+"");
            }
        } catch (Exception e) {
            N1.setText("");
        }
        
        try {
            if(tbAPGAR.getValueAt(0,5).toString().equals("")||tbAPGAR.getValueAt(1,5).toString().equals("")||tbAPGAR.getValueAt(2,5).toString().equals("")||
                tbAPGAR.getValueAt(3,5).toString().equals("")||tbAPGAR.getValueAt(4,5).toString().equals("")){
                N5.setText("");
            }else{
                N5.setText((Valid.SetInteger(tbAPGAR.getValueAt(0,5).toString())+Valid.SetInteger(tbAPGAR.getValueAt(1,5).toString())+Valid.SetInteger(tbAPGAR.getValueAt(2,5).toString())+Valid.SetInteger(tbAPGAR.getValueAt(3,5).toString())+Valid.SetInteger(tbAPGAR.getValueAt(4,5).toString()))+"");
            }
        } catch (Exception e) {
            N5.setText("");
        }
        
        try {
            if(tbAPGAR.getValueAt(0,6).toString().equals("")||tbAPGAR.getValueAt(1,6).toString().equals("")||tbAPGAR.getValueAt(2,6).toString().equals("")||
                tbAPGAR.getValueAt(3,6).toString().equals("")||tbAPGAR.getValueAt(4,6).toString().equals("")){
                N10.setText("");
            }else{
                N10.setText((Valid.SetInteger(tbAPGAR.getValueAt(0,6).toString())+Valid.SetInteger(tbAPGAR.getValueAt(1,6).toString())+Valid.SetInteger(tbAPGAR.getValueAt(2,6).toString())+Valid.SetInteger(tbAPGAR.getValueAt(3,6).toString())+Valid.SetInteger(tbAPGAR.getValueAt(4,6).toString()))+"");
            }
        } catch (Exception e) {
            N10.setText("");
        }
    }
}
