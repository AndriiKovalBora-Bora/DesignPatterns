package com.company.Iterator;

public class Main {

    public static void main(String[] args) {
        Tasks tasks = new Tasks();
        Iterator iterator = tasks.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

interface Iterator {
    boolean hasNext();

    Object next();
}

interface Container {
    Iterator getIterator();
}

class Tasks implements Container {
    private String[] tasks = {"Build house", "Have a son", "Plant a tree"};

    @Override
    public Iterator getIterator() {
        return new TaskIterator();
    }

    private class TaskIterator implements Iterator {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < tasks.length;
        }

        @Override
        public Object next() {
            return tasks[index++];
        }
    }
}
