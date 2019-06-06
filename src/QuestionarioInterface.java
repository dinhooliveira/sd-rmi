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
	String getPermissao() throws RemoteException;
	void setPermissao(String permissao) throws RemoteException;
	String getFeedback() throws RemoteException;
	void setFeedback(String feedback) throws RemoteException;
	void limpezaParcial() throws RemoteException;
    String[] getPergunta()  throws RemoteException;
    void setPergunta(String[] pergunta) throws RemoteException;
    float[] getResposta()  throws RemoteException;
    void setResposta(float[] resposta)  throws RemoteException;
	
}
