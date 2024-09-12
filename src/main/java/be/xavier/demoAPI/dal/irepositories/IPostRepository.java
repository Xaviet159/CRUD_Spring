package be.xavier.demoAPI.dal.irepositories;

import be.xavier.demoAPI.dl.entities.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {


}
