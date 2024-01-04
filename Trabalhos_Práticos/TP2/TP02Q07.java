import java.io.RandomAccessFile;

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
        MyIO.println("[" + this.id + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso + " ## " + this.anoNascimento + " ## " + this.universidade + " ## " + this.estadoNascimento + " ## " + this.cidadeNascimento + "]"); 
    }

    // Função responsável por clonar o jogador X

    public Jogador clone () {

        Jogador resp = new Jogador(this.id, this.altura, this.peso, this.nome, this.universidade, this.anoNascimento, this.estadoNascimento, this.cidadeNascimento);

        return resp;
    }

}

public class TP02Q07 {
    
    // Função que trata a string colocando no lugar dos espaços vazios o número -1

    public static String[] mudarString (String str) {

        String resp = "";

        for (int i = 0; i < str.length(); i++) {
            // if (str.charAt(i) == '*') {
            //     i++;
            // }
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
    
    public static Jogador[] mudarTamVetor (Jogador jogador[]){
        
        int contador = 0;

        for (int i = 0; i < jogador.length; i++) {
            if(jogador[i] == null){
                contador++;
            }
        }

        Jogador resp[] = new Jogador[jogador.length - contador];

        for (int i = 0; i < resp.length; i++) {
            resp[i] = jogador[i].clone();
        }

        return resp;
    }

    public static void ordenarPorNascimento (Jogador jogador[]){

        for (int i = 1; i < jogador.length; i++) {
            Jogador temp = jogador[i];
            int j = i - 1;
            while (j >= 0 && (Integer.parseInt(jogador[j].getAnoNascimento()) > Integer.parseInt(temp.getAnoNascimento()) || (Integer.parseInt(jogador[j].getAnoNascimento()) == Integer.parseInt(temp.getAnoNascimento()) && jogador[j].getNome().compareTo(temp.getNome()) > 0))) {
                jogador[j + 1] = jogador[j];
                j--;
            }
            jogador[j + 1] = temp;
        }

    } 

    public static void main(String[] args) throws Exception {

        // Definição do tamanho do vetor e criação de uma variável de leitura de arquivo
        // usando a classe RamdomAccessFile

        Jogador vetorJogador[] = new Jogador[4000];
        RandomAccessFile raf = new RandomAccessFile("/tmp/players.csv", "r");

        String str;
        raf.seek(60); // Posiciona o leitor na segunda linha do arquivo
        int i = 0;

        // Salva todos os dados do arquivo no vetor de jogadores

        while ((str = raf.readLine()) != null) {
            String itens[] = mudarString(str);
            vetorJogador[i] = new Jogador(Integer.parseInt(itens[0]), Integer.parseInt(itens[2]),
                    Integer.parseInt(itens[3]), itens[1], itens[4], itens[5], itens[6], itens[7]);
            i++;
        }

        // Pesquisa no vetor jogador para criar um novo para a pesquisa sequencial

        String flag;
        Jogador resp[] = new Jogador[600];
        i = 0;
        flag = MyIO.readLine();

        do {
            int posicao = Integer.parseInt(flag);
            resp[i++] = vetorJogador[posicao];
            flag = MyIO.readLine();
        } while (flag.equals("FIM") != true);

        resp = mudarTamVetor(resp); // Muda o tamanho do vetor para a quantidade de itens inseridas

        ordenarPorNascimento(resp); // Função resposável pela ordenação

        // Mostra na tela todos os valores do vetor ordenados

        for (int j = 0; j < resp.length; j++) {
            resp[j].printTerminal();
        }

        raf.close();

    }

}