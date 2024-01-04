#include <stdio.h>
#include <stdlib.h>

typedef struct Celula
{
    int element;
    struct Celula *prox;
} Celula;

Celula *cabeca;
int tam = 0;


void criarFila()
{
    cabeca = (Celula *)malloc(sizeof(Celula));
    cabeca->prox = NULL;
}

int mediaAltura () {
    // Calcular a media da altura dos jogadores
    Celula *i = cabeca;
    int soma = 0, contador = 0;

    while (i->prox != NULL) {
        soma += i->element;
        contador++;
        i = i->prox;
    }

    // printf("\n");
    return round(soma / (float)contador); // Calcula a média
    
}

Celula *criarCelula(int x)
{
    Celula *novo = (Celula *)malloc(sizeof(Celula));
    novo->prox = NULL;
    novo->element = x;
    return novo;
}

int remover() {

    Celula *aux = cabeca->prox;
    int valorRemovido = aux->element;
    cabeca->prox = aux->prox;
    free(aux);
    tam--;

    return valorRemovido;
}

void insere(int x) {
    if (tam < 5) {
        Celula *i = cabeca;
        while (i->prox != NULL) {
            i = i->prox;
        }
        i->prox = criarCelula(x);
        tam++;
    } else {
        remover(); 
        insere(x); 
    }
}


void printarFila (){
    Celula *i = cabeca->prox;
    for (; i != NULL; i = i->prox)
    {
        printf("%d ", i->element);
    }
    printf("\n");
}

int main()
{
    criarFila();
    insere(10);
    insere(20);
    insere(30);
    insere(40);
    insere(50);
    printarFila();
    insere(60);
    insere(70);
    printarFila();
    insere(80);
    insere(90);
    printarFila();
    insere(100);
    insere(110);
    printarFila();
    return 0;
}
