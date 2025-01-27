package util;

public class MenuUtil {

    public static Byte entryApp() {
        System.out.println(
                """
                        
                        ----------| Library Management Service |----------
                        
                        [0] -> Exit
                        [1] -> Login as Admin
                        [2] -> Login or Register as User
                        [3] -> Book Management
                        [4] -> Borrow Management
                        [5] -> Common Functions
                        """
        );
        return InputUtil.getInstance().inputTypeByte("Choose the module: ");
    }

    public static Byte loginOrRegisterAsUserMenu() {
        System.out.println(

                """
                        
                        ----------| Login or Register as User Menu |----------
                        
                        [0] -> Back to Main Menu
                        [1] -> Register as User
                        [2] -> Login
                        """
        );

        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }

    public static Byte loginAsAdmin() {

        System.out.println(

                """
                        
                        ----------| Admin Login Menu |----------
                        
                        [0] -> Back to Main Menu
                        [1] -> Login Admin
                        """
        );

        return InputUtil.getInstance().inputTypeByte("Choose option: ");
    }


    public static Byte adminMenu() {

        System.out.println(

                """
                        
                        ----------| Admin Menu |----------
                        
                        [0] -> Back to Main Menu
                        [1] -> Update User
                        [2] -> Delete User
                        [3] -> Show All User
                        [4] -> Show Profile
                        """
        );

        return InputUtil.getInstance().inputTypeByte("Choose option: ");
    }

    public static Byte userMenu() {
        System.out.println(
                """
                        
                        ----------| User Menu |----------
                        
                        [0] -> Back to Main Menu
                        [1] -> Update User
                        [2] -> Delete User
                        [3] -> Show Profile
                        """
        );
        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }


    public static Byte bookMenu() {
        System.out.println(
                """
                        
                        ----------| Book Management |----------
                        
                        [0] -> Back to Main Menu
                        [1] -> (add book methods)
                        """
        );
        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }

    public static Byte borrowMenu() {
        System.out.println(
                """
                        
                        ----------| Borrow Management |----------
                        
                        [0] -> Back to Main Menu
                        [1] -> Borrow Book
                        [2] -> Return Book
                        [3] -> Show All Books
                        [4] -> Show Overdue Books
                        [5] -> Show Overdue Fee
                        [6] -> Show Total Cost
                        [7] -> Update Borrow Record
                        [8] -> Delete Borrow Record
                        """
        );
        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }

    public static Byte commonMenu() {
        System.out.println(
                """
                        
                        ----------| Common Functions |----------
                        
                        [0] -> Back to Main Menu
                        [1] -> (add common methods)
                        """
        );
        return InputUtil.getInstance().inputTypeByte("Choose the option: ");
    }
}
