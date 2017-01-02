package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by jichao on 2016/12/26.
 *  * hibernate逆向工程自动生成的类，别乱动
 */
@Entity
public class Equipment {
    private int id;
    private Timestamp purchaseDate;
    private Timestamp scrapeDate;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "purchaseDate")
    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Basic
    @Column(name = "scrapeDate")
    public Timestamp getScrapeDate() {
        return scrapeDate;
    }

    public void setScrapeDate(Timestamp scrapeDate) {
        this.scrapeDate = scrapeDate;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Equipment equipment = (Equipment) o;

        if (id != equipment.id)
            return false;
        if (purchaseDate != null ? !purchaseDate.equals(equipment.purchaseDate) : equipment.purchaseDate != null)
            return false;
        if (scrapeDate != null ? !scrapeDate.equals(equipment.scrapeDate) : equipment.scrapeDate != null)
            return false;
        if (name != null ? !name.equals(equipment.name) : equipment.name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (purchaseDate != null ? purchaseDate.hashCode() : 0);
        result = 31 * result + (scrapeDate != null ? scrapeDate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
