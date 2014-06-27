package Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class PContext implements Cloneable{
	private String context;
	private int id;
	private int hierarchy_index;	
	private int layer;
	private int T;
	private PContext parent;
	
	private int[] childern;
	
	public PContext() {
	}
	
	public PContext(String s, int i) {
		context = s;
		id = i;
		parent = null;
	}
	
	public PContext(String s, int i, int j) {
		context = s;
		id = i;
		hierarchy_index = j;
		parent = null;
	}

	public void setChildern(HashSet<Integer> set) {
		childern = new int[set.size()];
		Iterator<Integer> it = set.iterator();
		
		int i = 0;
		while (it.hasNext()) {
			childern[i] = it.next();
			i++;
		}
	}
	
	public void setLayer(int i) {
		layer = i;
	}

	public int[] getChildern() {
		return childern;
	}
	
	public int findChild(int index){
		for (int i = 0; i < childern.length; i++) {
			if (childern[i] == index) {
				return i;
			}
		}
		return -1;
	}

	public void setT(int t) {
		T = t;
	}

	public int getHierarchyIndex() {
		return hierarchy_index;
	}
}
