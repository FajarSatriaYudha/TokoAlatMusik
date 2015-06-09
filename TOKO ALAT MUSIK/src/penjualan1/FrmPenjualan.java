/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan1;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author Debi
 */
public class FrmPenjualan extends javax.swing.JFrame {
private Connection con;
    private Statement stat;
    private ResultSet res;
private Object tabel;
    /**
     * Creates new form FrmPenjualan
     */
    public FrmPenjualan() {
        initComponents();
        koneksi();
Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
Dimension frameSize=this.getSize();
if(frameSize.height > screenSize.height){
frameSize.height=screenSize.height;
}
if(frameSize.width > screenSize.width){
frameSize.width=screenSize.width;
}
this.setLocation((screenSize.width - frameSize.width) / 2,
(screenSize.height = screenSize.height) / 10);
Table.setModel(tableModel);
Tabel(Table, new int[]{90,300,90,60,60,90});
setDefaultTable();
TanggalOtomatis();
SetEditOff();
TampilComboBarang();
TampilComboPetugas();
    }
private void koneksi(){
    try{
       Class.forName("com.mysql.jdbc.Driver");
       con=DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
       stat=con.createStatement();
   } catch (Exception e){
       JOptionPane.showMessageDialog(null, e);
   }
    }
private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
private void Tabel(javax.swing.JTable tb, int lebar[] ) {
tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
int kolom=tb.getColumnCount();
for(int i=0;i < kolom;i++) {
javax.swing.table.TableColumn tbc=tb.getColumnModel().getColumn(i);
tbc.setPreferredWidth(lebar[i]);
tb.setRowHeight(17);
}
}
private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
return new javax.swing.table.DefaultTableModel(
new Object[][] {},
new String [] {"Kode Barang","Nama Barang","Harga Jual","Stok","Jumlah","Sub Total"}
){
boolean[] canEdit = new boolean[]{
false, false, false, false
};
public boolean isCellEditable(int rowIndex, int columnIndex){ 
return canEdit[columnIndex];
}
}; 
}
String data[]=new String[6];
private void setDefaultTable() {
String stat ="";
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "SELECT tblbarang.kodebarang,tblbarang.namabarang,tblbarang.hargajual," + 
"tblbarang.stok,tbldetailpenjualan.jumlah,tbldetailpenjualan.subtotal,tblpenjualan.nofaktur " +
"FROM tblbarang,tbldetailpenjualan,tblpenjualan WHERE tblbarang.kodebarang=tbldetailpenjualan.kodebarang " +
"AND tblpenjualan.nofaktur='"+NoFaktur.getText()+"'";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
data[0] = res.getString(1);
data[1] = res.getString(2);
data[2] = res.getString(3);
data[3] = res.getString(4);
data[4] = res.getString(5);
data[5] = res.getString(6);
tableModel.addRow(data);
}
res.close();
stt.close();
kon.close();
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
}
private void TampilGridDetail(){
String stat ="";
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "SELECT tblbarang.kodebarang,tblbarang.namabarang,tblbarang.hargajual," 
+"tblbarang.stok,tbldetailpenjualan.jumlah,tbldetailpenjualan.subtotal,tblpenjualan.nofaktur " +
"FROM tblbarang, tbldetailpenjualan, tblpenjualan " +
"WHERE tblbarang.kodebarang=tbldetailpenjualan.kodebarang " +
"AND tblpenjualan.nofaktur=tbldetailpenjualan.nofaktur " +
"AND tbldetailpenjualan.nofaktur='" + NoFaktur.getText().toString() + "'";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
data[0] = res.getString(1);
data[1] = res.getString(2);
data[2] = res.getString(3);
data[3] = res.getString(4);
data[4] = res.getString(5);
data[5] = res.getString(6);
tableModel.addRow(data);
}
res.close();
stt.close();
kon.close();
} catch (Exception ex) {
System.err.println(ex.getMessage());
} 
} 
public void BersihData(){
tableModel.setRowCount(0);
NoFaktur.setText("");
NamaPetugas.setText("");
IDPetugas.setSelectedIndex(0);
KodeBarang.setSelectedIndex(0);
NamaBarang.setText("");  
HargaJual.setText("");
Jumlah.setText("");
Stok.setText("");
Bayar.setText("0");
Sisa.setText("0");
Total.setText("0");
SubTotal.setText("0");
}
public void BersihDetail(){
KodeBarang.setSelectedIndex(0);
NamaBarang.setText(""); 
HargaJual.setText("");
Stok.setText("");
Jumlah.setText("");
SubTotal.setText("0");
}
public void SetEditOff(){
NoFaktur.setEnabled(false);
TglPenjualan.setEnabled(false);
IDPetugas.setEnabled(false);
KodeBarang.setEnabled(false);
Jumlah.setEnabled(false);
Hitung.setEnabled(false);
CariData.setEnabled(false);
AddItem.setEnabled(false);
}
public void SetEditOn(){
NoFaktur.setEnabled(true);
TglPenjualan.setEnabled(true);
IDPetugas.setEnabled(true);
KodeBarang.setEnabled(true);
Jumlah.setEnabled(true);
SaveTransaction.setEnabled(true);
CariData.setEnabled(true);
Hitung.setEnabled(true);
AddItem.setEnabled(true);
}

