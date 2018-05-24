
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franklin.silva
 */
public interface RMIBD extends Remote {
    
    public ArrayList<Produto> buscar_produto(String codigo) throws RemoteException;
    public ArrayList<Produto> mostrar() throws RemoteException;
    public boolean eliminar(String codigo) throws RemoteException;
    public boolean modificar(String codigo,String nome) throws RemoteException;
    public boolean inserir(String codigo,String nome) throws RemoteException;

}
