package cucumber.customDataTableType;

public class PersonData {
    String name;
    String genre;
    String language;

    public PersonData(String name, String genre, String language) {
        this.name = name;
        this.genre = genre;
        this.language = language;
    }

    @Override
    public String toString() {
        return String.format("{Name = %s, Genre = %s, Language = %s}", name, genre, language);
    }
}
