package csula.cs4660.graphs.representations;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Adjacency matrix in a sense store the nodes in two dimensional array
 *
 * TODO: please fill the method body of this class
 */
public class AdjacencyMatrix implements Representation {
    private Node[] nodes;
    private int[][] adjacencyMatrix;

    public AdjacencyMatrix(File file) {
    	try{
    		ArrayList<String> arr = readFile(file);
    		Node fromNode;
    		Node toNode;
    		
    		//first record is total node number
    		try{
    			int NodeNum = Integer.parseInt(arr.get(0));
    			adjacencyMatrix = new int[NodeNum][NodeNum];
    			
    			//from second record
        		for(int i=1;i<arr.size();i++){
        			String s[] = arr.get(i).split(":");
        			//try{
        				//numaric node only
    					fromNode = new Node(Integer.parseInt(s[0]));
    					toNode = new Node(Integer.parseInt(s[1]));
    				//}catch(NumberFormatException e){
    					//non-numaric
    					//fromNode = new Node(s[0]);
    					//toNode = new Node(s[1]);
    				//}
    				System.out.println("test [" + fromNode.getData() + "][" + toNode.getData() + "]" +  Integer.parseInt(s[2]));
        			adjacencyMatrix[(int)fromNode.getData()][(int)toNode.getData()] = 1;
        			
        			
        		}
        		for(int j=0;j<NodeNum;j++){
    				System.out.println("matrix: ");
    				for(int k=0;k<NodeNum;k++){
    					System.out.print( adjacencyMatrix[j][k] + " ");
    				}
    				System.out.println();
    			}
    		}catch(NumberFormatException e){
    			System.out.println("First line of the file " + file + " needs to be a numeric number");
    		}
    		
    		
    	}catch(IOException e){
    		System.out.println(e.getMessage());
    	}
    	
    }

    public AdjacencyMatrix() {

    }
   
    public static ArrayList<String> readFile(File file) throws IOException{
    	
    	Scanner read = new Scanner(file);
    	//read first line
    	//String firstLine = read.nextLine().trim();
    	//int NodeNo = Integer.parseInt(firstLine);
    	ArrayList<String> strarr = new ArrayList<>();
        while(read.hasNext()){
            String line = read.nextLine().trim();
            System.out.println(line);
            strarr.add(line);
        }
        read.close();
    	return strarr;
    }
    @Override
    public boolean adjacent(Node x, Node y) {
    	int index_x = (int)x.getData();
    	int index_y = (int)y.getData();
    	if(adjacencyMatrix[index_x][index_y] > 0){
    		return true;
    	}
    	
        return false;
    }

    @Override
    public List<Node> neighbors(Node x) {
    	int index_x = (int)x.getData();
    	ArrayList<Node> node_arr = new ArrayList<>();
    	for(int i=0;i<adjacencyMatrix[index_x].length;i++){
    		if(adjacencyMatrix[index_x][i] > 0){
    			node_arr.add(new Node(i));
    		}
    	}
    	if(node_arr != null){
    		return node_arr;
    	}
    	
        return null;
    }

    @Override
    public boolean addNode(Node x) {
    	int n = (int)x.getData();
    	if(adjacencyMatrix[n] != null){
    		System.out.println("Error: THis node exists already!");
    	}else{
    		//existing matrix increase to n x n
    		for(int i=0;i<adjacencyMatrix.length;i++){
    			adjacencyMatrix[i][n] = 0;
    		}
    		//add last row for nth node
    		for(int j=0;j<n;j++){
    			adjacencyMatrix[n][j] = n; 
    		}
    		return true;		
    	}
        return false;
    }

    @Override
    public boolean removeNode(Node x) {
    	int index = (int)x.getData();
    	//number of rows and cols decrease 1
    	int num = adjacencyMatrix.length -1;
    	int[][] newMatrix = new int[num][num];
    	int m = 0;
    	int n = 0;
    	
    	for(int i=0;i<adjacencyMatrix[0].length;i++){
    		//remove row x
    		if(i==index){
    			continue;
    		}
    		m++;
    		System.out.print("m " + m + " ");
    		for(int j=0;i<adjacencyMatrix.length;j++){
    			//remove col x
    			if(j==index){
    				continue;
    			}else{
    				newMatrix[m][n] = adjacencyMatrix[i][j];
    				n++;
    				System.out.println("n: " + n);
    			}
    		}
    	}
    	try{
    		//overwrite the new array to the old one
    		System.arraycopy(newMatrix, 0, adjacencyMatrix, 0, num);
    		return true;
    	}catch(IndexOutOfBoundsException e1 ){
    		System.out.println(e1.getMessage());
    	}catch(ArrayStoreException e2){
    		System.out.println(e2.getMessage());
    	}catch(NullPointerException e3){
    		System.out.println(e3.getMessage());
    	}    	
    	
        return false;
    }

    @Override
    public boolean addEdge(Edge x) {
    	
        int index_f = (int)x.getFrom().getData();
        int index_t = (int)x.getTo().getData();
    	
        //check if the fromNode exists or not, if not return error
        if(adjacencyMatrix[index_f] != null){
        	//check if toNode exists or not
            if(adjacencyMatrix[index_t] != null){
            	adjacencyMatrix[index_f][index_t] = 1;
            	return true;
            }else{
            	this.addNode(x.getTo());
            	adjacencyMatrix[index_f][index_t] = 1;
            	return true;
            }
        }else{
        	System.out.println("Error: fromNode does not exists!");
        }
        
        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
    	
        return false;
    }

    @Override
    public int distance(Node from, Node to) {
        return 0;
    }

    @Override
    public Optional<Node> getNode(int index) {
        return null;
    }
}
