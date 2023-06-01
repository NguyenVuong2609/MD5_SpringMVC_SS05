package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academy.DTO.MusicDTO;
import rikkei.academy.model.Music;
import rikkei.academy.service.IMusicService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/music"})
public class MusicController {
    @Autowired
    private IMusicService musicService;
    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping
    public String showListMusic(Model model) {
        List<Music> musicList = musicService.findAll();
        model.addAttribute("listMusic", musicList);
        return "music/list";
    }
    @GetMapping("/{id}")
    public String detailMusic(@PathVariable Long id, Model model){
        Music music = musicService.findById(id);
        model.addAttribute("music", music);
        return "music/detail";
    }
    @GetMapping("/create")
    public String showFormCreate( Model model){
        MusicDTO music = new MusicDTO();
        model.addAttribute("music", music);
        return "music/create";
    }
    @PostMapping("/create")
    public String actionCreate(MusicDTO musicDTO, RedirectAttributes rs){
        MultipartFile multipartFile = musicDTO.getLink();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(fileUpload+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Music music = new Music(musicDTO.getId(), musicDTO.getName(), musicDTO.getArtist(), musicDTO.getCategory(),fileName);
        musicService.save(music);
        rs.addFlashAttribute("validate", "create music success!");
        return "redirect:/";
    }
}
