

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Navigation {
	public static void main(String args[]) throws IOException {
		int[] nodes = {
			0,12,19
		};
		
		// Get paths
		ArrayList<Integer> path = getPath(nodes);
		
		// Write paths
		FileWriter fout = new FileWriter(new File("Path.txt"));
		int node,prev=0,dir,len;
		String out;
		for (int i=0; i<path.size(); i++) {
			node = path.get(i);
			if (i==0) dir = 0;
			else dir = (node==graph[prev][2]) ? 0 : ((node==graph[prev][1]) ? -1 : 1);
			len = graph[node][0];
			out = node + " " + dir + " " + len + "\n";
			fout.write(out);
			System.out.print(out);
			prev = node;
		}
		fout.flush();
		fout.close();
	}
	
	public static int n = -1;
	public static int[][] graph = {
		{  21,   1, n, n, 0  }, // Starting node
		{ 207,   2, n, n, 1  },
		{ 284,   3, n, n, 2  },
		{ 146,  10,12, n, 3  },
		{ 257,   7, 1, n, 4  },
		{ 104,   6, n,17, 5  },
		{ 104,   n, 7, n, 6  },
		{ 102,   5, 8, n, 7  },
		{  76,  20, 9, n, 8  },
		{  56,  11, n, n, 9  },
		{ 420,   n,11, n, 10 },
		{ 420,  30, n, n, 11 },
		{ 117,   n,30, n, 12 },
		{ 106,  14, n, n, 13 },
		{  70,  20, n, n, 14 },
		{ 211,  16, 4, n, 15 },
		{  47,  24,18, n, 16 },
		{ 150,   n,24,18, 17 },
		{  89,   n,19,13, 18 },
		{  70,  28,21, n, 19 },
		{ 111,   n,28,21, 20 },
		{  72,  30, n, n, 21 },
		{ 104,  19,13, n, 22 },
		{  47,  15, n, n, 23 },
		{ 104,  23,32, n, 24 },
		{  89,   n,23,32, 25 },
		{  44,   n,22,25, 26 },
		{  70,  22,25, n, 27 },
		{ 104,  27,37, n, 28 },
		{  72,   n,27,37, 29 },
		{ 305,  29,40, n, 30 },
		{  47,  45,33, n, 31 },
		{ 105,   n,45,33, 32 },
		{  50,  36, n,34, 33 },
		{ 239,   n,35,26, 34 },
		{  75,   n,34,36, 35 },
		{  30,  49,38, n, 36 },
		{ 105,   n,49,38, 37 },
		{  72,  40, n, n, 38 },
		{ 107,   n, n,34, 39 },
		{ 180,  42,55, n, 40 },
		{  47,  72, n, n, 41 },
		{  72,   n, n,49, 42 },
		{ 158,  44,72, n, 43 },
		{  47,  58,46, n, 44 },
		{ 147,   n,58,46, 45 },
		{  89,   n,48,39, 46 },
		{  88,  48,39, n, 47 },
		{  70,  53,50, n, 48 },
		{ 147,   n,53,50, 49 },
		{  72,  55, n, n, 50 },
		{  68,   n,47, n, 51 },
		{  70,  47, n, n, 52 },
		{  88,  52,60, n, 53 },
		{  72,   n,52,60, 54 },
		{ 162,  54,67, n, 55 },
		{ 201,  57,43, n, 56 },
		{  47,  63,59, n, 57 },
		{ 158,   n,63,59, 58 },
		{ 161,  65,61, n, 59 },
		{  68,   n,65,61, 60 },
		{  72,  67, n, n, 61 },
		{  47,  56, n, n, 62 },
		{ 111,  62, n, n, 63 },
		{ 161,   n,62, n, 64 },
		{ 111,  64,69, n, 65 },
		{  72,   n,64,69, 66 },
		{ 181,  66,71, n, 67 },
		{ 210,  56, n, n, 68 },
		{  88,  68, n, n, 69 },
		{  72,   n,68, n, 70 },
		{  88,  70, n, n, 71 },
		{ 147,  31,15, n, 72 },
	};
	
	public static ArrayList<Integer> getPath(int[] nodes) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(nodes[0]);
		for (int i=0; i<nodes.length-1; i++) {
			path.addAll(dij(nodes[i],nodes[i+1]));
		}
		return path;
	}
	
	public static ArrayList<Integer> dij(int a,int b) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(a);
		int[][] table = new int[graph.length][2];
		for (int i=0; i<table.length; i++) {
			table[i][0] = 0;
			table[i][1] = -1;
		}
		
		int node,l,f,r;
		while (queue.size()>0 && table[b][1]==-1) {
			node = queue.remove(0);
			l = graph[node][1];
			f = graph[node][2];
			r = graph[node][3];
			if (l>-1 && (table[l][1]==-1 || table[node][0]+graph[l].length<table[l][0])) {
				queue.add(l);
				table[l][0] = table[node][0]+graph[l].length;
				table[l][1] = node;
			}
			if (f>-1 && (table[f][1]==-1 || table[node][0]+graph[f].length<table[f][0])) {
				queue.add(f);
				table[f][0] = table[node][0]+graph[f].length;
				table[f][1] = node;
			}
			if (r>-1 && (table[r][1]==-1 || table[node][0]+graph[r].length<table[r][0])) {
				queue.add(r);
				table[r][0] = table[node][0]+graph[r].length;
				table[r][1] = node;
			}
		}
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		int current = b;
		while (current!=-1 && current!=a) {
			path.add(0,current);
			current = table[current][1];
		}
		
		return path;
	}
}
