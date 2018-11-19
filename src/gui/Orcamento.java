/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author thiagoalmeida
 */
public class Orcamento extends CustomJPanel {

    /**
     * Creates new form Orcamento
     */
    public Orcamento() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogPopUpEscolhaMes = new javax.swing.JDialog();
        jLabelTitle = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox<>();
        jComboBoxAno = new javax.swing.JComboBox<>();
        jButtonOk = new javax.swing.JButton();
        jLabelForwardSlash = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReceita = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaGastos = new javax.swing.JTextArea();
        jLabelDataPreview = new javax.swing.JLabel();
        jLabelReceitaPreview = new javax.swing.JLabel();
        jLabelGastosPreview = new javax.swing.JLabel();
        jLabelLineSplit = new javax.swing.JLabel();
        jLabelBalanço = new javax.swing.JLabel();
        jLabelSaldoFinal = new javax.swing.JLabel();
        jLabelBalançoValue = new javax.swing.JLabel();
        jLabelSaldoFinalValue = new javax.swing.JLabel();
        jButtonAtualizar = new javax.swing.JButton();
        jButtonMudarMês = new javax.swing.JButton();
        jButtonExportar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();

        jLabelTitle.setText("Qual mês você deseja vizualizar?");

        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBoxMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesActionPerformed(evt);
            }
        });

        jComboBoxAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2012", "2013", "2014", "2015", "2016", "2017", "2018" }));

        jButtonOk.setText("Ok");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jLabelForwardSlash.setText("/");

        javax.swing.GroupLayout jDialogPopUpEscolhaMesLayout = new javax.swing.GroupLayout(jDialogPopUpEscolhaMes.getContentPane());
        jDialogPopUpEscolhaMes.getContentPane().setLayout(jDialogPopUpEscolhaMesLayout);
        jDialogPopUpEscolhaMesLayout.setHorizontalGroup(
            jDialogPopUpEscolhaMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogPopUpEscolhaMesLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jDialogPopUpEscolhaMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogPopUpEscolhaMesLayout.createSequentialGroup()
                        .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelForwardSlash)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jButtonOk)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogPopUpEscolhaMesLayout.createSequentialGroup()
                        .addComponent(jLabelTitle)
                        .addGap(23, 23, 23))))
        );
        jDialogPopUpEscolhaMesLayout.setVerticalGroup(
            jDialogPopUpEscolhaMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogPopUpEscolhaMesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitle)
                .addGap(18, 18, 18)
                .addGroup(jDialogPopUpEscolhaMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogPopUpEscolhaMesLayout.createSequentialGroup()
                        .addGroup(jDialogPopUpEscolhaMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelForwardSlash))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogPopUpEscolhaMesLayout.createSequentialGroup()
                        .addComponent(jButtonOk)
                        .addContainerGap())))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setBorder(null);

        jTextAreaReceita.setColumns(20);
        jTextAreaReceita.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextAreaReceita.setRows(5);
        jTextAreaReceita.setText("+ Roubo: R$ 19.999,99\n+ Aplicações: R$ 0,01\n");
        jTextAreaReceita.setBorder(null);
        jScrollPane1.setViewportView(jTextAreaReceita);

        jScrollPane2.setBorder(null);

        jTextAreaGastos.setColumns(20);
        jTextAreaGastos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextAreaGastos.setRows(5);
        jTextAreaGastos.setText("- Água: R$ 932.33\n- Luz: R$ 2.342,23\n");
        jTextAreaGastos.setBorder(null);
        jScrollPane2.setViewportView(jTextAreaGastos);

        jLabelDataPreview.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabelDataPreview.setText("Orçamento Abril 2002");

        jLabelReceitaPreview.setText("Receita");

        jLabelGastosPreview.setText("Gastos");

        jLabelLineSplit.setText("__________________________________________");

        jLabelBalanço.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabelBalanço.setText("Balanço");
        jLabelBalanço.setToolTipText("");

        jLabelSaldoFinal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabelSaldoFinal.setText("Saldo Final");

        jLabelBalançoValue.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabelBalançoValue.setText("R$ 20.000,00");

        jLabelSaldoFinalValue.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabelSaldoFinalValue.setText("R$ 200.000,00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDataPreview)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabelSaldoFinal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSaldoFinalValue))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabelBalanço)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelBalançoValue))
                        .addComponent(jLabelLineSplit, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabelReceitaPreview)
                            .addGap(229, 229, 229))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelGastosPreview)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabelDataPreview)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelReceitaPreview)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGastosPreview)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLineSplit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBalanço)
                    .addComponent(jLabelBalançoValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSaldoFinal)
                    .addComponent(jLabelSaldoFinalValue))
                .addContainerGap())
        );

        jButtonAtualizar.setText("Atualizar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        jButtonMudarMês.setLabel("Mudar Mês");
        jButtonMudarMês.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMudarMêsActionPerformed(evt);
            }
        });

        jButtonExportar.setText("Exportar");
        jButtonExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonMudarMês, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonExportar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonMudarMês, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVoltar)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonMudarMêsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMudarMêsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonMudarMêsActionPerformed

    private void jButtonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonExportarActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void jComboBoxMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMesActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonOkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonExportar;
    private javax.swing.JButton jButtonMudarMês;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBoxAno;
    private javax.swing.JComboBox<String> jComboBoxMes;
    private javax.swing.JDialog jDialogPopUpEscolhaMes;
    private javax.swing.JLabel jLabelBalanço;
    private javax.swing.JLabel jLabelBalançoValue;
    private javax.swing.JLabel jLabelDataPreview;
    private javax.swing.JLabel jLabelForwardSlash;
    private javax.swing.JLabel jLabelGastosPreview;
    private javax.swing.JLabel jLabelLineSplit;
    private javax.swing.JLabel jLabelReceitaPreview;
    private javax.swing.JLabel jLabelSaldoFinal;
    private javax.swing.JLabel jLabelSaldoFinalValue;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaGastos;
    private javax.swing.JTextArea jTextAreaReceita;
    // End of variables declaration//GEN-END:variables

    public void addActionListener(ActionListener listener){
        this.jButtonAtualizar.addActionListener(listener);
        this.jButtonExportar.addActionListener(listener);
        this.jButtonMudarMês.addActionListener(listener);
        
        this.jButtonOk.addActionListener(listener);
        this.jButtonVoltar.addActionListener(listener);
    }

    public JButton getjButtonAtualizar() {
        return jButtonAtualizar;
    }

    public JButton getjButtonExportar() {
        return jButtonExportar;
    }

    public JButton getjButtonMudarMês() {
        return jButtonMudarMês;
    }

    public JButton getjButtonOk() {
        return jButtonOk;
    }

    public JButton getjButtonVoltar() {
        return jButtonVoltar;
    }
    
    public void setGastosPreview(String rawText){
        this.jTextAreaGastos.setText(rawText);
    }
    
    public void setReceitaPreview(String rawText){
        this.jTextAreaReceita.setText(rawText);
    }
    
    public void setBalançoPreview(String rawText){
        this.jLabelBalançoValue.setText(rawText);
    }
    
    public void setSaldoFinalPreview(String rawText){
        this.jLabelSaldoFinalValue.setText(rawText);
    }
    
    public String getSelectedMonthInPopUp(){
        return this.jComboBoxMes.getSelectedItem().toString();
    }
    
    public String getSelectedYearInPopUp(){
        return this.jComboBoxAno.getSelectedItem().toString();
    }
    
    public void setMonthsInPopUp(String[] meses){
        this.jComboBoxMes.removeAllItems();
        for(int i = 0;i<meses.length;i++){
            this.jComboBoxMes.addItem(meses[i]);
        }
        
    }
    
    public void setYearsInPopUp(String[] anos){
        this.jComboBoxAno.removeAllItems();
        for(int i = 0;i<anos.length;i++){
            this.jComboBoxAno.addItem(anos[i]);
        }
        
    }
}
