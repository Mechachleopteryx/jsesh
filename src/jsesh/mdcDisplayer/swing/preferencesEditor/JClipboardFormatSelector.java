/*
 * This file is distributed under the LGPL
 * Author: Serge Rosmorduc
 */

/*
 * JClipboardFormatSelector.java
 *
 * Created on 10 avr. 2009, 10:21:00
 */

package jsesh.mdcDisplayer.swing.preferencesEditor;

import javax.swing.JCheckBox;

/**
 *
 * @author rosmord
 */
public class JClipboardFormatSelector extends javax.swing.JPanel {

    /** Creates new form JClipboardFormatSelector */
    public JClipboardFormatSelector() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rtfCheckBox = new javax.swing.JCheckBox();
        pdfCheckBox = new javax.swing.JCheckBox();
        bitmapCheckBox = new javax.swing.JCheckBox();
        plainTextCheckBox = new javax.swing.JCheckBox();

        setOpaque(false);

        jLabel1.setText("Select the formats wich will be used for cut and paste. ");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        jLabel2.setText("Some of them might not work on your particular machine");

        rtfCheckBox.setText("rtf");

        pdfCheckBox.setText("pdf (works on mac, only with some softwares)");

        bitmapCheckBox.setText("bitmap pictures");

        plainTextCheckBox.setText("plain text");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(rtfCheckBox)
                    .add(pdfCheckBox)
                    .add(bitmapCheckBox))
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap(20, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap(20, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(plainTextCheckBox)
                .addContainerGap(369, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel2)
                .add(18, 18, 18)
                .add(rtfCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pdfCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(bitmapCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(plainTextCheckBox)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox bitmapCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox pdfCheckBox;
    private javax.swing.JCheckBox plainTextCheckBox;
    private javax.swing.JCheckBox rtfCheckBox;
    // End of variables declaration//GEN-END:variables

    public JCheckBox getBitmapCheckBox() {
        return bitmapCheckBox;
    }

    public JCheckBox getPdfCheckBox() {
        return pdfCheckBox;
    }

    public JCheckBox getPlainTextCheckBox() {
        return plainTextCheckBox;
    }

    public JCheckBox getRtfCheckBox() {
        return rtfCheckBox;
    }

    
}
