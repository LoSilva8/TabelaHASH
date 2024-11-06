public abstract class HashAbstrata {
    protected static final int tamanho_tabela = 5000;
    protected String[] tabela;
    protected int colisoes;
    protected int[] colisoesPorPosicao;

    public HashAbstrata() {
        tabela = new String[tamanho_tabela];
        colisoes = 0;
        colisoesPorPosicao = new int[tamanho_tabela];
    }

    protected abstract int hash(String key);

    public void insert(String key) {
        int index = hash(key);
        
        while (tabela[index] != null) {
            colisoes++;
            colisoesPorPosicao[index]++;
            index = (index + 1) % tamanho_tabela;
        }
        
        tabela[index] = key;
    }

    public boolean search(String key) {
        int index = hash(key);
        int originalIndex = index;
        
        while (tabela[index] != null) {
            if (tabela[index].equals(key)) {
                return true;
            }
            index = (index + 1) % tamanho_tabela;
            
            if (index == originalIndex) {
                break;
            }
        }
        
        return false;
    }

    public int[] calculateDistribution() {
        int[] distribution = new int[tamanho_tabela];
        for (String item : tabela) {
            if (item != null) {
                int index = hash(item);
                distribution[index]++;
            }
        }
        return distribution;
    }

    public int getColisoes() {
        return colisoes;
    }
    
    public int[] getColisoesPorPosicao() {
        return colisoesPorPosicao;
    }
}