/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package br.com.ifba.cyberjogos.administrador.view;

import br.com.ifba.cyberjogos.administrador.entity.Administrador;
import br.com.ifba.cyberjogos.administrador.service.AdministradorService;
import javax.swing.JOptionPane;
import java.time.LocalDate;

/**
 * Formulário de cadastro e edição de Administradores.
 * Abre como janela modal sobre a TelaPrincipal.
 *
 * Modos de operação:
 *   CADASTRO → administrador == null → campos vazios → INSERT no banco
 *   EDIÇÃO   → administrador != null → campos preenchidos → UPDATE no banco
 *
 * @author Italo
 */
public class AdministradorForm extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdministradorForm.class.getName());

    private final AdministradorService administradorService;

    private final Administrador administradorEditando;

    
    private final AdministradorListar telaListar;
    /**
     * Creates new form AdministradorForm
     */
    public AdministradorForm(AdministradorService administradorService,
                             Administrador administrador,
                             AdministradorListar telaListar) {
        this.administradorService  = administradorService;
        this.administradorEditando = administrador;
        this.telaListar            = telaListar;

        initComponents();
        setLocationRelativeTo(null);
        preencherCombo();
        configurarEventosBotoes();

        if (administradorEditando != null) {
            setTitle("Editar Administrador");
            preencherCampos();
        } else {
            setTitle("Cadastrar Administrador");
            txtDataCadastro.setText(LocalDate.now().toString());
            txtDataCadastro.setEditable(false);
        }
    }
    
    /**
     * Preenche o JComboBox com os níveis de acesso disponíveis.
     */
    private void preencherCombo() {
        cmbNivelAcesso.removeAllItems();
        cmbNivelAcesso.addItem("1 - Básico");
        cmbNivelAcesso.addItem("2 - Gerente");
        cmbNivelAcesso.addItem("3 - Super Admin");
    }
    
    /**
     * Preenche os campos com os dados do administrador sendo editado.
     * Chamado apenas no modo EDIÇÃO.
     */
    private void preencherCampos() {
        txtNome.setText(administradorEditando.getNome());
        txtEmail.setText(administradorEditando.getEmail());
        txtSenha.setText(administradorEditando.getSenha());
        txtCargo.setText(administradorEditando.getCargo());
        txtDataCadastro.setText(administradorEditando.getDataCadastro());
        txtDataCadastro.setEditable(false);

        // Seleciona o nível correto no combo
        int nivel = administradorEditando.getNivelAcesso();
        cmbNivelAcesso.setSelectedIndex(nivel - 1);
    }
    
    /**
     * Configura os eventos dos botões Salvar e Cancelar.
     */
    private void configurarEventosBotoes() {
        btnSalvar.addActionListener(e -> salvar());
        btnCancelar.addActionListener(e -> dispose());
    }
    
    /**
     * Valida os campos, monta o objeto Administrador e salva no banco.
     * Após salvar, atualiza a tabela e fecha o formulário.
     */
    private void salvar() {

        // Validações dos campos obrigatórios
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Nome é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocus();
            return;
        }

        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Email é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return;
        }

        if (txtSenha.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Senha é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtSenha.requestFocus();
            return;
        }

        if (txtCargo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Cargo é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtCargo.requestFocus();
            return;
        }

        // Extrai o número do nível selecionado no combo
        // Ex: "1 - Básico" → pega o primeiro caractere → 1
        int nivelAcesso = Integer.parseInt(
            cmbNivelAcesso.getSelectedItem().toString().substring(0, 1)
        );

        // Monta o objeto Administrador com os dados do formulário
        Administrador admin = (administradorEditando != null)
            ? administradorEditando
            : new Administrador();

        admin.setNome(txtNome.getText().trim());
        admin.setEmail(txtEmail.getText().trim());
        admin.setSenha(txtSenha.getText().trim());
        admin.setCargo(txtCargo.getText().trim());
        admin.setNivelAcesso(nivelAcesso);
        admin.setDataCadastro(txtDataCadastro.getText().trim());

        // Salva no banco via service
        administradorService.salvar(admin);

        JOptionPane.showMessageDialog(this,
            "Administrador salvo com sucesso!",
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Atualiza a tabela na tela de listagem
        telaListar.carregarAdministradores();

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
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        lblCargo = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        lblNivelAcesso = new javax.swing.JLabel();
        cmbNivelAcesso = new javax.swing.JComboBox<>();
        lblDataCadastro = new javax.swing.JLabel();
        txtDataCadastro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Administrador");
        setModal(true);
        setResizable(false);
        setSize(new java.awt.Dimension(520, 380));

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

        pnlForm.setLayout(new java.awt.GridLayout(6, 2, 10, 10));

        lblNome.setText("Nome:");
        pnlForm.add(lblNome);
        pnlForm.add(txtNome);

        lblEmail.setText("Email:");
        pnlForm.add(lblEmail);
        pnlForm.add(txtEmail);

        lblSenha.setText("Senha:");
        pnlForm.add(lblSenha);
        pnlForm.add(txtSenha);

        lblCargo.setText("Cargo:");
        pnlForm.add(lblCargo);
        pnlForm.add(txtCargo);

        lblNivelAcesso.setText("Nível de Acesso: ");
        pnlForm.add(lblNivelAcesso);

        pnlForm.add(cmbNivelAcesso);

        lblDataCadastro.setText("Data Cadastro:");
        pnlForm.add(lblDataCadastro);

        txtDataCadastro.setText("jTextField4");
        pnlForm.add(txtDataCadastro);

        getContentPane().add(pnlForm, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbNivelAcesso;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblDataCadastro;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNivelAcesso;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtDataCadastro;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}
