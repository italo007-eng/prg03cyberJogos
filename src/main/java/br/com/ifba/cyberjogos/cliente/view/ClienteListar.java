/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.com.ifba.cyberjogos.cliente.view;

import br.com.ifba.cyberjogos.cliente.entity.Cliente;
import br.com.ifba.cyberjogos.cliente.service.ClienteService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Tela de listagem de Clientes do sistema CyberJogos.
 * Carregada dinamicamente no painel central da TelaPrincipal.
 *
 * @author Italo
 */
@Lazy
@Component
public class ClienteListar extends javax.swing.JPanel {

    @Autowired
    private ClienteService clienteService;

    
    private DefaultTableModel modeloTabela;
    
    /**
     * Construtor — inicializa os componentes visuais
     * e configura a tabela.
     */
    public ClienteListar() {
        initComponents();
        configurarTabela();
        configurarEventosBotoes();
    }
    
    /**
     * Executado pelo Spring após injetar o clienteService.
     * Carrega os clientes do banco na tabela.
     */
    @PostConstruct
    public void init() {
        carregarClientes();
    }
    
    /**
     * Configura as colunas da tabela e impede
     * edição direta nas células.
     */
    private void configurarTabela() {
        modeloTabela = new DefaultTableModel(
            new String[]{"ID", "Nome", "CPF", "Email", "Telefone", "Endereço"},
            0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblClientes.setModel(modeloTabela);
        tblClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(180);
        tblClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblClientes.getColumnModel().getColumn(5).setPreferredWidth(180);
    }
    
    /**
     * Configura os eventos de clique dos botões.
     */
    private void configurarEventosBotoes() {
        btnNovo.addActionListener(e -> abrirFormulario(null));

        btnEditar.addActionListener(e -> {
            Cliente selecionado = getClienteSelecionado();
            if (selecionado == null) {
                JOptionPane.showMessageDialog(this,
                    "Selecione um cliente para editar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            abrirFormulario(selecionado);
        });

        btnExcluir.addActionListener(e -> excluirClienteSelecionado());
        btnAtualizar.addActionListener(e -> carregarClientes());
    }
    
    /**
     * Busca todos os clientes no banco e preenche a tabela.
     */
    public void carregarClientes() {
        modeloTabela.setRowCount(0);

        List<Cliente> clientes = clienteService.listarTodos();

        for (Cliente cliente : clientes) {
            modeloTabela.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco()
            });
        }
    }
    
    /**
     * Retorna o Cliente da linha selecionada na tabela.
     * Retorna null se nenhuma linha estiver selecionada.
     */
    private Cliente getClienteSelecionado() {
        int linha = tblClientes.getSelectedRow();
        if (linha == -1) return null;

        Long id = (Long) modeloTabela.getValueAt(linha, 0);
        return clienteService.buscarPorId(id).orElse(null);
    }
    
    /**
     * Abre o ClienteForm para cadastro ou edição.
     *
     * @param cliente null para cadastro, cliente preenchido para edição
     */
    private void abrirFormulario(Cliente cliente) {
        ClienteForm form = new ClienteForm(clienteService, cliente, this);
        form.setVisible(true);
    }
    
    /**
     * Exclui o cliente selecionado após confirmação.
     */
    private void excluirClienteSelecionado() {
        Cliente cliente = getClienteSelecionado();

        if (cliente == null) {
            JOptionPane.showMessageDialog(this,
                "Selecione um cliente para excluir.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Deseja excluir o cliente \"" + cliente.getNome() + "\"?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            clienteService.excluir(cliente.getId());
            carregarClientes();
            JOptionPane.showMessageDialog(this,
                "Cliente excluído com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
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
        tblClientes = new javax.swing.JTable();

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
        btnAtualizar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnAtualizar.setForeground(new java.awt.Color(107, 114, 128));
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setBorderPainted(false);
        btnAtualizar.setFocusPainted(false);
        pnlBotoes.add(btnAtualizar);

        add(pnlBotoes, java.awt.BorderLayout.PAGE_START);

        tblClientes.setBackground(new java.awt.Color(13, 17, 23));
        tblClientes.setForeground(new java.awt.Color(203, 213, 225));
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.setGridColor(new java.awt.Color(20, 28, 40));
        tblClientes.setRowHeight(32);
        tblClientes.setSelectionBackground(new java.awt.Color(15, 35, 75));
        scrollTabela.setViewportView(tblClientes);

        add(scrollTabela, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JScrollPane scrollTabela;
    private javax.swing.JTable tblClientes;
    // End of variables declaration//GEN-END:variables
}
