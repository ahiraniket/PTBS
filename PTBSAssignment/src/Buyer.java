public class Buyer extends Person {

    Buyer() {
        type = 0;
    }

    public void showMenu() {


    }

    public ProductMenu CreateProductMenu(int category) {
        if (category == 0) {
            productMenu = new MeatProductMenu();
        } else {
            productMenu = new ProduceProductMenu();
        }
        return productMenu;
    }

}
