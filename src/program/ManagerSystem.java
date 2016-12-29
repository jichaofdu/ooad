package program;

import dao.BackupDao;
import dao.EquipmentDao;
import entity.Backup;
import entity.Equipment;

import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jichao on 2016/12/26.
 * 仓库管理员自己的系统
 */
public class ManagerSystem {

    private static final char SELECT_VIEW_EQUIPMENT_LIST = 'A';
    private static final char SELECT_VIEW_BACKUP_LIST = 'B';
    private static final char SELECT_ADD_EQUIPMENT = 'C';
    private static final char SELECT_SCRAPE_EQUIPMENT = 'D';
    private static final char SELECT_ADD_BACKUP = 'E';
    private static final char SELECT_SCRAPE_BACKUP = 'F';
    private static final char SELECT_VIEW_STAFF_BORROW_AND_RETURN = 'G';
    private static final char SELECT_VIEW_EQUIPMENT_BORROW_AND_RETURN = 'H';
    private static final char SELECT_VIEW_BACKUP_BORROW_AND_RETURN = 'I';
    private static final char SELECT_QUIT_STAFF_SYSTEM = 'Q';
    private static ArrayList<Character> SELECT_SET;

    public int launch(){
        System.out.println("管理员系统启动中....");
        SELECT_SET = new ArrayList<>();
        SELECT_SET.add(SELECT_VIEW_EQUIPMENT_LIST);
        SELECT_SET.add(SELECT_VIEW_BACKUP_LIST);
        SELECT_SET.add(SELECT_ADD_EQUIPMENT);
        SELECT_SET.add(SELECT_SCRAPE_EQUIPMENT);
        SELECT_SET.add(SELECT_ADD_BACKUP);
        SELECT_SET.add(SELECT_SCRAPE_BACKUP);
        SELECT_SET.add(SELECT_VIEW_STAFF_BORROW_AND_RETURN);
        SELECT_SET.add(SELECT_VIEW_EQUIPMENT_BORROW_AND_RETURN);
        SELECT_SET.add(SELECT_VIEW_BACKUP_BORROW_AND_RETURN);
        SELECT_SET.add(SELECT_QUIT_STAFF_SYSTEM);

        while(true){
            System.out.println("请选择您要进行的操作: [A]浏览设备列表 [B]浏览备件列表");
            System.out.println("[C]新增设备 [D]报废设备 [E]新增备件 [F]报废备件");
            System.out.println("[G]查看员工借还记录 [H]查看设备借还记录 [I]查看备件借还记录 [Q]退出管理员系统");
            char modeSelection = getSelectionMode();
            switch (modeSelection){
                case SELECT_VIEW_EQUIPMENT_LIST:
                    viewEquipmentList();
                    break;
                case SELECT_VIEW_BACKUP_LIST:
                    viewBackupList();
                    break;
                case SELECT_ADD_EQUIPMENT:
                    addEquipment();
                    break;
                case SELECT_SCRAPE_EQUIPMENT:
                    scrapeEquipment();
                    break;
                case SELECT_ADD_BACKUP:
                    addBackup();
                    break;
                case SELECT_SCRAPE_BACKUP:
                    scrapeBackup();
                    break;
                case SELECT_VIEW_STAFF_BORROW_AND_RETURN:
                    viewStaffBorrowAndReturn();
                    break;
                case SELECT_VIEW_EQUIPMENT_BORROW_AND_RETURN:
                    viewEquipmentBorrowAndReturn();
                    break;
                case SELECT_VIEW_BACKUP_BORROW_AND_RETURN:
                    viewBackupBorrowAndReturn();
                    break;
                case SELECT_QUIT_STAFF_SYSTEM:
                    System.out.println("管理员系统关闭中....");
                    return 0;
            }
        }
    }

    private int viewEquipmentList(){
        System.out.println("以下是查询到的设备列表");
        ArrayList<Equipment> equipments = EquipmentDao.getEquipmentList();
        for(int i = 0;i < equipments.size();i++){
            System.out.println("----------------------");
            System.out.println("设备ID：" + equipments.get(i).getId());
            System.out.println("设备名称：" + equipments.get(i).getName());
            System.out.println("采购时间：" + equipments.get(i).getPurchaseDate());
            if(equipments.get(i).getScrapeDate() == null || equipments.get(i).getScrapeDate().equals("null")){
                System.out.println("报废时间：尚未报废");
            }else{
                System.out.println("报废时间：" + equipments.get(i).getScrapeDate());
            }
        }
        System.out.println("----------------------");
        System.out.println("共查到 " + equipments.size() + " 条记录");
        System.out.println();
        return 0;
    }

    private int viewBackupList(){
        System.out.println("以下是查询到的备件列表");
        ArrayList<Backup> backups = BackupDao.getBackupList();
        for(int i = 0;i < backups.size();i++){
            System.out.println("----------------------");
            System.out.println("备件ID：" + backups.get(i).getId());
            System.out.println("备件名称：" + backups.get(i).getName());
            System.out.println("采购时间：" + backups.get(i).getPurchaseDate());
            if(backups.get(i).getScrapeDate() == null || backups.get(i).getScrapeDate().equals("null")){
                System.out.println("报废时间：尚未报废");
            }else{
                System.out.println("报废时间：" + backups.get(i).getScrapeDate());
            }
        }
        System.out.println("----------------------");
        System.out.println("共查到 " + backups.size() + " 条记录");
        System.out.println();
        return 0;
    }

    private int addEquipment(){
        System.out.println("请输入设备的名称。 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了设备的添加操作。");
            return 0;
        }else{
            String equipmentName = read;
            Equipment newEquipment = new Equipment();
            newEquipment.setName(equipmentName);
            newEquipment.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
            int result = EquipmentDao.saveOrUpdateEquipment(newEquipment);
            if(result == EquipmentDao.SAVEORUPDATE_SUCCESS){
                System.out.println("设备添加成功");
                return 0;
            }else{
                System.out.println("设备添加失败，请重试");
                return -1;
            }
        }
    }

    private int scrapeEquipment(){
        System.out.println("请输入要报废设备的id。 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了设备的报废操作。");
            return 0;
        }else{
            //1.检测是否是数字
            //2.执行报废操作
        }
        return 0;
    }

    private int addBackup(){
        System.out.println("请输入备件的名称。 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了备件的添加操作。");
            return 0;
        }else{
            String backupName = read;
            Backup newBackup = new Backup();
            newBackup.setName(backupName);
            newBackup.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
            int result = BackupDao.saveOrUpdateBackup(newBackup);
            if(result == EquipmentDao.SAVEORUPDATE_SUCCESS){
                System.out.println("备件添加成功");
                return 0;
            }else{
                System.out.println("备件添加失败，请重试");
                return -1;
            }
        }
    }

    private int scrapeBackup(){
        System.out.println("请输入要报废设备的id。 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了备件的报废操作。");
            return 0;
        }else{
            //1.检测是否是数字
            //2.执行报废操作
        }
        return 0;
    }

    private int viewStaffBorrowAndReturn(){
        return 0;
    }

    private int viewEquipmentBorrowAndReturn(){
        return 0;
    }

    private int viewBackupBorrowAndReturn(){
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
                System.out.println("请选择您要进行的操作: [A]浏览设备列表 [B]浏览备件列表");
                System.out.println("[C]新增设备 [D]报废设备 [E]新增备件 [F]报废备件");
                System.out.println("[G]查看员工借还记录 [H]查看设备借还记录 [I]查看备件借还记录 [Q]退出管理员系统");
            }
        }
    }


}
