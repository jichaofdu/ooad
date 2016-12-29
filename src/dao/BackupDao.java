package dao;

import entity.Backup;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.MySessionFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jichao on 2016/12/26.
 */
public class BackupDao {
    public static final int SAVEORUPDATE_SUCCESS = 200;
    public static final int SAVEORUPDATE_FAIL = 201;

    public static int saveOrUpdateBackup(Backup backup){
        Session session = MySessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try{
            session.saveOrUpdate(backup);
            tx.commit();
            session.close();
            return SAVEORUPDATE_SUCCESS;
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return SAVEORUPDATE_FAIL;
        }
    }

    public static Backup getBackupById(int id){
        Session session = MySessionFactory.getSession();
        Query<Backup> query = session.createQuery(
                "select i from Backup i where i.id = :id",Backup.class
        ).setParameter("id",id);
        Backup backup = query.uniqueResult();
        session.close();
        return backup;
    }

    public static ArrayList<Backup> getBackupList(){
        Session session = MySessionFactory.getSession();
        Query<Backup> query = session.createQuery(
                "select i from Backup i",Backup.class
        );
        List<Backup> list  = query.list();
        session.getClass();
        ArrayList<Backup> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }
}
