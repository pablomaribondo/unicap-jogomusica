package game.play;

public class MusicGenre {

    private String name;
    private String description;
    private String imgUrl;
    private String soundUrl;
    private String outfitUrl;
    private int spotX;
    private int spotY;

    public MusicGenre(String name, String description, String imgUrl, String soundUrl, String outfitUrl, int spotX, int spotY) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.soundUrl = soundUrl;
        this.outfitUrl = outfitUrl;
        this.spotX = spotX;
        this.spotY = spotY;
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

    public String getOutfitUrl() {
        return outfitUrl;
    }

    public void setOutfitUrl(String outfitUrl) {
        this.outfitUrl = outfitUrl;
    }

    public int getSpotX() {
        return spotX;
    }

    public void setSpotX(int spotX) {
        this.spotX = spotX;
    }

    public int getSpotY() {
        return spotY;
    }

    public void setSpotY(int spotY) {
        this.spotY = spotY;
    }

}
