package skincare;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductsParameterResolver implements ParameterResolver {

    public ProductsParameterResolver(){
    }

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return Objects.equals(parameter.getParameterizedType().getTypeName(),"java.util.Map<java.lang.String, skincare.Product>");
    }

    @Override
    public Object resolveParameter(final ParameterContext parameterContext,final ExtensionContext extensionContext) throws ParameterResolutionException{
        //return Products;
        ExtensionContext.Store store = extensionContext.getStore(ExtensionContext.Namespace.create(Product.class));

        System.out.println(Product.class);

        return store.getOrComputeIfAbsent("Products",k -> getProducts() // <-- Default creator
        );
    }

    private Object getProducts() {
        Map<String, Product> Products = new HashMap<>();

        Products.put("EltaMD PM Therapy", new Product("EltaMD PM Therapy Facial Moisturizer", "Moisturizer", LocalDate.of(2022, Month.MAY, 8), 39, "dry skin", "Elta MD", "purchased"));

        Products.put("DHC Face Wash", new Product("DHC Face Wash", "Cleanser", LocalDate.of(2023, Month.MAY, 9), 13.5, "dry skin", "DHC", "purchased"));

        Products.put("DHC Cleansing Foam", new Product("DHC Cleansing Foam", "Cleanser", LocalDate.of(2022, Month.NOVEMBER, 7), 12, "oily skin", "DHC", "interested"));

        Products.put("SkinCeuticals C E Ferulic", new Product("SkinCeuticals C E Ferulic", "Serum", LocalDate.of(2022, Month.DECEMBER, 21), 169, "sensitive skin", "Skinceuticals", "purchased"));

        Products.put("EltaMD Skin Recovery Essence Toner", new Product("EltaMD Skin Recovery Essence Toner", "Toner", LocalDate.of(2023, Month.AUGUST, 17), 34, "combination skin", "EltaMD ", "purchased"));

        Products.put("Supergoop! Mineral Lotion SPF50", new Product("Supergoop! PLAY 100% Mineral Lotion SPF50", "Sunscreen", LocalDate.of(2023, Month.JUNE, 16), 36, "sensitive skin", "Supergoop!", "interested"));

        Products.put("CeraVe Sunscreen Stick SPF50", new Product("CeraVe Sunscreen Stick SPF 50", "Sunscreen", LocalDate.of(2024, Month.JULY, 19), 9, "combination skin", "CeraVe", "purchased"));

        Products.put("Glytone Enhance Brightening Complex", new Product("Glytone Enhance Brightening Complex", "Exfoliator", LocalDate.of(2022, Month.AUGUST, 11), 74, "oily skin", "Glytone", "purchased"));

        Products.put("SkinMedica AHA/BHA Exfoliator", new Product("SkinMedica AHA/BHA Exfoliator Cleanser", "Exfoliator", LocalDate.of(2023, Month.JANUARY, 12), 48, "combination skin", "SkinMedica", "purchased"));

        Products.put("CeraVe Moisturizing Cream", new Product("CeraVe Moisturizing Cream ", "Moisturizer", LocalDate.of(2022, Month.JULY, 1), 18.99, "dry skin", "CeraVe", "purchased"));

        return Products;
    }

}
