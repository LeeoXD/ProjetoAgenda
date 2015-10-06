import java.util.List;
import java.util.function.Predicate;

/**
 * Created by pcqs on 06/10/2015.
 */
public interface Filtro {

    List<Compromisso> filtraLista(List<Compromisso> compromissos);
}
