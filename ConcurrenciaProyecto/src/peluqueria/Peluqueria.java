package peluqueria;

import java.util.LinkedList;
import java.util.Queue;

public class Peluqueria {

	private static final int NUM_PELUQUEROS = 3;
	
	private static final int NUM_CLIENTES = 10;
	
	private Queue<Cliente> colaClientes = new LinkedList<>();
	
	Object lock = new Object();
	
	private Thread[] peluquerosThreads;
	
	private Peluquero[] peluqueros;
	
	private boolean todosAtendidos = false;
	
	private boolean seEstanAnhadiendo = true;
	
	public Peluqueria () {
		
		peluqueros = new Peluquero[NUM_PELUQUEROS];
		peluquerosThreads = new Thread[NUM_PELUQUEROS];
		
		for(int i=0; i<NUM_PELUQUEROS;i++) {
			peluqueros[i] = new Peluquero(this, "Peluquero" + (i + 1));  
            peluquerosThreads[i] = new Thread(peluqueros[i]);  
		}
	}
	
	public void iniciar() {
		
		// Iniciar todos los peluqueros
		for(Thread peluquero : peluquerosThreads) {
			peluquero.start();
		}
		
		// Crear y agreagar clientes a la cola
		for (int i = 1; i<=NUM_CLIENTES;i++) {
			Cliente cliente = new Cliente ("Cliente"+ i);
			agregarCliente(cliente);
		}
		
		seEstanAnhadiendo = false;
		
		// Cuando se han añadido todos los clientes se indica que todos han sido atendidos
		synchronized (lock) {
			todosLosClientesAtendidos();
			lock.notifyAll();
		}
		
		 // Esperamos a que todos los peluqueros terminen su trabajo
        synchronized (lock) {
            while (!todosAtendidos) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
		
		// Detener todos los pelqueros cando todo el trabajo este hecho
		if (colaClientes.isEmpty()) {
        for (Peluquero peluquero : peluqueros) {
	            peluquero.detenerTrabajo();
		}}
		
	}
	
	public void agregarCliente(Cliente cliente) {
		synchronized (lock) {
			colaClientes.offer(cliente);
			lock.notify();
		}	
	}
	
	public Cliente obtenerCliente() throws InterruptedException{
		synchronized (lock) {
			while(colaClientes.isEmpty()&& !todosAtendidos) {
				lock.wait();
			}
			
			if (colaClientes.isEmpty() && todosAtendidos && !seEstanAnhadiendo) {
                lock.notifyAll(); 
                return null;
            }

			return colaClientes.poll();
		}
	}
	
	public void todosLosClientesAtendidos() {
		synchronized (lock) {
			todosAtendidos=true;
			lock.notifyAll();
		}
	}
}
