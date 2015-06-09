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
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Debi
 */
public class FrmBarang extends javax.swing.JFrame {
private Connection con;
    private Statement stat;
    private ResultSet res;
private Object tabel;
    /**
     * Creates new form FrmBarang
     */
    public FrmBarang() {
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
(screenSize.height = screenSize.height) / 6);
Table.setModel(tableModel);
Tabel(Table, new int[]{90,200,90,90,90,70});
setDefaultTable();
SetEditOff();
TampilComboJenis();
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
new String [] {"Kode Barang","Nama Barang","Kode Jenis","Harga NET","Harga Jual","Stok"}
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
String SQL = "SELECT * FROM tblbarang";
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
int row = 0;
public void Tampil(){
row = Table.getSelectedRow();
KodeBarang.setText(tableModel.getValueAt(row, 0).toString());
NamaBarang.setText(tableModel.getValueAt(row, 1).toString());
KodeJenis.setSelectedItem(tableModel.getValueAt(row, 2).toString());
HargaNet.setText(tableModel.getValueAt(row, 3).toString());
HargaJual.setText(tableModel.getValueAt(row, 4).toString());
Stok.setText(tableModel.getValueAt(row, 5).toString());
Save.setEnabled(false);
Update.setEnabled(true);
Delete.setEnabled(true);
SetEditOn();
} 
public void BersihData(){
KodeBarang.setText("");
NamaBarang.setText(""); 
KodeJenis.setSelectedIndex(0);
HargaNet.setText("");
HargaJual.setText("");
Stok.setText("0");
}
public void SetEditOff(){
KodeBarang.setEnabled(false);
NamaBarang.setEnabled(false);
KodeJenis.setEnabled(false);
HargaNet.setEnabled(false);
HargaJual.setEnabled(false);
Stok.setEnabled(false);
}
public void SetEditOn(){
KodeBarang.setEnabled(true);
NamaBarang.setEnabled(true);
KodeJenis.setEnabled(true);
HargaNet.setEnabled(true);
HargaJual.setEnabled(true);
Stok.setEnabled(true);
}
public void TampilComboJenis(){
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement  stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tbljenis";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
KodeJenis.addItem(res.getString("kodejenis"));
}
} catch (SQLException ex) {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        KodeBarang = new javax.swing.JTextField();
        NamaBarang = new javax.swing.JTextField();
        KodeJenis = new javax.swing.JComboBox();
        HargaNet = new javax.swing.JTextField();
        HargaJual = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        AddNew = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        Stok = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Kode Barang");

        jLabel2.setText("Nama Barang");

        jLabel3.setText("Kode Jenis Barang");

        jLabel5.setText("HargaNet");

        jLabel6.setText("Harga Jual");

        KodeJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pilih kode jenis barang" }));
        KodeJenis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KodeJenisItemStateChanged(evt);
            }
        });
        KodeJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KodeJenisActionPerformed(evt);
            }
        });

        HargaJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HargaJualActionPerformed(evt);
            }
        });

        jLabel4.setText("Rp");

        jLabel7.setText("Rp");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel8.setText("ENTRY DATA BARANG");

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
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        AddNew.setText("Add New");
        AddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewActionPerformed(evt);
            }
        });

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
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

        jLabel9.setText("Stok");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(KodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                            .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel9))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(HargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel7)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(HargaNet, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4))
                                    .addComponent(KodeJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Save)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Update)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Delete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Close)))
                        .addContainerGap(144, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(KodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(KodeJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(HargaNet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddNew)
                    .addComponent(Save)
                    .addComponent(Update)
                    .addComponent(Delete)
                    .addComponent(Cancel)
                    .addComponent(Close))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HargaJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HargaJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HargaJualActionPerformed

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
BersihData();
KodeBarang.requestFocus();
Save.setEnabled(true);
Update.setEnabled(false);
Delete.setEnabled(false);
SetEditOn();
    }//GEN-LAST:event_AddNewActionPerformed

    private void KodeJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KodeJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeJenisActionPerformed

    private void KodeJenisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_KodeJenisItemStateChanged
try { 
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
ResultSet.CONCUR_UPDATABLE);
String SQL = "SELECT * FROM tbljenis where kodejenis='"+ KodeJenis.getSelectedItem().toString()+"'";
ResultSet res = stt.executeQuery(SQL);
res.absolute(1);
} catch (SQLException ex) {
}
    }//GEN-LAST:event_KodeJenisItemStateChanged

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
String KB=KodeBarang.getText();
String NB=NamaBarang.getText();
String KJ=KodeJenis.getSelectedItem().toString(); 
String HN=HargaNet.getText();
String HJ=HargaJual.getText();
String ST=Stok.getText();
if ((KB.isEmpty()) | (NB.isEmpty()) |(KJ.isEmpty()) |(HN.isEmpty())|(HJ.isEmpty())|(ST.isEmpty())) {
JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodeBarang.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "insert into tblbarang values('"+KodeBarang.getText()+"',"+
"'"+NamaBarang.getText()+"',"+
"'"+KodeJenis.getSelectedItem()+"',"+
"'"+HargaNet.getText()+"',"+
"'"+HargaJual.getText()+"',"+
"'"+Stok.getText()+"')";
stt.executeUpdate(SQL);
data[0] = KodeBarang.getText();
data[1] = NamaBarang.getText();
data[2] = KodeJenis.getSelectedItem().toString();
data[3] = HargaNet.getText();
data[4] = HargaJual.getText();
data[5] = Stok.getText();
tableModel.insertRow(0, data);
stt.close();
kon.close();
BersihData();
Save.setEnabled(false);
SetEditOff();
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
}
    }//GEN-LAST:event_SaveActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
