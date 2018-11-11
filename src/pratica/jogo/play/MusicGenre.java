package pratica.jogo.play;

public class MusicGenre {

    private int id;
    String name;
    private String description;
    private String imgUrl;
    private String soundUrl;
    private String spotName;
    private int[] spotXY;

    public MusicGenre(int id, String name, String description, String imgUrl, String soundUrl, String spotName, int[] spotXY) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.soundUrl = soundUrl;
        this.spotName = spotName;
        this.spotXY = spotXY;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public int[] getSpotXY() {
        return spotXY;
    }

    public void setSpotXY(int[] spotXY) {
        this.spotXY = spotXY;
    }

}
