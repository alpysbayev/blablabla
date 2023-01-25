package com.prometeo.drp_final.utils.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prometeo.drp_final.utils.exception.ResourceNotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
class Resultset<T> {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private T data;

    private Resultset()
    {
        this.timestamp = LocalDateTime.now();
    }

    public Resultset(T o, HttpStatus status ) throws ResourceNotFoundException
    {
        this();
        if ( o == null || ( o instanceof List && ( ( List<?> ) o ).isEmpty() ) )
            throw new ResourceNotFoundException( "No Content Found" );
        this.status = status;
        this.data = o;
    }

}
