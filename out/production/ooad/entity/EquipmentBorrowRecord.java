package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jichao on 2016/12/26.
 *  * hibernate逆向工程自动生成的类，别乱动
 */
@Entity
@Table(name = "equipment_borrow_record", schema = "ooad", catalog = "")
public class EquipmentBorrowRecord {
    private int id;
    private int userId;
    private int equipmentId;
    private Timestamp borrowDate;
    private Timestamp returnDate;


    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "equipmentId")
    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Basic
    @Column(name = "borrowDate")
    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Basic
    @Column(name = "returnDate")
    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EquipmentBorrowRecord that = (EquipmentBorrowRecord) o;

        if (userId != that.userId)
            return false;
        if (equipmentId != that.equipmentId)
            return false;
        if (id != that.id)
            return false;
        if (borrowDate != null ? !borrowDate.equals(that.borrowDate) : that.borrowDate != null)
            return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + equipmentId;
        result = 31 * result + (borrowDate != null ? borrowDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
