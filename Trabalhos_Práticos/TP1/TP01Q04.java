import java.util.Random;

class TP01Q04 {
    
    public static String iterativo(String str, char letra, char substituir){
        
        String resp = "";

        // Mudar a letra

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == letra) {
                resp+= substituir;
            } else {
                resp+= str.charAt(i);
            }            
        }

        return resp;
    }

    public static void main(String[] args) {
        
        // Declaracao de variaveis

        String str = MyIO.readLine();
        boolean flag = true;
        Random gerador = new Random();
        gerador.setSeed(4);
        char letra, substituir;

        while (flag) {
            
        
            // Caracter "aleatorio"
            letra = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            substituir = (char)('a' + (Math.abs(gerador.nextInt()) % 26));

            str = iterativo(str, letra, substituir);

            MyIO.println(str);

            str = MyIO.readLine();

            // Condicao de parada

            if (str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
                flag = false;
            }
        }
        
    }

}