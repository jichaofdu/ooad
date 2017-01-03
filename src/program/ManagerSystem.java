package program;

import dao.*;
import entity.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    private static final char SELECT_ADD_STAFF = 'J';
    private static final char SELECT_VIEW_STAFF_LIST = 'K';
    private static final char SELECT_VIEW_EQUIPMENT_BACKUP_INSTALL_RECORD = 'L';
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
        SELECT_SET.add(SELECT_ADD_STAFF);
        SELECT_SET.add(SELECT_VIEW_STAFF_LIST);
        SELECT_SET.add(SELECT_VIEW_EQUIPMENT_BACKUP_INSTALL_RECORD);
        SELECT_SET.add(SELECT_QUIT_STAFF_SYSTEM);

        while(true){
            System.out.println("请选择您要进行的操作: [A]浏览设备列表 [B]浏览备件列表");
            System.out.println("[C]新增设备 [D]报废设备 [E]新增备件 [F]报废备件");
            System.out.println("[G]查看员工借还记录 [H]查看设备借还记录 [I]查看备件借还记录");
            System.out.println("[J]添加员工 [K]查看员工列表 [L]查看设备的备件安装记录 [Q]退出管理员系统");
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
                case SELECT_ADD_STAFF:
                    addStaff();
                    break;
                case SELECT_VIEW_STAFF_LIST:
                    viewStaffList();
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
        return equipments.size();
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
            return -1;
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
                return -2;
            }
        }
    }

    private int scrapeEquipment(){
        System.out.println("请输入要报废设备的id。 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了设备的报废操作。");
            return -1;
        }else{
            //1.检测是否是数字
            boolean isInteger = isInteger(read);
            if(isInteger == false){
                System.out.println("你输入的数字不合法。");
                return -2;
            }
            //2.查询数据并返回
            int equipmentId = Integer.parseInt(read);
            Equipment equipment = EquipmentDao.getEquipmentById(equipmentId);
            if(equipment == null){
                System.out.println("查无此设备");
                System.out.println();
                return -3;
            }else{
                System.out.println("----------------------");
                System.out.println("设备ID：" + equipment.getId());
                System.out.println("设备名称：" + equipment.getName());
                System.out.println("采购时间：" + equipment.getPurchaseDate());
                if(equipment.getScrapeDate() == null || equipment.getScrapeDate().equals("null")){
                    System.out.println("报废时间：尚未报废");
                }else{
                    System.out.println("报废时间：" + equipment.getScrapeDate());
                }
                System.out.println("----------------------");
            }
            //3.确认报废等操作
            if(equipment.getScrapeDate() != null){
                System.out.println("此设备已报废");
                return -4;
            }else{
                System.out.println("确认要报废吗？ [Y]是 [N]不报废");
                Scanner scanConfirm = new Scanner(System.in);
                String readConfirm = scanConfirm.nextLine();
                if(readConfirm.length() == 1 && (readConfirm.charAt(0) == 'Y' || readConfirm.charAt(0) == 'N')){
                    if(readConfirm.charAt(0) == 'N'){
                        System.out.println("你选择了不报废设备");
                        return -5;
                    }else{
                        equipment.setScrapeDate(new Timestamp(System.currentTimeMillis()));
                        int result = EquipmentDao.saveOrUpdateEquipment(equipment);
                        if(result == EquipmentDao.SAVEORUPDATE_SUCCESS){
                            System.out.println("报废设备成功");
                            return 0;
                        }else{
                            System.out.println("报废设备失败");
                            return -6;
                        }
                    }
                }else{
                    System.out.println("输入不合法.");
                    return -7;
                }
            }
        }
    }

    private int addBackup(){
        System.out.println("请输入备件的名称。 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了备件的添加操作。");
            return -1;
        }else{
            String backupName = read;
            Backup newBackup = new Backup();
            newBackup.setName(backupName);
            newBackup.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
            int result = BackupDao.saveOrUpdateBackup(newBackup);
            if(result == BackupDao.SAVEORUPDATE_SUCCESS){
                System.out.println("备件添加成功");
                return 0;
            }else{
                System.out.println("备件添加失败，请重试");
                return -2;
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
            boolean isInteger = isInteger(read);
            if(isInteger == false){
                System.out.println("你输入的数字不合法。");
                return -1;
            }
            //2.查询数据并返回
            int backupId = Integer.parseInt(read);
            Backup backup = BackupDao.getBackupById(backupId);
            if(backup == null){
                System.out.println("查无此备件");
                return -1;
            }else{
                System.out.println("----------------------");
                System.out.println("备件ID：" + backup.getId());
                System.out.println("备件名称：" + backup.getName());
                System.out.println("采购时间：" + backup.getPurchaseDate());
                if(backup.getScrapeDate() == null || backup.getScrapeDate().equals("null")){
                    System.out.println("报废时间：尚未报废");
                }else{
                    System.out.println("报废时间：" + backup.getScrapeDate());
                }
                System.out.println("----------------------");
            }
            //3.确认报废等操作
            if(backup.getScrapeDate() != null){
                System.out.println("此备件已报废");
                return 0;
            }else{
                System.out.println("确认要报废吗？ [Y]是 [N]不报废");
                Scanner scanConfirm = new Scanner(System.in);
                String readConfirm = scanConfirm.nextLine();
                if(readConfirm.length() == 1 && (readConfirm.charAt(0) == 'Y' || readConfirm.charAt(0) == 'N')){
                    if(readConfirm.charAt(0) == 'N'){
                        System.out.println("你选择了不报废备件");
                        return -1;
                    }else{
                        backup.setScrapeDate(new Timestamp(System.currentTimeMillis()));
                        int result = BackupDao.saveOrUpdateBackup(backup);
                        if(result == BackupDao.SAVEORUPDATE_SUCCESS){
                            System.out.println("报废备件成功");
                            return 0;
                        }else{
                            System.out.println("报废备件失败");
                            return -1;
                        }
                    }
                }else{
                    System.out.println("输入不合法.");
                    return -1;
                }
            }
        }
    }

    private int viewStaffBorrowAndReturn(){
        System.out.println("请输入要查看所有借还记录的员工ID。 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了查看设备借还记录的操作。");
            return -1;
        }else {
            //1.检测是否是数字
            boolean isInteger = isInteger(read);
            if (isInteger == false) {
                System.out.println("你输入的数字不合法。");
                return -1;
            }
           int staffId = Integer .parseInt(read);
            //2.从数据库中查看这个人的设备借还记录
            ArrayList<EquipmentBorrowRecord> equipmentBorrowRecords = EquipmentBorrowRecordDao
                    .getEquipmentBorrowRecordListByStaffId(staffId);
            System.out.println("以下是此员工的设备借还记录");
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
            //3.从数据库中查看这个人的备件借还记录
            ArrayList<BackupBorrowRecord> backupBorrowRecords = BackupBorrowRecordDao
                    .getBorrowBorrowRecordListByStaffId(staffId);
            for(int i = 0;i < backupBorrowRecords.size();i++){
                System.out.println("----------------------");
                System.out.println("租借记录ID：" + backupBorrowRecords.get(i).getId());
                System.out.print("租借备件ID：" + backupBorrowRecords.get(i).getBackupId());
                System.out.println("  租借备件名称：" + BackupDao.getBackupById(backupBorrowRecords.get(i).getBackupId()).getName());
                if(backupBorrowRecords.get(i).getEquipmentId() == 0){
                    System.out.print("安装设备ID：" + backupBorrowRecords.get(i).getEquipmentId());
                    System.out.println("  安装设备名称：尚未安装");
                }else {
                    System.out.print("安装设备ID：" + backupBorrowRecords.get(i).getEquipmentId());
                    System.out.println("  安装设备名称：" + EquipmentDao.getEquipmentById(backupBorrowRecords.get(i).getEquipmentId()).getName());
                }
                System.out.println("租借时间：" + backupBorrowRecords.get(i).getBorrowDate());
                if(backupBorrowRecords.get(i).getReturnDate() == null){
                    System.out.println("归还时间：尚未归还");
                }else{
                    System.out.println("归还时间：" + backupBorrowRecords.get(i).getReturnDate());
                }
            }
            System.out.println("----------------------");
            System.out.println();
            //4.总结
            System.out.println("共搜索到设备借还记录 " + equipmentBorrowRecords.size() + " 条");
            System.out.println("共搜索到备件借还记录 " + backupBorrowRecords.size() + " 条");
            System.out.println();
            return equipmentBorrowRecords.size() + backupBorrowRecords.size();
        }
    }

    private int viewEquipmentBorrowAndReturn(){
        System.out.println("请输入要查看借还记录的设备的id。 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了查看设备借还记录的操作。");
            return -1;
        }else {
            //1.检测是否是数字
            boolean isInteger = isInteger(read);
            if (isInteger == false) {
                System.out.println("你输入的数字不合法。");
                return -1;
            }
            //2.查找相关的记录
            int equipmentId = Integer.parseInt(read);
            ArrayList<EquipmentBorrowRecord> equipmentBorrowRecords = EquipmentBorrowRecordDao
                    .getEquipmentBorrowRecordListByEquipmentId(equipmentId);
            for(int i = 0;i < equipmentBorrowRecords.size();i++){
                System.out.println("----------------------");
                System.out.println("租借记录ID：" + equipmentBorrowRecords.get(i).getId());
                System.out.println("租借员工ID：" + equipmentBorrowRecords.get(i).getUserId());
                System.out.println("租借员工姓名：" + StaffDao.getStaffById(equipmentBorrowRecords.get(i).getUserId()).getName());
                System.out.println("租借时间：" + equipmentBorrowRecords.get(i).getBorrowDate());
                if(equipmentBorrowRecords.get(i).getReturnDate() == null){
                    System.out.println("归还时间：尚未归还");
                }else{
                    System.out.println("归还时间：" + equipmentBorrowRecords.get(i).getReturnDate());
                }
            }
            System.out.println("----------------------");
            System.out.println("共查到 " + equipmentBorrowRecords.size() + " 条记录");
            System.out.println();
            return equipmentBorrowRecords.size();
        }
    }

    private int viewBackupBorrowAndReturn(){
        System.out.println("请输入要查看借还记录的备件的id。 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了查看备件借还记录的操作。");
            return -1;
        }else {
            //1.检测是否是数字
            boolean isInteger = isInteger(read);
            if (isInteger == false) {
                System.out.println("你输入的数字不合法。");
                return -1;
            }
            //2.查找相关的记录
            int backupId = Integer.parseInt(read);
            ArrayList<BackupBorrowRecord> backupBorrowRecords = BackupBorrowRecordDao
                    .getBorrowBorrowRecordListByBackupId(backupId);
            for(int i = 0;i < backupBorrowRecords.size();i++){
                System.out.println("----------------------");
                System.out.println("租借记录ID：" + backupBorrowRecords.get(i).getId());
                System.out.print("租借员工ID：" + backupBorrowRecords.get(i).getUserId());
                System.out.println("  租借员工姓名：" + StaffDao.getStaffById(backupBorrowRecords.get(i).getUserId()).getName());
                if(backupBorrowRecords.get(i).getEquipmentId() == 0){
                    System.out.print("安装设备ID：" + backupBorrowRecords.get(i).getEquipmentId());
                    System.out.println("  安装设备名称：尚未安装");
                }else {
                    System.out.print("安装设备ID：" + backupBorrowRecords.get(i).getEquipmentId());
                    System.out.println("  安装设备名称：" + EquipmentDao.getEquipmentById(backupBorrowRecords.get(i).getEquipmentId()).getName());
                }
                System.out.println("租借时间：" + backupBorrowRecords.get(i).getBorrowDate());
                if(backupBorrowRecords.get(i).getReturnDate() == null){
                    System.out.println("归还时间：尚未归还");
                }else{
                    System.out.println("归还时间：" + backupBorrowRecords.get(i).getReturnDate());
                }
            }
            System.out.println("----------------------");
            System.out.println("共查到 " + backupBorrowRecords.size() + " 条记录");
            System.out.println();
            return backupBorrowRecords.size();
        }
    }

    private int addStaff(){
        System.out.println("请输入要添加员工的姓名 按[Q]取消添加返回上级");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了员工的添加操作。");
            return -1;
        }else{
            String staffName = read;
            Staff staff = new Staff();
            staff.setName(staffName);
            int result = StaffDao.insertStaff(staff);
            if(result == StaffDao.INSERT_SUCCESS){
                System.out.println("员工添加成功");
                return 0;
            }else{
                System.out.println("员工添加失败");
                return -2;
            }
        }
    }

    private int viewStaffList(){
        System.out.println("以下是查询到的员工列表");
        ArrayList<Staff> staffs = StaffDao.getStaffList();
        for(int i = 0;i < staffs.size();i++){
            System.out.println("----------------------");
            System.out.println("员工ID：" + staffs.get(i).getId());
            System.out.println("员工姓名：" + staffs.get(i).getName());
        }
        System.out.println("----------------------");
        System.out.println("共查到 " + staffs.size() + " 条记录");
        System.out.println();
        return staffs.size();
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
                System.out.println("[G]查看员工借还记录 [H]查看设备借还记录 [I]查看备件借还记录");
                System.out.println("[J]添加员工 [K]查看员工列表 [L]查看设备的备件安装记录 [Q]退出管理员系统");
            }
        }
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
