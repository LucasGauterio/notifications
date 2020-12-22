package com.cvccorp.notifications.notifications.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {

    private String sender_id;
    private String recipient_id;
    private Context context;
    private String type;
    private String template;
    private Object data;
    private List<Attachment> attachments = new ArrayList<>();

}
