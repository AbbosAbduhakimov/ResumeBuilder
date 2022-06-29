package uz.abbos.resumebuilder.restcontroller;


import com.fasterxml.classmate.types.ResolvedArrayType;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.abbos.resumebuilder.dto.ImageDto;
import uz.abbos.resumebuilder.model.Image;
import uz.abbos.resumebuilder.service.ImageService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/files")
public class ImageController {


    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    private final ImageService imageService;

    @PostMapping
    Long uploadImage(@RequestParam("file") MultipartFile multipartImage) throws IOException {
        Image Image = new Image();
        Image.setFileName(multipartImage.getName());
        Image.setData(multipartImage.getBytes());


        return imageService.save(Image);
    }


    @GetMapping(value = "/image/{imageId}",produces = MediaType.ALL_VALUE)
    Resource downloadImage(@PathVariable("imageId") Long imageId) {
        byte[] image = imageService.get(imageId);
        return new ByteArrayResource(image);
    }
}
