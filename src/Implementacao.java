import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

public class Implementacao extends UnicastRemoteObject implements QuestionarioInterface {

	private int controlador = 0;
	private int identificador = 0;
	private int escolha = -9999;
	private int indexPergunta = -9999;
	private String permissao = "";
	private String feedback = "";
	String[] pergunta = new String[5];
	float[] resposta = new float[5];

	protected Implementacao() throws RemoteException {

		super();
		pergunta[0] = " Quanto é 1 + 1?";
		pergunta[1] = " Quanto é 2 / 1 * 5 ?";
		pergunta[2] = " Quanto é 1 + 1 + 5 - 3 ?";
		pergunta[3] = " Quanto é -2 * 1 + 1 ?";
		pergunta[4] = " Quanto é -2 * -1 + 1 ?";

		resposta[0] = 2;
		resposta[1] = 10;
		resposta[2] = 4;
		resposta[3] = -1;
		resposta[4] = -1;
	}

	public String getFeedback() throws RemoteException {
		return feedback;
	}

	public void setFeedback(String feedback) throws RemoteException {
		this.feedback = feedback;
	}

	public int getCountCliente() throws RemoteException {
		return identificador;
	}

	public void setCountCliente(int identificador) throws RemoteException {
		this.identificador += identificador;
	}

	public int getProximoCliente() throws RemoteException {

		if (controlador == 0) {
			controlador = 1;
		} else if (controlador == 1) {
			controlador = 2;
		} else {
			controlador = 1;
		}

		return controlador;
	}

	public int getCliente() throws RemoteException {
		return controlador;
	}

	public int getEscolha() throws RemoteException {
		return escolha;
	}

	public void setEscolha(int escolha) throws RemoteException {
		this.escolha = escolha;
	}

	public int getIndexPergunta() throws RemoteException {
		return indexPergunta;
	}

	public void setIndexPergunta(int indexPergunta) throws RemoteException {
		this.indexPergunta = indexPergunta - 1;
	}

	public String getPermissao() throws RemoteException {
		return permissao;
	}

	public void setPermissao(String permissao) throws RemoteException {
		this.permissao = permissao;
	}

	public String[] getPergunta()  throws RemoteException {
		return pergunta;
	}

	public void setPergunta(String[] pergunta) throws RemoteException  {
		this.pergunta = pergunta;
	}

	
	public float[] getResposta()  throws RemoteException  {
		return resposta;
	}

	public void setResposta(float[] resposta)  throws RemoteException  {
		this.resposta = resposta;
	}

	
	public void limpezaParcial() throws RemoteException {
		identificador=0;
		controlador = 0;
		escolha = -9999;
		indexPergunta = -9999;
		permissao = "";
		feedback = "";
	}
}
