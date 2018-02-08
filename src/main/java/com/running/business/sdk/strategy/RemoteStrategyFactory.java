package com.running.business.sdk.strategy;

/**
 * @author liumingyu
 * @create 2018-01-20 下午3:43
 */
public class RemoteStrategyFactory {
    private static RemoteStrategyFactory ourInstance = new RemoteStrategyFactory();

    public static RemoteStrategyFactory getInstance() {
        return ourInstance;
    }

    public RemoteStrategy getRemoteStrategy(boolean flag) {
        if (flag) {
            CallStrategy callStrategy = new CallStrategy();
            return callStrategy;
        } else {
            HttpStrategy httpStrategy = new HttpStrategy();
            return httpStrategy;
        }
    }

    private RemoteStrategyFactory() {
    }
}
