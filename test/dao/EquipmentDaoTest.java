package dao;

import entity.Equipment;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import java.sql.Timestamp;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/** 
* EquipmentDao Tester. 
* 
* @author <Authors name> 
* @since <pre>һ�� 3, 2017</pre> 
* @version 1.0 
*/ 
public class EquipmentDaoTest { 

    @Before
    public void before() throws Exception {
        System.out.println("EquipmentDaoTest Begin");
    }

    @After
    public void after() throws Exception {
        System.out.println("EquipmentDaoTest End");
    }

    /**
    *
    * Method: saveOrUpdateEquipment(Equipment equipment)
    *
    */
    @Test
    public void testSaveOrUpdateEquipment() throws Exception {
        Equipment equipment = new Equipment();
        equipment.setName("JUNIT Testing");
        equipment.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
        int result = EquipmentDao.saveOrUpdateEquipment(equipment);
        assertEquals(EquipmentDao.SAVEORUPDATE_SUCCESS,result);
    }

    /**
    *
    * Method: getEquipmentById(int id)
    *
    */
    @Test
    public void testGetEquipmentById() throws Exception {
        //1.测试正常情况
        Equipment equipment = EquipmentDao.getEquipmentById(1);
        assertTrue(equipment.getId() >= 0);
        assertTrue(equipment.getName() != null);
        assertTrue(equipment.getPurchaseDate() != null);
        //2.测试不存在情况
        Equipment equipmentNotExist = EquipmentDao.getEquipmentById(10000);
        assertTrue(equipmentNotExist == null);
    }

    /**
    *
    * Method: getEquipmentListByKeyWords(String keywords)
    *
    */
    @Test
    public void testGetEquipmentListByKeyWords() throws Exception {
        //1.测试正常情况
        ArrayList<Equipment> equipments = EquipmentDao.getEquipmentListByKeyWords("冀超");
        assertTrue(equipments.size() > 0);
        //2.测试异常情况
        ArrayList<Equipment> equipmentsNotExists = EquipmentDao.getEquipmentListByKeyWords("sdhssfajdkaljdqwd");
        assertTrue(equipmentsNotExists.size() == 0);
    }

    /**
    *
    * Method: getEquipmentList()
    *
    */
    @Test
    public void testGetEquipmentList() throws Exception {
        //1.测试正常情况
        ArrayList<Equipment> equipments = EquipmentDao.getEquipmentList();
        assertTrue(equipments.size() > 0);
        //2.没有异常情况
    }


} 
