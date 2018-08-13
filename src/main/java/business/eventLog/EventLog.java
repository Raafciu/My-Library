package business.eventLog;

import business.user.User;
import util.converter.EventLogEnumConverter;
import util.enums.EventLogEnum;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dziennik_zdarzen")
public class EventLog {

    @EmbeddedId
    private EventLogKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "akcja", length = 20, nullable = false)
    @Convert(converter = EventLogEnumConverter.class)
    private EventLogEnum akcja;

    public EventLogKey getId() {
        return id;
    }

    public void setId(EventLogKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventLogEnum getAkcja() {
        return akcja;
    }

    public void setAkcja(EventLogEnum akcja) {
        this.akcja = akcja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventLog eventLog = (EventLog) o;
        return Objects.equals(id, eventLog.id) &&
                Objects.equals(user, eventLog.user) &&
                akcja == eventLog.akcja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, akcja);
    }

    @Override
    public String toString() {
        return "EventLog{" +
                "id=" + id +
                ", user=" + user +
                ", akcja=" + akcja +
                '}';
    }
}
