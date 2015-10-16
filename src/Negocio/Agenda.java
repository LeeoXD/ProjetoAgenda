package Negocio;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by pcqs on 05/10/2015.
 */
public class Agenda {

    private String titulo;
    private ArrayList<Compromisso> compromissos;

    public Agenda(String tit) {
        titulo = tit;
        compromissos = new ArrayList<>();
    }

    public Agenda() {
        compromissos = new ArrayList<>();
    }

    public void inserirCompromisso(String titulo, String assunto, String local, LocalDateTime dataIn, LocalDateTime dataOut) {
        Compromisso comp = new Compromisso(titulo, assunto, local, dataIn, dataOut);
        if(compromissos.size() > 0) {
            LocalDateTime aux = compromissos.get(0).getDataIn();
            int pos = 0;
            for (int i = 0; i < compromissos.size() && comp.getDataIn().compareTo(aux) > 0; i++) {
                pos++;
                aux = compromissos.get(pos).getDataIn();
            }
            compromissos.add(pos, comp);
        }
        else {
            compromissos.add(comp);
        }
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
