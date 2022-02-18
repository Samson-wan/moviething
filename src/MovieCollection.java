import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.*;

public class MovieCollection
{
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName)
  {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  
  public void menu()
  {
    String menuOption = "";
    
    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");
    
    while (!menuOption.equals("q"))
    {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();
      
      if (!menuOption.equals("q"))
      {
        processOption(menuOption);
      }
    }
  }
  
  private void processOption(String option)
  {
    if (option.equals("t"))
    {
      searchTitles();
    }
    else if (option.equals("c"))
    {
      searchCast();
    }
    else if (option.equals("k"))
    {
      searchKeywords();
    }
    else if (option.equals("g"))
    {
      listGenres();
    }
    else if (option.equals("r"))
    {
      listHighestRated();
    }
    else if (option.equals("h"))
    {
      listHighestRevenue();
    }
    else
    {
      System.out.println("Invalid choice!");
    }
  }
  
  private void searchTitles()
  {
    System.out.print("Enter a tital search term: ");
    String searchTerm = scanner.nextLine();
    
    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();
    
    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();
    
    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();
      
      if (movieTitle.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }
    
    // sort the results by title
    sortResults(results);
    
    // now, display them all to the user    
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();
      
      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;
      
      System.out.println("" + choiceNum + ". " + title);
    }
    
    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");
    
    int choice = scanner.nextInt();
    scanner.nextLine();
    
    Movie selectedMovie = results.get(choice - 1);
    
    displayMovieInfo(selectedMovie);
    
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void sortResults(ArrayList<Movie> listToSort)
  {
    for (int j = 1; j < listToSort.size(); j++)
    {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();
      
      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
      {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }
  
  private void displayMovieInfo(Movie movie)
  {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }
  
  private void searchCast()
  {
    
  }

  private void searchKeywords()
  {
    
  }
  
  private void listGenres()
  {
  
  }
  
  private void listHighestRated()
  {
  
  }
  
  private void listHighestRevenue()
  {
  
  }

  public boolean containsLetters(String text) {
    String alphabet = "abcdefghijklmnopqrstuvw!?xyz\"";
    for (int i = 0; i < text.length(); i++) {
      if (alphabet.contains(text.toLowerCase().substring(i,i+1))) {
        return true;
      }
    }
    return false;
  }

  
  private void importMovieList(String fileName)
  {
    try
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      movies = new ArrayList<Movie>();

      try {
        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();

        while (line != null) {
          sb.append(line);
          sb.append(System.lineSeparator());
          line = bufferedReader.readLine();
        }
        String everything = sb.toString();
//        System.out.println(everything.substring(0, 10)); //works!!
      } finally {
        bufferedReader.close();
      }

      
//      while ((line = bufferedReader.readLine()) != null)
//      {
//        String[] movieFromCSV = line.split(",");
//
//        String title = movieFromCSV[5];
//        String cast = movieFromCSV[6];
//        String director = movieFromCSV[8];
//        String tagline = movieFromCSV[9];
//        String keywords = movieFromCSV[10];
//        String overview = movieFromCSV[11]; // The overview seems to always start at index 11
//        // but if it's long enough it can spill into other indexes...
//        // write somethign to deal with that obviously
//
//        for (int i = 0; i < movieFromCSV.length; i++) {
//          System.out.println(i + ": " + movieFromCSV[i]);
//        }
//        int lastOverviewIndex = 11;
//
//        System.out.println(movieFromCSV.length);
//        while (containsLetters(movieFromCSV[lastOverviewIndex+1])) {
//           lastOverviewIndex++;
//        }
//        System.out.println(movieFromCSV[lastOverviewIndex+1]);
//        System.out.println("LOI: " + lastOverviewIndex);
//        int runtime = Integer.parseInt(movieFromCSV[lastOverviewIndex+1]);
//        String genres = movieFromCSV[lastOverviewIndex+2];
//        double userRating = Double.parseDouble(movieFromCSV[lastOverviewIndex+6]);
//        int year = Integer.parseInt(movieFromCSV[lastOverviewIndex+7]);
//        int revenue = (int)Double.parseDouble((movieFromCSV[lastOverviewIndex+9]));
//
//        Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
//        movies.add(nextMovie);
//      }
      bufferedReader.close();
    }
    catch(IOException exception)
    {
      // Print out the exception that occurred
      System.out.println("Unable to access " + exception.getMessage());              
    }
  }
}