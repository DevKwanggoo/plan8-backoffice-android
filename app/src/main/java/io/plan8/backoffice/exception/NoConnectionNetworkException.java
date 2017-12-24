package io.plan8.backoffice.exception;

import java.io.IOException;

/**
 * Created by chokwanghwan on 2017. 12. 22..
 */

public class NoConnectionNetworkException extends IOException {
    @Override
    public String getMessage() {
        return "No network available, please check your WiFi or Data connection";
    }
}
