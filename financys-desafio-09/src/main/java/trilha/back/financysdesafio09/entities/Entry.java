package trilha.back.financysdesafio09.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import trilha.back.financysdesafio09.enums.EntryType;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private EntryType type;

    @Column(name = "amount")
    private String amount;

    @Column(name = "date")
    private  String date;

    @Column(name = "paid")
    private  boolean paid = false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    Category categoryId;



    public boolean getPaid() {
        return paid;
    }

    public void isPaid(boolean paid) {
        this.paid = paid;
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
}
