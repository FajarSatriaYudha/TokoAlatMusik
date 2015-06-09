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
import java.util.Scanner;
/**
 *
 * @author Debi
 */
public class FrgBrgMasuk extends javax.swing.JFrame {
private Connection con;
    private Statement stat;
    private ResultSet res;
private Object tabel;
    /**
     * Creates new form FrgBrgMasuk
     */
    public FrgBrgMasuk() {
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
SetEditOff();
TanggalOtomatis(); 
TampilComboBarang();
TampilComboPetugas();
TampilComboDistributor();
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
"tblbarang.stok,tbldetailbrgmasuk.jumlah,tbldetailbrgmasuk.subtotal,tblbrgmasuk.nonota " +
"FROM tblbarang,tbldetailbrgmasuk,tblbrgmasuk WHERE tblbarang.kodebarang=tbldetailbrgmasuk.kodebarang " +
"AND tblbrgmasuk.nonota='"+NoNota.getText()+"'";
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
String SQL = "SELECT tblbarang.kodebarang,tblbarang.namabarang,tblbarang.hargajual," + 
"tblbarang.stok,tbldetailbrgmasuk.jumlah,tbldetailbrgmasuk.subtotal,tblbrgmasuk.nonota " +
"FROM tblbarang,tbldetailbrgmasuk,tblbrgmasuk WHERE tblbarang.kodebarang=tbldetailbrgmasuk.kodebarang " +
"AND tblbrgmasuk.nonota=tbldetailbrgmasuk.nonota " +
"AND tbldetailbrgmasuk.nonota='"+NoNota.getText().toString()+"'";
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
NoNota.setText("");
IDDistributor.setSelectedIndex(0);
NamaPetugas.setText("");
IDPetugas.setSelectedIndex(0);
NamaDistributor.setText("");
KotaAsal.setText("");
KodeBarang.setSelectedIndex(0);
NamaBarang.setText(""); 
HargaJual.setText("");
Jumlah.setText("");
Stok.setText("");
SubTotal.setText("0");
Total.setText("0");
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
NoNota.setEnabled(false);
TglMasuk.setEnabled(false);
IDDistributor.setEnabled(false);
IDPetugas.setEnabled(false);
KodeBarang.setEnabled(false);
Jumlah.setEnabled(false);
Hitung.setEnabled(false);
CariData.setEnabled(false);
AddItem.setEnabled(false);
}
public void SetEditOn(){
NoNota.setEnabled(true);
TglMasuk.setEnabled(true);
IDDistributor.setEnabled(true);
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
public void TampilComboDistributor(){
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tbldistributor";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
IDDistributor.addItem(res.getString("iddistributor"));
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
TglMasuk.setText(""+ (String.format("%1$td:%1$tb:%1$tY",tanggal)));
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TglMasuk = new javax.swing.JTextField();
        NoNota = new javax.swing.JTextField();
        IDPetugas = new javax.swing.JComboBox();
        NamaPetugas = new javax.swing.JTextField();
        IDDistributor = new javax.swing.JComboBox();
        NamaDistributor = new javax.swing.JTextField();
        KotaAsal = new javax.swing.JTextField();
        CariData = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        KodeBarang = new javax.swing.JComboBox();
        NamaBarang = new javax.swing.JTextField();
        HargaJual = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Stok = new javax.swing.JTextField();
        Jumlah = new javax.swing.JTextField();
        Hitung = new javax.swing.JButton();
        SubTotal = new javax.swing.JTextField();
        AddItem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        AddNew = new javax.swing.JButton();
        SaveTransaction = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        Total = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setText("ENTRY DATA BARANG MASUK");

        jLabel2.setText("Tanggal Barang Masuk");

        jLabel3.setText("No Nota");

        jLabel4.setText("ID Petugas");

        jLabel5.setText("Nama Petugas");

        jLabel6.setText("ID Distributor");

        jLabel7.setText("Nama Distributor");

        jLabel8.setText("Kota Asal");

        IDPetugas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Petugas" }));
        IDPetugas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IDPetugasItemStateChanged(evt);
            }
        });
        IDPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDPetugasActionPerformed(evt);
            }
        });

        IDDistributor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Distributor" }));
        IDDistributor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IDDistributorItemStateChanged(evt);
            }
        });

        CariData.setText("Cari Data");
        CariData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariDataActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("Detail Barang");

        jLabel10.setText("Kode Barang");

        jLabel11.setText("Nama Barang");

        jLabel12.setText("Harga Jual");

        KodeBarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Data Barang" }));
        KodeBarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KodeBarangItemStateChanged(evt);
            }
        });

        jLabel13.setText("Rp");

        jLabel14.setText("Stok");

        jLabel15.setText("Jumlah");

        jLabel16.setText("Sub Total Rp");

        Jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumlahActionPerformed(evt);
            }
        });

        Hitung.setText("Hitung");
        Hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HitungActionPerformed(evt);
            }
        });

        SubTotal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SubTotalCaretUpdate(evt);
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

        SaveTransaction.setText("Save Transaksi");
        SaveTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveTransactionActionPerformed(evt);
            }
        });

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        jLabel17.setText("Total      Rp");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(AddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(178, 178, 178))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TglMasuk)
                                .addComponent(IDPetugas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(NamaPetugas)
                                .addComponent(IDDistributor, 0, 180, Short.MAX_VALUE)
                                .addComponent(NamaDistributor)
                                .addComponent(KotaAsal))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(NoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CariData))))
                    .addComponent(jLabel9)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(KodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NamaBarang)
                            .addComponent(HargaJual, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Hitung))
                            .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddNew)
                        .addGap(18, 18, 18)
                        .addComponent(SaveTransaction)
                        .addGap(18, 18, 18)
                        .addComponent(Close)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NoNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CariData))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(IDPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(NamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(IDDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(NamaDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(KotaAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(KodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(Stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Hitung))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(HargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16)
                    .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddNew)
                    .addComponent(SaveTransaction)
                    .addComponent(Close)
                    .addComponent(jLabel17)
                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahActionPerformed

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

    private void IDDistributorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IDDistributorItemStateChanged
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tbldistributor where iddistributor='"+ 
IDDistributor.getSelectedItem().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
NamaDistributor.setText(res.getString("namadistributor"));
KotaAsal.setText(res.getString("kotaasal")); 
} catch (SQLException ex) {
}
    }//GEN-LAST:event_IDDistributorItemStateChanged

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
double c; 
double d;
a = Integer.parseInt(HargaJual.getText()); 
b = Integer.parseInt(Jumlah.getText());
c = a * b; 
d = c;
SubTotal.setText(String.valueOf(c)); 
Total.setText(String.valueOf(d));
    }//GEN-LAST:event_HitungActionPerformed

    private void SubTotalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SubTotalCaretUpdate

    }//GEN-LAST:event_SubTotalCaretUpdate

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
SetEditOn();
NoNota.requestFocus();
BersihData();
    }//GEN-LAST:event_AddNewActionPerformed

    private void AddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemActionPerformed
