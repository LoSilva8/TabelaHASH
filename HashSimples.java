public class HashSimples extends HashAbstrata {
    @Override
    protected int hash(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash += c;
        }
        return Math.abs(hash % tamanho_tabela);
    }
}