import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class JPAUsuarioDAO {
    
    private EntityManager em;
    
    public JPAUsuarioDAO(){}
    
    public void cadastra(Usuario u){
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        em.persist(u);
        
        et.commit();
        em.close();
    }
    
    public Usuario recupera(Long id){
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        Usuario u = em.find(Usuario.class, id);
        
        et.commit();
        em.close();
        
        return u;
    }
}
