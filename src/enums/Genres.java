package enums;

public class Genres {
    public enum BookGenre {
        FICTION(1),
        NON_FICTION(2),
        MYSTERY(3),
        FANTASY(4),
        ROMANCE(5),
        SCIENCE_FICTION(6),
        BIOGRAPHY(7);

        private final int id;

        // Constructor
        BookGenre(int id) {
            this.id = id;
        }

        // Getter for the ID
        public int getId() {
            return id;
        }

        // Get Genre by ID
        public static BookGenre getById(int id) {
            for (BookGenre genre : BookGenre.values()) {
                if (genre.getId() == id) {
                    return genre;
                }
            }
            throw new IllegalArgumentException("Invalid genre ID: " + id);
        }

        @Override
        public String toString() {
            return id + " - " + this.name();
        }
    }

}
