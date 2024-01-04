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

void printJogador (Jogador * jogador){

    char * uni = jogador->universidade;

    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", 
    jogador->id,  jogador->nome, 
    jogador->peso,  jogador->altura, 
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

void ordenacaoShellSort(Jogador **jogador, int tam) {
    int h, i, j;
    Jogador *temp;

    for (h = 1; h < tam / 3; h = (3 * h) + 1);

    while (h >= 1) {
        for (i = h; i < tam; i++) {
            temp = jogador[i];
            j = i;
            while (j >= h && (jogador[j - h]->peso > temp->peso || j>=0 && (jogador[j - h]->peso == temp->peso && strcmp(jogador[j - h]->nome, temp->nome) > 0))) {
                jogador[j] = jogador[j - h];
                j -= h;
            }
            jogador[j] = temp;
        }
        h /= 3;
    }
}


int main() {
    
    FILE* ler = fopen("/tmp/players.csv", "r");
    fseek(ler, 60, SEEK_SET);
    char * leitura = (char *) malloc(sizeof(char) * 600);
    Jogador ** jogadores = (Jogador **) malloc(sizeof(Jogador *) * 4000);
    int i = 0;

    while (fscanf(ler, " %[^\n]", leitura) != EOF)
    {
        jogadores[i++] = criaJogador(leitura);
    }

    scanf(" %s", leitura);
    Jogador ** new = (Jogador **) malloc(sizeof(Jogador *) * 800), ** tmp;
    i = 0;

    while (strcmp(leitura, "FIM") != 0)
    {
        int id = atoi(leitura);
        new[i++] = jogadores[id];
        scanf(" %s", leitura);
    }

    tmp = (Jogador **) realloc(new, sizeof(Jogador **) * i);

    if (tmp == NULL) {
        return 0;
    }
    else {
        new = tmp;
    }

    ordenacaoShellSort(new, i-1);
    
    for (int j = 0; j < i; j++)
    {
        printJogador(new[j]);
    }
    
    fclose(ler);
    
    return 0;
}