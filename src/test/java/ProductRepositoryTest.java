import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.AlreadyExistsException;
import ru.netology.NotFoundException;
import ru.netology.Product;
import ru.netology.repository.ShopRepository;

public class ProductRepositoryTest {

    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(11, "Product1", 300);
    Product product2 = new Product(22, "Product 2", 800);
    Product product3 = new Product(33, "Product 3", 50_000);
    Product product4 = new Product(44, "Product 4", 10_000);
    Product product5 = new Product(55, "Product 5", 100);
    Product product6 = new Product(66, "Product 6", 6500);

    @BeforeEach
    public void setup() {
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);
        repo.save(product5);
        repo.save(product6);
    }


    @Test
    public void shouldAddProduct() {
        Product newProduct = new Product(77, "New Product", 5000);
        repo.add(newProduct);

        Product[] expected = {product1, product2, product3, product4, product5, product6, newProduct};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionRemoveById() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(15);
        });
    }

    @Test
    public void shouldSaveProduct() {
        Product[] expected = {product1, product2, product3, product4, product5, product6};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldExceptionSaveProduct() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(product5);
        });
    }
}