public class HashMelhorada extends HashAbstrata {
    @Override
    protected int hash(String key) {
        int hash = 0;
        int peso = 1;
        
        for (char c : key.toCharArray()) {
            hash += c * peso;
            peso = (peso * 31) % tamanho_tabela;
        }
        
        return Math.abs(hash % tamanho_tabela);
    }
}