package guavaStudyAnnotations.processor;

import guavaStudyAnnotations.annotation.SerializerMethod;
import guavaStudyAnnotations.annotation.SerializerType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class SerializerProcessor {

    public String serializer(final Object obj) throws IllegalAccessException, InvocationTargetException {
        Objects.requireNonNullElse(obj, "Enter with no null object");
        var clazz = obj.getClass();
        var typeAnnotation = Stream.of(clazz.getAnnotations())
                .flatMap(a -> (a instanceof SerializerType s)? Stream.of(s) : Stream.empty())
                /*.filter(SerializerType.class::isInstance)
                .map(SerializerType.class::cast)*/
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Annotation with @SerializerType"));
        var fieldNameFormatter = typeAnnotation.fieldFormat().getFormat();
        var prettyfy = typeAnnotation.prettify();

        Map<String, Object> elements = new HashMap<>();
        for (var field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            elements.put(field.getName(), field.get(obj));
        }

        var annotatedMethods = Stream.of(obj.getClass().getMethods())
                .filter(m -> Stream.of(m.getAnnotations())
                        .anyMatch(a -> a.annotationType().equals(SerializerMethod.class))).toList();

        for (var method: annotatedMethods) {
            method.setAccessible(true);
            var customName = method.getAnnotation(SerializerMethod.class).value();
            elements.put(customName.isBlank() ? method.getName() : customName, method.invoke(obj));
        }

        var jsonFields = elements.entrySet().stream()
                .map(e -> String.format(
                        "    \"%s\": %s",
                        fieldNameFormatter.apply(e.getKey()),
                        formatValue(e.getValue())
                        ))
                .collect(joining(String.format(",%s", System.lineSeparator())));
        var json = String.format("{%s%s%s}",
                System.lineSeparator(),
                jsonFields,
                System.lineSeparator());

        return prettyfy ? json : json.replaceAll(System.lineSeparator(), "")
                .replaceAll("\\\s+", " ");
    }

    private String formatValue(final Object value) {
        return value instanceof String s ? String.format("\"%s\"", s) : value.toString();
    }

}
