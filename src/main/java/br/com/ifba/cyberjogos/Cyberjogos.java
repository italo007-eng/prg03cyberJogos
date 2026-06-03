/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.ifba.cyberjogos;

import br.com.ifba.cyberjogos.view.TelaPrincipal;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import javax.swing.SwingUtilities;
/**
 *
 * @author Italo
 */
@SpringBootApplication
public class Cyberjogos {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
       
        ConfigurableApplicationContext context =
            new SpringApplicationBuilder(Cyberjogos.class)
                .web(WebApplicationType.NONE)
                .run(args);

        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = context.getBean(TelaPrincipal.class);
            tela.setVisible(true);
        });
    }
}
