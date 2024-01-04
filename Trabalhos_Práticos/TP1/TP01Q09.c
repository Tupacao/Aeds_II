#include <stdio.h>
#include <stdlib.h>

int main()
{   
    // Definindo as variaveis

    FILE *escrever;
    FILE *ler;
    int tamanho;
    double number, aux;

    scanf("%d", &tamanho);
    escrever = fopen("arquivo.txt", "w");

    // Escrevendo no arquivo

    for (int i = 0; i < tamanho; i++)
    {
        scanf("%lf", &number);
        fwrite(&number, sizeof(double), 1, escrever);
    }
    
    fclose(escrever);

    ler = fopen("arquivo.txt", "r");

    // Lendo do arquivo e printando ao contrario

    for (int i = 0; i < tamanho; i++)
    {
        fseek(ler, -(i + 1) * sizeof(double), SEEK_END);
        fread(&aux, sizeof(double), 1, ler);
        if (aux == (int) aux)
        {
            printf("%d\n", (int) aux);
        }
        else
        {
            // Responsavel por printar o numero do jeito que ele é sem a adição de zeros a esquerda

            printf("%g\n", aux);
        }
        
    }
    
    fclose(ler);

    return 0;
}