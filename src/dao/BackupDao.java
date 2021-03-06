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
    public static final int SAVEORUPDATE_SUCCESS = 300;
    public static final int SAVEORUPDATE_FAIL = 301;

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

    /**
     * 根据备件的id获取备件
     * @param id 希望获取的备件的id
     * @return   id对应的那个备件
     */
    public static Backup getBackupById(int id){
        Session session = MySessionFactory.getSession();
        Query<Backup> query = session.createQuery(
                "select i from Backup i where i.id = :id",Backup.class
        ).setParameter("id",id);
        Backup backup = query.uniqueResult();
        session.close();
        return backup;
    }

    /**
     * 根据关键字模糊查询
     * @param keywords 关键词
     * @return 根据关键词查询到的结果
     */
    public static ArrayList<Backup> getBackupListByKeyWords(String keywords){
        Session session = MySessionFactory.getSession();
        String queryString = " select i from Backup i where i.name like '%" + keywords + "%'";
        Query<Backup> query = session.createQuery(
                queryString,Backup.class
        );
        List<Backup> list = query.list();
        session.getClass();
        ArrayList<Backup> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }

    /**
     * 获取数据中的所有备件的列表
     * @return 备件列表ArrayList
     */
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
