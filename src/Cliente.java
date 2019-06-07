import java.awt.HeadlessException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import javax.swing.*;

/**
 * @author Adereldo Oliveira
 */
public class Cliente {

	private static QuestionarioInterface controlador = null;
	private static int cliente = 0;

	public static void main(String[] args) throws HeadlessException, NumberFormatException, RemoteException {

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
				controlador = (QuestionarioInterface) Naming.lookup("//localhost/rmi");
				controlador.setCountCliente(1);
				cliente = controlador.getCountCliente();
				Inicializacao();
				break;

			case "2":
				controlador = (QuestionarioInterface) Naming.lookup("//10.0.200.18/rmi");
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
			int i;
			while (controlador.getIndexPergunta() < 0) {

				String texto = " SELECIONE UMA QUESTÃO PARA SER RESOLVIDA (CLIENTE 1) \n\n";
				for (i = 0; i < controlador.getPergunta().length; i++) {
					texto += (i + 1) + ") " + controlador.getPergunta()[i] + "\n\n";
				}

				int resp = Integer.parseInt(JOptionPane.showInputDialog(texto));

				if (resp > 0 && resp <= controlador.getPergunta().length) {
					controlador.setIndexPergunta(resp);
				}

			}

			System.out.print("Cliente 1 perguntou");
			controlador.getProximoCliente();

			while (!vezCliete()) {
			}

			int resp;
			// cliente 1 permite visualizar resposta ou não
			while (controlador.getPermissao().equals("")) {
				resp = JOptionPane.showConfirmDialog(null, "Deseja mostrar a resposta para o cliente 2 (CLIENTE 1) ");
				if (resp == 0) {
					controlador.setPermissao("s");
				} else {
					controlador.setPermissao("n");
				}
			}
			System.out.print("Cliente 1 liberando permissão");
			// mostra para a resposta
			controlador.getProximoCliente();

			while (!vezCliete()) {
			}

			System.out.print("Cliente 1 viu feedback");
			// mostra o feedback
			if (controlador.getFeedback().equals("s")) {
				JOptionPane.showMessageDialog(null, "Foi facil", "FEEDBAK (CLIENTE 1) ", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Foi dificil", "FEEDBAK (CLIENTE 1) ", JOptionPane.INFORMATION_MESSAGE);
			}

			String texto;
			if(controlador.getEscolha() == controlador.getResposta()[controlador.getIndexPergunta()])
				texto = "Parabens a resposta esta certa (CLIENTE 1) ";
			else
				texto = "Errou (CLIENTE 1) ";
			
			JOptionPane.showMessageDialog(null,texto,"", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.print("Cliente um e limpeza parcial");
			controlador.limpezaParcial();
			menu();

		} catch (Exception e) {

		}
	}

	/**
	 * FUNCIONALIDADES DO CLIENTE 2
	 */
	public static void cliente2() {

		try {

			JOptionPane.showMessageDialog(null, "Aguardando pergunta do CLIENTE (1). (CLIENTE 2)","",
					JOptionPane.INFORMATION_MESSAGE);

			while (!vezCliete()) {
			}
			// cliente 2 responde a pergunta
			int resp;
			String pergunta;
			while (controlador.getEscolha() == -9999) {
				pergunta = controlador.getIndexPergunta() + 1 + ") "
						+ controlador.getPergunta()[controlador.getIndexPergunta()];
				resp = Integer.parseInt(JOptionPane.showInputDialog(pergunta));
				controlador.setEscolha(resp);
			}
			controlador.getProximoCliente();
			System.out.print("Cliente 2 reposndeu");

			while (!vezCliete()) {
			}

			// cliente 2 responde se ficou satisfeito com resposta ao final
			while (controlador.getFeedback().equals("")) {
				resp = JOptionPane.showConfirmDialog(null, "A pergunta foi facil? (CLIENTE 2)");
				if (resp == 0) {
					controlador.setFeedback("s");
				} else {
					controlador.setFeedback("n");
				}
			}

			System.out.print("Cliente 2 deu feedback ");
			controlador.getProximoCliente();
			// mostra a respota e se tiver permissao
			if (controlador.getPermissao().equals("s")) {
				String texto;
				if(controlador.getEscolha() == controlador.getResposta()[controlador.getIndexPergunta()])
					texto = "Parabens a resposta esta certa (CLIENTE 1) ";
				else
					texto = "Errou (CLIENTE 1) ";
			}

			menu();

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
