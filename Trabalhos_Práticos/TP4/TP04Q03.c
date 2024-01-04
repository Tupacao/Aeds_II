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

void printJogador(Jogador *jogador, int i)
{

    char *uni = jogador->universidade;

    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
           i, jogador->nome,
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

// Arvore AVL em C

typedef struct No
{
    Jogador *element;
    struct No *dir, *esq;
    int nivel;
} No;

No *raiz;

No *criarNo1(Jogador *x)
{
    No *aux = malloc(sizeof(No));
    aux->element = x;
    aux->dir = aux->esq = NULL;
    aux->nivel = 1;

    return aux;
}

No *criarNo2(Jogador *x, No esq, No dir, int val)
{
    No *aux = malloc(sizeof(No));
    aux->element = x;
    aux->dir = &dir;
    aux->esq = &esq;
    aux->nivel = val;

    return aux;
}

int getNivel(No *node)
{
    return (node == NULL) ? 0 : node->nivel;
}

void setNivel(No *aux)
{
    aux->nivel = 1 + (getNivel(aux->dir) > getNivel(aux->esq)) ? getNivel(aux->dir) : getNivel(aux->esq);
}

int pesquisar(No *node, char x[])
{
    if (node == NULL)
    {
        return 0;
    }
    else if (strcmp(node->element->nome, x) == 0)
    {
        return 1;
    }
    else if (strcmp(x, node->element->nome) < 0)
    {
        printf("esq ");
        return pesquisar(node->esq, x);
    }
    else
    {
        printf("dir ");
        return pesquisar(node->dir, x);
    }
}

void search(char x[])
{
    printf("%s raiz ", x);
    printf("%s\n", pesquisar(raiz, x) ? "SIM" : "NAO");
}

No *rotacionarDir(No *no)
{
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;
    setNivel(no);
    setNivel(noEsq);

    return noEsq;
}

No *rotacionarEsq(No *no)
{
    No *noDir = no->dir;
    No *noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    setNivel(no);
    setNivel(noDir);
    return noDir;
}

No *balancear(No *no)
{
    if (no != NULL)
    {
        int fator = getNivel(no->dir) - getNivel(no->esq);
        if (fator <= 1)
        {
            setNivel(no);
        }
        else if (fator == 2)
        {
            int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);
            if (fatorFilhoDir == -1)
            {
                no->dir = rotacionarDir(no->dir);
            }
            no = rotacionarEsq(no);
        }
        else if (fator == -2)
        {
            int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);
            if (fatorFilhoEsq == 1)
            {
                no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);
        }
    }
    return no;
}

No *insereArvore(Jogador *x, No *node)
{
    if (node == NULL)
    {
        node = criarNo1(x);
    }
    else if (strcmp(x->nome, node->element->nome) < 0)
    {
        node->esq = insereArvore(x, node->esq);
    }
    else if (strcmp(x->nome, node->element->nome) > 0)
    {
        node->dir = insereArvore(x, node->dir);
    }
    return balancear(node);
}

void add(Jogador *x)
{
    raiz = insereArvore(x, raiz);
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

    scanf(" %s", leitura);

    while (strcmp(leitura, "FIM") != 0)
    {
        int id = atoi(leitura);
        add(jogadores[id]);
        scanf(" %s", leitura);
    }

    scanf(" %[^\n]", leitura);
    
    while (strcmp(leitura, "FIM") != 0)
    {
        search(leitura);
        scanf(" %[^\n]", leitura);
    }

    fclose(ler);

    return 0;
}