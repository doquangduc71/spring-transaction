package com.example.springtransaction.entity;

import com.example.springtransaction.enums.ErrorCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name="alllog")
@Table(name="alllog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllLog {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long fromID;

    private long toID;

    private long amount;

    private ErrorCode resultCode;

    private String detail;

    private Date createdOn;

    public AllLog (long fromID, long toID, Long amount,
                   ErrorCode resultCode, String detail){
        this.fromID = fromID;
        this.toID = toID;
        this.amount = amount;
        this.resultCode = resultCode;
        this.detail = detail;
        this.createdOn = new Date();
    }
}
