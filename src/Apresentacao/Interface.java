package Apresentacao;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;

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
    private JButton carregarButton;
    private JButton salvarButton;
    private JButton salvarButton1;
    private JButton carregarButton1;
    private DefaultListModel<Agenda> listaAgendas = new DefaultListModel<Agenda>();
    private DefaultListModel<Compromisso> listaComp = new DefaultListModel<Compromisso>();

    public Interface() {
        criaAgendaButton.addActionListener(new CriaAgendaListener());
        criarCompromissoButton.addActionListener(new CriaCompromissoListener());
        filtrarButton.addActionListener(new FiltrarListener());
        verInfoButton.addActionListener(new VerInfoListener());
        removeAgendaButton.addFocusListener(new RemoveAgendaListenerAux());
        removeAgendaButton.addActionListener(new RemoveAgendaListener());
        removeCompromissoButton.addFocusListener(new RemoveCompListenerAux());
        removeCompromissoButton.addActionListener(new RemoveCompListener());
        salvarButton.addActionListener(new SalvarXMLListener());
        carregarButton.addActionListener(new CarregarXMLListener());
        list1.addMouseListener(new ListaListener());
        list1.setModel(listaAgendas);
        list2.setModel(listaComp);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interface");
        frame.setContentPane(new Interface().painel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private class ListaListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(listaAgendas.size() > 0) {
                Agenda a = (Agenda) list1.getSelectedValue();
                listaComp = Fachada.atualizaComps(listaComp, a.getCompromissos());
                list2.setModel(listaComp);
                list2.setSelectedIndex(0);
            }
        }
    }

    private class VerInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Compromisso comp = (Compromisso) list2.getSelectedValue();
            JOptionPane.showMessageDialog(verInfoButton, comp.getInfo(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class RemoveAgendaListenerAux extends FocusAdapter {
        public void focusLost(FocusEvent e) {
            listaAgendas = RemoveAgendaListener.listaAgendas;
            list1.setModel(listaAgendas);
            list2.setModel(new DefaultListModel<Compromisso>());

        }
        public void focusGained(FocusEvent e) {
            RemoveAgendaListener.agenda = (Agenda) list1.getSelectedValue();
            RemoveAgendaListener.listaAgendas = listaAgendas;
        }
    }

    private class CarregarXMLListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = JOptionPane.showInputDialog(carregarButton,"Digite o nome do arquivo(sem extensao):");
            Fachada.carregaXML(nome);
            listaAgendas = Fachada.atualizaAgendas(listaAgendas);
            list1.setModel(listaAgendas);
        }
    }

    private class SalvarXMLListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = JOptionPane.showInputDialog(salvarButton,"Digite o nome do arquivo(Sem extensao):");
            Fachada.salvaXML(nome);
        }
    }

    private class RemoveCompListenerAux extends FocusAdapter {
        public void focusLost(FocusEvent e) {
            listaComp = RemoveCompListener.listaComp;
            list2.setModel(listaComp);
        }
        public void focusGained(FocusEvent e) {
            if(listaAgendas.size() > 0 && listaComp.size() > 0) {
                RemoveCompListener.ag = (Agenda) list1.getSelectedValue();
                RemoveCompListener.comp = (Compromisso) list2.getSelectedValue();
                RemoveCompListener.listaComp = listaComp;
            }
        }
    }

    private class CriaCompromissoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(list1.getSelectedValue() == null || listaAgendas.size() <= 0) {
                JOptionPane.showMessageDialog(criarCompromissoButton,"Selecione uma agenda antes de criar um compromisso","Aviso",JOptionPane.WARNING_MESSAGE);
            }
            else {
                String titulo = JOptionPane.showInputDialog("Digite o titulo do compromisso:");
                String assunto = JOptionPane.showInputDialog("Digite o assunto do compromisso:");
                String local = JOptionPane.showInputDialog("Digite o local do compromisso:");
                String dataI = JOptionPane.showInputDialog("Digite a data de inicio do compromisso(YYYY-MM-DD'T'HH:MM:SS):");
                String dataO = JOptionPane.showInputDialog("Digite a data de termino do compromisso(YYYY-MM-DD'T'HH:MM:SS):");
                Agenda age = (Agenda) list1.getSelectedValue();
                LocalDateTime dataIn = LocalDateTime.parse(dataI);
                LocalDateTime dataOut = LocalDateTime.parse(dataO);
                Fachada.insereCompromisso(age, titulo, assunto, local, dataIn, dataOut);
                listaComp = Fachada.atualizaComps(listaComp, age.getCompromissos());
                list2.setModel(listaComp);
            }
        }
    }

    private class CriaAgendaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String texto;
            if(TextField.getText().equals("") || TextField.getText().matches("[\\s]+")) {
                texto = "Sem titulo";
            }
            else {
                texto = TextField.getText();
            }
            Fachada.criaAgenda(texto);
            listaAgendas = Fachada.atualizaAgendas(listaAgendas);
            list1.setModel(listaAgendas);
            list1.setSelectedIndex(list1.getModel().getSize() - 1);
        }
    }

    private class FiltrarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = comboBox1.getSelectedIndex();
            if((listaComp.size() <= 0 || listaAgendas.size() <= 0) && index > 0) {
                JOptionPane.showMessageDialog(filtrarButton,"Nao ha compromissos para filtrar", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else {
                Agenda agenda = (Agenda) list1.getSelectedValue();
                String param = "";
                if (comboBox1.getSelectedIndex() == 4 || comboBox1.getSelectedIndex() == 5) {
                    if (comboBox1.getSelectedIndex() == 4) {
                        param = JOptionPane.showInputDialog(comboBox1, "Digite o assunto:");
                    } else if (comboBox1.getSelectedIndex() == 5) {
                        param = JOptionPane.showInputDialog(comboBox1, "Digite o local:");
                    }
                }
                listaComp = Fachada.atualizaComps(listaComp, Fachada.aplicaFiltro(index, param, agenda));
                list2.setModel(listaComp);
            }
        }
    }
}

