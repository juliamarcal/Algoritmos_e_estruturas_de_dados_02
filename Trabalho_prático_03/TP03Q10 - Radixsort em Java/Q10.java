import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;






class Serie{

    // declaracao de atributos
    private String nome;
    private String formato;
    private String duracao;
    private String paisDeOrigem;
    private String idiomaOriginal;
    private String emissora;
    private String transmissaoOriginal;
    private int numTeporadas;
    private int numEpisodios;
    public int key;

    //construtores
    Serie(){
        nome = "";
        formato = "";
        duracao = "";
        paisDeOrigem = "";
        idiomaOriginal = "";
        emissora = "";
        transmissaoOriginal = "";
        numTeporadas = 0;
        numEpisodios = 0;
        key = 0;
    }

    Serie(String nome, String formato, String duracao, String paisDeOrigem, String idiomaOriginal, String emissora, String transmissaoOriginal, int numTeporadas, int numEpisodios){
        nome = this.nome;
        formato = this.formato;
        duracao = this.duracao;
        paisDeOrigem = this.paisDeOrigem;
        idiomaOriginal = this.idiomaOriginal;
        emissora = this.emissora;
        transmissaoOriginal = this.transmissaoOriginal;
        numTeporadas = this.numTeporadas;
        numEpisodios = this.numEpisodios;
        key = (numEpisodios*1000) + numTeporadas;
    }

    //setters e getters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao; 
    }

    public String getPaisDeOrigem() {
        return paisDeOrigem;
    }

    public void setPaisDeOrigem(String paisDeOrigem) {
        this.paisDeOrigem = paisDeOrigem;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public String getTransmissaoOriginal() {
        return transmissaoOriginal;
    }

    public void setTransmissaoOriginal(String transmissaoOriginal) {
        this.transmissaoOriginal = transmissaoOriginal;
    }

    public int getNumTeporadas() {
        return numTeporadas;
    }

    public void setNumTeporadas(int numTeporadas) {
        this.numTeporadas = numTeporadas;
    }

    public int getNumEpisodios() {
        return numEpisodios;
    }

    public void setNumEpisodios(int numEpisodios) {
        this.numEpisodios = numEpisodios;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    //metodos clone
    public Serie clone(){
        Serie resp = new Serie();

        resp.nome = this.nome;
        resp.formato = this.formato;
        resp.duracao = this.duracao;
        resp.paisDeOrigem = this.paisDeOrigem;
        resp.idiomaOriginal = this.idiomaOriginal;
        resp.emissora = this.emissora;
        resp.transmissaoOriginal = this.transmissaoOriginal;
        resp.numTeporadas = this.numTeporadas;
        resp.numEpisodios = this.numEpisodios;
        resp.key = this.key;

        return resp;
    }

    //metodo imprimir
    public void imprimir(){
        System.out.println(nome + " " + formato + " " + duracao + " " + paisDeOrigem + " " + idiomaOriginal + " " + emissora + " " + transmissaoOriginal + " " + numTeporadas + " " + numEpisodios);
    }

    //retira as tags de uma string
    public String tiraTags(String html) {
        String semTags = "";
        int i=0;

        while(i<html.length()){
            if(html.charAt(i)=='<'){
                i++;
                while(html.charAt(i)!='>'){
                    i++;
                }
            }else{
                semTags += html.charAt(i);
            }
            i++;
        }

        return semTags;
    }

    //transforma um numero em formato String para formato int
    public int transformaEmInteiro(String s){
        int num = 0;
        int mult = 0;

        for(int i=s.length()-1; i>=0; i--){
            num += (((int) s.charAt(i)) - 48) * Math.pow(10, mult);
            mult++;
        }

        return num;
    }

    //retira todos os caracteres nao numeros de uma string
    public String soDeixaNumeros(String s){
        String novaLinha="";

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)==' '){
                i = s.length();
            }else{
                novaLinha += s.charAt(i);
            }
        }

        return novaLinha;
    }

    //retira espacos no inicio ou final da string
    public String trim(String s) {
        String novaLinha="";
        int posInicio=0, posFinal=s.length()-1;

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)!=' '){
                posInicio = i;
                i = s.length();
            }
        }
        
        for(int i=s.length()-1; i>0; i--){
            if(s.charAt(i)!=' '){
                posFinal = i;
                i = 0;
            }
        }

        for(int i=posInicio; i<=posFinal; i++){
            novaLinha += s.charAt(i);
        }
    
        return novaLinha;
    }

    //retira informacoes disnecessarias como "&#160;" da transmissao original
    public String retiraSpan(String s) {
        String novaLinha="";

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='&'){
                i++;
                while(s.charAt(i)!=';'){
                    i++;
                }
            }else{
                novaLinha += s.charAt(i);
            }
        }

        return novaLinha;
    }

    //formata o nome da serie
    public String formataNome(String nome){
        String novoNome="";

        for(int i=0; i<nome.length(); i++){
            if(nome.charAt(i)=='_'){
                novoNome += ' ';
            }else if(nome.charAt(i)=='/'){
                i += 11;
                

            }else if(nome.charAt(i)=='.' && nome.charAt(i+1)=='h' && nome.charAt(i+2)=='t' && nome.charAt(i+3)=='m' && nome.charAt(i+4)=='l'){
                i += 5;
            }else{
                novoNome += nome.charAt(i);
            }
        }

        return novoNome;
    }

    //metodo ler
    public void ler(String nomeArquivo) throws Exception{
        InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeArquivo));
        BufferedReader br = new BufferedReader(isr);
        
        this.nome = formataNome(nomeArquivo);

        while(!br.readLine().contains("Formato"));
        this.formato = tiraTags(br.readLine());

        while(!br.readLine().contains("Duração")); 
        this.duracao = tiraTags(br.readLine());

        while(!br.readLine().contains("País de origem"));
        this.paisDeOrigem = trim(retiraSpan(tiraTags(br.readLine())));

        while(!br.readLine().contains("Idioma original"));
        this.idiomaOriginal = trim(tiraTags(br.readLine()));

        while(!br.readLine().contains("Emissora"));
        this.emissora = trim(tiraTags(br.readLine()));

        while(!br.readLine().contains("Transmissão original"));
        this.transmissaoOriginal = trim(retiraSpan(tiraTags(br.readLine())));

        while(!br.readLine().contains("temporadas"));
        this.numTeporadas = transformaEmInteiro(soDeixaNumeros(tiraTags(br.readLine())));

        while(!br.readLine().contains("episódios"));
        this.numEpisodios = Integer.parseInt(soDeixaNumeros(tiraTags(br.readLine())));

        this.key = (numEpisodios*1000) + numTeporadas;

        br.close();
    }

}



