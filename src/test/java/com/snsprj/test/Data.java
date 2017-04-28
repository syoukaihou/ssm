package com.snsprj.test;

import java.io.Serializable;

/**
 * ≤‚ ‘Serializable
 * @author john
 *
 */
public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6517559879068985095L;
	
	public int n;
	
	public Data(int n){
		this.n = n;
	}

	public String toString(){
		return Integer.toString(n);
	}
	
	
}
