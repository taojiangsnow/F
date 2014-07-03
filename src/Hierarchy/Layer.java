/*
 * @author xuejiang
 * 2014.6.25
 * 
 * */

package Hierarchy;

import java.util.ArrayList;

import Context.PContext;
import Model.Model;
import Utils.PreferredSettings;

public class Layer {
	private ArrayList<PContext> s;
	private Model m; //model of each layer of innernodes 
	
	public Layer() {
		s = new ArrayList<PContext>();
	}
	
	public void add(PContext p) {
		s.add(p);
	}
	
	public void setModel(Model i) {
		m = i;
	}
	
	public void trainModel() {
		
	}
	
	public ArrayList<PContext> getPC() {
		return s;
	}
}