class Lista{
    private Serie[] array;
    private int n;

    //construtores
    public Lista(){
        this(1000);
    }

    public Lista(int size){
        array = new Serie[size];
        n=0;
    }

    //Insere um elemento na primeira posicao da lista e move os demais
    public void inserirInicio(Serie serie) throws Exception {

        //validar insercao
        if(n >= array.length){
            throw new Exception("Erro ao inserir!");
        } 

        //levar elementos para o fim do array
        for(int i = n; i > 0; i--){
            array[i] = array[i-1];
        }

        array[0] = serie;
        n++;
    }

    //Insere um elemento na ultima posicao da lista.
    public void inserirFim(Serie serie) throws Exception {

        //validar insercao
        if(n >= array.length){
            throw new Exception("Erro ao inserir!");
        }

        array[n] = serie;
        n++;
    }

    //Insere um elemento em uma posicao especifica e move os demais
    public void inserir(Serie serie, int pos) throws Exception {

        //validar insercao
        if(n >= array.length || pos < 0 || pos > n){
            throw new Exception("Erro ao inserir!");
        }

        //levar elementos para o fim do array
        for(int i = n; i > pos; i--){
            array[i] = array[i-1];
        }

        array[pos] = serie;
        n++;
    }

    //Remove um elemento da primeira posicao da lista e movimenta
    public Serie removerInicio() throws Exception {

        //validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        Serie resp = array[0];
        n--;

        for(int i = 0; i < n; i++){
            array[i] = array[i+1];
        }

        return resp;
    }

