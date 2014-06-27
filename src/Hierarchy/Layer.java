/*
 * @author xuejiang
 * 2014.6.25
 * 
 * */

package Hierarchy;

import java.util.ArrayList;

import Context.PContext;

public class Layer {
	ArrayList<PContext> s;
	
	public Layer() {
		s = new ArrayList<PContext>();
	}
	
	public void add(PContext p) {
		s.add(p);
	}
}
