public abstract class HashAbstrata {
    protected static final int tamanho_tabela = 5000;
    protected String[] tabela;
    protected int colisoes;
    protected int[] colisoesPorPosicao;
    protected int elementCount;

    public HashAbstrata() {
        tabela = new String[tamanho_tabela];
        colisoes = 0;
        colisoesPorPosicao = new int[tamanho_tabela];
        elementCount = 0;
    }

    protected abstract int hash(String key);

    public void insert(String key) {
        if (elementCount >= tamanho_tabela) {
            throw new IllegalStateException("Tabela hash está cheia");
        }
        
        int originalIndex = hash(key);
        int index = originalIndex;
        
        if (tabela[index] != null) {
            colisoes++;
            colisoesPorPosicao[originalIndex]++;
        }
        
        while (tabela[index] != null) {
            index = (index + 1) % tamanho_tabela;
        }
        
        tabela[index] = key;
        elementCount++;
    }

    public boolean search(String key) {
        int index = hash(key);
        int originalIndex = index;
        
        do {
            if (tabela[index] == null) {
                return false;
            }
            if (tabela[index].equals(key)) {
                return true;
            }
            index = (index + 1) % tamanho_tabela;
        } while (index != originalIndex);
        
        return false;
    }

    public int[] indicePorPos() {
        int[] distribuidor = new int[tamanho_tabela];
        for (String item : tabela) {
            if (item != null) {
                int index = hash(item);
                distribuidor[index]++;
            }
        }
        return distribuidor;
    }

    public int getColisoes() {
        return colisoes;
    }
    
    public int[] getColisoesPorPosicao() {
        return colisoesPorPosicao;
    }

    public int getElementCount() {
        return elementCount;
    }
    
    public double getFatorCarga() {
        return (double) elementCount / tamanho_tabela;
    }
}