package com.running.business.sdk.strategy;

import com.running.business.sdk.common.Request;
import com.running.business.sdk.common.Response;

public interface RemoteStrategy {

    void call(Request request, Response response);
}
