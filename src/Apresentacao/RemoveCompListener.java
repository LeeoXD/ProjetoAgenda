package Apresentacao;

import Negocio.Agenda;
import Negocio.Compromisso;
import Negocio.Fachada;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pcqs on 07/10/2015.
 */
public class RemoveCompListener implements ActionListener{

    public static Compromisso comp;
    public static Agenda ag;
    public static DefaultListModel<Compromisso> listaComp;

    @Override
    public void actionPerformed(ActionEvent e) {
        Fachada.removeCompromisso(ag, comp);
        listaComp = Fachada.atualizaComps(listaComp,ag.getCompromissos());
        ((JButton)e.getSource()).transferFocusBackward();
    }
}
