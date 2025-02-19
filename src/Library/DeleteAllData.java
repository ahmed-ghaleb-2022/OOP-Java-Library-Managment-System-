package Library;

import java.util.Scanner;

public class DeleteAllData implements IOOperation {
    @Override
    public void oper(Database database, User user){
        System.out.println("Are you sure that you want to delete all data:");
        System.out.println("1. continue \n 2. Main Menu");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        if(i == 1){
            database.deleteAllData();
        } else {
            user.menu(database, user);
        }
    }
}
