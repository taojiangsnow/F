/*
 * @author xuejiang
 * 
 */

package Model;

import java.util.ArrayList;

import SuppressionVector.SupVec;
import SuppressionVector.SupVec.PartialOutSeq;
import Utils.Sequence;

public abstract class Model {
	//public abstract void setTrainMode(boolean m);
	protected int trainCycle;
	protected ArrayList<Sequence> observation;
	
	public Model(int cycle, ArrayList<Sequence> s) {
		trainCycle = cycle;
		observation = s;
	}

	public abstract void train();
	
	public abstract double getProb(int t, int index, int start, int index2);
	
	public abstract double getPriorProb(int t, int index);
	
	public abstract double getPosterior(int index, int t, Sequence pos, int cj);
	
	public abstract double getPosterior(int index, int t, PartialOutSeq s, SupVec sv);
	
	public abstract double getPosterior(int t, int index, int start, int index1, int end, int index2);

	public abstract int[] Reached(int t);

	public abstract int[] Reached( int t, int index1, int step);
}
