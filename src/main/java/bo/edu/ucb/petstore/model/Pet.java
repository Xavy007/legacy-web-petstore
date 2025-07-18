package bo.edu.ucb.petstore.model;

public class Pet {
    private Long id;
    private String name;
    private String species;
    private int age;
    private double price;
    private boolean available;

    public Pet() {
    }

    public Pet(Long id, String name, String species, int age, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.price = price;
        this.available = available;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}