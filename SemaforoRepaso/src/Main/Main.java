package Main;

public class Main {

	public static void main (String [] args) throws InterruptedException {
		
		Buffer buffer = new Buffer (5);
		Productor productor = new Productor(buffer, 10);
		Consumidor consumidor = new Consumidor(buffer, 10);
		
		productor.start();
		//Para visualizar comodamente por consola:
		Thread.sleep(500);
		consumidor.start();
	}
	
}
