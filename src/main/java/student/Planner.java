package student;

import java.util.Set;
import java.util.stream.Stream;
import java.util.Comparator;

/*
 * Implements the IPlanner interface to filter and sort a collection of board games. 
 */

public class Planner implements IPlanner { 
    private Set<BoardGame> games;
    /**
     * Build Planner with a set of board games.
     * @param games the set of the board games to manage
     */
    public Planner(Set<BoardGame> games) {
        this.games = games;    
    }

    /**
     * @return a stream of filtered and sorted board games.
     */
    @Override
    public Stream<BoardGame> filter(String filter) {
        return filter(filter, null, true);
    }
    /**
     * @return a stream of filtered and sorted boards games.
     */
    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        return filter(filter, sortOn, true);
    }
    /**
     * @return a stream of filtered and sorted boards games.
     */
    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        Stream<BoardGame> filteredStream = games.stream();
        // Apply the filter form the filter string
        if (filter != null && !filter.trim().isEmpty()) {
            String[] filters = filter.split(",");
            for (String f : filters) {
                filteredStream = applyFilter(f.trim(), filteredStream);
            }
        }
        // Sort the stream if specified.
        if (sortOn != null) {
            filteredStream = sortStream(filteredStream, sortOn, ascending);
        }

        return filteredStream;
    }
    // Reset the filter to unmatched.
    @Override
    public void reset() {
        games.stream().forEach(game -> game.setMatched(false));    
    }

    private Stream<BoardGame> applyFilter(String filter, Stream<BoardGame> stream) {
        // Implement your filter logic based on the filter string
        // Example: minPlayers>4, maxPlayers<6, name~=pandemic, etc.
        // You can use Predicate and other functional interfaces to compose filters
        throw new UnsupportedOperationException("Filtering logic not implemented yet");
    }

    // Helper method to sort the stream based on a given column and order
    private Stream<BoardGame> sortStream(Stream<BoardGame> stream, GameData sortOn, boolean ascending) {
        Comparator<BoardGame> comparator = Comparator.comparing(BoardGame::getName, String.CASE_INSENSITIVE_ORDER);
        // Determind the comparator based on the sort column
        if (sortOn == GameData.ID) {
            comparator = Comparator.comparingInt(BoardGame::getId);
        } else if (sortOn == GameData.RATING) {
            comparator = Comparator.comparingDouble(BoardGame::getRating);
        } else if (sortOn == GameData.DIFFICULTY) {
            comparator = Comparator.comparingDouble(BoardGame::getDifficulty);
        } else if (sortOn == GameData.RANK) {
            comparator = Comparator.comparingInt(BoardGame::getRank);
        } else if (sortOn == GameData.MIN_PLAYERS) {
            comparator = Comparator.comparingInt(BoardGame::getMinPlayers);
        } else if (sortOn == GameData.MAX_PLAYERS) {
            comparator = Comparator.comparingInt(BoardGame::getMaxPlayers);
        } else if (sortOn == GameData.MIN_TIME) {
            comparator = Comparator.comparingInt(BoardGame::getMinPlayTime);
        } else if (sortOn == GameData.MAX_TIME) {
            comparator = Comparator.comparingInt(BoardGame::getMaxPlayTime);
        } else if (sortOn == GameData.YEAR) {
            comparator = Comparator.comparingInt(BoardGame::getYearPublished);
        }
        // Revese the comparator is it is not ascending(descending)
        if (!ascending) {
            comparator = comparator.reversed();
        }

        return stream.sorted(comparator);
    }
}
