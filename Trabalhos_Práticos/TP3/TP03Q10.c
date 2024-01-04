#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

typedef struct Jogador
{
    int id;
    int altura;
    int peso;
    int anoNascimento;
    char *nome;
    char *universidade;
    char *cidadeNascimento;
    char *estadoNascimento;
}Jogador;

// Funções referentes à "classe" Jogador

Jogador * insereJogador (int id, int altura, int peso, int anoNascimento, char nome[], char universidade[], char cidadeNascimento[], char estadoNascimento[]){
    Jogador * jogador = (Jogador *) malloc(sizeof(Jogador));

    jogador->id = id;
    jogador->altura = peso;
    jogador->peso = altura;
    jogador->anoNascimento = anoNascimento;
    jogador->nome = nome;
    jogador->universidade = universidade;
    jogador->cidadeNascimento = cidadeNascimento;
    jogador->estadoNascimento = estadoNascimento;

    return jogador;
}

void printJogador (Jogador * jogador, int i){

    char * uni = jogador->universidade;

    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", 
    i,  jogador->nome, 
    jogador->altura ,jogador->peso, 
    jogador->anoNascimento, uni, 
    jogador->cidadeNascimento, jogador->estadoNascimento);
}

Jogador clonarJogador (Jogador jogador){
    Jogador resp = jogador;
    return resp;
}

void mudarString (char str[]){

    for (int i = 0; i < strlen(str); i++)
    {
        if(str[i] == ','){
            
            if(i+1 == strlen(str) || str[i+1] == ','){
                str[i] = ']';
            }
        }

    }
    
}

Jogador * criaJogador (char * str){
    
    // 0,Curly Armstrong,180,77,Indiana University,1918,,

    char aux[400];
    int i = 0, j = 0;

    while (str[i] != ',')
    {
        aux[j++] = str[i++];
    }

    i++;
    aux[j] = '\0';
    int id = atoi(aux);

    j = 0;

    while (str[i] != ',')
    {
        aux[j++] = str[i++];
    }

    i++;
    aux[j] = '\0';
    char * nome = (char *) malloc(sizeof(char) * 200);
    strcpy(nome, aux);
    j = 0;

    while (str[i] != ',')
    {
        aux[j++] = str[i++];
    }

    i++;
    aux[j] = '\0';
    int peso = atoi(aux);
    j = 0;

    while (str[i] != ',')
    {
        aux[j++] = str[i++];
    }

    i++;
    aux[j] = '\0';
    int altura = atoi(aux);
    j = 0;

    char * universidade = (char *) malloc(sizeof(char) * 200);

    if(str[i] == ',' && str[i - 1] == ','){
        strcpy(universidade, "nao informado");
        i++;
    }
    else{

        while (str[i] != ',')
        {
            aux[j++] = str[i++];
        }

        i++;
        aux[j] = '\0';
        strcpy(universidade, aux);
        j = 0;

    }

    
    while (str[i] != ',')
    {
        aux[j++] = str[i++];
    }

    i++;
    aux[j] = '\0';
    int dataNascimento = atoi(aux);
    j = 0;


    char * cidade = (char *) malloc(sizeof(char) * 200);

    if(str[i] == ',' && str[i - 1] == ','){
        strcpy(cidade,"nao informado");
        i++;
    }
    else{
        while (str[i] != ',')
        {
            aux[j++] = str[i++];
        }

        i++;
        aux[j] = '\0';
        strcpy(cidade, aux);
        j = 0;

    }

    char * estado = (char *) malloc(sizeof(char) * 200);
    
    if(i == strlen(str)){
        strcpy(estado,"nao informado");
    }
    else{

        while (str[i] != '\0')
        {
            aux[j++] = str[i++];
        }

        i++;
        aux[j] = '\0';
        strcpy(estado, aux);
        j = 0;
    }
    
    return insereJogador(id,altura,peso,dataNascimento,nome,universidade,cidade,estado);

}

// Pilha em C

typedef struct Pilha
{
    Jogador * element;
    struct Pilha *prox;
} Pilha;

Pilha *topo;
int tam = 0;

void criarTopo (){
    topo = (Pilha*) malloc(sizeof(Pilha));
    topo->element = 0;
    topo->prox = NULL;
}

Pilha *criarNova (Jogador x){
    Pilha *aux = (Pilha*)malloc(sizeof(Pilha));
    aux->element = (Jogador*) malloc (sizeof(Jogador));
    *(aux->element) = x;
    aux->prox = NULL;
    return aux;
}

void insere (Jogador x){
    Pilha *nova = criarNova(x);

    if (topo->prox == NULL)
    {
        topo->prox = nova;
        tam++;
        return;
    }
    
    Pilha *aux = topo->prox;
    topo->prox = nova;
    nova->prox = aux;
    tam++;
}

Jogador *remover() {
    if (topo->prox ==  NULL) {
        printf("Nao eh possivel remover");
        return NULL;
    }
    
    Pilha *temp = topo->prox;
    Jogador *aux = (Jogador*)malloc(sizeof(Jogador));
    *(aux) = *(temp->element);
    topo->prox = temp->prox;
    tam--;
    return aux;
}

void printarVetor (Pilha *i, int j){
    
    if(i != NULL){
        printarVetor(i->prox, --j);
        printJogador(i->element, j);
    }
}


int main() {

    // Leitura do arquivo e posicionamento na segunda linha

    FILE* ler = fopen("./tmp/players.csv", "r");
    fseek(ler, 60, SEEK_SET);
    char * leitura = (char *) malloc(sizeof(char) * 600);
    Jogador ** jogadores = (Jogador **) malloc(sizeof(Jogador *) * 4000);
    int i = 0;

    // Salva todos os jogadores no vetor 'jogadores'

    while (fscanf(ler, " %[^\n]", leitura) != EOF)
    {
        jogadores[i++] = criaJogador(leitura);
    }

    // Cria uma pilha para receber os jogadores
    
    criarTopo();

    scanf(" %s", leitura);

    // Insere na pilha os jogadores selecionados;

    while (strcmp(leitura, "FIM") != 0)
    {   
        int id = atoi(leitura);
        insere(*jogadores[id]);
        scanf(" %s", leitura);
    }

    // Realização das operações de inserção e remoção na lista

    int operacoes;

    scanf("%d", &operacoes);

    for (int j = 0; j < operacoes; j++)
    {
        scanf(" %s", leitura);
        int posicao, valor;

        if (strcmp(leitura, "I") == 0)
        {
            scanf("%d", &valor);
            insere(*jogadores[valor]);
        }
        else if (strcmp(leitura, "R") == 0)
        {
            Jogador *aux = remover();
            printf("(R) %s\n", aux->nome);
        }
    }
    
    // Printa a lista final

    printarVetor(topo->prox, tam);

    fclose(ler);
    
    return 0;
}