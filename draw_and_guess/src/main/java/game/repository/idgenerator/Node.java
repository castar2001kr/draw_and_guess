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
//	���� ����
//	b.afterNode = d
//	a.afterNode = c
//	c.beforeNode = a
//	d.beforeNode = b
//
//	�ٸ� �� : a c d
//	���� : d b a
//	���� ���� : a d (�ٸ� ���� �������ؼ� �ִ� ���ҵ鸸 �����´�.)
//
//	�ð��� ����
//	a.afterNode = c
//	c.beforeNode = a
//
//	a.beforeNode = d
//	d.beforeNode = a
//
//	�ٸ� �� : a d
//	���� : d a

// ������ �߰��� ����ϸ� ������ �߻�.
// ������ ��忡 �߰��Ǵ� ������ ������ ��带 �����ϴ� ������ ���ÿ� �̷������,
// ������ ��尡 �����.-> �߰��� ������ ��, �����ϴ� �ؽø��� ����ȭ�ϴ� ���� ����.
	
}
