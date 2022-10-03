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
	
	synchronized public void setBefore(Node node) {
		
		
		this.before=node;
	}
	
	synchronized public void setAfter(Node node) {
		
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
	
	
//	a b c d
//
//	동시 수행
//	b.afterNode = d
//	a.afterNode = c
//	c.beforeNode = a
//	d.beforeNode = b
//
//	바른 순 : a c d
//	역순 : d b a
//	교차 검증 : a d (바른 순을 역순비교해서 있는 원소들만 가져온다.)
//
//	시간차 수행
//	a.afterNode = c
//	c.beforeNode = a
//
//	a.beforeNode = d
//	d.beforeNode = a
//
//	바른 순 : a d
//	역순 : d a

//  번거로운 부분이 많음. -> 삭제할 때, 참조하는 해시맵을 동기화하는 것이 나음.
	
	
}
