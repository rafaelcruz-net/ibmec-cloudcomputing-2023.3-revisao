package br.edu.ibmec.cloudcomputing.revisao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ibmec.cloudcomputing.revisao.exception.BusinessException;
import br.edu.ibmec.cloudcomputing.revisao.exception.PostException;
import br.edu.ibmec.cloudcomputing.revisao.model.Post;
import br.edu.ibmec.cloudcomputing.revisao.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AzureStorageAccountService azureStorageAccountService;

    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    public Optional<Post> getById(long id) {
        return this.postRepository.findById(id);
    }

    public List<Post> getAll() {
        return this.postRepository.findAll();
    }

    public void saveOrUpdate(Post item) {
        this.postRepository.save(item);
    }

    public Post update(long id, Post newData) throws PostException {
        Optional<Post> opPost = this.postRepository.findById(id);

        if (opPost.isPresent() == false) {
            throw new PostException("Não encontrei o post a ser atualizado");
        }

        Post post = opPost.get();
        post.setTitle(newData.getTitle());
        post.setArticle(newData.getArticle());
        post.setAuthor(newData.getAuthor());
        post.setDtPublish(newData.getDtPublish());

        this.postRepository.save(post);

        return post;
    }

    public void delete(long id) throws PostException {
        Optional<Post> opPost = this.postRepository.findById(id);

        if (opPost.isPresent() == false) {
            throw new PostException("Não encontrei o post a ser excluído");
        }

        this.postRepository.delete(opPost.get());
    }

    public void uploadFileToPost(MultipartFile file, long id) throws PostException, Exception {
        
        Optional<Post> opPost = this.postRepository.findById(id);
        
        if (opPost.isPresent() == false) {
            throw new PostException("Não encontrei o post para associar a imagem");
        }

        Post post = opPost.get();
        String ulrImage = this.azureStorageAccountService.uploadFileToAzure(file);
        post.setUrlImage(ulrImage);
        this.postRepository.save(post);
    }
}
