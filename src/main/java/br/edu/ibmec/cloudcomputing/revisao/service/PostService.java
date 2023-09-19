package br.edu.ibmec.cloudcomputing.revisao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.cloudcomputing.revisao.model.Post;
import br.edu.ibmec.cloudcomputing.revisao.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    public Optional<Post> getById(long id) {
        return this.postRepository.findById(id);
    }

    public List<Post> getAll() {
        return this.postRepository.findAll();
    }

    public Post update(long id, Post newData) throws Exception {
        Optional<Post> opPost = this.postRepository.findById(id);

        if (opPost.isPresent() == false) {
            throw new Exception("Não encontrei o post a ser atualizado");
        }

        Post post = opPost.get();
        post.setTitle(newData.getTitle());
        post.setArticle(newData.getArticle());
        post.setAuthor(newData.getAuthor());
        post.setDtPublish(newData.getDtPublish());

        this.postRepository.save(post);

        return post;
    }

    public void delete(long id) throws Exception {
        Optional<Post> opPost = this.postRepository.findById(id);

        if (opPost.isPresent() == false) {
            throw new Exception("Não encontrei o post a ser atualizado");
        }

        this.postRepository.delete(opPost.get());
    }
}
