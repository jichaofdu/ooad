package dao;

import entity.Backup;
import entity.BackupBorrowRecord;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.MySessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaoj on 2016/12/31.
 */
public class BackupBorrowRecordDao {

    public static final int SAVEORUPDATE_SUCCESS = 200;
    public static final int SAVEORUPDATE_FAIL = 201;

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

}
