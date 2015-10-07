package Negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pcqs on 06/10/2015.
 */
public class FiltraDiaAtual implements Filtro {

    @Override
    public List<Compromisso> filtraLista(List<Compromisso> compromissos) {
        List<Compromisso> res = compromissos.stream()
                .filter(com -> com.getDataIn().toLocalDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList());
        return res;
    }
}
