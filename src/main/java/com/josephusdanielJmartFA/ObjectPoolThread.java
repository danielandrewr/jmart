package com.josephusdanielJmartFA;

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
	}
	
	public void run() {
		
		while (exitSignal == false) {
			for (T object : objectPool) {
				while (routine.apply(object) != true) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if (routine.apply(object) == true) {
					objectPool.remove(object);
				}
				
				while (size() == 0) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if (size() != 0) {
					notify();
				}
			}
		}
	}
	
	public int size() {
		return objectPool.size();
	}
}
