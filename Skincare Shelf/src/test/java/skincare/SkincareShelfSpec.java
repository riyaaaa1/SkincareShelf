package skincare;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("<= SkincareShelf Specification =>")
@ExtendWith(ProductsParameterResolver.class)

public class SkincareShelfSpec extends DBConnectionPool {

    private SkincareShelf shelf;

    //ten skincare products
    private Product eltaTherapy;
    private Product dhcFaceWash;
    private Product dhcCleansingFoam;
    private Product skinceuticalsCSerum;
    private Product eltaMDEssenceToner;
    private Product supergoopMineralLotion;
    private Product ceraveSunscreenStick;
    private Product glytoneBrightening;
    private Product skinmedicaExfoliator;
    private Product ceraveMoisturizingCream;

    // First Test Constructor
    private SkincareShelfSpec(TestInfo testInfo){
        System.out.println("Working on test "+ testInfo.getDisplayName());
    }

    @BeforeEach
    void init(Map<String, Product> products){
        shelf = new SkincareShelf();

        this.eltaTherapy = products.get("EltaMD PM Therapy");
        this.dhcFaceWash = products.get("DHC Face Wash");
        this.dhcCleansingFoam = products.get("DHC Cleansing Foam");
        this.skinceuticalsCSerum = products.get("SkinCeuticals C E Ferulic");
        this.eltaMDEssenceToner = products.get("EltaMD Skin Recovery Essence Toner");
        this.supergoopMineralLotion = products.get("Supergoop! Mineral Lotion SPF50");
        this.ceraveSunscreenStick = products.get("CeraVe Sunscreen Stick SPF50");
        this.glytoneBrightening = products.get("Glytone Enhance Brightening Complex");
        this.skinmedicaExfoliator = products.get("SkinMedica AHA/BHA Exfoliator");
        this.ceraveMoisturizingCream = products.get("CeraVe Moisturizing Cream");
        
        System.out.println("<= Shelf is initialized. =>");
    }

    @Nested
    @DisplayName("Skincare Shelf is Empty")
    class isEmpty{

        @Test
        @DisplayName("when no skincare product is added to it")
        public void emptySkincareShelfWhenNoProductAdded(){
            List <Product> products = shelf.products();
            assertTrue (products.isEmpty(), () -> "Skincare Shelf should be empty.");
        }

        @Test
        @DisplayName("when add is called without products")
        void emptySkincareShelfWhenAddIsCalledWithoutProducts(){
            shelf.add();
            List<Product> products = shelf.products();
            assertTrue(products.isEmpty(), ()->"Bookshelf should be empty." );
        }
    }

    @Nested
    @DisplayName("after adding products")

    class ProductsAreAdded{
        //Test when multiple products are added
        @DisplayName("When five products are added in the Product shelf.")
        @Test
        void productShelfContainsFiveProductsWhenFiveProductsAdded() {
            shelf.add(eltaMDEssenceToner, eltaTherapy, ceraveMoisturizingCream, skinceuticalsCSerum, supergoopMineralLotion);
            List<Product> Products = shelf.products();
            assertEquals(5, Products.size(), () -> "ProductShelf should have five products.");
        }

        @DisplayName("Products returned from skincare shelf is immutable for client.")
        @Test
        void productsReturnedFromSkincareShelfIsImmutableForClient(){
            shelf.add(eltaMDEssenceToner, eltaTherapy, ceraveMoisturizingCream, skinceuticalsCSerum, supergoopMineralLotion);
            List<Product> Products = shelf.products();
            try{
                Products.add(ceraveSunscreenStick);
                fail(() -> "Should not be able to add product to Skincare shelf");
            } catch(Exception e){
                assertTrue(e instanceof UnsupportedOperationException, ()-> "Should throw UnsupportedOperationException.");
            }
        }
    }

    @Nested
    @DisplayName("is arranged")

    class IsArranged{

        // Test Case to arrange the products on certain criteria
        @DisplayName("Arranging the Products")
        @Test
        void skincareShelfArrangedByProductName(){
            shelf.add(eltaTherapy, ceraveMoisturizingCream, skinceuticalsCSerum, supergoopMineralLotion,dhcCleansingFoam);
            List <Product> products = shelf.arrange();
            assertEquals(asList(ceraveMoisturizingCream, dhcCleansingFoam, eltaTherapy, skinceuticalsCSerum,supergoopMineralLotion), //expected value
                    products, //actual/real value
                    ()->"Books in a bookshelf should be arranged by name"); //error message when actual and expected does not match
        }

        @DisplayName("Products in Skincare shelf retain their order after calling arrange() method." )
        @Test
        void productsInSkincareShelfAreInInsertionOrderAfterCallingArrange(){
            shelf.add(supergoopMineralLotion, dhcCleansingFoam, eltaTherapy, ceraveMoisturizingCream, skinceuticalsCSerum);
            shelf.arrange();
            List<Product> products = shelf.products();
            assertEquals(asList(supergoopMineralLotion, dhcCleansingFoam, eltaTherapy, ceraveMoisturizingCream, skinceuticalsCSerum),
                    products,
                    () -> "Products in Skincare shelf are in inserted order");
        }

