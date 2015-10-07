package Apresentacao;

import Negocio.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by pcqs on 07/10/2015.
 */
public class FiltrarListener implements ActionListener {

    public static int index;
    public static String param;
    public static Agenda agenda;
    public static DefaultListModel<Compromisso> listaModel;

    @Override
    public void actionPerformed(ActionEvent e) {
        listaModel = Fachada.atualizaComps(listaModel, Fachada.aplicaFiltro(index, param, agenda));
        if(index != 4 && index != 5) {
            ((JButton)e.getSource()).transferFocusBackward();
        }
    }
}
