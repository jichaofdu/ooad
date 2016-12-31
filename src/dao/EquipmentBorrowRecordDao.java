package dao;

import entity.EquipmentBorrowRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.MySessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaoj on 2016/12/31.
 */
public class EquipmentBorrowRecordDao {

    public static final int SAVEORUPDATE_SUCCESS = 200;
    public static final int SAVEORUPDATE_FAIL = 201;

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
                "select i from EquipmentBorrowRecord i where i.equipmentId = :equipmentId",EquipmentBorrowRecord.class
        ).setParameter("equipmentId",equipmentId);
        List<EquipmentBorrowRecord> list  = query.list();
        session.getClass();
        ArrayList<EquipmentBorrowRecord> resultList = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }
}
