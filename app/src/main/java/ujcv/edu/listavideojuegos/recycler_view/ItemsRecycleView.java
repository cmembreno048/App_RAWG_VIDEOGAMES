package ujcv.edu.listavideojuegos.recycler_view;

public class ItemsRecycleView {

    private String urlImage;
    private String nombre_Juego;
    private Double rating;

    public ItemsRecycleView(String urlImage, String nombre_Juego, Double rating) {
        this.urlImage = urlImage;
        this.nombre_Juego = nombre_Juego;
        this.rating = rating;
    }

    public String getUrlImage() { return urlImage; }

    public String getNombre_Juego() { return nombre_Juego; }

    public Double getRating() { return rating; }




}
