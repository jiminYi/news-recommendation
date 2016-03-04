package docWordMatrix;

import java.util.ArrayList;

import org.ejml.data.DenseMatrix64F;
import org.ejml.factory.DecompositionFactory;
import org.ejml.interfaces.decomposition.SingularValueDecomposition;
import org.ejml.simple.SimpleMatrix;
import org.ejml.simple.SimpleSVD;

import data.Article;
import data.Point;

public class EjmlSvdGenerator {
	private DenseMatrix64F u;

	public static void main(String args[]) {
		double[][] matrixValue = {
				{2,0,8,6,0},
				{1,6,0,1,7},
				{5,0,7,4,0},
				{7,0,8,5,0},
				{0,10,0,0,7}
		};
		DenseMatrix64F matrix = new DenseMatrix64F(matrixValue);
		SingularValueDecomposition<DenseMatrix64F> svd = 
				DecompositionFactory.svd(matrix.numRows, matrix.numCols, true, true, true);
		if(!DecompositionFactory.decomposeSafe(svd, matrix)) {
		    System.err.println("Decomposition failed");
		    return;
		}
		DenseMatrix64F u = svd.getU(null, false);
		for(int i = 0; i < u.getNumRows(); i++) {
			for(int j = 0; j < u.getNumCols(); j++) {
				System.out.print(u.get(i, j) + " ");
			}
			System.out.println();
		}
	}

	public EjmlSvdGenerator(double[][] matrixValue) {
		DenseMatrix64F matrix = new DenseMatrix64F(matrixValue);
		SingularValueDecomposition<DenseMatrix64F> svd = 
				DecompositionFactory.svd(matrix.numRows, matrix.numCols, true, true, true);
		if(!DecompositionFactory.decomposeSafe(svd, matrix)) {
		    System.err.println("Decomposition failed");
		    return;
		}
		u = svd.getU(null, false);
	}

	public void setArticlePoint(ArrayList<Article> articles) {
		int dimension = 5;
		for(int i = 0; i < u.getNumRows(); i++) {
			ArrayList<Double> point = new ArrayList<Double>();
			for(int j = 0; j < dimension; j++) {
				point.add(u.get(i, j));
			}
			articles.get(i).setPoint(new Point(point));
		}
	}
}
