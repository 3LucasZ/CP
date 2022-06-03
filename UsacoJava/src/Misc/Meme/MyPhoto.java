package Misc.Meme;

public class MyPhoto {
    public String photo;
    private final boolean likeCodeforces = true;
    public MyPhoto(String photoCode){
        this.photo = photoCode;
    }
    @Override
    public int hashCode(){
        return (int) (Math.random()*(1e9+7));
    }
    @Override
    public boolean equals(Object o){
        return false;
    }
}
