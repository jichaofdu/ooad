package program;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by chaoj on 2016/12/28.
 */
public class StaffSystem {
    private static final char SELECT_VIEW_MY_BORROW_EQUIPMENT_RECORD = 'A';
    private static final char SELECT_VIEW_MY_BORROW_BACKUP_RECORD = 'B';
    private static final char SELECT_BORROW_EQUIPMENT = 'C';
    private static final char SELECT_RETURN_EQUIPMENT = 'D';
    private static final char SELECT_BORROW_BACKUP = 'E';
    private static final char SELECT_RETURN_BACKUP = 'F';
    private static final char SELECT_VIEW_MY_HOLD = 'G';
    private static final char SELECT_QUIT_STAFF_SYSTEM = 'Q';
    private static ArrayList<Character> SELECT_SET;


    public int launch(){
        System.out.println("员工系统启动中....");
        SELECT_SET = new ArrayList<>();
        SELECT_SET.add(SELECT_VIEW_MY_BORROW_EQUIPMENT_RECORD);
        SELECT_SET.add(SELECT_VIEW_MY_BORROW_BACKUP_RECORD);
        SELECT_SET.add(SELECT_BORROW_EQUIPMENT);
        SELECT_SET.add(SELECT_RETURN_EQUIPMENT);
        SELECT_SET.add(SELECT_BORROW_BACKUP);
        SELECT_SET.add(SELECT_RETURN_BACKUP);
        SELECT_SET.add(SELECT_VIEW_MY_HOLD);
        SELECT_SET.add(SELECT_QUIT_STAFF_SYSTEM);


        while(true){
            System.out.println("请选择您要进行的操作: [A]查看本人设备借还记录 [B]查看本人备件借还记录");
            System.out.println("[C]租借设备 [D]归还设备 [E]租借备件 [F]归还备件 [G]查看持有设备和备件 [Q]退出员工系统");
            char modeSelection = getSelectionMode();
            switch (modeSelection){
                case SELECT_VIEW_MY_BORROW_EQUIPMENT_RECORD:
                    viewMyBorrowEquipmentRecord();
                    break;
                case SELECT_VIEW_MY_BORROW_BACKUP_RECORD:
                    viewMyBorrowBackupRecord();
                    break;
                case SELECT_BORROW_EQUIPMENT:
                    borrowEquipment();
                    break;
                case SELECT_RETURN_EQUIPMENT:
                    returnEquipment();
                    break;
                case SELECT_BORROW_BACKUP:
                    borrowBackup();
                    break;
                case SELECT_RETURN_BACKUP:
                    returnBackup();
                    break;
                case SELECT_VIEW_MY_HOLD:
                    viewMyHold();
                    break;
                case SELECT_QUIT_STAFF_SYSTEM:
                    System.out.println("员工系统关闭中....");
                    return 0;
            }
        }
    }

    private int viewMyBorrowEquipmentRecord(){
        return 0;
    }

    private int viewMyBorrowBackupRecord(){
        return 0;
    }

    private int borrowEquipment(){
        return 0;
    }

    private int returnEquipment(){
        return 0;
    }

    private int borrowBackup(){
        return 0;
    }

    private int returnBackup(){
        return 0;
    }

    private int viewMyHold(){
        return 0;
    }

    public static char getSelectionMode(){
        Scanner scan = new Scanner(System.in);
        char selection;
        while(true){
            String read = scan.nextLine();
            if(read.length() == 1 && SELECT_SET.contains(read.charAt(0))){
                selection = read.charAt(0);
                return selection;
            }else{
                System.out.println("您的输入不合法。请重新选择");
                System.out.println("请选择您要进行的操作: [A]查看设备借还记录 [B]查看备件借还记录");
                System.out.println("[C]租借设备 [D]归还设备 [E]租借备件 [F]归还备件 [G]查看持有设备和备件 [Q]退出员工系统");
            }
        }
    }
}
