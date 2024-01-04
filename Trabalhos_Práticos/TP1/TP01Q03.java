public class TP01Q03 {
    public static void main(String[] args) {
        
        // Declarar as variavéis

        String str = MyIO.readLine();
        boolean flag = true;
        
        while (flag) {
            
            char vetor[] = new char[500];

            for (int i = 0; i < str.length(); i++) {
                vetor[i] = (char)((int)str.charAt(i) + 3);
            }

            String str2 = new String(vetor);

            MyIO.println(str2);
            
            str = MyIO.readLine();

            if (str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
                flag = false;
            }
        }
    }
}