package josephusdanielJmartFA;

import java.util.Vector;
import java.util.function.Function;

public class ObjectPoolThread<T> extends Thread {

	private boolean exitSignal = false;
	private Vector<T> objectPool;
	private Function<T, Boolean> routine;
	
	public ObjectPoolThread(String name, Function<T, Boolean> routine) {
		super(name);
		this.routine = routine;
	}
	
	public ObjectPoolThread(Function<T, Boolean> routine) {
		super();
		this.routine = routine;
	}
	
	public synchronized void add(T object) {
		objectPool.add(object);
	}
	
	public synchronized void exit() {
		exitSignal = true;
		interrupt();
	}
	
	public void run() {
		while (!Thread.interrupted() && !exitSignal) {
			if (objectPool.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < size(); i++) {
				if (routine.apply(objectPool.get(i)) == true) {
					objectPool.remove(i);
				} else {
					continue;
				}
			}
		}
	}
	
	public int size() {
		return objectPool.size();
	}
}
