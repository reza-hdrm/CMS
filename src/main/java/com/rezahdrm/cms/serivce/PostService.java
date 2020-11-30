package com.rezahdrm.cms.serivce;

import com.rezahdrm.cms.model.Post;
import com.rezahdrm.cms.repositroy.PostRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class PostService {
    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public void create(){
        //TODO return category model (title , id)
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public Post findById(Long id){
        return postRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }
}
