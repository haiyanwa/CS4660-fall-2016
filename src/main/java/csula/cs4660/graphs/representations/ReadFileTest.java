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

public class ReadFileTest {
	private Map<Node, Collection<Edge>> adjacencyList;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file1 = "src/csula/cs4660/graphs/test/test1";
		String file2 = "src/csula/cs4660/graphs/test/test2";
		
		File file_1 = new File(file1);
		File file_2 = new File(file2);
		try{
			
			ArrayList<String> arr = readFile(file_2);
			for(int i=0;i<arr.size();i++){
    			String s[] = arr.get(i).split(":");
    			//System.out.println("Graph2 " + Arrays.toString(s));
			}
			Graph graph1 = new Graph(
		            Representation.of(
		                Representation.STRATEGY.ADJACENCY_LIST,
		                file_1
		            )
		        );
			
			Graph graph2 = new Graph(
		            Representation.of(
		                Representation.STRATEGY.ADJACENCY_LIST,
		                file_2
		            )
		        );
			System.out.println("adjacent: " + graph1.adjacent(new Node(0), new Node(10)));
			System.out.println(graph1.adjacent(new Node(3), new Node(6)));
			System.out.println("Add node " + graph1.addNode(new Node(1)));
			System.out.println("Add node " + graph1.addNode(new Node(6)));
			System.out.println("Add node " + graph1.addNode(new Node(11)));
			System.out.println("remove node " + graph1.removeNode(new Node(6)));
			System.out.println("neighbour " + graph1.neighbors(new Node(9)));
			System.out.println("remove node " + graph1.removeNode(new Node(1234)));
			System.out.println("Add edge " + graph1.addEdge(new Edge(new Node(1), new Node(4), 1)));
			System.out.println("neighbour " + graph1.neighbors(new Node(1)));
			System.out.println("Add edge " + graph1.addEdge(new Edge(new Node(1), new Node(4), 1)));
			
			System.out.println("graph2 neighbors " + graph2.neighbors(new Node(0)));
			System.out.println("graph2 neighbors " + graph2.neighbors(new Node(5)));
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	private static ArrayList<String> readFile(File file) throws IOException{
    	
    	Scanner read = new Scanner(file);
    	//read first line
    	//need check??
    	String firstLine = read.nextLine().trim();
    	int length = Integer.parseInt(firstLine);
    	ArrayList<String> arr = new ArrayList<String>();
    	int i = 0;
        while(read.hasNext()){
            String line = read.nextLine().trim();
            //System.out.println(line);
            arr.add(line);
        }
        read.close();
    	return arr;
    }

}
