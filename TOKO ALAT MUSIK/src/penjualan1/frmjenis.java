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
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author Debi
 */
public class frmjenis extends javax.swing.JFrame {
    private Connection con;
    private Statement stat;
    private ResultSet res;
private Object tabel;
    /**
     * Creates new form frmjenis
     */
    public frmjenis() {
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
(screenSize.height = screenSize.height) / 4);
Table.setModel(tableModel);
Tabel(Table, new int[]{90,270});
setDefaultTable();
SetEditOff();
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
new String [] {"Kode Jenis","Jenis Barang"}
){
boolean[] canEdit = new boolean[]{
false, false, false, false
};
public boolean isCellEditable(int rowIndex, int columnIndex){
return canEdit[columnIndex];
}
}; 
}
String data[]=new String[2];
private void setDefaultTable() {
String stat ="";
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "SELECT * FROM tbljenis";
ResultSet res = stt.executeQuery(SQL);
while(res.next()){
data[0] = res.getString(1);
data[1] = res.getString(2);
tableModel.addRow(data);
}
res.close();
stt.close();
con.close();
} catch (Exception ex) { 
System.err.println(ex.getMessage());
}
}
int row = 0;
public void Tampil(){
row = Table.getSelectedRow();
KodeJenis.setText(tableModel.getValueAt(row, 0).toString());
Jenis.setText(tableModel.getValueAt(row, 1).toString());
Save.setEnabled(false);
Update.setEnabled(true);
Delete.setEnabled(true);
SetEditOn();
}
public void BersihData(){
KodeJenis.setText("");
Jenis.setText(""); 
}
public void SetEditOff(){
KodeJenis.setEnabled(false);
Jenis.setEnabled(false);
}
public void SetEditOn(){
KodeJenis.setEnabled(true);
Jenis.setEnabled(true);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        KodeJenis = new javax.swing.JTextField();
        Jenis = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        AddNew = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Kode Jenis");

        jLabel2.setText("Jenis");

        KodeJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KodeJenisActionPerformed(evt);
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

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel3.setText("ENTRY DATA JENIS BARANG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AddNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Close))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(KodeJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(KodeJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(Jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddNew)
                    .addComponent(Save)
                    .addComponent(Update)
                    .addComponent(Delete)
                    .addComponent(Cancel)
                    .addComponent(Close))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KodeJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KodeJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeJenisActionPerformed

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
BersihData();
KodeJenis.requestFocus();
Save.setEnabled(true);
Update.setEnabled(false);
Delete.setEnabled(false);
SetEditOn();
    }//GEN-LAST:event_AddNewActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
String KJ=KodeJenis.getText();
String J=Jenis.getText();
if ((KJ.isEmpty()) | (J.isEmpty()))
{JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodeJenis.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "insert into tbljenis values(' "+KodeJenis.getText()+" '  , "+
" '  "+Jenis.getText()+"')";
stt.executeUpdate(SQL);
data[0] = KodeJenis.getText();
data[1] = Jenis.getText();
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
String KJ=KodeJenis.getText();
String J=Jenis.getText();
if ((KJ.isEmpty()) | (J.isEmpty()))
{JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodeJenis.requestFocus();
}else {
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "Update tbljenis set jenis=' "+Jenis.getText()+" ' "+
"Where kodejenis=' "+KodeJenis.getText()+" '  "; 
stt.executeUpdate(SQL);
data[0] = KodeJenis.getText();
data[1] = Jenis.getText();
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
String KJ=KodeJenis.getText();
String J=Jenis.getText();
if ((KJ.isEmpty()) | (J.isEmpty()))
{JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
KodeJenis.requestFocus();
}else { 
try {
Class.forName("com.mysql.jdbc.Driver");
Connection kon = DriverManager.getConnection(""+"jdbc:mysql://localhost/penjualan","root","");
Statement stt = kon.createStatement();
String SQL = "Delete From tbljenis Where kodejenis='"+KodeJenis.getText().toString()+"'";
stt.executeUpdate(SQL);
data[0] = KodeJenis.getText();
data[1] = Jenis.getText();
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
if(JOptionPane.showConfirmDialog(null,"This application will be close  \n if you press button OK",
"Information",JOptionPane.OK_CANCEL_OPTION,
JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION)
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
            java.util.logging.Logger.getLogger(frmjenis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmjenis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmjenis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmjenis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmjenis().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNew;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Close;
    private javax.swing.JButton Delete;
    private javax.swing.JTextField Jenis;
    private javax.swing.JTextField KodeJenis;
    private javax.swing.JButton Save;
    private javax.swing.JTable Table;
    private javax.swing.JButton Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
