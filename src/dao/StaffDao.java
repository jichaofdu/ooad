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

    /**
     * 通过id获取员工的信息
     * @param id 要获取的那个员工的Id
     * @return 返回的员工的信息
     */
    public static Staff getStaffById(int id){
        Session session = MySessionFactory.getSession();
        Query<Staff> query = session.createQuery(
                "select i from Staff i where i.id = :id",Staff.class
        ).setParameter("id",id);
        Staff staff = query.uniqueResult();
        session.close();
        return staff;
    }

    /**
     * 获取数据库中所有员工的列表
     * @return 员工列表ArrayList
     */
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

    /**
     * 向数据库中新增一个员工条目
     * @param newStaff 新增的员工的信息
     * @return 新增结果。100表示成功，101表示失败
     */
    public static int insertStaff(Staff newStaff){
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
