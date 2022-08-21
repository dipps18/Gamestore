package package1;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.TimeZone;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class FuncLib {
    public static final String CONTENT_TYPE = "text/html; charset=windows-1250";
    public static final String SQL_SERVER_SOURCE = "SQL SERVER";
    public static final String ORACLE_SOURCE = "ORACLE";
    public static final String CONNECTION_SOURCE = SQL_SERVER_SOURCE;
    
    public static BigDecimal getGrossAmt(BigDecimal Price,BigDecimal Discount) {
        BigDecimal Gross_amt=new BigDecimal("0");
        BigDecimal Discounted_price=Price.subtract((Discount.divide(new BigDecimal("100"),RoundingMode.CEILING)).multiply(Price));
        Gross_amt=Discounted_price.add(Discounted_price.multiply(new BigDecimal("0.23")));
        return Gross_amt;
    }
    
    public static BigDecimal getDiscount(String date) {
        BigDecimal Discount=new BigDecimal("0");
        String[] dateParts=date.split("-");
        int curYear=parseInteger(getCurDate().split("-")[0]);
        int RelYear=parseInteger(dateParts[0]);
        if(((RelYear-curYear)>3)||((RelYear-curYear)<-3)) {
            Discount= new BigDecimal("20.0");
        }
        else{
        }
        return Discount;
    }
    public static String getCurDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        return formatter.format(date);
    }
    public static Integer parseInteger(String inputText) {
        Integer retVal;
        try {
            retVal = Integer.parseInt(inputText);
        } catch (Exception e) {
            retVal = null;
        }
        return retVal;
    }
    
    public static Double parseDouble(String inputText) {
        Double retVal;
        try {
            retVal = Double.parseDouble(inputText);
        } catch (Exception e) {
            retVal = null;
        }
        return retVal;
    }
    
    
    public static java.sql.Date parseDate(String inputText) {
        Date retDate;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        try {
            retDate = df.parse(inputText);
            String newDateString = df.format(retDate);
        } catch (Exception e) {
            retDate = null;
        }
        
        return new java.sql.Date(retDate.getTime());
    }


    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;

        try {
            if (CONNECTION_SOURCE == ORACLE_SOURCE) {
                InitialContext ic = null;
                try {
                    ic = new InitialContext();
                } catch (NamingException e) {
                }
                DataSource ds = null;
                try {
                    ds = (DataSource) ic.lookup("jdbc/myDS");
                } catch (NamingException e) {
                }
                con = ds.getConnection();

            } else if (CONNECTION_SOURCE == SQL_SERVER_SOURCE) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection("jdbc:sqlserver://localhost\\MSSQLSERVER:1433;databaseName=GAMESTORE", "sa", "Thighoe7");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

}
