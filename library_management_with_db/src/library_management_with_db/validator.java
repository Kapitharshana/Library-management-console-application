package library_management_with_db;

import java.util.regex.Pattern;

public class validator {
	
	   
		 
		 //id should be in letters whether they can be capital or small or else both mixed	 
	     private static Pattern AuthorTitle_Pattern=Pattern.compile("^[a-zA-Z ]+$");
	     
	
	     public boolean validateAuthorTitle(String title, String author){
	    	 
		 boolean result;
       
         if(AuthorTitle_Pattern.matcher(title).matches() && AuthorTitle_Pattern.matcher(author).matches() ){
               
                result=true;
            }
            else{
            	result=false;
            }
         
         return result;
         
	     }
	     
        
  }


