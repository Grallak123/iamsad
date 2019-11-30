/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;



import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import model.Book;
import model.Library2Model;

public class Library2Controller {
    
    private final Library2Model model;
    private final Library2View view;

    public Library2Controller(Library2Model model, Library2View view) {
        this.model = model;
        this.view = view;
    }
    
    public void handleCheckLogin(String username,String password){
        System.out.println(username + " " + password);
        System.out.println("ree");
        try{
            model.tryToConnect(username,password);
            //view.mainMenuScene();
            view.bigTableBookScene();
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        } catch(Exception er){
            System.out.println(er.getMessage());
        }

        
    }
    
    
    

   
   public void handleCustomQuery(String chosenGenre,String chosenISBN,
           String chosenTitle,String chosenPublisher,String chosenAuthor){
                 //"SELECT * FROM Book"  
        String customQuery = "";
        
        customQuery += "Select book.*,authorname from book";

        if("Any".matches(chosenGenre.toString())){  
            customQuery += " join authorof on authorof.isbn = book.isbn";
            customQuery += " join author on author.authorid = authorof.authorid";
            customQuery += " where book.isbn like '%" + chosenISBN + "%'";
            customQuery += " and book.title like '%" + chosenTitle + "%'";
            customQuery += " and book.publisher like '%" + chosenPublisher + "%'";
            customQuery += " and author.authorname like '%" + chosenAuthor + "%'";
           // customQuery += " order by author.authorID ASC";
            customQuery += " order by isbn DESC";
            
        }else{ 
            customQuery += " join authorof on authorof.isbn = book.isbn";
            customQuery += " join author on author.authorid = authorof.authorid";
            customQuery += " where book.genre = '" + chosenGenre+"'"; 
            customQuery += " and book.isbn like '%" + chosenISBN + "%'";
            customQuery += " and book.title like '%" + chosenTitle + "%'";
            customQuery += " and book.publisher like '%" + chosenPublisher + "%'";
            customQuery += " and author.authorname like '%" + chosenAuthor + "%'";
            //customQuery += " order by author.authorID ASC";
            customQuery += " order by isbn DESC";
        }

        //System.out.println(customQuery);
        
        try{
            model.executeQuery2(customQuery);
        }catch(SQLException sqla){
            System.out.println(sqla.getMessage());
        }
        view.bigTableBookScene();
   }
   
   public void handleDeleteBookButton(){
       
      /* if(event.getSource() == deleteButton){
           
       }*/
   }
   
   public void handleSeeReviewsButton(ActionEvent event){
       
   }
    
   
   
   /* public void handlePrintQuery(String queryName){
        System.out.println(queryName);
        //System.out.println(model.getPassword() + " " + model.getUsername() 
          //      + " " + model.getCon());
                
        try{
            if(queryName.matches("Query1")){
                model.executeQuery1(model.getPassword(),
                        model.getUsername(),model.getDatabase(),
                        "SELECT * FROM Book");
            }
            if(queryName.matches("Query2")){
                model.executeQuery1(model.getPassword(),
                model.getUsername(),model.getDatabase(),
                "SELECT * FROM user where username = 'stardust' ");
            }
        }catch(SQLException sqla){
            System.out.println(sqla.getMessage());
        }

    }*/
    
    
    
}