import java.rmi.Naming;

public class Servidor1 {

	Servidor1() {
		try {
			Implementacao Servidor = new Implementacao();
			Naming.rebind("//192.168.0.104/rmi", Servidor);
			System.out.println("Servidor no ar");
		} catch (Exception e) {
			System.err.println("Houve algum problema" + e);
			e.printStackTrace();
			System.exit(2);
		}
	}

	public static void main(String[] args) {
		new Servidor1();
		System.out.println("Aguardando inicialização do objeto...");
	}
}
