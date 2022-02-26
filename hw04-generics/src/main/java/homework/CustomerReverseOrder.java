package homework;


import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {

    private final Deque<Customer> customers = new LinkedList<>();

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    // Вариант решения: для возврата в обратном порядке удобно использовать стек

    public void add(Customer customer) {
        customers.addLast(customer);
    }

    public Customer take() {
        return customers.removeLast();
    }
}
