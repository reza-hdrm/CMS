package com.rezahdrm.cms.serivce;

import com.rezahdrm.cms.model.Post;
import com.rezahdrm.cms.repositroy.PostRepository;
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
        return postRepository.findAll();
    }


    public void save(Post post, MultipartFile photoFile) {
        if (post.getId() != null) {
            if (photoFile.isEmpty()) {
                postRepository.save(post);
                return;
            }else
                photoService.setDeletedAt(post.getPhotoId(),new Date());
        }
        Long photoId = photoService.save(photoFile);
        post.setPhotoId(photoId);
        postRepository.save(post);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public void delete(Long id) {
        //TODO Delete photo
        postRepository.deleteById(id);
    }
}
