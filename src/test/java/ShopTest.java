import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import sun.plugin.dom.exception.InvalidStateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ShopTest {

    public static final String NAME_OF_BOTTLE_OF_WATER = "Bottle of water";
    public static final int PRICE_OF_WATER = 2;
    public static final Item BOTTLE_OF_WATER = new Item(NAME_OF_BOTTLE_OF_WATER, PRICE_OF_WATER);
    private Shop shop;

    private List<Item> listOfItems;

    private SoldItemsDBI ourDatabase;
    private DiscountCheckerServiceI ourDiscountChecker;

    @Before //Method annotated like this will be called before every test
    public void setUp() throws Exception {
        listOfItems = new ArrayList<>();
        ourDatabase = Mockito.mock(SoldItemsDBI.class);
        shop = new Shop(listOfItems, ourDatabase, ourDiscountChecker);
        ourDiscountChecker = Mockito.mock(DiscountCheckerServiceI.class);
    }

    @Test //This annotation mark our method as a test.
    public void shouldCreateNonNullObject() {
        assertThat(shop).isNotNull();
    }

    @Test
    public void shouldBeAbleToAddProductsToShop() {
        shop.addProduct(BOTTLE_OF_WATER);

        assertThat(listOfItems.get(0)).isNotNull();
    }

    @Test
    public void shouldRemoveItemWhenItWasSold(){
        shop.addProduct(BOTTLE_OF_WATER);

        Item result = shop.sellItem(NAME_OF_BOTTLE_OF_WATER);

        assertThat(result).isEqualTo(BOTTLE_OF_WATER);
        assertThat(listOfItems).doesNotContain(BOTTLE_OF_WATER);
    }

    @Test
    public void shouldReturnNullWhenItemNotExists(){
        Item result = shop.sellItem(NAME_OF_BOTTLE_OF_WATER);

        assertThat(result).isNull();
    }

    @Test
    public void shouldSaveSoldItemToDatabaseWhenSellItemCalled(){
        shop.addProduct(BOTTLE_OF_WATER);

        Item result = shop.sellItem(NAME_OF_BOTTLE_OF_WATER);

        Mockito.verify(ourDatabase).saveItem(ArgumentMatchers.any(Item.class));
    }

    @Test
    public void shouldReturnTrueWhenItemWasSold() throws IOException {
        Mockito.when(ourDatabase.isInDatabase(BOTTLE_OF_WATER)).thenReturn(true);

        boolean result = shop.wasItemSold(BOTTLE_OF_WATER);

        Mockito.verify(ourDatabase).isInDatabase(BOTTLE_OF_WATER);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenItemWasNotSold(){
        boolean result = shop.wasItemSold(BOTTLE_OF_WATER);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenIsInDatabaseReturnFalse() throws IOException {
        Mockito.when(ourDatabase.isInDatabase(BOTTLE_OF_WATER)).thenReturn(false);

        boolean result =shop.wasItemSold(BOTTLE_OF_WATER);

        assertThat(result).isFalse();

    }

    @Test(expected = InvalidStateException.class)
    public void shouldThrowExceptionWhenConnectionToDBisLost() throws IOException {
        Mockito.when(ourDatabase.isInDatabase(ArgumentMatchers.any(Item.class)))
                .thenThrow(IOException.class);

        shop.wasItemSold(BOTTLE_OF_WATER);
    }

    @Test
    public void shouldReturnMinusOneWhenDiscountCheckerReturnsMinusOne(){
        Mockito.when(ourDiscountChecker.howMuchDiscount(ArgumentMatchers.any(Item.class))).thenReturn(-1);
        int result = shop.isAnyExtraDiscount(NAME_OF_BOTTLE_OF_WATER);

        assertThat(result).isEqualTo(-1);

    }

    @Test
    public void shouldReturnPositiveValueWhenDiscountCheckerReturnsPositiveValue(){
        Mockito.when(ourDiscountChecker.howMuchDiscount(ArgumentMatchers.any(Item.class))).thenReturn(1);
        int result = shop.isAnyExtraDiscount(NAME_OF_BOTTLE_OF_WATER);

        assertThat(result).isEqualTo(1);

    }


}