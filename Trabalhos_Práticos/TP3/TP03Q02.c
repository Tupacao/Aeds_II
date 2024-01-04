#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
    jogador->altura = altura;
    jogador->peso = peso;
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
    jogador->peso ,jogador->altura, 
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

int tam = 0;

// Funções referentes a lista

void inserirFIM (Jogador *lista, Jogador x){
    lista[tam] = x;
    tam++;
}

void inserirInicio (Jogador *lista, Jogador x){
    tam++;

    for (int i = tam; i > 0; i--)
    {
        lista[i] = lista[i-1];
    }

    lista[0] = x;
}

void inserir (Jogador *lista, Jogador x, int pos){
    tam++;

    for (int i = tam; i > pos; i--)
    {
        lista[i] = lista[i - 1];    
    }

    lista[pos] = x;
}

Jogador removeFIM (Jogador *lista){

    Jogador aux = lista[tam];
    tam--;

    return aux;
}

Jogador removeInicio (Jogador *lista){
    Jogador aux = lista[0];
    
    tam--;

    for (int i = 0; i < tam; i++)
    {
        lista[i] = lista[i+1];
    }

    return aux;
}

Jogador remover (Jogador *lista, int pos){
    Jogador aux = lista[pos];

    for (int i = pos; i < tam - 1; i++)
    {
        lista[i] = lista[i+1];
    }
    
    tam--;

    return aux;
}

void printarVetor (Jogador *lista){
    for (int i = 0; i < tam; i++)
    {
        printJogador(&lista[i], i);
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

    // Cria um novo vetor para receber os jogadores

    Jogador *lista = (Jogador*) malloc (sizeof(Jogador) * 1000);

    scanf(" %s", leitura);

    // Insere no vetor lista os jogadores que o usuario insere o ID;

    while (strcmp(leitura, "FIM") != 0)
    {   
        int id = atoi(leitura);
        inserirFIM(lista, *jogadores[id]);
        scanf(" %s", leitura);
    }

    // Realização das operações de inserção e remoção na lista

    int operacoes;

    scanf("%d", &operacoes);
    
    for (int j = 0; j < operacoes; j++)
    {
        scanf(" %s", leitura);
        int posicao, valor;

        if (strcmp(leitura, "II") == 0)
        {
            scanf("%d", &valor);
            inserirInicio(lista, *jogadores[valor]);
        }
        else if (strcmp(leitura, "IF") == 0)
        {
            scanf("%d", &valor);
            inserirFIM(lista, *jogadores[valor]);
        }
        else if (strcmp(leitura, "I*") == 0)
        {
            scanf("%d %d", &posicao, &valor);
            inserir(lista, *jogadores[valor], posicao);
        }
        else if (strcmp(leitura, "RF") == 0){
            Jogador aux = removeFIM(lista);
            printf("(R) %s\n", aux.nome);
        }
        else if (strcmp(leitura, "RI") == 0){
            Jogador aux = removeInicio(lista);
            printf("(R) %s\n", aux.nome);
        }
        else if (strcmp(leitura, "R*") == 0){
            scanf("%d", &posicao);
            Jogador aux = remover(lista, posicao);
            printf("(R) %s\n", aux.nome);
        }
        
    }
    
    // Printa a lista final

    printarVetor(lista);

    fclose(ler);
    
    return 0;
}