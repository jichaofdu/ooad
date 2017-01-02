package program;

import dao.StaffDao;
import dao.BackupDao;
import dao.EquipmentDao;
import dao.EquipmentBorrowRecordDao;
import dao.BackupBorrowRecordDao;
import entity.BackupBorrowRecord;
import entity.EquipmentBorrowRecord;
import entity.Staff;
import entity.Equipment;
import entity.Backup;

import javax.naming.SizeLimitExceededException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by jichao on 2016/12/28.
 */
public class StaffSystem {
    private static final char SELECT_VIEW_MY_BORROW_EQUIPMENT_RECORD = 'A';
    private static final char SELECT_VIEW_MY_BORROW_BACKUP_RECORD = 'B';
    private static final char SELECT_BORROW_EQUIPMENT = 'C';
    private static final char SELECT_RETURN_EQUIPMENT = 'D';
    private static final char SELECT_BORROW_BACKUP = 'E';
    private static final char SELECT_RETURN_BACKUP = 'F';
    private static final char SELECT_SETUP_BACKUP = 'G';
    private static final char SELECT_REMOVE_BACKUP = 'H';
    private static final char SELECT_VIEW_MY_HOLD = 'I';
    private static final char SELECT_VIEW_EQUIPMENT_LIST_BY_KEY_WORDS = 'J';
    private static final char SELECT_VIEW_BACKUP_LIST_BY_KEY_WORDS = 'K';
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
        SELECT_SET.add(SELECT_SETUP_BACKUP);
        SELECT_SET.add(SELECT_REMOVE_BACKUP);
        SELECT_SET.add(SELECT_VIEW_MY_HOLD);
        SELECT_SET.add(SELECT_VIEW_EQUIPMENT_LIST_BY_KEY_WORDS);
        SELECT_SET.add(SELECT_VIEW_BACKUP_LIST_BY_KEY_WORDS);
        SELECT_SET.add(SELECT_QUIT_STAFF_SYSTEM);

