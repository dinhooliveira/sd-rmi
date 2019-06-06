import java.rmi.Naming;

public class Servidor2 {

	Servidor2() {
		try {
			Implementacao Servidor = new Implementacao();
			Naming.rebind("rmi://127.0.0.1/questionario2", Servidor);
			System.out.println("Servidor no ar");
		} catch (Exception e) {
			System.err.println("Houve algum problema" + e);
			e.printStackTrace();
			System.exit(2);
		}
	}

	public static void main(String[] args) {
		new Servidor2();
		System.out.println("Aguardando inicialização do objeto...");
	}
}
