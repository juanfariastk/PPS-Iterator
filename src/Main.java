import model.*;
public class Main {
    public static void main(String[] args) {
        // Criação da lista encadeada e adição de elementos
        ListaEncadeada lista = new ListaEncadeada("Minha Lista");

        lista.inserirNaFrente("Elemento 1");
        lista.inserirNaFrente("Elemento 2");
        lista.inserirNoFinal("Elemento 3");
        lista.inserirNoFinal("Elemento 4");

        System.out.println("Iteração na ordem natural:");
        ListaEncadeada.Iterator forwardIterator = lista.forwardIterator();
        while (forwardIterator.hasNext()) {
            System.out.println(forwardIterator.next());
        }

        System.out.println("\nIteração na ordem reversa:");
        ListaEncadeada.Iterator reverseIterator = lista.reverseIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }
    }
}