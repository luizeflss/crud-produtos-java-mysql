package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {
    
    private Connection conexao;
    
    public ProdutoDAO(){
        conexao = new Conexao().getConexao();
    }
    
    //CREATE
    public void inserir(Produto produto){
        String sql = "INSERT INTO produto(nome, quantidade, valorUnitario) VALUE (?, ?, ?)";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getValorUnitario());
            
            stmt.executeUpdate();
            stmt.close();
            
            System.out.println("Produto adicionado com sucesso!");
        }catch(SQLException e){
            System.out.println("Erro ao inserir o produto: " + e.getMessage());
        }
    }
    
    //READ - Busca por ID
    public Produto buscaPorID(int id){
        try{
            String sql = "SELECT * FROM produto WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            Produto produto = null;
            if(rs.next()){
                produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getDouble("valorUnitario")
                    
                );
            }
            
            rs.close();
            stmt.close();
            return produto;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    //READ - lista todos os produtos
    public List<Produto> listarTodos(){
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";
            
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValorUnitario(rs.getDouble("valorUnitario"));
                
                lista.add(produto);
            }
            rs.close();
            stmt.close();
            
        }catch (SQLException e){
            throw new RuntimeException("Erro ao listar os produtos: " + e.getMessage());
        }
        
        return lista;
    }
    
    //UPDATE
    public void atualizar(Produto produto){
        String sql = "UPDATE produto SET nome = ?, quantidade = ?, valorUnitario = ? WHERE id = ?";
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getValorUnitario());
            stmt.setInt(4, produto.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            
            if(linhasAfetadas > 0){
                System.out.println("Produto atualizado com sucesso!");
            }else{
                System.out.println("Nenhum produto encontrado com esse ID.");
            }
            
        }catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage());
        }
    }
    
    //DELETE
    public void excluir(Produto produto){
        String sql = "DELETE FROM produto WHERE id = ?";
        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            
            if(linhasAfetadas > 0){
                System.out.println("Produto excluido com sucesso!");
            }else{
                System.out.println("Nenhum produto encontrado com esse ID.");
            }
            
        }catch (SQLException e){
            throw new RuntimeException("Erro ao excluir produto: " + e.getMessage());
        }
    }  
}
