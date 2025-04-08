package util;

public class ErrorHandler {
    // ** Método para exibir uma mensagem de erro genérica
    public static void handle(Exception e, String mensagem) {
        System.out.println("Erro: " + mensagem);
        System.out.println("Detalhes técnicos: " + e.getMessage());
    }

}
