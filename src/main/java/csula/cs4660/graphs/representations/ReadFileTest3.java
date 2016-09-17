package csula.cs4660.graphs.representations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import csula.cs4660.graphs.representations.Representation.STRATEGY;

public class ReadFileTest3 {
	private Map<Node, Collection<Edge>> adjacencyList;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file1 = "src/csula/cs4660/graphs/test/test1";
		String file2 = "src/csula/cs4660/graphs/test/test2";
		
		File file_1 = new File(file1);
		File file_2 = new File(file2);
		try{
			
			ArrayList<String> arr = AdjacencyMatrix.readFile(file_1);
			
			Graph graph1 = new Graph(
		            Representation.of(
		                Representation.STRATEGY.OBJECT_ORIENTED,
		                file_1
		            )
		        );
			
			Graph graph2 = new Graph(
		            Representation.of(
		                Representation.STRATEGY.OBJECT_ORIENTED,
		                file_2
		            )
		        );
			System.out.println("adjacent: " + graph1.adjacent(new Node(1), new Node(2)));
			System.out.println("adjacent:" + graph1.adjacent(new Node(3), new Node(6)));
			System.out.println("adjacent: " + graph1.adjacent(new Node(3), new Node(10)));
			System.out.println("adjacent: " + graph1.adjacent(new Node(1), new Node(6)));
			System.out.println("adjacent: " + graph1.adjacent(new Node(4), new Node(9)));
			System.out.println("neighbor: " + graph1.neighbors(new Node(1)));
			
			System.out.println("addnode 1: " + graph1.addNode(new Node(1)));
			System.out.println("addnode 6:" + graph1.addNode(new Node(6)));
			System.out.println("addnode 11:" + graph1.addNode(new Node(11)));
			
			System.out.println("removenode 6" + graph1.removeNode(new Node(6)));
			System.out.println("neighbor: " + graph1.neighbors(new Node(9)));
			System.out.println("removenode 6" + graph1.removeNode(new Node(1234)));
			System.out.println("add edge " + graph1.addEdge(new Edge(new Node(1), new Node(4), 1)));
			System.out.println("neighbor: " + graph1.neighbors(new Node(1)));
			System.out.println("adjacent " + graph1.adjacent(new Node(1), new Node(4)));
			System.out.println("add edge 1-2: " + graph1.addEdge(new Edge(new Node(1), new Node(2), 1)));
			System.out.println("remove edge 1,6" + graph1.removeEdge(new Edge(new Node(1), new Node(6), 1)));
			System.out.println("remove edge 6,5" + graph1.removeEdge(new Edge(new Node(6), new Node(5), 1)));
			System.out.println("adjacent " + graph1.adjacent(new Node(5), new Node(6)));
			System.out.println("neighbor: " + graph2.neighbors(new Node(0)));
			
			
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	
}
