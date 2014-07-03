package Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Utils.Date;

public class PContext implements Cloneable{
	private String context;
	private Date timestamp;
	private int id;
	private int layer;
	private int T;
	private PContext parent;
	
	private ArrayList<Context> children; //store initial each context's index
	private int hierarchy_index; //the index of the children
	
	public PContext() {
	}
	
	public PContext(String s, Date time, int i, int l, int t) {
		context = s;
		timestamp = time;
		id = i;
		layer = l;
		T = t;
		parent = null;
		
	}
	
	public PContext(String s, Date time, int i, int j, int l, int t) {
		context = s;
		timestamp = time;
		id = i;
		hierarchy_index = j;
		layer = l;
		T = t;
		parent = null;
	}
	
	public void setLayer(int i) {
		layer = i;
	}

	public ArrayList<Context> getChildren() {
		return children;
	}
	
	public void addChild(Context c) {
		children.add(c);
	}
	
	public int findChild(int index){
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).getIndex() == index) {
				return i;
			}
		}
		return -1;
	}

	public int getT() {
		return T;
	}
	
	public void setT(int t) {
		T = t;
	}

	public int getHierarchyIndex() {
		return hierarchy_index;
	}
	
	public Date getDate() {
		return timestamp;
	}

	public void setChildren(ArrayList<Context> array) {
		children = array;
	}
}
