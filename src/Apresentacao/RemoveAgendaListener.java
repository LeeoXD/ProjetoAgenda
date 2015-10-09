package Apresentacao;

import Negocio.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pcqs on 07/10/2015.
 */
public class RemoveAgendaListener implements ActionListener {

    public static Agenda agenda;
    public static DefaultListModel<Agenda> listaAgendas = new DefaultListModel<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        Fachada.removeAgenda(agenda);
        listaAgendas = Fachada.atualizaAgendas(listaAgendas);
        ((JButton)e.getSource()).transferFocusBackward();
    }
}
