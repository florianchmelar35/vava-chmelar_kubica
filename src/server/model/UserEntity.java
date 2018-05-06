package server.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "organizeit", catalog = "VAVA")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private Collection<GroupEntity> groupsById;
    private Collection<PartOfEntity> partOfsById;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password);
    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<GroupEntity> getGroupsById() {
        return groupsById;
    }

    public void setGroupsById(Collection<GroupEntity> groupsById) {
        this.groupsById = groupsById;
    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<PartOfEntity> getPartOfsById() {
        return partOfsById;
    }

    public void setPartOfsById(Collection<PartOfEntity> partOfsById) {
        this.partOfsById = partOfsById;
    }
}
