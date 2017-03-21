package com.springboot.kubernetes.chuck;

import java.io.Serializable;

class ChuckFact implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String fact;

	public ChuckFact() {
		super();
	}

	public ChuckFact(int id, String string) {
		this.id = id;
		this.fact = string;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the fact
	 */
	public String getFact() {
		return fact;
	}

}