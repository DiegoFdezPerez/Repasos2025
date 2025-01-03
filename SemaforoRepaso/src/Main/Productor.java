package Main;
import java.util.Random;
import java.util.random.*;

public class Productor extends Thread {
	
	private Random random = new Random();
	private Buffer buffer;
	private int cantidadAProducir;
	
	public Productor(Buffer buffer, int cantidadAProducir) {
		this.buffer=buffer;
		this.cantidadAProducir=cantidadAProducir;
	}
	
	@Override
	public void run() {
		
		for(int i = 0; i< cantidadAProducir; i++) {
			int aux = random.nextInt(10);
			try {
				buffer.ponerDato(aux);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("El productor puso el elemento: " + aux + " en el buffer.");
		}
	}
	
}
