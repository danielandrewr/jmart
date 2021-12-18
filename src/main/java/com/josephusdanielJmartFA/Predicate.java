package com.josephusdanielJmartFA;

@FunctionalInterface
public interface Predicate<T> {
	/**
	 * Used to check a predicate validation
	 * @param arg
	 * @return
	 */
	public boolean predicate(T arg);
}
