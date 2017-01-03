package dao;

import entity.BackupBorrowRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.MySessionFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaoj on 2016/12/31.
 */
public class BackupBorrowRecordDao {

    public static final int SAVEORUPDATE_SUCCESS = 200;
    public static final int SAVEORUPDATE_FAIL = 201;

    public static int borrowBackup(BackupBorrowRecord record){
        Session session = MySessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try{
            session.saveOrUpdate(record);
            tx.commit();
            session.close();
            return SAVEORUPDATE_SUCCESS;
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return SAVEORUPDATE_FAIL;
        }
    }

    public static ArrayList<BackupBorrowRecord> getBorrowBorrowRecordListByBackupId(int backupId){
        Session session = MySessionFactory.getSession();
        Query<BackupBorrowRecord> query = session.createQuery(
                "select i from BackupBorrowRecord i where i.backupId = :backupId",BackupBorrowRecord.class
        ).setParameter("backupId",backupId);
        List<BackupBorrowRecord> list  = query.list();
        session.getClass();
        ArrayList<BackupBorrowRecord> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }

    public static ArrayList<BackupBorrowRecord> getBorrowBorrowRecordListByStaffId(int staffId){
        Session session = MySessionFactory.getSession();
        Query<BackupBorrowRecord> query = session.createQuery(
                "select i from BackupBorrowRecord i where i.userId = :staffId",BackupBorrowRecord.class
        ).setParameter("staffId",staffId);
        List<BackupBorrowRecord> list  = query.list();
        session.getClass();
        ArrayList<BackupBorrowRecord> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }


    public static BackupBorrowRecord getUserBackupById(int userId, int backupId){
        Session session = MySessionFactory.getSession();
        Query<BackupBorrowRecord> query =
                session.createQuery("select max(i) from BackupBorrowRecord i where i.backupId = :backupId and i.userId = :userId", BackupBorrowRecord.class)
                .setParameter("userId", userId).setParameter("backupId", backupId);
        BackupBorrowRecord record = query.uniqueResult();
        session.close();
        if(record == null){
            return null;
        }else {
            return record;
        }
    }

    public static ArrayList<BackupBorrowRecord> getSetupBackup(int userId, int equipmentId){
        Session session = MySessionFactory.getSession();
        Query<BackupBorrowRecord> query = session.createQuery("select i from BackupBorrowRecord i where i.userId = :userId and i.returnDate is null and i.equipmentId = :equipmentId", BackupBorrowRecord.class)
                .setParameter("userId", userId).setParameter("equipmentId", equipmentId);
        List<BackupBorrowRecord> list = query.list();
        session.close();
        ArrayList<BackupBorrowRecord> result = new ArrayList<>();
        if(list != null && list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                result.add(list.get(i));
            }
            return result;
        }else {
            return null;
        }
    }

    public static Timestamp[] getBackupDate(int backupId){
        Timestamp[] backupDate = new Timestamp[2];
        Session session = MySessionFactory.getSession();
        Query<BackupBorrowRecord> query = session.createQuery("select max(i) from BackupBorrowRecord i where i.backupId = :id", BackupBorrowRecord.class)
                .setParameter("id", backupId);
        BackupBorrowRecord record = query.uniqueResult();
        session.close();
        if(record == null){
            backupDate[0] = null;
            backupDate[1] = null;
        }else {
            backupDate[0] = record.getBorrowDate();
            backupDate[1] = record.getReturnDate();
        }
        return backupDate;
    }

    public static ArrayList<BackupBorrowRecord> getOwnBackup(int userId){
        Session session = MySessionFactory.getSession();
        Query<BackupBorrowRecord> query =
                session.createQuery("select i from BackupBorrowRecord i where i.userId = :userId and i.returnDate is null",
                        BackupBorrowRecord.class)
                .setParameter("userId", userId);
        List<BackupBorrowRecord> records = query.list();
        session.close();
        ArrayList<BackupBorrowRecord> result = new ArrayList<>();
        if(records != null && records.size() > 0) {
            result.addAll(records);
        }
        return result;
    }
}
