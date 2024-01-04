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


// HashLista em C

typedef struct Celula {
    Jogador *element;
    struct Celula *prox;
}Celula;

Celula* vetor[21];

Celula* criarCelula (Jogador *x){
    Celula* nova = malloc(sizeof(Celula));
    nova->element = x;
    nova->prox = NULL;
    return nova;
}

int mod (int x){
    return x % 21;
}

void inicializarHash (){
    Jogador *aux = insereJogador(-1, -1, -1, -1, "A", "A", "A", "A"); 
    for(int i = 0; i < 21; i++){
        vetor[i] = criarCelula(aux);
    }
}

Celula* guardarLista (Jogador *x, Celula* cell){
    if(cell == NULL){
        cell = criarCelula(x);
        return cell;
    }

    Celula* j = cell;

    for (; j->prox != NULL; j = j->prox);
    j->prox = criarCelula(x);
    return cell;
}

void add (Jogador *x) {
    int pos = mod(x->altura);
    if(vetor[pos]->element->altura == -1){
        vetor[pos] = criarCelula(x);
    } else {
        vetor[pos]->prox = guardarLista(x, vetor[pos]->prox);
    }
}

int procurarLista (Celula* cell, Jogador* x){
    
    for (Celula* i = cell; i != NULL; i = i->prox)
    {
        if(strcmp(i->element->nome, x->nome) == 0){
            return 1;
        }
    }
    return 0;
}

int search(Jogador* x){
    int pos = mod(x->altura);
    if(strcmp(vetor[pos]->element->nome, x->nome) == 0){
        return 1;     
    } else {
        return procurarLista(vetor[pos], x);
    }
}

Jogador* procuraPorNome (Jogador** vetor, char str[200]){

    Jogador *aux = insereJogador(-1, -1, -1, -1, "A", "A", "A", "A"); 

    for (int i = 0; i < 4000; i++)
    {
        if(vetor[i] == NULL){
            return aux;
        } else if(strcmp(vetor[i]->nome, str) == 0){
            return vetor[i];
        }
    }

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

    // Cria uma hash para receber todos os jogadores
    
    inicializarHash();

    scanf(" %s", leitura);

    // Insere no hash os jogadores passados pelo id;

    while (strcmp(leitura, "FIM") != 0)
    {   
        int id = atoi(leitura);
        add(jogadores[id]);
        scanf(" %s", leitura);
    }

    // Procura de Jogadores

    scanf(" %[^\n]", leitura);
    int contador = 0;
    while (contador < 99 && strcmp(leitura, "FIM") != 0)
    {
        Jogador *aux = procuraPorNome(jogadores, leitura);
        if(aux->id == -1){
            printf("%s NAO\n", leitura);
        } else {
            printf("%s ", leitura);
            printf(search(aux) ? "SIM\n" : "NAO\n");
        }

        contador++;
        scanf(" %[^\n]", leitura);
    }

    fclose(ler);
    
    return 0;
}