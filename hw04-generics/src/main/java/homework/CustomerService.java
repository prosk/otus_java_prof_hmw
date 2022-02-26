package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    private final NavigableMap<Customer,String> scoresOrderedMap =
            new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> smallest = scoresOrderedMap.firstEntry();
        Customer customerWithSmallestScore = smallest.getKey();
        Customer copyOfCustomerWithSmallestScore =
                new Customer(customerWithSmallestScore.getId(),
                              customerWithSmallestScore.getName(), customerWithSmallestScore.getScores());
        return Map.entry(copyOfCustomerWithSmallestScore, smallest.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextByScore = scoresOrderedMap.higherEntry(customer);
        if (nextByScore == null) return null;

        Customer nextCustomer = nextByScore.getKey();
        Customer copyOfNextCustomer =
                new Customer(nextCustomer.getId(),
                        nextCustomer.getName(), nextCustomer.getScores());
        return Map.entry(copyOfNextCustomer, nextByScore.getValue());
    }

    public void add(Customer customer, String data) {
        scoresOrderedMap.put(customer, data);
    }
}
