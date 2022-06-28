package org.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "test_object")
@Data
public class TestObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_object_id", nullable = false)
    private long id;

    @Column(name = "param1", nullable = false)
    private int param1;

    @Column(name = "param2")
    private String param2;

    public static TestObject getTypicalTestObject() {
        TestObject testObject = new TestObject();
        testObject.setParam1(111);
        testObject.setParam2("some value");
        return testObject;
    }
}
