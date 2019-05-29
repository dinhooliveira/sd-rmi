import java.rmi.RemoteException;
import java.rmi.Naming;
import javax.swing.*;

/**
 * @author Adereldo Oliveira
 */
public class Cliente {

	private static QuestionarioInterface controlador = null;
	private static int cliente = 0;

	public static void main(String[] args) {

		try {
			menu();
		} catch (Exception e) {

		}

	}

	/**
	 * MENU DE ESCOLHA DOS SERVIDORES
	 */
	public static void menu() {

		String opcao = JOptionPane.showInputDialog(null,
				"OPÇÕES:\n\n1) Para acessar o SERVIDOR 1\n\n2) Para acessar o SERVIDOR 2\n\n3) Digite 3 para SAIR! ",
				"ESCOLHA DE SERVIDORES", JOptionPane.PLAIN_MESSAGE);

		try {

			switch (opcao) {
			case "1":
				controlador = (QuestionarioInterface) Naming.lookup("rmi://127.0.0.1/questionario");
				controlador.setCountCliente(1);
				cliente = controlador.getCountCliente();
				Inicializacao();
				break;

			case "2":
				controlador = (QuestionarioInterface) Naming.lookup("//localhost/rmi");
				controlador.setCountCliente(1);
				cliente = controlador.getCountCliente();
				Inicializacao();
				break;

			case "3":
				break;

			default:
				menu();
				break;

			}

		} catch (Exception e) {
			menu();
		}

	}

	/**
	 * DISTRIBUI AS FUNCIONALIDADES PARA OS CLIENTES
	 */
	public static void Inicializacao() {
		try {

			if (controlador.getCountCliente() == 1) {
				JOptionPane.showMessageDialog(null, "Aguardando o Segundo Cliente", "Informação",
						JOptionPane.WARNING_MESSAGE);
			}

			while (controlador.getCountCliente() != 2) {
			}

			if (cliente == 1) {
				cliente1();
			}

			if (cliente == 2) {
				cliente2();
			}

		} catch (Exception e) {

		}

	}

	/**
	 * FUNCIONALIDADES DO CLIENTE 1
	 */
	public static void cliente1() {

		try {
			controlador.getProximoCliente();
			while (!vezCliete()) {
			}
			// Cliente 1 seleciona um pergunta de uma lista de pergunta
			controlador.selecionarQuestao();
			while (controlador.getIndexPergunta() < 0) {
				controlador.selecionarQuestao();
			}
			controlador.getProximoCliente();

			while (!vezCliete()) {
			}
			// cliente 1 permite visualizar resposta ou não
			controlador.perguntaSeDesejaMostrarresposta();
			while (controlador.getPermissao().equals("")) {
				controlador.perguntaSeDesejaMostrarresposta();
			}
			// mostra para a resposta
			controlador.mostrarRespota();
			controlador.getProximoCliente();

			while (!vezCliete()) {
			}

			// mostra o feedback
			if (controlador.getFeedback().equals("s")) {
				JOptionPane.showMessageDialog(null, "Foi facil", "FEEDBAK", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Foi dificil", "FEEDBAK", JOptionPane.INFORMATION_MESSAGE);
			}
			cliente1();

		} catch (Exception e) {

		}
	}

	/**
	 * FUNCIONALIDADES DO CLIENTE 2
	 */
	public static void cliente2() {

		try {

			JOptionPane.showMessageDialog(null, "Aguardando pergunta do CLIENTE (1).", "CLIENTE (2)",
					JOptionPane.INFORMATION_MESSAGE);

			while (!vezCliete()) {
			}
			// cliente 2 responde a pergunta
			controlador.mostrarPergunta();
			while (controlador.getEscolha() == -9999) {
				controlador.mostrarPergunta();
			}
			controlador.getProximoCliente();

			while (!vezCliete()) {
			}

			// cliente 2 responde se ficou satisfeito com resposta ao final
			controlador.FeedBack();
			while (controlador.getFeedback().equals("")) {
				controlador.FeedBack();
			}
			controlador.getProximoCliente();
			// mostra a respota e se tiver permissao
			if (controlador.getPermissao().equals("s")) {
				controlador.mostrarRespota();
			}

			controlador.limpezaParcial();
			cliente2();

		} catch (Exception e) {

		}
	}

	public static boolean vezCliete() {
		try {
			return cliente == controlador.getCliente();
		} catch (Exception e) {
			return false;
		}
	}

}
