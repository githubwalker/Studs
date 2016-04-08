package com.alprojects.mvc_ctrl;

import com.alprojects.data.Student;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class StudentDeserializer extends JsonDeserializer<Student>
{
    @Override
    public Student deserialize(JsonParser p, DeserializationContext ctxt) throws IOException //, JsonProcessingException
    {
        ObjectCodec oc = p.getCodec();
        JsonNode student_node = oc.readTree( p );
        Student newStudent = new Student();

        JsonNode n;

        if ( (n = student_node.get("Name")) != null)
            newStudent.setName(n.textValue());
        else
            throw new IOException("Name field is not found");

        if ( (n = student_node.get("Age")) != null)
            newStudent.setAge( n.asInt() );
        else
            throw new IOException("Name field is not found");

        if ( (n = student_node.get("PersonId")) != null)
            newStudent.setId(n.asInt());
        else
            newStudent.setId(-1);

        return newStudent;
    }
}

