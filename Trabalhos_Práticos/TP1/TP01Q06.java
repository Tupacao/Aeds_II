public class TP01Q06 {

    // Testa se todos os caracters sao vogais
    
    public static boolean isVogal(String str){


        boolean var = true;

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U') {
                var = true;
            }
            else{
                var = false;
                i = str.length();
            }
        }

        return var;
    }
    
    // Testa se todos os caracters são consoantes

    public static boolean isConsoante (String str){
        boolean var = true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U') {
                var = false;
                i = str.length();
            } else {
                if (str.charAt(i) >= 65 && str.charAt(i) <= 90 || str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                    var = true;
                }
                else{
                    var = false;
                    i = str.length();
                }
            }
        }

        return var;
    }

    // Testa se todos os caracters são inteiros

    public static boolean isInteiro(String str){
        
        boolean var = true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) <= 47 || str.charAt(i) >= 58) {
                var = false;
                i = str.length();
            }
        }

        return var;
    }
    
    // Testa se a string é um numero real

    public static boolean isReal (String str){

        boolean var = true, var_inteiro = isInteiro(str);
        
        if (var_inteiro == false) {

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    if (str.charAt(i) == '.' || str.charAt(i) == ',') {
                        var = true;
                    } else {
                        var = false;
                        i = str.length();
                    }
                } else {
                    if(str.charAt(i) == ','){
                        for (int j = i; j < str.length(); j++) {
                            if (str.charAt(j) == ',') {
                                var = false;
                                i = str.length();
                            }
                        }
                    }
                    else if(str.charAt(i) == '.'){
                        if( (i+3) <= str.length()){
                            if(str.charAt(i+3) == '.'){
                                var = true;
                            }
                            else{
                                var = false;
                                i = str.length();
                            }
                        }
                        else{
                            var = true;
                        }
                    }
                }
              
            }
        }   
        else{
            var = true;
        }
        
        return var;

    }
    
    public static void main(String[] args) {
        
        // Definição das variaveis

        String resp = "", str = MyIO.readLine();
        boolean flag = true;

        // Chamada das funções e insere na tela o resultado

        while (flag) {
            
            resp = "";

            if (isVogal(str)) {
                resp+="SIM ";
            } else {
                resp+="NAO ";
            }
    
            if (isConsoante(str)) {
                resp+="SIM ";
            } else {
                resp+="NAO ";
            }
    
            if (isInteiro(str)) {
                resp+="SIM ";
            } else {
                resp+="NAO ";
            }
    
            if (isReal(str)) {
                resp+="SIM";
            } else {
                resp+="NAO";
            }

            MyIO.println(resp);

            str = "";
            
            str = MyIO.readLine();

            if (str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
                flag = false;
            }

        }

    }
}