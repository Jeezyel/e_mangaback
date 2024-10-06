package model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MangaGenre {
    ACTION(1, "Action"),
    ADVENTURE(2, "Adventure"),
    COMEDY(3, "Comedy"),
    DRAMA(4, "Drama"),
    FANTASY(5, "Fantasy"),
    HORROR(6, "Horror"),
    MYSTERY(7, "Mystery"),
    PSYCHOLOGICAL(8, "Psychological"),
    ROMANCE(9, "Romance"),
    SCI_FI(10, "Sci-Fi"),
    SLICE_OF_LIFE(11, "Slice of Life"),
    SPORTS(12, "Sports"),
    SUPERNATURAL(13, "Supernatural"),
    THRILLER(14, "Thriller"),
    SHOUNEN(15, "Shounen"),
    SHOJO(16, "Shojo"),
    SEINEN(17, "Seinen"),
    JOSEI(18, "Josei"),
    ISEKAI(19, "Isekai"),
    MECHA(20, "Mecha"),
    HAREM(21, "Harem"),
    ECCHI(22, "Ecchi"),
    YAOI(23, "Yaoi"),
    YURI(24, "Yuri"),
    HISTORICAL(25, "Historical"),
    MARTIAL_ARTS(26, "Martial Arts"),
    MUSIC(27, "Music"),
    PARODY(28, "Parody"),
    SCHOOL(29, "School"),
    GAME(30, "Game"),
    VAMPIRE(31, "Vampire"),
    MAGIC(32, "Magic"),
    DEMONS(33, "Demons");

    private int id;
    private String label;

    MangaGenre(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static MangaGenre valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (MangaGenre genre : MangaGenre.values()) {
            if (id.equals(genre.getId()))
                return genre;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
