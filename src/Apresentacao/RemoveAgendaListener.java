package Apresentacao;

import Negocio.Agenda;
import Negocio.Fachada;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pcqs on 07/10/2015.
 */
public class RemoveAgendaListener implements ActionListener {

    public static Agenda agenda;
    public static DefaultListModel<Agenda> listaAgendas;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(listaAgendas.toString());
        Fachada.removeAgenda(agenda);
        listaAgendas = Fachada.atualizaAgendas(listaAgendas);
        System.out.println(listaAgendas.toString());
        ((JButton)e.getSource()).transferFocusBackward();
    }
}
