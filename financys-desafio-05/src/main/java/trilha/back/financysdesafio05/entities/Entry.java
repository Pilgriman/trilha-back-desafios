package trilha.back.financysdesafio05.entities;



import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "lancamentos")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private  String description;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private String amount;

    @Column(name = "date")
    private  String date;

    @Column(name = "paid")
    private  boolean paid = false;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    Category categoryId;


    public Entry(String name, String description, String type, String amount, String date, boolean paid, Category categoryId) {
        super();
        this.name = name;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.paid = paid;
        this.categoryId = categoryId;
    }


    public Entry() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getPaid() {
        return paid;
    }

    public void isPaid(boolean paid) {
        this.paid = paid;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return paid == entry.paid && Objects.equals(id, entry.id) && Objects.equals(name, entry.name) && Objects.equals(description, entry.description) && Objects.equals(type, entry.type) && Objects.equals(amount, entry.amount) && Objects.equals(date, entry.date) && Objects.equals(categoryId, entry.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, amount, date, paid, categoryId);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                ", paid=" + paid +
                ", categoryId=" + categoryId +
                '}';
    }
}

