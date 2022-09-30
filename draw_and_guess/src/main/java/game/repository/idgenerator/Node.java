package game.repository.idgenerator;


public class Node {

	Node before;
	
	Node after;
	
	int id;
	
	public Node(Node before, Node after) {
			
			this.before=before;
			this.after=after;
		
		
	}
	
	public Node() {}
	
	public void erase() {
		
		if(before!=null) {
			
			
			after.setBefore(before);
			
		}
		
		if(after!=null) {
			
			before.setAfter(after);
		}
		
		
	}
	
	public void setBefore(Node node) {
		
		
		this.before=node;
	}
	
	public void setAfter(Node node) {
		
		this.after=node;
	}
	
	@Override
	public int hashCode() {
		
		return id;
		
	}
	
	public boolean equals(Node node) {
		
		if(id==node.id) {
			return true;
		}
		
		return false;
		
	}
	
	
}
