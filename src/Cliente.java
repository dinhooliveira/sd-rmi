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
				System.out.println("Cliente" + controlador.getCountCliente() + " Entrou");
				break;

			case "2":
				controlador = (QuestionarioInterface) Naming.lookup("//localhost/rmi");
				controlador.setCountCliente(1);
				cliente = controlador.getCountCliente();
				Inicializacao();
				System.out.println("Cliente" + controlador.getCountCliente() + " Entrou");
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

			controlador.selecionarQuestao();
			if (controlador.getIndexPergunta() > -1) {
				System.out.println("Vez do cliente" + controlador.getProximoCliente());
			}

			while (!vezCliete()) {
			}
			
			controlador.perguntaSeDesejaMostrarresposta();
			while(controlador.isPermissao()!=false && controlador.isPermissao()!=true) {
			}
			System.out.println("Vez do cliente" + controlador.getProximoCliente());
			while (!vezCliete()) {
			}
			
			
		} catch (Exception e) {

		}
	}

	/**
	 * FUNCIONALIDADES DO CLIENTE 2
	 */
	public static void cliente2() {

		try {

			System.out.println("Aguardando pergunta do primeiro cliente.");
			JOptionPane.showMessageDialog(null, "Aguardando pergunta do CLIENTE (1).", "CLIENTE (2)",
					JOptionPane.WARNING_MESSAGE);

			while (!vezCliete()) {
			}

			controlador.mostrarPergunta();
			if (controlador.getEscolha() != -9999) {
				controlador.getProximoCliente();
			}
			System.out.println("Respondeu" + controlador.getEscolha());

			while (!vezCliete()) {
			}
			
			System.out.println("Respondeu" + controlador.getEscolha());

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
