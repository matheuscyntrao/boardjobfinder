package guavaStudyAnnotations.model;

import guavaStudyAnnotations.annotation.FieldFormatEnum;
import guavaStudyAnnotations.annotation.SerializerMethod;
import guavaStudyAnnotations.annotation.SerializerType;

@SerializerType(fieldFormat = FieldFormatEnum.KEBAB_CASE, prettify = false)
public class Person {

    private long id;
    private String name;
    private int age;

    public Person() {

    }

    public Person(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @SerializerMethod("firstPersonName")
    public String getFirstName() {
        if (this.name == null || this.name.isEmpty()) {
            return "";
        }
        return this.name.split(" ")[0];
    }

}
