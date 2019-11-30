/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Book;



public class Library2Model {

    private Connection con;
    
    private String user = "lurker";
    private String pwd = "321";
    private String database = "library";
    
    private ArrayList<Book> booklist;
    private Book book;
    private String server = "jdbc:mysql://localhost:3306/" + database +
        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    
    
    public Library2Model() {
        Connection con = null;
        booklist = new ArrayList<Book>();
       
    }
    
    public Connection getCon(){
        return this.con;
    }
    
    public String getUsername(){
        return this.user;
    }
    
    public String getPassword(){
        return this.pwd;
    }
    
    public String getDatabase(){
        return this.database;
    }
    
    public String getServer(){
        return this.server;
    }
    
    public ArrayList<Book> getBooks(){
        
        return this.booklist;
    }
    
    public void testBooks(){
        
        
        for(Book book : booklist){
            System.out.println(book);
            
        }
    }
    
    public void tryToConnect(String username, String password) throws Exception{
        /*if (args.length != 2) {
            System.out.println("Usage: java JDBCTest <user> <password>");
            System.exit(0);
        }*/

        //String user = args[0]; // user name
        //String pwd = args[1]; // password 
        this.user = username;
        this.pwd = password;
        System.out.println(user + ", *********");
        this.database = "library"; // the name of the specific database 
        this.server
                = "jdbc:mysql://localhost:3306/" + database
                + "?UseClientEnc=UTF8";
        //actually this but ok lol
        server = "jdbc:mysql://localhost:3306/" + database +
        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Connection con = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(server, user, pwd);
            System.out.println("Connected!");
            

            //executeQuery(con, "SELECT * FROM Book");
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
            }
        }
    }
    
    public void tryToCloseConnection(){
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Connection Terminanted!");
                }
            } catch (SQLException e) {
            }
    }

    // WRONG!!!!!!!!!
   /* public void executeQuery1(String pass, String uname,
            String db,String query) throws SQLException {
        
        
         try {
            
            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(server, uname, pass);
            System.out.println("Connected!");
        
            try (Statement stmt = con.createStatement()) {
                // Execute the SQL statement
                ResultSet rs = stmt.executeQuery(query);

                // Get the attribute names
                ResultSetMetaData metaData = rs.getMetaData();
                int ccount = metaData.getColumnCount();
                
              
                for (int c = 1; c <= ccount; c++) {
                    System.out.print(metaData.getColumnName(c) + "\t");
                    
                }
                System.out.println();
                this.booklist.clear();
                // Get the attribute values
                while (rs.next()) {
                    // NB! This is an example, -not- the preferred way to retrieve data.
                    // You should use methods that return a specific data type, like
                    // rs.getInt(), rs.getString() or such.
                    // It's also advisable to store each tuple (row) in an object of
                    // custom type (e.g. Employee).
                    ArrayList<String> tmpBookList = new ArrayList<String>();
                    ArrayList<Author> tmpAuthorList = new ArrayList<Author>();
                    for (int c = 1; c <= ccount; c++) {
                        tmpBookList.add(rs.getString(c));
                        
                        
                        System.out.print(rs.getObject(c) + "\t");
                       
                    }
                    
                    Book.Genre bookGenre = Book.Genre.valueOf(tmpBookList.get(1));
                    tmpAuthorList.add(new Author(rs.getString(5)));
                    //System.out.print("fag" + tmpAuthorList.toString());
                    
                    try{
                        book = new Book(tmpBookList.get(0),bookGenre,tmpBookList.get(2),
                        tmpBookList.get(3),tmpAuthorList);
                        
                        if(book == null){
                            throw new Exception("Book was dead");
                        }
                        this.booklist.add(book);
                        book = null;
                    }catch(IllegalArgumentException ile){
                        System.out.println(ile.getMessage());
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                    System.out.println();
                }   
                stmt.close();
            }    
            
            
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
            }
        }
    }*/
    
    public void executeQuery2(String query) throws SQLException {
        
         
         try {
            
            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(server, this.user, this.pwd);
            System.out.println("Connected!");
            boolean ItsTreasonThen = false;
            ArrayList<String> tmpSeenISBNList = new ArrayList<String>();
            Statement stmt = null;
            //Statement stmt2 = null; 
            try{
                stmt = con.createStatement();
                //stmt2 = con.createStatement();
                
                // Execute the SQL statement
                
                ResultSet rs = stmt.executeQuery(query);
                

                // Get the attribute names
                ResultSetMetaData metaData = rs.getMetaData();
                int ccount = metaData.getColumnCount();
                
              
                for (int c = 1; c <= ccount; c++) {
                    System.out.print(metaData.getColumnName(c) + "\t");
                    
                }
                System.out.println();
                
                
                this.booklist.clear();
                
                int bookCounter = 0;
                boolean secondLife = true;
                ArrayList<Author> tmpAuthorList = new ArrayList<Author>();;
                //ArrayList<String> tmpSeenISBNList = new ArrayList<String>();
                // Get the attribute values
                while (rs.next()) {
                    // NB! This is an example, -not- the preferred way to retrieve data.
                    // You should use methods that return a specific data type, like
                    // rs.getInt(), rs.getString() or such.
                    // It's also advisable to store each tuple (row) in an object of
                    // custom type (e.g. Employee).
                    ArrayList<String> tmpBookList = new ArrayList<String>();
                    
                    for (int c = 1; c <= ccount; c++) {
                        tmpBookList.add(rs.getString(c));
                        
                        
                        System.out.print(rs.getObject(c) + "\t");
                       
                    }
                        
                    Book.Genre bookGenre = Book.Genre.valueOf(tmpBookList.get(1));
                    
                    //System.out.print("fag" + tmpAuthorList.toString());
                    //System.out.print(bookGenre);
                    
                    try{
                        if(!tmpSeenISBNList.contains(tmpBookList.get(0))){
                            //System.out.println("good");
                            tmpSeenISBNList.add(tmpBookList.get(0));
                            tmpAuthorList.clear();
                            tmpAuthorList.add(new Author(rs.getString(5)));
                            book = new Book(tmpBookList.get(0),bookGenre,tmpBookList.get(2),
                            tmpBookList.get(3),tmpAuthorList);
                            this.booklist.add(book);
                            bookCounter++;
                            ItsTreasonThen = true;
                            if(secondLife){
                                bookCounter--;
                                secondLife = false;
                            }
                            
                            /* tmpISBN = tmpBookList.get(0);
                            );
                            IAmTheOneAndOnly = true;
                           book = new Book(tmpBookList.get(0),bookGenre,tmpBookList.get(2),
                            tmpBookList.get(3),tmpAuthorList);*/
                           
                        }
                        else{
                           // System.out.println("bad");
                           
                           this.booklist.get(bookCounter).getTheAuthors().add
                            (new Author(rs.getString(5)));
                            
                           /* this.booklist.get(bookCounter).getTheAuthors().add(new Author(rs.getString(5)));
                            tmpAuthorList.clear();
                            IAmTheOneAndOnly = false;*/
                        }

                        
                        if(book == null){
                            throw new Exception("Book was dead");
                        }
                        
                        book = null;
                        
                    }catch(IllegalArgumentException ile){
                        System.out.println(ile.getMessage());
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                    System.out.println();
                }   
                
               
                
            }finally{

                
               
               stmt.close(); 
            } 
            
            //do the all author check call here
            if(ItsTreasonThen){
                String Order66 = createOrder66(tmpSeenISBNList);
                executeOrder66(Order66);
            }
            
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
            }
        }
    } 
  
    public String createOrder66(ArrayList<String> usedISBN){
        
        String Order66 = "";
        System.out.println(usedISBN);
        /*select authorof.*,authorname from authorof
        join author on authorof.authorID = author.authorID
        where authorof.isbn ='9789144131757' or authorof.isbn ='9789144069197';*/
        
        Order66 += "select authorof.*,authorname from authorof"
                + " join author on authorof.authorID = author.authorID"
                + " where";
        /*for (Author authors : authorsInput) {
                this.theAuthors.add(authors);
                  
        } */
        for(String younglings : usedISBN){
            Order66 += " authorof.isbn = '" + younglings + "' or";
        }
        Order66 += " authorof.isbn = '9999999999999'  order by ISBN DESC";
        
       
        System.out.println(Order66);
        
        
        return Order66;
    }
    
    public void executeOrder66(String Order66) throws SQLException{
        
            Statement stmt = null;
            //Statement stmt2 = null; 
            try{
                stmt = con.createStatement();
                //stmt2 = con.createStatement();
                
                // Execute the SQL statement
                
                ResultSet rs = stmt.executeQuery(Order66);
                

                // Get the attribute names
                ResultSetMetaData metaData = rs.getMetaData();
                int ccount = metaData.getColumnCount();
                
              
                for (int c = 1; c <= ccount; c++) {
                    System.out.print(metaData.getColumnName(c) + "\t");
                    
                }
                System.out.println();
                
                int safeRow = 0;
                //int numbersOfRows = 0;
                /*for (Book books : this.booklist) {
                
                  numbersOfRows++;
                } */
               // System.out.println("saferow = " + safeRow);
               // System.out.println("cancer")
                String tempISBN = null;
                boolean tryonce = false;
                while (rs.next()) {
                    
                    if(!tryonce){
                        tempISBN = rs.getString(1);
                        System.out.println(tempISBN);
                        tryonce = true;
                    }
                    
                    
                    for (int c = 1; c <= ccount; c++) {
                        
                       System.out.print(rs.getObject(c) + "\t");
                       
                    }
  
                    
                    try{
                       
                       
                       /* if(!this.booklist.get(safeRow).getTheAuthors().contains(
                                new Author(rs.getString(3)))){
                             System.out.println("hello");
                            this.booklist.get(safeRow).getTheAuthors().add(
                            new Author(rs.getString(3)));
                        
                        }*/
                       // System.out.println("I suck " +this.booklist.get(safeRow).toString());
                       System.out.println(rs.getString(1));
                       //tempISBN = this.booklist.get(1).getIsbn();
                       if(this.booklist.get(safeRow).getIsbn().equals(rs.getString(1))){      
                           
                           
                               
                            if(!this.booklist.get(safeRow).getTheAuthors().contains(new Author(rs.getString(3)))){
                                this.booklist.get(safeRow).getTheAuthors().add(
                                new Author(rs.getString(3)));
                            }

                       }else{
                            
                            //tempISBN = this.booklist.get(safeRow).getIsbn();
                            safeRow++;
                            if(!this.booklist.get(safeRow).getTheAuthors().contains(new Author(rs.getString(3)))){
                                this.booklist.get(safeRow).getTheAuthors().add(
                                new Author(rs.getString(3)));
                            }
                            
                       }

                        //safeRow++;
                        
                        if(book == null){
                            throw new Exception("Book was reee");
                        }
                        
                        book = null;
                        
                    }catch(IllegalArgumentException ile){
                        System.out.println(ile.getMessage());
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                    System.out.println();
                }   
                
               
                
            }finally{

               stmt.close(); 
            }        
        

        
    }

    
      
    
}