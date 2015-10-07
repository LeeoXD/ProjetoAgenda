package Negocio; /**
 * Created by pcqs on 07/10/2015.
 */

import Negocio.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Fachada {
    private static Agendas gerenciador = new Agendas();
    private static Filtro filtro;

    public static void criaAgenda(String titulo) {
        gerenciador.criaAgenda(titulo);
    }

    public static void removeAgenda(Agenda ag) {
        gerenciador.removeAgenda(ag);
    }

    public static void insereCompromisso(Agenda age, String titulo, String assunto, String local, LocalDateTime dataIn, LocalDateTime dataOut) {
        age.inserirCompromisso(titulo, assunto, local, dataIn, dataOut);
    }

    public static void removeCompromisso(Agenda ag, Compromisso com) {
        ag.removerCompromisso(com);
    }

    public static ArrayList<Agenda> getAgendas() {
        return gerenciador.getAgendas();
    }

    public static List<Compromisso> aplicaFiltro(int tipo, String param, Agenda ag) {
        String aux = "";
        switch (tipo) {
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
                break;
            case 5:
                filtro = new FiltraLocal();
                break;
        }
        List<Compromisso> comps;
        if (!aux.equals("")) {
            if (tipo == 4) {
                comps = ((FiltraAssunto) filtro).filtraLista(ag.getCompromissos(), aux);
            } else {
                comps = ((FiltraLocal) filtro).filtraLista(ag.getCompromissos(), aux);
            }
        } else {
            comps = filtro.filtraLista(ag.getCompromissos());
        }
        return comps;
    }

    public static DefaultListModel<Agenda> atualizaAgendas(DefaultListModel<Agenda> listaAgendas) {
        listaAgendas = new DefaultListModel<Agenda>();
        for(Agenda a : gerenciador.getAgendas()) {
            listaAgendas.addElement(a);
        }
        return listaAgendas;
    }

    public static DefaultListModel<Compromisso> atualizaComps(DefaultListModel<Compromisso> listaModel, List<Compromisso> listaComps) {
        listaModel = new DefaultListModel<>();
        for(Compromisso a : listaComps) {
            listaModel.addElement(a);
        }
        return listaModel;
    }
}
