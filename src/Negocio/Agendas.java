package Negocio;

import java.util.ArrayList;

/**
 * Created by pcqs on 05/10/2015.
 */
public class Agendas {

    private ArrayList<Agenda> agendas;

    public Agendas() {
        agendas = new ArrayList<Agenda>();
    }

    public boolean criaAgenda(String titulo) {
        return agendas.add(new Agenda(titulo));
    }

    public boolean removeAgenda(Agenda ag) {
        return agendas.remove(ag);
    }

    public ArrayList<Agenda> getAgendas() {
        return agendas;
    }
}
