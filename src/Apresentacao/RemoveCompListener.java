package Apresentacao;

import Negocio.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pcqs on 07/10/2015.
 */
public class RemoveCompListener implements ActionListener{

    public static Compromisso comp = new Compromisso();
    public static Agenda ag = new Agenda();
    public static DefaultListModel<Compromisso> listaComp = new DefaultListModel<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        Fachada.removeCompromisso(ag, comp);
        listaComp = Fachada.atualizaComps(listaComp,ag.getCompromissos());
        ((JButton)e.getSource()).transferFocusBackward();
    }
}
