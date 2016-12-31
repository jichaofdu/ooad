package dao;

import entity.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.MySessionFactory;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jichao on 2016/12/26.
 */
public class StaffDao {

    public static final int INSERT_SUCCESS = 100;
    public static final int INSERT_FAIL = 101;

    public static Staff getUserById(int id){
        Session session = MySessionFactory.getSession();
        Query<Staff> query = session.createQuery(
                "select i from Staff i where i.id = :id",Staff.class
        ).setParameter("id",id);
        Staff staff = query.uniqueResult();
        session.close();
        return staff;
    }

    public static ArrayList<Staff> getStaffList(){
        Session session = MySessionFactory.getSession();
        Query<Staff> query = session.createQuery(
                "select i from Staff i",Staff.class
        );
        List<Staff> list  = query.list();
        session.getClass();
        ArrayList<Staff> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }

    public static int insertUser(Staff newStaff){
        Session session = MySessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(newStaff);
            tx.commit();
            session.close();
            return INSERT_SUCCESS;
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
            return INSERT_FAIL;
        }
    }
}
