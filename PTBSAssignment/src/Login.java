import java.io.*;
import java.util.Scanner;

public class Login {
    private String uname, password;
    int user_type;

    private UserInfoItem.USER_TYPE UserType = UserInfoItem.USER_TYPE.Buyer;


    public boolean getLogin() {

        boolean login = true;
        Scanner sc = new Scanner(System.in);
        while (login) {
            System.out.print("\n===== Login =====");
            System.out.print("\n Username:");
            uname = sc.nextLine();

            System.out.print("\n Password:");
            password = sc.nextLine();

            System.out.print("\n User Type:\n1. Buyer\n2. Seller\n(0 -> Exit)\nEnter your choice[1-2]:");
            user_type = sc.nextInt();
            switch (user_type) {
                case 1:
                    login = false;
                    UserType = UserInfoItem.USER_TYPE.Buyer;
                    break;
                case 2:
                    UserType = UserInfoItem.USER_TYPE.Seller ;
                    login = false;
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Enter Valid Choice!!!");
                    break;
            }
            if (checkUser(uname, password, user_type)) {
                login = false;
                return true;
            }
            else
            {
                System.out.println("Incorrect ID/Password!!!");
                login = true;
            }
        }
        return false;
    }

    public boolean checkUser(String un, String pwd, int role) {
        boolean valid = false;
        FileReader fr = null;
        try {
            if (role == 1) {

                fr = new FileReader("src/BuyerInfo.txt");
            } else {
                fr = new FileReader("src/SellerInfo.txt");
            }

            String line;
            String data[] = new String[2];
            BufferedReader bufferedReader = new BufferedReader(fr);
            while ((line = bufferedReader.readLine()) != null) {
                data = line.split(":");
                if (data[0].compareTo(un) == 0 && data[1].compareTo(pwd) == 0) {
                    fr.close();
                    bufferedReader.close();
                    return true;
                }
            }
            fr.close();
            bufferedReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return valid;
    }

    public UserInfoItem.USER_TYPE getUserType() {
        return UserType;
    }

    public int getUser_type() {
        return user_type;
    }

    public String getUname() {
        return uname;
    }
}
