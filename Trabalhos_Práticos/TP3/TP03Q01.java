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

class Lista {

    int tam = -1;
    Jogador vetor[];

    public int getTAM () {
        return this.tam;
    }

    public Lista (){
        this.vetor = new Jogador[1100];
    }
    
    public void inserirFIM (Jogador x) {
        tam++;
        this.vetor[tam] = x;
    }

    public void inserirInicio (Jogador x) {

        tam++;

        for(int i = 0; i < tam; i++){
            this.vetor[tam - i] = this.vetor[tam -i -1];
        }

        this.vetor[0] = x;

    }

    public void inserir (int pos, Jogador x){
        
        tam++;

        for(int i = pos, j = 0; i < tam; i++, j++){
            this.vetor[tam - j] = this.vetor[tam -1 - j];
        }

        this.vetor[pos] = x;
        
    }

    public Jogador removerFIM () {
        
        Jogador aux;

        aux = this.vetor[tam]; 

        this.vetor[tam] = null;

        tam--;

        return aux;
    }

    public Jogador removerInicio () {
        Jogador aux;

        aux = this.vetor[0];

        for(int i = 0; i < tam; i++){
            vetor[i] = vetor[i+1];
        }

        tam--;

        return aux;
    }

    public Jogador remover (int pos) {
        
        Jogador aux = this.vetor[pos];

        for(int i = pos; i <= tam; i++){
            this.vetor[i] = this.vetor[i+1];
        }

        tam--;

        return aux;

    }

    public void printarLista () {
        
        for(int i = 0; i <= tam; i++){
            this.vetor[i].printTerminal(i);
        }
        
    }

}

public class TP03Q01 {
    
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

        raf.close();
        String flag = "";

        flag = MyIO.readLine();
        
        Lista lista = new Lista (); // Criando uma nova lista
        
        do {
            // Adicionando os elementos a lista criada

            int var = Integer.parseInt(flag);
            lista.inserirFIM(vetorJogador[var]);
            flag = MyIO.readLine();

        } while (flag.equals("FIM") != true);

        int operacoes = MyIO.readInt();

        for (int j = 0; j < operacoes; j++) {
            String comando = MyIO.readString();
            int valor, posicao;
            Jogador aux;

            switch (comando) {
                case "II":
                    valor = MyIO.readInt();
                    lista.inserirInicio(vetorJogador[valor]);
                break;
                case "IF":
                    valor = MyIO.readInt();
                    lista.inserirFIM(vetorJogador[valor]);
                break;
                case "I*":
                    posicao = MyIO.readInt();
                    valor = MyIO.readInt();
                    lista.inserir(posicao, vetorJogador[valor]);
                break;
                case "RI":
                    aux = lista.removerInicio();
                    MyIO.println("(R) " + aux.getNome());
                break;
                case "RF":
                    aux = lista.removerFIM();
                    MyIO.println("(R) " + aux.getNome());
                break;
                case "R*":
                    posicao = MyIO.readInt();
                    aux = lista.remover(posicao);
                    MyIO.println("(R) " + aux.getNome());
                break;

            }
        }

        lista.printarLista();

    }

}
