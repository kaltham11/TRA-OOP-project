package InterfaceClasses;

public interface Editable<T> {
    T edit();
    void validate(T entity);
}
