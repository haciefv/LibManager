package util;

public class MenuUtil {
    public static Byte entryApp() {

        System.out.println(
                """
                        ----------| Library Management Service |----------
                        
                        [0] -> Exit\
                        
                        [1] -> Register\
                        
                        [2] -> Borrow Book\
                        
                        [3] -> Show Users\
                        
                        [4] -> Add Book"""
        );

        return InputUtil.getInstance().inputTypeByte("\nChoose the option: ");
    }
}
