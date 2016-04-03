package com.alprojects.spr;



import com.alprojects.Student;
import com.alprojects.StudentAdd;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

/**
 * Created by andrew on 28.03.2016.
 */

// http://www.programcreek.com/java-api-examples/index.php?api=com.fasterxml.jackson.databind.module.SimpleModule

public class StudentObjectMapper extends ObjectMapper
{

    public StudentObjectMapper()
    {
        SimpleModule sm = new SimpleModule( "sm", new Version(1,0,0,null));

        sm.addSerializer(Student.class, new JsonSerializer<Student>()
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
        );

        sm.addDeserializer(Student.class, new JsonDeserializer<Student>()
            {
                @Override
                public Student deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException
                {
                    ObjectCodec oc = p.getCodec();
                    JsonNode student_node = oc.readTree( p );
                    Student newStudent = new Student();

                    JsonNode n = null;

                    if ( (n = student_node.get("Name")) != null)
                        newStudent.setName(n.textValue());
                    else
                        throw new IOException("Name field is not found");

                    if ( (n = student_node.get("Age")) != null)
                        newStudent.setAge( n.intValue() );
                    else
                        throw new IOException("Name field is not found");

                    if ( (n = student_node.get("PersonId")) != null)
                        newStudent.setId( n.intValue() );
                    else
                        newStudent.setId(-1);

                    return newStudent;
                }
            }
        );

        this.registerModule(sm);
    }


}
