import java.io.*;
import java.net.*;

class ExemploURL {
   public static String getHtml(String endereco){
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
         url = new URL(endereco);
         is = url.openStream();  // throws an IOException
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
}

public class Q8{

    //conta a quantidade dos Padroes < table > em uma dada string
   public static int contarPadraoTable(String s) {
    String padrao = "<table>";
     int cont=0;

    for(int i=0; i<s.length(); i++){
         if(s.charAt(i) == padrao.charAt(0) && s.charAt(i+1) == padrao.charAt(1) && s.charAt(i+2) == padrao.charAt(2) &&
             s.charAt(i+3) == padrao.charAt(3) && s.charAt(i+4) == padrao.charAt(4) && s.charAt(i+5) == padrao.charAt(5) &&
             s.charAt(i+6) == padrao.charAt(6)){
             cont++;
         }
     }

    return cont;
}


   //conta a quantidade dos Padroes < br > em uma dada string
   public static int contarPadraoBr(String s) {
       String padrao = "<br>";
        int cont=0;

       for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == padrao.charAt(0) && s.charAt(i+1) == padrao.charAt(1) && s.charAt(i+2) == padrao.charAt(2) &&
                s.charAt(i+3) == padrao.charAt(3)){
                cont++;
            }
        }

       return cont;
   }


   //conta quantas consoantes tem em uma string
   public static int contarConsoantes(String s) {
        int cont=0;
            
        for(int i=0; i<s.length(); i++){
            if((s.charAt(i) != 'a' &&  s.charAt(i) != 'e' &&  s.charAt(i) != 'i' &&  s.charAt(i) != 'o' &&
                s.charAt(i) != 'u' &&  s.charAt(i) != '??' &&  s.charAt(i) != '??' &&  s.charAt(i) != '??'
                &&  s.charAt(i) != '??' &&  s.charAt(i) != '??' &&  s.charAt(i) != '??'
                &&  s.charAt(i) != '??' &&  s.charAt(i) != '??' &&  s.charAt(i) != '??' &&  s.charAt(i) != '??'
                &&  s.charAt(i) != '??' &&  s.charAt(i) != '??' &&  s.charAt(i) != '??' &&  s.charAt(i) != '??'
                &&  s.charAt(i) != '??' &&  s.charAt(i) != '??' &&  s.charAt(i) != '??' ) && (s.charAt(i)>= 'a' && s.charAt(i)<= 'z') ){
            }else{
                cont++;
            }
        }
        
        return cont;
   }


    //conta a quantidade de um caractere especifico em uma string fornecida
    public static int contarCaractere(String s, char elemento) {
        int cont=0;

        for(int i=0; i<s.length(); i++){
            if(elemento==s.charAt(i)){
                cont++;
            }
        }

        return cont;
    }


    public static String montarStringSaida(String nome, String endereco) {
        String saida, html;
        html = ExemploURL.getHtml(endereco);

        
        //padroes
        int contBr = contarPadraoBr(html);
        int contTable = contarPadraoTable(html);

        //letras sem acentuacao
        int contA = contarCaractere(html, 'a') - contTable;
        int contE = contarCaractere(html, 'e') - contTable;
        int contI = contarCaractere(html, 'i');
        int contO = contarCaractere(html, 'o');
        int contU = contarCaractere(html, 'u');

        //elementos com acento agudo
        int contAAgudo = contarCaractere(html, '??');
        int contEAgudo = contarCaractere(html, '??');
        int contIAgudo = contarCaractere(html, '??');
        int contOAgudo = contarCaractere(html, '??');
        int contUAgudo = contarCaractere(html, '??');

        //elementos com crase
        int contACrase = contarCaractere(html, '??');
        int contECrase = contarCaractere(html, '??');
        int contICrase = contarCaractere(html, '??');
        int contOCrase = contarCaractere(html, '??');
        int contUCrase = contarCaractere(html, '??');

        //elementos com tiu
        int contAtiu = contarCaractere(html, '??');
        int contOtiu = contarCaractere(html, '??');

        //elementos com acento circunflexo
        int contACircunflex = contarCaractere(html, '??');
        int contECircunflex = contarCaractere(html, '??');
        int contICircunflex = contarCaractere(html, '??');
        int contOCircunflex = contarCaractere(html, '??');
        int contUCircunflex = contarCaractere(html, '??');

        //consoantes
        int contConsoantes = contarConsoantes(html) - (2*contBr) - (3*contTable);
        
        //montagem da string de saida
        saida = "a("+contA+") e("+contE+") i("+contI+") o("+contO+") u("+contU+") ??("+contAAgudo+") ??("+contEAgudo+") ??("+contIAgudo+") ??("+contOAgudo+") ??("+contUAgudo+") ??("+contACrase+") ??("+contECrase+") ??("+contICrase+") ??("+contOCrase+") ??("+contUCrase+") ??("+contAtiu+") ??("+contOtiu+") ??("+contACircunflex+") ??("+contECircunflex+") ??("+contICircunflex+") ??("+contOCircunflex+") ??("+contUCircunflex+") consoante("+contConsoantes+") <br>("+contBr+") <table>("+contTable+") "+nome;

        return saida;
    }
  
    
    //checa se a confi??????o de parada (FIM) foi atingida
    public static boolean isFim(String s) {
        if(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M'){
            return true;
        }else{
            return false;
        }
    }


    public static void main(String[] args) {
        String nome="", endereco="", saida;
        int cont=0;

        do{
            if(cont%2==0){
                nome = MyIO.readLine();
            }else{
                endereco = MyIO.readLine();
                saida = montarStringSaida(nome, endereco);
                System.out.println(saida); 
            }

            cont++;
        }while(isFim(nome)==false);

        

    }
}