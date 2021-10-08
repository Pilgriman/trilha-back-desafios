package trilha.back.financys.entities;

import javax.persistence.*;


@Entity
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(nullable = false,length = 40)
        private String name;

        @Column(nullable = false, length = 50)
        private String description;

        public Category() {}


        public Category(long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }


