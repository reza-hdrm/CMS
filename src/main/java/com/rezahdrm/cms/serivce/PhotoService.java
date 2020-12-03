package com.rezahdrm.cms.serivce;

import com.ibm.icu.text.SimpleDateFormat;
import com.rezahdrm.cms.model.Photo;
import com.rezahdrm.cms.repositroy.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final SimpleDateFormat simpleDateFormat;

    public PhotoService(PhotoRepository photoRepository, SimpleDateFormat simpleDateFormat) {
        this.photoRepository = photoRepository;
        this.simpleDateFormat = simpleDateFormat;
    }

    public Long save(MultipartFile photoFile) {
        Photo photo = new Photo();
        String path = savePhotoFile(photoFile);
        photo.setPath(path);
        return photoRepository.save(photo).getId();
    }

    private String savePhotoFile(MultipartFile photoFile) {
        String fileName = photoFile.getOriginalFilename();
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf('.'));
        Random random = new Random();
        StringBuilder temporaryName = new StringBuilder();
        temporaryName.append(simpleDateFormat.format(new Date())).append(random.nextInt(100)).append(suffixName);
        File fileDirectory = new File("/root/IntelliJIDEAProjects/cms/src/main/resources/upload");
        File destFile = new File("/root/IntelliJIDEAProjects/cms/src/main/resources/upload/" + temporaryName);
        try {
            if (!fileDirectory.exists())
                if (!fileDirectory.mkdir())
                    throw new IOException("upload directory not found");
            photoFile.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile.getAbsolutePath();
    }

    public void setDeletedAt(Long photoId, Date DeletedAt) {
        photoRepository.setDeletedAt(photoId, DeletedAt);
    }
}

