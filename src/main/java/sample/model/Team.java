package sample.model;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;
import sample.gui.GUIRepresentable;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by No3x on 03.02.2017.
 */
@Entity
public class Team implements GUIRepresentable, Comparable<Team>  {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private Set<Person> persons = new TreeSet<>();

    public Team(String s) {
        this.name.set(s);
    }

    public Team() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    public IntegerProperty idProperty() {
        return id;
    }
    @Basic
    @Column(name = "name")
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }

    @ManyToMany(mappedBy="teams")
    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    @Transient
    public String getTitle() {
        return name.getValue();
    }

    public static Callback<Team, Observable[]> extractor() {
        return (Team p) -> new Observable[]{p.nameProperty()};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (getId() != null ? !getId().equals(team.getId()) : team.getId() != null) return false;
        if (getName() != null ? !getName().equals(team.getName()) : team.getName() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name.getValue();
    }

    @Override
    public int compareTo(Team o) {
        return 0;
    }
}
