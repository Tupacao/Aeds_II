import java.io.*;

class Jogador {

    // Declaração dos atributos da classe Jogador

    private int id, altura, peso;
    private String nome, universidade, anoNascimento, estadoNascimento, cidadeNascimento;

    // Construtor sem parâmetro

    public Jogador() {
        this.id = -1;
        this.altura = -1;
        this.peso = -1;
        this.nome = "nao informado";
        this.universidade = "nao informado";
        this.anoNascimento = "nao informado";
        this.estadoNascimento = "nao informado";
    }

    // Construtor com parâmetro

    public Jogador(int id, int altura, int peso, String nome, String universidade, String anoNascimento,
            String cidadeNascimento, String estadoNascimento) {
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
        } else {
            this.universidade = universidade;
        }
    }

    public void setAnoNascimento(String anoNascimento) {

        // Caso um dado não seja informado ele é substituido para "nao informado"

        if (anoNascimento.equals("-1")) {
            this.anoNascimento = "nao informado";
        } else {
            this.anoNascimento = anoNascimento;
        }
    }

    public void setEstadoNascimento(String estadoNascimento) {

        // Caso um dado não seja informado ele é substituido para "nao informado"

        if (estadoNascimento.equals("-1")) {
            this.estadoNascimento = "nao informado";
        } else {
            this.estadoNascimento = estadoNascimento;
        }
    }

    public void setCidadeNascimento(String cidadeNascimento) {

        // Caso um dado não seja informado ele é substituido para "nao informado"

        if (cidadeNascimento.equals("-1")) {
            this.cidadeNascimento = "nao informado";
        } else {
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

    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    // Função para mostrar na tela o jogador X

    public void printTerminal(int i) {
        MyIO.println("[" + i + "]" + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso + " ## "
                + this.anoNascimento + " ## " + this.universidade + " ## " + this.cidadeNascimento + " ## "
                + this.estadoNascimento + " ##");
    }

    // Função responsável por clonar o jogador X

    public Jogador clone() {

        Jogador resp = new Jogador(this.id, this.altura, this.peso, this.nome, this.universidade, this.anoNascimento,
                this.estadoNascimento, this.cidadeNascimento);

        return resp;
    }

}

class No {
    Jogador element;
    boolean cor;
    No dir, esq;

    public No(Jogador x) {
        this.element = x;
        this.dir = this.esq = null;
        this.cor = false;
    }

    public No(Jogador x, boolean cor) {
        this.element = x;
        this.dir = this.esq = null;
        this.cor = cor;
    }

    public No(Jogador x, boolean cor, No esq, No dir) {
        this.element = x;
        this.cor = cor;
        this.dir = dir;
        this.esq = esq;
    }

}

class Arvore {

    private No raiz;

    public Arvore() {
        raiz = null;
    }

    public void search(String elemento) {
        System.out.print(elemento + " raiz ");
        System.out.print(pesquisar(elemento, raiz) ? "SIM\n" : "NAO\n");
    }

    private boolean pesquisar(String elemento, No i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (elemento.compareTo(i.element.getNome()) == 0) {
            resp = true;
        } else if (elemento.compareTo(i.element.getNome()) < 0) {
            System.out.print("esq ");
            resp = pesquisar(elemento, i.esq);
        } else {
            System.out.print("dir ");
            resp = pesquisar(elemento, i.dir);
        }
        return resp;
    }

    public void add(Jogador elemento) throws Exception {
  
        if (raiz == null) {
            raiz = new No(elemento);
        } else if (raiz.esq == null && raiz.dir == null) {
            if (elemento.getNome().compareTo(raiz.element.getNome()) < 0) {
                raiz.esq = new No(elemento);
            } else {
                raiz.dir = new No(elemento);
            }           
        } else if (raiz.esq == null) {
            if (elemento.getNome().compareTo(raiz.element.getNome()) < 0) {
                raiz.esq = new No(elemento);
            } else if (elemento.getNome().compareTo(raiz.dir.element.getNome()) < 0) {
                raiz.esq = new No(raiz.element);
                raiz.element = elemento;
            } else {
                raiz.esq = new No(raiz.element);
                raiz.element = raiz.dir.element;
                raiz.dir.element = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;       
        } else if (raiz.dir == null) {
            if (elemento.getNome().compareTo(raiz.element.getNome()) > 0) {
                raiz.dir = new No(elemento);
            } else if (elemento.getNome().compareTo(raiz.esq.element.getNome()) > 0) {
                raiz.dir = new No(raiz.element);
                raiz.element = elemento;
            } else {
                raiz.dir = new No(raiz.element);
                raiz.element = raiz.esq.element;
                raiz.esq.element = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;           
        } else {
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void balancear(No bisavo, No avo, No pai, No i) {
       
        if (pai.cor == true) {
            
            if (pai.element.getNome().compareTo(avo.element.getNome()) > 0) { 
                if (i.element.getNome().compareTo(pai.element.getNome()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { 
                if (i.element.getNome().compareTo(pai.element.getNome()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.element.getNome().compareToIgnoreCase(bisavo.element.getNome()) < 0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } 
    }

    private void inserir(Jogador elemento, No bisavo, No avo, No pai, No i) throws Exception {
        if (i == null) {
            if (elemento.getNome().compareTo(pai.element.getNome()) < 0) {
                i = pai.esq = new No(elemento, true);
            } else {
                i = pai.dir = new No(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getNome().compareTo(i.element.getNome()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getNome().compareTo(i.element.getNome()) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private No rotacaoDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private No rotacaoEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}

public class TP04Q04 {

    // Função que trata a string colocando no lugar dos espaços vazios o número -1

    public static String[] mudarString(String str) {

        String resp = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',') {
                resp += str.charAt(i);
                if (i + 1 == str.length() || str.charAt(i + 1) == ',') {
                    resp += "-1";
                } else {
                    resp += str.charAt(++i);
                }
            } else {
                resp += str.charAt(i);
            }
        }

        // A função retorna vetores de caracteres separados pela ","

        return resp.split(",");
    }

    public static void main(String[] args) throws Exception {

        // Definição do tamanho do vetor e criação de uma variável de leitura de arquivo
        // usando a classe RamdomAccessFile

        Jogador vetorJogador[] = new Jogador[3922];
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

        String flag = "";

        flag = MyIO.readLine();

        Arvore arvore = new Arvore(); // Criando uma nova árvore

        do {
            // Adicionando os elementos a árvore criada

            int var = Integer.parseInt(flag);
            arvore.add(vetorJogador[var]);
            flag = MyIO.readLine();

        } while (flag.equals("FIM") != true);

        flag = MyIO.readLine();

        do {
            arvore.search(flag);
            flag = MyIO.readLine();
        } while (flag.equals("FIM") != true);

        raf.close();
    }

}
