import java.time.LocalDateTime;

/**
 * Created by pcqs on 05/10/2015.
 */
public class Compromisso {

    private String titulo, assunto, local;
    private LocalDateTime dataIn, dataOut;

    public Compromisso(String titulo, String assunto, String local, LocalDateTime dataIn, LocalDateTime dataOut) {
        this.titulo = titulo;
        this.assunto = assunto;
        this.local = local;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataIn() {
        return dataIn;
    }

    public void setDataIn(LocalDateTime dataIn) {
        this.dataIn = dataIn;
    }

    public LocalDateTime getDataOut() {
        return dataOut;
    }

    public void setDataOut(LocalDateTime dataOut) {
        this.dataOut = dataOut;
    }

    public String toString() {
        return "Compromisso: " + titulo + " Assunto: " + assunto + " Local: " + local + " Data de Início: " + dataIn.toString() + " Data de Término: " + dataOut.toString();
    }
}
