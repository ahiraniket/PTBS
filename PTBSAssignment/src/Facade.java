import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Facade {

    private Login login = new Login();


    private Product theSelectedProduct = null;

    private int nProductCategory = 0;

    private static ClassProductList theProductList = new ClassProductList();

    private Person thePerson;

    public boolean login(UserInfoItem userInfoItem) {
        if (login.getLogin()) {
            userInfoItem.UserType = login.getUserType();
            userInfoItem.strUserName = login.getUname();
            thePerson.UserName = login.getUname();
            thePerson.type = login.user_type;
            return true;
        } else {
            return false;
        }

    }

    public void addTrading(Product product) {
        TradingMenu tradingMenu;
        if (thePerson.type == 0) {
            tradingMenu = new BuyerTradingMenu();
        } else {
            tradingMenu = new SellerTradingMenu();
        }
        Trading trading = new Trading();
        tradingMenu.ShowMenu(trading, thePerson);
        product.addTrading(trading);


    }

    public void viewTrading(Trading trading) {
        TradingMenu tradingMenu;
        if (thePerson.type == 0) {
            tradingMenu = new BuyerTradingMenu();
        } else {
            tradingMenu = new SellerTradingMenu();
        }
        tradingMenu.ShowMenu(trading, thePerson);
    }

    public void decideBidding() {

    }

    public void discussBidding() {

    }

    public void remind() {
        theProductList = new ClassProductList();
        theProductList.InitializeFromFile();

    }

    public void createUser(UserInfoItem userinfoitem) {

    }

    public void createProductList() {

    }

    public void AttachProductToUser() {
        BufferedReader file;
        try {
            file = new BufferedReader(new FileReader("src/UserProduct.txt"));
            String aline, strUserName, strProductName;
            String data[] = new String[2];
            while ((aline = file.readLine()) != null) {
                data = aline.split(":");
                strUserName = data[0];
                strProductName = data[1];
                if (strUserName.compareTo(thePerson.UserName) == 0) {
                    theSelectedProduct = FindProductByProductName(strProductName);
                    if (theSelectedProduct != null) {
                        thePerson.addProduct(theSelectedProduct);
                    }
                }
            }
        } catch (Exception ignored) {

        }

    }

    public void SelectProduct() {
        ProductIterator iterator = new ProductIterator(theProductList);
        Product product = (Product) iterator.next();
        System.out.println("\tID  | Product Category | Product Name");
        while (product != null) {
            System.out.println("\t " + product.getId() + " \t|\t" + product.toStringPCat() + "\t\t   |\t" + product.toStringPName());
            product = (Product) iterator.next();
        }
    }

    public void productOperation(UserInfoItem userInfoItem) {
        int choice = 0;
        Trading trading = new Trading();
        if (userInfoItem.UserType == UserInfoItem.USER_TYPE.Buyer) {

            BuyerTradingMenu bmenu = new BuyerTradingMenu();
            System.out.println("Operations:\n1. Buy\t2. Logout Enter your Choice[1-2]:");
            Scanner in = new Scanner(System.in);
            in.nextInt();
            switch (choice) {
                case 1:
                    bmenu.ShowMenu(trading, thePerson);
                    break;
                case 2:
                    break;
                default:
                    System.out.println();
            }
        }


    }

    public Product FindProductByProductName(String strProduct) {
        ProductIterator iterator = new ProductIterator(theProductList);
        return (Product) iterator.next(strProduct);
    }

}
