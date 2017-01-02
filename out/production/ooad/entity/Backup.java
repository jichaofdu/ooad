package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by jichao on 2016/12/26.
 * hibernate逆向工程自动生成的类
 *
 */
@Entity
public class Backup {
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

        Backup backup = (Backup) o;

        if (id != backup.id)
            return false;
        if (purchaseDate != null ? !purchaseDate.equals(backup.purchaseDate) : backup.purchaseDate != null)
            return false;
        if (scrapeDate != null ? !scrapeDate.equals(backup.scrapeDate) : backup.scrapeDate != null)
            return false;
        if (name != null ? !name.equals(backup.name) : backup.name != null)
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
