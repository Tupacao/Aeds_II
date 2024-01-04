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

    public void printTerminal(int i){
        MyIO.println("[" + i + "]" + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso + " ## " + this.anoNascimento + " ## " + this.universidade + " ## " + this.cidadeNascimento + " ## " + this.estadoNascimento + " ##"); 
    }

    // Função responsável por clonar o jogador X

    public Jogador clone () {

        Jogador resp = new Jogador(this.id, this.altura, this.peso, this.nome, this.universidade, this.anoNascimento, this.estadoNascimento, this.cidadeNascimento);

        return resp;
    }

}

class No {
    Jogador element;
    No dir, esq;

    public No (Jogador x){
        this.element = x;
        this.dir = this.esq = null;
    }
}

class Arvore {
    private No raiz;

    public Arvore () {
        this.raiz = null;
    }

    public void add (Jogador x){ 
        this.raiz = insereArvore(this.raiz, x);
    }

    public boolean search (String str){
        return buscarArvore(str, this.raiz);
    }

    public void sort(){
        sort(this.raiz);
    }

    private void sort(No node){
        if(node != null){
            sort(node.esq);
            MyIO.println(node.element.getNome() + " ");
            sort(node.dir);
        }
    }

    private boolean buscarArvore (String str, No node){
        if(node == null){
            return false;
        }
        else if(node.element.getNome().equals(str)){
            return true;
        }else if(node.element.getNome().compareTo(str) > 0){
            return buscarArvore(str, node.dir);
        } else if(node.element.getNome().compareTo(str) < 0){
            return buscarArvore(str, node.esq);
        }
        return false;
    }

    private No insereArvore (No node, Jogador x){
        if(node == null){
            node = new No(x);
        } else if(x.getNome().compareTo(node.element.getNome()) > 0){
            node.dir = insereArvore(node.dir, x);
        }else {
            node.esq = insereArvore(node.esq, x);
        }
        return node;
    }

}

public class TP04Q05 {
    
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

    public static Jogador acharJogadorporNome(Jogador vet[], String str){
        for (Jogador jogador : vet) {
            if (jogador != null && str.equals(jogador.getNome())) {
                return jogador;
            }
        }
        return null;
    }

    public static void sort (Jogador vet[], int tam){
        for(int i = 1; i < tam; i++){
            Jogador temp = vet[i];
            int j = i - 1;
            while (j >= 0 && temp.getNome().compareTo(vet[j].getNome()) < 0) {
                vet[j+1] = vet[j];
                j--;
            }
            vet[j+1] = temp;
        }

        printSort(vet, tam);
    }

    public static void printSort(Jogador vet[], int tam){
        for(int i= 0; i < tam; i++){
            MyIO.println(vet[i].getNome());
        }
    }

    public static void main(String[] args)throws Exception {
        
        // Definição do tamanho do vetor e criação de uma variável de leitura de arquivo usando a classe RamdomAccessFile

        Jogador vetorJogador[] = new Jogador[3922];
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
        
        Arvore arvore = new Arvore(); // Criando uma nova árvore
        
        do {
            // Adicionando os elementos a árvore criada

            int var = Integer.parseInt(flag);
            arvore.add(vetorJogador[var]);
            flag = MyIO.readLine();

        } while (flag.equals("FIM") != true);

        arvore.sort();
        
        raf.close();
    }

}
