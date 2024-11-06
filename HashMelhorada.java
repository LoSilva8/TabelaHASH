public class HashMelhorada extends HashAbstrata {
    @Override
    protected int hash(String key) {
        long hash = 5381;
        for (char c : key.toCharArray()) {
            hash = ((hash << 5) + hash) + c;
        }
        return Math.abs((int)(hash % tamanho_tabela));
    }
}
