package Concurrencia;

public class HiloRunnable implements Runnable{

	public void run() {
		for(int i=0;i<=5;i++) {
		System.out.println("Hilo "+ Thread.currentThread().threadId()+": runnable "+ i);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	public static void main(String[]args) {
		HiloRunnable runnable1 = new HiloRunnable();
		HiloRunnable runnable2 = new HiloRunnable();
		
		Thread hilo1 = new Thread(runnable1);
		Thread hilo2 = new Thread(runnable2);
		
		hilo1.start();
		hilo2.start();
	}
}