        //1.
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
                Staff staff = StaffDao.getStaffById(Integer.parseInt(read));
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
            System.out.println("[C]租借设备 [D]归还设备 [E]租借备件 [F]归还备件 [G]安装备件 [H]移除备件 [I]查看持有设备和备件");
            System.out.println("[J]根据关键字搜索设备 [K]根据关键字搜索备件 [L]查看设备的备件安装、更换记录 [Q]退出员工系统");
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
                case SELECT_SETUP_BACKUP:
                    setupBackup();
                    break;
                case SELECT_REMOVE_BACKUP:
                    removeBackup();
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
        System.out.println("共搜索到设备借还记录 " + equipmentBorrowRecords.size() + " 条");
        System.out.println();
        return equipmentBorrowRecords.size();
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
        System.out.println("共搜索到备件借还记录 " + backupBorrowRecords.size() + " 条");
        System.out.println();
        return backupBorrowRecords.size();
    }

    //租借成功 0
    //取消租借 -1
    //输入数字不合法 -2
    //查无此设备 -3
    //设备已报废 -4
    //设备已借出 -5
    //最后一步放弃租借 -6
    //系统租借失败 -7
    //异常状态 -8
    private int borrowEquipment(){
        System.out.println("请输入您想要租借的设备ID。按[Q]返回");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了租借设备的操作");
            return -1;
        }else{
            boolean isInteger = isInteger(read);
            if(!isInteger){
                System.out.println("你输入的数字不合法");
                return -2;
            }
            int equipmentId = Integer.parseInt(read);
            Equipment equipment = EquipmentDao.getEquipmentById(equipmentId);
            Timestamp[] equipmentDate = EquipmentBorrowRecordDao.getEquipmentDate(equipmentId);
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
//                    if(equipmentDate[0] != null){
//                        System.out.println("租借时间：" + equipmentDate[0]);
//                        if(equipmentDate[1] != null){
//                            System.out.println("归还时间：" + equipmentDate[1]);
//                        }else {
//                            System.out.println("归还时间：尚未归还");
//                        }
//                    }else {
//                        System.out.println("租借时间：尚未租借");
//                        System.out.println("归还时间：尚未归还");
//                    }
                }else{
                    System.out.println("报废时间：" + equipment.getScrapeDate());
                }
                System.out.println("----------------------");
                if(!(equipment.getScrapeDate() == null || equipment.getScrapeDate().equals("null"))){
                    System.out.println("对不起，该设备已报废，不能借出");
                    return -4;
                }else {
                    if(!(equipmentDate[0] == null || equipmentDate[1] != null)){
                        System.out.println("对不起，该设备尚未被归还，不能租借");
                        return -5;
                    }else {
                        System.out.println("确认要租借该设备吗？ [Y]租借 [N]暂不租借");
                        String temp = sc.next();
                        while (!temp.trim().equalsIgnoreCase("Y") && !temp.trim().equalsIgnoreCase("N")){
                            System.out.println("输入有误，请输入 [Y]租借 或 [N]暂不租借");
                            temp = sc.next();
                        }
                        if(temp.trim().equalsIgnoreCase("N")){
                            System.out.println("你取消了租借操作");
                            return -6;
                        }else if(temp.trim().equalsIgnoreCase("Y")){
                            EquipmentBorrowRecord newRecord = new EquipmentBorrowRecord();
                            newRecord.setUserId(currentUser.getId());
                            newRecord.setEquipmentId(equipment.getId());
                            newRecord.setBorrowDate(new Timestamp(System.currentTimeMillis()));
                            newRecord.setReturnDate(null);
                            int result = EquipmentBorrowRecordDao.borrowEquipment(newRecord);
                            if(result == EquipmentBorrowRecordDao.SAVEORUPDATE_SUCCESS){
                                System.out.println("租借成功");
                                return 0;
                            }else {
                                System.out.println("租借失败");
                                return -7;
                            }
                        }else{
                            System.out.println("[警告]不明状态");
                            return -8;
                        }
                    }
                }
            }
        }
    }

    //取消归还 -1
    //输入不合法 -2
    //没有借用 -3
    //已经归还 -4
    //系统归还失败 -5
    //最后一步放弃归还 -6
    //不明状态 -7
    private int returnEquipment(){
        System.out.println("请输入你想要归还的设备ID。。按[Q]返回");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了归还设备的操作。");
            return -1;
        }else{
            boolean isInteger = isInteger(read);
            if(!isInteger){
                System.out.println("你输入的数字不合法");
                return -2;
            }
            int equipmentId = Integer.parseInt(read);
            EquipmentBorrowRecord record = EquipmentBorrowRecordDao.getUserEquipmentById(currentUser.getId(), equipmentId);
            if(record == null){
                System.out.println("您没有借用此设备，请检查后再进行归还");
                return -3;
            }else {
                if(record.getReturnDate() != null){
                    System.out.println("您的设备已归还，无需再进行归还");
                    return -4;
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
                    System.out.println("确定要归还该设备吗？ [Y]归还 [N]暂不归还");
                    String temp = sc.next();
                    while(!temp.trim().equalsIgnoreCase("Y") && !temp.trim().equalsIgnoreCase("N")){
                        System.out.println("输入有误，请输入 [Y]归还 或 [N]暂不归还");
                        temp = sc.next();
                    }
                    if(temp.trim().equalsIgnoreCase("Y")){
                        record.setReturnDate(new Timestamp(System.currentTimeMillis()));
                        int result = EquipmentBorrowRecordDao.borrowEquipment(record);
                        if(result == EquipmentBorrowRecordDao.SAVEORUPDATE_SUCCESS){
                            System.out.println("归还成功");
                            return 0;
                        }else {
                            System.out.println("归还失败");
                            return -5;
                        }
                    }else if(temp.trim().equalsIgnoreCase("N")){
                        System.out.println("你取消了归还操作");
                        return -6;
                    }else{
                        System.out.println("[警告]状态不明");
                        return -7;
                    }
                }
            }
        }
    }

    //租借成功 0
    //取消租借 -1
    //输入数字不合法 -2
    //查无此设备 -3
    //设备已报废 -4
    //设备已借出 -5
    //最后一步放弃租借 -6
    //系统租借失败 -7
    //不明状态 -8
    private int borrowBackup(){
        System.out.println("请输入您想要租借的备件ID。按[Q]返回");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了租借备件的操作");
            return -1;
        }else{
            boolean isInteger = isInteger(read);
            if(!isInteger){
                System.out.println("你输入的数字不合法");
                return -2;
            }
            int backupId = Integer.parseInt(read);
            Backup backup = BackupDao.getBackupById(backupId);
            Timestamp[] backupDate = BackupBorrowRecordDao.getBackupDate(backupId);
            if(backup == null){
                System.out.println("查无此备件");
                System.out.println();
                return -3;
            }else{
                System.out.println("----------------------");
                System.out.println("备件ID：" + backup.getId());
                System.out.println("备件名称：" + backup.getName());
                System.out.println("采购时间：" + backup.getPurchaseDate());
                if(backup.getScrapeDate() == null || backup.getScrapeDate().equals("null")){
                    System.out.println("报废时间：尚未报废");
//                    if(backupDate[0] != null){
//                        System.out.println("租借时间：" + backupDate[0]);
//                        if(backupDate[1] != null){
//                            System.out.println("归还时间：" + backupDate[1]);
//                        }else {
//                            System.out.println("归还时间：尚未归还");
//                        }
//                    }else {
//                        System.out.println("租借时间：尚未租借");
//                        System.out.println("归还时间：尚未归还");
//                    }
                }else{
                    System.out.println("报废时间：" + backup.getScrapeDate());
                }
                System.out.println("----------------------");
                if(!(backup.getScrapeDate() == null || backup.getScrapeDate().equals("null"))){
                    System.out.println("对不起，该备件已报废，不能借出");
                    return -4;
                }else {
                    if(!(backupDate[0] == null || backupDate[1] != null)){
                        System.out.println("对不起，该备件尚未被归还，不能租借");
                        return -5;
                    }else {
                        System.out.println("确认要租借该备件吗？ [Y]租借 [N]暂不租借");
                        String temp = sc.next();
                        while (!temp.trim().equalsIgnoreCase("Y") && !temp.trim().equalsIgnoreCase("N")){
                            System.out.println("输入有误，请输入 [Y]租借 或 [N]暂不租借");
                            temp = sc.next();
                        }
                        if(temp.trim().equalsIgnoreCase("N")){
                            System.out.println("你取消了租借操作");
                            return -6;
                        }else if(temp.trim().equalsIgnoreCase("Y")){
                            BackupBorrowRecord newRecord = new BackupBorrowRecord();
                            newRecord.setUserId(currentUser.getId());
                            newRecord.setBackupId(backup.getId());
                            newRecord.setEquipmentId(0);
                            newRecord.setBorrowDate(new Timestamp(System.currentTimeMillis()));
                            newRecord.setReturnDate(null);
                            int result = BackupBorrowRecordDao.borrowBackup(newRecord);
                            if(result == BackupBorrowRecordDao.SAVEORUPDATE_SUCCESS){
                                System.out.println("租借成功");
                                return 0;
                            }else {
                                System.out.println("租借失败");
                                return -7;
                            }
                        }else{
                            System.out.println("[警告]不明状态");
                            return -8;
                        }
                    }
                }
            }
        }
    }

    //取消归还 -1
    //输入不合法 -2
    //没有借用 -3
    //已经归还 -4
    //备件没移除 -5
    //系统归还失败 -6
    //最后一步放弃归还 -7
    //不明状态 -8
    private int returnBackup(){
        System.out.println("请输入需要归还的备件ID。按[Q]返回");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了归还备件的操作");
            return -1;
        }else {
            boolean isInteger = isInteger(read);
            if (!isInteger) {
                System.out.println("你输入的数字不合法");
                return -2;
            }
            int backupId = Integer.parseInt(read);
            BackupBorrowRecord backupBorrowRecord = BackupBorrowRecordDao.getUserBackupById(currentUser.getId(), backupId);
            if(backupBorrowRecord == null){
                System.out.println("您没有租借此备件，请检查后再进行归还");
                return -3;
            }else {
                if(backupBorrowRecord.getReturnDate() != null){
                    System.out.println("您的备件已归还，无需再进行归还");
                    return -4;
                }else {
                    if(backupBorrowRecord.getEquipmentId() != 0){
                        System.out.println("此备件已经安装到设备上，请将备件移除后在进行归还");
                        return -5;
                    }else {
                        Backup backup = BackupDao.getBackupById(backupId);
                        System.out.println("----------------------");
                        System.out.println("备件ID：" + backup.getId());
                        System.out.println("备件名称：" + backup.getName());
                        System.out.println("采购时间：" + backup.getPurchaseDate());
                        if(backup.getScrapeDate() == null || backup.getScrapeDate().equals("null")){
                            System.out.println("报废时间：尚未报废");
                            System.out.println("租借时间：" + backupBorrowRecord.getBorrowDate());
                            System.out.println("归还时间：尚未归还");
                        }else{
                            System.out.println("报废时间：" + backup.getScrapeDate());
                        }
                        System.out.println("----------------------");
                        System.out.println("是否要归还该备件？[Y]归还 [N]暂不归还");
                        String temp = sc.next();
                        while (!temp.trim().equalsIgnoreCase("Y") && !temp.trim().equalsIgnoreCase("N")){
                            System.out.println("输入有误，请输入 [Y]归还 或 [N]暂不归还");
                            temp = sc.next();
                        }
                        if(temp.trim().equalsIgnoreCase("N")){
                            System.out.println("你取消了归还操作");
                            return -7;
                        }else if(temp.trim().equalsIgnoreCase("Y")){
                            backupBorrowRecord.setReturnDate(new Timestamp(System.currentTimeMillis()));
                            int result = BackupBorrowRecordDao.borrowBackup(backupBorrowRecord);
                            if(result == BackupBorrowRecordDao.SAVEORUPDATE_SUCCESS){
                                System.out.println("归还成功");
                                return 0;
                            }else {
                                System.out.println("归还失败");
                                return -6;
                            }
                        }else{
                            System.out.println("[警告]不明状态");
                            return -8;
                        }
                    }
                }
            }
        }
    }

    private int setupBackup(){
        System.out.println("请选择需要安装备件的设备ID。按[Q]返回");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了安装备件的操作");
            return 0;
        }else{
            boolean isInteger = isInteger(read);
            if(!isInteger){
                System.out.println("你输入的数字不合法");
                return -1;
            }
            int equipmentId = Integer.parseInt(read);
            EquipmentBorrowRecord record = EquipmentBorrowRecordDao.getUserEquipmentById(currentUser.getId(), equipmentId);
            if(record == null){
                System.out.println("您没有借用此设备，请检查后再进行安装");
                return -1;
            }else {
                if(record.getReturnDate() != null){
                    System.out.println("您没有借用此设备，请检查后再进行安装");
                    return -1;
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
                    System.out.println("请输入需要安装的备件ID");
                    String temp = sc.next();
                    if(read.length() == 1 && read.charAt(0) == 'Q'){
                        System.out.println("你取消了安装备件的操作");
                        return 0;
                    }else{
                        boolean isIntegerBackup = isInteger(read);
                        if(!isIntegerBackup){
                            System.out.println("你输入的数字不合法");
                            return -1;
                        }
                        int backupId = Integer.parseInt(temp);
                        BackupBorrowRecord backupBorrowRecord = BackupBorrowRecordDao.getUserBackupById(currentUser.getId(), backupId);
                        if(backupBorrowRecord == null){
                            System.out.println("您没有租借该备件，请检查后再进行安装");
                            return -1;
                        }else {
                            if(backupBorrowRecord.getReturnDate() != null){
                                System.out.println("您没有租借该备件，请检查后再进行安装");
                                return -1;
                            }else {
                                Backup backup = BackupDao.getBackupById(backupId);
                                System.out.println("----------------------");
                                System.out.println("备件ID：" + backup.getId());
                                System.out.println("备件名称：" + backup.getName());
                                System.out.println("采购时间：" + backup.getPurchaseDate());
                                if(backup.getScrapeDate() == null || backup.getScrapeDate().equals("null")){
                                    System.out.println("报废时间：尚未报废");
                                    System.out.println("租借时间：" + record.getBorrowDate());
                                    System.out.println("归还时间：尚未归还");
                                }else{
                                    System.out.println("报废时间：" + backup.getScrapeDate());
                                }
                                System.out.println("----------------------");
                            }
                            if(backupBorrowRecord.getReturnDate() == null){
                                System.out.println("要将此备件安装在设备上吗？ [Y]安装 [N]暂不安装");
                                String tempResult = sc.next();
                                while (!tempResult.trim().equalsIgnoreCase("Y") && !tempResult.trim().equalsIgnoreCase("N")){
                                    System.out.println("输入有误，请输入 [Y]安装 或 [N]暂不安装");
                                    tempResult = sc.next();
                                }
                                if(tempResult.trim().equalsIgnoreCase("N")){
                                    System.out.println("你取消了安装操作");
                                    return 0;
                                }else if(tempResult.trim().equalsIgnoreCase("Y")){
                                    backupBorrowRecord.setEquipmentId(equipmentId);
                                    int result = BackupBorrowRecordDao.borrowBackup(backupBorrowRecord);
                                    if(result == BackupBorrowRecordDao.SAVEORUPDATE_SUCCESS){
                                        System.out.println("安装成功");
                                        return 0;
                                    }else {
                                        System.out.println("安装失败");
                                        return -1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }


    private int removeBackup(){
        System.out.println("请输入需要移除备件的设备ID。按[Q]返回");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了移除备件的操作");
            return 0;
        }else{
            boolean isInteger = isInteger(read);
            if(!isInteger){
                System.out.println("你输入的数字不合法");
                return -1;
            }
            int equipmentId = Integer.parseInt(read);
            EquipmentBorrowRecord record = EquipmentBorrowRecordDao.getUserEquipmentById(currentUser.getId(), equipmentId);
            if(record == null){
                System.out.println("您没有借用此设备，请检查后再进行移除");
                return -1;
            }else {
                if(record.getReturnDate() != null){
                    System.out.println("您没有借用此设备，请检查后再进行移除");
                    return -1;
                }else {
                    ArrayList<BackupBorrowRecord> backupBorrowRecords = BackupBorrowRecordDao.getSetupBackup(currentUser.getId(), equipmentId);
                    if(backupBorrowRecords != null && backupBorrowRecords.size() > 0){
                        System.out.println("此设备中安装的备件列表如下：");
                        List<Integer> backupIdList = new ArrayList<>();
                        for(BackupBorrowRecord backupBorrowRecord : backupBorrowRecords){
                            Backup backup = BackupDao.getBackupById(backupBorrowRecord.getBackupId());
                            if(backup != null){
                                System.out.println("备件ID：" + backup.getId() + "　   " + "备件名称：" + backup.getName());
                                backupIdList.add(backup.getId());
                            }else {
                                System.out.println("无");
                            }
                        }
                        System.out.println("请输入要移除的备件ID");
                        String temp = sc.next();
                        if(temp.length() == 1 && temp.charAt(0) == 'Q'){
                            System.out.println("你取消了安装备件的操作");
                            return 0;
                        }else {
                            boolean isIntegerBackup = isInteger(read);
                            if (!isIntegerBackup) {
                                System.out.println("你输入的数字不合法");
                                return -1;
                            }
                            int backupId = Integer.parseInt(temp);
                            while (!backupIdList.contains(backupId)) {
                                System.out.println("此设备中尚未安装该备件，请重新输入备件ID");
                                String tempBackupId = sc.next();
                                if (tempBackupId.length() == 1 && tempBackupId.charAt(0) == 'Q') {
                                    System.out.println("你取消了安装备件的操作");
                                    return 0;
                                } else {
                                    boolean isIntegerBackupId = isInteger(read);
                                    if (!isIntegerBackupId) {
                                        System.out.println("你输入的数字不合法");
                                        return -1;
                                    }
                                    backupId = Integer.parseInt(tempBackupId);
                                }
                            }
                            System.out.println("要移除该备件吗？ [Y]移除 [N]暂不移除");
                            String result = sc.next();
                            while (!result.trim().equalsIgnoreCase("Y") && !result.trim().equalsIgnoreCase("N")){
                                System.out.println("输入有误，请输入 [Y]移除 或 [N]暂不移除");
                                result = sc.next();
                            }
                            if(result.trim().equalsIgnoreCase("N")){
                                System.out.println("你取消了移除操作");
                                return 0;
                            }else if(result.trim().equalsIgnoreCase("Y")){
                                BackupBorrowRecord backupBorrowRecord = BackupBorrowRecordDao.getUserBackupById(currentUser.getId(), backupId);
                                if(backupBorrowRecord != null){
                                    backupBorrowRecord.setEquipmentId(0);
                                }
                                int finalResult = BackupBorrowRecordDao.borrowBackup(backupBorrowRecord);
                                if(finalResult == BackupBorrowRecordDao.SAVEORUPDATE_SUCCESS){
                                    System.out.println("移除成功");
                                    return 0;
                                }else {
                                    System.out.println("移除失败");
                                    return -1;
                                }
                            }
                        }
                    }else {
                        System.out.println("此设备中没有安装设备");
                        return -1;
                    }
                }
            }
        }
        return 0;
    }

    private int viewMyHold(){
        System.out.println("以下是您所持有的设备列表：");
        ArrayList<EquipmentBorrowRecord> equipmentRecords = EquipmentBorrowRecordDao.getOwnEquipment(currentUser.getId());
        if(equipmentRecords != null && equipmentRecords.size() > 0){
            for(EquipmentBorrowRecord equipmentBorrowRecord : equipmentRecords){
                Equipment equipment = EquipmentDao.getEquipmentById(equipmentBorrowRecord.getEquipmentId());
                System.out.println("----------------------");
                System.out.println("设备ID：" + equipment.getId());
                System.out.println("设备名称：" + equipment.getName());
                System.out.println("采购时间：" + equipment.getPurchaseDate());
                if(equipment.getScrapeDate() == null || equipment.getScrapeDate().equals("null")){
                    System.out.println("报废时间：尚未报废");
                    System.out.println("租借时间：" + equipmentBorrowRecord.getBorrowDate());
                    System.out.println("归还时间：尚未归还");
                }else{
                    System.out.println("报废时间：" + equipment.getScrapeDate());
                }
            }
            System.out.println("----------------------");
            System.out.println("你共持有 " + equipmentRecords.size() + " 件设备");
        }else {
            System.out.println("你当前不持有任何设备。");
        }
        System.out.println();
        System.out.println("以下是您所持有的备件列表：");
        ArrayList<BackupBorrowRecord> backupRecords = BackupBorrowRecordDao.getOwnBackup(currentUser.getId());
        if(backupRecords != null && backupRecords.size() > 0){
            for(BackupBorrowRecord backupBorrowRecord : backupRecords){
                Backup backup = BackupDao.getBackupById(backupBorrowRecord.getBackupId());
                System.out.println("----------------------");
                System.out.println("备件ID：" + backup.getId());
                System.out.println("备件名称：" + backup.getName());
                System.out.println("采购时间：" + backup.getPurchaseDate());
                if(backup.getScrapeDate() == null || backup.getScrapeDate().equals("null")){
                    System.out.println("报废时间：尚未报废");
                    System.out.println("租借时间：" + backupBorrowRecord.getBorrowDate());
                    System.out.println("归还时间：尚未归还");
                }else{
                    System.out.println("报废时间：" + backup.getScrapeDate());
                }
            }
            System.out.println("----------------------");
            System.out.println("你共持有 " + backupRecords.size() + " 件备件");
        }else {
            System.out.println("你当前不持有任何备件。");
        }
        System.out.println();
        return equipmentRecords.size() + backupRecords.size();
    }

    private int viewEquipmentListByKeyWords(){
        System.out.println("请输入设备的关键词。按[Q]返回上级");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了通过关键词查找设备的操作");
            return -1;
        }else{
            String keyWords = read;
            ArrayList<Equipment> equipments = EquipmentDao.getEquipmentListByKeyWords(keyWords);
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
        }
        return 0;
    }

    private int viewBackupListByKeyWords(){
        System.out.println("请输入备件的关键词。按[Q]返回上级");
        Scanner sc = new Scanner(System.in);
        String read = sc.next();
        if(read.length() == 1 && read.charAt(0) == 'Q'){
            System.out.println("你取消了通过关键词查找备件的操作");
            return -1;
        }else{
            String keyWords = read;
            ArrayList<Backup> backups = BackupDao.getBackupListByKeyWords(keyWords);
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
        }
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
                System.out.println("[C]租借设备 [D]归还设备 [E]租借备件 [F]归还备件 [G]安装备件 [H]移除备件 [I]查看持有设备和备件");
                System.out.println("[J]根据关键字搜索设备 [K]根据关键字搜索备件 [Q]退出员工系统");
            }
        }
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
