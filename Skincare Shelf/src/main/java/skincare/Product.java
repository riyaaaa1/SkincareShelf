package skincare;

import java.time.LocalDate;

public class Product implements Comparable<Product> {

    private final String name;
    private final String productType;
    private final LocalDate expiryDate;
    private final double price;
    private final String skinType;
    private final String brand;
    private final String tag;

    public Product (String name, String productType, LocalDate expiryDate, double price, String skinType, String brand, String tag ){
        this.name = name;
        this.productType = productType;
        this.expiryDate = expiryDate;
        this.price = price;
        this.skinType = skinType;
        this.brand = brand;
        this.tag = tag;

//        this.startedReadingOn = LocalDate.parse("1901-01-01");
//        this.finishedReadingOn = LocalDate.parse("1901-01-01");
    }

    // Get Methods

    public String getName(){
        return name;
    }

    public String getProductType(){
        return productType;
    }

    public LocalDate getExpiryDate(){
        return expiryDate;
    }

    public double getPrice(){
        return price;
    }

    public String  getSkinType(){
        return skinType;
    }

    public String getBrand(){
        return brand;
    }

    public String getTag() {
        return tag;
    }

    @Override

    public String toString() {

        return "Product{" +

                "name='" + name + '\'' +

                ", productType ='" + productType + '\'' +

                ", expiryDate=" + expiryDate + '\'' +

                ", price ='" + price + '\'' +

                ", skinType=" + skinType + '\'' +

                ", brand=" + brand + + '\'' +

                ", tag=" + tag +

                '}';
    }

    @Override
    public int compareTo(Product that) {
        return this.name.compareTo(that.name);
    }



}
