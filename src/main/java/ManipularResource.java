import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("manipular")
@RequestScoped
public class ManipularResource {

    private final JsonBuilderFactory factory;
    
    public ManipularResource() {
        factory = Json.createBuilderFactory(null);
    }

    @POST
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public void cadastra(@FormParam("nome") String nome, @FormParam("login") String login, @FormParam("email") String email, @FormParam("afiliacao") String afi) {
        JPAUsuarioDAO dao = new JPAUsuarioDAO();
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setLogin(login);
        u.setEmail(email);
        u.setAfiliacao(afi);
        dao.cadastra(u);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject createJSON(@PathParam("id") Long id){
        JPAUsuarioDAO dao = new JPAUsuarioDAO();
        Usuario u = dao.recupera(id);
        if(u==null){
            throw new RuntimeException("usuario não encontrado...");
        }
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder builder = factory.createObjectBuilder();
        JsonObject obj = builder.add("nome", u.getNome()).add("email", u.getEmail())
            .add("login", u.getLogin()).add("affiliacao", u.getAfiliacao()).build();
        return obj;
    }
}
