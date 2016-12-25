package us.inal.sna;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

/**
 * Created by alpereninal on 22/12/16.
 */
@NodeEntity
public class Takdir {

    @GraphId
    private Long id;

    private String postName;
    private String postText;
    private Double totalBalance;
    private String lmsReturnCode;
    private Integer languageId;
    private Integer fromUserId;
    private Integer nId;

    @DateString("yyyy-MM-dd\'T\'HH:mm:ss.SS")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }


    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getLmsReturnCode() {
        return lmsReturnCode;
    }

    public void setLmsReturnCode(String lmsReturnCode) {
        this.lmsReturnCode = lmsReturnCode;
    }

    public Integer getnId() {
        return nId;
    }

    public void setnId(Integer nId) {
        this.nId = nId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }
}
