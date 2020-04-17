import config.SQLConfig;

import javax.sql.rowset.serial.SQLInputImpl;
import java.io.*;
import java.sql.SQLException;

public class ApplicationStart {
    public static void main(String[] args) {
        SQLConfig.getConnection();
        try {
            System.out.println(SQLConfig.getConnection().isClosed());
            SQLConfig.getConnection().prepareStatement(readDocument("create")).execute();
            SQLConfig.getConnection().prepareStatement(readDocument("populate")).execute();
            SQLConfig.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static String readDocument(String filename) {
        StringBuilder query = new StringBuilder();

        try {
            FileReader fileReader = (new FileReader("/home/daniel/workspace/EPLabBD/src/main/resources/queries"+ File.separator+filename+".sql"));
            while (fileReader.ready()){
                query.append(Character.toChars(fileReader.read()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query.toString();
    }
}
