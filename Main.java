import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> names = readNamesFromCSV("female_names.txt");
            System.out.println("Numero de nomes lidos: " + names.size());

            if (names.size() > HashAbstrata.tamanho_tabela) {
                System.out.println("Numero de nomes maior que o tamanho da tabela");
            }
            
            HashSimples tabelaSimples = new HashSimples();
            HashMelhorada tabelaMelhorada = new HashMelhorada();
            
            long startTime, endTime;
            
            startTime = System.nanoTime();
            for (String name : names) {
                tabelaSimples.insert(name);
            }
            endTime = System.nanoTime();
            long insertTempoSimples = endTime - startTime;
            
            startTime = System.nanoTime();
            for (String name : names) {
                tabelaMelhorada.insert(name);
            }
            endTime = System.nanoTime();
            long insertTempoMelhorada = endTime - startTime;
            
            startTime = System.nanoTime();
            for (String name : names) {
                tabelaSimples.search(name);
            }
            endTime = System.nanoTime();
            long tempoSearchSimples = endTime - startTime;
            
            startTime = System.nanoTime();
            for (String name : names) {
                tabelaMelhorada.search(name);
            }
            endTime = System.nanoTime();
            long tempoSearchMelhorada = endTime - startTime;
            
            System.out.println("\nRelatorio de Comparacao de Tabelas Hash");
            System.out.println("------------------------------------");
            System.out.println("Tabela Hash Simples:");
            System.out.println("Numero de Colisoes: " + tabelaSimples.getColisoes());
            System.out.println("Tempo de Insercao: " + insertTempoSimples + " ns");
            System.out.println("Tempo de Busca: " + tempoSearchSimples + " ns");
            printColisoesPorPosicao("Simples", tabelaSimples.getColisoesPorPosicao());

            System.out.println("\nTabela Hash Melhorada:");
            System.out.println("Numero de Colisoes: " + tabelaMelhorada.getColisoes());
            System.out.println("Tempo de Insercao: " + insertTempoMelhorada + " ns");
            System.out.println("Tempo de Busca: " + tempoSearchMelhorada + " ns");
            printColisoesPorPosicao("Melhorada", tabelaMelhorada.getColisoesPorPosicao());
            
            printDistribuidor("Simples", tabelaSimples.indicePorPos());
            printDistribuidor("Melhorada", tabelaMelhorada.indicePorPos());
            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static List<String> readNamesFromCSV(String filename) throws IOException {
        List<String> names = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename, StandardCharsets.UTF_8))) {
            
            String linha;
            boolean primeiraLinha = true;
            
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                
                String name = linha.replace("\"", "").trim();
                
                if (!name.isEmpty()) {
                    names.add(name);
                }
            }
        }
        
        return names;
    }
    
    private static void printDistribuidor(String tableName, int[] distribuidor) {
        System.out.println("\nDistribuicao de Chaves - Tabela " + tableName);
        int maxItensPorBucket = 0;
        int bucketsPreenchidos = 0;
        int bucketsVazios = 0;
        
        for (int count : distribuidor) {
            if (count > 0) {
                bucketsPreenchidos++;
                maxItensPorBucket = Math.max(maxItensPorBucket, count);
            } else {
                bucketsVazios++;
            }
        }
        
        System.out.println("Numero de buckets preenchidos: " + bucketsPreenchidos);
        System.out.println("Numero de buckets vazios: " + bucketsVazios);
        System.out.println("Numero maximo de itens por bucket: " + maxItensPorBucket);
    }
    
    private static void printColisoesPorPosicao(String tableName, int[] colisoesPorPosicao) {
        int totalColisoes = 0;
        for (int colisoes : colisoesPorPosicao) {
            totalColisoes += colisoes;
        }
        System.out.println("\nTotal de Colisoes por Posicao - Tabela " + tableName + ": " + totalColisoes);
    }
}
