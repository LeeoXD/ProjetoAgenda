package Apresentacao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Negocio.Agenda;
import Negocio.Fachada;

import javax.swing.*;

/**
 * Created by pcqs on 07/10/2015.
 */

public class CriaAgendaListener implements ActionListener {

    public static String texto = "";
    public static DefaultListModel<Agenda> listaAgendas;

    @Override
    public void actionPerformed(ActionEvent e) {
        Fachada.criaAgenda(texto);
        listaAgendas = Fachada.atualizaAgendas(listaAgendas);
        ((JButton)e.getSource()).transferFocus();
    }
}
