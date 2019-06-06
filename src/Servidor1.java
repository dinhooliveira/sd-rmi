import java.rmi.Naming;

public class Servidor1 {

	public static void main(String[] args) {
		try {
			Implementacao Servidor = new Implementacao();
			Naming.rebind("//192.168.0.104/rmi", Servidor);
			System.out.println("Servidor no ar");
		} catch (Exception e) {
			System.err.println("Houve algum problema" + e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("Aguardando inicialização do objeto...");
	}
}
