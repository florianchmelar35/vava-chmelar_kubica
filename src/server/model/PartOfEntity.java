package server.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "part_of", schema = "organizeit", catalog = "VAVA")
public class PartOfEntity {
    private int id;
    private UserEntity userByIdUser;
    private GroupEntity groupByIdGroup;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartOfEntity that = (PartOfEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    public UserEntity getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(UserEntity userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_group", referencedColumnName = "id")
    public GroupEntity getGroupByIdGroup() {
        return groupByIdGroup;
    }

    public void setGroupByIdGroup(GroupEntity groupByIdGroup) {
        this.groupByIdGroup = groupByIdGroup;
    }
}
