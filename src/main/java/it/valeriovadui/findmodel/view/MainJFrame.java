/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainJFrame.java
 *
 * Created on 14-mar-2012, 17.43.22
 */
package it.valeriovadui.findmodel.view;

import it.valeriovadui.findmodel.control.OperatorControl;
import it.valeriovadui.findmodel.factory.ParserFactory;
import it.valeriovadui.findmodel.service.Parser;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.valeriovadui.findmodel.model.Expression;
import it.valeriovadui.findmodel.model.Formula;

/**
 *
 * @author mrFlick72
 */
public class MainJFrame extends javax.swing.JFrame {

    /** Creates new form MainJFrame */
    public MainJFrame() {
        
        initComponents();
        
        setTitle("Resolution's formulas propositional logic");
        Dimension dim = getToolkit().getScreenSize();
        this.setLocation((dim.width-this.getWidth())/2,(dim.height-this.getHeight())/2);
        
        jButtonAnd.setText("\u02C4");
        jButtonOr.setText("\u02C5");
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButtonEval = new javax.swing.JButton();
        jButtonAnd = new javax.swing.JButton();
        jButtonOr = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldSorgente = new javax.swing.JTextField();
        jTextFieldDestinazione = new javax.swing.JTextField();
        jScrollPaneAmbiente = new javax.swing.JScrollPane();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButtonEval.setText("Evaluate");
        jButtonEval.setFocusable(false);
        jButtonEval.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEval.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEval.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonEvalMousePressed(evt);
            }
        });
        jToolBar1.add(jButtonEval);

        jButtonAnd.setText("\\u02C4");
        jButtonAnd.setToolTipText("and");
        jButtonAnd.setFocusable(false);
        jButtonAnd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAnd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAnd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAndMousePressed(evt);
            }
        });
        jToolBar1.add(jButtonAnd);

        jButtonOr.setText("\\u02C5");
        jButtonOr.setFocusable(false);
        jButtonOr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonOr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonOr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonOrMousePressed(evt);
            }
        });
        jToolBar1.add(jButtonOr);

        jButton5.setText("=>");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jTextFieldSorgente.setToolTipText("Inserire la formula ben formata da valutare");
        jTextFieldSorgente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSorgenteKeyReleased(evt);
            }
        });

        jTextFieldDestinazione.setToolTipText("Status dell parser");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPaneAmbiente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addComponent(jTextFieldSorgente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addComponent(jTextFieldDestinazione, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jTextFieldSorgente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jTextFieldDestinazione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneAmbiente, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEvalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEvalMousePressed
        // TODO add your handling code here:
        String source = jTextFieldSorgente.getText().trim();
        Parser parse = ParserFactory.getSimParserParser(source);
        formula = parse.parse();
        
        try{
          
        jTextFieldDestinazione.setText("Il parser della stringa è avvenuto correttamente");
        
        variables = formula.getAllVariable();
  
        table = new ModelTable(OperatorControl.checkModel(formula, variables), variables);
      
        jScrollPaneAmbiente.setViewportView(table);
        
        }
        catch(NullPointerException ex){
            
        jTextAreaResult = new JTextArea();
             
       jTextFieldDestinazione.setText("Il parser della stringa ha generato un errore");
       jTextAreaResult.setText("Il parser della stringa ha generato un errore\n"
               + "prego controllare che la stringa passata in input sia ben formata e quindi che:\n"
               + "1) dopo ogni parentesi ci sia uno spazio\n"
               + "2) ussre la barra degli strumenti per generare gli operatori\n"
               + "3) prima e dopo ogni operatore ci sia uno spazio\n"
               + "4) la stringa passata sia o una formula elementare o un espressione binaria di formule\n"
               + "5) naturalmente le parentesi tonde devono essere bilanciate\n");
       
       jScrollPaneAmbiente.setViewportView(jTextAreaResult);
        }

    }//GEN-LAST:event_jButtonEvalMousePressed

    private void jButtonAndMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAndMousePressed
        // TODO add your handling code here:
         int position = jTextFieldSorgente.getCaretPosition();
         System.out.println(position);
         String aux = jTextFieldSorgente.getText();
         
         if(position < aux.length()){
          jTextFieldSorgente.setText(aux.substring(0,position) + " " + Expression.AND_STRING  + " " + aux.substring(position ,aux.length()));
          jTextFieldSorgente.setCaretPosition(position + 3);
         }
         else{
             jTextFieldSorgente.setText(aux +" " + Expression.AND_STRING + " ");
         }
    }//GEN-LAST:event_jButtonAndMousePressed

    private void jButtonOrMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonOrMousePressed
        // TODO add your handling code here:
         int position = jTextFieldSorgente.getCaretPosition();
         System.out.println(position);
         String aux = jTextFieldSorgente.getText();
         
         if(position < aux.length()){
            jTextFieldSorgente.setText(aux.substring(0,position) + " " + Expression.OR_STRING + " " + aux.substring(position ,aux.length()));
             jTextFieldSorgente.setCaretPosition(position + 3);
         }
         else {
             jTextFieldSorgente.setText(aux +" " + Expression.OR_STRING + " ");
         }
    }//GEN-LAST:event_jButtonOrMousePressed

    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        // TODO add your handling code here:
        
         int position = jTextFieldSorgente.getCaretPosition();
         System.out.println(position);
         String aux = jTextFieldSorgente.getText();
         
         if(position < aux.length()){
             jTextFieldSorgente.setText(aux.substring(0,position) + " " + Expression.IMPLICATION_STRING + " " + aux.substring(position ,aux.length()));
             jTextFieldSorgente.setCaretPosition(position + 3);
         }
         else {
             jTextFieldSorgente.setText(aux +" " + Expression.IMPLICATION_STRING + " ");
         }
        
        
    }//GEN-LAST:event_jButton5MousePressed

    private void jTextFieldSorgenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSorgenteKeyReleased
        // TODO add your handling code here:
        
         int position = jTextFieldSorgente.getCaretPosition();
         System.out.println(position);
         String aux = jTextFieldSorgente.getText();
         char[] arrayString = aux.toCharArray();
        if(evt.getKeyChar() == '('){
         
         if(position < aux.length()){
             jTextFieldSorgente.setText(aux.substring(0,position) + " " + aux.substring(position ,aux.length()));
             jTextFieldSorgente.setCaretPosition(position+2);
         }
         else {
             jTextFieldSorgente.setText(aux + " ");
         }
         
        }
        else if(evt.getKeyChar() == ')'){

             jTextFieldSorgente.setText(aux.substring(0,position-1) + " " + aux.substring(position-1 ,aux.length()));
             jTextFieldSorgente.setCaretPosition(position+1);
         
        
        }

                
    }//GEN-LAST:event_jTextFieldSorgenteKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private String currentLookAndFeel;
            @Override
            public void run() {
                try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
    catch (UnsupportedLookAndFeelException e) {
    JOptionPane.showMessageDialog(null,"Non è possibile utilizzare "+currentLookAndFeel+" su questa piattaforma");
    }
    catch (Exception e) {
    JOptionPane.showMessageDialog(null,"Si è verificato un errore nel cambiare il lookAndFeel");
    }
    new MainJFrame().setVisible(true);
            }
        });
    
    }
    private JTextArea jTextAreaResult;
    private ModelTable table;
    private String[] variables;
    private Formula formula;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonAnd;
    private javax.swing.JButton jButtonEval;
    private javax.swing.JButton jButtonOr;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneAmbiente;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldDestinazione;
    private javax.swing.JTextField jTextFieldSorgente;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