String NM=NoNota.getText();
String KB=KodeBarang.getSelectedItem().toString();
String JM=Jumlah.getText();
String SB=SubTotal.getText();
if ((NM.isEmpty()) | (KB.isEmpty()) |(JM.isEmpty())) {
JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodeBarang.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String   SQL = "insert into tbldetailbrgmasuk values('"+NoNota.getText()+"',"+
"'"+KodeBarang.getSelectedItem()+"',"+
"'"+Jumlah.getText()+"',"+
"'"+SubTotal.getText()+"')";
stt.executeUpdate(SQL);
Class.forName("com.mysql.jdbc.Driver");
Connection kon1 = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt1 = kon.createStatement();
String SQL1 = "Update tblbarang Set stok=stok + '"+Jumlah.getText()+"'" +
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
//TampilGrid();
KodeBarang.requestFocus();
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
}
    }//GEN-LAST:event_AddItemActionPerformed

    private void SaveTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveTransactionActionPerformed
String NM=NoNota.getText();
if ((NM.isEmpty())) {
JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
NoNota.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "insert into tblbrgmasuk values('"+NoNota.getText()+"',"+
"'"+TglMasuk.getText()+"',"+
"'"+IDDistributor.getSelectedItem()+"',"+
"'"+IDPetugas.getSelectedItem()+"',"+
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
if(JOptionPane.showConfirmDialog(null,"This application will be close \n if you press button OK",
        "Information", JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION)
this.dispose();
    }//GEN-LAST:event_CloseActionPerformed

    private void CariDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariDataActionPerformed
try {
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tblbrgmasuk where nonota='"+NoNota.getText().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
TglMasuk.setText(res.getString("tglmasuk"));
IDPetugas.setSelectedItem(res.getString("idpetugas"));
IDDistributor.setSelectedItem(res.getString("iddistributor"));
Total.setText(res.getString("total"));
TampilGridDetail();
SaveTransaction.setEnabled(false);
NoNota.setEnabled(false);
CariData.setEnabled(false);
} catch (SQLException ex) {
} 
    }//GEN-LAST:event_CariDataActionPerformed

    private void IDPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDPetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPetugasActionPerformed

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
            java.util.logging.Logger.getLogger(FrgBrgMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrgBrgMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrgBrgMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrgBrgMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrgBrgMasuk().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddItem;
    private javax.swing.JButton AddNew;
    private javax.swing.JButton CariData;
    private javax.swing.JButton Close;
    private javax.swing.JTextField HargaJual;
    private javax.swing.JButton Hitung;
    private javax.swing.JComboBox IDDistributor;
    private javax.swing.JComboBox IDPetugas;
    private javax.swing.JTextField Jumlah;
    private javax.swing.JComboBox KodeBarang;
    private javax.swing.JTextField KotaAsal;
    private javax.swing.JTextField NamaBarang;
    private javax.swing.JTextField NamaDistributor;
    private javax.swing.JTextField NamaPetugas;
    private javax.swing.JTextField NoNota;
    private javax.swing.JButton SaveTransaction;
    private javax.swing.JTextField Stok;
    private javax.swing.JTextField SubTotal;
    private javax.swing.JTable Table;
    private javax.swing.JTextField TglMasuk;
    private javax.swing.JTextField Total;
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
