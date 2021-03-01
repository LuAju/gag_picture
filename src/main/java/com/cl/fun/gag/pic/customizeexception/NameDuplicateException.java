package com.cl.fun.gag.pic.customizeexception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameDuplicateException extends RuntimeException {
    private String errorMsg;
}
