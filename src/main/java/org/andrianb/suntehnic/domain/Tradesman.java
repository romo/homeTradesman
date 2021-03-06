package org.andrianb.suntehnic.domain;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AndrianB on 3/28/2017.
 */
@Entity
@Table(name = Tradesman.TABLE_NAME)
public class Tradesman implements Serializable {
    public static final String TABLE_NAME = "tradesman";

    public static final String TRADESMAN_ID = "tradesman_id";
    public static final String BIDS = "bids";
    public static final String TITLE = "title";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TRADESMAN_ID)
    private Long id;

    private String title;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = User.USER_ID)
    private User user;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tradesman_categories", joinColumns = @JoinColumn(name = TRADESMAN_ID
            , referencedColumnName = TRADESMAN_ID),
            inverseJoinColumns = @JoinColumn(name = "job_category_id", referencedColumnName = JobCategory.COLUMN_ID))
    private List<JobCategory> jobCategory;

    @OneToMany(mappedBy = Bid.COLUMN_SUBMITTER, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bid> bids;

    public Long getId() {
        return id;
    }

    public String getBusinessTitle() {
        return title;
    }

    public void setBusinessTitle(String businessTitle) {
        this.title = businessTitle;
    }

    public UserDetails getUserDetails() {
        return user;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<JobCategory> getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(List<JobCategory> service) {
        this.jobCategory = service;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
