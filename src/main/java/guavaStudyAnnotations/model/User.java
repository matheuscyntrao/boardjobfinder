package guavaStudyAnnotations.model;

import guavaStudyAnnotations.annotation.FieldFormatEnum;
import guavaStudyAnnotations.annotation.SerializerType;

@SerializerType(fieldFormat = FieldFormatEnum.SNAKE_CASE)
public record User (
    long id,
    String fullName,
    int age,
    double salary
) {

}