public void TampilComboPetugas(){
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tblpetugas";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
IDPetugas.addItem(res.getString("idpetugas"));
}
} catch (SQLException ex) {
}
}
public void TampilComboBarang(){
try {   
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tblbarang";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
KodeBarang.addItem(res.getString("kodebarang"));
}
} catch (SQLException ex) {
}
} 
public void TanggalOtomatis(){
Date tanggal = new Date();
TglPenjualan.setText(""+ (String.format("%1$td:%1$tb:%1$tY",tanggal)));
}  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NoFaktur = new javax.swing.JTextField();
        IDPetugas = new javax.swing.JComboBox();
        NamaPetugas = new javax.swing.JTextField();
        TglPenjualan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        KodeBarang = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        NamaBarang = new javax.swing.JTextField();
        HargaJual = new javax.swing.JTextField();
        Stok = new javax.swing.JTextField();
        Jumlah = new javax.swing.JTextField();
        Hitung = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        SubTotal = new javax.swing.JTextField();
        AddItem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        AddNew = new javax.swing.JButton();
        SaveTransaction = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Bayar = new javax.swing.JTextField();
        Sisa = new javax.swing.JTextField();
        Total = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        CariData = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("No Faktur");

        jLabel2.setText("ID Petugas");

        jLabel3.setText("Nama Petugas");

        jLabel4.setText("Tanggal Penjualan");

        IDPetugas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Data Petugas" }));
        IDPetugas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IDPetugasItemStateChanged(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Data Barang");

        jLabel6.setText("Kode Barang");

        KodeBarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Data Barang" }));
        KodeBarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KodeBarangItemStateChanged(evt);
            }
        });

        jLabel7.setText("Nama Barang");

        jLabel8.setText("Harga Jual");

        jLabel9.setText("Stok");

        jLabel10.setText("Jumlah");

        Hitung.setText("Hitung");
        Hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HitungActionPerformed(evt);
            }
        });

        jLabel11.setText("Sub Total    Rp");

        SubTotal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SubTotalCaretUpdate(evt);
            }
        });
        SubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubTotalActionPerformed(evt);
            }
        });

        AddItem.setText("Add Item");
        AddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddItemActionPerformed(evt);
            }
        });

        Table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Table);

        AddNew.setText("Add New");
        AddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewActionPerformed(evt);
            }
        });

        SaveTransaction.setText("Save Transaction");
        SaveTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveTransactionActionPerformed(evt);
            }
        });

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        jLabel12.setText("Bayar   Rp");

        jLabel13.setText("Sisa  Rp");

        jLabel14.setText("Total   RP");

        Bayar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                BayarCaretUpdate(evt);
            }
        });

        Sisa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SisaCaretUpdate(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(0, 51, 204));
        jLabel15.setText("Grid Data Barang");

        CariData.setText("Cari Data");
        CariData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariDataActionPerformed(evt);
            }
        });

        jButton1.setText("Cek");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(NoFaktur)
                                    .addComponent(IDPetugas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(NamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(CariData, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TglPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(KodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(NamaBarang)
                                        .addComponent(HargaJual, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                        .addComponent(Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Hitung)
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddNew)
                                .addGap(36, 36, 36)
                                .addComponent(SaveTransaction)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cancel)
                                .addGap(55, 55, 55)
                                .addComponent(Close))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Total))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(18, 18, 18)
                                    .addComponent(Sisa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(25, 25, 25)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(NoFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TglPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CariData))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(IDPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(KodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(HargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(Stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Hitung)
                    .addComponent(jLabel11)
                    .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AddItem)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddNew)
                    .addComponent(SaveTransaction)
                    .addComponent(Cancel)
                    .addComponent(Close)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KodeBarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_KodeBarangItemStateChanged
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tblbarang where kodebarang='"+ 
KodeBarang.getSelectedItem().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
NamaBarang.setText(res.getString("namabarang"));
HargaJual.setText(res.getString("hargajual"));
Stok.setText(res.getString("stok"));
} catch (SQLException ex) {
}
Jumlah.requestFocus();
AddItem.setEnabled(true);
    }//GEN-LAST:event_KodeBarangItemStateChanged

    private void HitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HitungActionPerformed
