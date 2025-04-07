package service;

import entity.Usuario;
import repository.UsuarioRepository;

//** Classe de serviço que concentra as validações de negócio.
// Após validar, os métodos chamam o repositório para efetivar a operação.
public class UsuarioService {
    private UsuarioRepository repository;

    //** Construtor que recebe o repositório
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    
    //** Valida o CPF: deve ter exatamente 11 dígitos numéricos
    public void validarCpf(String cpf) throws Exception {
        if(cpf == null || !cpf.matches("\\d{11}")) {
            throw new Exception("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
        }
    }
    
    //** Valida o nome: não pode ser vazio
    public void validarNome(String nome) throws Exception {
        if(nome == null || nome.trim().isEmpty()){
            throw new Exception("Nome inválido. Não pode ser vazio.");
        }
    }
    
    //** Valida a idade: deve ser maior que zero
    public void validarIdade(int idade) throws Exception {
        if(idade <= 0){
            throw new Exception("Idade inválida. Deve ser maior que zero.");
        }
    }
    
    //** Valida o ID: deve ser maior que zero
    public void validarId(int id) throws Exception {
        if(id <= 0){
            throw new Exception("ID inválido. Deve ser maior que zero.");
        }
    }
    
    //** Método que valida todos os dados do usuário
    public void validarUsuario(Usuario usuario) throws Exception {
        validarId(usuario.getId());
        validarNome(usuario.getNome());
        validarCpf(usuario.getCpf());
        validarIdade(usuario.getIdade());
    }
    
    //** Método para cadastrar usuário após validação
    public boolean cadastrarUsuario(Usuario usuario) throws Exception {
        validarUsuario(usuario);
        return repository.insert(usuario);
    }
    
    //** Método para atualizar usuário após validação
    public boolean atualizarUsuario(Usuario usuario) throws Exception {
        validarUsuario(usuario);
        return repository.update(usuario);
    }
    
    //** Outros métodos de negócio podem ser adicionados com as devidas validações
}
