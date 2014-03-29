import lejos.nxt.ColorSensor.Color;



public class ColorIdentifier {
	public static final int
			BLACK = 0,
			WHITE = 1,
			YELLOW = 2,
			GRAY = 3,
			GREEN = 4,
			RED = 5,
			BLUE = 6
		;
	public static final int[][] colors1 = {
		{  50,  37,  28 },//{  0,  0,  0},
		{ 260, 252, 249 },//{255,255,255},
		{ 227, 170,  70 },//{255,216,  0},
		{ 999, 999, 999 },//{160,160,160},
		{ 999, 999, 999 },//{ 59,152, 95},
		{ 200, 121, 112 },//{127,  0,  0},
		{ 121, 136, 159 },//{  0, 38,255}
	};
	public static final int[][] colors2 = {
		{  60,  31,  31 },//{  0,  0,  0},
		{ 262, 271, 252 },//{255,255,255},
		{ 276, 236, 111 },//{255,216,  0},
		{ 999, 999, 999 },//{160,160,160},
		{ 999, 999, 999 },//{ 59,152, 95},
		{ 167,  92,  92 },//{127,  0,  0},
		{ 138, 134, 173 },//{  0, 38,255}
	};
	
	public static int identify1(Color c) {
		return identify1(c.getRed(),c.getGreen(),c.getBlue());
	}
	
	public static int identify2(Color c) {
		return identify2(c.getRed(),c.getGreen(),c.getBlue());
	}
	
	public static int identify1(int r,int g,int b) {
		int[] lsr = new int[7];
		int min = -1,minlsr = 255*255*3;
		int dr,dg,db,ds;
		for (int i=0; i<lsr.length; i++) {
			dr = colors1[i][0] - r;
			dg = colors1[i][1] - g;
			db = colors1[i][2] - b;
			ds = dr*dr + dg*dg + db*db;
			if (min==-1 || ds<minlsr) {
				min = i;
				minlsr = ds;
			}
		}
		return min;
	}
	
	public static int identify2(int r,int g,int b) {
		int[] lsr = new int[7];
		int min = -1,minlsr = 255*255*3;
		int dr,dg,db,ds;
		for (int i=0; i<lsr.length; i++) {
			dr = colors2[i][0] - r;
			dg = colors2[i][1] - g;
			db = colors2[i][2] - b;
			ds = dr*dr + dg*dg + db*db;
			if (min==-1 || ds<minlsr) {
				min = i;
				minlsr = ds;
			}
		}
		return min;
	}
}
