public class TP01Q05 {

    public static String mudaString (String str){
        
        String resp = "";

        for (int i = 0; i < str.length(); i++) {
            
            if(str.charAt(i) == 'o' && str.charAt(i+1) == 'r'){
                resp+= 'V';
            }
            else if(str.charAt(i) == 'n' && str.charAt(i+1) == 'o'){
                resp+= '~';
            }
            else if(str.charAt(i) == 'a'){
                resp+= '^';
            }
            else if(str.charAt(i) == '('){
                resp+='(';
            }
            else if(str.charAt(i) == ')'){
                resp+= ')';
            }
            else if(str.charAt(i) == 'A'){
                resp+= 'A';
            }
            else if(str.charAt(i) == 'B'){
                resp+= 'B';
            }
            else if(str.charAt(i) == 'C'){
                resp+= 'C';
            }
        }
    
        return resp;
    }

    public static String mudarValores(String str, int vetor[]){

        String resp = "";

        for (int i = 0; i < str.length(); i++) {
            
            if(str.charAt(i) == 'A'){
                if (vetor[0] == 0) {
                    resp+= '0';
                } else {
                    resp+= '1';
                }
            }
            else if(str.charAt(i) == 'B'){
                if (vetor[1] == 0) {
                    resp+= '0';
                } else {
                    resp+= '1';
                }
            }
            else if(str.charAt(i) == 'C'){
                if (vetor[2] == 0) {
                    resp+= '0';
                } else {
                    resp+= '1';
                }
            }
            else{
                resp+= str.charAt(i);
            }
        
        }

        return resp;
        
    }

    public static String resolveNot (String str, int posicao){
        
        String resp = "";

        int j;

        if (posicao == 0) {
            j = 0;
        } else {
            j = 1;
        }

        for (int i = 0; i <= posicao; i++, j++) {
            resp+= str.charAt(i);

            if (j == posicao) {

                if (str.charAt(j+2) == '0') {
                    resp+= '1';                    
                } else {
                    resp+= '0';
                }

                for (int j2 = j+4; j2 < str.length(); j2++) {
                    resp+= str.charAt(j2);
                }
                
                break;

            }
        }

        return resp;

    }

    public static String resolveAnd(String str, int posicao){
        String resp = "";

        int j;

        if (posicao == 0) {
            j = 0;
        } else {
            j = 1;
        }

        for (int i = 0; i <= posicao; i++, j++) {
            resp+= str.charAt(i);

            if (j == posicao) {
                if(str.charAt(j+2) == '0' || str.charAt(j+3) == '0'){
                    resp+= '0';
                }
                else{
                    resp+= '1';
                }

                for (int j2 = j+5; j2 < str.length(); j2++) {
                    resp+= str.charAt(j2);
                }
                break;
            }
        }

        return resp;
    }

    public static String resolveOr(String str, int posicao){
        String resp = "";

        int j;

        if (posicao == 0) {
            j = 0;
        } else {
            j = 1;
        }

        for (int i = 0; i <= posicao; i++, j++) {
            resp+= str.charAt(i);

            if(j == posicao){
                if(str.charAt(j+2) == '1' || str.charAt(j+3) == '1'){
                    resp+= '1';
                }
                else{
                    resp+= '0';
                }

                for (int j2 = j+5; j2 < str.length(); j2++) {
                    resp+= str.charAt(j2);
                }

                break;
            }            
        }

        return resp;

    }

    public static boolean zeroOuum(String str){
        boolean var = true;

        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '^') {
                str = resolveAnd(str, i);
            } else if(str.charAt(i) == '~'){
                str = resolveNot(str, i);
            } else if(str.charAt(i) == 'V'){
                str = resolveOr(str, i);
            }
        }

        if (str.charAt(1) == '0') {
            var = false;
        } else {
            var = true;
        }

        return var;

    }


    public static void main(String[] args) {

        // Pega a entrada do terminal e depois transfere o primeiro elemento para criar o tamanho do vetor
        String str = MyIO.readLine();

        while(str.charAt(0) != '0'){

            int tam;
            
            tam = str.charAt(0)-48;
    
            int vetor[] = new int[tam];
            int j=2;
            for (int i = 0; i < vetor.length; i++, j+=2) {
                vetor[i] = str.charAt(j)-48;
            }
            
            // Chama a função para criar uma nova string para ser manipulada
    
            str = mudaString(str);
            
            // Chama a função para criar uma nova string com os valores do vetor
    
            str = mudarValores(str, vetor);
    
            // Chama a função que faz as operações e descobre se o valor final é true ou false;
    
            boolean var = zeroOuum(str);
                
            if (var == true) {
                MyIO.println(1);
            } else {
                MyIO.println(0);
            }

            str = MyIO.readLine();

        }

    }
}