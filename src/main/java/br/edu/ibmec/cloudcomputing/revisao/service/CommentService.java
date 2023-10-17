package br.edu.ibmec.cloudcomputing.revisao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.edu.ibmec.cloudcomputing.revisao.exception.CommentException;
import br.edu.ibmec.cloudcomputing.revisao.model.Comment;
import br.edu.ibmec.cloudcomputing.revisao.model.Post;
import br.edu.ibmec.cloudcomputing.revisao.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository repository;

    @Autowired 
    PostService postService;

    public List<Comment> findAll() {
        return this.repository.findAll();
    }

    public Optional<Comment> findById(long id) {
        return this.repository.findById(id);
    }

    public Comment update(long id, Comment newData) throws CommentException {
        Optional<Comment> opComment = this.repository.findById(id);

        if (opComment.isPresent() == false) {
            throw new CommentException("Não encontrei o comentário a ser atualizado");
        }

        Comment comment = opComment.get();

        comment.setAuthor(newData.getAuthor());
        comment.setText(newData.getText());
        comment.setDtComment(newData.getDtComment());

        this.repository.save(comment);

        return comment;
    }
    
    public Comment save(long idPost, Comment item) throws CommentException {
        Optional<Post> opPost = this.postService.getById(idPost);

        if (opPost.isPresent() == false) {
            throw new CommentException("Post não encontrado");
        }

        Post post = opPost.get();
        item.setPost(post);
        this.repository.save(item);
       
        return item;
    }

    public void delete(long id) throws CommentException {
        Optional<Comment> opPost = this.repository.findById(id);

        if (opPost.isPresent() == false) {
            throw new CommentException("Não encontrei o comentário a ser excluído");
        }

        this.repository.delete(opPost.get());
    }
    
}
