import java.util.List;

public class Shop {
    private final List<Item> listOfItems;

    public Shop(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public void addProduct(Item item) {
        listOfItems.add(item);
    }

    public Item sellItem(String nameOdSoldItem) {
        Item result = null;
        for(Item item: listOfItems){
            if(item.getName().equals(nameOdSoldItem)){
                result = item;
            }
        }
        listOfItems.remove(result);
        return result;
    }
}
