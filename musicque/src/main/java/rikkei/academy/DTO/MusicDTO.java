package rikkei.academy.DTO;

import org.springframework.web.multipart.MultipartFile;

public class MusicDTO {
    private Long id;
    private String name;
    private String artist;
    private String category;
    private MultipartFile link;

    public MusicDTO() {
    }

    public MusicDTO(Long id, String name, String artist, String category, MultipartFile link) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.category = category;
        this.link = link;
    }

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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getLink() {
        return link;
    }

    public void setLink(MultipartFile link) {
        this.link = link;
    }
}
