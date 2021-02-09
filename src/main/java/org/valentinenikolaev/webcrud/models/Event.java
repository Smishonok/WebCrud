package org.valentinenikolaev.webcrud.models;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "date_time")
    private Instant dateTime;

    @Transient
    private Clock clock;

    public Event() {
        clock = Clock.systemUTC();
    }

    public Event(Long id, User user, File file, EventType eventType, Clock clock) {
        this.id = id;
        this.user = user;
        this.file = file;
        this.eventType = eventType;
        this.clock = clock;
        this.dateTime = Instant.now(clock);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
