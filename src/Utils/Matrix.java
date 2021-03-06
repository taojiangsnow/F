package Utils;

public class Matrix implements Cloneable{
	private int nr;
	private int nc;
	double [][] Matrix;
	
	public Matrix(int nr, int nc) {
		this.nr = nr;
		this.nc = nc;
		Matrix = new double[nr][];
		for (int i = 0; i < nr; i++) {
			Matrix[i] = new double[nc];
		}
	}

	public Matrix(double[][] m) {
		nr = m.length;
		nc = m[0].length;
		Matrix = new double[nr][];
		for (int i = 0; i < nr; i++) {
			Matrix[i] = new double[nc];
		}
		
		for (int i = 0; i < nr; i++) {
			for (int j = 0 ; j < nc; j++) {
				Matrix[i][j] = m[i][j];
			}
		}
	}
	
	public Object clone(){
		double[][] r;
		try {
			super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r = new double[nr][];
		for (int i = 0; i < nr; i++) {
			r[i] = new double[nc];
			for (int j = 0; j < nc; j++) {
				r[i][j] = Matrix[i][j];
			}
		}
		return r;		
	}
	
	public static void multiply(Matrix t, double[][] m) {
		if (t.nc != m.length) {
			System.out.println("Matrix multiply----The dimention does not match! nc "+t.nc+" length "+m.length);
			return;
		} else {
			double sum;
			int row = t.nr;
			int col = m[0].length;
			Matrix result = new Matrix(row,col);
			for (int i = 0; i < row; i++) {
				for (int j= 0; j < col; j++) {
					sum = 0.0;
					for (int p = 0; p < t.nc; p++) {
						sum += t.get(i, p) * m[p][j];
					}
					result.set(i,j,sum);
				}
			}
			for (int i = 0; i < t.nr; i++) {
				for (int j = 0; j < t.nc; j++) {
					t.set(i, j, result.get(i, j));
				}
			}
		}	
	}		

	public static void multiply(FastVector vector, Matrix t) {
		if (vector.size() != t.nr) {
			System.out.println("The dimention does not match");
			return;
		} else {
			double[] result = new double[t.nc];
			double sum;
			for (int i = 0; i < t.nc; i++) {
				sum = 0.0;
				for (int j = 0; j < t.nr; j++) {
					sum += vector.get(j) * t.get(j, i);					
				}
				result[i] = sum;
			}
			for (int i = 0; i < t.nc; i++) {
				vector.set(i, result[i]);
			}
		}
	}
	
	public double sumOfCol(int c) {
		double sum = 0.0;
		for (int i = 0; i < nr; i++) {
			sum += Matrix[i][c];
		}
		return sum;
	}
	
	public void clear() {
		for (int i = 0; i < nr; i++) {
			Matrix[i] = null;
		}
		Matrix = null;
		nr = 0;
		nc = 0;
	}

	public void set(int i, int j, double sum) {
		if ((i >= nr) || (j >= nc)) {
			System.out.println("The index exceeds the max dimention of Matrix");
		} else {
			Matrix[i][j] = sum;
		}
	}
	
	public void set(int row, double j) {
		for (int i = 0; i < nc; i++) {
			Matrix[row][i] = j;
		}
	}
	
	public double get(int i, int j) {
		if ((i >= nr) || (j >= nc)) {
			System.out.println("The index exceeds the max dimention of Matrix");
		}
		return Matrix[i][j];
	}

	public int RowSize() {
		return nr;
	}
	
	public int ColSize() {
		return nc;
	}
	
	public static void output(Matrix m) {
		for (int i = 0; i < m.nr; i++) {
			for (int j = 0; j < m.nr; j++) {
				System.out.print(m.get(i, j)+" ");
			}
			System.out.println();
		}
	}
}
