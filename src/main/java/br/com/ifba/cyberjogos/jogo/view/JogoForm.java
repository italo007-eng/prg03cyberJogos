/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package br.com.ifba.cyberjogos.jogo.view;
import br.com.ifba.cyberjogos.jogo.model.Jogo;
import br.com.ifba.cyberjogos.jogo.service.JogoService;
import javax.swing.JOptionPane;
/**
 * Formulário de cadastro e edição de Jogos.
 * Abre como janela modal sobre a tela principal.
 *
 * Modos de operação:
 *   CADASTRO → jogo == null → campos vazios → INSERT no banco
 *   EDIÇÃO   → jogo != null → campos preenchidos → UPDATE no banco
 *
 * @author Italo
 */
public class JogoForm extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JogoForm.class.getName());

    /** Serviço para salvar o jogo no banco */
    private final JogoService jogoService;

    /** Jogo sendo editado — null para novo cadastro */
    private final Jogo jogoEditando;

    /** Referência à listagem para atualizar a tabela após salvar */
    private final JogoListar telaListar;

    /**
     * Construtor do formulário.
     *
     * @param jogoService  serviço de jogo
     * @param jogo         jogo a editar (null para novo cadastro)
     * @param telaListar   tela de listagem para atualizar após salvar
     */
    
    public JogoForm(JogoService jogoService, Jogo jogo, JogoListar telaListar) {
        this.jogoService  = jogoService;
        this.jogoEditando = jogo;
        this.telaListar   = telaListar;

        initComponents(); 
        setLocationRelativeTo(null);
        preencherCombos();
        configurarEventosBotoes();

        // Define o modo: cadastro ou edição
        if (jogoEditando != null) {
            setTitle("Editar Jogo");
            preencherCampos();
        } else {
            setTitle("Cadastrar Jogo");
        }
    }
    
    /**
     * Preenche os JComboBox com as opções disponíveis.
     * Remove itens padrão antes de adicionar os corretos.
     */
    private void preencherCombos() {
        cmbPlataforma.removeAllItems();
        cmbPlataforma.addItem("PC");
        cmbPlataforma.addItem("PS5");
        cmbPlataforma.addItem("Xbox");
        cmbPlataforma.addItem("Switch");

        cmbGenero.removeAllItems();
        cmbGenero.addItem("Ação");
        cmbGenero.addItem("Aventura");
        cmbGenero.addItem("RPG");
        cmbGenero.addItem("FPS");
        cmbGenero.addItem("Estratégia");
        cmbGenero.addItem("Esporte");
        cmbGenero.addItem("Luta");
        cmbGenero.addItem("Simulação");

        cmbClassificacao.removeAllItems();
        cmbClassificacao.addItem("Livre");
        cmbClassificacao.addItem("10+");
        cmbClassificacao.addItem("12+");
        cmbClassificacao.addItem("14+");
        cmbClassificacao.addItem("16+");
        cmbClassificacao.addItem("18+");
    }
    
    /**
     * Preenche os campos com os dados do jogo sendo editado.
     * Chamado apenas no modo EDIÇÃO.
     */
    private void preencherCampos() {
        txtTitulo.setText(jogoEditando.getTitulo());
        txtDescricao.setText(jogoEditando.getDescricao());
        txtDesenvolvedor.setText(jogoEditando.getDesenvolvedor());
        txtPreco.setText(String.valueOf(jogoEditando.getPreco()));
        txtEstoque.setText(String.valueOf(jogoEditando.getQuantidadeEstoque()));
        cmbPlataforma.setSelectedItem(jogoEditando.getPlataforma());
        cmbGenero.setSelectedItem(jogoEditando.getGenero());
        cmbClassificacao.setSelectedItem(jogoEditando.getClassificacaoEtaria());
    }
    
    /**
     * Configura os eventos dos botões Salvar e Cancelar.
     */
    private void configurarEventosBotoes() {
        btnSalvar.addActionListener(e -> salvar());
        btnCancelar.addActionListener(e -> dispose());
    }
    
    /**
     * Valida os campos, monta o objeto Jogo e salva no banco.
     * Após salvar, atualiza a tabela e fecha o formulário.
     */
    private void salvar() {

        // Validação dos campos obrigatórios
        if (txtTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Título é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtTitulo.requestFocus();
            return;
        }

        if (txtPreco.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Preço é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtPreco.requestFocus();
            return;
        }

        if (txtEstoque.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O campo Estoque é obrigatório.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtEstoque.requestFocus();
            return;
        }

        // Validação do formato numérico
        double preco;
        int estoque;

        try {
            preco = Double.parseDouble(
                txtPreco.getText().trim().replace(",", ".")
            );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Preço inválido. Use apenas números. Ex: 199.90",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtPreco.requestFocus();
            return;
        }

        try {
            estoque = Integer.parseInt(txtEstoque.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Estoque inválido. Use apenas números inteiros.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            txtEstoque.requestFocus();
            return;
        }

        // Monta o objeto Jogo com os dados do formulário
        // Reutiliza o objeto se for edição (mantém o ID)
        Jogo jogo = (jogoEditando != null) ? jogoEditando : new Jogo();
        jogo.setTitulo(txtTitulo.getText().trim());
        jogo.setDescricao(txtDescricao.getText().trim());
        jogo.setDesenvolvedor(txtDesenvolvedor.getText().trim());
        jogo.setPreco(preco);
        jogo.setQuantidadeEstoque(estoque);
        jogo.setPlataforma((String) cmbPlataforma.getSelectedItem());
        jogo.setGenero((String) cmbGenero.getSelectedItem());
        jogo.setClassificacaoEtaria((String) cmbClassificacao.getSelectedItem());

        // Salva no banco via service
        jogoService.salvar(jogo);

        JOptionPane.showMessageDialog(this,
            "Jogo salvo com sucesso!",
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Atualiza a tabela na tela de listagem
        telaListar.carregarJogos();

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
        lblTitulo = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblDesenvolvedor = new javax.swing.JLabel();
        txtDesenvolvedor = new javax.swing.JTextField();
        lblPreco = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        lblEstoque = new javax.swing.JLabel();
        txtEstoque = new javax.swing.JTextField();
        lblPlataforma = new javax.swing.JLabel();
        cmbPlataforma = new javax.swing.JComboBox<>();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox<>();
        lblClassificacao = new javax.swing.JLabel();
        cmbClassificacao = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Jogo");
        setModal(true);
        setResizable(false);
        setSize(new java.awt.Dimension(520, 500));

        btnCancelar.setText("Cancelar");

        btnSalvar.setText("Salvar");

        javax.swing.GroupLayout pnlBotoesLayout = new javax.swing.GroupLayout(pnlBotoes);
        pnlBotoes.setLayout(pnlBotoesLayout);
        pnlBotoesLayout.setHorizontalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotoesLayout.createSequentialGroup()
                .addContainerGap(425, Short.MAX_VALUE)
                .addGroup(pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addGap(108, 108, 108))
        );
        pnlBotoesLayout.setVerticalGroup(
            pnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(pnlBotoes, java.awt.BorderLayout.PAGE_END);

        pnlForm.setLayout(new java.awt.GridLayout(8, 2, 10, 10));

        lblTitulo.setText("Título: *");
        pnlForm.add(lblTitulo);
        pnlForm.add(txtTitulo);

        lblDescricao.setText("Descrição:");
        pnlForm.add(lblDescricao);
        pnlForm.add(txtDescricao);

        lblDesenvolvedor.setText("Desenvolvedor:");
        pnlForm.add(lblDesenvolvedor);
        pnlForm.add(txtDesenvolvedor);

        lblPreco.setText("Preço (R$): *");
        pnlForm.add(lblPreco);
        pnlForm.add(txtPreco);

        lblEstoque.setText("Estoque: *");
        pnlForm.add(lblEstoque);
        pnlForm.add(txtEstoque);

        lblPlataforma.setText("Plataforma: *");
        pnlForm.add(lblPlataforma);

        pnlForm.add(cmbPlataforma);

        lblGenero.setText("Gênero: *");
        pnlForm.add(lblGenero);

        pnlForm.add(cmbGenero);

        lblClassificacao.setText("Classificação: *");
        pnlForm.add(lblClassificacao);

        pnlForm.add(cmbClassificacao);

        getContentPane().add(pnlForm, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbClassificacao;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JComboBox<String> cmbPlataforma;
    private javax.swing.JLabel lblClassificacao;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDesenvolvedor;
    private javax.swing.JLabel lblEstoque;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblPlataforma;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtDesenvolvedor;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
