package guavaStudyAnnotations.model;

import guavaStudyAnnotations.annotation.SerializerType;

@SerializerType
public record User (
    long id,
    String fullName,
    int age,
    double salary
) {

}
