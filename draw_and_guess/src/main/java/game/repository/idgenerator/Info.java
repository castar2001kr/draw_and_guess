package game.repository.idgenerator;

public class Info<E> {

	private boolean state;
	private E info;
	
	boolean getState() {
		
			
			return state;
		
		
	}
	
	void setState(boolean bool) {

		synchronized(this) {
			
			this.state=bool;
		}
		
	}
	
	public E getInfo() {
		
		return this.info;
	}
	
	public void setInfo(E info) {
		
		synchronized(this) {
			
			this.info=info;
		}
		
	}
	
	
}
