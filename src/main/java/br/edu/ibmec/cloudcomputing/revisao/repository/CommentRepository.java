package br.edu.ibmec.cloudcomputing.revisao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ibmec.cloudcomputing.revisao.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> { }
