import Negocio.Agenda;
import Negocio.Compromisso;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.time.LocalDateTime;

/**
 * Created by pcqs on 06/10/2015.
 */
public class Teste {

    public static void main(String[] args) {
        XStream xstream = new XStream();
        LocalDateTime hora = LocalDateTime.now();
        Compromisso comp = new Compromisso("Aniversario","Aniversario do Joao","Casa do Joao",hora,hora.plusHours(3));
        Compromisso comp2 = new Compromisso("Aniversario","Aniversario do Joao","Casa do Joao",hora,hora.plusHours(3));
        Compromisso comp3 = new Compromisso("Aniversario","Aniversario do Joao","Casa do Joao",hora,hora.plusHours(3));
        Compromisso comp4 = new Compromisso("Aniversario","Aniversario do Joao","Casa do Joao",hora,hora.plusHours(3));
//        Agenda ag = new Agenda("Negocio.Agenda");
//        ag.inserirCompromisso(comp);
//        ag.inserirCompromisso(comp2);
//        ag.inserirCompromisso(comp3);
//        ag.inserirCompromisso(comp4);
        try {
            //xstream.toXML(ag, new BufferedOutputStream(new FileOutputStream("Test.xml")));
            Agenda ag2 = (Agenda)xstream.fromXML(new FileReader("Test.xml"));
            System.out.println(ag2.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
