package org.jiumao.remote.client;


import org.jiumao.remote.exception.RemotingConnectException;
import org.jiumao.remote.exception.RemotingSendRequestException;
import org.jiumao.remote.exception.RemotingTimeoutException;
import org.jiumao.remote.exception.RemotingTooMuchRequestException;
import org.jiumao.remote.service.InvokeCallback;
import org.jiumao.remote.service.RemotingCommand;
import org.jiumao.remote.service.RemotingService;


/**
 * 远程通信，Client接口
 */
public interface RemotingClient extends RemotingService {

    long TimeoutMillis = 300;


    public RemotingCommand invokeSync(final RemotingCommand request) throws InterruptedException,
            RemotingConnectException, RemotingSendRequestException, RemotingTimeoutException;


    public RemotingCommand invokeSync(final String addr, final RemotingCommand request,
            final long timeoutMillis) throws InterruptedException, RemotingConnectException,
            RemotingSendRequestException, RemotingTimeoutException;


    public void invokeAsync(final RemotingCommand request, final InvokeCallback invokeCallback)
            throws InterruptedException, RemotingConnectException, RemotingTooMuchRequestException,
            RemotingTimeoutException, RemotingSendRequestException;


    public void invokeAsync(final String addr, final RemotingCommand request, final long timeoutMillis,
            final InvokeCallback invokeCallback) throws InterruptedException, RemotingConnectException,
            RemotingTooMuchRequestException, RemotingTimeoutException, RemotingSendRequestException;


    public void invokeOneway(final RemotingCommand request) throws InterruptedException,
            RemotingConnectException, RemotingTooMuchRequestException, RemotingTimeoutException,
            RemotingSendRequestException;


    public void invokeOneway(final String addr, final RemotingCommand request, final long timeoutMillis)
            throws InterruptedException, RemotingConnectException, RemotingTooMuchRequestException,
            RemotingTimeoutException, RemotingSendRequestException;

}
