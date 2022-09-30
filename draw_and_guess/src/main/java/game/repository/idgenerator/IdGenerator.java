package game.repository.idgenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class IdGenerator<E> {
	
	private final int size;
	
	private final Info<E>[] infoSet;
	
	private final Queue<Integer> bucket;
	
	private final Node firstNode;
	
	private Node lastNode;
	
	private HashMap<Integer,Node> nodeMap;
	
	
	@SuppressWarnings("unchecked")
	public IdGenerator(int size){
	
			
			
			this.size=size;

			infoSet=(Info<E>[]) new Object[size];
			
			this.firstNode=new Node();
			
			this.lastNode=firstNode;
			
			bucket=new LinkedList<Integer>();
			
			for(int i=0; i<this.size; i++) {
				
				
				bucket.add(i);
			}
		
		
		
	}
	
	
	public int getID() {
		
		Object tmp;
		
		synchronized(bucket) {
			
			tmp = bucket.poll();
			
		}
			if(tmp==null) {
				return -1;
			}
			
			int temp=(int)tmp;
			
			Node n = new Node();
			
			n.id=temp;
			
			synchronized(this) {
				
				n.setBefore(lastNode);
				
				lastNode.setAfter(n);
				
				nodeMap.put(temp, n);
				
				lastNode=n;
				
				infoSet[temp].setState(true);
				
				return temp;
			}
			
		
		
	}
	
	public boolean erase(int id) {
		
		synchronized(this) {
			
			if(nodeMap.containsKey(id)) {
				
				Node n = nodeMap.get(id);
				
				if(n.equals(lastNode)) {
					
					lastNode=n.before;	
				}
				
				n.erase();
				
				infoSet[id].setState(false);
				
				return true;
				
			}
			
			
			return false;
		}
		
	}
	
	public Queue<Integer> getAll() {
			
			Queue<Integer> result=new LinkedList<>();
			
			if(lastNode.equals(firstNode)) {
				return result;
			}
			
			Node cur = this.firstNode.after;
			
			while(cur!=null) {
				
				result.add(cur.after.id);
				cur=cur.after;
			}
			
			
			return result;
		}
	
	
	public boolean set(int id, Info<E> info) {
		
		if(infoSet[id].getState()) {
			
			infoSet[id]=info;
			return true;
		}
		
		return false;
		
	}
	
	public Info<E> get(int id) {
		
		if(infoSet[id].getState()) {
			
			return infoSet[id];
		}
		
		return null;
	}

		
		
	
	
	
	

}
