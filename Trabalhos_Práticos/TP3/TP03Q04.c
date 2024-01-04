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

int tam = 6, primeiro = 0, ultimo = 0;

// Funções referentes a lista circular

int mediaAltura (Jogador *vetor) {
    // Calcular a media da altura dos jogadores
    int i = primeiro, soma = 0, contador = 0;

    while (i != ultimo) {
        soma += vetor[i].altura;
        contador++;
        i = (i + 1) % tam;
    }

    // printf("\n");
    return round(soma / (float)contador); // Calcula a média
    
}

void removerCheio (Jogador *vetor, Jogador x) {
    // Remove quando o vetor já estiver cheio
    primeiro = (primeiro + 1) % tam;
    inserir(vetor, x);
}

void inserir (Jogador *vetor, Jogador x) {
    // Insere o jogador no vetor

    if((ultimo + 1) % tam == primeiro){
        // Caso o vetor esteja cheio um espaço é liberado e outro é inserido
        removerCheio(vetor, x);
        return;
    }
    
    vetor[ultimo] = x;
    ultimo = (ultimo + 1) % tam;
}

Jogador retirar (Jogador *vetor){
    // Remover do vetor um jogador

    Jogador resp = vetor[primeiro];

    primeiro = (primeiro + 1) % tam;

    return resp;
}

void printVetor (Jogador *vetor){
    // Printa o vetor inteiro

    int i = primeiro, j = 0;
    while (i != ultimo)
    {
        printJogador(&vetor[i], j++);
        i = (i+1)% tam;
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

    Jogador *lista = (Jogador*) malloc (sizeof(Jogador) * 1000);

    scanf(" %s", leitura);

    // Insere no vetor lista os jogadores que o usuario insere o ID;

    while (strcmp(leitura, "FIM") != 0)
    {   
        int id = atoi(leitura);
        inserir(lista, *jogadores[id]);
        int resp = mediaAltura(lista);
        printf("%d\n", resp);
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
            inserir(lista, *jogadores[valor]);
            int resp = mediaAltura(lista);
            printf("%d\n", resp);
        }
        else if (strcmp(leitura, "R") == 0)
        {
            Jogador aux = retirar(lista);
            printf("(R) %s\n", aux.nome);
        }
    }
    
    // Printa a lista final

    printVetor(lista);

    fclose(ler);
    
    return 0;
}