    //Remove um elemento da ultima posicao da lista.
    public Serie removerFim() throws Exception {

        //validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        return array[--n];
        }

    //Remove um elemento de uma posicao especifica da lista e movimenta os demais elementos para o inicio da mesma.
    public Serie remover(int pos) throws Exception {

        //validar remocao
        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }

        Serie resp = array[pos];
        n--;

        for(int i = pos; i < n; i++){
            array[i] = array[i+1];
        }

        return resp;
    }

    //Mostra os elementos da lista
    public void mostrar (){

        for(int i = 0; i < n; i++){
            array[i].imprimir();
        }

        }

    //Procura um elemento e retorna se ele existe
    public boolean pesquisar(String nome) {
        boolean retorno = false;
        for (int i = 0; i < n && retorno == false; i++) {
            retorno = (array[i].getNome() == nome);
        }
        return retorno;
    }

    //retorna o numero de elementos na lista
    public int getNumElementos() {
        return n;
    }

    //devolve a maior chave
    public int getMax(){
        int mx = array[0].key;
        for (int i = 1; i < n; i++)
            if (array[i].key > mx)
                mx = array[i].key;
        return mx;
    }

    // counting sort do array de acordo com o digito
    public void countSort(int exp, Log log){
        Serie output[] = new Serie[n]; 
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
 
        for (i = 0; i < n; i++){
            count[(array[i].key / exp) % 10]++;
        }
            
        for (i = 1; i < 10; i++){
            count[i] += count[i - 1];
        }
            
        for (i = n - 1; i >= 0; i--) {
            log.mov++;
            output[count[(array[i].key / exp) % 10] - 1] = array[i];
            count[(array[i].key / exp) % 10]--;
        }
 
        for (i = 0; i < n; i++){
            log.mov++;
            array[i] = output[i];
        }
            
    }

    //Ordenacao por radixSort dos elementos da lista com chave key = (numEpisodios * 1000 + numTemporadas)
    public void ordenar(int esq, int dir, Log log){
        // Encontrar o menor numero para saber a quantidade de digitos que devem ser usados
        int m = getMax();
 
        // Faz a coutSort para cada digito
        for (int exp = 1; m / exp > 0; exp *= 10){
            countSort(exp, log);
        }

        //ordena empates de chave
        ordenarEmpate(log);
            
    }
    
    //ordenar os elementos que empatam com a chave nome, por meio do metodo de ordenacao por selecao
    public void ordenarEmpate(Log log) {
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++){
                log.comp++;
               if ((array[menor].getKey() == array[j].getKey()) && (array[menor].getNome().compareTo(array[j].getNome()) > 0)){
                  menor = j;
               }
            }
            swap(menor, i, log);
         }
    }

    //troca de elementos do array
    public void swap(int i, int j, Log log) {
        log.mov++;
        Serie temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}



class Log{
    public int comp;
    public int mov;
}



public class Q10{

    public static boolean isFIM(String s) {
        return(s.length()==3 && s.charAt(0)=='F' && s.charAt(1)=='I' && s.charAt(2)=='M');
    }

    public static void main(String[] args) throws Exception {
        String entrada = MyIO.readLine();
        Serie serie = new Serie();
        Lista lista = new Lista();
        
        while(isFIM(entrada)==false){
            serie.ler("/tmp/series/" + entrada);
            lista.inserirFim(serie);

            serie = new Serie();
            entrada = MyIO.readLine();
        }

        int n = lista.getNumElementos();


        long inicio = new Date().getTime(); //pega a hora exata de inicio
        Log log = new Log();

        lista.ordenar(0, n-1, log);

        //preenchimento do arquivo log
        long fim = new Date().getTime(); //pega a hora exata do fim
        double tempo = (fim-inicio)/1000; //calcula o tempo de execucao

        int numComparacoes = log.comp;
        int numMovimentacoes = log.mov;

        Arq.openWrite("matricula radixsort.txt", "UTF-8");
        Arq.print("741019\t"); //matricula
        Arq.print(numComparacoes+"\t");// comparacoes
        Arq.print(numMovimentacoes+"\t");//movimentacoes
        Arq.print(tempo+"\t");//tempo de execucao
        Arq.close();


        lista.mostrar();



    }
}









