import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by pcqs on 06/10/2015.
 */
public class FiltraMesAtual implements Filtro {

    @Override
    public List<Compromisso> filtraLista(List<Compromisso> compromissos) {
        List<Compromisso> res = compromissos.stream()
                .filter(com -> com.getDataIn().toLocalDate().getYear() == LocalDate.now().getYear() &&
                        com.getDataIn().toLocalDate().getMonth().equals(LocalDate.now().getMonth()))
                .collect(Collectors.toList());
        return res;
    }
}
