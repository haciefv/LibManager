package util;

public class MenuUtil {

    public static Byte entryApp() {
        System.out.println("""
                                        
                ----------| Library Management Service |----------
                                        
                [1] -> Login as Admin
                [2] -> Login or Register as User
                [3] -> Books List
                [0] -> Exit
                """);
        return InputUtil.getInstance().inputTypeByte("Choose the module: ");
    }

    public static Byte loginOrRegisterAsUserMenu() {
        System.out.println(

                """
                                                
                        ----------| Login or Register as User Menu |----------
                                                
                        [1] -> Register as User
                        [2] -> Login
                        [0] -> Back to Main Menu
                        """);

        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }

    public static Byte loginAsAdmin() {

        System.out.println(

                """
                                                
                        ----------| Admin Login Menu |----------
                                                
                        [1] -> Login Admin
                        [0] -> Back to Main Menu
                        """);

        return InputUtil.getInstance().inputTypeByte("Choose option: ");
    }


    public static Byte adminMenu() {

        System.out.println(

                """
                                                
                        ----------| Admin Menu |----------
                                                
                        [1] -> Update User
                        [2] -> Delete User
                        [3] -> Show All User
                        [4] -> Show Profile
                        [5] -> Book Management
                        [6] -> Logout
                        [0] -> Back to Main Menu
                        """);

        return InputUtil.getInstance().inputTypeByte("Choose option: ");
    }

    public static Byte userMenu() {
        System.out.println("""
                                        
                ----------| User Menu |----------
                                        
                [1] -> Update User
                [2] -> Delete User
                [3] -> Show Profile
                [4] -> Borrow Book
                [5] -> Return Book
                [0] -> Back to Main Menu
                """);
        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }


    public static Byte bookMenu() {
        System.out.println("""
                                        
                ----------| Book Management |----------
                                        
                [1] -> Add Book
                [2] -> Update Book
                [3] -> Delete Book
                [4] -> Show All Books
                [0] -> Back to Main Menu
                """);
        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }

    public static Byte borrowMenu() {
        System.out.println("""
                                        
                ----------| Borrow Management |----------
                                        
                [1] -> (add borrow methods)
                [0] -> Back to Main Menu
                """);
        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }

    public static Byte commonMenu() {
        System.out.println("""
                                        
                ----------| Common Functions |----------
                                        
                [1] -> (add common methods)
                [0] -> Back to Main Menu
                """);
        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }
}
