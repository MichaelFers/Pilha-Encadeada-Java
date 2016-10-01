package PilhaEncadeada;

//O <T> logo ap�s o nome da classe(isso se chama generic) � para informar o tipo na hora que for inst�nciar um objeto da classe
//exemplo: Pilha<String> pilha = new Pilha();
public class PilhaEncadeada<T>{

	//atributos
	private No<T> primeiro;
	private int tamanho;

	//Construtor da pilha
	public PilhaEncadeada(){

		 tamanho = 0;//quando for instanciado um objeto de Pilha, o tamanho inicial � 0
		 primeiro = null;

	}
	//m�todo que retorna o tamanho
	public int tamanho(){
		return this.tamanho;
	}

	//metodo add
	public void empilhar(T item){
		
		//cria um novo No<T> passando o tipo T chamado de item para a instancia do No
		//Esse item vai ser armazena e referenciado pelo tipo No
		No<T> novoNo = new No(item);
		
		//Se for vazio, ele atribue o novoNo criado a cima como o No chamado de primeiro(atributo da classe)
		if(eVazio()){
			primeiro = novoNo;
			this.tamanho++;
		}else{//caso n�o for fazio, ele cria 2 tipo No, um para receber o valor do No primeiro(atributo da classe)
			  //e o outro como null para ser alterado dentro do while

			No aux = primeiro;
			No aux2 = null;

			while(aux != null){
				aux2 = aux;//o No aux2 pega o valor do aux pq o aux vai ser alterado para o pr�ximo no dele
				aux = aux.proximo;
			}
			//aqui o No aux tem valor de null e n�o nos importa, dentro do while o No aux2 recebeu o �ltimo elemento.
			//atribue o proximo do aux2 para o novoNo
			aux2.proximo = novoNo;//aqui ele faz a refer�ncia do pr�ximo no aux2 com o novoNo, observe que quem faz a liga��o entre eles � o No chamado de Primeiro, o No primeiro tem No dentro dele e com isso faz a liga��o entre eles
			this.tamanho++;//incrementa mais 1 a vari�vel tamanho para informar que 1 elemento foi adicionado

		}
	}//______________________________________Fim add

	//m�todo add que retorna um tipo gen�rico chamado de T, esse T pode ser qualquer objeto
	public T pegar(int index){
		
		if(eVazio()){
			throw new IllegalAccessError("Erro: Pilha vazia");//verifica se � vazia e se for, lan�a uma exce��o
		}else if(index==tamanho){
			return this.pegar();//se n�o for e se o n�mero recebido como par�metro for igual ao tamanho total de elementos encadeado, ele chama um outro m�todo que retorna a ultima posi��o
		}else{
			No<T> aux = primeiro;
			No<T> aux2 = null;
			int cont = 1;
			//a condi��o do while � usada para parar de trocar os valores enquanto con <= index, incrementando +1 dentro do while e atribuindo o valor do aux ao pr�ximo no dele mesmo
			while(aux != null && cont <= index){
				aux2 = aux;
				aux = aux.proximo;
				cont++;
			}
			//parou o while e agora temos o elemento que queremos na vari�vel de referencia aux2, e retornando o elemento armazenado pelo no em item
			return aux2.item;
		}

	}//___________________________________________________________Fim pegar

	//m�todo que retorna o ultimo elemento encadeado, o topo da pilha
	public T pegar(){

		if(eVazio()){
			throw new IllegalAccessError("Erro: Pilha vazia");
		}else{
		No<T> aux = primeiro;// � importante criar uma vari�vel de No, auxiliar, para armazenar as informa��es do No primeiro(atributo da classe) e que vai ser utilizado como condi��o para o while enquanto for diferente de nulo
		No<T> aux2 = null;   //aux2 tem valor de nulo para receber dentro do while o valor da aux pq o aux vai ficar mudando de elemento a cada itera��o do while
		while(aux != null){
			aux2 = aux;
			aux = aux.proximo;
		}
		//o No aux tem o valor de null ap�s o termino do while e n�o nos interessa, o importante � o aux2 que tem valor anterior de aux ou seja, antes dele passar a ser nulo
		return aux2.item;
		}
	}//_______________________________________________Fim pegar
	
	//m�todo eVazio que verifica se o No primeiro(atributo da classe) � igual ao null, esse m�todo s� vai ser usado dentro da classe, por isso � privado
	private boolean eVazio(){
		if(primeiro==null){
			return true;
		}else{
			return false;
		}
	}//_______________________________________________Fim eVazio
	
	//m�todo remover, recebe como par�metro a posi��o a ser removida
	private boolean remover(int index){
		if(this.eVazio() || index < 0 || index > tamanho){//se o m�todo eVazio retornar true, ele entra no if ou se o valor for menor que zero ou maior que o tamanho de elementos
			throw new IllegalAccessError("Erro"); //lan�a uma exce��o
		}else if(index==1){
			primeiro = primeiro.proximo; //aqui se quiser remover a posi��o 1, apenas atribuei o valor do primeiro para o proximo do primeiro
			tamanho--;
			return true;
		}else{
			No<T> aux = primeiro;
			No<T> aux2 = null;
			int cont = 1;
			while(cont<index){//condi��o do while para ele parar quando o cont for menor que index recebido como par�metro para o m�todo
				aux2 = aux;
				aux = aux.proximo;//entrou no while e alterou o valor do No aux para o pr�ximo dele
				cont++;// incrementou mais 1 a vari�vel cont
			}
			
			aux2.proximo = aux.proximo;//aqui faz a substitui��o dos No do aux2.pr�ximo para o aux.pr�ximo. o aux2 tem o No anterior do aux, por isso tem que fazer a refer�ncia com o outro No
			tamanho--;
			return true;
		}
	}//___________________________________________________________________________Fim remover
	public boolean desempilhar(){
		return this.remover(this.tamanho);//faz uso da chamada do m�todo remover para desempilhar a pilha pegando o ultimo elemento que entrou FIFO
	}
	
	public boolean desempilhar(int index){
		return this.remover(index);//faz uso da chamada do m�todo remover e desempilha conforme a posi��o informado no par�metro do m�todo
	}
	
	public String toString(){
		String to_string = "";
		No<T> aux = primeiro;

		while(aux != null){
			to_string += aux.item+" -> ";
			aux = aux.proximo;
		}
		return to_string;
	}

}
