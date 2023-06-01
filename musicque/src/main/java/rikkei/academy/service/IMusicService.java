package rikkei.academy.service;

import rikkei.academy.model.Music;

import java.util.List;

public interface IMusicService {
    List<Music> findAll();
    Music findById(Long id);
    void save(Music music);
    void deleteById(Long id);
}
