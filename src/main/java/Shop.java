import java.util.List;

public class Shop {
    private final List<Item> listOfItems;

    public Shop(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public void addProduct(Item bottleOfWater) {
        listOfItems.add(bottleOfWater);
    }

}
