package peluqueria;

import java.util.Random;

public class Peluquero implements Runnable{

	private final Peluqueria peluqueria;
	
	private final String nombre;
	
	private volatile boolean corriendo = true;
	
	public Peluquero(Peluqueria peluqueria, String nombre) {
		this.peluqueria = peluqueria;
		this.nombre = nombre;
	}


	@Override
	public void run() {
		
	while (corriendo) {
		Random random = new Random();
				try {
				
					Cliente cliente = peluqueria.obtenerCliente();
						
					if (cliente==null) {
							break;
						}
						
					// Si hay cliente atenderlo
					System.out.println(nombre+" comienza a atender a "+ cliente.getName() +".");
					int tiempoAtencion = random.nextInt(10)+1;
					Thread.sleep(tiempoAtencion * 1000);
					System.out.println(nombre+ " ha terminado de atender a " + cliente.getName() + " en " + tiempoAtencion + " segundos.");
				
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
				}
	
		System.out.println("El peluquero "+ nombre + " se ha quedado sin trabajo, se marcha a descansar");
	}
	
	public void detenerTrabajo () {
		corriendo = false;
	}

}
