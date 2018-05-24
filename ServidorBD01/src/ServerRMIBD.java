import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//import RMIBD.RMIBD;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franklin.silva
 */
public class ServerRMIBD extends UnicastRemoteObject implements RMIBD{
    public ServerRMIBD() throws RemoteException{
            super();
    }

    @Override
    public ArrayList<Produto> buscar_produto(String codigo) throws RemoteException {
        String cod,nom;
        ArrayList<Produto> lista = new ArrayList<>();
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmibd","root","123456");
        String sql = "select * from produto where codigo = '" +codigo+ "' ";
        Statement stm = (Statement) cn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        
        while(rs.next()){
            cod = rs.getString(1);
            nom = rs.getString(2);
            Produto oempleado = new Produto(cod,nom);
            lista.add(oempleado);
        }
            cn.close();
            
        }catch(Exception e){
            System.out.println(e);
        } 
        return lista;
}

    public static void main(String[] args) {
         try {
             Registry registro=LocateRegistry.createRegistry(1099);
             registro.rebind("rmi://localhost:1099/RMIBD",new  ServerRMIBD());
             System.out.println("ServerRMIBD.Ativo");
            
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }
    
    @Override
    public ArrayList<Produto> mostrar() throws RemoteException {
        
        String cod,nom;
        ArrayList<Produto> lista = new ArrayList<>();
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmibd","root","123456");
        String sql = "select * from produto ";
        Statement stm = (Statement) cn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        
        while(rs.next()){
            cod = rs.getString(1);
            nom = rs.getString(2);
            Produto oempleado = new Produto(cod,nom);
            lista.add(oempleado);
        }
            cn.close();
            
        }catch(Exception e){
            System.out.println(e);
        } 
        return lista;
    }

    @Override
    public boolean eliminar(String codigo) throws RemoteException {
        boolean exito;
        exito = false;
        
       try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmibd","root","123456");
        String sql = " delete from produto where codigo = ? ";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, sql);
        ps.executeUpdate();
        exito=true;
        cn.close();
        }
            
        catch (Exception e) {
            System.out.println(e);
        }
        
    return exito;    
}

    @Override
    public boolean modificar(String codigo, String nome) throws RemoteException {
        boolean exito;
        exito = false;
        
       try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmibd","root","123456");
       // DriverManager.getConnection("jdbc:mysql://localhost/nome_banco","usuario","senha");
       //"jdbc:mysql://localhost/nome_banco","usuario","senha"
        String sql = "update produto set nome=? where codigo = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, codigo);
        ps.executeUpdate();
        exito=true;
        cn.close();
        }   
        catch (Exception e) {
            System.out.println(e);
        }
        
        return exito;
        
    }

    @Override
    public boolean inserir(String codigo, String nome) throws RemoteException {
        boolean exito;
        exito = false;
        
       try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmibd","root","123456");
        String sql = "insert into produto(codigo,nome) values (?,?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, codigo);
        ps.setString(2, nome);
        ps.executeUpdate();
        exito=true;
        cn.close();
        }   
        catch (Exception e) {
            System.out.println(e);
        }
        
        return exito;
    }

}

