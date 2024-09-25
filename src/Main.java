package main;

import org.apache.commons.math3.linear.*;
import com.raylib.Raylib;
import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.*;
import java.util.LinkedList;



class Main
{
	static final int SCREEN_WIDTH = 1100;
	static final int SCREEN_HEIGHT = 1100;
	static final double PI = 3.1415926535f;
	static final int LINE_LEN = 50;
	static final int ITERATIONS = 3;
	static final int BRANCHES = 7;
	static int iterator = 0;
	static boolean first = true;
	static LinkedList<int[]> pointsToDo = new LinkedList<int[]>();
	static LinkedList<int[]> pointsToAdd = new LinkedList<int[]>();

	
	public static void main(String[] args)
	{
		InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Snowflake");


		pointsToDo.add(new int[] {SCREEN_WIDTH/2, SCREEN_HEIGHT/2});
		
		while(!WindowShouldClose()){

			// DrawLine(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, SCREEN_WIDTH/2, SCREEN_HEIGHT/2+LINE_LEN, BLACK);

			BeginDrawing();
			ClearBackground(RAYWHITE);
			int i = 0;
			for(int[] startPos : pointsToDo){
				i++;
				drawFractal(startPos[0], startPos[1], LINE_LEN,
							ColorFromHSV((i/(ITERATIONS*BRANCHES))* 200, 100, 100), iterator<ITERATIONS);
			}
			EndDrawing();
			pointsToDo.addAll(pointsToAdd);
			pointsToAdd.clear();


			if(iterator<=ITERATIONS){
				System.out.println("iterator: " + iterator);
				iterator++;
			}

		}
	}


	/*
	 * Rotate Vec
	 [  cos alpha    sin alpha ]
	 [ -sin alpha    cos alpha ]
	*/
	static void drawFractal(int x, int y, int len, Raylib.Color color, boolean addMore){
		int splitcount = BRANCHES;
		double anglediff = (2*PI)/BRANCHES;
		ArrayRealVector startVec =  new ArrayRealVector(new double[] {0, LINE_LEN});
		int branchesMI = BRANCHES;
		if(!first){
			branchesMI--;
			first = false;
		}

		for (int i = 1; i <= BRANCHES; i++) {
			double[] mVec = rotateVec(i*anglediff, startVec);
			mVec[0] = mVec[0]+x;
			mVec[1] = mVec[1]+y;
			int[] iVec = {(int) mVec[0], (int) mVec[1]};

			if(addMore)
				pointsToAdd.add(iVec);
			DrawLine(x, y, iVec[0], iVec[1], color);
		}


	}

	static double[] rotateVec(double anglediff, RealVector startVec){
		/*
		RealMatrix matrix = new Array2DRowRealMatrix(
			new double[][] {
				{Math.cos(anglediff),  Math.sin(anglediff)},
				{-Math.sin(anglediff), Math.cos(anglediff)}
			},
			false);

		DecompositionSolver solver = new LUDecomposition(matrix).getSolver();
		RealVector solution = solver.solve(startVec);

		return new double[]{solution.toArray()[0], solution.toArray()[1]};
		*/

		double x = startVec.toArray()[0];
		double y = startVec.toArray()[1];
		double xr = x*Math.cos(anglediff) - y*Math.sin(anglediff);
		double yr = x*Math.sin(anglediff) + y*Math.cos(anglediff);
		return new double[]{xr,yr};


		
	}
	


}




// WAS in drawfractal
// double[] mVec2 = rotateVec(2*anglediff, startVec);
// double[] mVec3 = rotateVec(3*anglediff, startVec);

// /*
// System.out.println("mVec");
// for (double i:mVec)
// 	System.out.println("i: "+i);
		
// System.out.println("mVec2");
// for (double i:mVec2)
//     System.out.println("i: "+i);
// */




// mVec2[0] = mVec2[0]+x;
// mVec2[1] = mVec2[1]+y;



// mVec3[0] = mVec3[0]+x;
// mVec3[1] = mVec3[1]+y;


// int[] iVec2 = {(int) mVec2[0], (int) mVec2[1]};
// int[] iVec3 = {(int) mVec3[0], (int) mVec3[1]};

// if(addMore){

// 	pointsToAdd.add(iVec2);
// 	pointsToAdd.add(iVec3);
// }
		

// DrawLine(x, y, iVec2[0], iVec2[1], color);
// DrawLine(x, y, iVec3[0], iVec3[1], color);
