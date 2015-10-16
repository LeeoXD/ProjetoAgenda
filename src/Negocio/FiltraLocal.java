package Negocio;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pcqs on 06/10/2015.
 */
public class FiltraLocal implements Filtro {

    private String local;

    public List<Compromisso> filtraLista(List<Compromisso> comp, String local) {
        this.local = local;
        return filtraLista(comp);
    }

    @Override
    public List<Compromisso> filtraLista(List<Compromisso> compromissos) {
        List<Compromisso> res = compromissos.stream()
                .filter(com -> com.getAssunto().equals(local))
                .collect(Collectors.toList());
        return res;
    }
}
