import sun.plugin.dom.exception.InvalidStateException;

import java.io.IOException;
import java.util.List;

public class Shop {
    private final List<Item> listOfItems;
    private final SoldItemsDBI ourDatabase;
    private final DiscountCheckerServiceI ourDiscountChecker;

    public Shop(List<Item> listOfItems, SoldItemsDBI ourDatabase, DiscountCheckerServiceI ourDiscountChecker) {
        this.listOfItems = listOfItems;
        this.ourDatabase = ourDatabase;
        this.ourDiscountChecker = ourDiscountChecker;
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
        ourDatabase.saveItem(result);
        listOfItems.remove(result);
        return result;
    }

    public boolean wasItemSold(Item item) {
        boolean result = false;
        try {
            result = ourDatabase.isInDatabase(item);
        } catch (IOException exception){
            throw new InvalidStateException("DB connection is lost.");
        }
        return result;
    }

    public int isAnyExtraDiscount(String itemName){

        return ourDiscountChecker.howMuchDiscount(new Item(itemName, 3));
    }
}
