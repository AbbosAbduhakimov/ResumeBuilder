package uz.abbos.resumebuilder.service;



import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.abbos.resumebuilder.dao.ImageDao;
import uz.abbos.resumebuilder.dto.ImageDto;
import uz.abbos.resumebuilder.exception.BadRequestException;
import uz.abbos.resumebuilder.model.Image;


@Service
public class ImageService {
    public ImageService(ModelMapper modelMapper, ImageDao imageDao) {
        this.modelMapper = modelMapper;
        this.imageDao = imageDao;
    }

    private final ModelMapper modelMapper;
    private final ImageDao imageDao;



    @Transactional
    public Long save(Image image) {
        try {

            return imageDao.save(image).getImageId();
        }
        catch (Exception e){
            throw new BadRequestException("Image create failed");
        }
    }


    @Transactional
    public byte[] get(Long imageId) {
        try {
            Image image = imageDao.find(imageId);
            return image.getData();
        }
        catch (Exception e){
            throw new BadRequestException("Image not found");
        }
    }


    public Image check(Long id) {
        Image image = imageDao.find(id);
        if (image == null){
            throw new BadRequestException("image not found");
        }
        return image;
    }
}
