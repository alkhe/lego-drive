package edu.mbhs.lego.drive;


public class ColorIdentifier {
	public static final int	BLACK = 0;
	public static final int WHITE = 1;
	public static final int YELLOW = 2;
	public static final int GRAY = 3;
	public static final int GREEN = 4;
	public static final int RED = 5;
	public static final int BLUE = 6;

	public static final int[][] colors = {
		{  0,  0,  0},
		{255,255,255},
		{255,216,  0},
		{160,160,160},
		{ 59,152, 95},
		{127,  0,  0},
		{  0, 38,255}
	};

	public static int identify(int r, int g, int b) {
		int[] lsr = new int[7];
		int min = -1, minlsr = 255 * 255 * 3;
		int dr;
		int dg;
		int db;
		int ds;
		for (int i = 0; i < lsr.length; i++) {
			dr = colors[i][0] - r;
			dg = colors[i][1] - g;
			db = colors[i][2] - b;
			ds = dr * dr + dg * dg + db * db;
			if (min == -1 || ds < minlsr) {
				min = i;
				minlsr = ds;
			}
		}
		return min;
	}
}
