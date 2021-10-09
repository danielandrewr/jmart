package josephusdanielJmartFA;

import java.util.Iterator;
import java.util.*;

public class Algorithm {

	private Algorithm() {
		
	}
	
	public static <T> int count(T[] array, T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> predicate = value::equals;
		return count(iterator, predicate);
	}
	
	public static <T> int count(Iterable<T> iterable, T value) {
		Predicate<T> predicate = value::equals;
		return count(iterable, predicate);
	}
	
	public static <T> int count(Iterator<T> iterator, T value) {
		Predicate<T> predicate = value::equals;
		return count(iterator, predicate);
	}
	
	public static <T> int count(T[] array, Predicate<T> predicate) {
		return count(array, predicate);
	}
	
	public static <T> int count(Iterable<T> iterable, Predicate<T> predicate) {
		Iterator<T> iterator = iterable.iterator();
		return count(iterator, predicate);
	}
	
	public static <T> int count(Iterator<T> iterator, Predicate<T> predicate) {
		int res = 0;
		while(iterator.hasNext()) {
			if (predicate.predicate(iterator.next())) {
				return res;
			}
		}
		return res;
	}
	
	public <T> boolean exists(T[] array, T value) {
		
		return false;
	}
	
	public <T> boolean exists(Iterable<T> iterable, T value) {
		
		return false;
	}
	
	public <T> boolean exists(Iterator<T> iterator, T value) {
		
		return false;
	}

	public <T> boolean exists(T[] array, Predicate<T> predicate) {
		
		return false;
	}

	public <T> boolean exists(Iterable<T> iterable, Predicate<T> predicate) {
	
		return false;
	}
	
	public <T> boolean exists(Iterator<T> iterator, Predicate<T> predicate) {
		
		return false;
	}
	
	public static <T> T find(T[] array, T value) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		Predicate<T> predicate = value::equals;
		return find(iterator, predicate);
	}
	
	public static <T> T find(Iterable<T> iterable, T value) {
		Predicate<T> predicate = value::equals;
		Iterator<T> iterator = iterable.iterator();
		return find(iterator, predicate);
	}
	
	public static <T> T find(Iterator<T> iterator, T value) {
		Predicate<T> predicate = value::equals;
		return find(iterator, predicate);
	}
	
	public static <T> T find(T[] array, Predicate<T> predicate) {
		Iterator<T> iterator = Arrays.stream(array).iterator();
		return find(iterator, predicate);
	}
	
	public static <T> T find(Iterable<T> iterable, Predicate<T> predicate) {
		Iterator<T> iterator = iterable.iterator();
		
		return find(iterator, predicate);
	}
	
	public static <T> T find(Iterator<T> iterator, Predicate<T> predicate) {
		while(iterator.hasNext()) {
			T e = iterator.next();
			if (predicate.predicate(e)) {
				return e;
			}
		}
		return null;
	}
}