        @DisplayName("Skincare shelf can be arranged by user provided criteria. Name descending")
        @Test
        void skincareShelfArrangedByUserDescending(){
            shelf.add(supergoopMineralLotion, dhcCleansingFoam, eltaTherapy, ceraveMoisturizingCream, skinceuticalsCSerum);
            List <Product> products = shelf.arrange(Comparator.<Product>naturalOrder().reversed());
            assertEquals(asList(supergoopMineralLotion, skinceuticalsCSerum,eltaTherapy, dhcCleansingFoam,ceraveMoisturizingCream), products, () -> "Products in a Skincare shelf are arranged in a descending order"+ "of Product name");
        }

//        @DisplayName("Skincare shelf can be arranged by user provided criteria. Price ascending")
//        @Test
//        void skincareShelfArrangedByPriceAscending(){
//            shelf.add(supergoopMineralLotion, dhcCleansingFoam, eltaTherapy, ceraveMoisturizingCream, skinceuticalsCSerum );
//            List <Product> Products = shelf.arrange();
//            assertEquals(asList(codeComplete, effectiveJava, theManOnTheMoon), Products, () -> "Products in a Skincare Shelf are arranged in a descending order"+ "of Product name");
//        }

        @DisplayName("Skincare Shelf can be arranged by expiry date. Ascending")
        @Test
        void skincareShelfArrangedByExpiryDate(){
            shelf.add(supergoopMineralLotion, dhcCleansingFoam, eltaTherapy, ceraveMoisturizingCream, skinceuticalsCSerum );
            List <Product> Products = shelf.arrange((b1, b2) -> b1.getExpiryDate().compareTo(b2.getExpiryDate()));
            assertEquals(asList(eltaTherapy, ceraveMoisturizingCream, dhcCleansingFoam,skinceuticalsCSerum, supergoopMineralLotion), Products, ()-> "Products in a Skincare shelf are arranged by the Product expiry date in ascending order.");
        }

        //AssertJ example
//        @DisplayName("Products are arranged by title")
//        @Test
//        void ProductshelfArrangedByUserAssertJ(){
//            shelf.add(effectiveJava, codeComplete, theManOnTheMoon );
//            Comparator<Product> reversed_criteria = Comparator.<Product>naturalOrder().reversed();
//            List <Product> Products = shelf.arrange(reversed_criteria);
//            assertThat(Products).isSortedAccordingTo(reversed_criteria);
//        }
    }

    @Nested
    @DisplayName("is Grouped")

    class IsGrouped{

        @DisplayName("Products inside skincare shelf are grouped by expiry year")
        @Test
        void groupProductInsideSkincareShelfByExpiryYear(){
            shelf.add(skinmedicaExfoliator,dhcFaceWash,ceraveSunscreenStick,glytoneBrightening);
            Map<Year, List<Product>> productsByExpiryYear = shelf.groupByExpiryYear();
            assertThat(productsByExpiryYear)
                    .containsKey(Year.of(2023))
                    .containsValues(Arrays.asList(skinmedicaExfoliator, dhcFaceWash));
            assertThat(productsByExpiryYear).containsKey(Year.of(2024)).containsValues(singletonList(ceraveSunscreenStick));
            assertThat(productsByExpiryYear). containsKey(Year.of(2022)).containsValues(singletonList(glytoneBrightening));
        }

        @Test
        @DisplayName("Products inside skincare shelf are grouped according to user" +" provided criteria(group by brand name)")
        void groupProductsByUserProvidedCriteria() {
            shelf.add(skinmedicaExfoliator,dhcFaceWash,dhcCleansingFoam, ceraveSunscreenStick, ceraveMoisturizingCream, glytoneBrightening);
            Map<String, List<Product>> productsByBrand = shelf.groupBy(Product::getBrand);
            assertThat(productsByBrand)
                    .containsKey("SkinMedica")
                    .containsValues(singletonList(skinmedicaExfoliator));
            assertThat(productsByBrand)
                    .containsKey("CeraVe")
                    .containsValues(Arrays.asList(ceraveSunscreenStick, ceraveMoisturizingCream));
            assertThat(productsByBrand)
                    .containsKey("Glytone")
                    .containsValues(singletonList(glytoneBrightening));
        }

        @Test
        @DisplayName("Products inside skincare shelf are grouped according to user" +" provided criteria(group by expiry month)")
        void groupProductsByExpiryMonth(){
            shelf.add(supergoopMineralLotion,eltaTherapy,dhcCleansingFoam, ceraveSunscreenStick, ceraveMoisturizingCream, glytoneBrightening, skinceuticalsCSerum);

            Map<Month, List<Product>> productsByExpiryMonth = shelf.groupByExpiryMonth();
            assertThat(productsByExpiryMonth).containsKey(Month.MAY).containsValues(singletonList(eltaTherapy));
            assertThat(productsByExpiryMonth).containsKey(Month.JUNE).containsValues(singletonList(supergoopMineralLotion));
            assertThat(productsByExpiryMonth).containsKey(Month.NOVEMBER).containsValues(singletonList(dhcCleansingFoam));
            assertThat(productsByExpiryMonth).containsKey(Month.DECEMBER).containsValues(singletonList(skinceuticalsCSerum));
        }

        @Test
        @DisplayName("Products inside skincare shelf are grouped according to user" +" provided criteria(group by first alphabet of the product name)")
        void groupProductsByNameFirstAlphabet(){
            shelf.add(supergoopMineralLotion,eltaTherapy,dhcCleansingFoam, ceraveSunscreenStick, ceraveMoisturizingCream, glytoneBrightening);
            Map<String, List<Product>> ProductsByFirstAlphabet = shelf.groupByFirstAlphabet() ;
            assertThat(ProductsByFirstAlphabet).containsKey("C").containsValues(Arrays.asList(ceraveSunscreenStick, ceraveMoisturizingCream));
            assertThat(ProductsByFirstAlphabet).containsKey("S").containsValues(singletonList(supergoopMineralLotion));
            assertThat(ProductsByFirstAlphabet).containsKey("G").containsValues(singletonList(glytoneBrightening));
        }
    }

    @AfterEach
    void deleteShelfFromDB() {
        System.out.println("<= Cart from DB Deleted. =>");
    }
}
