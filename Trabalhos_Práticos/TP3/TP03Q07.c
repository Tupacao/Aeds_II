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

typedef struct Celula
{
    Jogador *element;
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
    Celula *i = cabeca->prox;
    int soma = 0, contador = 0;

    while (i != NULL) {
        soma += i->element->altura;
        contador++;
        i = i->prox;
    }

    // printf("\n");
    return round(soma / (float)contador); // Calcula a média
    
}

Celula *criarCelula(Jogador* x)
{
    Celula *novo = (Celula *)malloc(sizeof(Celula));
    novo->prox = NULL;
    novo->element = x;
    return novo;
}

Jogador* remover() {

    Celula *aux = cabeca->prox;
    Jogador* valorRemovido = aux->element;
    cabeca->prox = aux->prox;
    free(aux);
    tam--;

    return valorRemovido;
}

void insere(Jogador* x) {
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
    int j = 0;
    for (; i != NULL; i = i->prox)
    {
        printJogador(i->element, j++);
    }
    printf("\n");
}


int main() {

    // Leitura do arquivo e posicionamento na segunda linha

    FILE* ler = fopen("/tmp/players.csv", "r");
    fseek(ler, 60, SEEK_SET);
    char * leitura = (char *) malloc(sizeof(char) * 600);
    Jogador ** jogadores = (Jogador **) malloc(sizeof(Jogador *) * 4000);
    int i = 0;

    // Salva todos os jogadores no vetor 'jogadores'

    while (fscanf(ler, " %[^\n]", leitura) != EOF)
    {
        jogadores[i++] = criaJogador(leitura);
    }

    // Cria um novo vetor para receber os jogadores

    criarFila();

    scanf(" %s", leitura);

    // Insere no vetor lista os jogadores que o usuario insere o ID;

    while (strcmp(leitura, "FIM") != 0)
    {   
        int id = atoi(leitura);
        insere(jogadores[id]);
        int resp = mediaAltura();
        printf("%d\n", resp);
        scanf(" %s", leitura);
    }

    // Realização das operações de inserção e remoção na lista

    int operacoes;

    scanf("%d", &operacoes);

    for (int j = 0; j < operacoes; j++)
    {
        scanf(" %s", leitura);
        int valor;

        if (strcmp(leitura, "I") == 0)
        {
            scanf("%d", &valor);
            insere(jogadores[valor]);
            int resp = mediaAltura();
            printf("%d\n", resp);
        }
        else if (strcmp(leitura, "R") == 0)
        {
            Jogador *aux = remover();
            printf("(R) %s\n", aux->nome);
        }
    }
    
    // Printa a lista final

    printarFila();

    fclose(ler);
    
    return 0;
}