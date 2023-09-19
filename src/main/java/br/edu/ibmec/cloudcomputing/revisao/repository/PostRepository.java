package br.edu.ibmec.cloudcomputing.revisao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ibmec.cloudcomputing.revisao.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> { }
