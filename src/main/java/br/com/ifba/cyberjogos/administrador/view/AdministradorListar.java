/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.com.ifba.cyberjogos.administrador.view;

import br.com.ifba.cyberjogos.administrador.entity.Administrador;
import br.com.ifba.cyberjogos.administrador.service.AdministradorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Tela de listagem de Administradores do sistema CyberJogos.
 * Carregada dinamicamente no painel central da TelaPrincipal.
 *
 * @author Italo
 */
@Lazy
@Component
public class AdministradorListar extends javax.swing.JPanel {

    
    @Autowired
    private AdministradorService administradorService;

    
    private DefaultTableModel modeloTabela;

    
    public AdministradorListar() {
        initComponents();
        configurarTabela();
        configurarEventosBotoes();
    }
    
    
    @PostConstruct
    public void init() {
        carregarAdministradores();
    }
    
    /**
     * Configura as colunas da tabela e impede
     * edição direta nas células.
     */
    private void configurarTabela() {
        modeloTabela = new DefaultTableModel(
            new String[]{"ID", "Nome", "Email", "Cargo", "Nível de Acesso"},
            0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblAdministradores.setModel(modeloTabela);
        tblAdministradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblAdministradores.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblAdministradores.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblAdministradores.getColumnModel().getColumn(2).setPreferredWidth(180);
        tblAdministradores.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblAdministradores.getColumnModel().getColumn(4).setPreferredWidth(100);
    }
    
    private void configurarEventosBotoes() {
        btnNovo.addActionListener(e -> abrirFormulario(null));

        btnEditar.addActionListener(e -> {
            Administrador selecionado = getAdministradorSelecionado();
            if (selecionado == null) {
                JOptionPane.showMessageDialog(this,
                    "Selecione um administrador para editar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            abrirFormulario(selecionado);
        });

        btnExcluir.addActionListener(e -> excluirAdministradorSelecionado());
        btnAtualizar.addActionListener(e -> carregarAdministradores());
    }
    
    /**
     * Busca todos os administradores no banco e preenche a tabela.
     */
    public void carregarAdministradores() {
        modeloTabela.setRowCount(0);

        List<Administrador> administradores = administradorService.listarTodos();

        for (Administrador admin : administradores) {
            modeloTabela.addRow(new Object[]{
                admin.getId(),
                admin.getNome(),
                admin.getEmail(),
                admin.getCargo(),
                admin.getNivelAcesso()
            });
        }
    }
    
    /**
     * Retorna o Administrador da linha selecionada na tabela.
     * Retorna null se nenhuma linha estiver selecionada.
     */
    private Administrador getAdministradorSelecionado() {
        int linha = tblAdministradores.getSelectedRow();
        if (linha == -1) return null;

        Long id = (Long) modeloTabela.getValueAt(linha, 0);
        return administradorService.buscarPorId(id).orElse(null);
    }
    
    /**
     * Abre o AdministradorForm para cadastro ou edição.
     *
     * @param administrador null para cadastro, preenchido para edição
     */
    private void abrirFormulario(Administrador administrador) {
        AdministradorForm form = new AdministradorForm(
            administradorService, administrador, this
        );
        form.setVisible(true);
    }
    
    /**
     * Exclui o administrador selecionado após confirmação.
     */
    private void excluirAdministradorSelecionado() {
        Administrador admin = getAdministradorSelecionado();

        if (admin == null) {
            JOptionPane.showMessageDialog(this,
                "Selecione um administrador para excluir.",
                "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Deseja excluir o administrador \"" + admin.getNome() + "\"?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            administradorService.excluir(admin.getId());
            carregarAdministradores();
            JOptionPane.showMessageDialog(this,
                "Administrador excluído com sucesso!",
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
        tblAdministradores = new javax.swing.JTable();

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

        scrollTabela.setBackground(new java.awt.Color(13, 17, 23));
        scrollTabela.setForeground(new java.awt.Color(203, 213, 225));

        tblAdministradores.setBackground(new java.awt.Color(13, 17, 23));
        tblAdministradores.setForeground(new java.awt.Color(203, 213, 225));
        tblAdministradores.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAdministradores.setGridColor(new java.awt.Color(20, 28, 40));
        tblAdministradores.setRowHeight(32);
        tblAdministradores.setSelectionBackground(new java.awt.Color(15, 35, 75));
        scrollTabela.setViewportView(tblAdministradores);

        add(scrollTabela, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JScrollPane scrollTabela;
    private javax.swing.JTable tblAdministradores;
    // End of variables declaration//GEN-END:variables
}
