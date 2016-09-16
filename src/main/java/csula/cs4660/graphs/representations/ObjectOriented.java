package csula.cs4660.graphs.representations;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Object oriented representation of graph is using OOP approach to store nodes
 * and edges
 *
 * TODO: Please fill the body of methods in this class
 */
public class ObjectOriented implements Representation {
    private Collection<Node> nodes;
    private Collection<Edge> edges;
    Node fromNode;
    Node toNode;
    Edge edge;

    public ObjectOriented(File file) {
    	try{
    		ArrayList<String> arr = readFile(file);
    		for(int i=1;i<arr.size();i++){
    			String s[] = arr.get(i).split(":");
    			try{
    				//numaric node only
    				fromNode = new Node(Integer.parseInt(s[0]));
    				toNode = new Node(Integer.parseInt(s[1]));
    			}catch(NumberFormatException e){
    				//non-numaric
    				fromNode = new Node(s[0]);
    				toNode = new Node(s[1]);
    			}
    			edge = new Edge(fromNode,toNode,Integer.parseInt(s[2]));
    			nodes.add(fromNode);
        		edges.add(edge);
        	}
    		
    	}catch(IOException e){
    		System.out.println(e.getMessage());
    	}
    }

    public ObjectOriented() {

    }
    //read file into an arraylist of Strings
    public static ArrayList<String> readFile(File file) throws IOException{
    	
    	Scanner read = new Scanner(file);
    	//read first line
    	String firstLine = read.nextLine().trim();
    	int NodeNo = Integer.parseInt(firstLine);
    	ArrayList<String> strarr = new ArrayList<>();
        while(read.hasNext()){
            String line = read.nextLine().trim();
            //System.out.println(line);
            strarr.add(line);
        }
        read.close();
    	return strarr;
    }
    @Override
    public boolean adjacent(Node x, Node y) {
    	Iterator<Edge> iterator = edges.iterator();
    	while(iterator.hasNext()){
    		Edge ed = iterator.next();
    		if(ed.getFrom().equals(x) && ed.getTo().equals(y)){
    			return true;
    		}
    	}
        return false;
    }

    @Override
    public List<Node> neighbors(Node x) {
    	Iterator<Edge> iterator = edges.iterator();
    	LinkedList<Node> neighbor_nodes= new LinkedList<Node>();
    	while(iterator.hasNext()){
    		Edge ed = iterator.next();
    		if(ed.getFrom().equals(x)){
    			neighbor_nodes.add(ed.getTo());
    			return neighbor_nodes;
    		}
    	}
        return null;
    }

    @Override
    public boolean addNode(Node x) {
    	//check if node x exists or not
    	if(nodes.contains(x)){
    		System.out.println("Error: this node exists already!");
    	}else{
    		nodes.add(x);
    		return true;
    	}
        return false;
    }

    @Override
    public boolean removeNode(Node x) {
    	//check if x exists
    	if(nodes.contains(x)){
    		//remove node
    		nodes.remove(x);
    		//remove edge
    		Iterator<Edge> iterator = edges.iterator();
    		while(iterator.hasNext()){
        		Edge ed = iterator.next();
        		if(ed.getFrom().equals(x)||ed.getTo().equals(x)){
        			edges.remove(ed);
        		}
        	}
    	}else{
    		System.out.println("Error: this node does not exist!");
    	}
        return false;
    }

    @Override
    public boolean addEdge(Edge x) {
    	
    	//from node does not exist then give error
    	if(!nodes.contains(x.getFrom())){
    		System.out.println("Error: from node does not exist");
    	}else{
    		//if to node does not exist then add toNode then add the edge
    		if(!nodes.contains(x.getTo())){
    			addNode(x.getTo());
    		}
    		edges.add(x);
    		return true;
    	}
    	
        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
    	//from or to node does not exist then give error
    	if((!nodes.contains(x.getFrom())) || (!nodes.contains(x.getTo()))){
    		System.out.println("Error: fromNode or toNode not exists");
    	}else{
    		edges.remove(x);
    	}
        return false;
    }

    @Override
    public int distance(Node from, Node to) {
    	Iterator iter = edges.iterator();
    	while(iter.hasNext()){
    		Edge ed = (Edge)iter.next();
    		if(ed.getFrom().equals(from) && ed.getTo().equals(to)){
    			return ed.getValue();
    		}
    	}
        return 0;
    }

    @Override
    public Optional<Node> getNode(int index) {
        return null;
    }
}
