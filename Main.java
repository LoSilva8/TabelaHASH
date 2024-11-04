import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Leitura do arquivo CSV
            List<String> names = readNamesFromCSV("female_names.txt");
            System.out.println("Total de nomes lidos: " + names.size());
            
            // Inicialização das tabelas hash
            SimpleHashTable simpleTable = new SimpleHashTable();
            AdvancedHashTable advancedTable = new AdvancedHashTable();
            
            // Medição de tempo para inserção
            long startTime, endTime;
            
            // Inserção na tabela simples
            startTime = System.nanoTime();
            for (String name : names) {
                simpleTable.insert(name);
            }
            endTime = System.nanoTime();
            long simpleInsertTime = endTime - startTime;
            
            // Inserção na tabela avançada
            startTime = System.nanoTime();
            for (String name : names) {
                advancedTable.insert(name);
            }
            endTime = System.nanoTime();
            long advancedInsertTime = endTime - startTime;
            
            // Medição de tempo para busca
            startTime = System.nanoTime();
            for (String name : names) {
                simpleTable.search(name);
            }
            endTime = System.nanoTime();
            long simpleSearchTime = endTime - startTime;
            
            startTime = System.nanoTime();
            for (String name : names) {
                advancedTable.search(name);
            }
            endTime = System.nanoTime();
            long advancedSearchTime = endTime - startTime;
            
            // Geração de relatório
            System.out.println("\nRelatório de Comparação de Tabelas Hash");
            System.out.println("------------------------------------");
            System.out.println("Tabela Hash Simples:");
            System.out.println("Número de Colisoes: " + simpleTable.getCollisions());
            System.out.println("Tempo de Insercao: " + simpleInsertTime + " ns");
            System.out.println("Tempo de Busca: " + simpleSearchTime + " ns");
            
            System.out.println("\nTabela Hash Avançada:");
            System.out.println("Número de Colisoes: " + advancedTable.getCollisions());
            System.out.println("Tempo de Inserção: " + advancedInsertTime + " ns");
            System.out.println("Tempo de Busca: " + advancedSearchTime + " ns");
            
            // Distribuição de chaves
            printDistribution("Simples", simpleTable.calculateDistribution());
            printDistribution("Avançada", advancedTable.calculateDistribution());
            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método para leitura do arquivo CSV
    private static List<String> readNamesFromCSV(String filename) throws IOException {
        List<String> names = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename, StandardCharsets.UTF_8))) {
            
            String line;
            // Pula o cabeçalho se existir
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                // Se for a primeira linha (cabeçalho), ignora
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                // Remove aspas duplas se existirem e faz trim do nome
                String name = line.replace("\"", "").trim();
                
                // Adiciona o nome apenas se não estiver vazio
                if (!name.isEmpty()) {
                    names.add(name);
                }
            }
        }
        
        return names;
    }
    
    // Método auxiliar para imprimir distribuição
    private static void printDistribution(String tableName, int[] distribution) {
        System.out.println("\nDistribuição de Chaves - Tabela " + tableName);
        int maxItemsPerBucket = 0;
        int bucketsWithItems = 0;
        int emptyBuckets = 0;
        
        for (int count : distribution) {
            if (count > 0) {
                bucketsWithItems++;
                maxItemsPerBucket = Math.max(maxItemsPerBucket, count);
            } else {
                emptyBuckets++;
            }
        }
        
        System.out.println("Número de buckets com itens: " + bucketsWithItems);
        System.out.println("Número de buckets vazios: " + emptyBuckets);
        System.out.println("Número máximo de itens em um bucket: " + maxItemsPerBucket);
    }
}