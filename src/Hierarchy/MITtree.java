package Hierarchy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import Main.Maskit;
import Utils.Sequence;
import Context.Context;
import Context.PContext;

public class MITtree extends Tree{
	public MITtree(int i) {
		super(i);
		structure = new ArrayList<Layer>();
	}
	
	public void initial(ArrayList<Sequence> s) {		
		HashMap<Integer,HashSet<Integer>> cell_tower_set = new HashMap<Integer,HashSet<Integer>>(); //store different cell_tower
		HashMap<Integer,ArrayList<Integer>> cell_tower_list = new HashMap<Integer, ArrayList<Integer>>(); //store all cell_tower
		HashMap<Integer,ArrayList<Context>> cell_context_list = new HashMap<Integer, ArrayList<Context>>();
		HashSet<Integer> diff_cell = new HashSet<Integer>();
		HashSet<Integer> diff_tower = new HashSet<Integer>();
		
		Sequence sq;
		Context c;
		Iterator<Integer> it;
		double[][][] t_tower_cell;
		double[][] t_tower;
		int cell,tower;
		
		Hierarchy.structure = new ArrayList<Layer>();
		structure.add(new Layer()); // the bottom layer 
		Layer sigle_layer = new Layer();
		Hierarchy.structure.add(sigle_layer);
		
		for (int i = 0; i < s.size(); i++) {
			sq = s.get(i);
			for (int j = 0; j < sq.getLength(); j++) {
				c = sq.getContext(j);
				cell = c.getCell();
				tower = c.getTower();
				c.output();
				
				diff_cell.add(cell);
				diff_tower.add(tower);
				System.out.println("  cell "+cell+" tower "+tower);
				
				if (!cell_tower_set.containsKey(cell)) {
					ArrayList<Integer> array = new ArrayList<Integer>();
					HashSet<Integer> list = new HashSet<Integer>();
					ArrayList<Context> contextlist = new ArrayList<Context>();
					list.add(tower);
					array.add(tower);
					contextlist.add(c);
					cell_tower_set.put(cell, list);
					cell_tower_list.put(cell, array);
					cell_context_list.put(cell, contextlist);
				} else {
					cell_tower_set.get(cell).add(tower);
					cell_tower_list.get(cell).add(tower);
				}
				
			}
		}
		
		/*transfer the hashset to array*/
		ArrayList<Integer> cell_list = new ArrayList<Integer>(); 
		ArrayList<Integer> tower_list = new ArrayList<Integer>();
		it = diff_cell.iterator();
		while (it.hasNext()) {
			int key = it.next();
			cell_list.add(key);
		}
		it = diff_tower.iterator();
		while (it.hasNext()) {
			int key = it.next();
			tower_list.add(key);
		}
		
		setInnerNodeNum(cell_list.size());
		t_tower_cell = new double[Maskit.T][tower_list.size()][cell_list.size()];
		t_tower = new double[Maskit.T][tower_list.size()];
		
		for (int i = 0; i < s.size(); i++) {
			sq = s.get(i);
			for (int j = 0; j < sq.getLength(); j++) {
				c = sq.getContext(j);
				PContext pc = new PContext(null,c.getTimetmp(),c.getCell(),cell_list.indexOf(c.getCell()),c.getT());
				sigle_layer.add(pc);
				
				/*construct childern of the parent*/
				pc.setChildren(cell_context_list.get(c.getCell())); 
				
				c.setParent(pc);
				
				if ((c.getT() != 0) && (c.getT() != Maskit.T)) {
					t_tower_cell[c.getT()][pc.getHierarchyIndex()][tower_list.indexOf(c.getTower())] += 1.0;
					t_tower[c.getT()][pc.getHierarchyIndex()] += 1.0;
				}
				
				/*set the index in each parent's domain*/
				c.setHierarchyIndex(pc.findChild(c.getTower()));
			}
		}
		
		/*get the probability of each tower in the cell*/
		PContext pc;
		for (int i = 0; i < s.size(); i++) {
			sq = s.get(i);			
			for (int j = 0; j < sq.getLength(); j++) {
				c = sq.getContext(j);
				pc = c.getParent();
				if ((c.getT() != 0) && (c.getT() != Maskit.T)) {
					if (t_tower[c.getT()][pc.getHierarchyIndex()] != 0) {
						c.setProbToParent(t_tower_cell[c.getT()][pc.getHierarchyIndex()][c.getHierarchyIndex()]/t_tower[c.getT()][pc.getHierarchyIndex()]);
					}
				} else {
					c.setProbToParent(1.0);
				}
			}
		}
		
		it = cell_tower_list.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next();
			System.out.println("******cell "+key+" *********");
			ArrayList<Integer> list = cell_tower_list.get(key);
			for (int j = 0; j < list.size(); j++) {
				System.out.print(list.get(j)+" ");
			}
			System.out.println();
		}	
	}
	
	public void output(HashMap<Integer,HashSet<Integer>> set) {
		Iterator<Integer> it = set.keySet().iterator();
		while (it.hasNext()) {
			int cell = it.next();
			System.out.println("******cell "+cell+" ******");
			int num = 0;
			for (Integer i: set.get(cell)) {
				if (num == 10) {
					System.out.println();
					num = 0;
				}
				System.out.print(i+" ");
				num++;
			}
			System.out.println();
			System.out.println();
		}
	}

	@Override
	public void setInnerNodeNum(int i) {
		inner_node_num = i;
	}
}

