package br.com.ifba.cyberjogos.jogo.view;

import jakarta.annotation.PostConstruct;
import br.com.ifba.cyberjogos.jogo.model.Jogo;
import br.com.ifba.cyberjogos.jogo.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

/**
 * Tela de listagem de jogos do sistema CyberJogos.
 * Exibe todos os jogos em uma tabela e permite
 * as operações de CRUD (criar, editar, excluir).
 *
 * @author Italo
 */
@Lazy
@Component
public class JogoListar extends javax.swing.JPanel {

    /**
     * Serviço injetado pelo Spring para acessar
     * as operações de banco de dados do Jogo.
     */
    @Autowired
    private JogoService jogoService;

    /**
     * Modelo da tabela que controla os dados exibidos.
     */
    private DefaultTableModel modeloTabela;

    // Componentes da tela
    private JPanel    pnlBotoes;
    private JButton   btnNovo;
    private JButton   btnEditar;
    private JButton   btnExcluir;
    private JButton   btnAtualizar;
    private JScrollPane scrollTabela;
    private JTable    tblJogos;

    /**
     * Construtor — inicializa os componentes visuais,
     * configura a tabela e carrega os jogos do banco.
     */
    public JogoListar() {
        initComponents();
        configurarTabela();
        configurarEventosBotoes();
    }
    /**
    * Executado pelo Spring após injetar o jogoService.
    * Carrega os jogos do banco na tabela.
    */
     @PostConstruct
     public void init() {
    carregarJogos();
    }

    /**
     * Monta os componentes visuais da tela manualmente.
     * Substitui o initComponents() gerado pelo NetBeans.
     */
    private void initComponents() {

        // Layout principal do painel
        setLayout(new BorderLayout());

        // --- Painel de botões (topo) ---
        pnlBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnNovo      = new JButton("Novo");
        btnEditar    = new JButton("Editar");
        btnExcluir   = new JButton("Excluir");
        btnAtualizar = new JButton("Atualizar");

        pnlBotoes.add(btnNovo);
        pnlBotoes.add(btnEditar);
        pnlBotoes.add(btnExcluir);
        pnlBotoes.add(btnAtualizar);

        // --- Tabela (centro) ---
        tblJogos    = new JTable();
        scrollTabela = new JScrollPane(tblJogos);

        // Adiciona os painéis ao layout
        add(pnlBotoes,   BorderLayout.NORTH);
        add(scrollTabela, BorderLayout.CENTER);
    }

    /**
     * Configura as colunas da JTable e impede
     * edição direta nas células.
     */
    private void configurarTabela() {

        modeloTabela = new DefaultTableModel(
        new String[]{"ID", "Título", "Plataforma", "Gênero", "Preço", "Estoque", "Classificação"},
        0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    tblJogos.setModel(modeloTabela);
    tblJogos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    tblJogos.getColumnModel().getColumn(0).setPreferredWidth(40);
    tblJogos.getColumnModel().getColumn(1).setPreferredWidth(200);
    tblJogos.getColumnModel().getColumn(2).setPreferredWidth(80);
    tblJogos.getColumnModel().getColumn(3).setPreferredWidth(80);
    tblJogos.getColumnModel().getColumn(4).setPreferredWidth(80);
    tblJogos.getColumnModel().getColumn(5).setPreferredWidth(60);
    tblJogos.getColumnModel().getColumn(6).setPreferredWidth(90);

    
    }

    /**
     * Configura os eventos de clique dos botões.
     */
    private void configurarEventosBotoes() {

        // Botão NOVO → abre formulário vazio
        btnNovo.addActionListener(e -> abrirFormulario(null));

        // Botão EDITAR → abre formulário com dados do jogo selecionado
        btnEditar.addActionListener(e -> {
            Jogo jogoSelecionado = getJogoSelecionado();
            if (jogoSelecionado == null) {
                JOptionPane.showMessageDialog(this,
                    "Selecione um jogo para editar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            abrirFormulario(jogoSelecionado);
        });

        // Botão EXCLUIR → confirma e exclui
        btnExcluir.addActionListener(e -> excluirJogoSelecionado());

        // Botão ATUALIZAR → recarrega a tabela
        btnAtualizar.addActionListener(e -> carregarJogos());
    }

    /**
     * Busca todos os jogos no banco e preenche a tabela.
     */
    public void carregarJogos() {
        modeloTabela.setRowCount(0);

        List<Jogo> jogos = jogoService.listarTodos();

        for (Jogo jogo : jogos) {
            modeloTabela.addRow(new Object[]{
                jogo.getId(),
                jogo.getTitulo(),
                jogo.getPlataforma(),
                jogo.getGenero(),
                String.format("R$ %.2f", jogo.getPreco()),
                jogo.getQuantidadeEstoque() + " un.",
                jogo.getClassificacaoEtaria()
            });
        }
    }

    /**
     * Retorna o Jogo da linha selecionada na tabela.
     * Retorna null se nenhuma linha estiver selecionada.
     *
     * @return Jogo selecionado ou null
     */
    private Jogo getJogoSelecionado() {
        int linhaSelecionada = tblJogos.getSelectedRow();
        if (linhaSelecionada == -1) return null;

        Long id = (Long) modeloTabela.getValueAt(linhaSelecionada, 0);
        return jogoService.buscarPorId(id).orElse(null);
    }

    /**
     * Abre o JogoForm para cadastro ou edição.
     *
     * @param jogo null para novo cadastro, ou jogo para edição
     */
    private void abrirFormulario(Jogo jogo) {
        JogoForm form = new JogoForm(jogoService, jogo, this);
        form.setVisible(true);
    }

    /**
     * Exclui o jogo selecionado após confirmação do usuário.
     */
    private void excluirJogoSelecionado() {
        Jogo jogo = getJogoSelecionado();

        if (jogo == null) {
            JOptionPane.showMessageDialog(this,
                "Selecione um jogo para excluir.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Deseja realmente excluir o jogo \"" + jogo.getTitulo() + "\"?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirmacao == JOptionPane.YES_OPTION) {
            jogoService.excluir(jogo.getId());
            carregarJogos();
            JOptionPane.showMessageDialog(this,
                "Jogo excluído com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}