package model.admin_side;

import java.io.File;
import java.util.Objects;

public class CategoryData {
    private int id = Integer.MAX_VALUE;
    private String name;
    private Boolean isDisplayed;
    private String description;
    private String coverImage;

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getDisplayed() {
        return isDisplayed;
    }

    public String getDescription() {
        return description;
    }

    public File getCoverImage() {
        return new File(coverImage);
    }

    //Setters
    public CategoryData setId(int id) {
        this.id = id;
        return this;
    }

    public CategoryData setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryData setDisplayed(Boolean displayed) {
        isDisplayed = displayed;
        return this;
    }

    public CategoryData setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryData setCoverImage(File coverImage) {
        this.coverImage = coverImage.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "CategoryData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryData category = (CategoryData) o;
        return id == category.id &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
