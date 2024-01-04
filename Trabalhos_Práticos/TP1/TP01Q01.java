public class TP01Q01 {
    public static void main(String[] args) {
        
        // Definir variaveis

        String str = MyIO.readLine(), resp = "";
        boolean var = true;

        // While para receber varias entradas

        while (var) {
            
            // Teste se é ou não palindromo

            for (int i = 0; i < str.length()/2; i++) {
                if (str.charAt(i) != str.charAt( str.length() - 1 - i)) {
                    resp = "NAO";
                    break;
                } else {
                    resp = "SIM";
                }
            }

            MyIO.println(resp);

            str = MyIO.readLine();

            // Flag de parada do código

            if (str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
                var = false;
            }

        }

    }
}
