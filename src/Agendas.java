import java.util.ArrayList;

/**
 * Created by pcqs on 05/10/2015.
 */
public class Agendas {

    private ArrayList<Agenda> agendas;

    public Agendas() {
        agendas = new ArrayList<Agenda>();
    }

    public boolean criaAgenda(Agenda ag) {
        return agendas.add(ag);
    }

    public boolean removeAgenda(Agenda ag) {
        return agendas.remove(ag);
    }

    public ArrayList<Agenda> getAgendas() {
        return agendas;
    }
}
