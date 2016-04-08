package com.alprojects.mvc_ctrl;

import com.alprojects.data.Student;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StudentSerializer extends JsonSerializer<Student>
{
    @Override
    public void serialize(Student student, JsonGenerator jgen, SerializerProvider serializers) throws IOException, JsonProcessingException
    {
        jgen.writeStartObject();
        jgen.writeStringField( "Name", student.getName() );
        jgen.writeNumberField( "Age", student.getAge() );
        jgen.writeNumberField( "PersonId", student.getId() );
        jgen.writeEndObject();
    }
}
