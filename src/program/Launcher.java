package program;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jichao on 2016/12/26.
 * 这个类是程序的启动器
 */
public class Launcher {

    private static final char MODE_STAFF = 'A';
    private static final char MODE_MANAGER = 'B';
    private static final char MODE_QUIT = 'Q';
    private static ArrayList<Character> MODE_SET;

    public static void main(String[] args){
        System.out.println("程序启动中....");

        MODE_SET = new ArrayList<>();
        MODE_SET.add(MODE_STAFF);
        MODE_SET.add(MODE_MANAGER);
        MODE_SET.add(MODE_QUIT);

        while(true){
            System.out.println("请选择您的角色： [A]员工 [B]仓库管理员 [Q]离开系统");
            char modeSelection = getSelectionMode();
            if(modeSelection == MODE_STAFF){
                StaffSystem staffSystem = new StaffSystem();
                staffSystem.launch();
            }else if(modeSelection == MODE_MANAGER){
                ManagerSystem managerSystem = new ManagerSystem();
                managerSystem.launch();
            }else if(modeSelection == MODE_QUIT){
                System.out.println("系统关闭中....");
                break;
            }
        }

    }

    public static char getSelectionMode(){
        Scanner scan = new Scanner(System.in);
        char selection;
        while(true){
            String read = scan.nextLine();
            if(read.length() == 1 && MODE_SET.contains(read.charAt(0))){
                selection = read.charAt(0);
                return selection;
            }else{
                System.out.println("您的输入不合法。请选择您的角色： [A]员工 [B]仓库管理员 ");
            }
        }
    }
}
