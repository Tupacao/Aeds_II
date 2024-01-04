import java.io.*;
import java.net.*;

public class TP01Q07 {
    
    // Armazena os elementos de um html em uma string e depois retorna

    public static String getHtml(String endereco){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
  
        try {
           url = new URL(endereco);
           is = url.openStream();
           br = new BufferedReader(new InputStreamReader(is));
  
           while ((line = br.readLine()) != null) {
              resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
           mue.printStackTrace();
        } catch (IOException ioe) {
           ioe.printStackTrace();
        } 
  
        try {
           is.close();
        } catch (IOException ioe) {
           // nothing to see here  
        }
  
        return resp;
    }
    
    // Conta a quantidade de vogais que existem sem acento

    public static void contVogaisNormais(String str, int vetor[]){

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') {
                vetor[0]+=1;
            } else if (str.charAt(i) == 'e'){
                vetor[1]+=1;
            }else if (str.charAt(i) == 'i'){
                vetor[2]+=1;
            }else if (str.charAt(i) == 'o'){
                vetor[3]+=1;
            }else if (str.charAt(i) == 'u'){
                vetor[4]+=1;
            }
        }
    }

    // Quantidade de vogais que existem com acento

    public static void contVogaisAcento(String str, int vetor[]){

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == (char) 160) {
                vetor[5]+=1;
            } else if (str.charAt(i) == (char) 130){
                vetor[6]+=1;
            } else if (str.charAt(i) == (char) 161){
                vetor[7]+=1;
            } else if (str.charAt(i) == (char) 162){
                vetor[8]+=1;
            } else if (str.charAt(i) == (char) 163){
                vetor[9]+=1;
            } else if (str.charAt(i) == (char) 133 ) {
                vetor[10]+=1;
            } else if (str.charAt(i) == (char) 138 ){
                vetor[11]+=1;
            } else if (str.charAt(i) == (char) 141 ){
                vetor[12]+=1;
            } else if (str.charAt(i) == (char) 149 ){
                vetor[13]+=1;
            } else if (str.charAt(i) == (char) 151 ){ 
                vetor[14]+=1;
            } else if (str.charAt(i) == (char) 198 ) {
                vetor[15]+=1;
            } else if (str.charAt(i) == (char) 228 ){
                vetor[16]+=1;
            } else if (str.charAt(i) == (char) 131 ){
                vetor[17]+=1;
            } else if (str.charAt(i) == (char) 136 ){
                vetor[18]+=1;
            } else if (str.charAt(i) == (char) 140 ){ 
                vetor[19]+=1;
            } else if (str.charAt(i) == (char) 147 ){
                vetor[20]+=1;
            } else if (str.charAt(i) == (char) 150 ){
                vetor[21]+=1;
            }
        }
    }

    // Contagem de quantas consoantes existem

    public static void contConsoantes (String str, int vetor[]){
        
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'b'|| str.charAt(i) == 'c' || str.charAt(i) == 'd' || str.charAt(i) == 'f' ||  str.charAt(i) == 'g' || str.charAt(i) == 'h' || str.charAt(i) == 'j' || str.charAt(i) == 'k' || str.charAt(i) == 'l' || str.charAt(i) == 'm' || str.charAt(i) == 'n' || str.charAt(i) == 'p' || str.charAt(i) == 'q' || str.charAt(i) == 'r' || str.charAt(i) == 's' || str.charAt(i) == 't' || str.charAt(i) == 'v' || str.charAt(i) == 'w' || str.charAt(i) == 'x' || str.charAt(i) == 'y' || str.charAt(i) == 'z') {
                vetor[22]+=1;
            }
        }
    }

    // Contagem da quantidade de tags html

    public static void contTagsHtml(String str, int vetor[]){

        for (int i = 0; i < str.length(); i++) {
            
            if (str.charAt(i) == '<') {
                if(str.charAt(i+1) == 'b' && str.charAt(i+2) == 'r' && str.charAt(i+3) == '>'){
                    vetor[23]+=1;
                }
                else if(str.charAt(i+1) == 't' && str.charAt(i+2) == 'a' && str.charAt(i+3) == 'b' && str.charAt(i+4) == 'l' && str.charAt(i+5) == 'e' && str.charAt(i+6) == '>'){
                    vetor[24]+=1;
                } 
            }
        }
    }

    public static void main(String[] args) {
        
        // Defidindo as variaveis

        String nome, endereco, html;
        int vetor[] = new int[25];

        // Guardando as strings

        nome = MyIO.readLine();
        endereco = MyIO.readLine();

        while(true){
            
            html = getHtml(endereco);
            
            // Definindo o valor zero para todas as posicões do vetor;
            
            for (int i = 0; i < 25; i++) {
                vetor[i] = 0;
            }
            
            // Chamada das funções

            contVogaisNormais(html, vetor);
            contVogaisAcento(html, vetor);
            contConsoantes(html, vetor);
            contTagsHtml(html, vetor);
            
            // Mostrando os valores na tela

            MyIO.println("a("+ vetor[0] +") e(" + vetor[1] + ") i(" + vetor[2] + ") o(" + vetor[3] +") u(" + vetor[4] +") á("+ vetor[5] +") é(" + vetor[6] +") í(" + vetor[7] +") ó(" + vetor[8] + ") ú(" + vetor[9] + ") à(" + vetor[10] + ") è(" + vetor[11] + ") ì(" + vetor[12] + ") ò(" + vetor[13] + ") ù(" + vetor[14] + ") ã(" + vetor[15] + ") õ(" + vetor[16] + ") â(" + vetor[17] + ") ê(" + vetor[18] + ") î(" + vetor[19] + ") ô(" + vetor[20] + ") û(" + vetor[21] + ") consoante(" + vetor[22] + ") <br>(" + vetor[23] + ") <table>(" + vetor[24] + ") " + nome);
            
            nome = MyIO.readLine();

            if (nome.charAt(0) == 'F' && nome.charAt(1) == 'I' && nome.charAt(2) == 'M'){
                break;
            }

            endereco = MyIO.readLine();
        }


    }
}