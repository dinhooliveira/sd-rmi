import java.rmi.Naming;

public class Servidor1 {

	public static void main(String[] args) {
	
		try {
			Implementacao obj = new Implementacao();
			Naming.rebind("//localhost/rmi", obj);
			System.out.println("Servidor no Ar!");
		}

		catch (Exception e) {
			System.err.println("Houve algum problema" + e);
			e.printStackTrace();
			System.exit(2);
		}

		System.out.println("Aguardando inicialização do objeto...");

	}
}
