import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Agendas agendas = new Agendas();
    private DefaultListModel<Agenda> listaAgendas = new DefaultListModel<Agenda>();
    private DefaultListModel<Compromisso> listaComp = new DefaultListModel<Compromisso>();
    private Filtro filtro;

    public Interface() {
        criaAgendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agenda ag = new Agenda(TextField.getText());
                agendas.criaAgenda(ag);
                atualizaModel();
                list1.setModel(listaAgendas);
                list1.setSelectedIndex(0);
            }
        });
        criarCompromissoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Digite o titulo do compromisso:");
                String assunto = JOptionPane.showInputDialog("Digite o assunto do compromisso:");
                String local = JOptionPane.showInputDialog("Digite o local do compromisso:");
                String dataI = JOptionPane.showInputDialog("Digite a data de inicio do compromisso(YYYY-MM-DD'T'HH:MM:SS):");
                LocalDateTime dataIn = LocalDateTime.parse(dataI);
                String dataO = JOptionPane.showInputDialog("Digite a data de termino do compromisso(YYYY-MM-DD'T'HH:MM:SS):");
                LocalDateTime dataOut = LocalDateTime.parse(dataO);
                Compromisso comp = new Compromisso(titulo, assunto, local, dataIn, dataOut);
                Agenda a = (Agenda) list1.getSelectedValue();
                a.inserirCompromisso(comp);
                atualizaComps(a.getCompromissos());
                list2.setModel(listaComp);
            }
        });
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Agenda a = (Agenda) list1.getSelectedValue();
                atualizaComps(a.getCompromissos());
                list2.setModel(listaComp);
            }
        });
        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pos = comboBox1.getSelectedIndex();
                String aux = "";
                switch (pos) {
                    case 0:
                        break;
                    case 1:
                        filtro = new FiltraMesAtual();
                        break;
                    case 2:
                        filtro = new FiltraDiaAtual();
                        break;
                    case 3:
                        filtro = new FiltraSemanaAtual();
                        break;
                    case 4:
                        filtro = new FiltraAssunto();
                        aux = JOptionPane.showInputDialog(comboBox1, "Digite o assunto:");
                        break;
                    case 5:
                        filtro = new FiltraLocal();
                        aux = JOptionPane.showInputDialog(comboBox1, "Digite o local:");
                        break;
                }
                Agenda ag = (Agenda) list1.getSelectedValue();
                List<Compromisso> comps;
                if (!aux.equals("")) {
                    if(pos == 4) {
                        comps = ((FiltraAssunto)filtro).filtraLista(ag.getCompromissos(), aux);
                    }
                    else {
                        comps = ((FiltraLocal)filtro).filtraLista(ag.getCompromissos(), aux);
                    }
                } else {
                    comps = filtro.filtraLista(ag.getCompromissos());
                }
                atualizaComps(comps);
                list2.setModel(listaComp);
            }
    });
        list1.setModel(listaAgendas);
        list2.setModel(listaComp);
    }

    public void atualizaModel() {
        listaAgendas = new DefaultListModel<Agenda>();
        for(Agenda a : agendas.getAgendas()) {
            listaAgendas.addElement(a);
        }
    }

    public void atualizaComps(List<Compromisso> compromissos) {
        listaComp = new DefaultListModel<Compromisso>();
        for(Compromisso c : compromissos) {
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
}
