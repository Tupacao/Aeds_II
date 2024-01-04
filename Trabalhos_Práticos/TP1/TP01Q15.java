public class TP01Q15 {
    
    public static boolean isVogal (String str, int i){

        // Identifica se a string é apenas composta por vogal de maneira recursiva

        boolean var = true;

        if (i < str.length()) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U') {
                return isVogal(str, i+1);
            }
            else{
                var = false;
            }
        }

        return var;
    }
    
    public static boolean isConsoante(String str, int i){

        // Identifica se a string é apenas composta por consoantes de maneira recursiva

        boolean var = true;

        if (i < str.length()) {
            if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U'){ 
                var = false;
            }
            else if(str.charAt(i) >= 65 && str.charAt(i) <= 90 || str.charAt(i) >= 97 && str.charAt(i) <= 122){
                return isConsoante(str, i+1);
            }
            else{
                var = false;
            }
        }

        return var;
    }
    
    public static boolean isInteiro(String str, int i){
        
        // Identifica se a string é apenas composta por um numero inteiro de maneira recursiva

        boolean var = true;

        if (i < str.length()) {
            if (str.charAt(i) <= 47 || str.charAt(i) >= 58) {
                var = false;
            }
            else{
                return isInteiro(str, i+1);
            }
        }

        return var;
    }
    
    public static boolean isReal (String str, int i){
        
        // Identifica se a string é apenas composta por um numero real de maneira recursiva
        
        boolean var = true;

        if (i < str.length()) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                if (str.charAt(i) == '.' || str.charAt(i) == ',') {
                    return isReal(str, i + 1);
                } else {
                    var = false;
                }
            } else {
                if(str.charAt(i) == ','){
                    for (int j = i; j < str.length(); j++) {
                        if (str.charAt(j) == ',') {
                            var = false;
                        }
                    }
                }
                else if(str.charAt(i) == '.'){
                    if( (i+3) <= str.length()){
                        if(str.charAt(i+3) == '.'){
                            return isReal(str, i+1);
                        }
                        else{
                            var = false;
                        }
                    }
                    else{
                        return isReal(str, i+1);
                    }
                }
            }
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

            if (isVogal(str, 0)) {
                resp+="SIM ";
            } else {
                resp+="NAO ";
            }
    
            if (isConsoante(str, 0)) {
                resp+="SIM ";
            } else {
                resp+="NAO ";
            }
    
            if (isInteiro(str, 0)) {
                resp+="SIM ";
            } else {
                resp+="NAO ";
            }
    
            if (isReal(str, 0)) {
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