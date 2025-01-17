package service.impl;


import enums.Exceptions;
import exceptions.GeneralExceptions;
import service.BookService;
import service.BorrowService;
import service.ManagementService;
import service.UserService;
import util.MenuUtil;

public class ManagementServiceImpl implements ManagementService {
    @Override
    public void management() {
//        UserService userService = new UserServiceImpl();
//        BookService bookService = new BookServiceImpl();
//        BorrowService borrowService = new BorrowServiceImpl();
        while(true){
            try {
                byte option = MenuUtil.entryApp();
                switch (option){
                    case 0 -> System.exit(-1);
//                    case 1 -> userService.register();
//                    case 2 -> userService.addBook();
//                    case 3 -> userService.showAllUser();
//                    case 4 -> userService.borrowBook();
                    default -> throw  new GeneralExceptions(Exceptions.INVALID_OPTION);

                }
            }catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}