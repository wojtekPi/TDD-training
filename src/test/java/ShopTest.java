import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ShopTest {

    private Shop shop;

    @Before
    public void setUp() throws Exception {
    }

    @Test //This annotation mark our method as a test.
    public void shouldCreateNonNullObject(){
        shop = new Shop(null);
        assertThat(shop).isNotNull();
    }

    @Test
    public void shouldBeAbleToAddProductsToShop(){
        List<Item> listOfItems = new ArrayList<Item>();
        shop = new Shop(listOfItems);
        Item bottleOfWater = new Item("Bottle of water", 2);

        shop.addProduct(bottleOfWater);
        assertThat(listOfItems.get(0)).isNotNull();

    }



}