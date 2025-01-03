package Main;

public class Consumidor extends Thread {

	private Buffer buffer;
	private int cantidadAConsumir;
	
	public Consumidor(Buffer buffer, int cantidadAConsumir) {
		this.buffer=buffer;
		this.cantidadAConsumir=cantidadAConsumir;
	}
	
	@Override
	public void run() {
		
		for (int i=0; i < cantidadAConsumir; i++) {
			
			try {
				int aux = buffer.extraerDato();
				System.out.println("El consumidor consume: " + aux + " del buffer.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
