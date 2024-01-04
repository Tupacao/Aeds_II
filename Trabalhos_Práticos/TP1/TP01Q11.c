#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int isPalindromo (char vetor[], int i){

    // Verifa recursivamente se é ou não palindromo

    int var = 0;

    if (i < strlen(vetor)/2)
    {
        if(vetor[i] == vetor[strlen(vetor) - 1 - i]){
            return isPalindromo(vetor, i + 1);
        }
        else{
            var = 1;
        }
    }

    return var;
}

int main()
{
    // Declaração das variáveis

    char vetor[500];
    int flag = 0, aux;

    scanf("%[^\n]", vetor);

    // Pegando as entradas e vendo se é ou não palindromo

    while (flag != 1)
    {
        aux = isPalindromo(vetor, 0);

        if(aux == 0)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
        
        scanf(" %[^\n]", vetor);

        if (strcmp(vetor, "FIM") == 0)
        {
            flag = 1;
        }
        
    }

    return 0;
}
