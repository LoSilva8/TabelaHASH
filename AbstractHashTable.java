// Classe abstrata para a Tabela Hash
public abstract class AbstractHashTable {
    protected static final int TABLE_SIZE = 5003; // Número primo próximo a 5000
    protected String[] table;
    protected int collisions;

    public AbstractHashTable() {
        table = new String[TABLE_SIZE];
        collisions = 0;
    }

    // Método abstrato para a função hash
    protected abstract int hash(String key);

    // Método para inserir uma chave na tabela
    public void insert(String key) {
        int index = hash(key);
        
        // Tratamento de colisões por endereçamento linear
        while (table[index] != null) {
            collisions++;
            index = (index + 1) % TABLE_SIZE;
        }
        
        table[index] = key;
    }

    // Método para buscar uma chave
    public boolean search(String key) {
        int index = hash(key);
        int originalIndex = index;
        
        while (table[index] != null) {
            if (table[index].equals(key)) {
                return true;
            }
            index = (index + 1) % TABLE_SIZE;
            
            // Se completamos um ciclo na tabela
            if (index == originalIndex) {
                break;
            }
        }
        
        return false;
    }

    // Método para calcular a distribuição de chaves
    public int[] calculateDistribution() {
        int[] distribution = new int[TABLE_SIZE];
        for (String item : table) {
            if (item != null) {
                int index = hash(item);
                distribution[index]++;
            }
        }
        return distribution;
    }

    // Getters para relatório
    public int getCollisions() {
        return collisions;
    }
}
