package org.jiumao.remote.service;

public interface RPCHook {
    public void doBeforeRequest(final String remoteAddr, final RemotingCommand request);

    public void doAfterResponse(final RemotingCommand request, final RemotingCommand response);

}
