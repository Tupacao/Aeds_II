import java.io.*;

class Jogador {

    // Declaração dos atributos da classe Jogador

    private int id, altura, peso;
    private String nome, universidade, anoNascimento, estadoNascimento, cidadeNascimento;

    // Construtor sem parâmetro

    public Jogador () {
        this.id = -1;
        this.altura = -1;
        this.peso = -1;
        this.nome = "nao informado";
        this.universidade = "nao informado";
        this.anoNascimento = "nao informado";
        this.estadoNascimento = "nao informado";
    }

    // Construtor com parâmetro 
    
    public Jogador (int id, int altura, int peso, String nome, String universidade, String anoNascimento, String cidadeNascimento, String estadoNascimento) {
        setID(id);
        setAltura(altura);
        setPeso(peso);
        setNome(nome);
        setUniversidade(universidade);
        setAnoNascimento(anoNascimento);
        setCidadeNascimento(cidadeNascimento);
        setEstadoNascimento(estadoNascimento);
    }

    // Criação de todos os setters

    public void setID(int id) {
        this.id = id;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUniversidade(String universidade) {
        
        // Caso um dado não seja informado ele é substituido para "nao informado"

        if (universidade.equals("-1")) {
            this.universidade = "nao informado";
        }
        else{
            this.universidade = universidade;
        }
    }

    public void setAnoNascimento(String anoNascimento) {
        
        // Caso um dado não seja informado ele é substituido para "nao informado"
        
        if (anoNascimento.equals("-1")) {
            this.anoNascimento = "nao informado";
        }
        else{
            this.anoNascimento = anoNascimento;
        }
    }

    public void setEstadoNascimento(String estadoNascimento) {
        
        // Caso um dado não seja informado ele é substituido para "nao informado"
        
        if (estadoNascimento.equals("-1")) {
            this.estadoNascimento = "nao informado";
        }
        else{
            this.estadoNascimento = estadoNascimento;
        }
    }   

    public void setCidadeNascimento (String cidadeNascimento){
        
        // Caso um dado não seja informado ele é substituido para "nao informado"
        
        if (cidadeNascimento.equals("-1")) {
            this.cidadeNascimento = "nao informado";
        }
        else{
            this.cidadeNascimento = cidadeNascimento;
        }
    }

    // Criação dos getters

    public int getID() {
        return this.id;
    }

    public int getAltura() {
        return this.altura;
    }

    public int getPeso() {
        return this.peso;
    }

    public String getNome() {
        return this.nome;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public String getAnoNascimento() {
        return this.anoNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    public String getCidadeNascimento (){
        return this.cidadeNascimento;
    }

    // Função para mostrar na tela o jogador X

    public void printTerminal(){
        MyIO.println("[" + this.id + "]" + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso + " ## " + this.anoNascimento + " ## " + this.universidade + " ## " + this.cidadeNascimento + " ## " + this.estadoNascimento + " ##"); 
    }

    // Função responsável por clonar o jogador X

    public Jogador clone () {

        Jogador resp = new Jogador(this.id, this.altura, this.peso, this.nome, this.universidade, this.anoNascimento, this.estadoNascimento, this.cidadeNascimento);

        return resp;
    }

}

class Celula {

    public Jogador element;
    public Celula prox;
    public Celula ant;

    public Celula (Jogador x){
        this.element = x;
        this.prox = null;
        this.ant = null;
    }

    public Celula (){
        this.element = new Jogador();
        this.prox = null;
        this.ant = null;
    }

}

class Lista {
    
    private Celula cafecomleite;
    public int tam = 0;
    public Lista(){
        this.cafecomleite = new Celula();
    }

    public void insereInicio (Jogador x){

        if(cafecomleite.prox == null){
            Celula insere = new Celula(x);
            cafecomleite.prox = insere;
            insere.ant = cafecomleite;
            return;
        }

        Celula insere = new Celula(x);
        
        Celula aux = cafecomleite.prox;
        cafecomleite.prox = insere;
        insere.ant = cafecomleite;
        insere.prox = aux;
        aux.ant = insere;
    }

    public void insereFinal (Jogador x){

        if(cafecomleite.prox == null){
            Celula insere = new Celula(x);
            cafecomleite.prox = insere;
            return;
        }

        Celula i = cafecomleite;
        Celula insere = new Celula(x);
        for(; i.prox != null; i = i.prox); 
        i.prox = insere;
        tam++;
    }

    public void insere (Jogador x, int pos){

        Celula i = cafecomleite;

        for(int j = 0; j < pos; j++, i = i.prox);

        Celula aux = i.prox;
        Celula insere = new Celula(x);
        
        i.prox = insere;
        insere.prox = aux; 

    }

    public Jogador removeFIM (){
        
        Celula i = cafecomleite.prox;
        for(; i.prox.prox != null; i = i.prox);

        Celula aux = i.prox;
        i.prox = null;

        return aux.element;

    }

    public Jogador removeInicio () {
        Celula aux;

        aux = cafecomleite.prox;
        cafecomleite.prox = cafecomleite.prox.prox;

        return aux.element;
    }

    public Jogador remove (int pos){
        Celula aux;
        
        Celula i = cafecomleite;

        for(int j = 0; j < pos; j++, i = i.prox);

        aux = i.prox;
        i.prox = i.prox.prox;
        
        return aux.element;
    }

    public void printarTela (){
        for (Celula i = cafecomleite.prox; i != null; i = i.prox) {
            i.element.printTerminal();
        }
        MyIO.print("\n");
    }

    public void CopiarVetor(Jogador vetor[]) {
    Celula j = cafecomleite.prox;
    for (int i = 0; i < tam; i++, j = j.prox) {
        vetor[i] = j.element;
    }
}

public void refazerLista(Jogador vetor[]) {
    Celula j = cafecomleite.prox;
    for (int i = 0; i < tam; i++, j = j.prox) {
        j.element = vetor[i];
    }
}

}

public class TP03Q11 {
    
    public static void trocar(Jogador[] jogadores, int i, int j) {
        Jogador temp = jogadores[i];
        jogadores[i] = jogadores[j];
        jogadores[j] = temp;
    }
    
    public static void ordenacaoQuickSort(int esq, int dir, Jogador[] jogadores) {
        if (esq < dir) {
            int i = esq, j = dir;
            Jogador pivo = jogadores[(esq + dir) / 2];
    
            while (i <= j) {
                while (jogadores[i].getCidadeNascimento().compareTo(pivo.getCidadeNascimento()) < 0 ||
                        (jogadores[i].getCidadeNascimento().equals(pivo.getCidadeNascimento()) && jogadores[i].getNome().compareTo(pivo.getNome()) > 0)) {
                    i++;
                }
                while (jogadores[j].getCidadeNascimento().compareTo(pivo.getCidadeNascimento()) > 0 ||
                        (jogadores[j].getCidadeNascimento().equals(pivo.getCidadeNascimento()) && jogadores[j].getNome().compareTo(pivo.getNome()) < 0)) {
                    j--;
                }
                if (i <= j) {
                    trocar(jogadores, i, j);
                    i++;
                    j--;
                }
            }
            
            // Se o índice final do primeiro segmento (i) for menor que k, chame o QuickSort apenas no segundo segmento
            if (esq < j) {
                ordenacaoQuickSort(esq, j, jogadores);
            }
    
            // Verifique se é necessário ordenar o primeiro segmento
            if (i < dir) {
                ordenacaoQuickSort(i, dir, jogadores);
            }
        }
    }

    // Função que trata a string colocando no lugar dos espaços vazios o número -1

    public static String[] mudarString (String str) {

        String resp = "";

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ','){
                resp+= str.charAt(i);
                if (i+1 == str.length() || str.charAt(i+1) == ',') {
                    resp+= "-1";
                } else {
                    resp+= str.charAt(++i);
                }
            }
            else{
                resp+= str.charAt(i);
            }
        }

        // A função retorna vetores de caracteres separados pela ","

        return resp.split(",");
    }

    public static void main(String[] args)throws Exception {
        
        // Definição do tamanho do vetor e criação de uma variável de leitura de arquivo usando a classe RamdomAccessFile

        Jogador vetorJogador[] = new Jogador[4000];
        RandomAccessFile raf = new RandomAccessFile("/tmp/players.csv", "r");
        
        String str;
        raf.seek(60); // Posiciona o leitor na segunda linha do arquivo
        int i = 0;

        // Salva todos os dados do arquivo no vetor de jogadores

        while ((str = raf.readLine()) != null) {
            String itens[] = mudarString(str);
            vetorJogador[i] = new Jogador(Integer.parseInt(itens[0]), Integer.parseInt(itens[2]), Integer.parseInt(itens[3]), itens[1], itens[4], itens[5], itens[6], itens[7]);
            i++;
        }

        String flag = "";

        flag = MyIO.readLine();
        
        Lista lista = new Lista (); // Criando uma nova lista
        
        do {
            // Adicionando os elementos a lista criada

            int var = Integer.parseInt(flag);
            lista.insereFinal(vetorJogador[var]);
            flag = MyIO.readLine();

        } while (flag.equals("FIM") != true);

        Jogador ordenar[] = new Jogador[lista.tam];

        lista.CopiarVetor(ordenar);

        for(int k = 0; k < lista.tam -1; k++){
            ordenar[k].printTerminal();;
        }

        ordenacaoQuickSort(0, lista.tam -1, ordenar);
        
        lista.refazerLista(ordenar);
        lista.printarTela();
        
        raf.close();
        
    }

}
