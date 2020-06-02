import config.Consumer;
import config.Producer;
import config.SQLConfig;
import objects.SQLObject;
import org.apache.kafka.common.serialization.StringSerializer;
import utils.PropertyUtils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ApplicationStart {
    static PropertyUtils propertyUtils = PropertyUtils.getInstance();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        if (Arrays.stream(args).anyMatch(s -> s.contains("-sql")))
            sqlWay();

        if (Arrays.stream(args).anyMatch(s -> s.contains("-develop")))
            openDevelopConsole();

    }




    private static void sqlWay() {
        SQLConfig.getConnection();
        try {
            System.out.println(SQLConfig.getConnection().isClosed() ? "Server off" : "Server on");
            SQLConfig.getConnection().prepareStatement(readDocument(propertyUtils.getProperty("query.create"))).execute();
            SQLConfig.getConnection().prepareStatement(readDocument(propertyUtils.getProperty("query.populate"))).execute();
            SQLConfig.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void openDevelopConsole() throws ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String s = "";
        Producer producer = Producer.getInstance();
        while (scanner.hasNext()) {

            while (!s.endsWith(";\n"))
                s += scanner.nextLine()+ "\n";

//        System.out.println(s);


            producer.kafkaSendMessage(new SQLObject(s));
            s = "";
        }
        producer.close();
    }

    private static String readDocument(String filename) {
        StringBuilder query = new StringBuilder();

        try {
            FileReader fileReader = (new FileReader(propertyUtils.filePath(filename)));
            while (fileReader.ready()) {
                query.append(Character.toChars(fileReader.read()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query.toString();
    }
}
