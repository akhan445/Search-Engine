# Search-Engine
Java search engine application using an inverted index to map data and implement the strategy design pattern to perform various types of search queries

This is the search engine which is a project which is part of the the hyperskill/jetbrains academy website. 

The inverted index is stored using a Map for easy access.

The Source files for this project can be found by filepath:

    task/src/search/

This Directory contains 3 files:
 
 1. Main.java
 
      Contains the logic of the search engine program. First the data is read through the source file and is used to create an inverted index of all terms in the "database".
      
      Then user is prompted to choose an option through a menu and if chosen to perform a search query, the user can choose which search method to perform and finally once user is done, they may exit the program.
      
2. SearchingMethod.java
 
      This file contains the strategy interface for the family of search algorithms.
      
      Within the same file there are 3 concrete strategy classes which follow the strategy interface and implement search methods
        
        a) AllSearchingMethod Class - for "ALL" search strategy. The program results in terms containing all words from search query. 
        
        b) AnySearchingMethod Class - for "ANY" search strategy. The program results in terms containing at least one word(s) from the search query
        
        c) NoneSearchingMethod - for "NONE" search strategy. The program results in terms that do not contain any words entered in the search query
        

2. PersonSearcher.java
 
      This file is the special Context Class for storing a reference to the strategy.
  

