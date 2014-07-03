package Model.GraphModel;

import java.util.ArrayList;
import java.util.Iterator;

import Model.Model;
import Model.MarkovModel.*;
import SuppressionVector.SupVec;
import SuppressionVector.SupVec.PartialOutSeq;
import Utils.Sequence;
import Context.PContext;
import Hierarchy.Hierarchy;
import Hierarchy.Layer;

public class GraphModel extends Model{
	private ArrayList<Model> each_layer_transition; //for each layer of PContexts
	private ArrayList<Model> inner_transition; //for the bottom layer
	private Hierarchy h;
	
	public GraphModel(int cycle, ArrayList<Sequence> s, Hierarchy hi) {
		super(cycle,s);
		h = hi;
		each_layer_transition = new ArrayList<Model>();
		inner_transition = new ArrayList<Model>();
	}

	/*two parts:
	 * 1:train each_layer model
	 * 2:train bottom layer
	 * */
	public void train() {
		/*part 1*/
		Layer l;
		Model lm,im;
		Iterator<Layer> it = Hierarchy.structure.iterator();
		Iterator<PContext> ip;
		while (it.hasNext()) {
			l = it.next();
			lm = new FirstOrderMarkovModel(l.getPC());
			lm.trainForLayer();
			each_layer_transition.add(lm);
			
			/*part 2*/
			ip = l.getPC().iterator();
			while (ip.hasNext()) {
				im = new FirstOrderMarkovModel();
				im.setContextList(ip.next().getChildren());
				im.trainForCList();
				inner_transition.add(im);
			}
		}
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

	@Override
	public void trainForLayer() {

	}
	
	public Model getLayerTransition(int i) {
		return each_layer_transition.get(i);
	}

	public Model getInnerTransition(int i) {
		return inner_transition.get(i);
	}

	public void setHierarchy(Hierarchy i) {
		h = i;
	}
	
	public Hierarchy getHierarchy() {
		return h;
	}

	@Override
	public void trainForCList() {
		// TODO Auto-generated method stub
		
	}
}
