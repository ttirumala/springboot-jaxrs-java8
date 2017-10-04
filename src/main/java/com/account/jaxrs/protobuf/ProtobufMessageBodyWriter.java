package com.account.jaxrs.protobuf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.account.jaxrs.patch.MediaTypes;
import com.google.protobuf.Message;
import org.springframework.stereotype.Component;

@Provider
@Component
@Produces({MediaTypes.APPLICATION_PROTOBUF})
public class ProtobufMessageBodyWriter implements MessageBodyWriter<Message> {
    /**
     * a cache to save the cost of duplicated call(getSize, writeTo) to one
     * object.
     */
    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return Message.class.isAssignableFrom(type);
    }

   // private Map<Object, byte[]> buffer = new WeakHashMap<Object, byte[]>();

    public long getSize(Message m, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
/*        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            m.writeTo(baos);
        } catch (IOException e) {
            return -1;
        }
        byte[] bytes = baos.toByteArray();
        buffer.put(m, bytes);
        return bytes.length;*/
        return m.getSerializedSize();
    }

    @Override
    public void writeTo(Message m, Class type, Type genericType,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {
        entityStream.write(m.toByteArray());
    }
}