package guavaStudyAnnotations;

import guavaStudyAnnotations.model.Person;
import guavaStudyAnnotations.model.User;
import guavaStudyAnnotations.processor.SerializerProcessor;

import java.lang.reflect.InvocationTargetException;

public class Main {

    static void main() throws InvocationTargetException, IllegalAccessException {
        var processor = new SerializerProcessor();
        System.out.println(processor.serializer(
                new User(1, "Matheus Cyntrao", 13, 10.0)));

        System.out.println(processor.serializer(
                new Person(1, "Matheus Cyntrao", 13)));
    }

}
