package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ListaEncadeada {
    public Node primeiroNode;
    public Node ultimoNode;
    public String desc;

    public class Node {
        public Object dado;
        public Node proximo;

        public Node(Object o) {
            this(o, null);
        }

        public Node(Object o, Node proximoNode) {
            dado = o;
            proximo = proximoNode;
        }

        public Object getObject() {
            return dado;
        }

        public Node getProximo() {
            return proximo;
        }
    }

    public ListaEncadeada(String s) {
        this.desc = s;
        primeiroNode = ultimoNode = null;
    }

    public ListaEncadeada() {
        this("Unknown");
    }

    public synchronized void inserirNaFrente(Object insertItem) {
        if (estaVazia())
            primeiroNode = ultimoNode = new Node(insertItem);
        else
            primeiroNode = new Node(insertItem, primeiroNode);
    }

    public synchronized void inserirNoFinal(Object insertItem) {
        if (estaVazia())
            primeiroNode = ultimoNode = new Node(insertItem);
        else
            ultimoNode = ultimoNode.proximo = new Node(insertItem);
    }

    public synchronized Object removeDaFrente() throws Exception {
        if (estaVazia())
            throw new Exception(this.desc);

        Object itemRemovido = primeiroNode.dado;

        if (primeiroNode.equals(ultimoNode))
            primeiroNode = ultimoNode = null;
        else
            primeiroNode = primeiroNode.proximo;

        return itemRemovido;
    }

    public synchronized Object removeDoFinal() throws Exception {
        if (estaVazia())
            throw new Exception(this.desc);

        Object itemRemovido = ultimoNode.dado;

        if (primeiroNode.equals(ultimoNode))
            primeiroNode = ultimoNode = null;
        else {
            Node corrente = primeiroNode;

            while (corrente.proximo != ultimoNode)
                corrente = corrente.proximo;

            ultimoNode = corrente;
            corrente.proximo = null;
        }

        return itemRemovido;
    }

    public synchronized boolean estaVazia() {
        return primeiroNode == null;
    }

    public synchronized String toString() {
        Node corrente = primeiroNode;
        StringBuilder ret = new StringBuilder();

        if (estaVazia()) {
            return "{}";
        }

        while (corrente != null) {
            ret.append(corrente.dado.toString()).append("\n");
            corrente = corrente.proximo;
        }

        return ret.toString();
    }

    public boolean localizaNode(Object obj) {
        Node corrente = this.primeiroNode;
        boolean encontrado = false;

        while (corrente != null) {
            if (corrente.dado.equals(obj)) {
                encontrado = true;
                break;
            } else
                corrente = corrente.proximo;
        }
        return encontrado;
    }

    // Retorna o iterador para percorrer a lista na ordem normal
    public Iterator forwardIterator() {
        return new ForwardIterator(primeiroNode);
    }

    // Retorna o iterador para percorrer a lista na ordem reversa
    public Iterator reverseIterator() {
        return new ReverseIterator(primeiroNode);
    }

    // Iterador para percorrer a lista na ordem normal
    private class ForwardIterator implements Iterator {
        private Node currentNode;

        public ForwardIterator(Node startNode) {
            this.currentNode = startNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            Object data = currentNode.dado;
            currentNode = currentNode.proximo;
            return data;
        }
    }

    // Iterador para percorrer a lista na ordem reversa
    private class ReverseIterator implements Iterator {
        private List<Object> items;
        private int currentIndex;

        public ReverseIterator(Node startNode) {
            items = new ArrayList<>();
            Node currentNode = startNode;
            while (currentNode != null) {
                items.add(currentNode.dado);
                currentNode = currentNode.proximo;
            }
            Collections.reverse(items);
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < items.size();
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items.get(currentIndex++);
        }
    }

    public interface Iterator {
        boolean hasNext();
        Object next();
    }
}
