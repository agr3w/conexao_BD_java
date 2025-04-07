# Sistema de Cadastro de Usuários

Este é um projeto simples desenvolvido em Java para gerenciar o cadastro de usuários. Ele permite realizar operações como cadastrar, editar, remover e consultar usuários em um banco de dados.

## Funcionalidades

- **Cadastrar Usuário**: Adiciona um novo usuário ao sistema.
- **Editar Usuário**: Permite atualizar os dados de um usuário existente.
- **Remover Usuário**: Exclui um usuário do sistema.
- **Consultar Usuários**:
  - Listar todos os usuários cadastrados.
  - Buscar usuário pelo CPF.
  - Buscar usuários pelas iniciais do nome.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **JDBC**: Para conexão com o banco de dados.
- **MySQL**: Banco de dados utilizado.
- **Visual Studio Code**: IDE para desenvolvimento.

## Estrutura do Projeto

```plaintext
.
├── src/
│   ├── app/
│   │   └── [Main.java](src/app/Main.java)                # Classe principal
│   ├── config/
│   │   └── [DatabaseConnection.java](src/config/DatabaseConnection.java) # Gerenciamento da conexão com o banco
│   ├── entity/
│   │   └── [Usuario.java](src/entity/Usuario.java)            # Classe que representa o usuário
│   ├── repository/
│   │   ├── [UsuarioRepository.java](src/repository/UsuarioRepository.java)  # Interface do repositório
│   │   └── [UsuarioRepositoryImpl.java](src/repository/UsuarioRepositoryImpl.java) # Implementação do repositório
│   ├── screens/
│   │   └── [TelaUsuario.java](src/screens/TelaUsuario.java)        # Interface de interação com o usuário
│   └── service/
│       └── [UsuarioService.java](src/service/UsuarioService.java)     # Regras de negócio
├── bin/                            # Arquivos compilados
├── lib/                            # Dependências externas
└── .vscode/                        # Configurações do Visual Studio Code
```

## Configuração do Ambiente

1. Certifique-se de ter o **Java JDK** instalado (versão 8 ou superior).
2. Configure um banco de dados MySQL com as seguintes credenciais (ou ajuste no arquivo `DatabaseConnection.java`):
   - **URL**: `jdbc:mysql://localhost:3306/sistema_usuarios`
   - **Usuário**: `root`
   - **Senha**: `sua_senha`
3. Crie a tabela no banco de dados com o seguinte comando SQL:
   ```sql
   CREATE TABLE usuarios (
       id INT PRIMARY KEY AUTO_INCREMENT,
       nome VARCHAR(100),
       cpf VARCHAR(11) UNIQUE,
       idade INT
   );
   ```
4. Adicione o driver do MySQL (arquivo `.jar`) na pasta `lib/` e configure no arquivo `.vscode/settings.json`.

## Como Executar

1. Compile o projeto:
   ```bash
   javac -d bin -sourcepath src src/app/Main.java
   ```
2. Execute o programa:
   ```bash
   java -cp "bin;lib/mysql-connector-java-8.0.32.jar" app.Main
   ```

## Observações

- Certifique-se de que o banco de dados MySQL esteja em execução antes de iniciar o programa.
- O driver MySQL utilizado deve ser compatível com a versão do seu banco de dados.

---
