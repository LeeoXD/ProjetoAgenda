package Persistencia;

import Negocio.Agenda;
import Negocio.Agendas;
import Negocio.Compromisso;
import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.DateStart;
import biweekly.property.Summary;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pcqs on 14/10/2015.
 */
public class GerenciaICalendar {

    public static void salvarDados(Agendas gerente) {

        for (int i = 0; i < gerente.getAgendas().size(); i++) {
            ICalendar ical = new ICalendar();
            Agenda ag = gerente.getAgendas().get(i);
            for (int j = 0; j < ag.getCompromissos().size(); j++) {
                Compromisso comp = ag.getCompromissos().get(j);
                VEvent evento = converteComp(comp);
                ical.addEvent(evento);
            }
            File file = new File("Agenda - " + ag.getTitulo() + ".ics");
            try {
                Biweekly.write(ical).go(file);
            } catch (IOException ex) {
                Logger.getLogger(GerenciaICalendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static VEvent converteComp(Compromisso comp){
        VEvent event = new VEvent();
        event.setSummary(comp.getTitulo());
        event.setDescription(comp.getAssunto());
        event.setLocation(comp.getLocal());
        LocalDateTime inicio = comp.getDataIn();
        inicio = inicio.minusHours(2);
        LocalDateTime fim = comp.getDataOut();
        fim = fim.minusHours(2);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String inicio1 = inicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String fim1 = fim.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        try {
            event.setDateStart(df.parse(inicio1));
            event.setDateEnd(df.parse(fim1));
        } catch (ParseException ex) {
            Logger.getLogger(GerenciaICalendar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }

}
