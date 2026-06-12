/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package br.com.ifba.cyberjogos.cliente.view;

import br.com.ifba.cyberjogos.cliente.entity.Cliente;
import br.com.ifba.cyberjogos.cliente.service.ClienteService;
import javax.swing.JOptionPane;
import java.time.LocalDate;

/**
 * Formulário de cadastro e edição de Clientes.
 * Abre como janela modal sobre a TelaPrincipal.
 *
 * Modos de operação:
 *   CADASTRO → cliente == null → campos vazios → INSERT no banco
 *   EDIÇÃO   → cliente != null → campos preenchidos → UPDATE no banco
 *
 * @author Italo
 */
public class ClienteForm extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ClienteForm.class.getName());

    /** Serviço para salvar o cliente no banco */
    private final ClienteService clienteService;

    /** Cliente sendo editado — null para novo cadastro */
    private final Cliente clienteEditando;

    /** Referência à listagem para atualizar após salvar */
    private final ClienteListar telaListar;
    
    /**
     * Construtor do formulário.
     *
     * @param clienteService  serviço de cliente
     * @param cliente         cliente a editar (null para novo cadastro)
     * @param telaListar      tela de listagem para atualizar após salvar
     */
    public ClienteForm(ClienteService clienteService, Cliente cliente, ClienteListar telaListar) {
        this.clienteService  = clienteService;
        this.clienteEditando = cliente;
        this.telaListar      = telaListar;

        initComponents();
        setLocationRelativeTo(null);
        configurarEventosBotoes();

        if (clienteEditando != null) {
            setTitle("Editar Cliente");
            preencherCampos();
        } else {
            setTitle("Cadastrar Cliente");
            // Preenche a data atual automaticamente
            txtDataCadastro.setText(LocalDate.now().toString());
            txtDataCadastro.setEditable(false);
        }
    }
    
    /**
     * Preenche os campos com os dados do cliente sendo editado.
     * Chamado apenas no modo EDIÇÃO.
     */
    private void preencherCampos() {
        txtNome.setText(clienteEditando.getNome());
        txtCpf.setText(clienteEditando.getCpf());
        txtEmail.setText(clienteEditando.getEmail());
        txtSenha.setText(clienteEditando.getSenha());
        txtTelefone.setText(clienteEditando.getTelefone());
        txtEndereco.setText(clienteEditando.getEndereco());
        txtDataCadastro.setText(clienteEditando.getDataCadastro());
        txtDataCadastro.setEditable(false);
    }
    
    /**
     * Configura os eventos dos botões Salvar e Cancelar.
     */
    private void configurarEventosBotoes() {
        btnSalvar.addActionListener(e -> salvar());
        btnCancelar.addActionListener(e -> dispose());
    }
    
    /**
     * Valida os campos, monta o objeto Cliente e salva no banco.
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

        if (txtCpf.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo CPF é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtCpf.requestFocus();
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

        // Verifica CPF duplicado apenas no cadastro novo
        if (clienteEditando == null &&
            clienteService.cpfJaCadastrado(txtCpf.getText().trim())) {
            JOptionPane.showMessageDialog(this,
                "Este CPF já está cadastrado!",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtCpf.requestFocus();
            return;
        }

        // Monta o objeto Cliente com os dados do formulário
        Cliente cliente = (clienteEditando != null) ? clienteEditando : new Cliente();
        cliente.setNome(txtNome.getText().trim());
        cliente.setCpf(txtCpf.getText().trim());
        cliente.setEmail(txtEmail.getText().trim());
        cliente.setSenha(txtSenha.getText().trim());
        cliente.setTelefone(txtTelefone.getText().trim());
        cliente.setEndereco(txtEndereco.getText().trim());
        cliente.setDataCadastro(txtDataCadastro.getText().trim());

        // Salva no banco via service
        clienteService.salvar(cliente);

        JOptionPane.showMessageDialog(this,
            "Cliente salvo com sucesso!",
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Atualiza a tabela na tela de listagem
        telaListar.carregarClientes();

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
        lblCpf = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        lblEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        lblDataCadastro = new javax.swing.JLabel();
        txtDataCadastro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Cliente");
        setModal(true);
        setResizable(false);
        setSize(new java.awt.Dimension(520, 420));

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

        pnlForm.setLayout(new java.awt.GridLayout(7, 2, 10, 10));

        lblNome.setText("Nome:");
        pnlForm.add(lblNome);
        pnlForm.add(txtNome);

        lblCpf.setText("CPF:");
        pnlForm.add(lblCpf);
        pnlForm.add(txtCpf);

        lblEmail.setText("Email:");
        pnlForm.add(lblEmail);
        pnlForm.add(txtEmail);

        lblSenha.setText("Senha:");
        pnlForm.add(lblSenha);
        pnlForm.add(txtSenha);

        lblTelefone.setText("Telefone:");
        pnlForm.add(lblTelefone);
        pnlForm.add(txtTelefone);

        lblEndereco.setText("Endereço:");
        pnlForm.add(lblEndereco);
        pnlForm.add(txtEndereco);

        lblDataCadastro.setText("Data Cadastro:");
        pnlForm.add(lblDataCadastro);
        pnlForm.add(txtDataCadastro);

        getContentPane().add(pnlForm, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblDataCadastro;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtDataCadastro;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
