/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.cyberjogos.view;

import br.com.ifba.cyberjogos.jogo.view.JogoListar;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.awt.Color;
import org.springframework.context.annotation.Lazy;
import br.com.ifba.cyberjogos.cliente.view.ClienteListar;
/**
 * Tela principal do sistema CyberJogos.
 * Contém o menu de navegação lateral e o painel central
 * onde as telas de CRUD são carregadas dinamicamente.
 *
 * @author Italo
 */
@Lazy
@Component
public class TelaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipal.class.getName());

    /**
     * Contexto do Spring — usado para buscar
     * os beans das telas de forma gerenciada.
     */
    @Autowired
    private ApplicationContext context;
    
    /**
     * Construtor — inicializa os componentes,
     * configura a janela e monta o sidebar.
     */
    public TelaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        configurarSidebar();
        configurarEventosBotoes();
        mostrarPainelInicio();
    }
    
    /**
     * Monta os botões do sidebar programaticamente.
     * Mais simples do que arrastar no Form Designer.
     */
    private void configurarSidebar() {
        
    
    pnlSidebar.removeAll();

    pnlSidebar.setLayout(new java.awt.GridLayout(10, 1));

    btnMenuInicio     = criarBotaoMenu("  Início");
    btnMenuJogos      = criarBotaoMenu("  Jogos");
    btnMenuClientes   = criarBotaoMenu("  Clientes");
    btnMenuPedidos    = criarBotaoMenu("  Pedidos");
    btnMenuCupons     = criarBotaoMenu("  Cupons");
    btnMenuAdmin      = criarBotaoMenu("  Admin");
    btnMenuRelatorios = criarBotaoMenu("  Relatórios");

    pnlSidebar.add(btnMenuInicio);
    pnlSidebar.add(btnMenuJogos);
    pnlSidebar.add(btnMenuClientes);
    pnlSidebar.add(btnMenuPedidos);
    pnlSidebar.add(btnMenuCupons);
    pnlSidebar.add(btnMenuAdmin);
    pnlSidebar.add(btnMenuRelatorios);
    }

    /**
     * Cria um botão do sidebar já estilizado.
     *
     * @param texto texto exibido no botão
     * @return botão configurado
     */
    private javax.swing.JButton criarBotaoMenu(String texto) {
        javax.swing.JButton btn = new javax.swing.JButton(texto);
        btn.setBackground(new Color(13, 27, 42));
        btn.setForeground(new Color(136, 153, 170));
        btn.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        return btn;
    }
    
    /**
     * Configura os eventos de clique de todos os botões.
     */
    private void configurarEventosBotoes() {
        btnNavJogos.addActionListener(e -> navegarPara("jogos"));
        btnNavClientes.addActionListener(e -> navegarPara("clientes"));
        btnNavPedidos.addActionListener(e -> navegarPara("pedidos"));

        btnMenuInicio.addActionListener(e -> mostrarPainelInicio());
        btnMenuJogos.addActionListener(e -> navegarPara("jogos"));
        btnMenuClientes.addActionListener(e -> navegarPara("clientes"));
        btnMenuPedidos.addActionListener(e -> navegarPara("pedidos"));
    }
    
    /**
     * Navega para uma seção carregando o painel correspondente
     * no centro da tela principal.
     *
     * @param secao nome da seção: "jogos", "clientes", "pedidos"
     */
    public void navegarPara(String secao) {
        javax.swing.JPanel painel = null;

        switch (secao) {
            case "jogos" -> painel = (javax.swing.JPanel) context.getBean(JogoListar.class); 
            case "clientes" -> painel = (javax.swing.JPanel) context.getBean(ClienteListar.class);
            // case "pedidos"  -> painel = context.getBean(PedidoListar.class);
        }

        if (painel != null) {
            pnlConteudo.removeAll();
            pnlConteudo.add(painel);
            pnlConteudo.revalidate();
            pnlConteudo.repaint();
        }

        destacarBotaoAtivo(secao);
    }
    
    /**
     * Exibe uma mensagem de boas-vindas no painel central.
     * Chamado ao iniciar a aplicação.
     */
    private void mostrarPainelInicio() {
        pnlConteudo.removeAll();

        javax.swing.JLabel lblBemVindo = new javax.swing.JLabel(
            "Bem-vindo ao CyberJogos!",
            javax.swing.SwingConstants.CENTER
        );
        lblBemVindo.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 22));
        lblBemVindo.setForeground(new Color(200, 210, 220));

        pnlConteudo.add(lblBemVindo, java.awt.BorderLayout.CENTER);
        pnlConteudo.revalidate();
        pnlConteudo.repaint();
    }
    
    /**
     * Destaca visualmente o botão do menu correspondente
     * à seção atualmente ativa.
     *
     * @param secao seção ativa
     */
    private void destacarBotaoAtivo(String secao) {
        Color corPadrao   = new Color(13, 27, 42);
        Color corAtivo    = new Color(233, 69, 96);
        Color textoNormal = new Color(136, 153, 170);
        Color textoAtivo  = Color.WHITE;

        // Reseta todos os botões para cor padrão
        btnMenuInicio.setBackground(corPadrao);
        btnMenuInicio.setForeground(textoNormal);
        btnMenuJogos.setBackground(corPadrao);
        btnMenuJogos.setForeground(textoNormal);
        btnMenuClientes.setBackground(corPadrao);
        btnMenuClientes.setForeground(textoNormal);
        btnMenuPedidos.setBackground(corPadrao);
        btnMenuPedidos.setForeground(textoNormal);

        // Destaca o botão da seção ativa
        switch (secao) {
            case "jogos" -> {
                btnMenuJogos.setBackground(corAtivo);
                btnMenuJogos.setForeground(textoAtivo);
            }
            case "clientes" -> {
                btnMenuClientes.setBackground(corAtivo);
                btnMenuClientes.setForeground(textoAtivo);
            }
            case "pedidos" -> {
                btnMenuPedidos.setBackground(corAtivo);
                btnMenuPedidos.setForeground(textoAtivo);
            }
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

        pnlHeader = new javax.swing.JPanel();
        pnlLogo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlNav = new javax.swing.JPanel();
        btnNavJogos = new javax.swing.JButton();
        btnNavClientes = new javax.swing.JButton();
        btnNavPedidos = new javax.swing.JButton();
        btnNavAdmin = new javax.swing.JButton();
        pnlStatusBar = new javax.swing.JPanel();
        lblStatusEsq = new javax.swing.JLabel();
        lblStatusDir = new javax.swing.JLabel();
        pnlSidebar = new javax.swing.JPanel();
        lblMenuTitulo = new javax.swing.JLabel();
        btnMenuInicio = new javax.swing.JButton();
        btnMenuJogos = new javax.swing.JButton();
        btnMenuClientes = new javax.swing.JButton();
        btnMenuPedidos = new javax.swing.JButton();
        btnMenuCupons = new javax.swing.JButton();
        btnMenuAdmin = new javax.swing.JButton();
        btnMenuRelatorios = new javax.swing.JButton();
        pnlConteudo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CyberJogos — Loja de Games");

        pnlHeader.setBackground(new java.awt.Color(0, 53, 96));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1000, 60));
        pnlHeader.setLayout(new java.awt.BorderLayout());

        pnlLogo.setBackground(new java.awt.Color(0, 53, 96));
        pnlLogo.setPreferredSize(new java.awt.Dimension(220, 60));

        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(233, 69, 96));
        lblLogo.setText("CyberJogos");
        pnlLogo.add(lblLogo);

        pnlHeader.add(pnlLogo, java.awt.BorderLayout.LINE_START);

        pnlNav.setBackground(new java.awt.Color(0, 53, 96));

        btnNavJogos.setBackground(new java.awt.Color(233, 69, 96));
        btnNavJogos.setForeground(new java.awt.Color(255, 255, 255));
        btnNavJogos.setText("Jogos");
        btnNavJogos.setBorderPainted(false);
        btnNavJogos.setFocusPainted(false);
        btnNavJogos.addActionListener(this::btnNavJogosActionPerformed);
        pnlNav.add(btnNavJogos);

        btnNavClientes.setBackground(new java.awt.Color(0, 53, 96));
        btnNavClientes.setForeground(new java.awt.Color(200, 210, 220));
        btnNavClientes.setText("Clientes");
        btnNavClientes.setBorderPainted(false);
        btnNavClientes.setFocusPainted(false);
        pnlNav.add(btnNavClientes);

        btnNavPedidos.setBackground(new java.awt.Color(0, 53, 96));
        btnNavPedidos.setForeground(new java.awt.Color(200, 210, 220));
        btnNavPedidos.setText("Pedidos");
        btnNavPedidos.setBorderPainted(false);
        btnNavPedidos.setFocusPainted(false);
        pnlNav.add(btnNavPedidos);

        btnNavAdmin.setBackground(new java.awt.Color(0, 53, 96));
        btnNavAdmin.setForeground(new java.awt.Color(200, 210, 220));
        btnNavAdmin.setText("Admin");
        btnNavAdmin.setBorderPainted(false);
        btnNavAdmin.setFocusPainted(false);
        pnlNav.add(btnNavAdmin);

        pnlHeader.add(pnlNav, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlStatusBar.setBackground(new java.awt.Color(13, 17, 23));
        pnlStatusBar.setPreferredSize(new java.awt.Dimension(1000, 28));
        pnlStatusBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStatusEsq.setForeground(new java.awt.Color(68, 85, 102));
        lblStatusEsq.setText("CyberJogos v1.0 — IFBA Irecê");
        pnlStatusBar.add(lblStatusEsq, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lblStatusDir.setForeground(new java.awt.Color(74, 222, 128));
        lblStatusDir.setText("Conectado");
        pnlStatusBar.add(lblStatusDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));

        getContentPane().add(pnlStatusBar, java.awt.BorderLayout.PAGE_END);

        pnlSidebar.setBackground(new java.awt.Color(13, 27, 42));
        pnlSidebar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlSidebar.setPreferredSize(new java.awt.Dimension(180, 600));
        pnlSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMenuTitulo.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblMenuTitulo.setForeground(new java.awt.Color(68, 85, 102));
        lblMenuTitulo.setText("MENU");
        pnlSidebar.add(lblMenuTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 40, 20));

        btnMenuInicio.setBackground(new java.awt.Color(13, 27, 42));
        btnMenuInicio.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnMenuInicio.setForeground(new java.awt.Color(136, 153, 170));
        btnMenuInicio.setText("Início");
        btnMenuInicio.setBorderPainted(false);
        btnMenuInicio.setFocusPainted(false);
        btnMenuInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuInicio.setMaximumSize(new java.awt.Dimension(180, 40));
        btnMenuInicio.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlSidebar.add(btnMenuInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        btnMenuJogos.setBackground(new java.awt.Color(13, 27, 42));
        btnMenuJogos.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnMenuJogos.setForeground(new java.awt.Color(136, 153, 170));
        btnMenuJogos.setText("Jogos");
        btnMenuJogos.setBorderPainted(false);
        btnMenuJogos.setFocusPainted(false);
        btnMenuJogos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuJogos.setMaximumSize(new java.awt.Dimension(180, 40));
        btnMenuJogos.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlSidebar.add(btnMenuJogos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        btnMenuClientes.setBackground(new java.awt.Color(13, 27, 42));
        btnMenuClientes.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnMenuClientes.setForeground(new java.awt.Color(136, 153, 170));
        btnMenuClientes.setText("Clientes");
        btnMenuClientes.setBorderPainted(false);
        btnMenuClientes.setFocusPainted(false);
        btnMenuClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuClientes.setMaximumSize(new java.awt.Dimension(180, 40));
        btnMenuClientes.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlSidebar.add(btnMenuClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        btnMenuPedidos.setBackground(new java.awt.Color(13, 27, 42));
        btnMenuPedidos.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnMenuPedidos.setForeground(new java.awt.Color(136, 153, 170));
        btnMenuPedidos.setText("Pedidos");
        btnMenuPedidos.setBorderPainted(false);
        btnMenuPedidos.setFocusPainted(false);
        btnMenuPedidos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuPedidos.setMaximumSize(new java.awt.Dimension(180, 40));
        btnMenuPedidos.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlSidebar.add(btnMenuPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        btnMenuCupons.setBackground(new java.awt.Color(13, 27, 42));
        btnMenuCupons.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnMenuCupons.setForeground(new java.awt.Color(136, 153, 170));
        btnMenuCupons.setText("Cupons");
        btnMenuCupons.setBorderPainted(false);
        btnMenuCupons.setFocusPainted(false);
        btnMenuCupons.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuCupons.setMaximumSize(new java.awt.Dimension(180, 40));
        btnMenuCupons.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlSidebar.add(btnMenuCupons, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        btnMenuAdmin.setBackground(new java.awt.Color(13, 27, 42));
        btnMenuAdmin.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnMenuAdmin.setForeground(new java.awt.Color(136, 153, 170));
        btnMenuAdmin.setText("Admin");
        btnMenuAdmin.setBorderPainted(false);
        btnMenuAdmin.setFocusPainted(false);
        btnMenuAdmin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuAdmin.setMaximumSize(new java.awt.Dimension(180, 40));
        btnMenuAdmin.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlSidebar.add(btnMenuAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        btnMenuRelatorios.setBackground(new java.awt.Color(13, 27, 42));
        btnMenuRelatorios.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnMenuRelatorios.setForeground(new java.awt.Color(136, 153, 170));
        btnMenuRelatorios.setText("Relatórios");
        btnMenuRelatorios.setBorderPainted(false);
        btnMenuRelatorios.setFocusPainted(false);
        btnMenuRelatorios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuRelatorios.setMaximumSize(new java.awt.Dimension(180, 40));
        btnMenuRelatorios.setPreferredSize(new java.awt.Dimension(180, 40));
        pnlSidebar.add(btnMenuRelatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        getContentPane().add(pnlSidebar, java.awt.BorderLayout.LINE_START);

        pnlConteudo.setBackground(new java.awt.Color(17, 24, 39));
        pnlConteudo.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlConteudo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavJogosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavJogosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNavJogosActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenuAdmin;
    private javax.swing.JButton btnMenuClientes;
    private javax.swing.JButton btnMenuCupons;
    private javax.swing.JButton btnMenuInicio;
    private javax.swing.JButton btnMenuJogos;
    private javax.swing.JButton btnMenuPedidos;
    private javax.swing.JButton btnMenuRelatorios;
    private javax.swing.JButton btnNavAdmin;
    private javax.swing.JButton btnNavClientes;
    private javax.swing.JButton btnNavJogos;
    private javax.swing.JButton btnNavPedidos;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenuTitulo;
    private javax.swing.JLabel lblStatusDir;
    private javax.swing.JLabel lblStatusEsq;
    private javax.swing.JPanel pnlConteudo;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlNav;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlStatusBar;
    // End of variables declaration//GEN-END:variables
}
