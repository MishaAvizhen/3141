package com.avizhen.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lord")
public class Lord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @OneToMany(mappedBy = "lord", cascade = {CascadeType.ALL})
    private List<Universe> lordUniverseList;

    public Lord() {
    }

    public Lord(String name, Integer age, List<Universe> lordUniverseList) {
        this.name = name;
        this.age = age;
        this.lordUniverseList = lordUniverseList;
    }

    public List<Universe> getLordUniverseList() {
        return lordUniverseList;
    }

    public void setLordUniverseList(List<Universe> lordUniverseList) {
        this.lordUniverseList = lordUniverseList;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
