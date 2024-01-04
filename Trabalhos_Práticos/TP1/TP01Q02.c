#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main () {

    // Declaração de variaveis
    char str[500];
    int flag = 0, var = 1;

    // Ler a primeira palavra

    scanf("%[^\n]", str);

    while (flag != 1)
    {
        
        // Indentificar se é ou não palindromo, var = 0 não é palindromo, var = 1 é palindromo

        for (int i = 0; i < strlen(str)/2; i++)
        {
            if (str[i] != str[strlen(str) - 1 - i])
            {
                var = 0;
                i = strlen(str);
            }
            else
            {
                var = 1;
            }
            
        }
        
        // Printar na tela

        if (var == 0)
        {
            printf("NAO\n");
        }
        else
        {
            printf("SIM\n");
        }

        scanf(" %[^\n]", str);

        // Flag para parar o programa

        if (strcmp(str, "FIM") == 0)
        {
            flag = 1;
        }

    }
    

    return 0;
}
