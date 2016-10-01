package PilhaEncadeada;

public class No<T> {

	protected No<T> proximo;
	protected T item;
	
	public No(T item){
		proximo = null;
		this.item = item;
	}
	
}
