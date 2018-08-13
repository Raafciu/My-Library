package business.eventLog;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
@SequenceGenerator(name = "dziennik_zdarzen_seq", sequenceName = "dziennik_zdarzen_seq", allocationSize = 1)
class EventLogKey implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dziennik_zdarzen_seq")
    @Column(name = "dziennik_id", unique = true, nullable = false, precision = 9)
    private long id;

    @Column(name = "data", nullable = false, unique = true, length = 50)
    private Date data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventLogKey that = (EventLogKey) o;
        return id == that.id &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        return "EventLogKey{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }
}