if (evt.getClickCount()==1) {
Tampil();
} 
    }//GEN-LAST:event_TableMouseClicked

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
String KB=KodeBarang.getText();
String NB=NamaBarang.getText();
String KJ=KodeJenis.getSelectedItem().toString();
String HN=HargaNet.getText();
String HJ=HargaJual.getText();
String ST=Stok.getText();
if ((KB.isEmpty()) | (NB.isEmpty()) |(KJ.isEmpty()) |(HN.isEmpty())|(HJ.isEmpty())|(ST.isEmpty())) {
JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodeBarang.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "Update tblbarang Set namabarang='"+NamaBarang.getText()+"',"+
"kodejenis='"+KodeJenis.getSelectedItem()+"',"+
"harganet='"+HargaNet.getText()+"',"+
"hargajual='"+HargaJual.getText()+"',"+
"stok='"+Stok.getText()+"'"+
"Where kodebarang='"+KodeBarang.getText()+"'";
stt.executeUpdate(SQL);
data[0] = KodeBarang.getText();
data[1] = NamaBarang.getText();
data[2] = KodeJenis.getSelectedItem().toString();
data[3] = HargaNet.getText();
data[4] = HargaJual.getText(); 
data[5] = Stok.getText();
tableModel.removeRow(row);
tableModel.insertRow(row,data);
stt.close();
kon.close();
BersihData();
Save.setEnabled(false);
SetEditOff();
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
}
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
String KB=KodeBarang.getText();
String NB=NamaBarang.getText();
String KJ=KodeJenis.getSelectedItem().toString();
String HN=HargaNet.getText();
String HJ=HargaJual.getText();
String ST=Stok.getText();
if ((KB.isEmpty()) | (NB.isEmpty()) |(KJ.isEmpty()) |(HN.isEmpty())|(HJ.isEmpty())|(ST.isEmpty())) {
JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodeBarang.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "Delete From tblbarang Where kodebarang='"+KodeBarang.getText().toString()+"'";
stt.executeUpdate(SQL);
data[0] = KodeBarang.getText();
data[1] = NamaBarang.getText();
data[2] = KodeJenis.getSelectedItem().toString();
data[3] = HargaNet.getText(); 
data[4] = HargaJual.getText();
data[5] = Stok.getText();
tableModel.removeRow(row);
stt.close();
kon.close();
BersihData();
Save.setEnabled(false);
SetEditOff();
} catch (Exception ex) {
System.err.println(ex.getMessage());
}
}
    }//GEN-LAST:event_DeleteActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
if(JOptionPane.showConfirmDialog(null,"This application will be close \n if you press button OK","Information", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION)
this.dispose();
    }//GEN-LAST:event_CloseActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
BersihData();
SetEditOff();
    }//GEN-LAST:event_CancelActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBarang().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNew;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Close;
    private javax.swing.JButton Delete;
    private javax.swing.JTextField HargaJual;
    private javax.swing.JTextField HargaNet;
    private javax.swing.JTextField KodeBarang;
    private javax.swing.JComboBox KodeJenis;
    private javax.swing.JTextField NamaBarang;
    private javax.swing.JButton Save;
    private javax.swing.JTextField Stok;
    private javax.swing.JTable Table;
    private javax.swing.JButton Update;
    private javax.swing.JLabel jLabel1;
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
