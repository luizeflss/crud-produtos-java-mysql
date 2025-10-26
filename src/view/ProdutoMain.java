package view;
import dao.ProdutoDAO;
import model.Produto;
import java.util.Scanner;

public class ProdutoMain {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ProdutoDAO dao = new ProdutoDAO();
        
        int opc = -1;
        
        while (opc != 0){
            System.out.println("\n==== MNEU PRODUTOS ====");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar os produtos");
            System.out.println("3 - Atualizar produto");
            System.out.println("4 - Excluir produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
             
            opc = scn.nextInt();
            scn.nextLine();
            
            switch (opc){
                case 1:
                    //cadastrar
                    Produto p = new Produto();
                    
                    System.out.print("Nome: ");
                    p.setNome(scn.nextLine());
                    
                    System.out.print("Quantidade: ");
                    p.setQuantidade(scn.nextInt());
                    
                    System.out.print("Valor Unitário: ");
                    p.setValorUnitario(scn.nextDouble());
                    scn.nextLine();//limpa o resto do enter
                    
                    dao.inserir(p);
                    break;
                case 2:
                    //listar
                    for(Produto prod : dao.listarTodos()){
                        System.out.println(
                                prod.getId() + " | " +
                                prod.getNome() + " | " +
                                prod.getQuantidade() + " uni | R$ " +
                                prod.getValorUnitario()
                        );
                    }
                    break;
                case 3:
                    //atualizar
                    Produto prodAtualizar = new Produto();
                    
                    System.out.print("ID do produto a atualizar: ");
                    prodAtualizar.setId(scn.nextInt());
                    scn.nextLine();
                    
                    System.out.print("Novo nome: ");
                    prodAtualizar.setNome(scn.nextLine());
                    
                    System.out.print("Nova quantidade : ");
                    prodAtualizar.setQuantidade(scn.nextInt());
                    
                    System.out.print("Novo valor unitário: ");
                    prodAtualizar.setValorUnitario(scn.nextDouble());
                    scn.nextLine();
                    
                    dao.atualizar(prodAtualizar);
                    break;
                case 4:
                    //excluir
                    System.out.print("Digite o ID do produto a excluir: ");
                    int idExcluir = scn.nextInt();
                    scn.nextLine();
                    
                    dao.excluir(dao.buscaPorID(idExcluir));
                    break;
                case 0: 
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }
        scn.close();
    }    
}
