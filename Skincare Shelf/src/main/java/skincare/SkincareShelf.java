package skincare;

import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class SkincareShelf {

    private final List<Product> products = new ArrayList<>();

    public List<Product> products() {
        return Collections.unmodifiableList(products);
    }

    // method to add skincare products to the shelf
    public void add(Product... productsToAdd){
        Arrays.stream(productsToAdd).forEach(products::add);
    }

    public List<Product> arrange() {
        return arrange(Comparator.naturalOrder());
    }
    public List<Product> arrange(Comparator<Product> criteria) {
        return products.stream().sorted(criteria).collect(Collectors.toList());
    }

    public <K> Map<K, List<Product>> groupBy(Function<Product, K> fx) {
        return products.stream().collect(groupingBy(fx));
    }

    public Map<Year, List<Product>> groupByExpiryYear() {
        return groupBy(Product->Year.of(Product.getExpiryDate().getYear()));
    }

    public Map<Month, List<Product>> groupByExpiryMonth() {
        return groupBy(Product->Month.of(Product.getExpiryDate().getMonthValue()));
    }

    public Map<String, List<Product>> groupByFirstAlphabet() {
        return groupBy(Product->String.valueOf(Product.getName().toUpperCase().charAt(0)));
    }

}
