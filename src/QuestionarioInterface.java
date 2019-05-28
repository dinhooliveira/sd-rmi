import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuestionarioInterface extends Remote {
	

	int getCountCliente() throws RemoteException;
	void setCountCliente(int identificador) throws RemoteException;
	int getCliente() throws RemoteException;
	int getProximoCliente() throws RemoteException;
	int getEscolha() throws RemoteException;
	void setEscolha(int escolha) throws RemoteException;
	int getIndexPergunta() throws RemoteException;
	void setIndexPergunta(int indexPergunta) throws  RemoteException;
	void selecionarQuestao() throws RemoteException;
	void mostrarPergunta() throws RemoteException;
	void perguntaSeDesejaMostrarresposta() throws RemoteException;
	void mostrarRespota() throws RemoteException;
	boolean isPermissao() throws RemoteException;
	void setPermissao(boolean permissao) throws RemoteException;
	
	
}
