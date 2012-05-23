
package nettower.iterator;

public interface Iterator {
    Object first();
    Object current();
    Object next();
    boolean hasNext();
    Iterator clone();
}
