public class TP01Q10 {
    
    public static boolean isPalindromo (String str, int i){
        
        boolean var = true;

        if( i < str.length()/2){
            if (str.charAt(i) == str.charAt(str.length() - 1 - i)) {
                return isPalindromo(str, i + 1);
            }
            else{
                var = false;
            }
        }

        return var;

    }
    
    public static void main(String[] args) {
        
        // Declarando as variaveis
        
        String str = MyIO.readLine();
        boolean flag = true;

        while(flag){

            if (isPalindromo(str, 0)) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NAO");
            }
    
            str = MyIO.readLine();
    
            if (str.charAt(0) == 'F' && str.charAt(1) == 'I' &&str.charAt(2) == 'M') {
                flag = false;
            }
        }
        
    }
}
