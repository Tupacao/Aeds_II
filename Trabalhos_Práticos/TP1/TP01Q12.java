public class TP01Q12 {
    
    public static String cifraDeCesar(String str, int i){

        // Cifra de cesar de maneira recursiva

        String resp = "";

        if(i < str.length()){
            return resp += (char) (str.charAt(i) + 3) + cifraDeCesar(str, i+1);
        }

        return resp;
    }
    
    public static void main(String[] args) {
        
        // Definir as variavéis

        String str = MyIO.readLine();
        boolean flag = true; 

        // Realiza a cifra de cesar e mostra a string na tela

        while(flag){

            String resp = "";

            resp = cifraDeCesar(str, 0);

            MyIO.println(resp);

            str = MyIO.readLine();

            if (str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
                flag = false;
            }
        }

    }
}
