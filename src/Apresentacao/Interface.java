package Apresentacao;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.List;

import Negocio.*;

/**
 * Created by pcqs on 06/10/2015.
 */
public class Interface {

    private JTextField TextField;
    private JButton criaAgendaButton;
    private JList list1;
    private JPanel painel;
    private JScrollPane scrollpane;
    private JButton criarCompromissoButton;
    private JList list2;
    private JComboBox comboBox1;
    private JButton filtrarButton;
    private JButton verInfoButton;
    private JButton removeAgendaButton;
    private JButton removeCompromissoButton;
    private DefaultListModel<Agenda> listaAgendas = new DefaultListModel<Agenda>();
    private DefaultListModel<Compromisso> listaComp = new DefaultListModel<Compromisso>();

    public Interface() {
        criaAgendaButton.addActionListener(new CriaAgendaListener());
        criarCompromissoButton.addActionListener(new CriaCompromissoListener());
        filtrarButton.addActionListener(new FiltrarListener());
        criarCompromissoButton.addActionListener(new CompromissoListenerAux());
        list1.addMouseListener(new ListaListener());
        verInfoButton.addActionListener(new VerInfoListener());
        TextField.addFocusListener(new TextListener());
        criaAgendaButton.addFocusListener(new AgendaListenerAux());
        comboBox1.addFocusListener(new ComboBoxListener());
        filtrarButton.addFocusListener(new FiltrarListenerAux());
        removeAgendaButton.addFocusListener(new RemoveAgendaListenerAux());
        removeAgendaButton.addActionListener(new RemoveAgendaListener());
        removeCompromissoButton.addFocusListener(new RemoveCompListenerAux());
        removeCompromissoButton.addActionListener(new RemoveCompListener());
        list1.setModel(listaAgendas);
        list2.setModel(listaComp);
    }

    public void atualizaComps(List<Compromisso> compromissos) {
        listaComp = new DefaultListModel<>();
        for (Compromisso c : compromissos) {
            listaComp.addElement(c);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interface");
        frame.setContentPane(new Interface().painel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private class CompromissoListenerAux implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String titulo = JOptionPane.showInputDialog("Digite o titulo do compromisso:");
            String assunto = JOptionPane.showInputDialog("Digite o assunto do compromisso:");
            String local = JOptionPane.showInputDialog("Digite o local do compromisso:");
            String dataI = JOptionPane.showInputDialog("Digite a data de inicio do compromisso(YYYY-MM-DD'T'HH:MM:SS):");
            String dataO = JOptionPane.showInputDialog("Digite a data de termino do compromisso(YYYY-MM-DD'T'HH:MM:SS):");
            CriaCompromissoListener.titulo = titulo;
            CriaCompromissoListener.assunto = assunto;
            CriaCompromissoListener.local = local;
            CriaCompromissoListener.dataI = dataI;
            CriaCompromissoListener.dataO = dataO;
            CriaCompromissoListener.age = (Agenda) list1.getSelectedValue();
        }
    }

    private class ListaListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Agenda a = (Agenda) list1.getSelectedValue();
            listaComp = Fachada.atualizaComps(listaComp,a.getCompromissos());
            list2.setModel(listaComp);
        }
    }

    private class VerInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Compromisso comp = (Compromisso) list2.getSelectedValue();
            JOptionPane.showMessageDialog(verInfoButton, comp.getInfo(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class TextListener extends FocusAdapter {
        @Override
        public void focusLost(FocusEvent e) {
            CriaAgendaListener.texto = TextField.getText();
            CriaAgendaListener.listaAgendas = listaAgendas;
        }
    }

    private class AgendaListenerAux extends FocusAdapter {
        @Override
        public void focusLost(FocusEvent e) {
            listaAgendas = CriaAgendaListener.listaAgendas;
            list1.setModel(listaAgendas);
        }
    }

    private class RemoveAgendaListenerAux extends FocusAdapter {
        public void focusLost(FocusEvent e) {
            listaAgendas = RemoveAgendaListener.listaAgendas;
            list1.setModel(listaAgendas);
        }
        public void focusGained(FocusEvent e) {
            RemoveAgendaListener.agenda = (Agenda) list1.getSelectedValue();
            RemoveAgendaListener.listaAgendas = listaAgendas;
        }
    }

    private class ComboBoxListener extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            listaComp = CriaCompromissoListener.lista;
            list2.setModel(listaComp);
        }
        @Override
        public void focusLost(FocusEvent e) {
            FiltrarListener.index = comboBox1.getSelectedIndex();
            FiltrarListener.agenda = (Agenda) list1.getSelectedValue();
            FiltrarListener.listaModel = listaComp;
            if (comboBox1.getSelectedIndex() == 4 || comboBox1.getSelectedIndex() == 4) {
                if (comboBox1.getSelectedIndex() == 4) {
                    FiltrarListener.param = JOptionPane.showInputDialog(comboBox1, "Digite o assunto:");
                } else if (comboBox1.getSelectedIndex() == 5) {
                    FiltrarListener.param = JOptionPane.showInputDialog(comboBox1, "Digite o local:");
                }
                filtrarButton.transferFocus();
            }
        }
    }

    private class FiltrarListenerAux extends FocusAdapter{
        @Override
        public void focusLost(FocusEvent e) {
            listaComp = FiltrarListener.listaModel;
            list2.setModel(listaComp);
        }
    }

    private class RemoveCompListenerAux extends FocusAdapter {
        public void focusLost(FocusEvent e) {
            listaComp = RemoveCompListener.listaComp;
            list2.setModel(listaComp);
        }
        public void focusGained(FocusEvent e) {
            RemoveCompListener.ag = (Agenda) list1.getSelectedValue();
            RemoveCompListener.comp = (Compromisso) list2.getSelectedValue();
            RemoveCompListener.listaComp = listaComp;
        }
    }

}