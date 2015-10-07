package Apresentacao;

import Negocio.Agenda;
import Negocio.Compromisso;
import Negocio.Fachada;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

/**
 * Created by pcqs on 07/10/2015.
 */
public class CriaCompromissoListener implements ActionListener {

    public static String titulo, assunto, local, dataI, dataO;
    public static Agenda age;
    public static DefaultListModel<Compromisso> lista = new DefaultListModel<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        LocalDateTime dataIn = LocalDateTime.parse(dataI);
        LocalDateTime dataOut = LocalDateTime.parse(dataO);
        Fachada.insereCompromisso(age, titulo, assunto, local, dataIn, dataOut);
        System.out.println();
        lista = Fachada.atualizaComps(lista, age.getCompromissos());
        ((JButton)e.getSource()).transferFocusBackward();
    }
}
