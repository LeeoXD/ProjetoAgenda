package Negocio;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pcqs on 06/10/2015.
 */
public class FiltraAssunto implements Filtro{

    private String assunto;

    public List<Compromisso> filtraLista(List<Compromisso> comp, String assunt) {
        assunto = assunt;
        return filtraLista(comp);
    }

    @Override
    public List<Compromisso> filtraLista(List<Compromisso> compromissos) {
        List<Compromisso> res = compromissos.stream()
                .filter(com -> com.getAssunto().equals(assunto))
                .collect(Collectors.toList());
        return res;
    }

}
