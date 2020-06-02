package config;

import com.google.gson.internal.GsonBuildConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import utils.GsonSerializer;
import utils.PropertyUtils;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Producer implements Closeable {
    private static Producer producer;
    private static final List<KafkaProducer> kafkaProducers = new ArrayList<KafkaProducer>();
    PropertyUtils propertyUtils = PropertyUtils.getInstance();

    public static synchronized final Producer getInstance() {
        if (producer == null)
            producer = new Producer();
        return producer;
    }

    private Producer() {
        for (int i = 0; i < Integer.parseInt(propertyUtils.getProperty("MIN_PRODUCERS")); i++) {
            kafkaProducers.add(new KafkaProducer(propertyUtils.getProperties()));
        }
    }

    public void kafkaSendMessage(Object o) throws ExecutionException, InterruptedException {
        String uuid = UUID.randomUUID().toString();

        KafkaProducer producer = getProducer(GsonBuildConfig.class.getName());
        producer.send(new ProducerRecord<>(propertyUtils.getProperty("kafka.source.main.topic"), uuid, o), ((metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
                return;
            }
            System.out.println("Enviado com sucesso para o tópico: " + metadata.topic() + ":::partition " + metadata.partition() + " /offset" + metadata.offset() + " /timestamp " + metadata.timestamp() + "/key " + uuid.toString());
        }));
    }

    private KafkaProducer getProducer(String... name) {

        if (name == null) {
            if (kafkaProducers.isEmpty()) {
                for (int i = 0; i < Integer.parseInt(propertyUtils.getProperty("MIN_PRODUCERS")); i++) {
                    kafkaProducers.add(new KafkaProducer(propertyUtils.getProperties()));
                }
            }
        } else {
            propertyUtils.setProperties(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
            kafkaProducers.set(0, new KafkaProducer(propertyUtils.getProperties()));
        }
        return kafkaProducers.remove(0);
    }

    private int retrieveProducer(KafkaProducer kafkaProducer) throws Exception {
        if (kafkaProducers.size() > Integer.parseInt(propertyUtils.getProperty("MAX_PRODUCERS"))) {
            kafkaProducer.close();
            throw new Exception("Número máximo de conexões abertas. Encerrando conexão.");
        } else kafkaProducers.add(kafkaProducer);
        return kafkaProducers.size();
    }

    @Override
    public void close() {
        closeAllConnections();

    }

    private void closeAllConnections() {
        kafkaProducers.forEach(kafkaProducer -> kafkaProducer.close());
        kafkaProducers.clear();
    }
}
