import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

public class Implementacao extends UnicastRemoteObject implements QuestionarioInterface {

	private int controlador = 0;
	private int identificador = 0;
	private int escolha = -9999;
	private int indexPergunta = -9999;
	private int indexResposta = -9999;
	private boolean permissao;
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

	public int getCountCliente() throws RemoteException {
		return identificador;
	}

	public void setCountCliente(int identificador) throws RemoteException {
		this.identificador+= identificador;
	}

	public int getProximoCliente()  throws RemoteException{
		
		if(controlador==0) {
			controlador = 1;
		}else if(controlador==1) {
			controlador = 2;
		}else {
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

	

	public boolean isPermissao() throws RemoteException{
		return permissao;
	}

	public void setPermissao(boolean permissao) throws RemoteException {
		this.permissao = permissao;
	}

	public void selecionarQuestao() throws RemoteException {
		int i;
		String texto = " SELECIONE UMA QUESTÃO PARA SER RESOLVIDA \n\n";
		for (i = 0; i < this.pergunta.length; i++) {
			texto += (i + 1) + ") " + this.pergunta[i] + "\n\n";
		}

		int resp = Integer.parseInt(JOptionPane.showInputDialog(texto));

		if (resp > 0 && resp <= this.pergunta.length) {
			this.setIndexPergunta(resp);
		} else {
			this.selecionarQuestao();
		}

	}

	public void mostrarPergunta() throws RemoteException {
		String pergunta = this.getIndexPergunta() + 1 + ") " + this.pergunta[this.getIndexPergunta()];
		int resp = Integer.parseInt(JOptionPane.showInputDialog(pergunta));
		this.setEscolha(resp);
	}

	public void perguntaSeDesejaMostrarresposta() throws RemoteException {
		int resp = JOptionPane.showConfirmDialog(null, "Deseja mostrar a resposta para o cliente 2");
		System.out.println(resp + "res se deseja");
		if (resp == 0) {
            this.setPermissao(true);
		}else {
			this.setPermissao(false);
		}

	}

	public void mostrarRespota() throws RemoteException {
		String texto = this.getEscolha() == this.resposta[this.getIndexPergunta()] ? "Parabens a resposta esta certa"
				: "Errou";
		JOptionPane.showMessageDialog(null, texto);
	}

	public void perguntaFeedBack() {

	}
}
