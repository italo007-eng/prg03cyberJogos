/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.com.ifba.cyberjogos.jogo.view;
import br.com.ifba.cyberjogos.jogo.model.Jogo;
import br.com.ifba.cyberjogos.jogo.service.JogoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Tela de listagem de Jogos do sistema CyberJogos.
 * Carregada dinamicamente no painel central da TelaPrincipal.
 *
 * @author Italo
 */
@Lazy
@Component
public class JogoListar extends javax.swing.JPanel {

    /** Serviço injetado pelo Spring para acessar o banco */
    @Autowired
    private JogoService jogoService;

    /** Modelo da tabela que controla linhas e colunas */
    private DefaultTableModel modeloTabela;
    
    /**
     * Construtor — inicializa os componentes visuais
     * e configura a tabela.
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
     * Configura as colunas da tabela e impede
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
        btnNovo.addActionListener(e -> abrirFormulario(null));

        btnEditar.addActionListener(e -> {
            Jogo selecionado = getJogoSelecionado();
            if (selecionado == null) {
                JOptionPane.showMessageDialog(this,
                    "Selecione um jogo para editar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            abrirFormulario(selecionado);
        });

        btnExcluir.addActionListener(e -> excluirJogoSelecionado());
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
     * Exclui o jogo selecionado após confirmação.
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
            "Deseja excluir o jogo \"" + jogo.getTitulo() + "\"?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            jogoService.excluir(jogo.getId());
            carregarJogos();
            JOptionPane.showMessageDialog(this,
                "Jogo excluído com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Abre o JogoForm para cadastro ou edição.
     *
     * @param jogo null para cadastro, jogo preenchido para edição
     */
    private void abrirFormulario(Jogo jogo) {
        JogoForm form = new JogoForm(jogoService, jogo, this);
        form.setVisible(true);
    }
    
    /**
     * Retorna o Jogo da linha selecionada na tabela.
     * Retorna null se nenhuma linha estiver selecionada.
     */
    private Jogo getJogoSelecionado() {
        int linha = tblJogos.getSelectedRow();
        if (linha == -1) return null;

        Long id = (Long) modeloTabela.getValueAt(linha, 0);
        return jogoService.buscarPorId(id).orElse(null);
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
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        scrollTabela = new javax.swing.JScrollPane();
        tblJogos = new javax.swing.JTable();

        setBackground(new java.awt.Color(13, 17, 23));
        setLayout(new java.awt.BorderLayout());

        pnlBotoes.setBackground(new java.awt.Color(22, 27, 39));
        pnlBotoes.setPreferredSize(new java.awt.Dimension(800, 46));
        pnlBotoes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnNovo.setBackground(new java.awt.Color(37, 99, 235));
        btnNovo.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(255, 255, 255));
        btnNovo.setText("Novo");
        btnNovo.setBorderPainted(false);
        btnNovo.setFocusPainted(false);
        pnlBotoes.add(btnNovo);

        btnEditar.setBackground(new java.awt.Color(15, 28, 56));
        btnEditar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(96, 165, 250));
        btnEditar.setText("Editar");
        btnEditar.setBorderPainted(false);
        btnEditar.setFocusPainted(false);
        pnlBotoes.add(btnEditar);

        btnExcluir.setBackground(new java.awt.Color(45, 12, 12));
        btnExcluir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(248, 113, 113));
        btnExcluir.setText("Excluir");
        btnExcluir.setBorderPainted(false);
        btnExcluir.setFocusPainted(false);
        pnlBotoes.add(btnExcluir);

        btnAtualizar.setBackground(new java.awt.Color(22, 27, 39));
        btnAtualizar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnAtualizar.setForeground(new java.awt.Color(107, 114, 128));
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setBorderPainted(false);
        btnAtualizar.setFocusPainted(false);
        pnlBotoes.add(btnAtualizar);

        add(pnlBotoes, java.awt.BorderLayout.PAGE_START);

        scrollTabela.setBackground(new java.awt.Color(13, 17, 23));
        scrollTabela.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(30, 42, 58)));
        scrollTabela.setViewportBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 153, 51), new java.awt.Color(51, 153, 0), new java.awt.Color(0, 153, 102), new java.awt.Color(0, 153, 51)));

        tblJogos.setBackground(new java.awt.Color(13, 17, 23));
        tblJogos.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblJogos.setForeground(new java.awt.Color(203, 213, 225));
        tblJogos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblJogos.setGridColor(new java.awt.Color(20, 28, 40));
        tblJogos.setRowHeight(32);
        tblJogos.setSelectionBackground(new java.awt.Color(15, 35, 75));
        tblJogos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrollTabela.setViewportView(tblJogos);

        add(scrollTabela, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JScrollPane scrollTabela;
    private javax.swing.JTable tblJogos;
    // End of variables declaration//GEN-END:variables
}
