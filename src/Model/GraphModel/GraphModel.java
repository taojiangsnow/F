package Model.GraphModel;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Model;
import SuppressionVector.SupVec;
import SuppressionVector.SupVec.PartialOutSeq;
import Utils.Matrix;
import Utils.Sequence;
import Hierarchy.Hierarchy;

public class GraphModel extends Model{
	private ArrayList<Matrix> each_layer_transition;
	private ArrayList<Matrix> inner_transition;
	private Hierarchy h;
	
	public GraphModel(int cycle, ArrayList<Sequence> s, Hierarchy hi) {
		super(cycle,s);
		h = hi;
	}

	@Override
	public void train() {
		h.train(observation);
	}
	
	@Override
	public double getProb(int t, int index, int start, int index2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPriorProb(int t, int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPosterior(int index, int t, Sequence pos, int cj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPosterior(int index, int t, PartialOutSeq s, SupVec sv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPosterior(int t, int index, int start, int index1,
			int end, int index2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] Reached(int t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] Reached(int t, int index1, int step) {
		// TODO Auto-generated method stub
		return null;
	}

}
