package com.rezahdrm.cms.service;

import com.rezahdrm.cms.model.Post;
import com.rezahdrm.cms.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PhotoService photoService;

    public PostService(PostRepository postRepository, PhotoService photoService) {
        this.postRepository = postRepository;
        this.photoService = photoService;

    }

    public List<Post> findAll() {
        return postRepository.findAllByDeletedAtIsNull();
    }


    public void save(Post post, MultipartFile photoFile) {
        if (post.getId() != null) {
            if (photoFile.isEmpty()) {
                postRepository.save(post);
                return;
            } else
                photoService.softDelete(post.getPhotoId());
        }
        Long photoId = photoService.save(photoFile);
        post.setPhotoId(photoId);
        postRepository.save(post);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public void softDelete(Long id) {
        photoService.softDelete(this.findById(id).getPhotoId());
        postRepository.setDeletedAt(id, new Date());
    }

    public void restrictDelete(Long id) {
        postRepository.deleteById(id);
    }

    public void restore(Long id) {
        postRepository.setNullDeletedAt(id);
    }


}
