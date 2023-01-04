package com.emailutility;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDetails {
    private String msgBody;
    private String subject;
    private String attachment;
}
