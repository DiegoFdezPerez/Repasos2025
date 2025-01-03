package Main;
import java.util.concurrent.*;

public class Buffer {
   
	//Repsesentación del buffer
	private int[] arregloEnteros;
	
	//Apuntadores para decir cual es la posicion sigiuiente disponible en el buffer 
	//para que el productor o el consumidor realicen su operación
	//la i es para el productor y la j para el consumidor
	private int i = 0, j = 0;
	
	// Nos garantiza la exclusión mutua, un solo proceso accede a la vez a modificar el buffer
	private Semaphore mutex = new Semaphore(1,true);
	
	//Nos indica si en el buffer hay datos para poder consumirlos
	private Semaphore hayDatos = new Semaphore(0,true);
	
	//Le indica al productor si hay espacio para colocar nuevos datos
	private Semaphore hayEspacio;
	
	public Buffer(int tam) {
	 arregloEnteros = new int[tam];
	 hayEspacio = new Semaphore(tam,true);
	}
	
	public void ponerDato(int dato) throws InterruptedException {
		hayEspacio.acquire();
		mutex.acquire();
		arregloEnteros[i] = dato;
		
		i = (i++)%arregloEnteros.length;
		mutex.release();
		hayDatos.release();
		
		//para poder visualizar comodamente por consola
		Thread.sleep(1000);
	}
	
	public int extraerDato() throws InterruptedException {
		hayDatos.acquire();
		mutex.acquire();
		int dato = arregloEnteros[j];
		
		j = (j++)%arregloEnteros.length;
		mutex.release();
		hayEspacio.release();
		
		//para poder visualizar comodamente por consola
		Thread.sleep(1000);
		
		return dato;
	}
}
