package program;

import dao.*;
import entity.BackupBorrowRecord;
import entity.Equipment;
import entity.EquipmentBorrowRecord;
import entity.Staff;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    private static final char SELECT_VIEW_EQUIPMENT_LIST_BY_KEY_WORDS = 'H';
    private static final char SELECT_VIEW_BACKUP_LIST_BY_KEY_WORDS = 'I';
    private static final char SELECT_QUIT_STAFF_SYSTEM = 'Q';
    private static ArrayList<Character> SELECT_SET;
    private Staff currentUser;

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
        SELECT_SET.add(SELECT_VIEW_EQUIPMENT_LIST_BY_KEY_WORDS);
        SELECT_SET.add(SELECT_VIEW_BACKUP_LIST_BY_KEY_WORDS);
        while(true){
            System.out.println("请输入您的id。按[Q]退出员工系统");
            Scanner sc = new Scanner(System.in);
            String read = sc.next();
            if(read.length() == 1 && read.charAt(0) == 'Q') {
                System.out.println("你取消了登录操作。");
                return 0;
            }
            boolean isInteger = isInteger(read);
            if(!isInteger){
                System.out.println("你输入的ID不合法。");
            }else{
                Staff staff = StaffDao.getUserById(Integer.parseInt(read));
                if(staff == null){
                    System.out.println("查无此人");
                }
                else{
                    currentUser = staff;
                    System.out.println("欢迎，" + currentUser.getName());
                    break;
                }
            }
        }


        while(true){
            System.out.println("请选择您要进行的操作: [A]查看本人设备借还记录 [B]查看本人备件借还记录");
            System.out.println("[C]租借设备 [D]归还设备 [E]租借备件 [F]归还备件 [G]查看持有设备和备件");
            System.out.println("[H]根据关键字搜索设备 [I]根据关键字搜索备件 [Q]退出员工系统");
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
                case SELECT_VIEW_EQUIPMENT_LIST_BY_KEY_WORDS:
                    viewEquipmentListByKeyWords();
                    break;
                case SELECT_VIEW_BACKUP_LIST_BY_KEY_WORDS:
                    viewBackupListByKeyWords();
                    break;
                case SELECT_QUIT_STAFF_SYSTEM:
                    System.out.println("员工系统关闭中....");
                    currentUser = null;
                    return 0;
            }
        }
    }

    private int viewMyBorrowEquipmentRecord(){
        int staffId = currentUser.getId();
        ArrayList<EquipmentBorrowRecord> equipmentBorrowRecords = EquipmentBorrowRecordDao
                .getEquipmentBorrowRecordListByStaffId(staffId);
        System.out.println("以下是您的的设备借还记录");
        for(int i = 0;i < equipmentBorrowRecords.size();i++){
            System.out.println("----------------------");
            System.out.println("租借记录ID：" + equipmentBorrowRecords.get(i).getId());
            System.out.print("租借设备ID：" + equipmentBorrowRecords.get(i).getEquipmentId());
            System.out.println("  租借设备名称：" + EquipmentDao.getEquipmentById(equipmentBorrowRecords.get(i)
                    .getEquipmentId()).getName());
            System.out.println("租借时间：" + equipmentBorrowRecords.get(i).getBorrowDate());
            if(equipmentBorrowRecords.get(i).getReturnDate() == null){
                System.out.println("归还时间：尚未归还");
            }else{
                System.out.println("归还时间：" + equipmentBorrowRecords.get(i).getReturnDate());
            }
        }
        System.out.println("----------------------");
        System.out.println();
        System.out.println("共搜索到设备借还记录 " + equipmentBorrowRecords.size() + " 条");
        System.out.println();
        return 0;
    }

    private int viewMyBorrowBackupRecord(){
        int staffId = currentUser.getId();
        ArrayList<BackupBorrowRecord> backupBorrowRecords = BackupBorrowRecordDao
                .getBorrowBorrowRecordListByStaffId(staffId);
        for(int i = 0;i < backupBorrowRecords.size();i++){
            System.out.println("----------------------");
            System.out.println("租借记录ID：" + backupBorrowRecords.get(i).getId());
            System.out.print("租借备件ID：" + backupBorrowRecords.get(i).getBackupId());
            System.out.println("  租借备件名称：" + BackupDao.getBackupById(backupBorrowRecords.get(i).getBackupId()).getName());
            System.out.print("安装设备ID：" + backupBorrowRecords.get(i).getEquipmentId());
            System.out.println("  安装设备名称：" + EquipmentDao.getEquipmentById(backupBorrowRecords.get(i).getEquipmentId()).getName());
            System.out.println("租借时间：" + backupBorrowRecords.get(i).getBorrowDate());
            if(backupBorrowRecords.get(i).getReturnDate() == null){
                System.out.println("归还时间：尚未归还");
            }else{
                System.out.println("归还时间：" + backupBorrowRecords.get(i).getReturnDate());
            }
        }
        System.out.println("----------------------");
        System.out.println();
        System.out.println("共搜索到备件借还记录 " + backupBorrowRecords.size() + " 条");
        System.out.println();
        return 0;
    }

    private int borrowEquipment(){
        System.out.println("请输入您想要租借的设备ID。按[Q]返回");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了租借设备的操作");
            return 0;
        }else{
            boolean isInteger = isInteger(read);
            if(!isInteger){
                System.out.println("你输入的数字不合法");
                return -1;
            }
            int equipmentId = Integer.parseInt(read);
            Equipment equipment = EquipmentDao.getEquipmentById(equipmentId);
            Timestamp[] equipmentDate = EquipmentDao.getEquipmentDate(equipmentId);
            if(equipment == null){
                System.out.println("查无此设备");
                System.out.println();
                return -1;
            }else{
                System.out.println("----------------------");
                System.out.println("设备ID：" + equipment.getId());
                System.out.println("设备名称：" + equipment.getName());
                System.out.println("采购时间：" + equipment.getPurchaseDate());
                if(equipment.getScrapeDate() == null || equipment.getScrapeDate().equals("null")){
                    System.out.println("报废时间：尚未报废");
                    if(equipmentDate[0] != null){
                        System.out.println("租借时间：" + equipmentDate[0]);
                        if(equipmentDate[1] != null){
                            System.out.println("归还时间：" + equipmentDate[1]);
                        }else {
                            System.out.println("归还时间：尚未归还");
                        }
                    }else {
                        System.out.println("租借时间：尚未租借");
                        System.out.println("归还时间：尚未归还");
                    }
                }else{
                    System.out.println("报废时间：" + equipment.getScrapeDate());
                }
                System.out.println("----------------------");
                if(!(equipment.getScrapeDate() == null || equipment.getScrapeDate().equals("null"))){
                    System.out.println("对不起，该设备已报废，不能借出");
                    return -1;
                }else {
                    if(!(equipmentDate[0] == null || equipmentDate[1] != null)){
                        System.out.println("对不起，该设备尚未被归还，不能租借");
                        return 0;
                    }else {
                        System.out.println("确认要租借该设备吗？ [Y]租借 [N]暂不租借");
                        String temp = sc.next();
                        while (!temp.trim().equalsIgnoreCase("Y") && !temp.trim().equalsIgnoreCase("N")){
                            System.out.println("输入有误，请输入 [Y]租借 或 [N]暂不租借");
                            temp = sc.next();
                        }
                        if(temp.trim().equalsIgnoreCase("N")){
                            System.out.println("你取消了租借操作");
                            return 0;
                        }else if(temp.trim().equalsIgnoreCase("Y")){
                            EquipmentBorrowRecord newRecord = new EquipmentBorrowRecord();
                            newRecord.setUserId(currentUser.getId());
                            newRecord.setEquipmentId(equipment.getId());
                            newRecord.setBorrowDate(new Timestamp(System.currentTimeMillis()));
                            newRecord.setReturnDate(null);
                            int result = EquipmentBorrowRecordDao.borrowEquipment(newRecord);
                            if(result == EquipmentDao.SAVEORUPDATE_SUCCESS){
                                System.out.println("租借成功");
                                return 0;
                            }else {
                                System.out.println("租借失败");
                                return -1;
                            }
                        }
                    }
                }
            }
            return 0;
        }
    }

    private int returnEquipment(){
        System.out.println("请输入你想要归还的设备ID：");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了归还设备的操作。");
            return 0;
        }else{
            boolean isInteger = isInteger(read);
            if(!isInteger){
                System.out.println("你输入的数字不合法");
                return -1;
            }
            int equipmentId = Integer.parseInt(read);
            System.out.println(currentUser.getId() + " " + equipmentId);
            EquipmentBorrowRecord record = EquipmentDao.getUserEquipmentById(currentUser.getId(), equipmentId);
            if(record == null){
                System.out.println("您没有借用此设备，请检查后再进行归还");
                return -1;
            }else {
                if(record.getReturnDate() != null){
                    System.out.println("您的设备已归还，无需再进行归还");
                    return 0;
                }else {
                    Equipment equipment = EquipmentDao.getEquipmentById(equipmentId);
                    System.out.println("----------------------");
                    System.out.println("设备ID：" + equipment.getId());
                    System.out.println("设备名称：" + equipment.getName());
                    System.out.println("采购时间：" + equipment.getPurchaseDate());
                    if(equipment.getScrapeDate() == null || equipment.getScrapeDate().equals("null")){
                        System.out.println("报废时间：尚未报废");
                        System.out.println("租借时间：" + record.getBorrowDate());
                        System.out.println("归还时间：尚未归还");
                    }else{
                        System.out.println("报废时间：" + equipment.getScrapeDate());
                    }
                    System.out.println("----------------------");
                }
                if(record.getReturnDate() == null){
                    System.out.println("确定要归还该设备吗？ [Y]归还 [N]暂不归还");
                    String temp = sc.next();
                    while(!temp.trim().equalsIgnoreCase("Y") && !temp.trim().equalsIgnoreCase("N")){
                        System.out.println("输入有误，请输入 [Y]归还 或 [N]暂不归还");
                        temp = sc.next();
                    }
                    if(temp.trim().equalsIgnoreCase("Y")){
                        record.setReturnDate(new Timestamp(System.currentTimeMillis()));
                        int result = EquipmentBorrowRecordDao.borrowEquipment(record);
                        if(result == EquipmentDao.SAVEORUPDATE_SUCCESS){
                            System.out.println("归还成功");
                            return 0;
                        }else {
                            System.out.println("归还失败");
                            return -1;
                        }
                    }else{
                        System.out.println("你取消了归还操作");
                        return 0;
                    }
                }
            }
        }
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

    private int viewEquipmentListByKeyWords(){
        return 0;
    }

    private int viewBackupListByKeyWords(){
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
                System.out.println("请选择您要进行的操作: [A]查看本人设备借还记录 [B]查看本人备件借还记录");
                System.out.println("[C]租借设备 [D]归还设备 [E]租借备件 [F]归还备件 [G]查看持有设备和备件");
                System.out.println("[H]根据关键字搜索设备 [I]根据关键字搜索备件 [Q]退出员工系统");
            }
        }
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
