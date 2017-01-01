package dao;

import entity.Equipment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.MySessionFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jichao on 2016/12/26.
 */
public class EquipmentDao {

    public static final int SAVEORUPDATE_SUCCESS = 200;
    public static final int SAVEORUPDATE_FAIL = 201;

    /**
     * 向数据库新增条目，或者更新数据库中的某个设备的数据
     * @param equipment 要保存或者更新的数据库的信息
     * @return 返回指令执行结果，200表示成功，201表示失败
     */
    public static int saveOrUpdateEquipment(Equipment equipment){
        Session session = MySessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try{
            session.saveOrUpdate(equipment);
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
     * 根据设备的id来获取设备
     * @param id 希望获取的设备的id
     * @return id对应的设备
     */
    public static Equipment getEquipmentById(int id){
        Session session = MySessionFactory.getSession();
        Query<Equipment> query = session.createQuery(
                "select i from Equipment i where i.id = :id",Equipment.class
        ).setParameter("id",id);
        Equipment equipment = query.uniqueResult();
        session.close();
        return equipment;
    }

    /**
     * 根据关键字模糊查询
     * @param keywords 关键词
     * @return 根据关键词查询到的结果
     */
    public static ArrayList<Equipment> getEquipmentListByKeyWords(String keywords){
        Session session = MySessionFactory.getSession();
        String queryString = " select i from Equipment i where i.name like '%" + keywords + "%'";
        Query<Equipment> query = session.createQuery(
                queryString,Equipment.class
        );
        List<Equipment> list = query.list();
        session.getClass();
        ArrayList<Equipment> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }

    /**
     * 获取数据库中所有设备的列表
     * @return 设备列表ArrayList
     */
    public static ArrayList<Equipment> getEquipmentList(){
        Session session = MySessionFactory.getSession();
        Query<Equipment> query = session.createQuery(
                "select i from Equipment i",Equipment.class
        );
        List<Equipment> list  = query.list();
        session.getClass();
        ArrayList<Equipment> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }
}
