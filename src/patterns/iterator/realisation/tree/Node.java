package patterns.iterator.realisation.tree;

public class Node <T> {
    T value;
    Node <T> left;
    Node <T> right;

public Node (T value) {this.value = value;}
}
