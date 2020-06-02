package config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import utils.PropertyUtils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Consumer implements Closeable {

    private static Consumer consumer;
    PropertyUtils propertyUtils = PropertyUtils.getInstance();
    List<KafkaConsumer> kafkaConsumers = new ArrayList<>();

    public static void main(String[] args) throws SQLException, IOException {
        getInstance();
        consumer.consume();
    }


    synchronized public static Consumer getInstance() {
        if (consumer == null)
            consumer = new Consumer();
        return consumer;
    }

    public Consumer() {
        createConsumers();
    }

    private void createConsumers() {
        for (int i = 0; i < Integer.parseInt(propertyUtils.getProperty("MIN_PRODUCERS")); i++) {
            kafkaConsumers.add(new KafkaConsumer<Object, Object>(propertyUtils.getProperties()));
            kafkaConsumers.get(i).subscribe(Collections.singletonList(propertyUtils.getProperty("kafka.source.main.topic")));
        }
    }

    private KafkaConsumer getConsumer() {
        if (kafkaConsumers.isEmpty()) {
            createConsumers();
        }
        return kafkaConsumers.remove(0);
    }

    public void consume() throws SQLException, IOException {
        KafkaConsumer consumer = getConsumer();
        consumer.subscribe(Collections.singletonList(propertyUtils.getProperty("kafka.source.main.topic")));
        while (true) {
            ConsumerRecords records = consumer.poll(Duration.ofMillis(1000));
            if (!records.isEmpty()) {
                print(records);
            }
        }
    }

    private void print(ConsumerRecords<Object, Object> records) throws SQLException, IOException {
        SQLConfig.getConnection();
        List<ResultSet> resultSets = new ArrayList<>();
        for (ConsumerRecord record : records) {
            String string = (String) record.value();

//            if (string.startsWith("SELECT")) {
//                resultSets.add(SQLConfig.getConnection().createStatement().executeQuery(string));
//            }

            System.out.println(string);

        }
//        SQLConfig.getConnection().close();
//
//        resultSets.forEach(resultSet -> {
//
//            try {
//                ResultSetMetaData metaData = resultSet.getMetaData();
//                for (int i = 1; i < metaData.getColumnCount(); i++) {
//                    System.out.print("|" + metaData.getColumnLabel(i) + "|");
//                }
//                System.out.println();
//                while (resultSet.next()) {
//                    for (int i = 1; i < metaData.getColumnCount(); i++) {
//                        System.out.print("|" + resultSet.getString(i) + "|");
//                    }
//                    System.out.println();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//        );


    }

    @Override
    public void close() throws IOException {
        for (KafkaConsumer kafkaConsumer : kafkaConsumers) {
            kafkaConsumer.close();
        }
    }
}


