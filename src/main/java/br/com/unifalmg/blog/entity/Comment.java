package br.com.unifalmg.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "db2020108017", name = "comment")
public class Comment implements Serializable {

    @Id
    private Integer id;

    private String name;

    private String email;

    private String  body;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


}
