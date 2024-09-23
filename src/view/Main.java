package view;
import java.util.Random;
import java.util.concurrent.Semaphore;
import controller.Caixa;

public class Main {

	public static void main(String[] args) {
		Random rand = new Random();
		
		Semaphore saque = new Semaphore(1);
		Semaphore deposito = new Semaphore(1);
		
		
		for (int i = 0; i < 20; i++) {
			int conta = rand.nextInt(100,20000);
			int saldo = rand.nextInt(0,80001);
			int transacao = rand.nextInt(1,3);
			int valor = rand.nextInt(0,100000);
			
			Thread t = new Caixa(saque, deposito, conta, saldo, transacao, valor);
			t.start();
		}
	}

}
