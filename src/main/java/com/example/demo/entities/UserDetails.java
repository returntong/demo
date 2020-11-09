package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.PrePersist;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private String username;
    private String password;
    private String createdBy;
    private Date createdTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = new Date();
        this.createdBy = getUsername();
    }

  /*  @PreUpdate
    protected void onUpdate() {
        this.updatedTime = new Date();
        this.updatedBy = getUsername();
    }
*/
    private String getUsername() {
        return getHeaderValue("X-username");
    }

    private String getHeaderValue(String headerKey) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        return servletRequest.getHeader(headerKey);
    }
}