int a;
int b;
int c;
int d;
a = Integer.parseInt(HargaJual.getText()); 
b = Integer.parseInt(Jumlah.getText());
c = a * b;
d = c ;
SubTotal.setText(String.valueOf(c));
Total.setText(String.valueOf(d));
    }//GEN-LAST:event_HitungActionPerformed

    private void SubTotalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SubTotalCaretUpdate

    }//GEN-LAST:event_SubTotalCaretUpdate

    private void BayarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_BayarCaretUpdate

    }//GEN-LAST:event_BayarCaretUpdate

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
SetEditOn();
NoFaktur.requestFocus();
BersihData();
    }//GEN-LAST:event_AddNewActionPerformed

    private void AddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemActionPerformed
String NM=NoFaktur.getText();
String KB=KodeBarang.getSelectedItem().toString();
String JM=Jumlah.getText();
if ((NM.isEmpty()) | (KB.isEmpty()) |(JM.isEmpty())) {
JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodeBarang.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "insert into tbldetailpenjualan values('"+NoFaktur.getText()+"',"+
"'"+KodeBarang.getSelectedItem()+"',"+
"'"+Jumlah.getText()+"',"+
"'"+SubTotal.getText()+"')";
stt.executeUpdate(SQL);
Class.forName("com.mysql.jdbc.Driver");
Connection kon1 = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt1 = kon.createStatement();
String SQL1 = "Update tblbarang Set stok=stok - '"+Jumlah.getText()+"'" +
"Where kodebarang='"+KodeBarang.getSelectedItem().toString()+"'";
stt1.executeUpdate(SQL1);
data[0] = KodeBarang.getSelectedItem().toString();
data[1] = NamaBarang.getText(); 
data[2] = HargaJual.getText();
data[3] = Stok.getText();
data[4] = Jumlah.getText();
data[5] = SubTotal.getText();
tableModel.insertRow(0, data);
stt.close();
kon.close();
KodeBarang.requestFocus();
AddItem.setEnabled(false);
BersihDetail();
KodeBarang.requestFocus();
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
}
    }//GEN-LAST:event_AddItemActionPerformed

    private void SaveTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveTransactionActionPerformed
String NF=NoFaktur.getText();
if ((NF.isEmpty())) {
JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
NoFaktur.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "insert into tblpenjualan values('"+NoFaktur.getText()+"',"+
"'"+TglPenjualan.getText()+"',"+
"'"+IDPetugas.getSelectedItem()+"',"+
"'"+Bayar.getText()+"',"+
"'"+Sisa.getText()+"',"+
"'"+Total.getText()+"')"; 
stt.executeUpdate(SQL);
stt.close(); 
kon.close();
BersihData();
SetEditOff();
SaveTransaction.setEnabled(false);
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
}
    }//GEN-LAST:event_SaveTransactionActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
if(JOptionPane.showConfirmDialog(null,"This application will be close \n if you press button OK","Information", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION)
this.dispose();
    }//GEN-LAST:event_CloseActionPerformed

    private void CariDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariDataActionPerformed
try {
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tblpenjualan where nofaktur='"+NoFaktur.getText().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
TampilGridDetail();
TglPenjualan.setText(res.getString("tglpenjualan"));
IDPetugas.setSelectedItem(res.getString("idpetugas"));
Bayar.setText(res.getString("bayar"));
Sisa.setText(res.getString("sisa"));
Total.setText(res.getString("total"));
SaveTransaction.setEnabled(false);
NoFaktur.setEnabled(false); 
CariData.setEnabled(false);
} catch (SQLException ex) {
}
    }//GEN-LAST:event_CariDataActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
BersihData();
SetEditOff();
    }//GEN-LAST:event_CancelActionPerformed

    private void IDPetugasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IDPetugasItemStateChanged
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tblpetugas where idpetugas='"+ 
IDPetugas.getSelectedItem().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
NamaPetugas.setText(res.getString("namapetugas"));
} catch (SQLException ex) {
}
    }//GEN-LAST:event_IDPetugasItemStateChanged

    private void SubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubTotalActionPerformed

    private void SisaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SisaCaretUpdate

    }//GEN-LAST:event_SisaCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
int g;
int h;
int i;
g = Integer.parseInt(Total.getText()); 
h = Integer.parseInt(Bayar.getText());
i = h - g; 
Sisa.setText(String.valueOf(i));
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPenjualan().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddItem;
    private javax.swing.JButton AddNew;
    private javax.swing.JTextField Bayar;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton CariData;
    private javax.swing.JButton Close;
    private javax.swing.JTextField HargaJual;
    private javax.swing.JButton Hitung;
    private javax.swing.JComboBox IDPetugas;
    private javax.swing.JTextField Jumlah;
    private javax.swing.JComboBox KodeBarang;
    private javax.swing.JTextField NamaBarang;
    private javax.swing.JTextField NamaPetugas;
    private javax.swing.JTextField NoFaktur;
    private javax.swing.JButton SaveTransaction;
    private javax.swing.JTextField Sisa;
    private javax.swing.JTextField Stok;
    private javax.swing.JTextField SubTotal;
    private javax.swing.JTable Table;
    private javax.swing.JTextField TglPenjualan;
    private javax.swing.JTextField Total;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
