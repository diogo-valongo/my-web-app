import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String nome;
    protected String login;
    protected String email;
    protected String afiliacao;
    
    public Long getId(){
        return this.id;
    }
    
    public void setID(Long id){
        this.id = id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
   public void setNome(String nome){
       this.nome = nome;
   }
   
   public String getLogin(){
       return this.login;
   }

   public void setLogin(String login){
       this.login = login;
   }
   
    public String getEmail(){
       return this.email;
   }
 
   public void setEmail(String email){
       this.email = email;
   }
    
    public String getAfiliacao(){
       return this.afiliacao;
   }
    
   public void setAfiliacao(String afiliacao){
       this.afiliacao = afiliacao;
   }
}
