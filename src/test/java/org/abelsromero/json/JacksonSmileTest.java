package org.abelsromero.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonSmileTest {


    @Test
    public void should_serialize_and_deserialize_json_with_LocalDate() throws IOException {
        // given
        final ObjectMapper objectMapper = objectMapper(false);

        final SimplePojo instance = new SimplePojo();
        instance.setText("why 42?");
        instance.setValue(42);
        instance.setLocaldate(LocalDate.now());

        // when
        byte[] json = objectMapper.writeValueAsBytes(instance);
        System.out.println(new String(json));
        SimplePojo deserializedInstance = objectMapper.readValue(json, SimplePojo.class);

        // then
        assertThat(deserializedInstance)
                .isEqualToComparingFieldByField(instance);
    }

    @Test
    public void should_serialize_and_deserialize_smile_without_LocalDate() throws IOException {
        // given
        final ObjectMapper objectMapper = objectMapper(true);

        final SimplePojo instance = new SimplePojo();
        instance.setText("why 42?");
        instance.setValue(42);

        // when
        byte[] json = objectMapper.writeValueAsBytes(instance);
        System.out.println(new String(json));
        SimplePojo deserializedInstance = objectMapper.readValue(json, SimplePojo.class);

        // then
        assertThat(deserializedInstance)
                .isEqualToComparingFieldByField(instance);
    }

    @Test
    public void should_serialize_and_deserialize_smile_with_LocalDate() throws IOException {
        // given
        final ObjectMapper objectMapper = objectMapper(true);

        final SimplePojo instance = new SimplePojo();
        instance.setText("why 42?");
        instance.setValue(42);
        instance.setLocaldate(LocalDate.now());

        // when
        byte[] json = objectMapper.writeValueAsBytes(instance);
        System.out.println(new String(json));
        SimplePojo deserializedInstance = objectMapper.readValue(json, SimplePojo.class);

        // then
        assertThat(deserializedInstance)
                .isEqualToComparingFieldByField(instance);
    }

    private static ObjectMapper objectMapper(boolean smile) {
        final SimpleModule module = new SimpleModule();
//        module.addSerializer(LocalDate.class, new LocalDateSerializer());
//        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        return smile ? new ObjectMapper(new SmileFactory()) : new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .registerModule(module)
                ;
    }
}
