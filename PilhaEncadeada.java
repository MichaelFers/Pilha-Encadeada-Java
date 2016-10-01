package PilhaEncadeada;

//O <T> logo após o nome da classe(isso se chama generic) é para informar o tipo na hora que for instânciar um objeto da classe
//exemplo: Pilha<String> pilha = new Pilha();
public class PilhaEncadeada<T>{

	//atributos
	private No<T> primeiro;
	private int tamanho;

	//Construtor da pilha
	public PilhaEncadeada(){

		 tamanho = 0;//quando for instanciado um objeto de Pilha, o tamanho inicial é 0
		 primeiro = null;

	}
	//método que retorna o tamanho
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
		}else{//caso não for fazio, ele cria 2 tipo No, um para receber o valor do No primeiro(atributo da classe)
			  //e o outro como null para ser alterado dentro do while

			No aux = primeiro;
			No aux2 = null;

			while(aux != null){
				aux2 = aux;//o No aux2 pega o valor do aux pq o aux vai ser alterado para o próximo no dele
				aux = aux.proximo;
			}
			//aqui o No aux tem valor de null e não nos importa, dentro do while o No aux2 recebeu o último elemento.
			//atribue o proximo do aux2 para o novoNo
			aux2.proximo = novoNo;//aqui ele faz a referência do próximo no aux2 com o novoNo, observe que quem faz a ligação entre eles é o No chamado de Primeiro, o No primeiro tem No dentro dele e com isso faz a ligação entre eles
			this.tamanho++;//incrementa mais 1 a variável tamanho para informar que 1 elemento foi adicionado

		}
	}//______________________________________Fim add

	//método add que retorna um tipo genérico chamado de T, esse T pode ser qualquer objeto
	public T pegar(int index){
		
		if(eVazio()){
			throw new IllegalAccessError("Erro: Pilha vazia");//verifica se é vazia e se for, lança uma exceção
		}else if(index==tamanho){
			return this.pegar();//se não for e se o número recebido como parâmetro for igual ao tamanho total de elementos encadeado, ele chama um outro método que retorna a ultima posição
		}else{
			No<T> aux = primeiro;
			No<T> aux2 = null;
			int cont = 1;
			//a condição do while é usada para parar de trocar os valores enquanto con <= index, incrementando +1 dentro do while e atribuindo o valor do aux ao próximo no dele mesmo
			while(aux != null && cont <= index){
				aux2 = aux;
				aux = aux.proximo;
				cont++;
			}
			//parou o while e agora temos o elemento que queremos na variável de referencia aux2, e retornando o elemento armazenado pelo no em item
			return aux2.item;
		}

	}//___________________________________________________________Fim pegar

	//método que retorna o ultimo elemento encadeado, o topo da pilha
	public T pegar(){

		if(eVazio()){
			throw new IllegalAccessError("Erro: Pilha vazia");
		}else{
		No<T> aux = primeiro;// é importante criar uma variável de No, auxiliar, para armazenar as informações do No primeiro(atributo da classe) e que vai ser utilizado como condição para o while enquanto for diferente de nulo
		No<T> aux2 = null;   //aux2 tem valor de nulo para receber dentro do while o valor da aux pq o aux vai ficar mudando de elemento a cada iteração do while
		while(aux != null){
			aux2 = aux;
			aux = aux.proximo;
		}
		//o No aux tem o valor de null após o termino do while e não nos interessa, o importante é o aux2 que tem valor anterior de aux ou seja, antes dele passar a ser nulo
		return aux2.item;
		}
	}//_______________________________________________Fim pegar
	
	//método eVazio que verifica se o No primeiro(atributo da classe) é igual ao null, esse método só vai ser usado dentro da classe, por isso é privado
	private boolean eVazio(){
		if(primeiro==null){
			return true;
		}else{
			return false;
		}
	}//_______________________________________________Fim eVazio
	
	//método remover, recebe como parâmetro a posição a ser removida
	private boolean remover(int index){
		if(this.eVazio() || index < 0 || index > tamanho){//se o método eVazio retornar true, ele entra no if ou se o valor for menor que zero ou maior que o tamanho de elementos
			throw new IllegalAccessError("Erro"); //lança uma exceção
		}else if(index==1){
			primeiro = primeiro.proximo; //aqui se quiser remover a posição 1, apenas atribuei o valor do primeiro para o proximo do primeiro
			tamanho--;
			return true;
		}else{
			No<T> aux = primeiro;
			No<T> aux2 = null;
			int cont = 1;
			while(cont<index){//condição do while para ele parar quando o cont for menor que index recebido como parâmetro para o método
				aux2 = aux;
				aux = aux.proximo;//entrou no while e alterou o valor do No aux para o próximo dele
				cont++;// incrementou mais 1 a variável cont
			}
			
			aux2.proximo = aux.proximo;//aqui faz a substituição dos No do aux2.próximo para o aux.próximo. o aux2 tem o No anterior do aux, por isso tem que fazer a referência com o outro No
			tamanho--;
			return true;
		}
	}//___________________________________________________________________________Fim remover
	public boolean desempilhar(){
		return this.remover(this.tamanho);//faz uso da chamada do método remover para desempilhar a pilha pegando o ultimo elemento que entrou FIFO
	}
	
	public boolean desempilhar(int index){
		return this.remover(index);//faz uso da chamada do método remover e desempilha conforme a posição informado no parâmetro do método
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
