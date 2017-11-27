package com.ict.dtube.remoting;

import com.ict.dtube.remoting.protocol.RemotingCommand;


public interface RPCHook {
    public void doBeforeRequest(final String remoteAddr, final RemotingCommand request);


    public void doAfterResponse(final RemotingCommand request, final RemotingCommand response);
}
