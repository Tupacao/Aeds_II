#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void Iterativo (char vetor[], char letra, char troca){

    // Troca os valores gerados de maneira "aleatoria" nas posições que ocorrem as aparições

    for (int i = 0; i < strlen(vetor); i++)
    {
        if(vetor[i] == letra){
            vetor[i] = troca;
        }
    }

}

int main()
{   
    // Definir as variaveis

    int flag = 0;
    char vetor[500];

    scanf("%[^\n]", vetor);

    
    while (flag != 1)
    {
        // Geração "aleatória"

        srand(4);
        char letra = (char)('a' + (abs(rand()) % 26));
        char troca = (char)('a' + (abs(rand()) % 26));

        // Chamada do método que troca os valores

        Iterativo(vetor, letra, troca);

        printf("%s\n", vetor);

        scanf(" %[^\n]", vetor);

        if (vetor[0] == 'F' && vetor[1] == 'I' && vetor[2] == 'M')
        {
            flag = 1;
        }
        
    }
    
    return 0;
}
