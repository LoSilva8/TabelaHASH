public class Main {
    public static void main(String[] args) {
        // Leitura do arquivo CSV
        List<String> names = readNamesFromCSV("names_5000.csv");
        
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
        System.out.println("Relatório de Comparação de Tabelas Hash");
        System.out.println("------------------------------------");
        System.out.println("Tabela Hash Simples:");
        System.out.println("Número de Colisões: " + simpleTable.getCollisions());
        System.out.println("Tempo de Inserção: " + simpleInsertTime + " ns");
        System.out.println("Tempo de Busca: " + simpleSearchTime + " ns");
        
        System.out.println("\nTabela Hash Avançada:");
        System.out.println("Número de Colisões: " + advancedTable.getCollisions());
        System.out.println("Tempo de Inserção: " + advancedInsertTime + " ns");
        System.out.println("Tempo de Busca: " + advancedSearchTime + " ns");
        
        // Distribuição de chaves
        printDistribution("Simples", simpleTable.calculateDistribution());
        printDistribution("Avançada", advancedTable.calculateDistribution());
    }
    
    // Método para leitura do arquivo CSV (a ser implementado)
    private static List<String> readNamesFromCSV(String filename) {
        // TODO: Implementar leitura do arquivo CSV
        // Por enquanto, retornará uma lista vazia
        return new ArrayList<>();
    }
    
    // Método auxiliar para imprimir distribuição
    private static void printDistribution(String tableName, int[] distribution) {
        System.out.println("\nDistribuição de Chaves - Tabela " + tableName);
        int maxItemsPerBucket = 0;
        int bucketsWithItems = 0;
        
        for (int count : distribution) {
            if (count > 0) {
                bucketsWithItems++;
                maxItemsPerBucket = Math.max(maxItemsPerBucket, count);
            }
        }
        
        System.out.println("Número de buckets com itens: " + bucketsWithItems);
        System.out.println("Número máximo de itens em um bucket: " + maxItemsPerBucket);
    }
}
