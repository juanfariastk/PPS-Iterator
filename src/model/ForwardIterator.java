package model;

import java.util.NoSuchElementException;

public class ForwardIterator<Object> implements Iterator {
    private ListaEncadeada.Node currentNode;

    public ForwardIterator(ListaEncadeada.Node startNode) {
        this.currentNode = startNode;
    }

    @Override
    public boolean hasNext() {
        return currentNode != null;
    }

    @Override
    public Object next() {
        if (!hasNext()) throw new NoSuchElementException();
        Object  data = (Object) currentNode.dado;
        currentNode = currentNode.proximo;
        return data;
    }
}
