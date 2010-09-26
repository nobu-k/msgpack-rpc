//
// MessagePack-RPC for Java
//
// Copyright (C) 2010 FURUHASHI Sadayuki
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
package org.msgpack.rpc.transport;

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;
import org.msgpack.*;
import org.msgpack.rpc.*;
import org.jboss.xnio.*;
import org.jboss.xnio.channels.*;

class TCPClientSocket extends AbstractStreamChannel {
	private TCPClientTransportPeer transport;
	private Session session;

	public TCPClientSocket(TcpChannel channel, TCPClientTransportPeer transport) {
		super(channel);
		this.transport = transport;
		this.session = transport.getSession();
	}

	public void onClose() {
		transport.removeSocket(this);
	}

	public void onRequest(int msgid, String method, MessagePackObject args) {
		return;  // FIXME error result
	}

	public void onNotify(String method, MessagePackObject args) {
		return;  // FIXME error result?
	}

	public void onResponse(int msgid, MessagePackObject error, MessagePackObject result) {
		session.onResponse(msgid, result, error);
	}
}

