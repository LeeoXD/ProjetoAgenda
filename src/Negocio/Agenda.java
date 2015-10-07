package Negocio;

import java.time.LocalDateTime;
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

    public void inserirCompromisso(String titulo, String assunto, String local, LocalDateTime dataIn, LocalDateTime dataOut) {
        Compromisso comp = new Compromisso(titulo, assunto, local, dataIn, dataOut);
        compromissos.add(comp);
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
