package com.josephusdanielJmartFA.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.josephusdanielJmartFA.Algorithm;
import com.josephusdanielJmartFA.dbjson.JsonTable;
import com.josephusdanielJmartFA.dbjson.Serializable;

public interface BasicGetController<T extends Serializable> {

	/**
	 * Get a model by ID
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public default T getById(int id) {
		for (T obj : getJsonTable()) {
			if (obj.id == id) return obj;
		}
		return null;
	}
	
	/**
	 * Returns jsonTable
	 * @return
	 */
	public abstract JsonTable<T> getJsonTable();
	
	/**
	 * Paginates an object of model to pages
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/page")
	public default List<T> getPage(@RequestParam(defaultValue="2") int page, @RequestParam(defaultValue="5") int pageSize) {
		return Algorithm.paginate(getJsonTable(), page, pageSize, (e) -> true);
	}
}
