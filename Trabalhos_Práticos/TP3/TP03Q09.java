class Celula {

    public int element;
    public Celula esq, dir, cim, bai;

    public Celula() {
        this.element = -1;
        this.esq = null;
        this.dir = null;
        this.cim = null;
        this.bai = null;
    }

    public Celula(int x) {
        this.element = x;
        this.esq = null;
        this.dir = null;
        this.cim = null;
        this.bai = null;
    }

}

class Matriz {

    public int linha, coluna;
    public Celula controle = new Celula();

    public Matriz() {
        this.linha = 2;
        this.coluna = 2;
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        criarMatriz();
    }

    private void criarMatriz() {

        Celula ln1 = controle;

        for (int i = 0; i < this.linha; i++, ln1 = ln1.bai) {
            Celula cn1 = ln1;
            for (int j = 0; i >= 1 ? j < this.coluna - 1 : j < this.coluna; j++, cn1 = cn1.dir) {
                if (i == 0) {
                    cn1.dir = new Celula(1);
                    cn1.dir.esq = cn1;
                } else {
                    cn1.dir = new Celula(1);
                    cn1.dir.esq = cn1;
                    cn1.dir.cim = cn1.cim.dir;
                    cn1.cim.dir.bai = cn1.dir;
                }
            }
            if (i == 0) {
                ln1 = ln1.dir;
                Celula aux = ln1;
                for (int k = 0; k < this.linha - 1; k++, aux = aux.bai) {
                    aux.bai = new Celula(1);
                    aux.bai.cim = aux;
                }
            }
        }

        insereValores();
    }

    public void printarMatriz() {
        Celula aux = controle.dir;

        for (int i = 0; i < this.linha; i++, aux = aux.bai) {
            Celula col = aux;
            for (int j = 0; j < this.coluna; j++, col = col.dir) {
                MyIO.print(col.element + " ");
            }
            MyIO.print("\n");
        }

        MyIO.print("\n");
    }

    public void somaMatriz(Matriz segunda) {

        Celula aux = this.controle.dir;
        Celula aux2 = segunda.controle.dir;

        for (int i = 0; i < this.linha; i++, aux = aux.bai, aux2 = aux2.bai) {
            Celula col = aux;
            Celula col2 = aux2;
            for (int j = 0; j < this.coluna; j++, col = col.dir, col2 = col2.dir) {
                MyIO.print(col.element + col2.element + " ");
            }
            MyIO.print("\n");
        }

    }

    public void diagonalPrincipal() {
        Celula aux = this.controle.dir;

        for (int i = 0; i < this.coluna; i++, aux = aux.bai.dir) {
            MyIO.print(aux.element + " ");
            if(i + 1 == this.linha){
                break; 
            }
        }
        MyIO.print("\n");
    }

    public void diagonalSecundaria() {
        Celula aux = controle;
        for (; aux.dir != null; aux = aux.dir);

        for (int i = 0; i < this.coluna; i++, aux = aux.bai.esq) {
            MyIO.print(aux.element + " ");
            if (i + 1 == this.linha) {
                break;
            }
        }
        MyIO.print("\n");
    }

    private void insereValores (){
        Celula aux = controle.dir;

        for (int i = 0; i < this.linha; i++, aux = aux.bai) {
            Celula col = aux;
            for (int j = 0; j < this.coluna; j++, col = col.dir) {
                col.element = MyIO.readInt();
            }
        }
    }

    public void multiplicarMatriz (Matriz segunda){
        for(int i = 0; i < this.linha; i++){
            for(int j = 0; j < segunda.coluna; j++){
                int soma = 0;
                for(int k = 0; k < this.coluna; k++){
                    soma += retornaElemento(i,k) * segunda.retornaElemento(k,j);
                }
                MyIO.print(soma + " ");
            }
            MyIO.print("\n");
        }
    }

    private int retornaElemento(int ln, int cn){
        int elemento;
        Celula aux = controle.dir;

        for(int i = 0; i < ln; i++, aux = aux.bai);
        for(int i = 0; i < cn; i++, aux = aux.dir);

        elemento = aux.element;

        return elemento;
    }
}

public class TP03Q09 {

    public static void main(String[] args) {
        int operacoes = MyIO.readInt();
        int linha, coluna;
        for(int i = 0; i < operacoes; i++){
            linha = MyIO.readInt();
            coluna = MyIO.readInt();
            Matriz primeira = new Matriz(linha, coluna);
            primeira.diagonalPrincipal();
            primeira.diagonalSecundaria();

            linha = MyIO.readInt();
            coluna = MyIO.readInt();
            Matriz segunda = new Matriz(linha,coluna);
            primeira.somaMatriz(segunda);
            primeira.multiplicarMatriz(segunda);
            
        }
    }
}
