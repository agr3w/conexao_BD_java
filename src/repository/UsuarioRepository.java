package repository;

import entity.Usuario;
import java.util.List;

//** Interface que define os métodos para persistência dos dados do usuário
public interface UsuarioRepository {
    boolean insert(Usuario usuario);
    boolean update(Usuario usuario);
    boolean delete(int id);
    Usuario findById(int id);
    Usuario findByCpf(String cpf);
    List<Usuario> findAll();
    List<Usuario> findByNomeInicial(String iniciais);
}
