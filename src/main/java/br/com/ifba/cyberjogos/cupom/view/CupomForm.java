/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package br.com.ifba.cyberjogos.cupom.view;

import br.com.ifba.cyberjogos.cupom.entity.Cupom;
import br.com.ifba.cyberjogos.cupom.service.CupomService;
import javax.swing.JOptionPane;

/**
 * Formulário de cadastro e edição de Cupons.
 * Abre como janela modal sobre a TelaPrincipal.
 *
 * Modos de operação:
 *   CADASTRO → cupom == null → campos vazios → INSERT no banco
 *   EDIÇÃO   → cupom != null → campos preenchidos → UPDATE no banco
 *
 * @author Italo
 */
public class CupomForm extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CupomForm.class.getName());

    
    private final CupomService cupomService;

    private final Cupom cupomEditando;

    private final CupomListar telaListar;
    /**
     * Creates new form CupomForm
     * @param cupomService
     * @param cupom
     * @param telaListar
     */
    
    public CupomForm(CupomService cupomService, Cupom cupom, CupomListar telaListar) {
        this.cupomService  = cupomService;
        this.cupomEditando = cupom;
        this.telaListar    = telaListar;

        initComponents();
        setLocationRelativeTo(null);
        preencherCombo();
        configurarEventosBotoes();

        if (cupomEditando != null) {
            setTitle("Editar Cupom");
            preencherCampos();
        } else {
            setTitle("Cadastrar Cupom");
        }
    }
    
    
    private void preencherCombo() {
        cmbAtivo.removeAllItems();
        cmbAtivo.addItem("Sim");
        cmbAtivo.addItem("Não");
    }

    
    private void preencherCampos() {
        txtCodigo.setText(cupomEditando.getCodigo());
        txtDesconto.setText(String.valueOf(cupomEditando.getDesconto()));
        txtValidade.setText(cupomEditando.getValidade());
        txtLimiteUso.setText(String.valueOf(cupomEditando.getLimiteUso()));
        cmbAtivo.setSelectedItem(cupomEditando.getAtivo() ? "Sim" : "Não");
    }

    
    private void configurarEventosBotoes() {
        btnSalvar.addActionListener(e -> salvar());
        btnCancelar.addActionListener(e -> dispose());
    }

    
    private void salvar() {

        // Validações dos campos obrigatórios
        if (txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Código é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtCodigo.requestFocus();
            return;
        }

        if (txtDesconto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Desconto é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtDesconto.requestFocus();
            return;
        }

        if (txtValidade.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Validade é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtValidade.requestFocus();
            return;
        }

        if (txtLimiteUso.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Limite de Uso é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtLimiteUso.requestFocus();
            return;
        }

        // Validação do formato numérico
        double desconto;
        int limiteUso;

        try {
            desconto = Double.parseDouble(
                txtDesconto.getText().trim().replace(",", ".")
            );
            if (desconto <= 0 || desconto > 100) {
                JOptionPane.showMessageDialog(this,
                    "Desconto deve ser entre 1 e 100.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
                txtDesconto.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Desconto inválido. Use apenas números. Ex: 10.5",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtDesconto.requestFocus();
            return;
        }

        try {
            limiteUso = Integer.parseInt(txtLimiteUso.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Limite de uso inválido. Use apenas números inteiros.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtLimiteUso.requestFocus();
            return;
        }

        // Verifica código duplicado apenas no cadastro novo
        if (cupomEditando == null &&
            cupomService.codigoJaCadastrado(txtCodigo.getText().trim().toUpperCase())) {
            JOptionPane.showMessageDialog(this,
                "Este código de cupom já está cadastrado!",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtCodigo.requestFocus();
            return;
        }

        Cupom cupom = (cupomEditando != null) ? cupomEditando : new Cupom();
        cupom.setCodigo(txtCodigo.getText().trim().toUpperCase());
        cupom.setDesconto(desconto);
        cupom.setValidade(txtValidade.getText().trim());
        cupom.setLimiteUso(limiteUso);
        cupom.setAtivo(cmbAtivo.getSelectedItem().equals("Sim"));

        cupomService.salvar(cupom);

        JOptionPane.showMessageDialog(this,
            "Cupom salvo com sucesso!",
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        telaListar.carregarCupons();

        // Fecha o formulário
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBotoes = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        pnlForm = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblDesconto = new javax.swing.JLabel();
        txtDesconto = new javax.swing.JTextField();
        lblValidade = new javax.swing.JLabel();
        txtValidade = new javax.swing.JTextField();
        lblLimiteUso = new javax.swing.JLabel();
        txtLimiteUso = new javax.swing.JTextField();
        lblAtivo = new javax.swing.JLabel();
        cmbAtivo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Cupom");
        setModal(true);
        setResizable(false);
        setSize(new java.awt.Dimension(520, 360));

        pnlBotoes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnCancelar.setBackground(new java.awt.Color(40, 55, 70));
        btnCancelar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(136, 153, 170));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        pnlBotoes.add(btnCancelar);

        btnSalvar.setBackground(new java.awt.Color(37, 99, 235));
        btnSalvar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("Salvar");
        btnSalvar.setBorderPainted(false);
        btnSalvar.setFocusPainted(false);
        pnlBotoes.add(btnSalvar);

        getContentPane().add(pnlBotoes, java.awt.BorderLayout.PAGE_END);

        pnlForm.setLayout(new java.awt.GridLayout(5, 2, 10, 10));

        lblCodigo.setText("Código:");
        pnlForm.add(lblCodigo);
        pnlForm.add(txtCodigo);

        lblDesconto.setText("Desconto (%):");
        pnlForm.add(lblDesconto);
        pnlForm.add(txtDesconto);

        lblValidade.setText("Validade:");
        pnlForm.add(lblValidade);
        pnlForm.add(txtValidade);

        lblLimiteUso.setText("Limite de Uso:");
        pnlForm.add(lblLimiteUso);
        pnlForm.add(txtLimiteUso);

        lblAtivo.setText("Ativo:");
        pnlForm.add(lblAtivo);

        pnlForm.add(cmbAtivo);

        getContentPane().add(pnlForm, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbAtivo;
    private javax.swing.JLabel lblAtivo;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDesconto;
    private javax.swing.JLabel lblLimiteUso;
    private javax.swing.JLabel lblValidade;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDesconto;
    private javax.swing.JTextField txtLimiteUso;
    private javax.swing.JTextField txtValidade;
    // End of variables declaration//GEN-END:variables
}
