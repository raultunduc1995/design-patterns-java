package behavioralpatterns;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Iterator is a behavioral design pattern that lets you traverse elements of a collection
 * without exposing its underlying representation (list, stack, tree, etc.).
 */
public final class Iterator {
    public static void main(String[] args) {
        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        System.out.println(integers);
        integers.remove(2);
        System.out.println(integers);
    }

    private static final class LinkedList<T> implements Collection<T> {
        private Node<T> head;
        private int size;

        public LinkedList() {
            head = null;
            size = 0;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NotNull
        @Override
        public java.util.Iterator<T> iterator() {
            return new LinkedListIterator(head);
        }

        @Contract(value = " -> new", pure = true)
        @NotNull
        @Override
        public Object @NotNull [] toArray() {
            return new Object[0];
        }

        @Contract(pure = true)
        @NotNull
        @Override
        public <T1> T1 @Nullable [] toArray(@NotNull T1[] a) {
            return null;
        }

        @Override
        public boolean add(T t) {
            if (t == null)
                return false;
            head = new Node<>(t, head);
            size++;
            return true;
        }

        @Override
        public boolean remove(Object o) {
            if (o == null || head == null)
                return false;
            if (head.getValue() == o) {
                head = head.getNext();
                size--;
                return true;
            }
            return findAndRemoveObject(o);
        }

        private boolean findAndRemoveObject(Object o) {
            Node<T> previous = head;
            Node<T> current = head.getNext();
            while (current != null) {
                if (current.getValue() == o) {
                    previous.setNext(current.getNext());
                    size--;
                    return true;
                }
                previous = current;
                current = current.getNext();
            }
            return false;
        }

        @Override
        public boolean containsAll(@NotNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NotNull Collection<? extends T> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NotNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NotNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {
            size = 0;
            head = null;
        }

        @Contract(pure = true)
        @Override
        public @NotNull String toString() {
            var stringBuilder = new StringBuilder();
            stringBuilder.append("Numbers: [");
            var current = head;
            while (current != null) {
                stringBuilder.append(current.getValue());
                if (current.getNext() != null)
                    stringBuilder.append(", ");
                current = current.getNext();
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        }

        private final class LinkedListIterator implements java.util.Iterator<T> {
            private Node<T> current;

            private LinkedListIterator(Node<T> current) {
                this.current = current;
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public @Nullable T next() {
                if (current == null)
                    return null;
                T value = current.getValue();
                current = current.getNext();
                return value;
            }
        }

        private final class Node<T> {
            private T value;
            private Node<T> next;

            private Node(T value, Node<T> next) {
                this.value = value;
                this.next = next;
            }

            public T getValue() {
                return value;
            }

            public void setValue(T value) {
                this.value = value;
            }

            public Node<T> getNext() {
                return next;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }
        }
    }
}
