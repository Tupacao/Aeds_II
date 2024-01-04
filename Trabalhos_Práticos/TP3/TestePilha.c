#include <stdlib.h>
#include <stdio.h>

typedef struct Pilha
{
    int element;
    struct Pilha *prox;
} Pilha;

Pilha *topo;

void criarTopo (){
    topo = (Pilha*) malloc(sizeof(Pilha));
    topo->element = 0;
    topo->prox = NULL;
}

Pilha *criarNova (int x){
    Pilha *aux = (Pilha*)malloc(sizeof(Pilha));
    aux->element = x;
    aux->prox = NULL;
    return aux;
}

void insere (int x){
    Pilha *nova = criarNova(x);

    if (topo->prox == NULL)
    {
        topo->prox = nova;
        return;
    }
    
    Pilha *aux = topo->prox;
    topo->prox = nova;
    nova->prox = aux;
}

void remover(){
    if (topo->prox ==  NULL)
    {
        printf("Nao eh possivel remover");
        return;
    }
    
    int aux = topo->prox->element;
    topo->prox = topo->prox->prox;

    printf("%d\n", aux);
}

void printarVetor (){
    for (Pilha *i = topo->prox; i != NULL; i = i->prox)
    {
        printf("%d ", i->element);
    }
    printf("\n");
}

int main()
{

    criarTopo();
    insere(10);
    insere(20);
    insere(40);
    printarVetor();
    insere(50);
    insere(30);
    printarVetor();
    remover();
    remover();
    printarVetor();

    return 0;
}
