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
} Jogador;

// Funções referentes à "classe" Jogador

Jogador *insereJogador(int id, int altura, int peso, int anoNascimento, char nome[], char universidade[], char cidadeNascimento[], char estadoNascimento[])
{
    Jogador *jogador = (Jogador *)malloc(sizeof(Jogador));

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

void printJogador(Jogador *jogador)
{

    char *uni = jogador->universidade;

    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogador->id, jogador->nome,
           jogador->altura, jogador->peso,
           jogador->anoNascimento, uni,
           jogador->cidadeNascimento, jogador->estadoNascimento);
}

Jogador clonarJogador(Jogador jogador)
{
    Jogador resp = jogador;
    return resp;
}

void mudarString(char str[])
{

    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] == ',')
        {

            if (i + 1 == strlen(str) || str[i + 1] == ',')
            {
                str[i] = ']';
            }
        }
    }
}

Jogador *criaJogador(char *str)
{

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
    char *nome = (char *)malloc(sizeof(char) * 200);
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

    char *universidade = (char *)malloc(sizeof(char) * 200);

    if (str[i] == ',' && str[i - 1] == ',')
    {
        strcpy(universidade, "nao informado");
        i++;
    }
    else
    {

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

    char *cidade = (char *)malloc(sizeof(char) * 200);

    if (str[i] == ',' && str[i - 1] == ',')
    {
        strcpy(cidade, "nao informado");
        i++;
    }
    else
    {
        while (str[i] != ',')
        {
            aux[j++] = str[i++];
        }

        i++;
        aux[j] = '\0';
        strcpy(cidade, aux);
        j = 0;
    }

    char *estado = (char *)malloc(sizeof(char) * 200);

    if (i == strlen(str))
    {
        strcpy(estado, "nao informado");
    }
    else
    {

        while (str[i] != '\0')
        {
            aux[j++] = str[i++];
        }

        i++;
        aux[j] = '\0';
        strcpy(estado, aux);
        j = 0;
    }

    return insereJogador(id, altura, peso, dataNascimento, nome, universidade, cidade, estado);
}

typedef struct Celula
{
    Jogador *element;
    struct Celula *prox;
    struct Celula *ant;
} Celula;

Celula *cabeca;
int tam = 0;

void criarFila()
{
    cabeca = (Celula *)malloc(sizeof(Celula));
    cabeca->prox = NULL;
}

Celula *criarCelula(Jogador *x)
{
    Celula *novo = (Celula *)malloc(sizeof(Celula));
    novo->prox = NULL;
    novo->ant = NULL;
    novo->element = x;
    return novo;
}

void insere(Jogador *x)
{
    if (cabeca->prox == NULL)
    {
        cabeca->prox = criarCelula(x);
        cabeca->prox->ant = cabeca;
        tam++;
        return;
    }

    Celula *i = cabeca;
    while (i->prox != NULL)
    {
        i = i->prox;
    }
    i->prox = criarCelula(x);
    i->prox->ant = i;
    tam++;
}

void printarFila()
{
    Celula *i = cabeca->prox;
    for (; i != NULL; i = i->prox)
    {
        printJogador(i->element);
    }
    printf("\n");
}

void trocar(Jogador **jogador, int i, int j) {
    Jogador *temp = jogador[i];
    jogador[i] = jogador[j];
    jogador[j] = temp;
}

void quickSort(int esq, int dir, Jogador **jogadores) {
    int i = esq, j = dir;
    Jogador *pivo = jogadores[(esq + dir) / 2];

    while (i <= j) {
        while (strcmp(jogadores[i]->estadoNascimento, pivo->estadoNascimento) < 0 || strcmp(jogadores[i]->estadoNascimento, pivo->estadoNascimento) == 0 && strcmp(jogadores[i]->nome, pivo->nome) < 0){
            i++;
        }
        while (strcmp(jogadores[j]->estadoNascimento, pivo->estadoNascimento) > 0 || strcmp(jogadores[j]->estadoNascimento, pivo->estadoNascimento) == 0 && strcmp(jogadores[j]->nome, pivo->nome) > 0) {
            j--;
        }
        if (i <= j) {
            trocar(jogadores, i, j);
            i++;
            j--;
        }
    }
    if (esq < j) {
        quickSort(esq, j, jogadores);
    }
    if (i < dir) {
        quickSort(i, dir, jogadores);
    }
}

void preencherVetor(Jogador **vetor){
    Celula *j = cabeca->prox;
    int i = 0;

    while (j != NULL && i < tam) {
        vetor[i++] = j->element;
        j = j->prox;
    }
}

void reFazer(Jogador **vetor) {
    Celula *i = cabeca->prox;
    for (int j = 0; j < tam-1; j++) {
        i->element = vetor[j];
        i->prox = criarCelula(NULL);
        i = i->prox;
    }
    // Atribuir o último elemento diretamente ao campo 'prox' da célula existente
    i->element = vetor[tam-1];
    i->prox = NULL;
}


int main()
{

    // Leitura do arquivo e posicionamento na segunda linha

    FILE *ler = fopen("/tmp/players.csv", "r");
    fseek(ler, 60, SEEK_SET);
    char *leitura = (char *)malloc(sizeof(char) * 600);
    Jogador **jogadores = (Jogador **)malloc(sizeof(Jogador *) * 4000);
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
        scanf(" %s", leitura);
    }

    // Cria um novo vetor com os dados do flexivel

    Jogador ** new = (Jogador **) malloc(sizeof(Jogador *) * tam);
    preencherVetor(new);

    // Chama o QuickSort
    quickSort(0, tam-1, new);

    // Refaz a lista e depois printa ela
    reFazer(new);
    printarFila();

    fclose(ler);

    return 0;
}