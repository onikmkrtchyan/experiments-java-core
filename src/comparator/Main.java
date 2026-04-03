package comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Product model
class Product implements Comparable<Product> {
    private String name;
    private double price;
    private double rating;

    public Product(String name, double price, double rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    // Natural ordering: by product name ascending
    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", rating=" + rating + "}";
    }
}

// Custom comparator: sort by price ascending
class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

// Another comparator: sort by name ascending
class ProductNameComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getName().compareTo(p2.getName());
    }
}

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Phone", 800.0, 4.6));
        products.add(new Product("Laptop", 1500.0, 4.8));
        products.add(new Product("Mouse", 40.0, 4.2));
        products.add(new Product("Tablet", 600.0, 4.4));

        System.out.println("Original list:");
        printProducts(products);

        // Sort by default (natural ordering from Comparable)
        products.sort(null);
        System.out.println("\nSorted by default (name ascending):");
        printProducts(products);

        // Sort by price using custom comparator class
        products.sort(new ProductPriceComparator());
        System.out.println("\nSorted by price ascending:");
        printProducts(products);

        // Sort by name using explicit comparator class
        products.sort(new ProductNameComparator());
        System.out.println("\nSorted by name ascending (custom comparator):");
        printProducts(products);

        // Sort by rating descending using lambda
        products.sort((p1, p2) -> Double.compare(p2.getRating(), p1.getRating()));
        System.out.println("\nSorted by rating descending:");
        printProducts(products);

        // Sort by price using built-in comparator helper
        products.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        System.out.println("\nSorted by price descending:");
        printProducts(products);
    }

    private static void printProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}