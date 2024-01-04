import java.io.*;

public class TP01Q08 {
    public static void main(String[] args) {
        
        // Declaração das variaveis

        int tamanho = MyIO.readInt();
        double number, aux;
        
        try{

            // Abrir o arquivo para escrever e ler

            RandomAccessFile raf = new RandomAccessFile("entrada.txt", "rw");
            
            // Escrever no arquivo

            for (int i = 0; i < tamanho; i++) {
                number = MyIO.readDouble();
                raf.writeDouble(number);
            }

            raf.close();

            // Ler do arquivo e printar na tela

            RandomAccessFile read = new RandomAccessFile("entrada.txt", "r");

            for (int i = 0; i < tamanho; i++) {
                read.seek((tamanho - 1 - i) * 8);
                
                // Saber se é ou não um valor inteiro

                aux = read.readDouble();
                if (aux == (int) aux) {
                    MyIO.println((int) aux);
                } else {
                    MyIO.println(aux);
                }
            }

            read.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
