package dao;

import entity.Equipment;
import entity.EquipmentBorrowRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.MySessionFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jichao on 2016/12/26.
 */
public class EquipmentDao {

    public static final int SAVEORUPDATE_SUCCESS = 200;
    public static final int SAVEORUPDATE_FAIL = 201;

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

    public static Equipment getEquipmentById(int id){
        Session session = MySessionFactory.getSession();
        Query<Equipment> query = session.createQuery(
                "select i from Equipment i where i.id = :id",Equipment.class
        ).setParameter("id",id);
        Equipment equipment = query.uniqueResult();
        session.close();
        return equipment;
    }

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

/*    public static boolean isBorrowed(int equipmentId){
        Session session = MySessionFactory.getSession();
        Query<EquipmentBorrowRecord> query = session.createQuery("select max(i) from EquipmentBorrowRecord i where i.equipmentId = :id", EquipmentBorrowRecord.class)
                .setParameter("id", equipmentId);
        EquipmentBorrowRecord record = query.uniqueResult();
        session.close();
        if(record == null){
            return true;
        }else {
            return false;
        }
    }*/

    public static Timestamp[] getEquipmentDate(int equipmentId){
        Timestamp[] equipmentDate = new Timestamp[2];
        Session session = MySessionFactory.getSession();
        Query<EquipmentBorrowRecord> query = session.createQuery("select max(i) from EquipmentBorrowRecord i where i.equipmentId = :id", EquipmentBorrowRecord.class)
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

}
