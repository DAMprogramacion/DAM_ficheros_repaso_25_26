package examen;

public record Mobile(String mobileBrand,String mobileModel, String mobileOS,
                     int mobileRelease,double mobilePrice) {
    @Override
    public String toString() {
        String precioFormateado = String.format("%.2f", mobilePrice).replace(',','.');
        return String.format("%s,%s,%s", mobileBrand, mobileModel, precioFormateado);
    }

    public static void main(String[] args) {
        Mobile mobile = new Mobile("marca" , "modelo", "OS",
                2000, 120.23);
        System.out.println(mobile);
    }
}
