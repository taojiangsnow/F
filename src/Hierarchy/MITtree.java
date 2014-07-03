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
	
	public void train(ArrayList<Sequence> s) {
		HashMap<Integer,HashSet<Integer>> cell_tower_set = new HashMap<Integer,HashSet<Integer>>();
		Sequence q;
		Context c;
		int cell,tower,t;
		Iterator<Integer> it;
		HashMap<Integer,ArrayList<Integer>> cell_tower_list = new HashMap<Integer, ArrayList<Integer>>();
		HashSet<Integer> diff_cell = new HashSet<Integer>();
		HashSet<Integer> diff_tower = new HashSet<Integer>();
		double[][][] t_tower_cell;
		double[][] t_tower;
		
		Hierarchy.structure = new ArrayList<Layer>();
		Layer sigle_layer = new Layer();
		Hierarchy.structure.add(sigle_layer);
		
		for (int i = 0; i < s.size(); i++) {
			q = s.get(i);
			for (int j = 0; j < q.getLength(); j++) {
				c = q.getContext(j);
				c.output();
				cell = c.getCell();
				tower = c.getTower();
				t = c.getT();
				diff_cell.add(cell);
				diff_tower.add(tower);
				System.out.println("  cell "+cell+" tower "+tower);
				if (!cell_tower_set.containsKey(cell)) {
					ArrayList<Integer> l = new ArrayList<Integer>();
					HashSet<Integer> list = new HashSet<Integer>();
					list.add(tower);
					l.add(tower);
					cell_tower_set.put(cell, list);
					cell_tower_list.put(cell, l);
				} else {
					cell_tower_set.get(cell).add(tower);
					cell_tower_list.get(cell).add(tower);
				}
				
			}
		}
		
		ArrayList<Integer> cell_list = new ArrayList<Integer>(); 
		ArrayList<Integer> tower_list = new ArrayList<Integer>();
		it = diff_cell.iterator();
		//System.out.println("***cell list***");
		while (it.hasNext()) {
			int key = it.next();
			cell_list.add(key);
			//System.out.print(key+" ");
		}
		//System.out.println();
		it = diff_tower.iterator();
		//System.out.println("***tower list***");
		while (it.hasNext()) {
			int key = it.next();
			tower_list.add(key);
			//System.out.print(key+" ");
		}
		//System.out.println();
		
		setInnerNodeNum(cell_list.size());
		t_tower_cell = new double[Maskit.T][tower_list.size()][cell_list.size()];
		t_tower = new double[Maskit.T][tower_list.size()];
		
		for (int i = 0; i < s.size(); i++) {
			q = s.get(i);
			for (int j = 0; j < q.getLength(); j++) {
				c = q.getContext(j);
				PContext pc = new PContext(null,c.getCell(),cell_list.indexOf(c.getCell()));
				sigle_layer.add(pc);
				
				
				/*construct childern of the parent*/
				HashSet<Integer> set = cell_tower_set.get(c.getCell());
				pc.setChildern(set);
				
				pc.setLayer(1);
				pc.setT(c.getT());
				c.setParent(pc);
				
				if ((c.getT() != 0) && (c.getT() != Maskit.T)) {
					t_tower_cell[c.getT()][pc.getHierarchyIndex()][tower_list.indexOf(c.getTower())] += 1.0;
					t_tower[c.getT()][pc.getHierarchyIndex()] += 1.0;
				}
				
				/*set the index in each parent's domain*/
				c.setHierarchyIndex(pc.findChild(c.getTower()));
			}
		}
		
		PContext pc;
		for (int i = 0; i < s.size(); i++) {
			q = s.get(i);			
			for (int j = 0; j < q.getLength(); j++) {
				c = q.getContext(j);
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

