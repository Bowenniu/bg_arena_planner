package student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList; 
import java.util.stream.Collectors;

/**
 *  GameList.java implements IGameList interface to manage the list of board games.
 */
public class GameList implements IGameList {
    /**
     * Create a list to store the board games.
     */
    private List<BoardGame> games;
    /**
     * Constructor for the GameList.
     */
    public GameList() {
        this.games = new ArrayList<>();
        }
    /**
     * @return A list of strings representing the game names.
     */
    @Override
    public List<String> getGameNames() {
       List<String> gameNames = new ArrayList<>();
       games.forEach(game -> gameNames.add(game.getName()));
       gameNames.sort(String.CASE_INSENSITIVE_ORDER);
       return gameNames;
    }
    // Clear the list of the board games/remove all the games from the list.
    @Override
    public void clear() {
        games.clear();    
    }
    /**
     * @return The total count of the board games in the list
     */
    @Override
    public int count() {
       return games.size();
    }
    /**
     * Save the name list of the games into the file.
     * The new games will be writen to the new line of the file.
     * 
     * @param filename  The name of the file which saves the games list.
     */
    @Override
    public void saveGame(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            List<String> gameNames = getGameNames();
            for (String name : gameNames) {
                writer.write(name);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving game list to file: " + e.getMessage());
        }
    }
    /**
     * Define a constant string to represent the command to add elements.
     */
    private static final String ADD_ALL = "ADD_ALL";
    // Override addToList method, to add elements into the list.
    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {
        // Collect the filtered stream into List.
        List<BoardGame> filteredGames = filtered.collect(Collectors.toList());
        // If the added str equals ADD_ALL(ignores the upper and lower cases)
        if (str.equalsIgnoreCase(ADD_ALL)) {
            // Add all elements in filteredGames into the game List.
            games.addAll(filteredGames);
        } else {
            /** 
             * Else use "-" to split the string.
             */
            String[] parts = str.split("-");
            int start, end;

            try {
                /**
                 * If there is only one part.
                 */
                if (parts.length == 1) {
                    /**
                     * Both start and end are set to the same index.
                     */
                    start = end = Integer.parseInt(parts[0].trim()) - 1;
                // If there are 2 parts, throw and exception.
                } else if (parts.length == 2) {
                    start = Integer.parseInt(parts[0].trim()) - 1;
                    end = Integer.parseInt(parts[1].trim()) - 1;
                } else {
                    throw new IllegalArgumentException("Invalid input format");
                }
                // Validate the indices.
                if (start < 0 || end >= filteredGames.size() || start > end) {
                    // Throw exception if the indices are out of bounds.
                    throw new IllegalArgumentException("Index out of bounds");
                }
                // Add games from the filtered list to the games list between the specified indices.
                for (int i = start; i <= end; i++) {
                    games.add(filteredGames.get(i));
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                // throw exception if there is an issue with parsing the indices or accessing the list.
                throw new IllegalArgumentException("Invalid input format or index out of bounds");
            }
        }
    }
    /**
     * Define a constant string to represent the command to remove all elements.
     */
    private static final String REMOVE_ALL = "REMOVE_ALL";
    // Override the removeFromList method to remove the elements from list.
    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        // If the input string matches "REMOVE_ALL" 
        if (str.equalsIgnoreCase(REMOVE_ALL)) {
            games.clear(); // clear the list.
        } else {
            /**
             * Split the input string by using "-" 
             */
            String[] parts = str.split("-");
            int start, end;

            try { 
                // If there is only 1 part.
                if (parts.length == 1) {
                    /**
                     * set start and end to the same index.
                     */
                    start = end = Integer.parseInt(parts[0].trim()) - 1;
                    // If there are 2 parts, set start and end to the respective indices.
                } else if (parts.length == 2) {
                    start = Integer.parseInt(parts[0].trim()) - 1;
                    end = Integer.parseInt(parts[1].trim()) - 1;
                } else {
                    // If there are more or less that 2 parts, throw exception.
                    throw new IllegalArgumentException("Invalid input format");
                }
                // If the indices are out of bonds, throw exception.
                if (start < 0 || end >= games.size() || start > end) {
                    throw new IllegalArgumentException("Index out of bounds");
                }
                // Remove the elemnts from the list in reverse order.
                for (int i = start; i <= end; i++) {
                    games.remove(i);
                }
                // Throw exception if there is an issue parsing the indices.
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input format or index out of bounds");
            }
        }
    }
}
