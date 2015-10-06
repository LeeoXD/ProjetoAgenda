import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by pcqs on 05/10/2015.
 */
public class Agenda {

    private String titulo;
    private ArrayList<Compromisso> compromissos;
    private Filtro filtro;

    public Agenda(String tit) {
        titulo = tit;
        compromissos = new ArrayList<Compromisso>();
    }

    public void inserirCompromisso(Compromisso com) {
        compromissos.add(com);
    }

    public void removerCompromisso(Compromisso com) {
        compromissos.remove(com);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String toString() {
        return titulo;
    }

    public ArrayList<Compromisso> getCompromissos() {
        return compromissos;
    }
}
