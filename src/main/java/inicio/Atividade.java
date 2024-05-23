package inicio;
import java.time.LocalTime;
import java.time.LocalDate;

        
public class Atividade {
    protected long id;
    public String nome;
    public enum tipo {FESTA, TESTE};
    public String descricao;
    public LocalDate data;
    public LocalTime horarioInicial;
    public LocalTime horarioFinal;
}
