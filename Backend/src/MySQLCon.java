import java.sql.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class MySQLCon {
    public static String ReadFile() {
        String data = "";
        File myObj = new File("credentials.txt");
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String Data = myReader.nextLine();
                data = Data;
            }
            myReader.close();

        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return data;
    }

    public static void GetDB(String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/weatherapp?characterEncoding=utf8", 
            "root", 
            password);

            Statement stmt = con.createStatement();
            String query = "SELECT * from apidata";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("Location: " + rs.getString("Location"));
            }
            con.close();
        }
        catch (Exception e)
        {System.out.println(e);}

    }

    public static void main(String[] args) {
        String pwd = ReadFile();
        GetDB(pwd);
        
    }
}


