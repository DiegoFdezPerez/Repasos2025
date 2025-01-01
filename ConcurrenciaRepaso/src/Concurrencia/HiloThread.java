package Concurrencia;

public class HiloThread extends Thread {

	public void run() {
		for(int i=0;i<=5;i++) {
		System.out.println("Hilo "+ Thread.currentThread().threadId()+": "+ i);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	public static void main(String[]args) {
		HiloThread hilo1 = new HiloThread();
		HiloThread hilo2 = new HiloThread();
		
		hilo1.start();
		hilo2.start();
	}
	
}
