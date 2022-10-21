import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ClassProductList extends ArrayList<Product> {

    ClassProductList() {
        InitializeFromFile();
    }

    void InitializeFromFile() {
        try {
            System.out.println("File Init Successful");
            BufferedReader file;
            String pName, pCat, line;
            String data[];
            int i = 0;
            file = new BufferedReader(new FileReader("src/ProductInfo.txt"));
            while ((line = file.readLine()) != null) {
                data = line.split(":");
                pCat = data[0];
                pName = data[1];
                Product theProduct = new Product(pCat, pName);
                theProduct.setId(i);
                i++;
                //theProduct.setProductName(strProductName);
                add(theProduct);
            }
        } catch (Exception ignored) {
        }
    }

    public void accept(NodeVisitor visitor) {
        visitor.visitClassProductList(this);
    }

}
