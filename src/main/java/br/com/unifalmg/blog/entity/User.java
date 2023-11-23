package br.com.unifalmg.blog.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "db", name = "user")
public class User implements Serializable {

    @Id
    private Integer id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String website;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    // TODO: Add a list of comments
}
