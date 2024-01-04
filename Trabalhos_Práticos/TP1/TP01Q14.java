public class TP01Q14 {
    
    public static boolean expressaoBool(int[] entradas, String expressao, int posicaoInicio){
        int[] checados = new int[expressao.length()];
        int result = resolve(entradas, expressao, posicaoInicio, checados);
        if(result==1) return true;
        return false;
    }

    public static int resolve (int[] entradas, String operacao, int posicao, int[] checados){
        int temp1, temp2, j,i=posicao;
            switch(operacao.charAt(i)){
                case 'a':
                    i+=4;
                    temp1 = resolve(entradas, operacao, i, checados);

                    j = i;
                    while(operacao.charAt(j)!=',' && checados[j]==0) j++;
                    checados[j] = 1;

                    temp2 = resolve(entradas, operacao, j+2, checados);
                    if(temp1 + temp2 == 2) return 1;
                    return 0;

                case 'o':
                    i+=3;
                    temp1 = resolve(entradas, operacao, i, checados);
                    
                    j = i;
                    while(operacao.charAt(j)!=',' && checados[j]==0) j++;
                    checados[j] = 1;

                    temp2 = resolve(entradas, operacao, j+2, checados);
                    if(temp2 + temp1 > 0) return 1;
                    return 0;

                case 'n':
                    temp1 = resolve(entradas, operacao, i+4, checados);
                    return temp1==1?0:1;
                    
                default:  
                    return entradas[operacao.charAt(i)-65];
            }
    }

    public static void main(String[] args) {
    
        // Pega a entrada do terminal e depois transfere o primeiro elemento para criar o tamanho do vetor

        String str = MyIO.readLine();

        while (str.charAt(0) != '0') {
            
            int tam;
            
            tam = str.charAt(0)-48;
    
            int vetor[] = new int[tam];
            int j=2;
            for (int i = 0; i < vetor.length; i++, j+=2) {
                vetor[i] = str.charAt(j)-48;
            }
            
            // Metodo que retorna um valor booleano para mostrar o resultado na tela

            boolean var = expressaoBool(vetor, str, j);
    
            if (var == true) {
                MyIO.println(1);
            } else {
                MyIO.println(0);
            }

            str = MyIO.readLine();
            
        }


    }

}