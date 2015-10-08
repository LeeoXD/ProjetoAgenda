package Persistencia;

import Negocio.Agendas;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * Created by pcqs on 08/10/2015.
 */
public class GerenciaXML {

    private static XStream xstream = new XStream();

    public static boolean Salva(Agendas agendas, String nomeArq) {
        try {
            xstream.toXML(agendas, new BufferedOutputStream(new FileOutputStream(nomeArq + ".xml")));
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public static Agendas Carrega(String nomeArq) {
        Agendas agendas = new Agendas();
        try {
            agendas = (Agendas) xstream.fromXML(new FileReader(nomeArq + ".xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return agendas;
    }
}
