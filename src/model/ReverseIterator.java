package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ReverseIterator<Object> implements Iterator {
    private List<Object> items;
    private int currentIndex;

    public ReverseIterator(ListaEncadeada.Node startNode) {
        items = new ArrayList<>();
        ListaEncadeada.Node currentNode = startNode;
        while (currentNode != null) {
            items.add((Object) currentNode.dado);
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

