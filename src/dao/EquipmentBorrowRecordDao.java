package dao;

import entity.EquipmentBorrowRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.MySessionFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jichao on 2016/12/31.
 */
public class EquipmentBorrowRecordDao {

    public static final int SAVEORUPDATE_SUCCESS = 400;
    public static final int SAVEORUPDATE_FAIL = 401;

    /**
     * 租借设备，向数据库中增加一个租借条目
     * @param record 记录的具体内容
     * @return 返回新增成功或者失败。
     */
    public static int borrowEquipment(EquipmentBorrowRecord record){
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

    public static ArrayList<EquipmentBorrowRecord> getEquipmentBorrowRecordListByStaffId(int staffId){
        Session session = MySessionFactory.getSession();
        Query<EquipmentBorrowRecord> query = session.createQuery(
                "select i from EquipmentBorrowRecord i where i.userId = :staffId",EquipmentBorrowRecord.class
        ).setParameter("staffId",staffId);
        List<EquipmentBorrowRecord> list  = query.list();
        session.getClass();
        ArrayList<EquipmentBorrowRecord> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }

    public static ArrayList<EquipmentBorrowRecord> getEquipmentBorrowRecordListByEquipmentId(int equipmentId){
        Session session = MySessionFactory.getSession();
        Query<EquipmentBorrowRecord> query = session.createQuery(
                "select i from EquipmentBorrowRecord i where i.equipmentId = :equipmentId",
                EquipmentBorrowRecord.class
        ).setParameter("equipmentId",equipmentId);
        List<EquipmentBorrowRecord> list  = query.list();
        session.getClass();
        ArrayList<EquipmentBorrowRecord> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }

    public static Timestamp[] getEquipmentDate(int equipmentId){
        Timestamp[] equipmentDate = new Timestamp[2];
        Session session = MySessionFactory.getSession();
        Query<EquipmentBorrowRecord> query = session.createQuery("select max(i) from EquipmentBorrowRecord i where i.equipmentId = :id",
                EquipmentBorrowRecord.class)
                .setParameter("id", equipmentId);
        EquipmentBorrowRecord record = query.uniqueResult();
        session.close();
        if(record == null){
            equipmentDate[0] = null;
            equipmentDate[1] = null;
        }else {
            equipmentDate[0] = record.getBorrowDate();
            equipmentDate[1] = record.getReturnDate();
        }
        return equipmentDate;
    }

    public static EquipmentBorrowRecord getUserEquipmentById(int userId, int equipmentId){
        Session session = MySessionFactory.getSession();
        Query<EquipmentBorrowRecord> query =
                session.createQuery("select max(i) from EquipmentBorrowRecord i where i.equipmentId = :equipmentId and i.userId = :userId",
                        EquipmentBorrowRecord.class)
                .setParameter("userId", userId).setParameter("equipmentId", equipmentId);
        EquipmentBorrowRecord record = query.uniqueResult();
        session.close();
        if(record == null){
            return null;
        }else {
            return record;
        }
    }

    public static ArrayList<EquipmentBorrowRecord> getOwnEquipment(int userId) {
        Session session = MySessionFactory.getSession();
        Query<EquipmentBorrowRecord> query =
                session.createQuery("select i from EquipmentBorrowRecord i where i.userId = :userId and i.returnDate is null",
                        EquipmentBorrowRecord.class)
                        .setParameter("userId", userId);
        List<EquipmentBorrowRecord> list = query.list();
        ArrayList<EquipmentBorrowRecord> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            result.addAll(list);
        }
        return result;
    }
}
