package Negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pcqs on 06/10/2015.
 */
public class FiltraSemanaAtual implements Filtro {

    @Override
    public List<Compromisso> filtraLista(List<Compromisso> compromissos) {
        List<Compromisso> res = compromissos.stream()
                .filter(com -> LocalDate.now().until(com.getDataIn().toLocalDate()).getDays() < 7 &&
                        LocalDate.now().until(com.getDataIn().toLocalDate()).getMonths() == 0)
                .collect(Collectors.toList());
        return res;
    }
}
