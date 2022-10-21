public class PTBSMainClass {
    static Facade facade = new Facade();
    static Login login = new Login();
    static UserInfoItem userInfoItem = new UserInfoItem();
    public static void main(String[] args) {
        if(facade.login(userInfoItem))
        {
            System.out.println("Login Successful " + userInfoItem.UserType);
            facade.SelectProduct();
            facade.productOperation(userInfoItem);
        }

    }
}
